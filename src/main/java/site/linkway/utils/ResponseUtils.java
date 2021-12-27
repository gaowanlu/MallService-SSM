package site.linkway.utils;

import javax.servlet.http.HttpServletResponse;

public class ResponseUtils {
    /**
     * 为Content-Type设置 text/json;charset=utf-8
     * @param httpServletResponse
     */
    public static void ContentTypeJSONUTF8(HttpServletResponse httpServletResponse){
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setHeader("content-type","text/json;charset=utf-8");
    }
}
