package at.ac.tgm.student.pelias;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.LinkedBlockingDeque;

public class Waiter {
    private BlockingDeque<String> queue;
    private final CopyOnWriteArrayList<String> ranking = new CopyOnWriteArrayList<>();
    private int readySignals = 0;
    private int needed = 0;

    private int finished = 0;

    public void readySignal() {
        readySignals++;
    }

    public synchronized void await() {
        while (this.needed != this.readySignals) {
            try {
                this.wait();
            } catch (InterruptedException e) {}
        }
        this.notifyAll();
    }

    public synchronized void wait(int needed) {
        this.needed = needed;
        this.queue = new LinkedBlockingDeque<>(needed * 3);
        while (readySignals != this.needed) {
            try {
                this.wait();
            } catch (InterruptedException e) {}
        }
    }

    public BlockingDeque<String> getQueue() {
        return queue;
    }

    public synchronized void finished() {
        this.ranking.add(Thread.currentThread().getName());
        System.out.println(Thread.currentThread().getName() + " finished. #" + (this.ranking.indexOf(Thread.currentThread().getName()) + 1));
        finished++;
    }

    public synchronized boolean hasFinished() {
        return this.finished == this.needed;
    }
}
