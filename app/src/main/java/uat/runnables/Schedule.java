package uat.runnables;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Schedule {

    private static ScheduledExecutorService xwyyOutputScheduler;

    public static void startSchedule() {
        RunTask runTask = new RunTask();
        runTask.start();
    }

    public static void createXyyOutputSchedule() {
        xwyyOutputScheduler = Executors.newScheduledThreadPool(1);

        Runnable task = new RunTask();
        long initialDelay = 1;
        long period = 3;
        xwyyOutputScheduler.scheduleAtFixedRate(task, initialDelay, period, TimeUnit.SECONDS);

        try {
            Thread.sleep(10000); // Let the scheduler run for 10 seconds
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public static void stopXyyOutputSchedule() {
        try {
            if (xwyyOutputScheduler != null && !xwyyOutputScheduler.isShutdown()) {
                xwyyOutputScheduler.shutdown();
                RunTask runTask = new RunTask();
                runTask.stop();
                System.out.println("XyyOutput schedule stopped.");
            } else {
                System.out.println("XyyOutput schedule is not running.");
            }
        } catch (Exception e) {
            Thread.currentThread().interrupt();
        }
    }
}
