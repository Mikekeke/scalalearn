/*
Что-то про изменяемый стейт в reader
 */

import cats.data.{Kleisli, ReaderT}
import monix.eval.{MVar, Task}
import monix.execution.Scheduler
import cats.syntax.functor._

import scala.concurrent.Future

case class AppState private(values: MVar[Map[String, String]]) {
  def update(key: String, value: String): Task[Unit] =
    for {
      current <- values.take
      updated = current + (key -> value)
      _ <- values.put(updated)
    } yield ()

  def read: Task[Map[String, String]] = values.read
}

object AppState {
  def create(values: Map[String, String] = Map.empty): Task[AppState] = MVar(values).map(AppState(_))
}

type AppReader[T] = ReaderT[Task, AppState, T]

object AppReader {
  def apply[T](f: AppState => Task[T]): AppReader[T] = Kleisli[Task, AppState, T](f)
  def update(key: String, value: String): AppReader[Unit] = apply(_.update(key, value))
  def read: AppReader[Map[String, String]] = apply(_.read)
}

//// usage

def fun1: AppReader[Int] = AppReader.update("key1", "value1") as 5


def fun2(int: Int): AppReader[String] = AppReader.update("key2", "value2") as int.toString

val reader: AppReader[String] = for {
  r1 <- fun1
  r2 <- fun2(r1)
  vals <- AppReader.read
} yield s"r1 = $r1, r2 = $r2,  vals = $vals"


import Scheduler.Implicits.global

AppState.create().flatMap(reader(_)).runSyncMaybe
//AppState.create().flatMap((smth: AppState) => reader(smth)).runSyncMaybe