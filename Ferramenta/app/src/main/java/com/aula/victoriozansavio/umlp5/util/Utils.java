package com.aula.victoriozansavio.umlp5.util;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.aula.victoriozansavio.umlp5.activity.LoginActivity;
import com.aula.victoriozansavio.umlp5.library.LoginResult;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

/**
 * Created by Victorio Zansavio on 05/12/2018.
 */

public class Utils {

    public static void saveToken(LoginResult loginResult, Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Faedu", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("token", loginResult.getToken());
        editor.putString("expiration", loginResult.getExpiration());
        editor.putString("id", loginResult.getId());
        editor.putString("level", loginResult.getLevel() + "");
        if(!editor.commit()){
            Toast.makeText(context, "Erro ao salvar Preferences!", Toast.LENGTH_SHORT).show();
        }

    }

    public static String getToken(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Faedu", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("token","");
    }

    public static String getExpiration(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Faedu", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("expiration","");
    }

    public static String getId(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Faedu", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("id","");
    }

    public static String getLevel(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Faedu", Context.MODE_PRIVATE);
        return  sharedPreferences.getString("level","");
    }


    public static boolean verifyUserTokenValidation(String id, String token, Context context){
        if(!id.isEmpty()){
            if(!token.isEmpty()){
                String expiration = Utils.getExpiration(context);
                DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");
                DateTime expirationDate = formatter.parseDateTime(expiration);
                DateTime dtNow = new DateTime();
                Log.i("App", "Id: " + id + "\n Token: " + token + "\n Expiration: " + expiration + "\n Now: " + dtNow);
                if(dtNow.isBefore(expirationDate) || dtNow.equals(expirationDate)){
                    return true;
                }
            }
        }
        return false;
    }

    public static void redirectToLoginPage(Context context){
        //Toast.makeText(context,"É necessário realizar Login!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(context, LoginActivity.class);
        context.startActivity(i);
    }

    public static void logOut(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("Faedu", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }

}
