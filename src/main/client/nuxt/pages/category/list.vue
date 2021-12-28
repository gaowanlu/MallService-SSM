<template>
  <div class="box">
    <el-breadcrumb class="breadcrumb container" separator="/">
      <el-breadcrumb-item>
        <NuxtLink :to="{ path: '/' }">
          首页
        </NuxtLink>
      </el-breadcrumb-item>
      <el-breadcrumb-item>全部商品分类</el-breadcrumb-item>
    </el-breadcrumb>
    <div class="category-box">
      <div class="category-list container" v-for="(item, index) in goodCategory" :key="index">
        <NuxtLink class="title" :to="{ path: '/product/list', query: { name: item.name }}">
          {{item.name}}
        </NuxtLink>
      </div>
    </div>
  </div>
</template>

<style lang='scss' scoped>
  @import "./scss/list";
</style>

<script>
import js from './js/list'
export default js
</script>
