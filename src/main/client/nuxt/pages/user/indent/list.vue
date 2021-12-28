<template>
  <div class="box">
    <div class="user-title">我的订单</div>
    <div class="padding-top-20">
      <el-tabs v-model="listQuery.index" @tab-click="getReloadList">
        <el-tab-pane label="全部订单" name="0"></el-tab-pane>
        <el-tab-pane label="待支付" name="1"></el-tab-pane>
        <el-tab-pane label="待发货" name="2"></el-tab-pane>
        <el-tab-pane label="待收货" name="3"></el-tab-pane>
      </el-tabs>
      <div class="indent-list" v-loading="loading">
        <div class="navigation">
          <div class="good">宝贝</div>
          <div class="total">实付款</div>
          <div class="state">交易状态</div>
          <div class="operation">交易操作</div>
        </div>
        <div class="li" v-for="(item, index) in orderList" :key="index">
          <div class="top">
            <div class="time">{{ item.order.time }}</div>
            <div class="odd">
              订单号: <span>{{ item.order.orderId }}</span>
            </div>
            <div
              class="delete"
              v-if="
                item.state === 4 ||
                  item.state === 5 ||
                  item.state === 6 ||
                  item.state === 7
              "
              @click="deleteOrder(item)"
            >
              <i class="el-icon-delete"></i>
            </div>
          </div>
          <div class="details">
            <div class="good">
              <div
                class="good-li"
                v-for="(good, goodIndex) in item.orderGoods"
                :key="goodIndex"
              >
                <NuxtLink
                  :to="{ path: '/product/detail', query: { id: good.good_id } }"
                >
                  <el-image class="image" :src="good.imgsURL[0]" fit="cover" />
                </NuxtLink>
                <div class="good-name">
                  <NuxtLink
                    :to="{
                      path: '/product/detail',
                      query: { id: good.good_id }
                    }"
                    >{{ good.name }}</NuxtLink
                  >
                  <div class="price">￥{{ good.price }} x {{ good.num }}</div>
                </div>
              </div>
            </div>
            <div class="total">
              <div>
                <div>￥{{ item.order.priceCount | thousands }}</div>
                <div class="freight">(含运费：￥{{ 0 | thousands }})</div>
              </div>
            </div>
            <div class="state">
              <div>
                <div>{{ item.order.status }}</div>
                <NuxtLink
                  :to="{ path: '/user/indent/detail', query: { id: item.order.orderId } }"
                  >订单详情</NuxtLink
                >
              </div>
            </div>
            <div class="operation">
              <div>
                <NuxtLink
                  :to="{ path: '/money/pay', query: { id: item.order.orderId } }"
                  v-if="item.order.status === '待付款'"
                  ><div class="button">
                    <el-button type="danger" size="mini" round
                      >立即付款</el-button
                    >
                  </div></NuxtLink
                >
                <div v-if="item.order.status === '已发货'" class="button">
                  <el-button
                    :loading="buttonLoading"
                    type="danger"
                    size="mini"
                    round
                    @click="confirmReceipt(item)"
                    >确认收货</el-button
                  >
                </div>
                <div v-if="item.order.status === '待发货'" class="button">
                  <el-button
                    :loading="buttonLoading"
                    size="mini"
                    round
                    @click="cancelOrder(item)"
                    >取消订单</el-button
                  >
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
      <div class="operation">
        <pagination
          v-if="total > 0"
          :total="total"
          :page.sync="listQuery.page"
          :limit.sync="listQuery.limit"
          class="pagination"
          @pagination="getList"
        />
      </div>
    </div>
  </div>
</template>

<style lang="scss" scoped>
@import "./scss/list";
</style>

<script>
import js from "./js/list";
export default js;
</script>
