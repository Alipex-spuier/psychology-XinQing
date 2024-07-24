<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 10px; padding-top: 15px;">
      <el-form :inline="true" :model="filters" :rules="filtersRules" ref="filters">
        <el-form-item prop="name">
          <el-input v-model="filters.name" placeholder="专家名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getAppointmentByExpertId">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getAppointments">刷新</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="appointments" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
              style="width: 100%;">
      <!-- <el-table-column type="selection" width="55">
      </el-table-column> -->
      <el-table-column prop="aptId" label="ID" width="210" align="center">
      </el-table-column>
      <el-table-column prop="userId" label="用户id" width="280" sortable>
      </el-table-column>
      <el-table-column prop="expertId" label="专家id" width="280" sortable>
      </el-table-column>
      <el-table-column prop="aptTime" label="预约时间" width="300" sortable>
      </el-table-column>

      <el-table-column label="操作" width="170">
        <template scope="scope">
          <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)">编辑预约</el-button>
          <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button>
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!-- <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button> -->
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="pageSize" :total="total"
                     style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <el-form-item label="ID" prop="aptId" class="inputDeep">
          <el-input v-model="editForm.aptId" auto-complete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="editForm.userId" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家id" prop="expertId">
          <el-input v-model="editForm.expertId" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="预约时间" prop="aptTime">
            <el-col :span="11">
              <el-date-picker type="datetime" placeholder="选择日期" v-model="addForm.aptTime" style="width: 100%;" auto-complete="off"></el-date-picker>
            </el-col>
          </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>

    <!--新增界面-->
    <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="140px" :rules="addFormRules" ref="addForm">
        <!-- <el-form-item label="预约id" prop="aptId">
          <el-input v-model="addForm.userId" auto-complete="off"></el-input>
        </el-form-item> -->
        <el-form-item label="用户id" prop="userId">
          <el-input v-model="addForm.userId" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家id" prop="expertId">
          <el-input v-model="addForm.expertId" auto-complete="off"></el-input>
        </el-form-item>
      <el-form-item label="预约时间" prop="aptTime">
          <el-col :span="11">
            <el-date-picker type="datetime" placeholder="选择日期" v-model="addForm.aptTime" style="width: 100%;" auto-complete="off"></el-date-picker>
          </el-col>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="addFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="addSubmit" :loading="addLoading">提交</el-button>
      </div>
    </el-dialog>
  </section>
</template>

<script>
import { getAppointmentListPage, getAppointmentListByExpertId, removeAppointment, addAppointment, editAppointment } from '../../api/api'
import util from '../../common/js/util'

