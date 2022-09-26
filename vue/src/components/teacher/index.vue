<template>
  <div id="index">
    <header1 class="topbar"></header1>
    <section class="container">
      <div class="left_side">
        <mainLeft></mainLeft>
      </div>
      <div class="main_wrapper">
        <navigator class="nav"></navigator>
        <router-view></router-view>
      </div>
    </section>
  </div>
</template>

<script lang="ts" setup>
import Navigator from './common/Navigator.vue'
import MainLeft from "@/components/teacher/common/MainLeft.vue";
import Header1 from "@/components/teacher/common/Header.vue";

</script>

<style lang="less" scoped>
#index .nav {
  box-shadow: 1px 0 5px rgba(0, 0, 0, 0.1);
  margin-bottom: 30px;
}
.container {
  display: flex;
  background-color: #fff;
}
.main_wrapper {
  overflow: hidden;
  flex: 1;
  background-color: #fff;
}
</style>