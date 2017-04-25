package com.app.mingshanmo.mylibrary.base;

/**
 * Created by mingshanmo on 2017/2/26.
 */

public interface BaseView {
    /*******内嵌加载*******/
    void showLoading(String title);
    void stopLoading();
    void showErrorTip(String msg);
}
