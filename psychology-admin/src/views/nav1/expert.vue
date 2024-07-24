<template>
  <section>
    <!--工具条-->
    <el-col :span="24" class="toolbar" style="padding-bottom: 10px; padding-top: 15px;">
      <el-form :inline="true" :model="filters" :rules="filtersRules" ref="filters">
        <el-form-item prop="name">
          <el-input v-model="filters.name" placeholder="专家姓名"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getExpertsByName">查询</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" v-on:click="getExperts">刷新</el-button>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="handleAdd">新增</el-button>
        </el-form-item>
       <el-form-item>
          <el-button type="primary" v-on:click="getExpertsByChecked">批量审核</el-button>
        </el-form-item>
      </el-form>
    </el-col>

    <!--列表-->
    <el-table :data="experts" highlight-current-row v-loading="listLoading" @selection-change="selsChange"
              style="width: 100%;">
      <!-- <el-table-column type="selection" width="55">
      </el-table-column> -->
      <el-table-column type="index" prop="exId" label="ID" width=60 align="center">
      </el-table-column>
      <el-table-column prop="exName" label="专家名" width=100 sortable>
      </el-table-column>
      <el-table-column prop="avatar" label="头像地址" width=130 >
      </el-table-column>
      <el-table-column prop="exBio" label="专家简介" width=180 >
      </el-table-column>
      <el-table-column prop="exDire" label="研究方向" width=80 >
      </el-table-column>
      <el-table-column prop="exQualification" label="职称" width=120>
      </el-table-column>
      <el-table-column prop="exEmail" label="邮箱" width=120>
      </el-table-column>
      <el-table-column prop="createdTime" label="账号创建时间" width=130 sortable>
      </el-table-column>
      <el-table-column prop="checked" label="审核状态" width=220 >
        <template v-slot="scope" >
          <el-switch
            v-model="scope.row.checked"
            class="mb-2"
            inline-prompt
            style="--el-switch-on-color: #13ce66; --el-switch-off-color: #ff4949"
            active-text="审核通过"
            inactive-text="待审核"
            @change="handleChange(scope.$index, scope.row)"
          />
        </template>
      </el-table-column>
      <el-table-column label="操作" width="90">
        <template scope="scope">
<!--          <el-button type="danger" size="small" @click="handleEditPwd(scope.$index, scope.row)">修改认证</el-button> -->
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
        <el-form-item label="专家ID" prop="exId" class="inputDeep">
          <el-input v-model="editForm.exId" auto-complete="off" :readonly="true"></el-input>
        </el-form-item>
        <el-form-item label="专家名" prop="exName">
          <el-input v-model="editForm.exName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家头像" prop="avatar">
          <el-input v-model="editForm.avatar" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家简介" prop="exBio">
          <el-input v-model="editForm.exBio" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="研究方向" prop="exDire">
          <el-input v-model="editForm.exDire" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家职称" prop="exQualification">
          <el-input v-model="editForm.exQualification" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家邮箱" prop="exEmail">
          <el-input v-model="editForm.exEmail" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="审核状态" prop="checked">
          <el-switch
            v-model="editForm.checked"
            class="ml-2"
            width=40
            inline-prompt
            active-text="审核通过"
            inactive-text="待审核"
          />
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
        <el-form-item label="专家名" prop="exName">
          <el-input v-model="addForm.exName" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家头像" prop="avatar">
          <el-input v-model="addForm.avatar" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家简介" prop="exBio">
          <el-input v-model="addForm.exBio" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="研究方向" prop="exDire">
          <el-input v-model="addForm.exDire" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家职称" prop="exQualification">
          <el-input v-model="addForm.exQualification" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="专家邮箱" prop="exEmail">
          <el-input v-model="addForm.email" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="请输入密码" prop="newOnePwd">
          <el-input type="password" v-model="addForm.newOnePwd" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="请再次输入密码" prop="reNewOnePwd">
          <el-input type="password" v-model="addForm.reNewOnePwd" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="审核状态" prop="checked">
          <el-switch
            v-model="addForm.checked"
            class="ml-2"
            width=40
            inline-prompt
            active-text="1"
            inactive-text="2"
          />
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
import { addExpert, getExpertsListByName, getExpertsListPage, editExperts, getExpertsListByChecked_0 } from '../../api/api'
// 审核通过checked为1，“通过”，未通过为“待审”

