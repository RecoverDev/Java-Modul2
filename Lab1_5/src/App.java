import ru.specialist.*;
import static java.lang.System.out;

public class App {
    public static void main(String[] args) throws Exception {

        Integral integral = new Integral(Math::sin);

        long t1 = System.currentTimeMillis();
        out.printf("Single %.2f \n", integral.calculuteArea(0, Math.PI/2));
        out.printf("time: %d \n", System.currentTimeMillis() - t1);

        long t2 = System.currentTimeMillis();
        out.printf("Fixed Pool %.2f \n", integral.fixedPoolCalculateArea(0, Math.PI/2));
        out.printf("time: %d \n", System.currentTimeMillis() - t2);

        long t3 = System.currentTimeMillis();
        out.printf("ForkJoin %.2f \n", integral.fixedPoolCalculateArea(0, Math.PI/2));
        out.printf("time: %d \n", System.currentTimeMillis() - t3);
    }
}
