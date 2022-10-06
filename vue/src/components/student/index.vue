<template>
  <div id="student">
    <el-menu
        :default-active="activeIndex"
        class="el-menu-demo"
        mode="horizontal"
        background-color="#545c64"
        text-color="#fff"
        active-text-color="#ffd04b"
        @select="handleSelect"
    >
      <el-menu-item index="1"><router-link to="/student/">首页</router-link></el-menu-item>
      <el-menu-item index="2"><router-link to="/student/exam">我的考试</router-link></el-menu-item>
      <el-menu-item index="3"><router-link to="/student/scoreTable">我的分数</router-link></el-menu-item>
    </el-menu>

    <!--路由区域-->
    <div class="main">
      <router-view></router-view>
    </div>
  </div>
</template>

<script lang="ts" setup>
import {ref} from "vue";

const activeIndex = ref('1')
const handleSelect = (key: string, keyPath: string[]) => {
  console.log(key, keyPath)
}
</script>

<style scoped>
@import "../../assets/css/common.css";
</style>