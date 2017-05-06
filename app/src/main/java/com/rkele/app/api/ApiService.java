package com.rkele.app.api;

import com.rkele.app.basedata.BaseData;
import com.rkele.app.basedata.OrderByVoucherBean;
import com.rkele.app.bean.FindProductBean;
import com.rkele.app.bean.HxOrderByVoucherBean;
import com.rkele.app.bean.LoginBean;

import java.util.List;
import java.util.Map;

import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import rx.Observable;

/**
 * Created by mingshanmo on 2017/2/28.
 */

public interface ApiService {

    //    @GET("login")
//    Observable<BaseRespose<User>> login(@Query("username") String username, @Query("password") String password);
//
//    @GET("nc/article/{postId}/full.html")
//    Observable<Map<String, NewsDetail>> getNewDetail(
//            @Header("Cache-Control") String cacheControl,
//            @Path("postId") String postId);
//
//    @GET("nc/article/{type}/{id}/{startPage}-20.html")
//    Observable<Map<String, List<NewsSummary>>> getNewsList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("type") String type, @Path("id") String id,
//            @Path("startPage") int startPage);
//
//    @GET
//    Observable<ResponseBody> getNewsBodyHtmlPhoto(
//            @Header("Cache-Control") String cacheControl,
//            @Url String photoPath);
//    //@Url，它允许我们直接传入一个请求的URL。这样以来我们可以将上一个请求的获得的url直接传入进来，baseUrl将被无视
//    // baseUrl 需要符合标准，为空、""、或不合法将会报错
//
//    @GET("data/福利/{size}/{page}")
//    Observable<GirlData> getPhotoList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("size") int size,
//            @Path("page") int page);
//
//    @GET("nc/video/list/{type}/n/{startPage}-10.html")
//    Observable<Map<String, List<VideoData>>> getVideoList(
//            @Header("Cache-Control") String cacheControl,
//            @Path("type") String type,
//            @Path("startPage") int startPage);
//}
    @POST(ApiConstants.PRODUCTBASEURL+"merchert/login")
    Observable<BaseData<LoginBean>> login(
            @Query("name") String name,
            @Query("password") String password);


    /**
     * 获取商户绑定商品
     *
     * @param merid
     * @param token
     * @return
     */
    @POST(ApiConstants.PRODUCTBASEURL+"merchert/findProduct")
    Observable<BaseData<List<FindProductBean>>> findProduct(
            @Query("merid") String merid,
            @Query("token") String token);


    /**
     * 代金卷下单
     *
     * @param proIds
     * @param numbers
     * @param vouchers
     * @param phone
     * @param token
     * @return
     */
    @POST(ApiConstants.PRODUCTBASEURL+"voucher/OrderByVoucher")
    Observable<BaseData<OrderByVoucherBean>> OrderByVoucher(
            @Query("proIds") String proIds,
            @Query("numbers") String numbers,
            @Query("vouchers") String vouchers,
            @Query("phone") String phone,
            @Query("token") String token);

    /**
     * 代金卷下单
     *
     * @return
     */
    @POST(ApiConstants.PRODUCTBASEURL+"voucher/OrderByVoucher")
    Observable<BaseData<OrderByVoucherBean>> OrderByVoucher(@QueryMap Map<String, Object> map);

    /**
     * 确认支付
     *
     * @param orderCode
     * @param vorcherStatus
     * @param vorcherCode
     * @param token
     * @return
     */
    @POST(ApiConstants.PRODUCTBASEURL+"voucher/hxOrderByVoucher")
    Observable<BaseData<HxOrderByVoucherBean>> hxOrderByVoucher(
            @Query("orderCode") String orderCode,
            @Query("vorcherStatus") String vorcherStatus,
            @Query("vorcherCode") String vorcherCode,
            @Query("token") String token);

    @POST(ApiConstants.PRODUCTBASEURL+"voucher/hxOrderByVoucher")
    Observable<BaseData<HxOrderByVoucherBean>> hxOrderByVoucher(@QueryMap Map<String, Object> map);

}
