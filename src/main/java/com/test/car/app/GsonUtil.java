package com.test.car.app;

import com.google.gson.Gson;
import okio.ByteString;

public class GsonUtil {

    public static <T> T fromJson(ByteString json, Class<T> targetClass) {
        return new Gson().fromJson(json.utf8(), targetClass);
    }

    public static ByteString toJson(Object object) {
        return ByteString.encodeUtf8(new Gson().toJson(object));
    }
}
