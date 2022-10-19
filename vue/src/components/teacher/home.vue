<template>
  <section class="index">
    <div class="hello">
      <span>很高兴遇见你,{{state.user.teacherName}}老师。</span>
    </div>
    <div class="msg">
      <el-button @click="sendData">点击发送</el-button>
      <p class="title">教务公告：</p>
      <ul>
        <li @click="centerDialogVisible = true" >放假通知</li>
        <el-dialog
            v-model="centerDialogVisible"
            title="放假通知"
            width="30%"
            align-center
        >
          <span>10.1-10.7国庆放假</span>
          <template #footer>
      <span class="dialog-footer">
        <el-button @click="centerDialogVisible = false">取消</el-button>
        <el-button type="primary" @click="centerDialogVisible = false"
        >确认</el-button
        >
      </span>
          </template>
        </el-dialog>
      </ul>
    </div>
  </section>
</template>

<script lang="ts" setup>
import {onMounted, onUnmounted, reactive, ref} from "vue";
import socket from "@/utils/socket";
import SocketService from "@/utils/socket";
import {io} from "socket.io-client";
import {store} from "@/vuex/store";


const centerDialogVisible = ref(false)
const user = reactive<any>({
})

const state=reactive({
  user
})

const getUserInfo=()=>{
  state.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")||'0') : null
  console.log(state.user)
}



const sendData = () =>{
  const message = "send message"
  console.log(message)
  socket.send(message)
  console.log("发送完成")
}
</script>

<style lang="less" scoped>
.index {
  margin-left: 70px;
.hello {
  font-size: 20px;
  color: #726f70;
}
.msg {
.title {
  font-size: 16px;
  color: #000;
  margin-top: 20px;
  margin-left: 10px;
}
ul {
  display: flex;
  flex-direction: column;
  width: 200px;
  overflow: hidden;
}
li {
  margin-top: 10px;
  font-size: 14px;
  color: lightcoral;
  cursor: pointer;
  display: inline-block;
}
}
}
</style>