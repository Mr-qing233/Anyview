<template>
  <div id="left">
    <el-menu
        active-text-color="#dd5862"
        text-color="#fff"
        :default-active="activePath"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
        :collapse="flag"
        background-color="#124280"
        menu-trigger="click" router>
      <el-sub-menu v-for="(item,index) in menu" :index='item.index' :key="index">
        <template #title>
          <div class="left-width">
            <el-icon>
              <component :is="item.icon"></component>
            </el-icon>
            <span class="title">{{item.title}}</span>
          </div>
        </template>
        <el-menu-item-group v-for="(list,index1) in item.content" :key="index1">
          <el-menu-item @click="handleTitle(item.index)" :index="list.path" v-if="list.item1 != null">{{list.item1}}</el-menu-item>
          <el-menu-item @click="handleTitle(item.index)" :index="list.path" v-if="list.item2 != null">{{list.item2}}</el-menu-item>
        </el-menu-item-group>
      </el-sub-menu>

    </el-menu>
  </div>
</template>

<script lang="ts" setup>
import {store} from "@/vuex/store"
import {computed, onMounted, ref} from "vue";
import {onBeforeRouteUpdate} from "vue-router";
import {emitter} from "@/mitt/mitt";

onMounted(()=>{
  addData()
})

//:default-active 刷新页面后，默认激活选中的菜单
const activePath = ref<string>('')
onBeforeRouteUpdate((to)=>{
  activePath.value = to.path
})

const handleOpen = (key:any, keyPath:any) => {
  // console.log(key, keyPath);
}
const handleClose = (key:any, keyPath:any) => {
  // console.log(key, keyPath);
}

let flag = computed(()=>{
  return store.state.flag
})

let menu = computed(()=>{
  return store.state.menu
})
//点击标题传递参数给navigator组件
const handleTitle =(index:any)=>{
  emitter.emit('sendIndex',index)
}

const addData =()=> {
  let permission = localStorage.getItem("permission") ? JSON.parse(localStorage.getItem("permission")||'0') : null
  if(permission == 0) {
    store.state.menu.push({
      index: '5',
      title: '教师管理',
      icon: 'icon-Userselect',
      content:[{item1:'教师管理',path:'/teacherManage'},{item2: '添加教师',path: '/addTeacher'}],
    })
  }
}
</script>

<style lang="less" scoped>
.el-menu-vertical-demo .el-submenu__title {
  overflow: hidden;
}
.left-width .iconfont {
  font-size: 18px;
  color: #fff;
}
.left-width {
  width: 213px;
}
.el-menu-vertical-demo:not(.el-menu--collapse) {
  max-height: 100vh;
}
#left {
  height: 89.3vh;
  background-color: #124280;
  z-index: -1;
}
#left .el-menu-vertical-demo .title {
  color: #fff;
  font-size: 16px;
  font-weight: bold;
  margin-left: 14px;
}
.el-submenu {
  border-bottom: 1px solid #eeeeee0f !important;
}
.el-submenu__title:hover {
  background-color: #fff;
}
.el-submenu__title i {
  color: #fbfbfc !important;
}
</style>