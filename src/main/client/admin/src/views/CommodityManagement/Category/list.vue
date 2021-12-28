<template>
  <div class="app-container">
    <div class="filter-container">
      <el-button class="filter-item" style="margin-left: 10px;" type="primary" icon="el-icon-edit" @click="handleCreate()">{{ $t('usuel.add') }}</el-button>
    </div>
    <el-table
      v-loading="listLoading"
      :key="tableKey"
      :data="list"
      border
      fit
      highlight-current-row
      style="width: 100%;"
      @sort-change="sortChange">
      <el-table-column :label="$t('usuel.id')" align="center" width="65" sortable="custom" prop="id">
        <template slot-scope="scope">
          <span>{{ scope.row.goodTypeId }}</span>
        </template>
      </el-table-column>
      <el-table-column label="类目名称" align="center">
        <template slot-scope="scope">
          <span>{{ scope.row.name }}</span>
        </template>
      </el-table-column>
      <el-table-column :label="$t('table.actions')" align="center" class-name="small-padding fixed-width" width="220">
        <template slot-scope="scope">
          <el-tooltip content="编辑" placement="top-start">
            <el-button type="primary" icon="el-icon-edit" circle @click="handleUpdate(scope.row)"/>
          </el-tooltip>
          <el-tooltip content="删除" placement="top-start">
            <el-button :loading="formLoading" type="danger" icon="el-icon-delete" circle @click="handleDelete(scope.row)"/>
          </el-tooltip>
        </template>
      </el-table-column>
    </el-table>

    <!--分页-->
    <pagination v-show="total>0" :total="total" :page.sync="listQuery.page" :limit.sync="listQuery.limit" @pagination="getList" />

    <!--添加-->
    <el-dialog :title="textMap[dialogStatus]" :visible.sync="dialogFormVisible" :close-on-click-modal="false">
      <el-form ref="dataForm" :rules="adminRules" :model="temp" label-position="left" label-width="120px" style="width:90%;">
        <el-form-item label="类目名称" prop="name" style="width:400px;">
          <el-input v-model="temp.name" maxlength="30" clearable/>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button :loading="formLoading" @click="dialogFormVisible = false">{{ $t('usuel.cancel') }}</el-button>
        <el-button :loading="formLoading" type="primary" @click="dialogStatus==='create'?createData():updateData()">{{ $t('usuel.confirm') }}</el-button>
      </div>
    </el-dialog>
  </div>
</template>
<style rel="stylesheet/scss" lang="scss">
  .timeInterval{
    top:-4px;
  }
  .avatar-uploader .el-upload {
    border: 1px dashed #d9d9d9;
    border-radius: 6px;
    cursor: pointer;
    position: relative;
    overflow: hidden;
  }
  .avatar-uploader .el-upload:hover {
    border-color: #409EFF;
  }
  .avatar-uploader-icon {
    font-size: 28px;
    color: #8c939d;
    width: 188px;
    height: 188px;
    line-height: 188px;
    text-align: center;
  }
  .progress-img{
    padding: 30px;
  }
  .avatar {
    width: 188px;
    height: 188px;
    display: block;
  }
  .el-dialog{
    width:65% !important;
  }
</style>

<script>
import { getList, create, edit, destroy } from '@/api/category'
import waves from '@/directive/waves'
import Pagination from '@/components/Pagination'
import { getToken } from '@/utils/auth'

