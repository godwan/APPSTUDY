package com.example.asus.useurlresponse.tools;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import com.example.asus.useurlresponse.R;
import com.example.asus.useurlresponse.utils.FormatUtil;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageFormat;
import android.graphics.Matrix;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.hardware.Camera.PictureCallback;
import android.media.MediaRecorder;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.app.Activity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by asus on 2016/11/7.
 */
public class TakePhoto extends Activity {

    private Camera camera; // Ïà»ú¶ÔÏó
    private boolean isPreview = false; // ÊÇ·ñÎªÔ¤ÀÀÄ£Ê½

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); // ÉèÖÃÈ«ÆÁÏÔÊ¾
        setContentView(R.layout.activity_camera_main);
        /****************** ÅÐ¶ÏÊÇ·ñ°²×°SD¿¨ *********************************/
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Toast.makeText(this, "Çë°²×°SD¿¨£¡", Toast.LENGTH_SHORT).show(); // µ¯³öÏûÏ¢ÌáÊ¾¿òÏÔÊ¾ÌáÊ¾ÐÅÏ¢
        }
        /******************************************************************/
        SurfaceView sv = (SurfaceView) findViewById(R.id.surfaceView1); // »ñÈ¡SurfaceView×é¼þ£¬ÓÃÓÚÏÔÊ¾Ïà»úÔ¤ÀÀ
        final SurfaceHolder sh = sv.getHolder();
        sh.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS); // ÉèÖÃ¸ÃSurfaceHolder×Ô¼º²»Î¬»¤»º³å

        Button preview = (Button) findViewById(R.id.preview); // »ñÈ¡¡°Ô¤ÀÀ¡±°´Å¥
        preview.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                // Èç¹ûÏà»úÎª·ÇÔ¤ÀÀÄ£Ê½£¬Ôò´ò¿ªÏà»ú
                if (!isPreview) {
                    camera=Camera.open(); // ´ò¿ªÏà»ú
                }
                try {
                    camera.setPreviewDisplay(sh); // ÉèÖÃÓÃÓÚÏÔÊ¾Ô¤ÀÀµÄSurfaceView
                    Camera.Parameters parameters = camera.getParameters();	//»ñÈ¡Ïà»ú²ÎÊý
                    parameters.setPictureSize(640, 480);	//ÉèÖÃÔ¤ÀÀ»­ÃæµÄ³ß´ç
                    parameters.setPictureFormat(PixelFormat.JPEG);	//Ö¸¶¨Í¼Æ¬ÎªJPEGÍ¼Æ¬
                    parameters.set("jpeg-quality", 80);	//ÉèÖÃÍ¼Æ¬µÄÖÊÁ¿
                    parameters.setPictureSize(640, 480); 	//ÉèÖÃÅÄÉãÍ¼Æ¬µÄ³ß´ç
                    camera.setParameters(parameters);	//ÖØÐÂÉèÖÃÏà»ú²ÎÊý
                    camera.startPreview();	//¿ªÊ¼Ô¤ÀÀ
                    camera.autoFocus(null); // ÉèÖÃ×Ô¶¯¶Ô½¹
                    camera.setDisplayOrientation(90);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        });

        Button takePhoto = (Button) findViewById(R.id.takephoto); // »ñÈ¡¡°ÅÄÕÕ¡±°´Å¥
        takePhoto.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(camera!=null){
                    camera.takePicture(null, null, jpeg); // ½øÐÐÅÄÕÕ
                }
            }
        });
    }
    //ÊµÏÖÅÄÕÕµÄ»Øµ÷½Ó¿Ú
    final PictureCallback jpeg = new PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            // ¸ù¾ÝÅÄÕÕËùµÃµÄÊý¾Ý´´½¨Î»Í¼
            final Bitmap bm = BitmapFactory.decodeByteArray(data, 0,
                    data.length);
            final Bitmap bitmap = rotate(bm,90);
            // ¼ÓÔØlayout/save.xmlÎÄ¼þ¶ÔÓ¦µÄ²¼¾Ö×ÊÔ´
            View saveView = getLayoutInflater().inflate(R.layout.activity_camera_savephoto, null);
            final EditText photoName = (EditText) saveView
                    .findViewById(R.id.phone_name);
            // »ñÈ¡¶Ô»°¿òÉÏµÄImageView×é¼þ
            ImageView show = (ImageView) saveView.findViewById(R.id.show);
            show.setImageBitmap(bitmap);			// ÏÔÊ¾¸Õ¸ÕÅÄµÃµÄÕÕÆ¬
            camera.stopPreview();		//Í£Ö¹Ô¤ÀÀ
            isPreview = false;

            // Ê¹ÓÃ¶Ô»°¿òÏÔÊ¾saveDialog×é¼þ
            new AlertDialog.Builder(TakePhoto.this).setView(saveView)
                    .setPositiveButton("保存", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            File file = new File("/sdcard/pictures/" + photoName
                                    .getText().toString() + ".jpg");//´´½¨ÎÄ¼þ¶ÔÏó
                            try {
                                file.createNewFile();								//´´½¨Ò»¸öÐÂÎÄ¼þ
                                FileOutputStream fileOS = new FileOutputStream(file);	//´´½¨Ò»¸öÎÄ¼þÊä³öÁ÷¶ÔÏó
                                //½«Í¼Æ¬ÄÚÈÝÑ¹ËõÎªJPEG¸ñÊ½Êä³öµ½Êä³öÁ÷¶ÔÏóÖÐ
                                bm.compress(Bitmap.CompressFormat.JPEG, 100, fileOS);
                                fileOS.flush();									//½«»º³åÇøÖÐµÄÊý¾ÝÈ«²¿Ð´³öµ½Êä³öÁ÷ÖÐ
                                fileOS.close();									//¹Ø±ÕÎÄ¼þÊä³öÁ÷¶ÔÏó
                                isPreview = true;
                                resetCamera();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }

                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int which) {
                    isPreview = true;
                    resetCamera();	//ÖØÐÂÔ¤ÀÀ

                }
            }).show();
        }
    };
    //ÖØÐÂÔ¤ÀÀ
    private void resetCamera(){
        if(isPreview){
            camera.startPreview();	//¿ªÆôÔ¤ÀÀ
        }
    }
    //Í£Ö¹Ô¤ÀÀ²¢ÊÍ·Å×ÊÔ´
    @Override
    protected void onPause() {
        if(camera!=null){
            camera.stopPreview();	//Í£Ö¹Ô¤ÀÀ
            camera.release();	//ÊÍ·Å×ÊÔ´
        }
        super.onPause();
    }
    public static Bitmap rotate(Bitmap b, int degrees) {
        if (degrees != 0 && b != null) {
            Matrix m = new Matrix();
            m.setRotate(degrees,
                    (float) b.getWidth() / 2, (float) b.getHeight() / 2);
            try {
                Bitmap b2 = Bitmap.createBitmap(b, 0, 0, b.getWidth(), b.getHeight(), m, true);
                if (b != b2) {
                    b.recycle();  //Android开发网再次提示Bitmap操作完应该显示的释放
                    b = b2;
                }

            } catch (OutOfMemoryError ex) {
//                     建议大家如何出现了内存不足异常，最好return 原始的bitmap对象。.
            }
        }
        return b;
    }
}
