package at.ac.tgm.student.pelias;

import at.ac.tgm.student.pelias.methods.Babylon;
import at.ac.tgm.student.pelias.methods.Fibonacci;
import at.ac.tgm.student.pelias.methods.NetworkRequest;

/**
 * :D
 * @author Patrick Elias
 * @version 2022-02-20
 */
public class Main {
    public static void main(String[] args) {
        SketchyWaiter latch = new SketchyWaiter();
        Runner runner1 = new Runner(latch, new Fibonacci(44));
        Runner runner2 = new Runner(latch, new Babylon(1253120048));
        Runner runner3 = new Runner(latch, new NetworkRequest());
        Thread t1 = new Thread(runner1, "Runner-1");
        Thread t2 = new Thread(runner2, "Runner-2");
        Thread t3 = new Thread(runner3, "Runner-3");
        t1.start();
        t2.start();
        t3.start();
        latch.sketch(3);
    }
}
