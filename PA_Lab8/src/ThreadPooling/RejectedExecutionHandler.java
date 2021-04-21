package ThreadPooling;

import java.util.concurrent.ThreadPoolExecutor;

public class RejectedExecutionHandler implements java.util.concurrent.RejectedExecutionHandler {
    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println(r.toString() + " has been rejected!...");
    }
}
