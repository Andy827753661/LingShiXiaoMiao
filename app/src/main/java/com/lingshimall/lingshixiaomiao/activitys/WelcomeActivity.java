package com.lingshimall.lingshixiaomiao.activitys;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.lingshimall.lingshixiaomiao.MainActivity;
import com.lingshimall.lingshixiaomiao.R;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * Description： 欢迎界面<br/>
 *
 */
public class WelcomeActivity extends Activity {

    private List<View> ds;
    private ViewPager vp_welcome_id;
    private LinearLayout ll_container_id;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sharedPreferences = getSharedPreferences("lingshi", MODE_PRIVATE);

        boolean isUsed = sharedPreferences.getBoolean("isUsed", false);
        if (isUsed) {
            setContentView(R.layout.activity_welcome2);
            SystemClock.sleep(3000);
            startActivity(new Intent(WelcomeActivity.this, MainActivity.class));
            finish();
        } else {

            setContentView(R.layout.activity_welcome1);


            vp_welcome_id = (ViewPager) findViewById(R.id.vp_welcome_id);
            ll_container_id = (LinearLayout) findViewById(R.id.ll_container_id);

            aboutDots();


            aboutViewPager();

            // 将用户的使用信息，存储起来
            saveUseInfoToFile();

        }

    }

    /**
     * 将app应用的使用情况通过文件存储起来
     */
    private void saveUseInfoToFile() {
        Editor edit = sharedPreferences.edit();
        edit.putBoolean("isUsed", true);
        edit.commit();

    }

    /**
     * 关于小圆点
     *
     */
    private void aboutDots() {

        // 监听器实例准备
        MyOnClickListener listener = new MyOnClickListener();

        // 通过循环分析父控件中的子控件
        // 1、循环体：
        for (int i = 0; i < ll_container_id.getChildCount(); i++) {
            // 1）找到每个孩子（ImageView）
            ImageView iv = (ImageView) ll_container_id.getChildAt(i);
            // 将所有的子控件设置为enable状态
            iv.setEnabled(true);

            // 2）给每个孩子都要添加监听器
            iv.setOnClickListener(listener);
            iv.setTag(i);

        }

        // 2、默认第一个小圆点被选中
        ll_container_id.getChildAt(0).setEnabled(false);

    }

    /**
     * 自定义的OnClickListener接口的实现类
     */
    private final class MyOnClickListener implements OnClickListener {

        /*
         * (non-Javadoc)
         *
         * @see android.view.View.OnClickListener#onClick(android.view.View)
         */
        @Override
        public void onClick(View v) {
            // 决定ViewPager中目前显示的是哪张图片
            // Set the currently selected page. 参数：item Item index to select
            vp_welcome_id.setCurrentItem((Integer) (v.getTag()));

        }

    }

    /**
     * 关于ViewPager的操作
     *
     *
     */
    private void aboutViewPager() {
        // 2、准备数据源
        int[] imageIds = new int[] {R.mipmap.bg_guide_1,R.mipmap.bg_guide_2, R.mipmap.bg_guide_3, R.mipmap.bg_guide_4};
        ds = new LinkedList<>();
        for (int imageId : imageIds) {
            // 构建ImageView的对象
            ImageView iv = new ImageView(this);
            iv.setImageResource(imageId);

            // 将对象添加到数据源中
            ds.add(iv);

        }
        // 将第三张图片对应的布局文件，添加到数据源中
        View lastPage = View.inflate(this, R.layout.last_page, null);
        View iv_tiyan_id = lastPage.findViewById(R.id.iv_tiyan_id);
        // 给该控件添加监听器
        iv_tiyan_id.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 点击后，跳转到主界面
               startActivity(new Intent(WelcomeActivity.this,
                       MainActivity.class));
                finish();
            }
        });

        ds.add(lastPage);

        // 3、准备适配器
        PagerAdapter adapter = new MyPagerAdapter();

        // 4、设置适配器
        vp_welcome_id.setAdapter(adapter);

        // 5、添加监听器（目的用来决定小圆点的状态）
        vp_welcome_id.setOnPageChangeListener(new MyOnPageChangeListener());

    }

    /**
     * 自定义OnPageChangeListener实现类
     */
    private final class MyOnPageChangeListener implements OnPageChangeListener {


        @Override
        public void onPageScrolled(int position, float positionOffset,
                                   int positionOffsetPixels) {

        }

        /*
         *
         * This method will be invoked when a new page becomes selected.
         * 新的页面被选中时，触发执行
         *
         * @see
         * android.support.v4.view.ViewPager.OnPageChangeListener#onPageSelected
         * (int)
         */
        @Override
        public void onPageSelected(int position) {
            // 思路：
            // ViewPager决定小圆点的状态
            // 1、将小圆点的状态都复原（初始）（循环）
            for (int i = 0; i < ll_container_id.getChildCount(); i++) {
                // 1）找到每个孩子（ImageView）
                ImageView iv = (ImageView) ll_container_id.getChildAt(i);
                // 将所有的子控件设置为enable状态
                iv.setEnabled(true);

            }

            // 2、将当前页面对应的小圆点状态置为不可点击
            ll_container_id.getChildAt(position).setEnabled(false);

        }

        /*
         * Called when the scroll state changes. 页面的状态发生变化时，触发执行
         *
         * @see android.support.v4.view.ViewPager.OnPageChangeListener#
         * onPageScrollStateChanged(int)
         */
        @Override
        public void onPageScrollStateChanged(int state) {

            int draging = ViewPager.SCROLL_STATE_DRAGGING;// 手指在屏幕上拖动页面时
            int idle = ViewPager.SCROLL_STATE_IDLE;// 空闲状态，页面固定了
            int settling = ViewPager.SCROLL_STATE_SETTLING;// 到达目的界面惯性，有一个上抛的动作
        }

    }

    /**
     * PageAdapter 自定义子类
     */
    private final class MyPagerAdapter extends PagerAdapter {

        /*
         * Return the number of views available. 返回数据源中控件的个数
         *
         * @see android.support.v4.view.PagerAdapter#getCount()
         */
        @Override
        public int getCount() {

            return ds.size();
        }

        /*
         * Determines whether a page View is associated with a specific key
         * object as returned by instantiateItem(ViewGroup, int).
         *
         * @see
         * android.support.v4.view.PagerAdapter#isViewFromObject(android.view
         * .View, java.lang.Object)
         */
        @Override
        public boolean isViewFromObject(View view, Object object) {

            return view == object;
        }

        /**
         * Create the page for the given position. The adapter is responsible
         * for adding the view to the container given here, although it only
         * must ensure this is done by the time it returns from
         * finishUpdate(ViewGroup).
         *
         * 根据参数给定的位置，创建页面。（直白的说：就是从数据源中取出对应的视图，且添加到ViewPager中）
         *
         */
        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            // ①从数据源中取出相应位置的视图视图
            View iv = ds.get(position);

            // ②添加到ViewPager
            container.addView(iv);// ？，此处容易忽略！不要忘了！作用：在ViewPager容器控件中添加上相应的视图。

            // ③返回

            return iv;
        }

        /**
         * Remove a page for the given position.
         *
         * container The containing View from which the page will be removed.
         * position The page position to be removed. object The same object that
         * was returned by instantiateItem(View, int).
         *
         *
         *
         * 从ViewPager容器控件中移除给定位置的控件实例。（原因：为了避免因为页面太多，而造成内存的溢出）
         *
         * 注意：不要从数据源中移除控件实例，否则，滑不回来了（没有数据了哦）
         *
         */
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

            // 方式一：移除instantiateItem（xxx）执行后的返回值
            // container.removeView((View) object);

            // 方式2：移除数据源中对应位置的控件实例
            container.removeView(ds.get(position));
        }

    }

}
