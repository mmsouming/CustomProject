package com.rkele.app.ui.main.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.mingshanmo.mylibrary.base.BaseActivity;
import com.rkele.app.R;
import com.rkele.app.app.AppConstant;
import com.rkele.app.basedata.OrderByVoucherBean;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ConfirmPointsActivity extends BaseActivity {


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
    @Bind(R.id.et_phone_verification)
    EditText etPhoneVerification;
    @Bind(R.id.tv_phone_point)
    TextView tvPhonePoint;
    @Bind(R.id.tv_phone_num)
    TextView tvPhoneNum;
    @Bind(R.id.btn_submit_order)
    Button btnSubmitOrder;
    private OrderByVoucherBean orderByVoucherBean;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_points;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        initTopBar();
        initData();

    }

    private void initData() {
        orderByVoucherBean = (OrderByVoucherBean) this.getIntent().getSerializableExtra(AppConstant.ORDERINFO);

        tvPhonePoint.setText(orderByVoucherBean.getTotalMoney());
        tvPhoneNum.setText(orderByVoucherBean.getPhone());
    }

    private void initTopBar() {
        toolbar.setNavigationIcon(R.drawable.back1);
        toolbar.setTitle("和包下单");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    finishAfterTransition();
                } else {
                    finish();
                }
            }
        });
    }


    @OnClick(R.id.btn_submit_order)
    public void onClick() {
//        mRxManager.add(mModel);
    }
}