export default {
  name: 'CategoryList',
  components: { Pagination },
  directives: { waves },
  data() {
    return {
      formLoading: false,
      data: [],
      dataBrand: [],
      value: [],
      filterMethod(query, item) {
        return item.label.indexOf(query) > -1
      },
      tableKey: 0,
      options: [],
      list: null,
      total: 0,
      textMap: {
        update: this.$t('usuel.amend'),
        create: this.$t('usuel.add')
      },
      imgData: {
        type: 1,
        size: 1024 * 500,
        specification: [80, 300]
      },
      actionurl: process.env.BASE_API + 'uploadPictures',
      imgHeaders: {
        Authorization: getToken('token_type') + ' ' + getToken('access_token')
      },
      imgProgressPercent: 0,
      listLoading: true,
      listQuery: {
        page: 1,
        limit: 10,
        sort: '',
        pid: []
      },
      imgProgress: false,
      temp: {
        name: ''
      },
      dialogFormVisible: false,
      dialogStatus: '',
      adminRules: {
        name: [
          { required: true, message: '类目名称必须填写', trigger: 'blur' }
        ]
      },
      downloadLoading: false
    }
  },
  created() {
    this.getList()
  },
  methods: {
    getList() {
      this.listLoading = true
      getList().then(response => {
        this.list = response.data.types
        this.listLoading = false
      })
    },
    handleFilter() {
      this.listQuery.page = 1
      if (this.listQuery.timeInterval) {
        this.listQuery.timeInterval = this.listQuery.timeInterval.join('至')
      }
      this.getList()
    },
    sortChange(data) {
      const { prop, order } = data
      if (order === 'ascending') {
        this.listQuery.sort = '+' + prop
      } else {
        this.listQuery.sort = '-' + prop
      }
      this.handleFilter()
    },
    resetTemp() {
      this.temp = {
        title: '',
        api: '',
        state: 0,
        pid: [],
        attribute: [],
        brand: [],
        sort: 5,
        is_recommend: 0
      }
    },
    handleCreate(row = null) {
      if (!row) {
        this.resetTemp()
      } else {
        this.temp = Object.assign({}, row)
      }
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleSonCreate(row = null) {
      this.resetTemp()
      this.temp.pid = row.id
      this.dialogStatus = 'create'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    createData() { // 添加
      this.formLoading = true
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          if (this.temp.pid.length > 0) {
            this.temp.pid = this.temp.pid.pop()
          }
          create(this.temp).then(() => {
            this.getList()
            this.dialogFormVisible = false
            this.formLoading = false
            this.$notify({
              title: this.$t('hint.succeed'),
              message: this.$t('hint.creatingSuccessful'),
              type: 'success',
              duration: 2000
            })
          }).catch(() => {
            this.formLoading = false
          })
        }
      })
    },
    updateData() { // 更新
      this.formLoading = true
      this.$refs['dataForm'].validate((valid) => {
        if (valid) {
          edit(this.temp).then(() => {
            this.getList()
            this.dialogFormVisible = false
            this.formLoading = false
            this.$notify({
              title: this.$t('hint.succeed'),
              message: this.$t('hint.updateSuccessful'),
              type: 'success',
              duration: 2000
            })
          }).catch(() => {
            this.formLoading = false
          })
        }
      })
    },
    handleUpdate(row) { // 修改
      this.temp = row
      if (row.pid === 0) {
        row.pid = [0]
      }
      if (row.resources) {
        this.temp.logo = row.resources.img
      }
      this.dialogStatus = 'update'
      this.dialogFormVisible = true
      this.$nextTick(() => {
        this.$refs['dataForm'].clearValidate()
      })
    },
    handleDelete(row) { // 删除
      this.$confirm(this.$t('hint.deleteDetermine'), this.$t('hint.hint'), {
        confirmButtonText: this.$t('usuel.confirm'),
        cancelButtonText: this.$t('usuel.cancel'),
        type: 'warning'
      }).then(() => {
        this.formLoading = true
        destroy(row.id).then(() => {
          this.getList()
          this.dialogFormVisible = false
          this.formLoading = false
          this.$notify({
            title: this.$t('hint.succeed'),
            message: this.$t('hint.deletedSuccessful'),
            type: 'success',
            duration: 2000
          })
        }).catch(() => {
          this.formLoading = false
        })
      }).catch(() => {
      })
    },
    // 上传成功
    handleAvatarSuccess(res, file) {
      this.temp.logo = file.response
      this.imgProgress = false
      this.imgProgressPercent = 0
    },
    // 上传时
    handleProgress(file, fileList) {
      this.imgProgressPercent = file.percent
    },
    // 图片格式大小验证
    beforeAvatarUpload(file) {
      const isLt2M = file.size / 1024 < 500

      if (
        ['image/jpeg',
          'image/gif',
          'image/png',
          'image/bmp'
        ].indexOf(file.type) === -1) {
        this.$message.error('请上传正确的图片格式')
        return false
      }
      if (!isLt2M) {
        this.$message.error('上传头像图片大小不能超过 500KB!')
      }
      this.imgProgress = true
      return isLt2M
    }
  }
}
</script>
