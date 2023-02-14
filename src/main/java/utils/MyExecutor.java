package utils;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyExecutor {
    public static ExecutorService executor = Executors.newFixedThreadPool(16);

    public static ExecutorService getExecutor() {
        return executor;
    }
}
