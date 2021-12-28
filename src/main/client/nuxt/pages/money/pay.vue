<template>
  <div class="box">
    <div class="money-pay">
      <el-card class="container" shadow="always" v-loading="loading">
        <div class="pay-order">
          <div class="el-icon-circle-check"></div>
          <div class="order-info" v-if="list.order">
            <div class="title">订单提交成功！去付款咯～</div>
            <div class="warn">
              请在<span>
                <no-ssr>
                  <vue-countdown
                    @end="getList"
                    :time="3600 * 1000"
                    v-slot="{ days, hours, minutes, seconds }"
                  >
                    {{ days }} 天 {{ hours }} 小时 {{ minutes }} 分
                    {{ seconds }} 秒
                  </vue-countdown>
                </no-ssr> </span
              >内完成支付, 超时后将取消订单
            </div>
            <div class="address" v-show="!detail">
              收货信息：{{ list.order.name }} {{ list.order.phone }}
              {{ list.order.address }}
            </div>
            <div class="order-details" v-show="detail">
              <el-divider></el-divider>
              <ul>
                <li>
                  <div class="label">订单号：</div>
                  <div class="content">
                    <span class="on">{{ list.order.orderId }}</span>
                  </div>
                </li>
                <li>
                  <div class="label">收货信息：</div>
                  <div class="content">
                    {{ list.order.name }}
                    {{ list.order.phone }}
                    {{ list.order.address
                    }}<template>({{ list.order.address }})</template>
                  </div>
                </li>
                <li>
                  <div class="label">商品名称：</div>
                  <div class="content">
                    <!-- <span v-for="(item, index) in list.orderGoods" :key="index"
                      >{{ item.name }}
                    </span> -->
                  </div>
                </li>
              </ul>
            </div>
            <div class="fright">
              <div class="total">
                应付总额：
                <div class="price">
                  <span>{{
                    (list.order.priceCount ? list.order.priceCount : 0)
                      | thousands
                  }}</span
                  >元
                </div>
              </div>
              <div class="show-detail" @click="showDetail">
                订单详情<i class="iconfont mallservice-xia"></i>
              </div>
            </div>
          </div>
        </div>
      </el-card>
      <el-card
        class="container mode-payment-box"
        shadow="always"
        v-loading="loading"
      >
        <div class="title">选择以下支付方式付款</div>
        <el-divider></el-divider>
        <div class="min-title">支付方式</div>
        <div class="list">
          <div
            class="li"
            v-if="user"
            v-loading="buttonLoading"
            @click="payment(1)"
          >
            余额支付（{{ user.money | thousands }}）
          </div>
        </div>
      </el-card>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import "./scss/pay";
</style>

<script>
import js from "./js/pay";
export default js;
</script>
