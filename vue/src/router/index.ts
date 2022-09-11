import {createRouter, createWebHashHistory, createWebHistory, RouteRecordRaw} from 'vue-router'


// @ts-ignore
// @ts-ignore
const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'login',
    component: ()=>import('../views/Login.vue')
  },
];

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
