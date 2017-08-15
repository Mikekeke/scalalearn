package gc_stuff;

public class JavaComa {
    public static void main(String[] args) {
        try {
            Object n = new Object();
            Thread.sleep(1000 * 60);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
