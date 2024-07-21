<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 0px;">
      <el-form :inline="true" :model="filters">
        <el-form-item>
          <el-input v-model="filters.name" placeholder="姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getUsersByName">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd">新增</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="users" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
              style="width: 100%;">
      <!-- <el-table-column type="selection" width="55">
      </el-table-column> -->
      <el-table-column type="index" prop="userId" label="ID" width="60" align="center">
      </el-table-column>
      <el-table-column prop="username" label="用户名" width="120" sortable>
      </el-table-column>
      <el-table-column prop="email" label="邮箱" width="100" sortable>
      </el-table-column>
      <el-table-column prop="age" label="年龄" width="80" sortable>
      </el-table-column>
      <el-table-column prop="avatar" label="头像地址" width="120" sortable>
      </el-table-column>
      <el-table-column prop="country" label="国籍" min-width="180" sortable width="100">
      </el-table-column>
      <el-table-column prop="work" label="职业" min-width="180" sortable width="100">
      </el-table-column>
      <el-table-column prop="created" label="账号创建时间" min-width="180" sortable>
      </el-table-column>
      <el-table-column prop="lastLogin" label="上次登录时间" min-width="180" sortable>
      </el-table-column>
      <el-table-column label="操作" width="170">
        <template scope="scope">
          <el-button type="danger" size="small" @click="handleEditPwd(scope.$index, scope.row)">修改密码</el-button>
          <el-button type="primary" size="small" @click="handleEdit(scope.$index, scope.row)">编辑</el-button>
          <!-- <el-button type="danger" size="small" @click="handleDel(scope.$index, scope.row)">删除</el-button> -->
        </template>
      </el-table-column>
    </el-table>

    <!--工具条-->
    <el-col :span="24" class="toolbar">
      <!-- <el-button type="danger" @click="batchRemove" :disabled="this.sels.length===0">批量删除</el-button> -->
      <el-pagination layout="prev, pager, next" @current-change="handleCurrentChange" :page-size="20" :total="total"
                     style="float:right;">
      </el-pagination>
    </el-col>

    <!--编辑界面-->
    <el-dialog title="编辑" :visible.sync="editFormVisible" :close-on-click-modal="false">
      <el-form :model="editForm" label-width="80px" :rules="editFormRules" ref="editForm">
        <el-form-item label="ID" prop="userId" class="inputDeep">
          <el-input v-model="editForm.userId" auto-complete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="username">
          <el-input v-model="editForm.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="editForm.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number v-model="editForm.age" :min="0" :max="200"></el-input-number>
        </el-form-item>
        <el-form-item label="头像地址" prop="avatar">
          <el-input v-model="editForm.avatar" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="国籍" prop="country">
          <el-input v-model="editForm.country" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="职业" prop="work">
          <el-input v-model="editForm.work" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editSubmit" :loading="editLoading">提交</el-button>
      </div>
    </el-dialog>

    <!--修改密码-->
    <el-dialog title="修改密码" :visible.sync="editPwdFormVisible" :close-on-click-modal="false">
      <el-form :model="editPwdForm" label-width="140px" :rules="editPwdFormRules" ref="editPwdForm">
        <el-form-item label="ID" prop="userId" class="inputDeep" label-align="left">
          <el-input v-model="editPwdForm.userId" auto-complete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="用户名" prop="username" class="inputDeep">
          <el-input v-model="editPwdForm.username" auto-complete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="请输入新密码" prop="newPwd">
          <el-input type="password" v-model="editPwdForm.newPwd" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="请再次输入新密码" prop="reNewPwd">
          <el-input type="password" v-model="editPwdForm.reNewPwd" auto-complete="off"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click.native="editPwdFormVisible = false">取消</el-button>
        <el-button type="primary" @click.native="editPwdSubmit" :loading="editPwdLoading">提交</el-button>
      </div>
    </el-dialog>
    <!--新增界面-->
    <el-dialog title="新增" :visible.sync="addFormVisible" :close-on-click-modal="false">
      <el-form :model="addForm" label-width="140px" :rules="addFormRules" ref="addForm">
        <el-form-item label="用户名" prop="username">
          <el-input v-model="addForm.username" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="邮箱" prop="email">
          <el-input v-model="addForm.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="年龄">
          <el-input-number v-model="addForm.age" :min="0" :max="200"></el-input-number>
        </el-form-item>
        <el-form-item label="头像地址" prop="avatar">
          <el-input v-model="addForm.avatar" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="国籍" prop="country">
          <el-input v-model="addForm.country" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="职业" prop="work">
          <el-input v-model="addForm.work" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="请输入密码" prop="newOnePwd">
          <el-input type="password" v-model="addForm.newOnePwd" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="请再次输入密码" prop="reNewOnePwd">
          <el-input type="password" v-model="addForm.reNewOnePwd" auto-complete="off"></el-input>
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
import util from '../../common/js/util'
import { getUserListPage, removeUser, batchRemoveUser, editUser, addUser, getUsersListByName } from '../../api/api'

