<template>
  <div id="app">
    <!-- 确认公告对话框-->
    <el-dialog
        v-model="store.state.webSocketMessage.flag"
        title="考试提示"
        width="30%"
        :before-close="handleClose"
    >
      <div class="confirm">{{store.state.webSocketMessage.msg}}</div>
      <div class="buttons">
            <span slot="footer" class="dialog-footer">
              <el-button @click="store.state.webSocketMessage.flag = false">取 消</el-button>
              <el-button type="primary" @click="store.state.webSocketMessage.flag = false">确 定</el-button>
            </span>
      </div>
    </el-dialog>
    <router-view></router-view>
  </div>
</template>

<script lang="ts" setup>
import {ref, provide, nextTick, reactive} from 'vue'
import {ElMessageBox} from "element-plus";
import {store} from "@/vuex/store";

const isRouterAlive  = ref(true);
const reload = () => {
  isRouterAlive.value = false;
  nextTick(() => {
    isRouterAlive.value = true
  })
}
provide("reload", reload);


const handleClose=(done:any)=> { //关闭提醒
  ElMessageBox.confirm('确认关闭？')
      .then(_ => {
        done();
      }).catch(_ => {});
}

</script>
<style lang="less">
ul {
  list-style: none;
}
a {
  text-decoration: none;
}
* {
  margin: 0;
  padding: 0;
}
#app {
  font-family: "Microsoft YaHei", "Helvetica", "Tahoma", "Geneva", "Arial", sans-serif;
}
</style>
<style lang="less" scoped>
.buttons{
  margin-top: 20px;
}

.confirm{
  display: flex;
  justify-content: left;
  align-items: center;
}
</style>