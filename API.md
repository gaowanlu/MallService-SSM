---
breaks: false
---

# MallService API 文档

（最新版本/草稿见[此处](https://md.yuuza.net/8db-waSeSdW1NnyYmYZeYw)）

## 返回 JSON 规范

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



## 身份安全

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



## 个人信息

#### 获得个人信息
`GET /api/myData`

返回 `PersonalData` （[数据类型](#数据类型)）

#### 更新性别或者昵称
`PUT /api/myData`

参数 `{sex, name}`

#### 更新头像
`PUT /api/myHeadImg`

表单文件属性 `"file": file`



## 购物车

#### 添加购物车条项
`POST /cart`

参数 `{goodId, num}`

#### 删除购物车条项
`DELETE /api/delete/{cartId}`

删除后，返回现有 `CartList`

#### 获得购物车全部条项
`GET /cart`

返回现有 `CartList`



## 收货地址

#### 获得全部存储在平台的收货地址
`GET /api/addresses`

#### 删除地址
`DELETE /api/addresses/{id}`

#### 添加地址
`POST /api/addresses`

参数 `{phone, name, address}`



## 图片分发

#### 获得指定图片 
`GET /imgApi?imgId={}`



## 待定 API

#### Admin  （管理端）
#### CommentSystem   （评论系统） 
#### Order  (用户自我订单的操作)
#### ProductBrowsing   (商品浏览相关)
#### Search (商品搜索)


## 数据类型

```typescript
interface PersonalData {
    userId: string;
    name: string;
    sex: string;
    headImgId: string;
    money: number;
    email: string;
}

interface Cart {
    cartId: string;
    goodId: string;
    userId: string;
    num: number;
}

interface CartList {
    result: boolean;
    carts: Cart[];
}
```