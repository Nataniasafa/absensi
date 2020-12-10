package com.natania.uas.remote;

public class APIUtils {

    private APIUtils(){
    }

    public static final String API_URL =
            "http://192.168.0.5/absensi/index.php/";

    public static DataService getDataService(){
        return RetrofitClient.getClient(API_URL)
                .create(DataService.class);
    }
}
