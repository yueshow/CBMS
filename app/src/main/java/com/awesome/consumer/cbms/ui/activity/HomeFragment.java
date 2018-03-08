package com.awesome.consumer.cbms.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.awesome.consumer.cbms.R;
import com.awesome.consumer.cbms.beans.JsonGenericsSerializator;
import com.awesome.consumer.cbms.beans.OrderBean;
import com.awesome.consumer.cbms.beans.User;
import com.awesome.consumer.cbms.beans.test.LoginJson;
import com.awesome.consumer.cbms.beans.test.Member;
import com.awesome.consumer.cbms.beans.test.ResultTemp;
import com.awesome.consumer.cbms.config.Config;
import com.awesome.consumer.cbms.okhttputil.OkHttpUtils;
import com.awesome.consumer.cbms.okhttputil.callback.GenericsCallback;
import com.awesome.consumer.cbms.ui.activity.fragment.BaseFragment;
import com.awesome.consumer.cbms.ui.adapter.OrderAdapter;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;

public class HomeFragment extends BaseFragment {
    private ListView listview;
    private OrderAdapter adapter;
    private View listviewHead;
    private View middleView;
    private int middleViewHeight = 0;

    private List<OrderBean> data = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        listview = view.findViewById(R.id.listview);
        listviewHead = inflater.inflate(R.layout.list_head, container, false);
//        testData();
        adapter = new OrderAdapter(this.getActivity().getApplicationContext(), data);
        listview.setAdapter(adapter);
        listview.addHeaderView(listviewHead);
        middleView = view.findViewById(R.id.ll_middle);
        middleViewHeight = middleView.getHeight();
//        middleView.setVisibility(View.GONE);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mHandler.sendEmptyMessage(GET);
//        mHandler.sendEmptyMessage(POST);
//        mHandler.sendEmptyMessage(RESULT);
        mHandler.sendEmptyMessage(OTHER);
    }
