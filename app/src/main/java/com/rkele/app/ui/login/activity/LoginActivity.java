package com.rkele.app.ui.login.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.mingshanmo.mylibrary.base.BaseActivity;
import com.app.mingshanmo.mylibrary.baserx.RxSchedulers;
import com.rkele.app.R;
import com.rkele.app.api.Api;
import com.rkele.app.basedata.BaseData;
import com.rkele.app.basedata.RxGetDataSubscriber;
import com.rkele.app.bean.LoginBean;
import com.rkele.app.ui.main.activity.MainActivity;
import com.rkele.app.utils.SPUtils;
import com.rkele.app.widget.CustomEditText;
import com.umpay.payplugin.UMPay;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseActivity {


    @Bind(R.id.toolbar_title)
    TextView toolbarTitle;
    @Bind(R.id.right_iv)
    ImageView rightIv;
    @Bind(R.id.tv_right)
    TextView tvRight;
    @Bind(R.id.rl_right)
    RelativeLayout rlRight;
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.tv_business_name)
    CustomEditText tvBusinessName;
    @Bind(R.id.et_password)
    CustomEditText etPassword;
    @Bind(R.id.cb_hide_show)
    CheckBox cbHideShow;
    @Bind(R.id.email_sign_in_button)
    Button emailSignInButton;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

        toolbar.setNavigationIcon(R.drawable.back1);
        toolbar.setTitle("登录");

        initPrient();
        UMPay.getInstance();

        cbHideShow.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //明文显示
                    etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //密文显示
                    etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                etPassword.setSelection(etPassword.length());
            }
        });
    }

    private void initPrient() {

    }


    @OnClick(R.id.email_sign_in_button)
    public void onClick() {

        mRxManager.add(Api.getDefault().login(tvBusinessName.getText().toString(), etPassword.getText().toString()).
                compose(RxSchedulers.<BaseData<LoginBean>>io_main()).subscribe(new RxGetDataSubscriber<BaseData<LoginBean>>(mContext, true) {
            @Override
            protected void _onNext(BaseData<LoginBean> loginBeanBaseData) {
                SPUtils.put(mContext, SPUtils.ACCOUNT, tvBusinessName.getText().toString());
                SPUtils.put(mContext, SPUtils.PASSWORD, etPassword.getText().toString());
                SPUtils.put(mContext, SPUtils.TOKEN, loginBeanBaseData.getData().getToken());
                SPUtils.put(mContext, SPUtils.MERID, loginBeanBaseData.getData().getMerId());

                SPUtils.put(mContext, SPUtils.MERNUMBER, loginBeanBaseData.getData().getMerNumber());
                SPUtils.put(mContext, SPUtils.NAME, loginBeanBaseData.getData().getName());
                SPUtils.put(mContext, SPUtils.PRICEUNIT, loginBeanBaseData.getData().getPriceUnit());

                startActivity(new Intent(mContext, MainActivity.class));
                finish();


            }
        }));

    }



}
