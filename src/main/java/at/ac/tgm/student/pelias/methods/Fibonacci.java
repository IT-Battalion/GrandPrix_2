package at.ac.tgm.student.pelias.methods;

public class Fibonacci implements IDelayMethod{
    private final long n;

    public Fibonacci(long n) {
        this.n = n;
    }

    private static long calculate(long n){
        if ((n == 0) || (n == 1))
            return n;
        else
            return calculate(n - 1) + calculate(n - 2);
    }

    @Override
    public void execute() {
        calculate(this.n - 2);
    }
}
