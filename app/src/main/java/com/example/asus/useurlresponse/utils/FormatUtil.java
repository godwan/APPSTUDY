package com.example.asus.useurlresponse.utils;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import android.os.Environment;
/**
 * Created by asus on 2016/11/7.
 */

public class FormatUtil {

    /**
     * ½«»º´æÎÄ¼þ¼ÐµÄÊý¾Ý×ª´æµ½vedioÎÄ¼þÏÂ
     * @param recAudioFile
     */
    public static void videoRename(File recAudioFile) {
        String path = Environment.getExternalStorageDirectory()
                .getAbsolutePath()
                + "/mahc/video/"
                + "0" + "/";
        String fileName = new SimpleDateFormat("yyyyMMddHHmmss")
                .format(new Date()) + ".3gp";
        File out = new File(path);
        if (!out.exists()) {
            out.mkdirs();
        }
        out = new File(path, fileName);
        if (recAudioFile.exists())
            recAudioFile.renameTo(out);

    }

    /**
     * ÓÃÒÔ¼ÆÊ±²Ù×÷µÄÏà¹Ø·½·¨
     * @param num
     * @return
     */
    public static String format(int num){

        String s = num + "";
        if (s.length() == 1) {
            s = "0" + s;
        }
        return s;
    }
}
