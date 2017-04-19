package com.rkele.app.app;


import com.app.mingshanmo.mylibrary.baseapp.BaseApplication;
import com.app.mingshanmo.mylibrary.commonutils.LogUtils;
import com.umpay.payplugin.UMPay;

/**
 * APPLICATION
 */
public class AppApplication extends BaseApplication {
    @Override
    public void onCreate() {
        super.onCreate();
        UMPay.getInstance().debug(true);
        //初始化logger
        LogUtils.logInit(true);
    }
}
