<template>
  <div id="msg">
    <div class="title">
      <span>试卷列表</span>
      <span>/{{state.examData.source}}</span>
    </div>
    <div class="wrapper">
      <ul class="top">
        <li class="example">{{state.examData.source}}</li>
        <li><i class="iconfont icon-pen-"></i></li>
        <li><i class="iconfont icon-share"></i></li>
        <li class="right">
          <div>
            <span class="count">总分</span>
            <span class="score">{{score[0]+score[1]+score[2]}}</span>
          </div>
        </li>
      </ul>
      <ul class="bottom">
        <li>更新于 {{state.examData.startTime}}</li>
        <li>来自 {{state.examData.institute}}</li>
        <li class="btn">{{state.examData.type}}</li>
        <el-button class="right" @click="toAnswer(state.examData.examCode)" :disabled="state.examData.state!==1" :type="state.examData.state!==1 ? 'danger':'primary'">开始答题</el-button>
      </ul>
      <ul class="info">
        <li @click="dialogVisible = true"><a href="javascript:;"><i class="iconfont icon-info"></i>考生须知</a></li>
      </ul>
    </div>
    <div class="content">
      <el-collapse v-model="activeName" >
        <el-collapse-item class="header" name="0">
          <template #title class="stitle" >
            <div class="title">
              <span>{{state.examData.source}}</span><i class="header-icon el-icon-info"></i>
              <span class="time">{{score[0]+score[1]+score[2]}}分</span>
              <el-button type="primary" size="small">点击查看试题详情</el-button>
            </div>
          </template>
          <el-collapse class="inner">
            <el-collapse-item>
              <template #title name="1">
                <div class="titlei">选择题 (共{{topicCount[0]}}题 共计{{score[0]}}分)</div>
              </template>
              <div class="contenti">
                <ul class="question" v-for="(list, index) in state.topic[1]" :key="index">
                  <li>{{index+1}}. {{list.question}} {{list.score}}分</li>
                </ul>
              </div>
            </el-collapse-item>
            <el-collapse-item>
              <template #title name="2">
                <div class="titlei">填空题 (共{{topicCount[1]}}题  共计{{score[1]}}分)</div>
              </template>
              <div class="contenti">
                <ul class="question" v-for="(list, index) in state.topic[2]" :key="index">
                  <li>{{topicCount[0]+index+1}}.{{list.question}}  {{list.score}}分</li>
                </ul>
              </div>
            </el-collapse-item>
            <el-collapse-item>
              <template #title name="3">
                <div class="titlei">判断题 (共{{topicCount[2]}}题 共计{{score[2]}}分)</div>
              </template>
              <div class="contenti">
                <ul class="question" v-for="(list, index) in state.topic[3]" :key="index">
                  <li>{{topicCount[0]+topicCount[1]+index+1}}. {{list.question}} {{list.score}}分</li>
                </ul>
              </div>
            </el-collapse-item>
          </el-collapse>
        </el-collapse-item>

      </el-collapse>
    </div>
    <!--考生须知对话框-->
    <el-dialog
        title="考生须知"
        :visible.sync="dialogVisible"
        width="30%">
      <span>{{state.examData.tips}}</span>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisible = false">知道了</el-button>
      </span>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import router from "@/router";
import {useRoute} from "vue-router";
import request from "@/utils/request";

const dialogVisible=ref<boolean|null>(false)//对话框属性
const activeName=ref<any>('0')//默认打开序号
const topicCount=reactive<any>([])//每种类型题目的总数
const score=reactive<any>([])////每种类型分数的总数
const examData=reactive<any|null>({
  //考试信息
  // source: null,
  // totalScore: null,
})
const topic=reactive<any|null>({})//试卷信息

const state=reactive<any>({
  examData,
  topic
})


const route=useRoute()
//初始化页面数据
const init=()=> {
  let examCode = route.query.examCode//获取路由传递过来的试卷编号
  request.get(`/exam-manage/exam/${examCode}`).then(res=>{ //通过examCode请求试卷详细信息
    state.examData = { ...res.data}//扩展运算符拆开数据
    let paperId = state.examData.paperId
    request.get(`/paper-manage/paper/${paperId}`).then((res:any)=>{//通过paperId获取题目信息
      state.topic = {...res}
      let keys = Object.keys(state.topic) //对象转数组
      keys.forEach(e=>{//遍历
        let data=state.topic[e]
        topicCount.push(data.length)
        let currentScore = 0
        for(let i = 0; i< data.length; i++) { //循环每种题型,计算出总分
          currentScore += data[i].score
        }
        score.push(currentScore)//把每种题型总分存入score
        console.log(currentScore)
      })
    })
  })
}
onMounted(()=>{
  init()
})

const toAnswer=(id: any)=> {
  router.push({path:"/student/answer",query:{examCode: id}})
}

</script>

<style lang="scss" scoped>
.bottom {
  .right{
    .el-button{
      color: #409EFF;
      border-color: #c6e2ff;
      background-color: #ecf5ff;
    }
  }
}
.right {
  margin-left: auto;
}
.inner .contenti .question {
  margin-left: 40px;
  color: #9a9a9a;
  font-size: 14px;
}
.content .inner .titlei {
  margin-left: 20px;
  font-size: 16px;
  color: #88949b;
  font-weight: bold;
}
.content .title .time {
  font-size: 16px;
  margin-left: 420px;
  color: #999;
}
.content .stitle {
  background-color: #0195ff;
}
.content .title span {
  margin-right: 10px;
}
#msg .content .title {
  font-size: 20px;
  margin: 0px;
  display: flex;
  align-items: center;
}
.content {
  margin-top: 20px;
  background-color: #fff;
}
.content .header {
  padding: 10px 30px;
}
.wrapper .info {
  margin: 20px 0px 0px 20px;
  border-top: 1px solid #eee;
  padding: 20px 0px 10px 0px;
}
.wrapper .info a {
  color: #88949b;
  font-size: 14px;
}
.wrapper .info a:hover {
  color: #0195ff;
}
.wrapper .bottom .btn {
  cursor: pointer;
  padding: 5px 10px;
  border: 1px solid #88949b;
  border-radius: 4px;
}
.wrapper .bottom {
  display: flex;
  margin-left: 20px;
  color: #999;
  font-size: 14px;
  align-items: center;
}
.wrapper .bottom li {
  margin-right: 14px;
}
#msg {
  background-color: #eee;
  width: 980px;
  margin: 0 auto;
}
#msg .title {
  margin: 20px;
}
#msg .wrapper {
  background-color: #fff;
  padding: 10px;
}
.wrapper .top {
  display: flex;
  margin: 20px;
  align-items: center;
}
.wrapper .top .right {
  margin-left: auto;
}
.wrapper .top .example {
  color: #333;
  font-size: 22px;
  font-weight: 700;
}
.wrapper .top li i {
  margin-left: 20px;
  color: #88949b;
}
.wrapper .right .count {
  margin-right: 60px;
  color: #fff;
  padding: 4px 10px;
  background-color: #88949b;
  border-top-left-radius: 4px;
  border-bottom-left-radius: 4px;
  border: 1px solid #88949b;
}
.wrapper .right .score {
  position: absolute;
  left: 53px;
  top: -5px;
  padding: 1px 12px;
  font-size: 20px;
  color: #88949b;
  border: 1px dashed #88949b;
  border-left: none;
  border-top-right-radius: 4px;
  border-bottom-right-radius: 4px;
  font-weight: bold;
}
.wrapper .right div {
  position: relative;
}
</style>