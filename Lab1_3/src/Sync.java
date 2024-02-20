public class Sync {
    private volatile double x;
    private boolean flag;

    public Sync(double x){
        this.setX(x);
        this.flag = false;
    }

    public synchronized boolean isFlag() {
        return flag;
    }

    public synchronized void setFlag(boolean flag) {
        this.flag = flag;
    }

    public synchronized void setX(double x){
        this.x = x;
    }

    public synchronized double getX(){
        return this.x;
    }
}
