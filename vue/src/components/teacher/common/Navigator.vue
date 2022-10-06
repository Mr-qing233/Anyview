<template>
  <div id="nav">
    <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item class="title">{{active.title}}</el-breadcrumb-item>
      <el-breadcrumb-item v-if="active.item1 != null">{{active.item1}}</el-breadcrumb-item>
      <el-breadcrumb-item v-if="active.item2 != null">{{active.item2}}</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
</template>

<script lang="ts" setup>

import {computed, onMounted, onUnmounted, ref} from "vue";
import {store} from "@/vuex/store";
import {emitter} from "@/mitt/mitt";


let index1 = ref<any>(null)

let active = ref<any>([])

const getIndex=()=>{
 emitter.on('sendIndex',(data)=>{
   index1 = data
   active = store.state.menu[data-1]
   console.log(JSON.stringify(active)+'----')
 })
}

onMounted(()=>{
  getIndex()
})

onUnmounted(()=>{
  emitter.off('sendIndex')
})
</script>

<style scoped>

</style>