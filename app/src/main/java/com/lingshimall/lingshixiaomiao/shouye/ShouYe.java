package com.lingshimall.lingshixiaomiao.shouye;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.lingshimall.lingshixiaomiao.R;
import com.lingshimall.lingshixiaomiao.beans.URLs;
import com.lingshimall.lingshixiaomiao.fragments.BlogsListView;
import com.lingshimall.lingshixiaomiao.utils.TeMaiUtils;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.zip.Inflater;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Created by 张 波 on 2016/7/6.
 */
public class ShouYe extends Fragment {
    private View view;
    private BlogsListView  listView;
    private ViewPager  vp_welcome_id;
    private LinearLayout  ll_container_id;
    private List<View> ds;
    private List<Shangpin> lt=new ArrayList<>();
    private  String urlString="http://api.ds.lingshi.cccwei.com/?cid=797470&uid=0&tms=20160708100417&sig=ae3431ebd6ca7b8f&wssig=743027a2bc2c9cef&os_type=3&version=24&channel_name=feibo&srv=2206&since_id=0&pg_cur=1&pg_size=20";

    public ShouYe() {

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_shouye_layout, container, false);
        listView=(BlogsListView) view.findViewById(R.id.lv_id);

        View view1=inflater.inflate(R.layout.shouye_top,null);

        vp_welcome_id = (ViewPager) view1.findViewById(R.id.vp_welcome_id);
        ll_container_id = (LinearLayout) view1.findViewById(R.id.ll_container_id);
        new MyTask(getActivity(), new OneCallBack() {
            @Override
            public void data(List<Shangpin> list) {
                lt=list;
            }
        }).execute(urlString);


        MyAdapter adapter=new MyAdapter(getActivity(),lt);


        listView.addHeaderView(view1);
        listView.setAdapter(adapter);
        aboutDots();
        aboutViewPager();

          return view;
    }



    private void aboutDots() {


        MyOnClickListener listener = new MyOnClickListener();


        for (int i = 0; i < ll_container_id.getChildCount(); i++) {

            ImageView iv = (ImageView) ll_container_id.getChildAt(i);
            iv.setEnabled(true);
            iv.setOnClickListener(listener);
            iv.setTag(i);

        }

        ll_container_id.getChildAt(0).setEnabled(false);

     }

    private final class MyOnClickListener implements OnClickListener {
        @Override
        public void onClick(View v) {

            vp_welcome_id.setCurrentItem((Integer) (v.getTag()));

        }

    } private void aboutViewPager() {
        int[] imageIds = new int[]{R.mipmap.imag_001, R.mipmap.imag_002, R.mipmap.imag_003, R.mipmap.imag_004};
        ds = new LinkedList<>();
        for (int imageId : imageIds) {
            ImageView iv = new ImageView(getActivity());
            iv.setImageResource(imageId);
            ds.add(iv);

        }

        PagerAdapter adapter = new MyPagerAdapter();

        vp_welcome_id.setAdapter(adapter);

        vp_welcome_id.setOnPageChangeListener(new MyOnPageChangeListener());

    }


    private final class MyOnPageChangeListener implements OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

        }


        @Override
        public void onPageSelected(int position) {

            for (int i = 0; i < ll_container_id.getChildCount(); i++) {

                ImageView iv = (ImageView) ll_container_id.getChildAt(i);

                iv.setEnabled(true);
            }


            ll_container_id.getChildAt(position).setEnabled(false);

        }


        @Override
        public void onPageScrollStateChanged(int state) {


        }

    }

    private final class MyPagerAdapter extends PagerAdapter {


        @Override
        public int getCount() {

            return ds.size();
        }


        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }


        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View iv = ds.get(position);

            container.addView(iv);

            return iv;
        }


        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {


            container.removeView(ds.get(position));
        }
    }







}

class  MyAdapter extends BaseAdapter{
    Context context;
    List<Shangpin>list=new ArrayList<>();


