package ru.specialist;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.function.DoubleFunction;

public class Integral{
    final private int STEP = 1000;
    final private int COUNT_THREADS = 20;

    private DoubleFunction<Double> func;
    
    public Integral(){}


    public Integral(DoubleFunction<Double> func) {
        this.func = func;
    }


    public DoubleFunction<Double> getFunc() {
        return func;
    }

    public void setFunc(DoubleFunction<Double> func) {
        this.func = func;
    }


    public double calculuteArea(double a, double b){
        double width = (b - a) / STEP;
        double result  = 0d;

        for(int i = 0; i < STEP; i++){
            double x = a + i*width + width/2;
            double y = func.apply(x);
            result += (y*width);
        }

        return result;
    }

    public double calculuteArea(double a, double b, int coutStep){
        double width = (b - a) / coutStep;
        double result  = 0d;

        for(int i = 0; i < coutStep; i++){
            double x = a + i*width + width/2;
            double y = func.apply(x);
            result += (y*width);
        }

        return result;
    }


    public double fixedPoolCalculateArea(double a, double b) throws InterruptedException, ExecutionException{
        ExecutorService service = Executors.newFixedThreadPool(COUNT_THREADS);
        double width = (b - a) / COUNT_THREADS;

        Future<Double>[] tasks = new Future[COUNT_THREADS];
        for(int i = 0; i < COUNT_THREADS; i++){
            double x1 = a + width * i;
            double x2 = x1 + width;
            tasks[i] = service.submit(() -> this.calculuteArea(x1,x2,STEP/COUNT_THREADS));
        }

        double result = 0.0;
        for(int i = 0; i < COUNT_THREADS; i++){
            result += tasks[i].get();
        }

        service.shutdown();
        return result;
    }

    public double ForkJoinCalculateArea(double a, double b){
        double result = 0.0;
        double width = (b - a)/COUNT_THREADS;
        ForkJoinTask<Double>[] tasks = new ForkJoinTask[COUNT_THREADS];

        for(int i = 0; i < COUNT_THREADS;i++){
            double x1 = a + width * i;
            double x2 = x1 + width;
            ForkJoinTask<Double> task = new ForkJoinTask<Double>() {
                double area = 0.0;
                @Override
                public Double getRawResult(){ return area;}
                @Override
                protected void setRawResult(Double value) {area = value; }
                @Override
                protected boolean exec(){
                    area = calculuteArea(x1, x2,STEP/COUNT_THREADS);
                    return true;
                }
            };

            task.fork();
            tasks[i] = task;
        }
        for(ForkJoinTask<Double> task : tasks){
            result += task.join();
        }


        return result;

    }
}