<template>
  <div>
    <div style="width: 400px; margin: 200px auto;">
      <h1 style="text-align: center; margin-bottom: 30px">登 录</h1>
      <el-form :model="user" :rules="rules" ref="loginForm" size="large">
        <el-form-item prop="cardId">
          <el-input v-model="user.cardId" :prefix-icon="User" />
        </el-form-item>
        <el-form-item prop="pwd">
          <el-input v-model="user.pwd" :prefix-icon="Lock" show-password  />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" style="width: 100%" @click="login">登 录</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>


<script lang="ts" setup>
import {ElMessage} from 'element-plus';
import {ref, reactive, getCurrentInstance, onMounted} from 'vue'
import request from "@/utils/request";
import router from '@/router';
const rules = reactive({
  cardId: [
    { required: true, message: '请输入用户名', trigger: 'blur' }
  ],
  pwd: [
    { required: true, message: '请输入密码', trigger: 'blur' }
  ],
})

const user = reactive({
  cardId:"",
  pwd:"",
})

const loginForm =ref<any|null>(null)

const login =()=>{
  loginForm.value.validate((valid:any)=>{
    if(valid){
      request.post('/user/login',user).then((res: any)=>{
        if(res.code=='200'){
          localStorage.setItem("user",JSON.stringify(res.data))//存储用户信息到浏览器
          router.push("/Home")
          ElMessage.success("登录"+res.msg)
        }else {
          ElMessage.error("错误码"+res.code+":"+res.msg)
        }
      })
    }
  })
}
</script>


<style scoped lang="scss">
</style>



