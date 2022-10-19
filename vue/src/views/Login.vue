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
import {ref, reactive, getCurrentInstance, onMounted, onBeforeUnmount, onUnmounted} from 'vue'
import request from "@/utils/request";
import router from '@/router';
import {store} from "@/vuex/store";
import socket from "@/utils/socket";
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
          sessionStorage.setItem("user",JSON.stringify(res.data))//存储session
          store.state.userInfo = res.data
          let resData=res.data
          socket.cardId = resData.cardId
          socket.init(receiveMessage)
          switch (resData.permission.valueOf()){
            case 0://管理员
              router.push("/admin")
              ElMessage.success("管理员登录"+res.msg)
              break
            case 1://教师
              ElMessage.success("教师登录"+res.msg)
              router.push("/teacher")
              break
            case 2://学生
              ElMessage.success("学生登录"+res.msg)
              router.push("/student")
              break
          }
        }else {
          ElMessage.error("错误码"+res.code+":"+res.msg)
        }
      })
    }
  })
}

const receiveMessage=(message: any)=> {
  let msg = message.data
  console.log(msg)
}

onUnmounted(()=>{

})
</script>


<style scoped lang="scss">
</style>



