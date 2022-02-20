package at.ac.tgm.student.pelias.methods;

public class Babylon implements IDelayMethod {
    private long number;

    public Babylon(long number) {
        this.number = number;
    }

    @Override
    public void execute() {
        long precision = 99999999;
        float x = precision * (10*10);
        for (long i = 0; i != precision; i++) {
            x = (1 / 2) * (x + this.number / x);
        }
    }
}