export default {
  data () {
    return {
      filters: {
        name: ''
      },
      filtersRules: {
        name: [
          { required: true, message: '请输入查询的专家名', trigger: 'blur' }
        ]
      },

      pageSize: 20,
      experts: [],
      total: 0,
      page: 1,
      listLoading: false,
      auth: null,
      sels: [], // 列表选中列
      editFormVisible: false, // 编辑界面是否显示
      editLoading: false,
      editFormRules: {
      //   userId: [
      //     { required: true, message: '请输入ID', trigger: 'blur' }
      //   ]
      },

      // 编辑界面数据
      editForm: {
        exId: 0,
        exName: '',
        avatar: '',
        exBio: '',
        exDire: '',
        exQualification: '',
        exEmail: '',
        checked: false
      },

      addFormVisible: false, // 新增界面是否显示
      addLoading: false,
      addFormRules: {
        exName: [
          { required: true, message: '请输入专家姓名', trigger: 'blur' }
        ],
        newOnePwd: [
          { required: true, message: '请输入密码', trigger: 'blur' }
        ],
        reNewOnePwd: [
          { required: true, validator: this.validatePass1, trigger: 'blur' }
        ]
      },

      // 新增界面数据
      addForm: {
        exId: 0,
        exName: '',
        avatar: '',
        exBio: '',
        exDire: '',
        exQualification: '',
        exEmail: '',
        newOnePwd: '',
        reNewOnePwd: '',
        checked: false
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
    handleCurrentChange (val) {
      this.page = val
      this.getExperts()
    },
    getExpertsByChecked () {
      let para = {
        pageSize: this.pageSize,
        pageNum: this.page
      }
      this.listLoading = true
      getExpertsListByChecked_0(para, this.auth).then((res) => {
        console.log(res)
        this.total = res.data.data.length
        this.experts = res.data.data
        this.listLoading = false
      })
    },
    // 获取用户列表
    getExperts () {
      let para = {
        pageSize: this.pageSize,
        pageNum: this.page
      }
      this.listLoading = true
      getExpertsListPage(para, this.auth).then((res) => {
        this.total = res.data.data.length
        this.experts = res.data.data
        // console.log(this.experts)
        this.setCheckedBoolean(this.experts)
        console.log(this.experts)
        this.listLoading = false
      })
    },
    setCheckedBoolean (experts) {
      for (let i = 0; i < experts.length; i++) {
        if (experts[i].checked == 0) {
          experts[i].checked = false
        } else {
          experts[i].checked = true
        }
      }
    },
    getExpertsByName () {
      this.$refs.filters.validate((valid) => {
        if (valid) {
          let para = {
            pageSize: this.pageSize,
            pageNum: this.page,
            param: {
              name: this.filters.name
            }
          }
          this.listLoading = true
          getExpertsListByName(para, this.auth).then((res) => {
            this.total = res.data.data.length
            this.experts = res.data.data
            this.listLoading = false
          })
        }
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
    handleChange: function (index, row) {
      this.editForm = Object.assign({}, row)
      this.$confirm('确认审核信息无误吗？', '提示', {}).then(() => {
        let para = Object.assign({}, this.editForm)
        this.setCheckedInt(para)
        editExperts(para, this.auth).then((res) => {
          this.$message({
            message: '审核完成',
            type: 'success'
          })
          this.$refs['editForm'].resetFields()
          this.getExperts()
        })
      })
    },
    // 显示新增界面
    handleAdd: function () {
      this.addFormVisible = true
      this.addForm = {
        exId: 0,
        exName: '',
        avatar: '',
        exBio: '',
        exDire: '',
        exQualification: '',
        exEmail: '',
        newOnePwd: '',
        reNewOnePwd: '',
        checked: false
      }
    },
    // 编辑
    editSubmit: function () {
      this.$refs.editForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.editLoading = true
            let para = Object.assign({}, this.editForm)
            this.setCheckedInt(para)
            editExperts(para, this.auth).then((res) => {
              this.editLoading = false
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.$refs['editForm'].resetFields()
              this.editFormVisible = false
              this.getExperts()
            })
          })
        }
      })
    },
    setCheckedInt (para) {
      if (para.checked == true) {
        para.checked = 1
      } else if (para.checked == false) {
        para.checked = 0
      }
    },
    // 新增
    addSubmit: function () {
      this.$refs.addForm.validate((valid) => {
        if (valid) {
          this.$confirm('确认提交吗？', '提示', {}).then(() => {
            this.addLoading = true
            // NProgress.start();
            let para = Object.assign({}, this.addForm)
            para.exPassword = this.addForm.newOnePwd
            // this.setCheckInt(para)
            para.checked = 1
            delete para.exId
            delete para.newOnePwd
            delete para.reNewOnePwd
            addExpert(para, this.auth).then((res) => {
              this.addLoading = false
              // NProgress.done();
              this.$message({
                message: '提交成功',
                type: 'success'
              })
              this.$refs['addForm'].resetFields()
              this.addFormVisible = false
              this.getExperts()
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
    this.getExperts()
  }
}

</script>

<style scoped>
.inputDeep>>>.el-input__inner{
    border:0;
}
</style>
