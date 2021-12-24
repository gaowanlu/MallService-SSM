<template>
  <NuxtLink
    class="li"
    :to="{ path: '/product/detail', query: { id: good.goodId } }"
  >
    <el-card class="card" shadow="hover">
      <el-image class="image" :src="good.imgsURL[0]" fit="cover" lazy />
      <div class="name">{{ good.name }}</div>
      <div class="price">
        <div class="symbol">¥</div>
        <div class="value">{{ good.price | thousands }}</div>
      </div>
    </el-card>
  </NuxtLink>
</template>

<script>
export default {
  props: {
    good: {
      type: Object,
      required: true
    }
  }
};
</script>

<style lang="scss" scoped>
.li{
  cursor:pointer;
  width: 20%;
  .card{
    margin: 0 10px 10px 0;
    .image{
      width: 100%;
      height: 190px;
    }
    .name{
      font-size: 14px;
      overflow: hidden;
      text-overflow:ellipsis;
      white-space: nowrap;
    }
    .price{
      display: flex;
      justify-content: center;
      margin-bottom: 10px;
      .symbol{
        font-size: 12px;
        line-height: 40px;
        color: $font-color-main;
      }
      .value{
        color: $font-color-main;
        line-height: 35px;
      }
    }
  }
  .card:hover{
    transform: translate(0,-5px);
  }
}
</style>
