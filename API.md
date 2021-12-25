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

## `请求参数约定` 
* 给出参数则使用 `application/x-www-form-urlencoded`    
* 输入请求体为请求数据类型 `application/json`    
* 带有上传文件的请求 `multipart/form-data`  


---

## `身份安全` 

### 登录 
`POST /api/identitySecurity/login` 

* 参数 `{id, password}` (id 暂时仅支持用户邮箱)
* 注 返回带有`isAdmin:boolean`字段   

### 退出登录 
`POST /api/identitySecurity/logout`  

### 注册账号
`POST /api/identitySecurity/register`

* 参数 `{password, emailCode}`



### 发送验证码
`POST /api/identitySecurity/sendEmailCode`

* 参数 `{email}`



### 修改密码
`POST /api/identitySecurity/changePassword`

* 参数 `{newPassword, emailCode}`



---

## `个人信息`

### 获得个人信息
`GET /api/user/myData`

* 返回格式 [PersonalData](#PersonalData)



### 更新性别或者昵称
`PUT /api/user/myData`

* 参数 `{sex, name}`



### 更新头像
`PUT /api/user/avatar`

* 表单文件属性 `"file": file`

---

## `购物车`

### 添加购物车条项 

`POST /api/user/cart`

* 参数 `{goodId, num}`



### 删除购物车条项
`DELETE /api/user/cart`  

* 参数 `{cartId}`

### 获得购物车全部条项
`GET /api/user/cart`

* 返回格式 [CartList](#CartList)  

### 购物车条项更新 
注：更新num字段  

`PUT /api/user/cart`  

* 请求格式 [CartItem](#CartItem)  

### 购物车覆盖 

`PUT /api/user/cart/cover`  
* 参数 Array [PostCart](#PostCart)  
* 返回格式 [CartList](#CartList) 

---

## `收货地址`

### 获得全部存储在平台的收货地址
`GET /api/user/addresses`


* 返回格式 [AddressList](#AddressList)



### 删除地址
`DELETE /api/user/addresses` 

* 参数`{addressId}`
* 返回格式 [AddressList](#AddressList)

### 添加地址 
`POST /api/user/addresses` 

* 参数 `{phone, name, address}` 

### 收货地址更新  
`PUT /api/user/addresses`   

* 参数 `{addressId, phone, name, address}`  

---

## `图片分发`

### 获得指定图片 
`GET /api/img?imgId={}` 

---

## `商品浏览` 
### 商品推荐列表(暂为随机检索出20个) 
  
`GET /api/commodity/recommendation`  

* 返回格式  [CommodityTipList](#CommodityTipList)

### 浏览商品  
`POST /api/commodity/detail`  

* 参数`{goodId:string}` 
* 返回格式 [Commodity](#Commodity)  

### 商品类型列表 
`GET /api/commodity/typeList`    
* 无参数 暂不页 总量有限   
* 返回格式 [CommodityTypeList](#CommodityTypeList)

--- 

## `商品评论`   

### 现存问题   
1. 没有买过的东西也可以评论   
2. 一个商品可以有多个同一个用户的一级评论   
3. 暂时只是简单的实现了评论的功能、能像一个小小的社交功能   

### 根据商品id获得评论列表

`POST /api/comment/fList`

* 参数 `{goodId:string,pageSize:number,pageNow:number}`
* 返回格式 [CommentList](#CommentList)




### 根据父评论id请求子评论列表

`POST /api/comment/sList`

* 参数 `{fCommentId:string,pageSize:number,pageNow:number}`
* 返回格式 [CommentList](#CommentList)


### 根据商品id添加父评论
`POST /api/comment/add/f`

* 参数 `{content:string,goodId:string,rate:number(1~5)} `
* 返回格式 `{result:boolean,message:(fCommentId)}` 
* 注：利用message将新的父评论id捎带 如需要改就改


### 根据父评论id增加子评论

`POST /api/comment/add/s`

* 参数 `{content:string,fCommentId:string}`
* 返回格式 `{result:boolean,message:(sCommentId)}`


### 删除父评论
`DELETE /api/comment/delete/f`

* 参数 `{fCommentId:string}`


### 删除子评论
`DELETE /api/comment/delete/s`

* 参数 `{sCommentId:string}`

---

## `订单-用户`
### 增加新订单

`POST /api/user/order`

* 请求格式 [PostOrder](#PostOrder)


### 查看已有订单详情
`POST /order/detail`

* 请求参数 `{orderId}`  
* 返回格式 [OrderItem](#OrderItem)

### 更新订单状态

`POST /api/user/order/update`

* 请求参数 `{orderId,status}`   
* 参数详情 status in ('已签收','退款中')

### 获得用户订单列表
`GET /api/user/order`

* 返回格式 [OrderList](#OrderList)

--- 

## `搜索`
### 商品搜索 
`POST /api/search/commodity`  
* 请求格式 [PostSearch](#PostSearch)
* 返回格式 [CommoditySearchResult](#CommoditySearchResult)

  注:可进行组合查询,当字符串为`''`,数字为`0`时则代表只是用此限制
--- 

## `用户管理`
### 充值 (提供ID与金额)
`POST /api/admin/recharge`  

注：提供用户邮箱与金额进行余额增减（主要为了生产模拟）   
参数 {email:string,amount:number}

---

## `数据类型` 

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
    onSale:number;//0 or 1
    imgsURL:string[];
    detailsImg:string;//商品详情图片URL
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
##### OrderItem 
```typescript 
/*订单项*/
interface OrderItem {
    orderGoods:OrderItemGood[];//订单商品
    order:Order;
}
```
##### OrderItemGood 
```typescript
/*订单商品*/
interface OrderItemGood {
    num:number;
    goodId:string;
    price:double;
    name:string;
    profile:string;//商品描述
    imgsURL:string[];
}
```
##### Order 
```typescript
/*订单*/
interface Order {
    orderId:string;
    userId:string;
    status:string;//'待付款','待发货','已发货','已签收','退款中'
    phone:string;
    time:string;//format YYYY-MM-DD HH:MM:SS  
    address:string;
    name:string;
    logisticsNumber:string;
    logisticsName:string;
    priceCount:double;
}
``` 
##### OrderList
```typescript
/*订单列表*/ 
interface OrderList {
    orderItems:OrderItem[];
    pageNow:number;
    pageSize:number;
    pageCount:number;
}
```

##### CommoditySearchResult
```typescript
/*商品搜索结果*/
interface CommoditySearchResult{
    commodities:Commodity[];
    pageCount:number; //总页数
    pageNow:number;//现在所在页号
    pageSize:number;//每页得的大小
    pageCount:number;//总共页数
}
```

##### CommodityTypeList 
```typescript
/*商品类型列表*/ 
interface CommodityTypeList{
    types:CommodityType[];
}
/*商品类型*/ 
interface CommodityType{
    goodTypeId:number;
    name:string;
}
```



### 请求
#### PostOrder
```typescript
/*提交订单*/
interface PostOrder
{
    goods:OrderGoodItem[];
    addressId:string;
}
```
#### OrderGoodItem
```typescript
/*提交订单商品项*/
interface OrderGoodItem{
    goodId:string;
    num:number;
}
```

#### PostSearch
```typescript
/*提交搜索
  注意事项：number:0 string:'' 则代表不使用此属性
*/
interface PostSearch{
    keyword:string;//商品名称关键词
    searchType:string;//类型名称
    price:MinMax;//价格区间
    pageNow:number;//现在页码1~n
    pageSize:number;//每页大小
    goodId:string;//商品id
}
interface MinMax {
    min:number;//最小
    max:number;//最大
}
```  

#### PostCommodityType
```typescript
interface PostCommodityType {
    goodTypeId:number;
    name:string;
    operator:number;//0 更新操作 1 插入操作
}
```

#### PostCart
```typescript
interface PostCart{
    goodId:string;
    num:number;
}
```


---

## 管理端  
### 商品管理
#### 商品搜索以及获取列表   
   * 请见商品搜索模块 


#### 获取类型列表   
请见商品浏览模块
#### 更新类型列表  
* 请求格式 [PostCommodityType](#PostCommodityType)  
注：会改变原来此类别的商品的类别 
* 返回格式  
当新增时,利用message:string   
捎带新的goodtypeId,注意要parseInt() 

#### 新增商品 
格式 multipart/form-data   
```
price:价格 number
name:名称 string
profile:简介 string
stock:库存量 number
goodTypeId:商品类型号 number (可能需要多个、暂为其指定一个类型)  
onSale:是否进行上架 number(0|1)  
file:string[] 已经上传的图片的imgId数组序列 可为空
detailsImg:File 可为空 
```
#### 更新商品属性    
`POST /api/admin/commodity/update`   
```
goodId:商品号 string  
price:价格 number
name:名称 string
profile:简介 string
stock:库存量 number
goodTypeId:商品类型号 number (可能需要多个、暂为其指定一个类型)  
onSale:是否进行上架 number(0|1)  
```

#### 商品图片删除     
`POST /api/admin/commodity/img/delete`   

参数 `{imgId:string}`  
#### 追加商品图片  
`POST /api/admin/commodity/img/add`   

参数 `{goodId:string,file:File[]}`   
### 订单   
#### 获取订单列表（订单搜索）     
`POST /api/admin/order/search`    

* 提交格式 
```typescript
/*订单搜索、string为'' 时代表不适用此属性*/ 
interface PostOrderSearch{
    orderId:string;//订单号
    email:string;//订单所属者email
    status:string;//订单状态
    time:DateMinMax;//订单产生时间区间 
    pageSize:number;//每页多少个 1~20 
    pageNow:number;//现在所在页码 1~
}
/*想要其不适用则放开区间例如2001-12-21 ~ 未来时间*/
interface DateMinMax{
    min:Date;
    max:Date;//Date format pattern = "YYYY-MM-DD HH:MM:SS"  
}
``` 
* 返回格式   

  [OrderList](#OrderList) 


#### 发货 
`POST /api/admin/order/ship`  

修改物流信息同时更改发货状态  

参数 `{orderId:string,logisticsNumber:string,logisticsName:string}`

#### 同意退款 
`POST /api/admin/order/refund`  

由退款中状态改为已结束、并将订单金额退还至用户（可能还会存在商品库存量问题）  

参数 `{orderId:string} `  


#### 更新商品详情图片 
`POST /api/admin/commodity/detailsImg`  

参数 `{goodId:string,detailsImg:File}`  

### 图片上传  
`POST /api/admin/img/upload`  
* 参数 `{file:File[]}`  
* 返回格式  
```json
{
  "result": true,
  "imgId": [
    "d76647d277ea4073acc8ebca0aa69040",
    "d0a37861bc6d4eb6827ea6df7fb52ef0"
  ],
  "imgURL": [
    "/api/img?imgId=d76647d277ea4073acc8ebca0aa69040",
    "/api/img?imgId=d0a37861bc6d4eb6827ea6df7fb52ef0"
  ]
}
```
---


