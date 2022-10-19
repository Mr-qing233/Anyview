import {createStore} from "vuex";
import { createApp } from 'vue';
import App from "@/App.vue";

export const store = createStore({
    state:{
        isPractice: false, //练习模式标志
        flag: false, //菜单栏左右滑动标志
        userInfo: null,
        menu: [{
            index: '1',
            title: '考试管理',
            icon: 'EditPen',
            content:[{item1:'考试查询',path:'/teacher/selectExam'},{item2:'添加考试',path:'/teacher/addExam'}],
        },
        {
            index: '2',
            title: '题库管理',
            icon: 'EditPen',
            content:[{item1:'所有题库',path:'/teacher/selectAnswer'},{item2:'增加题库',path:'/teacher/addAnswer'},{path: '/teacher/addAnswerChildren'}],
        },
        {
            index: '3',
            title: '成绩查询',
            icon: 'EditPen',
            content:[{item1:'学生成绩查询',path:'/teacher/allStudentsGrade'},{path: '/teacher/grade'},{item2: '成绩分段查询',path: '/teacher/selectExamToPart'},{path: '/teacher/scorePart'}],
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
        },

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