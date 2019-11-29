package thrad.pool;

import thrad.pool.interf.BatchDataHandlerInterface;

/**
 * Created by DEll on 2019-11-14.
 * 本类声明放入线程池的线程对象
 * 本类接受回调对象和回调参数
 * 线程执行时会调用回调对象的回调方法，并传递回调参数。
 */
public class DataHandlerTask implements Runnable{
    private DataObject params = null;
    private BatchDataHandlerInterface handler = null ;
    private ThreadPool pool = ThreadPool.getInstance();

    public DataHandlerTask(BatchDataHandlerInterface handler, DataObject params){
        this.setParams(handler, params);
    }

    /**
     *
     * @param handler 回调对象
     * @param params 回调参数
     */
    public void setParams(BatchDataHandlerInterface handler, DataObject params){
        this.handler = handler;
        this.params = params;
    }

    @Override
    public void run() {
        handler.taskHandler(params);
        pool.controlNotifyDefault();
    }
}
