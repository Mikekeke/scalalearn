
// https://www.youtube.com/watch?v=YXDm3WHZT5g

import cats.data.Kleisli
import cats.implicits._

final case class Config(fullName: String, age: Int)
case class Name(fst: String, snd: String)
case class Age(years: Int)
case class Person(name: Name, age: Age)

val readName: Config => Option[Name] = cfg => {
  val res = cfg.fullName.split(" ")
  if (res.length < 2) None else Some(Name(res.head, res.tail.mkString(" ")))
}
val readAge: Config => Option[Age] = cfg => {
  val age = cfg.age
  if (age >= 1 && age <= 150) Option(Age(age)) else None
}
def readPerson: Config => Option[Person] = cfg =>
  for {
    name <- readName(cfg)
    age <- readAge(cfg)
  } yield Person(name, age)

def readPersonDesugared: Config => Option[Person] = cfg =>
  readName(cfg).flatMap(name => readAge(cfg).map(age => Person(name, age)))

def readAgeK: Kleisli[Option, Config, Age] = Kleisli(cfg => {
  val age = cfg.age
  if (age >= 1 && age <= 150) Option(Age(age)) else None
})

def readPersonK: Kleisli[Option, Config, Person] =
  for {
    name <- Kleisli(readName)
    age <- readAgeK
  } yield Person(name, age)

def readPersonKDesugared: Kleisli[Option, Config, Person] =
  Kleisli(readName).flatMap(name => Kleisli(readAge).map(age => Person(name, age)))

val validConf = Config("Bob Lol", 44)
val invalidConf = Config("Bob Dead", 500)

readPerson(validConf)
readPerson(invalidConf)

readPersonK(validConf)
readPersonK(invalidConf)

readPersonDesugared(validConf)
readPersonDesugared(invalidConf)

