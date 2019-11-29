package thrad.pool.interf;

import thrad.pool.DataObject;

/**
 * Created by DEll on 2019-11-14.
 * 接口为线程任务接受对象的统一抽象。
 */
public interface BatchDataHandlerInterface {

    /**
     * 本方法为线程任务分发方法，用于线程远程调用并传递参数
     * @param params
     */
    public void taskHandler(DataObject params);
}
