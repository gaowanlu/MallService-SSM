# MallService
> 在线网上商城（前台、后台） 

## 项目目录结构  
├─src  
  ├─main  
  │  ├─java  
  │  │  └─site  
  │  │      └─linkway  
  │  │          ├─core  
  │  │          │  ├─controller（控制器）  
  │  │          │  ├─entity  （实体类）  
  │  │          │  │  ├─bo    
  │  │          │  │  ├─po  （mybatis）    
  │  │          │  │  └─vo  （视图层实体类）  
  │  │          │  ├─filter  （过滤器）  
  │  │          │  ├─mapper  （mapper类）  
  │  │          │  └─service （服务类）  
  │  │          └─utils  （常用工具类）  
  │  ├─resources  
  │  │  ├─mapper（mapper 相关xml文件）  
  │  │  ├─mybean  （自定义bean）    
  │  │  └─sql   (数据库初始化SQL)   
  │  └─webapp  （前端项目）  
  │      └─WEB-INF  
  └─test  
      └─java  
          └─site  
              └─linkway  

## 相关技术  
> Spring、SpringMVC、Mybatis 、MySQL  
## API  
### 身份安全
> * 登录
> /IdentitySecurity/login?id={email}&password={password}  
> * 注册账号  
> /IdentitySecurity/register?password={}&emailCode={}  
> * 发送验证码  
> /IdentitySecurity/sendEmailCode?email={}  
> * 修改密码  
> /IdentitySecurity/changePassword?newPassword={}&emailCode={}  

### 个人信息  
> * 获得个人信息  
> /apis/getMyData  
> * 更新性别或者昵称  
> /apis/updateMyData?sex={}&name={}  
> * 更新头像  
> /apis/updateHeadImg    表单文件属性 "file":file  
 

