<template>
  <div class="box">
    <el-page-header @back="goBack" content="订单详情"> </el-page-header>
    <div v-loading="loading">
      <div class="top">
        <div class="order-number">订单号：{{ indent.order.orderId }}</div>
        <div class="operation">
          <div v-if="indent.order.status === '待付款'">
            <el-button :loading="buttonLoading" size="mini">取消订单</el-button>
          </div>
          <NuxtLink
            :to="{ path: '/money/pay', query: { id: indent.order.orderId } }"
            v-if="indent.order.status === '待付款'"
            ><el-button :loading="buttonLoading" size="mini" type="danger"
              >立即付款</el-button
            ></NuxtLink
          >
          <div v-if="indent.state === '已发货'">
            <el-button
              :loading="buttonLoading"
              size="mini"
              type="danger"
              @click="confirmReceipt(item)"
              >确认收货</el-button
            >
          </div>
        </div>
      </div>
      <el-divider></el-divider>
      <div class="steps-box">
        <div class="title">{{ indent.order.status }}</div>
        <div class="steps">
          <div :class="{ on: indent.order.time }">
            <div class="chunk">下单</div>
          </div>
          <div :class="{ on: indent.order.time }">
            <div class="chunk">付款</div>
            <div class="name"></div>
          </div>
          <div :class="{ on: indent.order.time }">
            <div class="chunk">配货</div>
            <div class="name"></div>
          </div>
          <div :class="{ on: indent.order.status === '已收货' }">
            <div class="chunk">交易成功</div>
            <div class="name"></div>
          </div>
        </div>
      </div>
      <div class="steps-box-time">
        <div class="steps">
          <div>
            <div class="chunk">{{ indent.order.time }}</div>
          </div>
          <div>
            <div class="chunk">{{ indent.order.time }}</div>
          </div>
          <div>
            <div class="chunk">{{ indent.order.time }}</div>
          </div>
          <div>
            <div class="chunk">
              {{ indent.order.time }}
            </div>
          </div>
        </div>
      </div>
      <el-table :data="indent.orderGoods" ref="table" class="table">
        <el-table-column align="center" width="100">
          <template slot-scope="scope">
            <NuxtLink
              :to="{
                path: '/product/detail',
                query: { id: scope.row.goodId }
              }"
            >
              <el-image
                class="image"
                :src="scope.row.imgsURL[0]"
                fit="cover"
              />
            </NuxtLink>
          </template>
        </el-table-column>
        <el-table-column label="商品名称">
          <template slot-scope="scope">
            <NuxtLink
              :to="{
                path: '/product/detail',
                query: { id: scope.row.goodId }
              }"
            >
              <p>{{ scope.row.name }}</p>
            </NuxtLink>
          </template>
        </el-table-column>
        <el-table-column label="单价" width="150" align="center">
          <template slot-scope="scope">
            {{ scope.row.price | thousands }}
          </template>
        </el-table-column>
        <el-table-column label="数量" width="150" align="center">
          <template slot-scope="scope">
            {{ scope.row.number }}
          </template>
        </el-table-column>
        <el-table-column label="小计" width="150" align="center">
          <template slot-scope="scope">
            {{ (scope.row.price * scope.row.num) | thousands }}
          </template>
        </el-table-column>
      </el-table>
      <el-divider></el-divider>
      <div class="address" v-if="indent.order">
        <div class="min-title">收货信息</div>
        <div class="li">
          <div class="name">姓名：</div>
          <div class="value">{{ indent.order.name }}</div>
        </div>
        <div class="li">
          <div class="name">联系电话：</div>
          <div class="value">{{ indent.order.phone }}</div>
        </div>
        <div class="li">
          <div class="name">收货地址：</div>
          <div class="value">
            {{ indent.order.address }}
          </div>
        </div>
      </div>
      <el-divider></el-divider>
      <div v-if="indent.refund_time">
        <div class="address">
          <div class="min-title">退款方式</div>
          <div class="li">
            <div class="name">退款方式：</div>
            <div class="value">{{ indent.refund_way }}</div>
          </div>
          <div class="li">
            <div class="name">退款时间：</div>
            <div class="value">{{ indent.refund_time }}</div>
          </div>
          <div class="li">
            <div class="name">退款金额：</div>
            <div class="value">{{ indent.refund_money }}</div>
          </div>
          <div class="li">
            <div class="name">退款原因：</div>
            <div class="value">{{ indent.refund_reason }}</div>
          </div>
        </div>
        <el-divider></el-divider>
      </div>
      <div class="address">
        <div class="min-title">其它</div>
        <div class="li">
          <div class="name">备注：</div>
          <div class="value">{{ indent.remark ? indent.remark : "无" }}</div>
        </div>
      </div>
      <el-divider></el-divider>
      <div class="right">
        <div class="li">
          <div class="name">商品总价：</div>
          <div class="value">{{ total | thousands }}元</div>
        </div>
        <div class="li">
          <div class="name">运费：</div>
          <div class="value" v-if="indent.carriage > 0">
            {{ indent.carriage | thousands }}元
          </div>
          <div class="value" v-else>免运费</div>
        </div>
        <div class="li">
          <div class="name">应付金额：</div>
          <div class="value total">
            <span>{{ indent.total | thousands }}</span
            >元
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import "./scss/detail";
</style>

<script>
import js from "./js/detail";
export default js;
</script>
