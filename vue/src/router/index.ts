import {createRouter, createWebHistory, RouteRecordRaw} from 'vue-router'


// @ts-ignore
// @ts-ignore
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',//登录页面
    name: 'login',
    component: ()=>import('../views/Login.vue')
  },
  {
    path:'/admin',//管理员首页
    component:()=>import('../components/admin/index.vue')
  },
  {
    path:'/teacher',//教师首页
    component:()=>import('../components/teacher/index.vue')
  },
  {
    path:'/student',//学生首页
    component:()=>import('../components/student/index.vue'),
    children: [
      {path:"/student/",component: ()=> import('../components/student/exam.vue')},
      {path:"/student/exam",component: ()=> import('../components/student/exam.vue')},
      {path:"/student/examMsg",component: ()=> import('../components/student/examMsg.vue')},
      {path:'/student/startExam', component: () => import('../components/student/startExam.vue')},
      {path: '/student/manager', component: () => import('../components/student/manager.vue')},
      {path: '/student/studentScore', component: () => import("../components/student/answerScore.vue")},
      {path: '/student/scoreTable', component: () => import("../components/student/scoreTable.vue")}
    ]
  },
  {
    path: '/student/answer',//学生答题界面
    component: () => import('../components/student/answer.vue')
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
