# MallService API 文档

（最新版本/草稿见[此处](https://md.yuuza.net/8db-waSeSdW1NnyYmYZeYw)）
## 部署版本 git node hash 667ac20fb7ba6cb57b95a4973443968448c8b3b8
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
`POST /api/identitySecurity/login`

* 参数 `{id, password}` (id 暂时仅支持用户邮箱)



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

* 返回格式 `PersonalData` [数据类型](#返回)



### 更新性别或者昵称
`PUT /api/user/myData`

* 参数 `{sex, name}`



### 更新头像
`PUT /api/user/avatar`

* 表单文件属性 `"avatar": file`

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

* 返回格式 `CartList` [数据类型](#返回)

---

## `收货地址`

### 获得全部存储在平台的收货地址
`GET /api/user/addresses`


* 返回格式 `AddressList` [数据类型](#返回)



### 删除地址
`DELETE /api/user/addresses` 

* 参数`{addressId}`
* 返回格式 `AddressList` [数据类型](#返回)

### 添加地址 
`POST /api/user/addresses` 

* 参数 `{phone, name, address}` 

---

## `图片分发`

### 获得指定图片 
`GET /api/img?imgId={}` 

---

## `商品浏览` 
### 商品推荐列表(暂为随机检索出20个) 
  
`GET /api/commodity/recommendation`  

* 返回格式  `CommodityTipList` [数据类型](#返回)

### 浏览商品  
`POST /api/commodity/detail`  

* 参数`{goodId:string}` 
* 返回格式 `Commodity` [数据类型](#返回) 

--- 

## `商品评论`

### 根据商品id获得评论列表

`POST /api/comment/fList`
```
参数 {goodId:string,pageSize:number,pageNow:number}
返回格式 CommentList
```



### 根据父评论id请求子评论列表

`POST /api/comment/sList`
```
参数 {fCommentId:string,pageSize:number,pageNow:number}
返回格式 CommentList
```

### 根据商品id添加父评论
`POST /api/comment/add/f`
```
参数 {content:string,goodId:string,rate:number(1~5)}
返回格式 {result:boolean,message:(fCommentId)}
注：利用message将新的父评论id捎带 如需要改就改
```

### 根据父评论id增加子评论

`POST /api/comment/add/s`
```
参数 {content:string,fCommentId:string}
返回格式 {result:boolean,message:(sCommentId)}
```

### 删除父评论
`DELETE /api/comment/delete/f`
```
参数 {fCommentId:string}
```

### 删除子评论
`DELETE /api/comment/delete/s`
```
参数 {sCommentId:string}
```
---

## 订单-用户
### 增加新订单

`POST /api/user/order`

* 请求格式 `PostOrder` ([数据类型](#请求))


### 查看已有订单详情
`POST /order/detail`

* 请求参数 `{orderId}`  
* 返回格式 `OrderItem`

### 更新订单状态

`POST /api/user/order/update`

* 请求参数 `{orderId,status}`   
* 参数详情 status in ('已签收','退款中')

### 获得用户订单列表
`GET /api/user/order`

* 返回格式 `OrderList` ([数据类型](#返回))

--- 

## 商品搜索
`POST /api/search/commodity`  
* 请求格式 josn `PostSearch` ([数据类型](#返回))
* 返回格式 `CommoditySearchResult` ([数据类型](#返回))

  注:可进行组合查询,当字符串为`''`,数字为`0`时则代表只是用此限制
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
    onSale:number;//0 or 1
    imgsURL:string[];
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
    time:string;
    address:string;
    name:string;
    logisticsNumber:string;
    logisticsName:string;
    priceCount:double;
}
``` 
##### OrderList
``` typescript 
/*订单列表*/ 
interface OrderList {
    orderItems:OrderItem[];
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
/*提交搜索*/
interface PostSearch{
    keyword:string;//商品名称关键词
    searchType:string;
    price:MinMax;
    pageNow:number;
    pageSize:number;
}
interface MinMax {
    min:number;
    max:number;
}
```

---

## 进行中

---
### 管理端
* 商品管理    
  上下架、搜索、列表分页、新增商品、删除商品、类别管理

* 订单  
  订单状态管理、待处理列表、订单列表、订单搜索

* 用户管理  
  充值 (提供ID与金额)
