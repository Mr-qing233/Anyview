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
    component:()=>import('../components/teacher/index.vue'),
    children:[
      {
        path: '/teacher/', //首页默认路由
        component: () => import('../components/teacher/home.vue')
      },
      {
        path: '/teacher/grade',
        component: () => import('../components/charts/grade.vue')
      },
      {
        path: '/teacher/selectExamToPart', //学生分数段
        component: () => import('../components/teacher/selectExamToPart.vue')
      },
      {
        path: '/teacher/scorePart',
        component: () => import('../components/charts/scorePart.vue')
      },
      {
        path: '/teacher/allStudentsGrade', //所有学生成绩统计
        component: () => import('../components/teacher/allStudentsGrade.vue')
      },
      {
        path: '/teacher/selectExam', //查询所有考试
        component: () => import('../components/teacher/selectExam.vue')
      },
      {
        path: '/teacher/addExam', //添加考试
        component: () => import('../components/teacher/addExam.vue')
      },
      {
        path: '/teacher/selectAnswer', //查询所有题库
        component: () => import('../components/teacher/selectAnswer.vue')
      },
      {
        path: '/teacher/addAnswer', //增加题库主界面
        component: () => import('../components/teacher/addAnswer.vue')
      },
      {
        path: '/teacher/addAnswerChildren', //点击试卷跳转到添加题库页面
        component: () => import('../components/teacher/addAnswerChildren.vue')
      }
    ]
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
