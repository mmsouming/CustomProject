package com.rkele.app.ui.main.activity;

import android.content.Intent;
import android.os.Build;
import android.support.design.widget.AppBarLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.util.ArrayMap;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.app.mingshanmo.mylibrary.base.BaseActivity;
import com.app.mingshanmo.mylibrary.baserx.RxSchedulers;
import com.app.mingshanmo.mylibrary.commonutils.LogUtils;
import com.app.mingshanmo.mylibrary.commonutils.ToastUitl;
import com.app.mingshanmo.mylibrary.commonwidget.CommonAdapter;
import com.app.mingshanmo.mylibrary.commonwidget.CustomDialog;
import com.app.mingshanmo.mylibrary.commonwidget.NoScrollListview;
import com.app.mingshanmo.mylibrary.commonwidget.ViewHolder;
import com.rkele.app.R;
import com.rkele.app.api.Api;
import com.rkele.app.app.AppConstant;
import com.rkele.app.basedata.BaseData;
import com.rkele.app.basedata.OrderByVoucherBean;
import com.rkele.app.basedata.RxGetDataSubscriber;
import com.rkele.app.bean.FindProductBean;
import com.rkele.app.bean.VoucherBean;
import com.rkele.app.ui.login.activity.LoginActivity;
import com.rkele.app.utils.GlideUtils;
import com.rkele.app.utils.SPUtils;
import com.rkele.app.widget.ExtendedEditText;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Bind;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {


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
    @Bind(R.id.lv_order)
    NoScrollListview lvOrder;
    @Bind(R.id.tv_amount)
    TextView tvAmount;
    @Bind(R.id.tv_phone)
    EditText tvPhone;
    @Bind(R.id.lv_voucher)
    NoScrollListview lvVoucher;
    @Bind(R.id.btn_addvolume)
    Button btnAddvolume;
    @Bind(R.id.btn_submit_order)
    Button btnSubmitOrder;
    private CommonAdapter<FindProductBean> commonAdapter;
    private List<FindProductBean> proList;

    private List<VoucherBean> list = new ArrayList();
    private CommonAdapter<VoucherBean> vouchAdapter;
    private boolean flag = false;
    private Map<String, Object> map;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }


    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        map = new ArrayMap<>();

        toolbar.setNavigationIcon(R.drawable.back1);
        toolbar.setTitle("" + SPUtils.get(mContext, SPUtils.NAME, ""));
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

