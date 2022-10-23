<template>
  <div id="student">
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        :ellipsis="false"
        active-text-color="#ffd04b"
        @select="handleSelect"
    >
      <el-menu-item index="1"><router-link to="/student/">首页</router-link></el-menu-item>
      <el-menu-item index="2"><router-link to="/student/exam">我的考试</router-link></el-menu-item>
      <el-menu-item index="3"><router-link to="/student/scoreTable">我的分数</router-link></el-menu-item>
      <div class="flex-grow" />
      <el-menu-item index="4" @click="exit()">退出登录</el-menu-item>
    </el-menu>

    <!--路由区域-->
    <div class="main">
      <router-view></router-view>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import socket from "@/utils/socket";
import {store} from "@/vuex/store";
import {ElMessageBox} from "element-plus";
import router from "@/router";
import {ElMessage} from "element-plus/es";

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}

const exit=()=> {
  router.push("/")
  localStorage.removeItem("user")
  sessionStorage.removeItem("user")
  ElMessage.success("退出成功")
}

</script>

<style scoped>
@import "../../assets/css/common.css";
.flex-grow {
  flex-grow: 1;
}
</style>