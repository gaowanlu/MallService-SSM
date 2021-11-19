package site.linkway.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import site.linkway.core.controller.UserData;
import site.linkway.core.entity.vo.StatusResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

public class ApiInterceptor implements HandlerInterceptor {
    static Logger logger= Logger.getLogger(ApiInterceptor.class);
    private ObjectMapper mapper = new ObjectMapper();


    //在请求处理的方法之前执行
    //如果返回true执行下一个拦截器
    //如果返回false就不执行下一个拦截器
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        StatusResult statusResult=new StatusResult();
        httpServletResponse.setCharacterEncoding("utf-8");
        httpServletResponse.setHeader("content-type","text/json;charset=utf-8");
        //检测id与cookie值是否匹配,匹配则放行，不匹配则拒绝通过
        HttpSession httpSession=httpServletRequest.getSession();
        if(httpSession==null){
            httpServletResponse.setStatus(403);
            statusResult.setResult(false);
            PrintWriter pw=httpServletResponse.getWriter();
            pw.write(mapper.writeValueAsString(statusResult));
           return false;
        }
        //获取id，即邮箱
        String email=(String)httpSession.getAttribute("id");
        if(email==null||email.equals("")){
            httpServletResponse.setStatus(400);
            statusResult.setResult(false);
            PrintWriter pw=httpServletResponse.getWriter();
            pw.write(mapper.writeValueAsString(statusResult));
            return false;
        }
        return true;
    }
    //在请求处理方法执行之后执行
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    //在dispatcherServlet处理后执行,做清理工作.
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
