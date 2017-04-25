package com.app.mingshanmo.mylibrary.commonwidget;

import android.content.Context;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.app.mingshanmo.mylibrary.R;


/**
 * Created by ${mms} on 2016/12/16.
 */

public class CustomBottomDialog extends BaseDialog {
    private Window window;
    private LinearLayout ll_dialogcustom;
    private View v;
    private int HeightScale = 0;
    private Context mContext;


    public CustomBottomDialog(Context context) {
        super(context);
        mContext = context;
    }


    public View getV() {
        return v;
    }

    public void setV(View v) {
        this.v = v;
    }

    public CustomBottomDialog(Context context, int menuType) {
        super(context, menuType);
        mContext = context;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_custom);

        // initView(buttonText.length, mTopInfo);
        windowDeploy();
        initView();
        setCanceledOnTouchOutside(false);

    }

    private void initView() {
        ll_dialogcustom = (LinearLayout) findViewById(R.id.ll_dialogcustom);
    }

    private void windowDeploy() {

        window = getWindow();

        // window.setBackgroundDrawableResource(R.color.white);

        window.setWindowAnimations(R.style.bottom_dialog_buy_ani);

        WindowManager.LayoutParams wl = window.getAttributes();

        WindowManager windowManager = window.getWindowManager();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);

        wl.width = displayMetrics.widthPixels;
        if (HeightScale == 0) {
            wl.height = ViewGroup.LayoutParams.WRAP_CONTENT;

        } else {
            wl.height = displayMetrics.heightPixels * 2 / 3;
        }

        // wl.alpha = 1.5f;
        wl.gravity = Gravity.BOTTOM;
        window.setAttributes(wl);
    }

    public void addChildView(int layout) {
        // TODO Auto-generated method stub
        v = LayoutInflater.from(mContext).inflate(layout, null);
        ll_dialogcustom.addView(v);

    }

    public int getHeightScale() {
        return HeightScale;
    }

    public void setHeightScale(int heightScale) {
        HeightScale = heightScale;
    }
}
