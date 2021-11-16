package site.linkway.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID(){
        UUID uuid  =  UUID.randomUUID();
        String s = UUID.randomUUID().toString();
        return s;
    }
}
