package thrad.pool;

import java.util.List;
import java.util.Map;

/**
 * Created by DEll on 2019-11-14.
 * 该类为数据封装对象，为各种不同格式的数据提供一个统一的封装，方便传递给线程对象
 */
public class DataObject {
    private String state;
    private Map<String, String> params;
    private List<Map<String,  String>> dataPackage;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public List<Map<String, String>> getDataPackage() {
        return dataPackage;
    }

    public void setDataPackage(List<Map<String, String>> dataPackage) {
        this.dataPackage = dataPackage;
    }
}
