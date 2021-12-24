<template>
  <div class="box">
    <div class="container product-detail">
      <el-breadcrumb separator="/" class="breadcrumb">
        <el-breadcrumb-item>
          <NuxtLink :to="{ path: '/' }">
            首页
          </NuxtLink>
        </el-breadcrumb-item>
        <el-breadcrumb-item>{{ goodDetail.name }}</el-breadcrumb-item>
      </el-breadcrumb>
      <div class="product-box">
        <div class="picture">
          <el-carousel
            :autoplay="false"
            arrow="always"
            height="450px"
            indicator-position="outside"
          >
            <el-carousel-item
              v-for="(img, index) in resources_many_img"
              :key="index"
            >
              <template>
                <el-image
                  class="image"
                  fit="scale-down"
                  :src="img"
                  :preview-src-list="resources_many_img"
                ></el-image>
              </template>
            </el-carousel-item>
          </el-carousel>
        </div>
        <div class="parameter">
          <div class="title">{{ goodDetail.name }}</div>
          <div v-if="goodDetail.profile" class="description">
            {{ goodDetail.profile }}
          </div>
          <div class="price-box">
            <!-- 已选择规则-->
            <div class="price">
              <span class="symbol">¥</span>{{ goodDetail.price | thousands }}
            </div>
            <!-- 未选择规则-->
          </div>
          <el-divider></el-divider>
          <div class="purchase-quantity">
            <div class="name">购买数量</div>
            <div class="quantity">
              <el-input-number
                :min="1"
                :max="goodDetail.stock"
                v-model="cartGood.number"
              ></el-input-number>
            </div>
            <div class="inventory">
              件 (库存：{{ goodDetail.stock }}件)
            </div>
          </div>
          <el-divider></el-divider>
          <div class="shipping-address"></div>
          <div class="operation">
            <el-button type="danger" plain @click="buy"
              >立即购买</el-button
            >
            <el-button type="danger" @click="addCart">加入购物车</el-button>
            <el-button
              type="info"
              :class="{ 'product-detail-on': collect }"
              icon="el-icon-star-off"
              @click="toCollect"
              >收藏</el-button
            >
          </div>
        </div>
      </div>
    </div>
    <el-divider></el-divider>
    <!-- 详情-->
    <div class="product-box">
      <div class="tab">
        <span :class="{ on: tab === 1 }" @click="cutTab(1)">商品详情</span>
        <el-divider direction="vertical"></el-divider>
        <span :class="{ on: tab === 2 }" @click="cutTab(2)">商品规格</span>
      </div>
      <!-- <div class="detail-box">
        <div class="container" v-loading="tabLoading">
          <div v-if="tab === 1" v-html="goodDetail.details"></div>
          <div v-else-if="tab === 2"></div>
        </div>
      </div> -->
    </div>
  </div>
</template>
<style lang="scss">
.product-detail-on .el-icon-star-off {
  color: #fa524c;
}
</style>

<style lang="scss" scoped>
@import "./scss/detail";
</style>

<script>
import js from "./js/detail";
export default js;
</script>
