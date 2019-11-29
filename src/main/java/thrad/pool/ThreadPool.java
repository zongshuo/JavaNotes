package thrad.pool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by DEll on 2019-11-14.
 */
public class ThreadPool {
    private ThreadPoolExecutor pool = null;
    private int queueMaxCount = 10000;
    private volatile Thread control = new Thread();
    private static ThreadPool threadPool = null;

    private ThreadPool(){
        this.pool = new ThreadPoolExecutor(8, 16, 20, TimeUnit.SECONDS, new LinkedBlockingDeque<Runnable>(this.queueMaxCount));
        this.pool.prestartAllCoreThreads();
        this.control.start();
    }

    /**
     * 单例获取线程池管理对象
     * @return
     */
    public static ThreadPool getInstance(){
        if(null == threadPool){
            synchronized (ThreadPool.class){
                if(null == threadPool){
                    threadPool = new ThreadPool();
                }
            }
        }
        return threadPool;
    }

    /**
     * 终止线程池运行
     */
    public void shutDownPool(){
        if (!this.pool.isShutdown()){
            this.pool.shutdown();
        }
    }

    /**
     * 获取线程池队列的最大长度
     * @return
     */
    public int getQueueNum(){
        return this.queueMaxCount;
    }

    /**
     * 获取线程池队列中剩余线程数
     * @return
     */
    public long getThreadCount(){
        return this.pool.getQueue().size();
    }

    /**
     * 获取运行中的线程的数量
     * @return
     */
    public long getActiveCount(){
        return this.pool.getActiveCount();
    }

    /**
     * 向线程池中添加线程
     * @param task
     */
    public void addThread(Runnable task){
        if(pool.getQueue().size() == this.queueMaxCount){
            this.controlWait();
        }
        pool.execute(task);
    }

    /**
     * 让控制线程变为等待状态，该状态下线程池队列不添加线程
     */
    public  void controlWait(){
        synchronized (this.control){
            try {
                this.control.wait(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 唤醒控制线程，如果线程处于非活跃状态则唤醒
     */
    public void controlNotify(){
        synchronized (this.control){
            if(!this.control.isAlive()){
                this.control.notify();
            }
        }
    }

    /**
     * 当线程池队列中的剩余线程数为一定数量时唤醒线程
     * @param count
     */
    public void controlNotifyWhenCount(int count){
        if(this.getThreadCount() > count) return ;
        this.controlNotify();
    }

    /**
     * 默认线程池队列中的线程数量为最大值的三分之一时唤醒控制线程
     */
    public void controlNotifyDefault(){
        controlNotifyWhenCount(queueMaxCount/3);
    }
}
