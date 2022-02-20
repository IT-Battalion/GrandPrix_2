package at.ac.tgm.student.pelias;

import at.ac.tgm.student.pelias.methods.IDelayMethod;

public class Runner implements Runnable {
    private final IDelayMethod method;
    private final SketchyWaiter latch;

    public Runner(SketchyWaiter latch, IDelayMethod method) {
        this.latch = latch;
        this.method = method;
    }

    @Override
    public void run() {
        latch.readySignal();
        System.out.println(Thread.currentThread().getName() + " is ready.");
        latch.await();
        for (int i = 0; i != 3; i++) {
            long before = System.currentTimeMillis();
            this.method.execute();
            long after = System.currentTimeMillis();
            System.out.println(Thread.currentThread().getName() + " finished round: " + i + " in: "  + (after - before) + "ms.");
        }
        latch.finished();
    }
}
