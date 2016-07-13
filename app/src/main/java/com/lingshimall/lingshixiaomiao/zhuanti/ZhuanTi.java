package com.lingshimall.lingshixiaomiao.zhuanti;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Toast;

import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.activitys.XiangQingActivity;
import com.lingshimall.lingshixiaomiao.adapters.ZhuanTiListViewAdapter;
import com.lingshimall.lingshixiaomiao.beans.URLs;
import com.lingshimall.lingshixiaomiao.beans.ZhuanTiModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by 张 波 on 2016/7/6.
 */
public class ZhuanTi extends Fragment {

    private View view;
    private GridView gridView;
    private ListView listView;

    private  ArrayList<HashMap<String, Object>> gridItems;

    private String[] item = {"日韩", "欧美", "台湾", "韩国"};

    private String list_jsonStr;
    private ArrayList<ZhuanTiModel> list;

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            listViewsetdata();
        }
    };

    public ZhuanTi() {

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_zhuanti_layout, null);

        gridView = (GridView) view.findViewById(R.id.zhuanti_gv_id);
        listView = (ListView) view.findViewById(R.id.zhuanti_lv_id);
        listView.setOnItemClickListener(new TheListenerOfLV());
        return view;
    }

    public class TheListenerOfLV implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            if (list != null) {
                Intent intent = new Intent(getActivity(), XiangQingActivity.class);
                Bundle bundle = new Bundle();
                int listID = list.get(position).getId();
                bundle.putInt("GoodId", listID);
                intent.putExtras(bundle);
                startActivity(intent);
            } else {
                Toast.makeText(getActivity(), "网络阻塞，请刷新", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        aboutGridView();
        aboutListView();
    }

    private void aboutListView() {

        new Thread(){
            @Override
            public void run() {
                super.run();
//                byte[] bytes=TeMaiUtils.getJsonStrData(URLs.ZHUANTI);
//                Log.i("tag", "run: "+bytes);
//                if (bytes!=null && bytes.length>0){
//                list_jsonStr= new String(bytes,0,bytes.length);
//                Log.d("taag",list_jsonStr);}
                list_jsonStr=getJsonFromNet(URLs.ZHUANTI);
                Log.d("taag",list_jsonStr);

                handler.sendEmptyMessage(1);
            }
        }.start();


    }

    private void listViewsetdata(){
        if (list_jsonStr!=null) {
            list = parseJson(list_jsonStr);

            ZhuanTiListViewAdapter adapter = new ZhuanTiListViewAdapter(getContext(), list);

            listView.setAdapter(adapter);
           listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
               @Override
               public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               }
           });


        }
    }

    private void aboutGridView() {
        gridItems = new ArrayList<HashMap<String, Object>>();
        //将数组信息分别存入ArrayList中
        int len = item.length;
        for (int i = 0; i < len; i++) {
            HashMap<String, Object> map = new HashMap<String, Object>();

            map.put("item", item[i]);
            gridItems.add(map);
        }
        gridView.setAdapter(new ZhuantiGridAdapter(gridItems,getActivity()));

    }


    private ArrayList<ZhuanTiModel> parseJson(String list_jsonStr){

        if (list_jsonStr!=null){
            ArrayList<ZhuanTiModel> zhuanTis =null;

            try {
                zhuanTis=new ArrayList<>();
                JSONObject firstObj = new JSONObject(list_jsonStr);
//                int rs_code = firstObj.getInt("rs_code");
//                String rs_msg = firstObj.getString("rs_msg");

//                if (rs_msg.equals("success") && rs_code == 1000) {
                    JSONObject jsonObject=firstObj.getJSONObject("data");
                    JSONArray jsonArray=jsonObject.getJSONArray("items");
                    for (int i=0;i<jsonArray.length();i++){
                        ZhuanTiModel zhuantiModel=new ZhuanTiModel();

                        JSONObject itemObj = jsonArray.getJSONObject(i);
                        int id=itemObj.getInt("id");
                        Log.i("tag", "parseJson: "+id);
                        zhuantiModel.setId(id);
                        String desc=itemObj.getString("desc");
                        Log.i("tag", "parseJson: "+desc);
                        zhuantiModel.setDesc(desc);
                        String title=itemObj.getString("title");
                        Log.i("tag", "parseJson: "+title);
                        zhuantiModel.setTitle(title);
                        JSONObject jsonImg=itemObj.getJSONObject("img");
                        String img_url=jsonImg.getString("img_url");
                        Log.i("tag", "parseJson: "+img_url);
                        zhuantiModel.setImg_url(img_url);
                        int hotindex=itemObj.getInt("hotindex");
                        Log.i("tag", "parseJson: "+hotindex);
                        zhuantiModel.setHotindex(hotindex);

                        zhuanTis.add(zhuantiModel);
//                    }


                }

                Log.i("tag", "parseJson: zhuanTis.size()"+zhuanTis.size());
                return zhuanTis;

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static String getJsonFromNet(String path) {
        HttpURLConnection conn = null;
        BufferedReader reader = null;
        try {
            StringBuffer buffer = new StringBuffer();
            URL url = new URL(path);
            conn = (HttpURLConnection) url.openConnection();
            // 设置连接超时
            conn.setConnectTimeout(1000 * 5);
            // 设置 读取超时
            conn.setReadTimeout(1000 * 5);
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String str;
            while ((str = reader.readLine()) != null) {
                buffer.append(str);
            }
            return buffer.toString();

        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            //  断开连接
            if (conn != null) {
                conn.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
