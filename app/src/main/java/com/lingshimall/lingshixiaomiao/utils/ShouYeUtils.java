package com.lingshimall.lingshixiaomiao.utils;

import com.lingshimall.lingshixiaomiao.beans.ShangPin;

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
 * Created by 张 波 on 2016/7/12.
 */
public class ShouYeUtils {
    public static byte[] getJsonStrData(String path) {
//        path="http://api.ds.lingshi.cccwei.com/?cid=797470&uid=0&tms=20160708094347&sig=96cc706e46d90409&wssig=4e3e02365ff5a5ad&os_type=3&version=24&channel_name=feibo&srv=2301&special_type=1&since_id=0&pg_cur=1&pg_size=20";
        HttpURLConnection connection = null;
        ByteArrayOutputStream outputStream = null;
        try {
            URL url = new URL(path);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);
            if (connection.getResponseCode() == 200) {
                InputStream inputStream = connection.getInputStream();
                outputStream = new ByteArrayOutputStream();
                byte[] buff = new byte[1024];
                int len = 0;
                while ((len = inputStream.read(buff)) != -1) {
                    outputStream.write(buff, 0, len);
                    outputStream.flush();
                }
                return outputStream.toByteArray();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (outputStream != null) {
                try {
                    outputStream.close();
                    outputStream = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;
    }

    public static ArrayList<ShangPin> getDataFromJson(byte[] data) {
        if (data != null) {
            String jsonStr = new String(data);
            ArrayList<ShangPin> shangPins = new ArrayList<>();
            try {
                JSONObject firstObJ = new JSONObject(jsonStr);
                int rs_code = firstObJ.getInt("rs_code");
                if (rs_code == 1000) {
                    JSONObject dataObj = firstObJ.getJSONObject("data");
                    JSONArray itemArr = dataObj.getJSONArray("items");
                    int count = itemArr.length();
                    for (int i = 0; i < count; i++) {
                        JSONObject itemObj = itemArr.getJSONObject(i);
                        String title = itemObj.getString("title");
                        int shangpin_id=itemObj.getInt("id");

                        JSONObject priceObj = itemObj.getJSONObject("price");
                        int current = priceObj.getInt("current");

                        JSONObject imgObj = itemObj.getJSONObject("img");
                        String img_url = imgObj.getString("img_url");


                        ShangPin shangPin = new ShangPin(title, current, img_url,shangpin_id);
                        shangPins.add(shangPin);
                    }
                    return shangPins;
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
