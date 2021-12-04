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

---

## `身份安全`

#### 登录
`POST /identitySecurity/login`

* 参数 `{id, password}` (id 暂时仅支持用户邮箱)



#### 注册账号
`POST /identitySecurity/register`

* 参数 `{password, emailCode}`



#### 发送验证码
`POST /identitySecurity/sendEmailCode`

* 参数 `{email}`



#### 修改密码
`POST /identitySecurity/changePassword`

* 参数 `{newPassword, emailCode}`

---

## `个人信息`

#### 获得个人信息
`GET /api/myData`

* 返回格式 `PersonalData` ([数据类型](#数据类型))



#### 更新性别或者昵称
`PUT /api/myData`

* 参数 `{sex, name}`



#### 更新头像
`PUT /api/profilePhoto`

* 表单文件属性 `"file": file`

---

## `购物车`

#### 添加购物车条项 

`POST /api/cart`

* 参数 `{goodId, num}`



#### 删除购物车条项
`DELETE /api/cart`  

* 参数 `{cartId}`

#### 获得购物车全部条项
`GET /api/cart`

* 返回格式 `CartList` ([数据类型](#数据类型))

---

## `收货地址`

#### 获得全部存储在平台的收货地址
`GET /api/addresses`


* 返回格式 `AddressList`  ([数据类型](#数据类型))



#### 删除地址
`DELETE /api/addresses` 

* 参数`{addressId}`
* 返回格式 `AddressList` ([数据类型](#数据类型))

#### 添加地址 
`POST /api/addresses` 

* 参数 `{phone, name, address}` 

---

## `图片分发`

#### 获得指定图片 
`GET /imgApi?imgId={}` 

---

## `商品浏览` 
#### 商品推荐列表(暂为随机检索出20个) 
  
`GET /commodity/recommendation`  

* 返回格式  `CommodityTipList` ([数据类型](#数据类型)) 

#### 浏览商品  
`POST /commodity/detail`  

* 参数`{goodId:string}` 
* 返回格式 `Commodity` ([数据类型](#数据类型)) 

---

## 进行中 
### Order  (订单-用户) 
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


## 待定 API 

#### 管理端  
* 商品管理 
* 订单管理 
* 商品类别管理  
* 主页展示管理 
#### 商品评论 
* 对商品发布评价（文字+评星） 
* 发布子评论（文字） 
* 评论浏览 

#### 商品搜索 
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
    goodTypeId:number;
    imgsURL:string[] //商品相关图片
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
    result:boolean;
    addresses:Address[];
}

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

/*商品推荐列表*/
interface CommodityTipList{
    result:boolean;
    commodities:Commodity[]
}
```

## 拟定数据类型
```typescript

/*商品搜索结果*/
interface CommoditySearchResult{
    result:boolean,
    commodities:Commodity[],
    pageSum:number //总页数
}
```
