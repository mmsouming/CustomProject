package com.rkele.app.ui.main.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.mingshanmo.mylibrary.base.BaseActivity;
import com.app.mingshanmo.mylibrary.baserx.RxSchedulers;
import com.app.mingshanmo.mylibrary.commonutils.ToastUitl;
import com.rkele.app.R;
import com.rkele.app.api.Api;
import com.rkele.app.app.AppConstant;
import com.rkele.app.basedata.BaseData;
import com.rkele.app.basedata.OrderByVoucherBean;
import com.rkele.app.basedata.RxGetDataSubscriber;
import com.rkele.app.bean.HxOrderByVoucherBean;
import com.rkele.app.utils.SPUtils;

import java.util.Map;

import butterknife.Bind;
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
    Map<String, Object> map;

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

        if (TextUtils.isEmpty(etPhoneVerification.getText())){
            ToastUitl.showLong("请输入手机验证码");
            return;
        }
        map = new ArrayMap<>();
        map.put("orderCode", orderByVoucherBean.getOrderCode());
        map.put("vorcherStatus", orderByVoucherBean.getVorcherStatus());
        map.put("smsCode", etPhoneVerification.getText().toString());
        map.put("vorcherCode", orderByVoucherBean.getVorcherCode());
        map.put("token", SPUtils.get(mContext, SPUtils.TOKEN, "").toString());


        initConfirm();
    }

    private void initConfirm() {
        mRxManager.add(Api.getDefault().hxOrderByVoucher(map).compose(RxSchedulers.<BaseData<HxOrderByVoucherBean>>io_main()).subscribe(new RxGetDataSubscriber<BaseData<HxOrderByVoucherBean>>(mContext, true) {
            @Override
            protected void _onNext(BaseData<HxOrderByVoucherBean> hxOrderByVoucherBeanBaseData) {
                startActivity(new Intent(mContext, BuySuccessfullyActivity.class).putExtra(AppConstant.ORDERINFO, hxOrderByVoucherBeanBaseData.getData()));
                finish();
            }
        }));

    }
}
