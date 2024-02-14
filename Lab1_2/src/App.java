public class App {
    public static void main(String[] args) throws Exception {
        runThreads();
        experiment1();
        experiment2();
    }

    private static void runThreads() throws InterruptedException{
        ThreadClass t0 = new ThreadClass("t0");
        ThreadClass t1 = new ThreadClass("t1");

        t0.start();
        t1.setJoin(t0);
        t1.start();

    }

    private static void experiment1() throws InterruptedException{
        ThreadClass t0 = new ThreadClass("t0");
        ThreadClass t1 = new ThreadClass("t1");

        t0.start();
        //Thread.sleep(300);
        t1.setJoin(t0);
        Thread.sleep(300);
        t1.start();

    }

    private static void experiment2() throws InterruptedException{
        ThreadClass t0 = new ThreadClass("t0");
        ThreadClass t1 = new ThreadClass("t1");

        t1.start();
        t1.setJoin(t0);
        t0.start();

    }
}
