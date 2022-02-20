package at.ac.tgm.student.pelias;

import java.util.concurrent.CopyOnWriteArrayList;

public class SketchyWaiter {
    private final CopyOnWriteArrayList<String> ranking = new CopyOnWriteArrayList<>();
    private int readySignals = 0;
    private int needed = 0;

    public void readySignal() {
        readySignals++;
    }

    public synchronized void await() {
        while (this.needed != this.readySignals) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void sketch(int needed) {
        this.needed = needed;
        while (readySignals != this.needed) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public synchronized void finished() {
        this.ranking.add(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " finished. #" + (this.ranking.indexOf(Thread.currentThread().getName()) + 1));
    }
}