    public MyAdapter(Context context,List<Shangpin>list){
        this.context=context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder viewHolder;

        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(context);

            convertView = inflater.inflate(R.layout.first_top, null);

            // 初始化ViewHolder
            viewHolder = new ViewHolder();

            viewHolder.textView1 = (TextView) convertView
                    .findViewById(R.id.text_1);
            viewHolder.textView2=(TextView) convertView.findViewById(R.id.text_2);
            viewHolder.imageView=(ImageView) convertView.findViewById(R.id.photo_1);
            convertView.setTag(viewHolder);

        } else  {
            viewHolder = (ViewHolder) convertView.getTag();


        }
        Shangpin shangpin =list.get(position);
        viewHolder.textView1.setText(shangpin.getGoods());
        viewHolder.textView2.setText(shangpin.getPrice());









        return convertView;

    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }
    class ViewHolder {
        TextView textView1;
        TextView textView2;
        ImageView  imageView;
    }


}
class MyTask extends AsyncTask<String, Void, byte[]> {

    private ProgressDialog pDialog;
    private Context context = null;

   private OneCallBack getdata;
    // 构造方法，初始化进度对话框
    public MyTask(Context context,OneCallBack getdata) {
        this.context = context;
        this.getdata=getdata;
        pDialog = new ProgressDialog(context);
        pDialog.setTitle("提示：");
        pDialog.setMessage("数据加载中。。。");
    }

    // 事先执行方法中显示进度对话框
    @Override
    protected void onPreExecute() {
        pDialog.show();
        super.onPreExecute();
    }

    // 进度条进度改变方法。一般情况下，可以不写该方法
    @Override
    protected void onProgressUpdate(Void... values) {
        super.onProgressUpdate(values);
    }

// 后台执行方法，这个方法执行worker Thread异步访问网络，加载数据。

    @Override
    protected byte[] doInBackground(String... params) {
        BufferedInputStream bis = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            URL urlObj = new URL(params[0]);
            HttpURLConnection httpConn = (HttpURLConnection) urlObj.openConnection();
            httpConn.setDoInput(true);
            // httpConn.setDoOutput(true);
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            if (httpConn.getResponseCode() == 200) {
                bis = new BufferedInputStream(httpConn.getInputStream());
                byte[] buffer = new byte[1024 * 8];
                int c = 0;
                while ((c = bis.read(buffer)) != -1) {
                    baos.write(buffer, 0, c);
                    baos.flush();
                }

                Log.i("tag", "doInBackground: "+baos.toByteArray().toString());

                // Toast.makeText(context, baos.toByteArray().toString(),
                // Toast.LENGTH_LONG).show();
                return baos.toByteArray();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (bis != null) {
                    bis.close();
                }

                if (baos != null) {
                    baos.close();
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       // return TeMaiUtils.getJsonStrData(params[0]);
        return null;
    }

    // 事后方法，这个方法主要作用是执行对主线程中UI的操作。可以实现主线程和子线程之间的数据交互

    @Override
    protected void onPostExecute(byte[] bytes) {

        super.onPostExecute(bytes);
        if (bytes!=null && bytes.length>0){
            String jsonString=new String(bytes);
            List <Shangpin> list =Jsonparse.JsonContext(jsonString);

            getdata.data(list);
        }



    }

}
class  Jsonparse{
    private static   Shangpin shangpin;
    private static   List<Shangpin> list = new ArrayList<>();
    public  static  List<Shangpin>   JsonContext(String jsonString){
        try {
            JSONObject  object= new JSONObject(jsonString);
            int rs_code=object.getInt("rs_code");
            if(rs_code==200){
             JSONObject object1=object.getJSONObject("data");
                JSONArray array =object1.getJSONArray("items");
                for (int i=0;i<array.length();i++){
                   JSONObject object2=array.getJSONObject(i);
                    String  title= object2.getString("title");
                    JSONObject object3=object2.getJSONObject("price");
                     String  price=object3.getString("current");
                     JSONObject object4=object2.getJSONObject("img");
                    String  url=object4.getString("img_url");
                    shangpin.setGoods(title);
                    shangpin.setPrice(price);
                    shangpin.setPicture(url);
                    list.add(shangpin);


                }


            }

return list;

        } catch (JSONException e) {
            e.printStackTrace();
        }




        return null;

    }


}
class  Shangpin{
    private  String goods;
    private  String price;
    private  String picture;

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
 interface  OneCallBack{
    void  data(List <Shangpin>list);

}