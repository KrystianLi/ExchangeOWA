package utils;

import java.util.concurrent.*;

public class MyExecutor {
    private ThreadPoolExecutor executor;
    private final Integer MAXSIZE = 20;
    private final Integer KEEPALIVE = 60;
    private final Integer BLOCKQUEUESIZE = 1000;

    /**
     * 创建一个默认的线程池
     * @return 返回一个2线程，最大线程数5，空闲线程存活时间为60秒，队列大小为20的线程池
     */
    public static ThreadPoolExecutor getExecutor() {
        return threadPoolExecutor(2,5,60,20);
    }

    /**
     * 返回线程池
     * @param coreSize  核心线程数
     * @param maxSize   最大线程数（队列满了，就会使用最大线程数来运行）
     * @param keepalive 空闲线程存活时间，单位秒
     * @param blockQueueSize    任务队列，用来储存等待执行任务的队列
     * @return
     */
    public static ThreadPoolExecutor threadPoolExecutor(Integer coreSize, Integer maxSize, Integer keepalive, Integer blockQueueSize) {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                coreSize,
                maxSize,
                keepalive,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(blockQueueSize),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        executor.prestartCoreThread();
        return executor;
    }

    public ThreadPoolExecutor threadPoolExecutor(Integer coreSize) {
        executor = new ThreadPoolExecutor(
                coreSize,
                MAXSIZE,
                KEEPALIVE,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(BLOCKQUEUESIZE),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
        executor.prestartCoreThread();
        return executor;
    }

    public void stopPoolExecutor(){
        try {
            executor.shutdownNow();
        }catch (Exception e){
            System.out.println(e.getStackTrace());
        }
    }
}