//
//    private void testData() {
//        Random random = new Random();
//        int length = random.nextInt() % 30;
//        float tmp;
//        for (int i = 0; i < length; ++i) {
//            OrderBean bean = new OrderBean();
//            tmp = (float) random.nextInt(100000) / 100;
//            if (0 == i % 3 && tmp > 0) {
//                tmp = -tmp;
//            }
//            bean.money = tmp;
//            bean.date = new Date();
//            bean.explain1 = tmp + "接待费用";
//            bean.explain2 = tmp + "某某集团";
//            data.add(bean);
//        }
//    }

    private static final int GET = 0x01;
    private static final int POST = 0x02;
    private static final int OTHER = 0x03;
    private static final int RESULT = 0x04;
    private Handler mHandler = new Handler() {
        @Override
        public void dispatchMessage(Message msg) {
            switch (msg.what) {
                case GET:
                    Log.i("Yuexiu", "----------GET------------");

//                    getAsynHttp();
                    break;

                case POST:
                    Log.i("Yuexiu", "----------POST------------");
                    break;
                case RESULT:
                    Log.i("Yuexiu", "----------RESULT------------");
                    break;
                default:
                case OTHER:
                    Log.i("Yuexiu", "----------OTHER------------");
//                    login();
                    test();
                    break;
            }
        }
    };


    String mBaseUrl = "http://192.168.0.2/memberapi/";
    public void login2() {
        String url = mBaseUrl + "?ct=member&ac=login";
        OkHttpUtils
                .post()//
                .url(url)//
                .addParams("username", "adminP-5")//
                .addParams("password", "adminP-1")//
                .build()//
                .execute(new GenericsCallback<User>(new JsonGenericsSerializator()) {
                    @Override
                    public void onError(Call call, Exception e, int id) {
//                        mTv.setText("onError:" + e.getMessage());
                    }

                    @Override
                    public void onResponse(User response, int id) {
//                        mTv.setText("onResponse:" + response.username);
                        Log.e(Config.TAG, "onResponse：" + response);
                    }
                });
    }

    public void ShowWeatherInfo(LoginJson resultBean) {
//        if (Weatherobject != null && "ok".equals(Weatherobject.Status)) {
        Log.i("YukiCEO", "Object : "  + resultBean.toString());
//        Member member = (Member)resultBean.data;
//        Log.i("YukiCEO", "member : "  + member.id);

//            String Degree = Weatherobject.Now.Tmp + "℃";
//            String Cond = Weatherobject.Now.CondTxt;

//　　　　　　　　//集合数据
//            for (DailyForecastJson Forecast : Weatherobject.DailyForecast) {
//　　　　　　　　　　DateTxt.setText(Forecast.Date);
//　　　　　　　　　　WeatherInfo.setText(Forecast.CondTxtD);
//　　　　　　　　　　MaxTxt.setText(Forecast.TmpMax);
//　　　　　　　　　　MinTxt.setText(Forecast.TmpMin);
//　　　　　　  }
//
//    　　　　if (Weatherobject.AirNowCity.Aqi != null) {
//            　　AqiTxt.setText(Weatherobject.AirNowCity.Aqi);
//            　　Pm25Txt.setText(Weatherobject.AirNowCity.Pm25);
//       　　 }

//       　　 for (LifeStyleJson Life : Weatherobject.LifeStyle) {
//          　　  if (Life.Type.equals("comf")) {
//              　　  String Comfort = "舒适度：" + Life.Txt;
//             　　   ComfTxt.setText(Comfort);
//           　　 }
//        　　}

//            Log.i("YukiCEO", "Name : " + Name + " , UpdateTime : " + UpdateTime);
//        }
    }




    private void test(){
        String test = ResultTemp.RESULT_STR.substring(900, 914);
        Log.i(Config.TAG, test);
        try {
            JSONObject jsonObject = new JSONObject(ResultTemp.RESULT_STR);
            Log.i(Config.TAG, "code : " + jsonObject.getInt("code"));
            Log.i(Config.TAG, "msg : " + jsonObject.getString("msg"));
            String data = jsonObject.getString("data");
//            Log.i(Config.TAG, "data : " + data);

            jsonObject = new JSONObject(data);

            String memberString = jsonObject.getString("member");
            String memberInfo = jsonObject.getString("member_info");
            Log.i(Config.TAG, "member : " + memberString);
//            Log.i(Config.TAG, "memberInfo : " + memberInfo);
//            jsonObject = new JSONObject(memberString);
//            Log.i(Config.TAG, "id : " + jsonObject.getInt("id"));
//            Log.i(Config.TAG, "name : " + jsonObject.getString("name"));

//            Log.i(Config.TAG, "values : " + jsonObject.getString("values"));
            Gson gson = new Gson();
            Member member = gson.fromJson(memberInfo, Member.class);
            Log.i(Config.TAG, "Member : " + member.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

//        Gson gson = new Gson();
//        LoginJson lj = gson.fromJson(ResultBean.RESULT, LoginJson.class);
//        ShowWeatherInfo(lj);

        /**
        //    请求条件：platform=2&gifttype=2&compare=60841c5b7c69a1bbb3f06536ed685a48
        //    请求参数：page=1&code=news&pageSize=20&parentid=0&type=1
        RequestBody requestBodyPost = new FormBody.Builder()
                .add("username", "adminP-5")//
                .add("password", "adminP-1")
                .build();
        String POST_URL = "http://192.168.0.2/memberapi/" + "?ct=member&ac=login";
        Request requestPost = new Request.Builder()
                .url(POST_URL)
                .post(requestBodyPost)
                .build();
        OkHttpClient client = null;
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(10, TimeUnit.SECONDS)
                .build();

        client.newCall(requestPost).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String string = response.body().string();
                Log.i("YukiCEO" , string);

                Gson gson = new Gson();
                ResultBean res = gson.fromJson(string, ResultBean.class);

                Log.i("YukiCEO" , "code : " + res.code);
                Log.i("YukiCEO" , "msg : " + res.msg);
//                Log.i("YukiCEO" , "data : " + res.data);
            }
        });

         */
    }


}