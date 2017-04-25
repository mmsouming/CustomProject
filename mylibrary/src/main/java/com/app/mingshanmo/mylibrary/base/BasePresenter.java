package com.app.mingshanmo.mylibrary.base;

import android.content.Context;

import com.app.mingshanmo.mylibrary.baserx.RxManager;

/**
 * Created by mingshanmo on 2017/2/26.
 */

public abstract class BasePresenter<T,E>{
    public Context mContext;
    public E mModel;
    public T mView;
    public RxManager mRxManage = new RxManager();

    public void setVM(T v, E m) {
        this.mView = v;
        this.mModel = m;
        this.onStart();
    }
    public void onStart(){
    };
    public void onDestroy() {
        mRxManage.clear();
    }
}
