package com.example.azuredragon.cache;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Base64;

import com.example.azuredragon.http.bean.LoginBean;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class PreferencesUtils {


    /**
     * @param user
     */
    public static void saveUser(Context context, String preferenceName,String key,LoginBean user) throws Exception {
        if(user instanceof Serializable) {
            SharedPreferences sharedPreferences = context.getSharedPreferences(preferenceName, context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            try {
                ObjectOutputStream oos = new ObjectOutputStream(baos);
                oos.writeObject(user);//把对象写到流里
                String temp = new String(Base64.encode(baos.toByteArray(), Base64.DEFAULT));
                editor.putString(key, temp);
                editor.commit();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else {
            throw new Exception("User must implements Serializable");
        }
    }

    public static LoginBean getUser(Context context, String preferenceName, String key) {
        SharedPreferences sharedPreferences=context.getSharedPreferences(preferenceName,context.MODE_PRIVATE);
        String temp = sharedPreferences.getString(key, "");
        ByteArrayInputStream bais =  new ByteArrayInputStream(Base64.decode(temp.getBytes(), Base64.DEFAULT));
        LoginBean user = null;
        try {
            ObjectInputStream ois = new ObjectInputStream(bais);
            user = (LoginBean) ois.readObject();
        } catch (IOException e) {
        }catch(ClassNotFoundException e1) {

        }
        return user;
    }

    public static void cleanUser(Context context, String preferenceName, String key) {
        SharedPreferences sharedPreferences=context.getSharedPreferences(preferenceName,context.MODE_PRIVATE);
        sharedPreferences.edit().clear().commit();
    }

}
