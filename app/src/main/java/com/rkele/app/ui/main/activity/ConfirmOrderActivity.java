package com.rkele.app.ui.main.activity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.mingshanmo.mylibrary.base.BaseActivity;
import com.app.mingshanmo.mylibrary.baserx.RxSchedulers;
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
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public class ConfirmOrderActivity extends BaseActivity {

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
    @Bind(R.id.tv_commercial)
    TextView tvCommercial;
    @Bind(R.id.tv_coupon)
    TextView tvCoupon;
    @Bind(R.id.tv_money)
    TextView tvMoney;
    @Bind(R.id.tv_couponmoney)
    TextView tvCouponmoney;
    @Bind(R.id.tv_ordermoney)
    TextView tvOrdermoney;
    @Bind(R.id.tv_balance)
    TextView tvBalance;
    @Bind(R.id.tv_order_time)
    TextView tvOrderTime;
    @Bind(R.id.btn_confirm_use)
    Button btnConfirmUse;
    private OrderByVoucherBean orderByVoucherBean;

    Map<String, Object> map;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirm_order;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
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

        orderByVoucherBean = (OrderByVoucherBean) this.getIntent().getSerializableExtra(AppConstant.ORDERINFO);
        initData();
    }

    private void initData() {
        tvCommercial.setText(""+SPUtils.get(mContext,SPUtils.NAME,""));

        if (null!=orderByVoucherBean.getVorcherCode()&&!TextUtils.isEmpty(orderByVoucherBean.getVorcherCode())){

            tvCoupon.setText("" + orderByVoucherBean.getVorcherCode());//通兑劵码
        }
        if (null!=orderByVoucherBean.getVoucherMoney()&&!TextUtils.isEmpty(orderByVoucherBean.getVoucherMoney())){

        tvMoney.setText("¥" + orderByVoucherBean.getVoucherMoney());//通兑劵码金额
        }else{
            tvMoney.setText("¥" + "0.00");
        }
        if (null!=orderByVoucherBean.getVoucherMoney()&&!TextUtils.isEmpty(orderByVoucherBean.getVoucherMoney())){

            tvMoney.setText("¥" + orderByVoucherBean.getVoucherMoney());
        }
        if (null!=orderByVoucherBean.getHxMoney()&&!TextUtils.isEmpty(orderByVoucherBean.getHxMoney())){

            tvCouponmoney.setText("¥" + orderByVoucherBean.getHxMoney());//通用代金券金额:
        }
        if (null!=orderByVoucherBean.getTotalMoney()&&!TextUtils.isEmpty(orderByVoucherBean.getTotalMoney())){

            tvOrdermoney.setText("¥" + orderByVoucherBean.getTotalMoney());
        }
        if (null!=orderByVoucherBean.getOrderTime()&&!TextUtils.isEmpty(orderByVoucherBean.getOrderTime())){

            tvOrderTime.setText(orderByVoucherBean.getOrderTime());
        }







    }


    @OnClick(R.id.btn_confirm_use)
    public void onClick() {
//        startActivity(new Intent(mContext, BuySuccessfullyActivity.class));
          map = new ArrayMap<>();
        map.put("orderCode",orderByVoucherBean.getOrderCode());
        map.put("vorcherStatus",orderByVoucherBean.getVorcherStatus());
        map.put("vorcherCode",orderByVoucherBean.getVorcherCode());
        map.put("token",SPUtils.get(mContext, SPUtils.TOKEN, "").toString());


        initConfirm();


    }

    private void initConfirm() {


        mRxManager.add(Api.getDefault().hxOrderByVoucher(map).compose(RxSchedulers.<BaseData<HxOrderByVoucherBean>>io_main()).subscribe(new RxGetDataSubscriber<BaseData<HxOrderByVoucherBean>>(mContext,true) {
            @Override
            protected void _onNext(BaseData<HxOrderByVoucherBean> hxOrderByVoucherBeanBaseData) {
                startActivity(new Intent(mContext, BuySuccessfullyActivity.class).putExtra(AppConstant.ORDERINFO, hxOrderByVoucherBeanBaseData.getData()));
                finish();
            }
        }));
    }


}
