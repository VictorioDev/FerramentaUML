package com.aula.victoriozansavio.umlp5.activity.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ImageViewCompat;
import android.widget.ImageView;

import com.aula.victoriozansavio.umlp5.R;

/**
 * Created by Victorio Zansavio on 05/12/2018.
 */

public class Utils {
    public static void saveToken(String token, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Faedu", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", token);
        editor.commit();
    }

    public static String getToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Faedu", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("token","");
    }


}
