package com.rkele.app.ui.main.activity;

import android.os.Bundle;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.app.mingshanmo.mylibrary.base.BaseActivity;
import com.app.mingshanmo.mylibrary.commonwidget.CommonAdapter;
import com.app.mingshanmo.mylibrary.commonwidget.NoScrollListview;
import com.app.mingshanmo.mylibrary.commonwidget.ViewHolder;
import com.rkele.app.R;
import com.rkele.app.app.AppConstant;
import com.rkele.app.bean.HxOrderByVoucherBean;
import com.rkele.app.bean.VoucherBean;
import com.rkele.app.utils.SPUtils;
import com.rkele.app.widget.ExtendedEditText;
import com.umpay.payplugin.UMPay;
import com.umpay.payplugin.callback.BasePrintCallback;
import com.umpay.payplugin.callback.UMBindCallBack;
import com.umpay.payplugin.printUtils.PrintUtils;
import com.umpay.payplugin.util.UMPayLog;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.id.list;

public class BuySuccessfullyActivity extends BaseActivity {


    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.tv_sucess)
    TextView tvSucess;
    @Bind(R.id.tv_tenant)
    TextView tvTenant;
    @Bind(R.id.tv_tenantname)
    TextView tvTenantname;
    @Bind(R.id.tv_deal)
    TextView tvDeal;
    @Bind(R.id.tv_order)
    TextView tvOrder;
    @Bind(R.id.tv_ordertime)
    TextView tvOrdertime;
    @Bind(R.id.tv_moeny)
    TextView tvMoeny;
    @Bind(R.id.tv_allmoney)
    TextView tvAllmoney;
    @Bind(R.id.tv_phonenumber)
    TextView tvPhonenumber;
    @Bind(R.id.tv_state)
    TextView tvState;
    @Bind(R.id.tv_orderstate)
    TextView tvOrderstate;
    @Bind(R.id.tv_orderdetail)
    TextView tvOrderdetail;
    @Bind(R.id.tv_dealnumber)
    TextView tvDealnumber;
    @Bind(R.id.tv_phone)
    TextView tvPhone;
    @Bind(R.id.lv_pro_list)
    NoScrollListview lvProList;
    @Bind(R.id.fab)
    FloatingActionButton fab;
    private HxOrderByVoucherBean hxOrderByVoucherBean;

    private String fontType = "simsun.ttc";
    private CommonAdapter<HxOrderByVoucherBean.ProductBean> proAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_buy_successfully;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        registerPrint();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                print();
            }
        });

        hxOrderByVoucherBean = (HxOrderByVoucherBean) this.getIntent().getSerializableExtra(AppConstant.ORDERINFO);
        initData();
    }

    private void print() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();

        try {
            //打印文字
            jsonArray.put(PrintUtils.setStringContent("购买成功", 2, 4));
            jsonArray.put(PrintUtils.setStringContent("交易单号：" + hxOrderByVoucherBean.getOrderCode(), 2, 2));
            jsonArray.put(PrintUtils.setStringContent("商户名称"+SPUtils.get(mContext,SPUtils.NAME,""), 2, 2));
            jsonArray.put(PrintUtils.setStringContent("订单总金额" + hxOrderByVoucherBean.getTotalMoney(), 2, 3));
            jsonArray.put(PrintUtils.setStringContent("手机号码:" + hxOrderByVoucherBean.getPhone(), 2, 2));
            jsonArray.put(PrintUtils.setStringContent("订单状态:交易成功", 2, 2));
            jsonArray.put(PrintUtils.setStringContent("订单详细", 1, 3));
            jsonArray.put(PrintUtils.setbitmap(2));
            jsonArray.put(PrintUtils.setStringContent("--------------------------------", 2, 1));
            for (int i = 0; i < hxOrderByVoucherBean.getProduct().size(); i++) {
                jsonArray.put(PrintUtils.setStringContent(hxOrderByVoucherBean.getProduct().get(i).getProName()+"-----"+hxOrderByVoucherBean.getProduct().get(i).getNumber(),2,1));
                jsonArray.put(PrintUtils.setfreeLine("1"));
            }
            jsonArray.put(PrintUtils.setfreeLine("3"));

            jsonObject.put("spos", jsonArray);

        } catch (JSONException e) {
            e.printStackTrace();
        }


        UMPay.getInstance().print(jsonObject.toString(), null, fontType, 4, new BasePrintCallback() {
            @Override
            public void onStart() throws RemoteException {

            }

            @Override
            public void onFinish() throws RemoteException {

            }

            @Override
            public void onError(int i, String s) throws RemoteException {

            }
        });


    }

    private void registerPrint() {

        UMPay.getInstance().bind(mContext, new UMBindCallBack() {
            @Override
            public void bindException(Exception e) {
                UMPayLog.e("绑定失败！" + e.getMessage());
            }

            @Override
            public void bindSuccess() {
                UMPayLog.e("绑定成功！");
            }

            @Override
            public void bindDisconnected() {
                UMPayLog.e("断开绑定！");
            }
        });


    }

    private void initData() {
        tvTenantname.setText(""+ SPUtils.get(mContext,SPUtils.NAME,""));
        tvDealnumber.setText(hxOrderByVoucherBean.getOrderCode());
        tvOrdertime.setText(hxOrderByVoucherBean.getOrderTime());
        tvAllmoney.setText("¥" + hxOrderByVoucherBean.getTotalMoney() + "元");
        tvPhonenumber.setText(hxOrderByVoucherBean.getPhone());

        proAdapter = new CommonAdapter<HxOrderByVoucherBean.ProductBean>(mContext, R.layout.item_pro_list) {
            @Override
            public void convert(ViewHolder holder, HxOrderByVoucherBean.ProductBean productBean, int position, View convertView) {
                holder.setText(R.id.tv_product, productBean.getProName()+"    *"+productBean.getNumber());
//                holder.setText(R.id.tv_pro_num, productBean.getNumber());

            }

        };
        lvProList.setAdapter(proAdapter);
        proAdapter.setDatas(hxOrderByVoucherBean.getProduct());
    }

}
