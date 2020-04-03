package com.hxb.wan.android.mvp.model.entity.event;

import com.hxb.wan.android.app.constant.Constants;
import com.ljy.devring.util.DataSPUtils;

/**
 * @Author :hexingbo
 * @Date :2020/4/3
 * @FileName： MainDataEvent
 * @Describe :
 */
public class MainDataEvent {

    private static MainDataEvent dataEvent;


    public static MainDataEvent init() {
        if (dataEvent == null) {
            dataEvent = new MainDataEvent();
        }
        return dataEvent;
    }


    /**
     * 保存登陆信息
     *
     * @param userName
     * @param userHead
     * @param userCollectedNumber
     */
    public MainDataEvent saveLoginInfo(String userName, String userHead, int userCollectedNumber) {
        DataSPUtils.putBoolean(Constants.SP_IsLogin, true);
        setUserCollectedNumber(userCollectedNumber);
        setUserHead(userHead);
        setUserName(userName);
        return dataEvent;
    }

    /**
     * 清除登录信息
     */
    public MainDataEvent cleanLoginInfo() {
        DataSPUtils.putBoolean(Constants.SP_IsLogin, false);
        setUserCollectedNumber(0);
        setUserHead("");
        setUserName("");
        return dataEvent;
    }


    public boolean getLogin() {
        return DataSPUtils.getBoolean(Constants.SP_IsLogin, false);
    }


    public String getUserHead() {
        return DataSPUtils.getString(Constants.SP_UserHead, "");
    }

    public void setUserHead(String userHead) {
        DataSPUtils.putString(Constants.SP_UserHead, userHead);
    }

    public String getUserName() {
        return DataSPUtils.getString(Constants.SP_UserName, "");
    }

    public void setUserName(String userName) {
        DataSPUtils.putString(Constants.SP_UserName, userName);
    }

    public int getUserCollectedNumber() {
        return DataSPUtils.getInt(Constants.SP_UserCollectedNumber, 0);
    }

    public void setUserCollectedNumber(int userCollectedNumber) {
        DataSPUtils.putInt(Constants.SP_UserCollectedNumber, userCollectedNumber);
    }
}
