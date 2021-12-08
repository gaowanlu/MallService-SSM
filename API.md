# MallService API 文档

（最新版本/草稿见[此处](https://md.yuuza.net/8db-waSeSdW1NnyYmYZeYw)）

## `返回 JSON 规范`

### 操作成功时：

```jsonc
{
    "result": true,
    // ...其它信息...
}
```

### 操作失败时：


```jsonc
{
    "result": false,
    "message": "（错误消息）"
}
```

---

## `身份安全`

### 登录
`POST /identitySecurity/login`

* 参数 `{id, password}` (id 暂时仅支持用户邮箱)



### 注册账号
`POST /identitySecurity/register`

* 参数 `{password, emailCode}`



### 发送验证码
`POST /identitySecurity/sendEmailCode`

* 参数 `{email}`



### 修改密码
`POST /identitySecurity/changePassword`

* 参数 `{newPassword, emailCode}`

---

## `个人信息`

### 获得个人信息
`GET /api/myData`

* 返回格式 `PersonalData` ([数据类型](#数据类型))



### 更新性别或者昵称
`PUT /api/myData`

* 参数 `{sex, name}`



### 更新头像
`PUT /api/profilePhoto`

* 表单文件属性 `"file": file`

---

## `购物车`

### 添加购物车条项 

`POST /api/cart`

* 参数 `{goodId, num}`



### 删除购物车条项
`DELETE /api/cart`  

* 参数 `{cartId}`

### 获得购物车全部条项
`GET /api/cart`

* 返回格式 `CartList` ([数据类型](#数据类型))

---

## `收货地址`

### 获得全部存储在平台的收货地址
`GET /api/addresses`


* 返回格式 `AddressList`  ([数据类型](#数据类型))



### 删除地址
`DELETE /api/addresses` 

* 参数`{addressId}`
* 返回格式 `AddressList` ([数据类型](#数据类型))

### 添加地址 
`POST /api/addresses` 

* 参数 `{phone, name, address}` 

---

## `图片分发`

### 获得指定图片 
`GET /imgApi?imgId={}` 

---

## `商品浏览` 
### 商品推荐列表(暂为随机检索出20个) 
  
`GET /commodity/recommendation`  

* 返回格式  `CommodityTipList` ([数据类型](#数据类型)) 

### 浏览商品  
`POST /commodity/detail`  

* 参数`{goodId:string}` 
* 返回格式 `Commodity` ([数据类型](#数据类型)) 

--- 

## `商品评论`

### 根据商品id获得评论列表

`POST /comment/fList`
```
参数 {goodId:string,pageSize:number,pageNow:number}
返回格式 CommentList
```



### 根据父评论id请求子评论列表

`POST /comment/sList`
```
参数 {fCommentId:string,pageSize:number,pageNow:number}
返回格式 CommentList
```

### 根据商品id添加父评论
```
POST /comment/add/f

参数 {content:string,goodId:string,rate:number(1~5)}
返回格式 {result:boolean,message:(fCommentId)}
注：利用message将新的父评论id捎带 如需要改就改
```

### 根据父评论id增加子评论

`POST /comment/add/s`
```
参数 {content:string,fCommentId:string}
返回格式 {result:boolean,message:(sCommentId)}
```

### 删除父评论
`DELETE /comment/delete/f`
```
参数 {fCommentId:string}
```

### 删除子评论
`DELETE /comment/delete/s`
```
参数 {sCommentId:string}
```



---

## 进行中 
### `订单-用户`  
coding: [@gaowanlu](https://www.github.com/gaowanlu) 
* 产生新订单 

```
拟定提交格式 json
{
    List<OrderGoodItem> goods;
    String addressId;
}

interface OrderGoodItem{
    goodId:string,
    num:number
}

返回格式
{
    result: boolean,
    massge: '下单失败相关原因' 
}
```
* 查看已有订单详情 (个人订单有限暂不分页)
```
返回格式
{
    result:boolean;
    orders:Order[];
}

返回格式
{
    orderId:string;//订单号 
    statusId:number; //订单状态号 
    statusDescribe:string;//订单状态描述 
    goods:[ 
        {good:Commodity,num:number}, 
        {good:Commodity,num:number} 
    ] 
}

``` 
* 订单取消（退款申请 需要管理员审批退款） 
```
请求格式 表单 
参数 {orderId:string} 
```

* 确认签收
```
请求格式 表单 
参数 {orderId:string} 
```




--- 




## 待定 API (🔫)

### 管理端  
* 商品管理  
* 订单管理  
* 商品类别管理   
* 主页展示管理  

### 商品搜索 
* 根据类别检索   
* 名称关键词检索  

---

## 数据类型 

### 返回 

##### PersonalData
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
```

##### CartItem
```typescript 
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
    goodTypeId:number;
    imgsURL:string[] //商品相关图片
}
```

##### CartList
```typescript 
/*购物车列表*/
interface CartList {
    result: boolean;
    carts: CartItem[];
}
``` 

##### Address
```typescript 
/*收货地址*/
interface Address{
    addressId: string;
    userId: string;
    phone: string;
    address: string;
    name: string;
}
``` 

##### AddressList
```typescript 
/*收货地址列表*/
interface AddressList{
    result:boolean;
    addresses:Address[];
}
``` 

##### Commodity
```typescript 
/*商品*/
interface Commodity{
    goodType:string;
    goodTypeId:number;
    name:string;
    profile:string;
    price:number;
    goodId:string;
    stock:number;
    soldSum:number;
    imgsURL:string[]
}
``` 

##### CommodityTipList
```typescript 
/*商品推荐列表*/
interface CommodityTipList{
    result:boolean;
    commodities:Commodity[]
}
``` 

##### CommentList
```typescript 
/*评论列表*/ 
interface CommentList{
    result:boolean;
    comments:Comment[];//评论列表
    pageNow:number;//现在所在页号
    pageSize:number;//每页得的大小
    pageCount:number;//总共页数
}
``` 

##### Comment
```typescript 
/*评论*/ 
interface Comment{
    commentId:string;//评论id
    content:string;//评论文字内容
    userId:string;//用户id
    userName:string;//用户昵称
    avatarURL:string;//用户头像URL
    time:string;//评论发布时间
    rate:number;//(注：子评论没有rate，但此字段返回至客户端) 
    childCount:number;//此条评论子评论数量
}
``` 

### 请求

---

## 拟定数据类型

```typescript

/*商品搜索结果*/
interface CommoditySearchResult{
    result:boolean;
    commodities:Commodity[];
    pageCount:number; //总页数
    pageNow:number;//现在所在页号
    pageSize:number;//每页得的大小
    pageCount:number;//总共页数
}
```
