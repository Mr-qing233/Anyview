import {createApp, onMounted} from 'vue'
import App from './App.vue'
import router from './router'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
// 引入所有图标
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
import request from "@/utils/request"

import './assets/css/common.css'
import socket from "@/utils/socket";
import {store} from "@/vuex/store";

const app=createApp(App).use(router).use(ElementPlus)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
app.config.globalProperties.request=request
app.config.globalProperties.socket=socket
app.config.globalProperties.store=store

const receiveMessage=(message: any)=> {
    //let params = JSON.parse(message.data)
    let params = JSON.parse(message.data)
    /**
     * 如果是公告消息,则存入store中webSocketMessage
     */
    if(params.flag==1){
        store.state.webSocketMessage = params.data
    }
    /**
     * 如果是考试剩余时间,则存入store中time
     */
    if(params.flag==2){
        store.state.time = params.data
        console.log(store.state.time)
    }
    console.log(params)
}
socket.init(receiveMessage)

app.mount('#app')