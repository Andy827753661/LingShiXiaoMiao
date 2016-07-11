package com.lingshimall.lingshixiaomiao.utils;

import android.util.Log;

import com.lingshimall.lingshixiaomiao.beans.Goods;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by 张 波 on 2016/7/9.
 */
public class TeMaiUtils {

    public static byte[] getJsonStrData(String path){
//        path="http://api.ds.lingshi.cccwei.com/?cid=797470&uid=0&tms=20160708094347&sig=96cc706e46d90409&wssig=4e3e02365ff5a5ad&os_type=3&version=24&channel_name=feibo&srv=2301&special_type=1&since_id=0&pg_cur=1&pg_size=20";
            HttpURLConnection connection=null;
            ByteArrayOutputStream outputStream=null;
            try {
                URL url=new URL(path);
                connection=(HttpURLConnection) url.openConnection();
                connection.connect();
                connection.setConnectTimeout(5000);
                connection.setReadTimeout(5000);
                if(connection.getResponseCode()==200){
                    InputStream inputStream = connection.getInputStream();
                    outputStream= new ByteArrayOutputStream();
                    byte [] buff=new byte[1024];
                    int len=0;
                    while((len=inputStream.read(buff))!=-1){
                        outputStream.write(buff,0,len);
                        outputStream.flush();
                    }
                    return outputStream.toByteArray();
                }
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }finally {
                if (outputStream!=null){
                    try {
                        outputStream.close();
                        outputStream=null;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

        return null;
        }


    public static ArrayList<Goods> getListFromJsonStr(byte []data) {
        if (data != null) {
            String jsonStr = new String(data);

            ArrayList<Goods> goodses = new ArrayList<>();
            try {
                JSONObject firstObj = new JSONObject(jsonStr);
                int rs_code = firstObj.getInt("rs_code");
                String rs_msg = firstObj.getString("rs_msg");
                if (rs_msg.equals("success") && rs_code == 1000) {
                    JSONObject dataObj = firstObj.getJSONObject("data");
                    JSONArray itemsArr = dataObj.getJSONArray("items");
                    int countOfArr = itemsArr.length();
                    for (int i = 0; i < countOfArr; ++i) {
                        JSONObject itemObj = itemsArr.getJSONObject(i);
                        int id = itemObj.getInt("id");

                        JSONObject imgObj = itemObj.getJSONObject("img");
                        String img_url = TextFormatUtils.unicode2UTF8(imgObj.getString("img_url"));

                        JSONObject priceObj = itemObj.getJSONObject("price");
                        double current = priceObj.getDouble("current");
                        double prime = priceObj.getDouble("prime");

                        int special_num = itemObj.getInt("special_num");
                        int special_percentage = itemObj.getInt("special_percentage");

                        JSONObject tagObj = itemObj.getJSONObject("tag");
                        String tag_title = TextFormatUtils.unicode2UTF8(tagObj.getString("title"));

                        int time = itemObj.getInt("time");
                        String title =TextFormatUtils.unicode2UTF8(itemObj.getString("title"));
//                        Log.e("TAG+title1===>", "getListFromJsonStr: "+title1 );
//                        String title=TextFormatUtils.unicode2UTF8(title1);
                        Log.e("TAG", "getListFromJsonStr:title"+title );
                        //给JavaBean填充数据
                        Goods goods = new Goods();
                        goods.setId(id);

                        Goods.ImgBean imgBean = new Goods.ImgBean();
                        imgBean.setImg_url(img_url);
                        goods.setImg(imgBean);

                        Goods.PriceBean priceBean = new Goods.PriceBean();
                        priceBean.setCurrent(current);
                        priceBean.setPrime(prime);
                        goods.setPrice(priceBean);

                        goods.setSpecial_num(special_num);
                        goods.setSpecial_percentage(special_percentage);

                        Goods.TagBean tagBean = new Goods.TagBean();
                        tagBean.setTitle(tag_title);

                        goods.setTime(time);
                        goods.setTitle(title);

                        goodses.add(goods);
                    }
                    return goodses;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else {
            Log.e("TAG=====>", "data=null" );
        }
            return null;
        }

}
