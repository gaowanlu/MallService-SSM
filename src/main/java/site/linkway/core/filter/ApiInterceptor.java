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

/*
* 登录身份拦截器
* 拦截资源 /api/**
* */
public class ApiInterceptor implements HandlerInterceptor {
    static Logger logger= Logger.getLogger(ApiInterceptor.class);
    private ObjectMapper mapper = new ObjectMapper();

    //在请求处理的方法之前执行
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /*如果身份错误将会 返回json信息*/
        StatusResult statusResult=new StatusResult();
        //检测id与cookie值是否匹配,匹配则放行，不匹配则拒绝通过
        HttpSession httpSession=httpServletRequest.getSession();
        /*获取id，即邮箱 原因登录暂使用 邮箱登录
        有相对于所有用户是唯一的 userId 采用为UUID 不便于操作*/
        String email=(String)httpSession.getAttribute("id");
        /*没有用户身份*/
        if(httpSession==null||email==null||email.equals("")){
            httpServletResponse.setStatus(403);
            statusResult.setResult(false);
            PrintWriter pw=httpServletResponse.getWriter();
            pw.write(mapper.writeValueAsString(statusResult));
            return false;
        }
        /*具有用户身份 则可以访问需登录才能够进行访问的资源*/
        return true;
    }
    //在请求处理方法执行之后执行
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    //在dispatcherServlet处理后执行,做清理工作.
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