//        rightIv.setImageResource(R.mipmap.ic_setting);
        tvRight.setText("退出");
        tvRight.setTextColor(ContextCompat.getColor(mContext, R.color.white));

        initData();
        initList();
        initVouch();


    }

    private void initVouch() {
        vouchAdapter = new CommonAdapter<VoucherBean>(mContext, R.layout.item_add_vouchers) {
            @Override
            public void convert(ViewHolder holder, VoucherBean voucherBean, final int position, View convertView) {
                list.get(position).setPosition(position);
                final ExtendedEditText editText = holder.getView(R.id.tv_input_volume);
                editText.clearTextChangedListeners();
                initaddchooselister(editText, position);
                holder.setText(R.id.tv_input_volume, voucherBean.getVoucherId());
                holder.setOnClickListener(R.id.tv_delete, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        list.remove(position);
                        vouchAdapter.notifyDataSetChanged();
                    }
                });

            }
        };
        lvVoucher.setAdapter(vouchAdapter);
        VoucherBean voucherBean = new VoucherBean();
        list.add(voucherBean);
        vouchAdapter.setDatas(list);
    }

    private void initaddchooselister(EditText editText, final int position) {

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (flag) {
                    return;
                }
                flag = true;

                if (list.get(position).getPosition() == position) {

                    list.get(position).setVoucherId(s.toString());
                    LogUtils.loge(s + "---" + position + "----" + list.toString());
                }
                flag = false;
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void initList() {
        commonAdapter = new CommonAdapter<FindProductBean>(mContext, R.layout.item_order) {
            @Override
            public void convert(ViewHolder holder, FindProductBean findProductBean, final int position, View convertView) {

                GlideUtils.displayBigPhoto(mContext, (ImageView) holder.getView(R.id.iv_product), findProductBean.getIcon());
                holder.setText(R.id.tv_number, findProductBean.getVolume1());
                holder.setText(R.id.tv_product_indtorduce, findProductBean.getName());
                holder.setText(R.id.et_product_number, findProductBean.getNum() + "");
                holder.setText(R.id.tv_moneydesc, findProductBean.getMoney() + findProductBean.getMoneyDesc());


                holder.setOnClickListener(R.id.btn_reduce, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        reduce(position);

                    }
                });
                holder.setOnClickListener(R.id.btn_add, new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        add(position);

                    }
                });

                calculatePrice();
            }


        };
        lvOrder.setAdapter(commonAdapter);

    }

    private void calculatePrice() {
        BigDecimal price = new BigDecimal("0");
        for (int i = 0; i < commonAdapter.getDatas().size(); i++) {
            BigDecimal p = BigDecimal.valueOf(commonAdapter.getDatas().get(i).getMoney() * commonAdapter.getDatas().get(i).getNum());
            price = price.add(p);
        }
        tvAmount.setText("¥" + price + "元");

    }

    private void add(int position) {


        int i = commonAdapter.getDatas().get(position).getNum();
        i++;
        commonAdapter.getDatas().get(position).setNum(i);
        commonAdapter.notifyDataSetChanged();


    }

    private void reduce(int position) {
        int i = commonAdapter.getDatas().get(position).getNum();
        if (i > 0)
            i--;
        commonAdapter.getDatas().get(position).setNum(i);
        commonAdapter.notifyDataSetChanged();
    }

    private void initData() {
        mRxManager.add(Api.getDefault().findProduct(SPUtils.get(mContext, SPUtils.MERID, "").toString(), SPUtils.get(mContext, SPUtils.TOKEN, "").toString()).
                compose(RxSchedulers.<BaseData<List<FindProductBean>>>io_main()).subscribe(new RxGetDataSubscriber<BaseData<List<FindProductBean>>>(mContext, true) {
            @Override
            protected void _onNext(BaseData<List<FindProductBean>> listBaseData) {
                commonAdapter.setDatas(listBaseData.getData());
                proList = listBaseData.getData();

            }
        }));


    }


    @OnClick({R.id.btn_addvolume, R.id.btn_submit_order, R.id.tv_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_addvolume:

                if (vouchAdapter.getDatas().size() < 6) {
                    List list = new ArrayList();
                    VoucherBean voucherBean = new VoucherBean();
                    list.add(voucherBean);
                    vouchAdapter.addDatas(list);
                } else {
                    ToastUitl.showLong("一次性最多只能使用6张通兑卷");
                }
                break;
            case R.id.btn_submit_order:

                confirmOrder();

                break;
            case R.id.tv_right:
                loginOut();
//                confirmOrder();

                break;
        }
    }

    private void loginOut() {
        final CustomDialog customDialog = new CustomDialog(mContext);
        customDialog.show();
        customDialog.addView(R.layout.dialog_del_view);
        customDialog.findViewById(R.id.tv_dia_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                SPUtils.clear(mContext);

                customDialog.cancel();
                Intent intent = new Intent(mContext, LoginActivity.class);
                startActivity(intent);
                finish();

            }
        });
        customDialog.findViewById(R.id.tv_dia_right).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialog.cancel();

            }
        });
    }

    private void confirmOrder() {

//        startActivity(new Intent(mContext, ConfirmOrderActivity.class));
        StringBuilder proIdsStringBuilder = new StringBuilder();
        StringBuilder numbersStringBuilder = new StringBuilder();
        StringBuilder vouchersStringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            if (!TextUtils.isEmpty(list.get(i).getVoucherId())) {
                vouchersStringBuilder.append(list.get(i).getVoucherId()).append(",");
            }
        }
        if (vouchersStringBuilder.length() > 0) {
            vouchersStringBuilder.delete(vouchersStringBuilder.length() - 1, vouchersStringBuilder.length());
        }


        for (int i = 0; i < proList.size(); i++) {
            if (proList.get(i).getNum() > 0) {
                proIdsStringBuilder.append(proList.get(i).getId()).append(",");
                numbersStringBuilder.append(proList.get(i).getNum()).append(",");
            }

        }
        if (proIdsStringBuilder.length() > 0 && numbersStringBuilder.length() > 0) {
            proIdsStringBuilder.delete(proIdsStringBuilder.length() - 1, proIdsStringBuilder.length());
            numbersStringBuilder.delete(numbersStringBuilder.length() - 1, numbersStringBuilder.length());
        } else {
            ToastUitl.showShort("请选择对应商品数量");
            return;
        }
        if (TextUtils.isEmpty(tvPhone.getText())) {
            ToastUitl.showShort("请输入手机号码");

        }
        if (vouchersStringBuilder.length() > 0) {
            map.put("vouchers", vouchersStringBuilder.toString());
        }
        map.put("proIds", proIdsStringBuilder.toString());
        map.put("numbers", numbersStringBuilder.toString());
        map.put("phone", tvPhone.getText().toString());
        map.put("token", SPUtils.get(mContext, SPUtils.TOKEN, "") + "");
        mRxManager.add(Api.getDefault().OrderByVoucher(map).compose(RxSchedulers.<BaseData<OrderByVoucherBean>>io_main()).subscribe(new RxGetDataSubscriber<BaseData<OrderByVoucherBean>>(mContext, true) {
            @Override
            protected void _onNext(BaseData<OrderByVoucherBean> orderByVoucherBeanBaseData) {
                startActivity(new Intent(mContext, ConfirmOrderActivity.class).putExtra(AppConstant.ORDERINFO, orderByVoucherBeanBaseData.getData()));
            }
        }));

    }


}
