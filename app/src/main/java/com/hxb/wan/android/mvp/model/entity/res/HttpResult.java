package com.hxb.wan.android.mvp.model.entity.res;

/**
 * author:  ljy
 * date:    2017/9/27
 * description: （用于豆瓣电影接口）网络请求回调的统一实体。这里是根据豆瓣接口返回的数据结构而定义的。
 *               开发中，服务器返回的数据结构一般为“状态值”，“描述信息”，“内容数据实体”。请根据具体结构进行调整
 */

public class HttpResult<T> {

    private int errorCode;
    private String errorMsg;
    private T data;

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
