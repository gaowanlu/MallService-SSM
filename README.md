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
  │  │          │  │  └─vo  （视图层实体类(JSON)）  
  │  │          │  ├─filter  （拦截器）  
  │  │          │  ├─mapper  （mapper类）  
  │  │          │  └─service （服务类）  
  │  │          └─utils  （常用工具类）  
  │  ├─resources  
  │  │  ├─mapper（mapper 相关xml文件）  
  │  │  ├─mybean  （自定义bean）    
  │  │  └─sql   (数据库初始化SQL)   
  │  └─webapp  （前端项目）  
  │      └─WEB-INF  


## 相关技术  
> Spring、SpringMVC、Mybatis 、MySQL  
## API  
> /apis/** 为登陆后操作 其余可在未登录状态下访问  
> 返回JSON规范 
>> /apis/** 返回均含有 status(int) 与 result(boolean) 属性   
>> status:200(处理正常) 400(身份错误)  
>> result:false(操作失败)  true(操作成功)

### 身份安全
> * 登录
> /identitySecurity/login?id={email}&password={password}  
> * 注册账号  
> /identitySecurity/register?password={}&emailCode={}  
> * 发送验证码  
> /identitySecurity/sendEmailCode?email={}  
> * 修改密码  
> /identitySecurity/changePassword?newPassword={}&emailCode={}  

### 个人信息  
> * 获得个人信息  
> /api/getMyData  
> * 更新性别或者昵称  
> /api/updateMyData?sex={}&name={}  
> * 更新头像  
> /api/updateHeadImg    表单文件属性 "file":file  

### 购物车
> * 添加购物车条项  
>  /api/addCart?goodId={}&num={}  
> * 删除购物车条项  
>  /api/deleteCart?cartId={}  
> * 获得购物车全部条项  
>  /api/getMyCarts  

### 收货地址  
> * 获得全部存储在平台的收货地址  
>  /api/getMyAddress  
> * 删除地址  
>  /api/deleteAddress?addressId={}  
> * 添加地址  
>  /api/addAddress?phone={}&name={}&address={}  

### 图片分发  
> * 获得指定图片 
> /imgApi?imgId={} 


### 待办事项
> Admin  （管理端）   
> CommentSystem   （评论系统）   
> Order  (用户自我订单的操作)      
> ProductBrowsing   (商品浏览相关)    
> Search (商品搜索)    
 

