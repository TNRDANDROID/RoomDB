package com.nic.newapkproject.Activity.Activity.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class Utils {

    public static boolean isNullJson(JSONObject jsonObject){
        try {
            if(jsonObject.isNull("")){
                return true;
            }
            else {
                return false;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
}
