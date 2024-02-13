import static java.lang.System.out;

public class ThreadClass extends Thread{

    private int limitValue = 0;
    private int startValue = 0;

    public void setStartValue(int startValue) {
        this.startValue = startValue;
    }

    public void setLimitValue(int limit) {
        this.limitValue = limit;
    }

    public ThreadClass(String name){
        super(name);
    }

    public ThreadClass(String name, int limit){
        this(name);
        this.limitValue = limit;
    }

    public ThreadClass(String name, int start, int limit){
        this(name);
        this.startValue = start;
        this.limitValue = limit;
    }

    
    @Override
    public void run(){
        if(limitValue == 0){
            limitValue = 100;
        }
        if(this.startValue > this.limitValue){
            startValue = limitValue - 100;
        }
        for(int i = this.startValue; i < this.limitValue; i++){
            out.printf("%s - %d \n", this.getName(),i);
        }
    }
    
}
