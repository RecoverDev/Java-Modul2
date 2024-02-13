import static java.lang.System.out;

public class App {
    public static void main(String[] args) throws Exception {

        Thread a = new ThreadClass("A",0,100);
        Thread b = new ThreadClass("B",101, 200);

        final int startC = 201;
        final int limitC = 300;
        Thread c = new Thread(() -> 
            {for(int i = startC; i < limitC; i++){
                out.printf("%s - %d\n", Thread.currentThread().getName(),i);
            }});
        c.setName("C");


        final int startD = 301;
        final int limitD = 400;
        Thread d = new Thread(() -> 
            {for(int i = startD; i < limitD; i++){
                out.printf("%s - %d\n", Thread.currentThread().getName(),i);
            }},"D");

        a.start();
        b.start();
        c.start();
        d.start();
    }
}
