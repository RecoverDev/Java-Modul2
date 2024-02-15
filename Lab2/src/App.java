import static java.lang.System.out;
import static java.lang.Math.*;

public class App {
    public static void main(String[] args) throws Exception {
       
        Sync sync = new Sync(1);

        Thread t0 = new Thread(() ->
        {
            for(int i = 0; i < 10; i++){
                out.printf("Sin(%f) = ", sync.getX());
                sync.setX(sin(sync.getX()));
                out.println(sync.getX());
                synchronized(sync){
                    sync.notify();
                    try {
                        sync.wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Thread-0");

        Thread t1 = new Thread(() ->
        {
            for(int i = 0; i < 10; i++){
                if(!sync.isFlag()){
                    synchronized(sync){
                        try {
                            sync.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
                out.printf("arcSin(%f) = ", sync.getX());
                sync.setX(asin(sync.getX()));
                sync.setFlag(true);
                out.println(sync.getX());
                synchronized(sync){
                    sync.notify();
                    try {
                        sync.wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        },"Thread-1");

        t0.start();
        t1.start();


    }
}
