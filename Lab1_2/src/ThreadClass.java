import static java.lang.System.out;

public class ThreadClass extends Thread{


    public ThreadClass(String name){
        super(name);
    }


    @Override
    public void run(){
        for(int i = 0; i < 1000; i++){
            out.printf("%s - %d\n", this.getName(),i);
        }
    }

    public void setJoin(Thread t) throws InterruptedException{
        t.join(10000);
    }
    
}
