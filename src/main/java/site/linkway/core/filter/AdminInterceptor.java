package site.linkway.core.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import site.linkway.core.entity.vo.ResultMessage;
import site.linkway.core.entity.vo.StatusResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

/*管理员权限拦截器
* /api/admin/**
* */
public class AdminInterceptor implements HandlerInterceptor {
    private ObjectMapper mapper = new ObjectMapper();

    //在请求处理的方法之前执行
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        /*如果身份错误将会 返回json信息*/
        ResultMessage resultMessage=new ResultMessage(false,"非管理员权限");
        HttpSession httpSession=httpServletRequest.getSession();
        String email=(String)httpSession.getAttribute("id");
        Boolean isAdmin=(Boolean) httpSession.getAttribute("isAdmin");
        /*没有管理员身份*/
        if(httpSession==null||email==null||email.equals("")||null==isAdmin){
            httpServletResponse.setStatus(403);
            httpServletResponse.setCharacterEncoding("utf-8");
            httpServletResponse.setContentType("text/json; charset=utf-8");
            PrintWriter pw=httpServletResponse.getWriter();
            pw.write(mapper.writeValueAsString(resultMessage));
            return false;
        }
        /*具有管理员身份*/
        return true;
    }
    //在请求处理方法执行之后执行
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }
    //在dispatcherServlet处理后执行,做清理工作.
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
