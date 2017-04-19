package com.rkele.app.basedata;

import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;

import com.app.mingshanmo.mylibrary.baseapp.BaseApplication;
import com.app.mingshanmo.mylibrary.baserx.ServerException;
import com.app.mingshanmo.mylibrary.commonutils.NetWorkUtils;
import com.app.mingshanmo.mylibrary.commonutils.ToastUitl;
import com.app.mingshanmo.mylibrary.commonwidget.LoadingDialog;
import com.rkele.app.R;

import rx.Subscriber;

/**
 * Created by ${mms} on 2016/12/26.
 */

public abstract class RxGetDataSubscriber<T extends BaseData> extends Subscriber<T> {

    private Context mContext;
    private String msg;
    private boolean showDialog = true;
    private LoadingDialog loadingDialog;
    private boolean isShowToast = true;


    /**
     * 是否显示浮动dialog
     */
    public void showDialog() {
        this.showDialog = true;
    }

    public void hideDialog() {
        this.showDialog = true;
    }

    /**
     * @param context
     * @param msg        dialog 加载文字
     * @param showDialog 是否加载dialog，默认加载
     */
    public RxGetDataSubscriber(Context context, String msg, boolean showDialog) {
        this.mContext = context;
        this.msg = msg;
        this.showDialog = showDialog;
        loadingDialog = new LoadingDialog(mContext, msg, true);


    }

    /**
     * @param context
     */
    public RxGetDataSubscriber(Context context) {
        this(context, "", true);
    }

    /**
     * @param context
     */
    public RxGetDataSubscriber(Context context, String msg) {
        this(context, "", true);
    }

    /**
     * @param context
     * @param showDialog 显示dialog，默认系统的<string name="loading">Loading...</string>
     */
    public RxGetDataSubscriber(Context context, boolean showDialog) {
        this(context, BaseApplication.getAppContext().getString(R.string.loading), showDialog);
    }

    @Override
    public void onCompleted() {
        if (showDialog)
            loadingDialog.cancelDialogForLoading();
    }

    @Override
    public void onStart() {
        super.onStart();
        if (showDialog) {
            loadingDialog.showDialogForLoading().setOnCancelListener(new DialogInterface.OnCancelListener() {
                @Override
                public void onCancel(DialogInterface dialog) {
                    unsubscribe();
                }
            });

        }
    }


    @Override
    public void onNext(T t) {
        if (!TextUtils.isEmpty(t.getStatus()) && t.getStatus().equals("200")) {
            _onNext(t);
            getDate(t.getData());

        } else {
            _onError(t.getDesc());
            ToastUitl.showShort(t.getDesc());
        }


    }

    @Override
    public void onError(Throwable e) {
        if (showDialog)
            loadingDialog.cancelDialogForLoading();
        e.printStackTrace();
        //网络
        if (!NetWorkUtils.isNetConnected(BaseApplication.getAppContext())) {
            ToastUitl.showShort(BaseApplication.getAppContext().getString(R.string.no_net));
            _onError(BaseApplication.getAppContext().getString(R.string.no_net));
        }
        //服务器
        else if (e instanceof ServerException) {
            ToastUitl.showShort(e.getMessage());
            _onError(e.getMessage());
        }
        //其它
        else {
            ToastUitl.showShort(e.getMessage());
            _onError(BaseApplication.getAppContext().getString(R.string.net_error));
        }
    }

    protected abstract void _onNext(T t);

    public void _onError(String message) {

    }


    public void getDate(Object data) {

    }
}
