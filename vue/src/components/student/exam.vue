<template>
  <div id="myExam">
    <div class="title">我的试卷</div>
    <div class="wrapper">
      <ul class="top">
        <li class="order">试卷列表</li>
        <li class="search-li"><div class="icon"><input type="text" placeholder="试卷名称" class="search" v-model="key"><i class="el-icon-search"></i></div></li>
        <li><el-button type="primary" @click="search()">搜索试卷</el-button></li>
      </ul>
      <div class="pagination">
        <el-pagination
            @size-change="handleSizeChange"
            @current-change="handleCurrentChange"
            :current-page="pagination.current"
            :page-sizes="[6, 10, 20, 40]"
            :page-size="pagination.size"
            layout="total, sizes, prev, pager, next, jumper"
            :total="pagination.total">
        </el-pagination>
      </div>
      <ul class="paper" v-loading="loading">
        <li class="item" v-for="(item,index) in state.pagination.records" :key="index">
          <h4 @click="toExamMsg(item.examCode)">{{item.source}}</h4>
          <p class="name">{{item.source}}-{{item.description}}</p>
          <div class="info">
            <i class="el-icon-loading"></i><span>{{item.examDate}}</span>
            <i class="iconfont icon-icon-time"></i><span v-if="item.totalTime != null">限时{{item.totalTime}}分钟</span>
            <i class="iconfont icon-fenshu"></i><span>满分{{item.totalScore}}分</span>
          </div>
        </li>
      </ul>

    </div>
  </div>
</template>

<script lang="ts" setup>
import {onBeforeMount, onMounted, reactive, ref} from "vue";
import request from "@/utils/request";
import router from "@/router";


let loading=ref<boolean|null>(false)
let key=ref<any|null>(null)//搜索关键字
let allExam//所有考试信息
let records=ref<any>([
  {
    "examCode": 20190001,
    "description": "2019年上期期末考试",
    "source": "计算机网络",
    "paperId": 1001,
    "examDate": "2019-03-21",
    "totalTime": 120,
    "grade": "2018",
    "term": "1",
    "major": "计算机科学与技术",
    "institute": "软件工程学院",
    "totalScore": 100,
    "type": "期末考试",
    "tips": "快乐千万条，学习第一条，平时不努力，考试两行泪。",
    "state": 0
  }
])
const pagination=reactive<any>({
    current:<number> 1, //当前页
    total:<number|null> null, //记录条数
    size:<number> 6 ,//每页条数
    records:[]
})
const state=reactive({pagination})
//获取当前所有考试信息
const getExamInfo=()=>{
  request.get(`/exam-manage/exams/${state.pagination.current}/${state.pagination.size}`).then((res:any)=>{
      state.pagination=res.data
      loading.value=false
      console.log(state)
    }).catch(error=>{
      console.log(error)
  })
}

onMounted(()=>{
  getExamInfo()
  loading.value=true
})

//改变当前记录条数
const handleSizeChange=(val: any)=>{
  pagination.size = val
  getExamInfo()
}

//改变当前页码，重新发送请求
const handleCurrentChange=(val:any)=> {
  pagination.current.value = val
  getExamInfo()
}

//搜索试卷
const search=()=> {
  request.get('/exam-manage/exams').then(res => {
    if(res.data.code == "200") {
      allExam = res.data.data
      let newPage = allExam.filter((item:any) => {
        return item.source.includes(key)
      })
      pagination.records = newPage
    }
  })
}

//跳转到试卷详情页
const toExamMsg=(examCode:any)=> {
  router.push({path: '/student/examMsg', query: {examCode: examCode}})
  console.log(examCode)
}
</script>

<style lang="scss" scoped>
li{
  list-style-type:none;
}
.pagination {
  padding: 20px 0px 30px 0px;
  .el-pagination {
    display: flex;
    justify-content: center;
  }
}
.paper {
  h4 {
    cursor: pointer;
  }
}
.paper .item a {
  color: #000;
}
.wrapper .top .order {
  cursor: pointer;
}
.wrapper .top .order:hover {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.wrapper .top .order:visited {
  color: #0195ff;
  border-bottom: 2px solid #0195ff;
}
.item .info i {
  margin-right: 5px;
  color: #0195ff;
}
.item .info span {
  margin-right: 14px;
}
.paper .item {
  width: 380px;
  border-radius: 4px;
  padding: 20px 30px;
  border: 1px solid #eee;
  box-shadow: 0 0 4px 2px rgba(217,222,234,0.3);
  transition: all 0.6s ease;
}
.paper .item:hover {
  box-shadow: 0 0 4px 2px rgba(140, 193, 248, 0.45);
  transform: scale(1.03);
}
.paper .item .info {
  font-size: 14px;
  color: #88949b;
}
.paper .item .name {
  font-size: 14px;
  color: #88949b;
}
.paper * {
  margin: 20px 0;
}
.wrapper .paper {
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.top .el-icon-search {
  position: absolute;
  right: 10px;
  top: 10px;
}
.top .icon {
  position: relative;
}
.wrapper .top {
  border-bottom: 1px solid #eee;
  margin-bottom: 20px;
}
#myExam .search-li {
  margin-left: auto;
}
.top .search-li {
  margin-left: auto;
}
.top li {
  display: flex;
  align-items: center;
}
.top .search {
  margin-left: auto;
  padding: 10px;
  border-radius: 4px;
  border: 1px solid #eee;
  box-shadow: inset 0 1px 1px rgba(0,0,0,.075);
  transition: border-color ease-in-out .15s,box-shadow ease-in-out .15s;
}
.top .search:hover {
  color: #0195ff;
  border-color: #0195ff;
}
.wrapper .top {
  display: flex;
}
.wrapper .top li {
  margin: 20px;
}
#myExam {
  width: 980px;
  margin: 0 auto;
}
#myExam .title {
  margin: 20px;
}
#myExam .wrapper {
  background-color: #fff;
}
</style>