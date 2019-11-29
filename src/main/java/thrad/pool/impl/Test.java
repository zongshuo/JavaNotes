package thrad.pool.impl;

import thrad.pool.DataHandlerTask;
import thrad.pool.DataObject;
import thrad.pool.ThreadPool;
import thrad.pool.interf.BatchDataHandlerInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by DEll on 2019-11-14.
 */
public class Test implements BatchDataHandlerInterface{


    public void setTask(){
        ThreadPool pool = ThreadPool.getInstance();
        for(int i=0 ; i<100 ; i++){
            List<Map<String, String>> dataPackage = new ArrayList<>();
            DataObject data = new DataObject();
            data.setDataPackage(dataPackage);
            pool.addThread(new DataHandlerTask(this, data));
        }

        while (pool.getThreadCount() > 0 || pool.getActiveCount() > 0){
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void taskHandler(DataObject params) {
        //用params做一些事情
    }
}
