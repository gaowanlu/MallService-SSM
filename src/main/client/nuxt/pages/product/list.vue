<template>
  <div class="box">
    <el-breadcrumb class="breadcrumb container" separator="/">
      <el-breadcrumb-item>
        <NuxtLink :to="{ path: '/' }">
          首页
        </NuxtLink>
      </el-breadcrumb-item>
      <el-breadcrumb-item v-if="listQuery.name">商品分类</el-breadcrumb-item>
      <el-breadcrumb-item v-else>搜索结果</el-breadcrumb-item>
      <el-breadcrumb-item>{{ title }}</el-breadcrumb-item>
    </el-breadcrumb>
    <div v-if="total">
      <div class="product-list container" v-loading="loading">
        <div class="list">
          <NuxtLink
            class="li"
            v-for="(item, index) in goodList"
            :key="index"
            :to="{ path: '/product/detail', query: { id: item.goodId } }"
          >
            <el-card class="card" shadow="hover">
              <el-image class="image" :src="item.imgsURL[0]" fit="cover" lazy />
              <div class="name">{{ item.name }}</div>
              <div class="price">
                <div class="symbol">¥</div>
                <div class="value">{{ item.price | thousands }}</div>
              </div>
            </el-card>
          </NuxtLink>
        </div>
      </div>
      <div class="operation container">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="listQuery.page"
          :page-sizes="[10, 20, 30, 40]"
          :page-size="listQuery.limit"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
        />
      </div>
    </div>
    <div class="no-goods" v-else>
      <img :src="require('assets/img/no-goods.png')" />
      <div v-if="listQuery.name">
        抱歉，“{{ title }}”分类下暂无商品，换个分类搜搜吧
      </div>
      <div v-else>抱歉，没有找到商品“{{ listQuery.keyword }}”，换个词搜搜吧</div>
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
