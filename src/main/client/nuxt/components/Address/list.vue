<template>
	<div>
    <div class="address-list" v-loading="loading">
      <div class="address-item" v-for="item in list" :key="item.addressId">
        <div class="item-on" :class="{on:item.on && select}" @click="switchAddress(item)">
          <div class="address-info">
            <div class="name">
              {{ item.name }}
              <span v-if="item.defaults">默认</span>
            </div>
            <div class="cellphone">{{ item.phone }}</div>
            <div class="address-con">{{ item.address }}</div>
            <div class="address-action">
              <el-link v-if="!select" type="danger" :underline="false" @click="defaultAddress(item)">设为默认</el-link>
              <el-link type="danger" :underline="false" @click="updateAddress(item)">修改</el-link>
              <el-link v-if="!select" type="danger" :underline="false" @click="deleteAddress(item)">删除</el-link>
            </div>
          </div>
        </div>
      </div>
      <div class="address-item" @click="updateAddress">
        <div class="item">
          <div class="add-desc">
            <div><i class="el-icon-circle-plus"></i></div>
            <div>添加新地址</div>
          </div>
        </div>
      </div>
    </div>
    <el-dialog
      :title="dialogTitle"
      :visible.sync="centerDialogVisible"
      :close-on-click-modal="false"
      width="600px">
      <el-form class="ruleForm" :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px">
        <el-form-item label="联系人" prop="name">
          <el-input v-model="ruleForm.name" clearable maxlength="20" placeholder="请输入姓名"></el-input>
        </el-form-item>
        <el-form-item label="手机号" prop="phone">
          <el-input v-model="ruleForm.phone" clearable maxlength="11" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="地址" prop="address">
          <el-input v-model="ruleForm.address" clearable placeholder="请输入地址"></el-input>
        </el-form-item>
      </el-form>
      <span slot="footer" class="dialog-footer">
        <el-button :loading="buttonLoading" @click="centerDialogVisible = false">取 消</el-button>
        <el-button :loading="buttonLoading" type="danger"  @click="submitForm('ruleForm')">确 定</el-button>
      </span>
    </el-dialog>
	</div>
</template>

<style lang='scss' scoped>
  @import "./scss/list";
</style>

<script>
import js from './js/list'
export default js
</script>
