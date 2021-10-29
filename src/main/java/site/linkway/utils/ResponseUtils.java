package site.linkway.utils;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {
    public static void ContentTypeJSONUTF8(HttpServletResponse httpServletResponse){
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("content-type","text/json;charset=utf-8");
    }
}