export default {
  data () {
    return {
      filtersRules: {
        name: [
          { required: true, message: '请输入查询的专家名', trigger: 'blur' }
        ]
      },
      filters: {
        name: null
      },
      pageSize: 10,
      appointments: [],
      total: 0,
      page: 1,
      listLoading: false,
      auth: null,
      sels: [], // 列表选中列

      editFormVisible: false, // 编辑界面是否显示
      editLoading: false,
      editFormRules: {},
      // 编辑界面数据
      editForm: {
        aptId: 0,
        userId: 0,
        expertId: 0,
        aptTime: ''
      },

      addFormVisible: false, // 新增界面是否显示
      addLoading: false,
      addFormRules: {
        // aptId: [
        //   { required: true, message: '请输入姓名', trigger: 'blur' }
        // ],
        userId: [
          { required: true, message: '请输入用户Id', trigger: 'blur' }
        ],
        expertId: [
          { required: true, message: '请输入专家Id', trigger: 'blur' }
        ],
        aptTime: [
          { required: true, message: '请输入预约时间', trigger: 'blur' }
        ]
      },
      // 新增界面数据
      addForm: {
        aptId: 0,
        userId: 0,
        expertId: 0,
        aptTime: ''
      }

    }
  },
  methods: {

    handleCurrentChange (val) {
      this.page = val
      this.getAppointments()
    },
    // 获取用户列表
    getAppointments () {
      let _this = this
      let para = {
        pageSize: this.pageSize,
        pageNum: this.page
      }
      this.listLoading = true
      getAppointmentListPage(para, this.auth).then((res) => {
        _this.total = res.data.total
        _this.appointments = res.data.data
        for (let i = 0; i < _this.appointments.length; i++) {
          let date = new Date(_this.appointments[i].aptTime)
          _this.appointments[i].aptTime = (!_this.appointments[i].aptTime || _this.appointments[i].aptTime === '') ? '' : util.formatDate.format(date, 'yyyy-MM-dd hh:mm:ss')
        }
        _this.listLoading = false
      })
    },

    getAppointmentByExpertId () {
      this.$refs.filters.validate((valid) => {
        if (valid) {
          let _this = this
          let para = {
            pageSize: this.pageSize,
            pageNum: this.page,
            param: {
              expertId: (this.filters.name - 1) + 1
            }
          }
          this.listLoading = true
          // console.log(typeof(para.param.expertId))
          getAppointmentListByExpertId(para, this.auth).then((res) => {
            _this.total = res.data.total
            _this.appointments = res.data.data
            _this.listLoading = false
          })
        }
      })
    },
    // 删除
    handleDel: function (index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      }).then(() => {
        this.listLoading = true
        // let para = { id: row.id }
        // console.log(row)
        removeAppointment(this.auth, row.aptId).then((res) => {
          this.listLoading = false
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.getAppointments()
        })
      }).catch(() => {

      })
    },
    // 显示编辑界面
    handleEdit: function (index, row) {
      this.editFormVisible = true
      this.editForm = Object.assign({}, row)
    },
    // 显示新增界面
    handleAdd: function () {
      this.addFormVisible = true
      this.addForm = {
        // aptId:0,
        userId: null,
        expertId: null,
        aptTime: ''
      }
    },
    // 编辑
    editSubmit: function () {
      this.$refs.editForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.editLoading = true
            let para = Object.assign({}, this.editForm)
            // para.aptTime=para.aptTime.getTime()
            para.aptTime = (!para.aptTime || para.aptTime === '') ? '' : util.formatDate.format(new Date(para.aptTime), 'yyyy-MM-dd')
            editAppointment(para, this.auth).then((res) => {
              this.editLoading = false
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.$refs['editForm'].resetFields()
              this.editFormVisible = false
              this.getAppointments()
            })
          })
        }
      })
    },

    // 新增
    addSubmit: function () {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.addLoading = true
            // NProgress.start();
            let para = Object.assign({}, this.addForm)
            para.aptTime = para.aptTime.getTime()// 时间戳

            addAppointment(para, this.auth).then((res) => {
              if (res.data.code == 200) {
                this.addLoading = false
                // NProgress.done();
                this.$message({
                  message: '提交成功',
                  type: 'success'
                })
                this.$refs['addForm'].resetFields()
                this.addFormVisible = false
                this.getAppointments()
              } else {
                this.$message({
                  message: res.data.msg,
                  type: 'fail'
                })
                this.$refs['addForm'].resetFields()
                this.addFormVisible = false
                this.addLoading = false
                this.getAppointments()
              }
              //   this.addLoading = false
              //   // NProgress.done();
              //   this.$message({
              //     message: '提交成功',
              //     type: 'success'
              //   })
              //   this.$refs['addForm'].resetFields()
              //   this.addFormVisible = false
              //   this.getAppointments()
            })
          })
        }
      })
    },
    selsChange: function (sels) {
      this.sels = sels
    }

  },
  mounted () {
    this.auth = sessionStorage.getItem('auth').replace('"', '').replace('"', '')
    this.getAppointments()
  }
}

</script>

<style scoped>
.inputDeep>>>.el-input__inner{
    border:0;
}
</style>
