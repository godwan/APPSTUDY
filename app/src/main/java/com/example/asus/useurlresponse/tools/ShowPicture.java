package com.example.asus.useurlresponse.tools;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.useurlresponse.R;

import java.io.InputStream;

/**
 * Created by asus on 2016/11/6.
 */
public class ShowPicture extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_showpicture);

        //定义UI组件
        final ImageView iv= (ImageView)findViewById(R.id.imageView);
        Gallery g = (Gallery) findViewById(R.id.gallery);

        //设置图片匹配器
        g.setAdapter(new ImageAdapter(this));

        //设置AdapterView点击监听器，Gallery是AdapterView的子类
        g.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                //显示点击的是第几张图片
                Toast.makeText(ShowPicture.this, "" + position,
                        Toast.LENGTH_LONG).show();
                //设置背景部分的ImageView显示当前Item的图片
                iv.setImageResource(((ImageView)view).getId());
            }
        });
    }

    //定义继承BaseAdapter的匹配器
    public class ImageAdapter extends BaseAdapter {

        //Item的修饰背景
        int mGalleryItemBackground;

        //上下文对象
        private Context mContext;

        //图片数组
        private Integer[] mImageIds = {
                R.raw.y1, R.raw.y2,
                R.raw.y3, R.raw.y4,
                R.raw.y5, R.raw.y6,
                R.raw.y7, R.raw.y8};
        //构造方法
        public ImageAdapter(Context c){
            mContext = c;
            //读取styleable资源
            TypedArray a = obtainStyledAttributes(R.styleable.HelloGallery);
            mGalleryItemBackground = a.getResourceId(
                    R.styleable.HelloGallery_android_galleryItemBackground, 0);
            a.recycle();

        }

        //返回项目数量
        @Override
        public int getCount() {
            return mImageIds.length;
        }

        //返回项目
        @Override
        public Object getItem(int position) {
            return position;
        }

        //返回项目Id
        @Override
        public long getItemId(int position) {
            return position;
        }

        //返回视图
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ImageView iv = new ImageView(mContext);

            InputStream is = getResources().openRawResource(mImageIds[position]);
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inJustDecodeBounds = false;
            options.inSampleSize = 10;   //width，hight设为原来的十分一
            Bitmap btp =BitmapFactory.decodeStream(is,null,options);
            iv.setImageBitmap(btp);
//                                   iv.setImageResource(mImageIds[position]);
            //给生成的ImageView设置Id，不设置的话Id都是-1
            iv.setId(mImageIds[position]);
            iv.setLayoutParams(new Gallery.LayoutParams(120, 160));
            iv.setScaleType(ImageView.ScaleType.FIT_XY);
            iv.setBackgroundResource(mGalleryItemBackground);
            return iv;
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_success, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}