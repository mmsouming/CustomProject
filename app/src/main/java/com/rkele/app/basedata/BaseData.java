package com.rkele.app.basedata;

import java.io.Serializable;

/**
 * Created by mingshanmo on 2017/3/2.
 */

public class BaseData<T> implements Serializable{

    public String status;
    public String desc;

    public T data;

    public boolean success() {
        return "200".equals(status);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "BaseRespose{" +
                "code='" + status + '\'' +
                ", msg='" + desc + '\'' +
                ", data=" + data +
                '}';
    }
}
