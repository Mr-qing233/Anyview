import {createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw} from 'vue-router'


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
    component:()=>import('../components/student/index.vue')
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