export default {
  data () {
    return {
      filters: {
        name: ''
      },
      pageSize: 20,
      users: [],
      total: 0,
      page: 1,
      listLoading: false,
      auth: null,
      sels: [], // 列表选中列
      editFormVisible: false, // 编辑界面是否显示
      editLoading: false,
      editFormRules: {
        userId: [
          { required: true, message: '请输入ID', trigger: 'blur' }
        ]
      },
      // 编辑界面数据
      editForm: {
        userId: 0,
        username: '',
        email: '',
        age: 0,
        avatar: '',
        country: '',
        work: ''
      },
      editPwdFormVisible: false,
      editPwdLoading: false,
      editPwdFormRules: {
        newPwd: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        reNewPwd: [
          { required: true, validator: this.validatePass2, trigger: 'blur' }
        ]
      },
      editPwdForm: {
        userId: 0,
        username: '',
        newPwd: '',
        reNewPwd: ''
      },
      addFormVisible: false, // 新增界面是否显示
      addLoading: false,
      addFormRules: {
        username: [
          { required: true, message: '请输入姓名', trigger: 'blur' }
        ],
        newOnePwd: [
          { required: true, message: '请输入新密码', trigger: 'blur' }
        ],
        reNewOnePwd: [
          { required: true, validator: this.validatePass1, trigger: 'blur' }
        ]
      },
      // 新增界面数据
      addForm: {
        username: '',
        email: '',
        age: 0,
        avatar: '',
        country: '',
        work: '',
        newOnePwd: '',
        reNewOnePwd: ''
      }

    }
  },
  methods: {
    validatePass1 (rule, value, callback) {
      if (value !== this.addForm.newOnePwd) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    },
    validatePass2 (rule, value, callback) {
      if (value !== this.editPwdForm.newPwd) {
        callback(new Error('两次输入的密码不一致'))
      } else {
        callback()
      }
    },
    // 性别显示转换
    // formatSex: function (row, column) {
    //   return row.sex === 1 ? '男' : row.sex === 0 ? '女' : '未知'
    // },
    handleCurrentChange (val) {
      this.page = val
      this.getUsers()
    },
    // 获取用户列表
    getUsers () {
      let para = {
        pageSize: this.pageSize,
        pageNum: this.page
      }
      this.listLoading = true
      getUserListPage(para, this.auth).then((res) => {
        this.total = res.data.data.length
        this.users = res.data.data
        this.listLoading = false
      })
    },
    getUsersByName () {
      let para = {
        pageSize: this.pageSize,
        pageNum: this.page,
        param: {
          name: this.filters.name
        }
      }
      console.log(para)
      this.listLoading = true
      getUsersListByName(para, this.auth).then((res) => {
        this.total = res.data.data.length
        this.users = res.data.data
        this.listLoading = false
      })
    },
    // 删除
    handleDel: function (index, row) {
      this.$confirm('确认删除该记录吗?', '提示', {
        type: 'warning'
      }).then(() => {
        this.listLoading = true
        let para = { id: row.id }
        removeUser(para).then((res) => {
          this.listLoading = false
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.getUsers()
        })
      }).catch(() => {

      })
    },
    // 显示编辑界面
    handleEdit: function (index, row) {
      this.editFormVisible = true
      this.editForm = Object.assign({}, row)
    },
    handleEditPwd: function (index, row) {
      this.editPwdFormVisible = true
      this.editPwdForm = Object.assign({}, row)
    },
    // 显示新增界面
    handleAdd: function () {
      this.addFormVisible = true
      this.addForm = {
        name: '',
        sex: -1,
        age: 0,
        birth: '',
        addr: ''
      }
    },
    // 编辑
    editSubmit: function () {
      this.$refs.editForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.editLoading = true
            let para = Object.assign({}, this.editForm)
            editUser(para, this.auth).then((res) => {
              this.editLoading = false
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.$refs['editForm'].resetFields()
              this.editFormVisible = false
              this.getUsers()
            })
          })
        }
      })
    },
    editPwdSubmit: function () {
      this.$refs.editPwdForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.editPwdLoading = true
            let para = Object.assign({}, this.editPwdForm)
            para.password = this.editPwdForm.newPwd
            console.log(para)
            editUser(para, this.auth).then((res) => {
              console.log(res)
              this.editPwdLoading = false
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.$refs['editPwdForm'].resetFields()
              this.editPwdFormVisible = false
              this.getUsers()
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
            para.password = this.addForm.newOnePwd
            para.birth = (!para.birth || para.birth === '') ? '' : util.formatDate.format(new Date(para.birth), 'yyyy-MM-dd')
            addUser(para).then((res) => {
              this.addLoading = false
              // NProgress.done();
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.$refs['addForm'].resetFields()
              this.addFormVisible = false
              this.getUsers()
            })
          })
        }
      })
    },
    selsChange: function (sels) {
      this.sels = sels
    },
    // 批量删除
    batchRemove: function () {
      var ids = this.sels.map(item => item.id).toString()
      this.$confirm('确认删除选中记录吗？', '提示', {
        type: 'warning'
      }).then(() => {
        this.listLoading = true
        let para = { ids: ids }
        batchRemoveUser(para).then((res) => {
          this.listLoading = false
          this.$message({
            message: '删除成功',
            type: 'success'
          })
          this.getUsers()
        })
      }).catch(() => {

      })
    }
  },
  mounted () {
    this.auth = sessionStorage.getItem('auth').replace('"', '').replace('"', '')
    this.getUsers()
  }
}

</script>

<style scoped>
.inputDeep>>>.el-input__inner{
    border:0;
}
</style>
