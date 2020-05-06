package com.Jordan.Example.gson;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;

public class GsonExample {
    public static void main(String argv[]) {
        HashMap<String, String> hashMap1 = new HashMap<>();
        hashMap1.put("abc", "def");
        Gson gson = new Gson();
        String json = gson.toJson(hashMap1);
        System.out.println("json: " + json);
        HashMap<String, String> hashMap2 = gson.fromJson(json, new TypeToken<HashMap<String, String> >(){}.getType());
        System.out.println("value: " + hashMap2.get("abc"));
    }
}
