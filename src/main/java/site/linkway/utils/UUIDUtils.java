package site.linkway.utils;

import java.util.UUID;

public class UUIDUtils {
    public static String getUUID(){
        UUID uuid  =  UUID.randomUUID();
        String s = UUID.randomUUID().toString().replace("-","");
        return s;
    }

    public static void main(String[] args) {
        System.out.println(getUUID());
        System.out.println(getUUID());
        System.out.println(getUUID());
    }
}
