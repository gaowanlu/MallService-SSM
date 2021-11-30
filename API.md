# MallService API 文档

（最新版本/草稿见[此处](https://md.yuuza.net/8db-waSeSdW1NnyYmYZeYw)）

## `返回 JSON 规范`

操作成功时：

```jsonc
{
    "result": true,
    // ...其它信息...
}
```

操作失败时：


```jsonc
{
    "result": false,
    "message": "（错误消息）"
}
```



## `身份安全`

#### 登录
`POST /identitySecurity/login`

参数 `{id, password}` （id 是用户邮箱）



#### 注册账号
`POST /identitySecurity/register`

参数 `{password, emailCode}`



#### 发送验证码
`POST /identitySecurity/sendEmailCode`

参数 `{email}`



#### 修改密码
`POST /identitySecurity/changePassword`

参数 `{newPassword, emailCode}`



## `个人信息`

#### 获得个人信息
`GET /api/myData`

返回 `PersonalData` （[数据类型](#数据类型)）



#### 更新性别或者昵称
`PUT /api/myData`

参数 `{sex, name}`



#### 更新头像
`PUT /api/profilePhoto`

表单文件属性 `"file": file`



## `购物车`

#### 添加购物车条项 

`POST /api/cart`

参数 `{goodId, num}`



#### 删除购物车条项
`DELETE /api/cart`
参数 `{cartId}`

#### 获得购物车全部条项
`GET /api/cart`

返回格式 `CartList` （[数据类型](#数据类型)）





## `收货地址`

#### 获得全部存储在平台的收货地址
`GET /api/addresses`

返回 `AddressList`



#### 删除地址
`DELETE /api/addresses` 

参数`{addressId}`

返回 `AddressList`



#### 添加地址 
`POST /api/addresses` 

参数 `{phone, name, address}` 


## `图片分发`

#### 获得指定图片 
`GET /imgApi?imgId={}` 



## 进行中 
#### Order  (用户自我订单的操作) code: gaowanlu 
* 产生新订单
* 查看已有订单详情
* 订单取消（退款申请）


## 待定 API 

#### Admin  （管理端）
* 商品管理 
* 订单管理 
* 商品类别管理  
* 主页展示管理 
#### CommentSystem   （评论系统） 
* 对商品发布评价（文字+评星） 
* 发布子评论（文字） 
* 评论浏览

#### ProductBrowsing   (商品浏览相关)
* 商品推荐列表   
* 浏览商品详情
#### Search (商品搜索)
* 根据类别检索   
* 名称关键词检索
## 数据类型

```typescript 
/*个人信息*/
interface PersonalData {
    userId: string;
    name: string;
    sex: string;
    profilePhotoURL: string;
    money: number;
    email: string;
}

/*购物车条项*/
interface CartItem {
    cartId: string;
    goodId: string;
    userId: string;
    num: number;
    price:number;
    name:string;
    profile:string;
    stock:number;
    soldSum:number;
    goodType:string;
}

/*购物车列表*/
interface CartList {
    result: boolean;
    carts: CartItem[];
}

/*收货地址*/
interface Address{
    addressId: string;
    userId: string;
    phone: string;
    address: string;
    name: string;
}

/*收货地址列表*/
interface AddressList{
    result: boolean;
    addresses: Address[];
}



```
