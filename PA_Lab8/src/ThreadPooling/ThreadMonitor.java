package ThreadPooling;

import java.util.concurrent.ThreadPoolExecutor;

public class ThreadMonitor implements Runnable {
    private final ThreadPoolExecutor executor;
    private final int seconds;
    private boolean run = true;

    public ThreadMonitor(ThreadPoolExecutor executor, int delay) {
        this.executor = executor;
        this.seconds = delay;
    }

    public void shutdown() {
        this.run = false;
    }

    @Override
    public void run() {
        while (this.run) {
            System.out.println(String.format("[MONITOR] : [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                    this.executor.getPoolSize(),
                    this.executor.getCorePoolSize(),
                    this.executor.getActiveCount(),
                    this.executor.getCompletedTaskCount(),
                    this.executor.getTaskCount(),
                    this.executor.isShutdown(),
                    this.executor.isTerminated()));
            try {
                Thread.sleep(seconds * 1000);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }

        }
    }
}
