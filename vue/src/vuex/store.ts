import {createStore} from "vuex";
import { createApp } from 'vue';
import App from "@/App.vue";

export const store = createStore({
    state:{
        sPractice: false, //练习模式标志
        flag: false, //菜单栏左右滑动标志
        userInfo: null,
        menu: [{
            index: '1',
            title: '考试管理',
            icon: 'icon-kechengbiao',
            content:[{item1:'功能介绍',path:'/examDescription'},{item2:'考试查询',path:'selectExam'},{item3:'添加考试',path:'/addExam'}],
        },
        {
            index: '2',
            title: '题库管理',
            icon: 'icon-tiku',
            content:[{item1:'功能介绍',path:'/answerDescription'},{item2:'所有题库',path:'/selectAnswer'},{item3:'增加题库',path:'/addAnswer'},{path: '/addAnswerChildren'}],
        },
        {
            index: '3',
            title: '成绩查询',
            icon: 'icon-performance',
            content:[{item1:'学生成绩查询',path:'/allStudentsGrade'},{path: '/grade'},{item2: '成绩分段查询',path: '/selectExamToPart'},{path: '/scorePart'}],
        }]
    },
    mutations: {
        practice(state:any,status:any) {
            state.isPractice = status
        },
        toggle(state:any) {
            state.flag = !state.flag
            console.log(state.flag)
        },
        changeUserInfo(state:any,info:any) {
            state.userInfo = info
        }
    },
    actions:{
        getUserInfo(context:any,info:any) {
            context.commit('changeUserInfo',info)
        },
        getPractice(context:any,status:any) {
            context.commit('practice',status)
        }
    },
    modules:{

    },
    getters:{

    }
})

// 将 store 实例作为插件安装
const app=createApp(App)
app.use(store)