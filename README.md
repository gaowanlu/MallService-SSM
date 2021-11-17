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

### 购物车
> * 添加购物车条项  
>  /apis/addCart?goodId={}&num={}  
> * 删除购物车条项  
>  /apis/deleteCart?cartId={}  
> * 获得购物车全部条项  
>  /apis/getMyCarts  

### 收货地址  
> * 获得全部存储在平台的收货地址  
>  /apis/getMyAddress  
> * 删除地址  
>  /apis/deleteAddress?addressId={}  
> * 添加地址  
>  /apis/addAddress?phone={}&name={}&address={}  

### 图片分发  
> * 获得指定图片  
> /imgapi?imgId={}  


### 待办事项
> Admin  （管理端）   
> CommentSystem   （评论系统）   
> Order  (用户自我订单的操作)      
> ProductBrowsing   (商品浏览相关)    
> Search (商品搜索)    
 

