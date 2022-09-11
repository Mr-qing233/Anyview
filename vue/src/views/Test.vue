<template>
  <div>
    <div style="width: 400px; margin: 200px auto;">
      <h1 style="text-align: center; margin-bottom: 30px">登 录</h1>
      <h1>{{user.userName}}</h1>
      <h1>{{user.userId}}</h1>
      <el-form :model="user" :rules="rules" ref="ruleFormRef" size="large">
        <el-form-item prop="username">
          <el-input v-model="user.username" :prefix-icon="User" />
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="user.password" :prefix-icon="Lock" show-password  />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
      <el-button @click="logout">退出</el-button>

      <el-table stripe="true" :data="state.tableData" style="width: 100%">
        <el-table-column label="用户名" prop="userName" width="180" />
        <el-table-column label="邮箱" prop="userEmail" width="180" />
        <el-table-column label="账号状态" prop="userStatus" />
      </el-table>
    </div>
  </div>
</template>

<script lang="ts">
import {ElMessage} from 'element-plus';
import {ref, reactive, onMounted, toRefs} from 'vue'
import request from "@/utils/request";
import router from '@/router';

export default {
  name:"Test",
  setup(){
    let user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")||'0') : null;

    const rules = reactive({
      username: [
        { required: true, message: '请输入用户名', trigger: 'blur' }
      ],
      password: [
        { required: true, message: '请输入密码', trigger: 'blur' }
      ],
    })

    const logout=()=>{
      router.push("/login")
      localStorage.removeItem("user")
      ElMessage.success("退出成功")
    }


    const state =reactive<any>({
      tableData:[],
    });

    onMounted(()=>{
      request.get('/user-info').then(response=>{
        console.log(response);
        state.tableData = response;
      })
    })
    return {
      user,
      state,
      rules,
      logout,
    }
  }
}
</script>