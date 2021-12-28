package site.linkway.utils;

import java.util.UUID;

public class UUIDUtils {
    /**
     * 获得一个UUID
     * @return UUID(String)
     */
    public static String getUUID(){
        UUID uuid  =  UUID.randomUUID();
        String s = UUID.randomUUID().toString().replace("-","");
        return s;
    }
}
