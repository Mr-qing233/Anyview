<!-- 顶部信息栏 -->
<template>
  <header id="topBar">
    <el-row>
      <el-col :span="4" class="topBar-left">
        <i class="iconfont icon-kaoshi"></i>
        <span class="title" @click="index()">EXAM-SYSTEM</span>
      </el-col>
      <el-col :span="20" class="topBar-right">
        <el-icon class="el-icon-menu" @click="toggle()"><Menu /></el-icon>
        <div class="user">
          <span>{{state.user.teacherName}}</span>
          <img src="" class="user-img" ref="img" @click="showSetting()" />
          <transition name="fade">
            <div class="out" ref="out" v-show="login_flag">
              <ul>
                <li><a href="">用户信息</a></li>
                <li><a href="">设置</a></li>
                <li class="exit" @click="exit()"><a>退出登录</a></li>
              </ul>
            </div>
          </transition>
        </div>
      </el-col>
    </el-row>
  </header>
</template>

<script lang="ts" setup>
import {mapState, mapMutations, useStore} from 'vuex'
import {computed, onMounted, reactive, ref, toRefs} from "vue";
import router from "@/router";
import {ElMessage} from "element-plus";
import {store} from "@/vuex/store"

const login_flag=ref<boolean>(false)

//显示、隐藏退出按钮
const showSetting=()=> {
  login_flag.value = !login_flag.value
}

const user = reactive<any>({
})

const state=reactive({user})

const getUserInfo=()=>{
  state.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")||'0') : null
  console.log(state.user)
}

onMounted(()=> {
  getUserInfo()
})


//左侧栏放大缩小
const toggle =()=> {
  store.commit('toggle',store.state.flag)
}

const index=()=> {
  router.push({path: '/teacher'})
}

const exit=()=> {
  router.push("/")
  localStorage.removeItem("user")
  ElMessage.success("退出成功")
}
</script>

<style lang="less" scoped>
.fade-enter-active, .fade-leave-active {
  transition: opacity .5s;
}
.fade-enter, .fade-leave-to /* .fade-leave-active below version 2.1.8 */ {
  opacity: 0;
}
#topBar {
  position: relative;
  z-index: 10;
  background-color: #124280;
  height: 80px;
  line-height: 80px;
  color: #fff;
  box-shadow: 5px 0px 10px rgba(0, 0, 0, 0.5);
}
#topBar .topBar-left {
  height: 80px;
  display: flex;
  justify-content: center;
  background: rgba(0, 0, 0, 0.05);
  overflow: hidden;
}
.topBar-left .icon-kaoshi {
  font-size: 60px;
}
.topBar-left .title {
  font-size: 20px;
  cursor: pointer;
}
.topBar-right {
  display: flex;
  justify-content: space-between;
  align-items: center;
}
.topBar-right .user-img {
  width: 50px;
  height: 50px;
  border-radius: 50%;
}
.topBar-right .el-icon-menu {
  font-size: 30px;
  margin-left: 20px;
}
.topBar-right .user {
  position: relative;
  margin-right: 40px;
  display: flex;
}
.topBar-right .user .user-img {
  margin-top: 15px;
  margin-left: 10px;
  cursor: pointer;
}
.user .out {
  font-size: 14px;
  position: absolute;
  top: 80px;
  right: 0px;
  background-color: #fff;
  box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
  padding: 12px;
}
.user .out ul {
  list-style: none;
  text-align: center;
  width: 60px;
}
.user .out ul > li {
  height: 26px;
  line-height: 26px;
}
.out a {
  text-decoration: none;
  color: #000;
}
.out .exit {
  margin-top: 4px;
  padding-top: 4px;
  border-top: 1px solid #ccc;
}
</style>
