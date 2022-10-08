<!--考生答题界面-->
<template>
  <div id="answer">
    <!--顶部信息栏-->
    <div class="top">
      <ul class="item">
        <li><el-icon class="icon20" ref="toggle" @click="slider_flag = !slider_flag"><Grid /></el-icon></li>
        <li>{{state.examData.type}}-{{state.examData.source}}</li>
        <li @click="flag = !flag">
          <el-icon class="icon20"><Avatar /></el-icon>
          <div class="msg"  v-if="flag" @click="flag = !flag">
            <p>姓名：{{state.user.studentName}}</p>
            <p>准考证号:  {{state.user.studentId}}</p>
          </div>
        </li>
        <li><el-icon class="icon20"><CaretLeft /></el-icon></li>
      </ul>
    </div>
    <div class="flexarea">
      <!--左边题目编号区-->
      <transition name="slider-fade">
        <div class="left" v-if="slider_flag">
          <ul class="l-top">
            <li>
              <a href="javascript:;"></a>
              <span>当前</span>
            </li>
            <li>
              <a href="javascript:;"></a>
              <span>未答</span>
            </li>
            <li>
              <a href="javascript:;"></a>
              <span>已答</span>
            </li>
            <li>
              <a href="javascript:;"></a>
              <span>标记</span>
            </li>
          </ul>
          <div class="l-bottom">
            <div class="item">
              <p>选择题部分</p>
              <ul>
                <li v-for="(list, index1) in state.topic[1]" :key="index1">
                  <a href="javascript:;"
                     @click="change(index1)"
                     :class="{'border': index === index1 && state.currentType === 1,'bg': bg_flag && state.topic[1][index1].isClick === true}">
                    <span :class="{'mark': state.topic[1][index1].isMark === true}"></span>
                    {{index1+1}}
                  </a>
                </li>
              </ul>
            </div>
            <div class="item">
              <p>填空题部分</p>
              <ul>
                <li v-for="(list, index2) in state.topic[2]" :key="index2">
                  <a href="javascript:;"
                     @click="fill(index2)"
                     :class="{'border': index === index2 && state.currentType === 2,'bg': state.fillAnswer[index2][3] === true}">
                    <span :class="{'mark': state.topic[2][index2].isMark === true}">
                  </span>{{topicCount[0]+index2+1}}</a>
                </li>
              </ul>
            </div>
            <div class="item">
              <p>判断题部分</p>
              <ul>
                <li v-for="(list, index3) in state.topic[3]" :key="index3">
                  <a href="javascript:;" @click="judge(index3)" :class="{'border': index === index3 && state.currentType === 3,'bg': state.topic[3][index3].isClick === true}">
                    <span :class="{'mark': state.topic[3][index3].isMark === true}"></span>{{topicCount[0]+topicCount[1]+index3+1}}</a>
                </li>
              </ul>
            </div>
            <div class="final" @click="commit()">结束考试</div>
          </div>
        </div>
      </transition>
      <!--右边选择答题区-->
      <transition name="slider-fade">
        <div class="right">
          <div class="title">
            <p>{{state.title}}</p>
            <i class="iconfont icon-right auto-right"></i>
            <span>全卷共{{topicCount[0] + topicCount[1] + topicCount[2]}}题  <i class="iconfont icon-time"></i>倒计时：<b>{{time}}</b>分钟</span>
          </div>
          <div class="content">
            <p class="topic"><span class="number">{{state.number}}</span>{{state.showQuestion}}</p>
            <div v-if="state.currentType == 1">
              <el-radio-group v-model="state.radio[index]" @change="getChangeLabel" >
                <el-radio :label="1">{{state.showAnswer.answerA}}</el-radio>
                <el-radio :label="2">{{state.showAnswer.answerB}}</el-radio>
                <el-radio :label="3">{{state.showAnswer.answerC}}</el-radio>
                <el-radio :label="4">{{state.showAnswer.answerD}}</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li><el-tag type="success">正确姿势：</el-tag><span class="right">{{state.reduceAnswer.rightAnswer}}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{state.reduceAnswer.analysis == null ? '暂无解析': state.reduceAnswer.analysis}}</li>
                </ul>
              </div>
            </div>
            <div class="fill" v-if="state.currentType === 2">
              <div v-for="(item,currentIndex) in part" :key="currentIndex">
                <el-input placeholder="请填在此处"
                          v-model="state.fillAnswer[index][currentIndex]"
                          clearable
                          @blur="fillBG" />
              </div>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li><el-tag type="success">正确答案：</el-tag><span class="right">{{state.topic[2][index].answer}}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{state.topic[2][index].analysis == null ? '暂无解析': state.topic[2][index].analysis}}</li>
                </ul>
              </div>
            </div>
            <div class="judge" v-if="state.currentType === 3">
              <el-radio-group v-model="judgeAnswer[index]" @change="getJudgeLabel" v-if="state.currentType === 3">
                <el-radio :label="1">正确</el-radio>
                <el-radio :label="2">错误</el-radio>
              </el-radio-group>
              <div class="analysis" v-if="isPractice">
                <ul>
                  <li> <el-tag type="success">正确姿势：</el-tag><span class="right">{{topic[3][index].answer}}</span></li>
                  <li><el-tag>题目解析：</el-tag></li>
                  <li>{{topic[3][index].analysis == null ? '暂无解析': topic[3][index].analysis}}</li>
                </ul>
              </div>
            </div>
          </div>
          <div class="operation">
            <ul class="end">
              <li @click="previous()"><i class="iconfont icon-previous"></i><span>上一题</span></li>
              <li @click="mark()"><i class="iconfont icon-mark-o"></i><span>标记</span></li>
              <li @click="next()"><span>下一题</span><i class="iconfont icon-next"></i></li>
            </ul>
          </div>
        </div>
      </transition>
    </div>
  </div>
</template>

<script lang="ts" setup>

import {computed, onMounted, reactive, ref} from "vue";
import {useRoute} from "vue-router";
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";
import router from "@/router";
import {store} from "@/vuex/store";





const user = reactive<any>({
  studentName:null,
  studentId:null
})
const topic=reactive<any|null>({
  isClick:false
})//试卷信息
const examData=reactive<any|null>({
  //考试信息
  // source: null,
  // totalScore: null,
})

let reduceAnswer=reactive<any>([{
  rightAnswer:<any>null,
  analysis:<any>null,
}]) //vue官方不支持3层以上数据嵌套,如嵌套则会数据渲染出现问题,此变量直接接收3层嵌套时的数据。
let index=ref<any>(0) //全局index
let topicCount=reactive<any>([])//每种类型题目的总数
let score=reactive<any>([])////每种类型分数的总数

let fillAnswer=reactive<any>([[]])//二维数组保存所有填空题答案
let judgeAnswer=reactive<any>([]) //保存所有判断题答案
let topicAnswer=reactive<any>([])//学生选择题作答编号

let number=ref<any>(1)//题号
let showQuestion=reactive<any>({})//当前显示题目信息
let showAnswer=reactive<any>({
  answerA:<any>null,
  answerB:<any>null,
  answerC:<any>null,
  answerD:<any>null,
})//当前题目对应的答案选项

let answerScore=ref<any>(0)//答题总分数
let radio=reactive<any>([]) //保存考生所有选择题的选项
/**
 * 取代
 * userInfo: { //用户信息
 *   name: null,
 *   id: null
 * }
 */

let isFillClick=ref<any>(false) //选择题是否点击标识符

let currentType=ref<any>(1)//当前题型类型  1--选择题  2--填空题  3--判断题
let title=ref<any>("请选择正确的选项")
let rightAnswer=ref<any>('')


const state=reactive({
  user,
  examData,
  topic,
  showQuestion,
  showAnswer,
  number,
  radio,
  reduceAnswer,
  fillAnswer,
  isFillClick,
  currentType,
  title,
  index,
})


const getLocalStorage=()=> {  //获取cookie
    state.user = localStorage.getItem("user") ? JSON.parse(localStorage.getItem("user")||'0') : null
    //console.log(state.user)
}
/**
 * 可能放到后端去做
 */
const calcuScore=()=> { //计算答题分数

}




const route=useRoute()
const getExamData=()=> { //获取当前试卷所有信息
  let examCode = route.query.examCode//获取路由传递过来的试卷编号
  request.get(`/exam-manage/exam/${examCode}`).then((res:any) => {  //通过examCode请求试卷详细信息
    state.examData = { ...res.data} //获取考试详情
    state.index = 0
    //使用websocket获取 time = state.examData.totalScore //获取分钟数
    let paperId = state.examData.paperId
    request.get(`/paper-manage/paper/${paperId}`).then((res:any) => {  //通过paperId获取试题题目信息
      state.topic = {...res}
      //console.log(state.topic)
      state.reduceAnswer = state.topic[1][state.index]
      //console.log(state.reduceAnswer)
      let keys = Object.keys(state.topic) //对象转数组
      keys.forEach(e => {
        let data = state.topic[e]
        topicCount.push(data.length)
        let currentScore = 0
        for(let i = 0; i< data.length; i++) { //循环每种题型,计算出总分
          currentScore += data[i].score
        }
        score.push(currentScore) //把每种题型总分存入score
      })
      let len = topicCount[1]
      let father = []
      for(let i = 0; i < len; i++) { //根据填空题数量创建二维空数组存放每道题答案
        let children = [null,null,null,null]
        father.push(children)
      }
      state.fillAnswer = father
      let dataInit = state.topic[1]

      state.number = 1
      state.showQuestion = dataInit[0].question

      state.showAnswer = dataInit[0]
      console.log(state.showAnswer)
    })
  })
}



const change=(index1:any)=> { //选择题
  state.index = index1
  state.reduceAnswer = state.topic[1][state.index]

  state.isFillClick = true
  state.currentType = 1
  let len = state.topic[1].length
  if(state.index < len) {
    if(state.index <= 0){
      state.index = 0
    }
    console.log(`总长度${len}`)
    console.log(`当前index:${state.index}`)
    state.title = "请选择正确的选项"
    let Data = state.topic[1]
    //console.log(Data)
    state.showQuestion = Data[state.index].question //获取题目信息
    state.showAnswer = Data[state.index]
    state.number = state.index + 1
  }else if(state.index >= len) {
    state.index = 0
    fill(state.index)
  }
}

const fillBG=()=> { //填空题已答题目 如果已答该题目,设置第四个元素为true为标识符
  if(state.fillAnswer[state.index][0] != null) {
    state.fillAnswer[state.index][3] = true
  }
}

let part=ref<any>(null)//填空题的空格数量
const fill=(index2:any)=>{ //填空题
  let len = state.topic[2].length
  state.currentType = 2
  state.index = index2
  if(state.index < len) {
    if(state.index < 0) {
      state.index = state.topic[1].length -1
      change(state.index)
    }else {
      console.log(`总长度${len}`)
      console.log(`当前index:${state.index}`)
      state.title = "请在横线处填写答案"
      let Data = state.topic[2]
      console.log(Data)
      state.showQuestion = Data[state.index].question //获取题目信息
      part= state.showQuestion.split("()").length -1 //根据题目中括号的数量确定填空横线数量
      state.number = topicCount[0] + state.index + 1
    }
  }else if(state.index >= len) {
    state.index = 0
    judge(state.index)
  }
}

const judge=(index3:any)=> { //判断题
  let len = state.topic[3].length
  state.currentType = 3
  state.index = index3
  if(state.index < len) {
    if(state.index < 0){
      state.index = state.topic[2].length - 1
      fill(state.index)
    }else {
      console.log(`总长度${len}`)
      console.log(`当前index:${state.index}`)
      state.title = "请作出正确判断"
      let Data = state.topic[3]
      console.log(Data)
      state.showQuestion = Data[state.index].question //获取题目信息
      state.number = topicCount[0] + topicCount[1] + state.index + 1
    }
  }else if (state.index >= len) {
    state.index = 0
    change(state.index)
  }
}


let bg_flag=ref<any>(false)//已答标识符,已答改变背景色
let slider_flag=ref<any>(true)//左侧显示隐藏标识符
let flag=ref<any>(false)//个人信息显示隐藏标识符

const getChangeLabel=(val:any)=> { //获取选择题作答选项
  state.radio[state.index] = val //当前选择的序号
  console.log(state.radio[state.index])
  if(val) {
    let data = state.topic[1]
    bg_flag = true
    data[state.index]["isClick"] = true
  }
  /* 保存学生答题选项 */
  topicAnswer[state.index] = val

}
const getJudgeLabel=(val:any)=> {  //获取判断题作答选项
  judgeAnswer[state.index] = val
  if(val) {
    let data = state.topic[3]
    bg_flag = true
    data[state.index]["isClick"] = true
  }
}
const previous=()=> { //上一题
  state.index --
  switch(state.currentType) {
    case 1:
      change(state.index)
      break
    case 2:
      fill(state.index)
      break
    case 3:
      judge(state.index)
      break
  }
}
const next=()=> { //下一题
  state.index ++
  switch(state.currentType) {
    case 1:
      change(state.index)
      break
    case 2:
      fill(state.index)
      break
    case 3:
      judge(state.index)
      break
  }
}

const mark=()=> { //标记功能
  switch(state.currentType) {
    case 1:
      state.topic[1][state.index]["isMark"] = !state.topic[1][state.index]["isMark"] //选择题标记
      break
    case 2:
      state.topic[2][state.index]["isMark"] = !state.topic[2][state.index]["isMark"] //填空题标记
      break
    case 3:
      state.topic[3][state.index]["isMark"] = !state.topic[3][state.index]["isMark"] //判断题标记
  }
}

const getTime=(date:any)=> { //日期格式化
  let year = date.getFullYear()
  let month= date.getMonth()+ 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
  let day=date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
  let hours=date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
  let minutes=date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
  let seconds=date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
  // 拼接
  return year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
}

/**
 * 以下三个时间使用websocket实现
 */
/**
 * 还未进行websocket获取时间
 */
let time=ref<any>(null) //考试持续时间
let startTime=ref<any>(null)//考试开始时间
let endTime=ref<any>(null) //考试结束时间

const commit=()=> { //答案提交计算分数
  /* 计算选择题总分 */
  let finalScore = 0
  topicAnswer.forEach((element:any,index1:any) => { //循环每道选择题根据选项计算分数
    let right = null
    if(element != null) {
      switch(element) { //选项1,2,3,4 转换为 "A","B","C","D"
        case 1:
          right = "A"
          break
        case 2:
          right = "B"
          break
        case 3:
          right = "C"
          break
        case 4:
          right = "D"
      }
      if(right == state.topic[1][index1].rightAnswer) { // 当前选项与正确答案对比
        finalScore += state.topic[1][index1].score // 计算总分数
      }
      console.log(right,state.topic[1][index1].rightAnswer)
    }
    // console.log(topic1Answer)
  })
  /**计算判断题总分 */
      // console.log(`this.fillAnswer${this.fillAnswer}`)
      // console.log(this.topic[2][this.index])

  fillAnswer.forEach((element:any,index1:any) => { //此处index和 this.index数据不一致，注意
    element.forEach((inner:any) => {
      if(state.topic[2][index1].answer.includes(inner)) { //判断填空答案是否与数据库一致
        console.log("正确")
        finalScore += state.topic[2][index1].score
      }
    })
  });
  /** 计算判断题总分 */
  judgeAnswer.forEach((element:any,index1:any) => {
    let right = null
    switch(element) {
      case 1:
        right = "T"
        break
      case 2:
        right = "F"
    }
    if(right == state.topic[3][index1].answer) { // 当前选项与正确答案对比
      finalScore += state.topic[3][index1].score // 计算总分数
    }
  })
  console.log(`目前总分${finalScore}`)
  if(time != 0) {
    ElMessageBox.confirm("考试结束时间未到,是否提前交卷","友情提示",{
      confirmButtonText: '立即交卷',
      cancelButtonText: '再检查一下',
      type: 'warning'
    }).then(() => {
      console.log("交卷")
      let date = new Date()
      endTime = getTime(date)
      let answerDate = endTime.substr(0,10)
      //提交成绩信息
      request({
        url: '/score/add',
        method: 'post',
        data: {
          examCode: state.examData.examCode, //考试编号
          studentId: state.user.id, //学号
          subject: state.examData.source, //课程名称
          etScore: finalScore, //答题成绩
          answerDate: answerDate, //答题日期
        }
      }).then((res:any) => {
        if(res.code == '200') {
          router.push({path:'/student/studentScore',query: {
              score: finalScore,
              startTime,
              endTime,
            }})
        }
      })
    }).catch(() => {
      console.log("继续答题")
    })
  }
}

const showTime=()=> { //倒计时
  setInterval(() => {
    time -= 1
    if(time == 10) {
      ElMessage.error({
        showClose: true,
        message: '考生注意,考试时间还剩10分钟！！！'
      })
      if(time == 0) {
        console.log("考试时间已到,强制交卷。")
      }
    }
  },1000 * 60)
}
let isPractice = computed(()=>{
  return store.state.isPractice
})

onMounted(()=>{
  getLocalStorage()
  getExamData()
  showTime()
})

</script>

<style lang="less" scoped>
.iconfont.icon-time {
  color: #2776df;
  margin: 0px 6px 0px 20px;
}
.analysis {
  margin-top: 20px;
  .right {
    color: #2776df;
    font-size: 18px;
    border: 1px solid #2776df;
    padding: 0px 6px;
    border-radius: 4px;
    margin-left: 20px;
  }
  ul li:nth-child(2) {
    margin: 20px 0px;
  }
  ul li:nth-child(3) {
    padding: 10px;
    background-color: #d3c6c9;
    border-radius: 4px;
  }
}
.analysis span:nth-child(1) {
  font-size: 18px;
}
.mark {
  position: absolute;
  width: 4px;
  height: 4px;
  content: "";
  background-color: red;
  border-radius: 50%;
  top: 0px;
  left: 22px;
}
.border {
  position: relative;
  border: 1px solid #FF90AA !important;
}
.bg {
  background-color: #5188b8 !important;
}
.fill .el-input {
  display: inline-flex;
  width: 150px;
  margin-left: 20px;
  .el-input__inner {
    border: 1px solid transparent;
    border-bottom: 1px solid #eee;
    padding-left: 20px;
  }
}
/* slider过渡效果 */
.slider-fade-enter-active {
  transition: all .3s ease;
}
.slider-fade-leave-active {
  transition: all .3s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}
.slider-fade-enter, .slider-fade-leave-to {
  transform: translateX(-100px);
  opacity: 0;
}

.operation .end li:nth-child(2) {
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-color: rgb(39, 118, 223);
  border-radius: 50%;
  width: 50px;
  height: 50px;
  color: #fff;
}
.operation .end li {
  cursor: pointer;
  margin: 0 100px;
}
.operation {
  background-color: #fff;
  border-radius: 4px;
  padding: 10px 0px;
  margin-right: 10px;
}
.operation .end {
  display: flex;
  justify-content: center;
  align-items: center;
  color: rgb(39, 118, 223);
}
.content .number {
  display: inline-flex;
  justify-content: center;
  align-items: center;
  width: 20px;
  height: 20px;
  background-color: rgb(39, 118, 223);
  border-radius: 4px;
  margin-right: 4px;
}
.content {
  padding: 0px 20px;
}
.content .topic {
  padding: 20px 0px;
  padding-top: 30px;
}
.right .content {
  background-color: #fff;
  margin: 10px;
  margin-left: 0px;
  height: 470px;
}
.content .el-radio-group label {
  color: #000;
  margin: 5px 0px;
}
.content .el-radio-group {
  display: flex;
  flex-direction:column;
}
.right .title p {
  margin-left: 20px;
}
.flexarea {
  display: flex;
}
.flexarea .right {
  flex: 1;
}
.auto-right {
  margin-left: auto;
  color: #2776df;
  margin-right: 10px;
}
.right .title {
  margin-right: 10px;
  padding-right: 30px;
  display: flex;
  margin-top: 10px;
  background-color: #fff;
  height: 50px;
  line-height: 50px;
}
.clearfix {
  clear: both;
}
.l-bottom .final {
  cursor: pointer;
  display: inline-block;
  text-align: center;
  background-color: rgb(39, 118, 223);
  width: 240px;
  margin: 20px 0px 20px 10px;
  border-radius: 4px;
  height: 30px;
  line-height: 30px;
  color: #fff;
  margin-top: 22px;
}
#answer .left .item {
  padding: 0px;
  font-size: 16px;
}
.l-bottom {
  border-radius: 4px;
  background-color: #fff;
}
.l-bottom .item p {
  margin-bottom: 15px;
  margin-top: 10px;
  color: #000;
  margin-left: 10px;
  letter-spacing: 2px;
}
.l-bottom .item li {
  width: 15%;
  margin-left: 5px;
  margin-bottom: 10px;
}
.l-bottom .item {
  display: flex;
  flex-direction: column;
}
.l-bottom .item ul {
  width: 100%;
  margin-bottom: -8px;
  display: flex;
  justify-content: space-around;
  flex-wrap: wrap;
}
.l-bottom .item ul li a {
  position: relative;
  justify-content: center;
  display: inline-flex;
  align-items: center;
  width: 30px;
  height: 30px;
  border-radius: 50%;
  background-color: #fff;
  border: 1px solid #eee;
  text-align: center;
  color: #000;
  font-size: 16px;
}
.left .l-top {
  display: flex;
  justify-content: space-around;
  padding: 16px 0px;
  border: 1px solid #eee;
  border-radius: 4px;
  margin-bottom: 10px;
  background-color: #fff;
}
.left {
  width: 260px;
  height: 100%;
  margin: 10px 10px 0px 10px;
}
.left .l-top li:nth-child(2) a {
  border: 1px solid #eee;
}
.left .l-top li:nth-child(3) a {
  background-color: #5188b8;
  border: none;
}
.left .l-top li:nth-child(4) a {
  position: relative;
  border: 1px solid #eee;
}
.left .l-top li:nth-child(4) a::before {
  width: 4px;
  height: 4px;
  content: " ";
  position: absolute;
  background-color: red;
  border-radius: 50%;
  top: 0px;
  left: 16px;
}
.left .l-top li {
  display: flex;
  justify-content: center;
  align-items: center;
  flex-direction: column;
}
.left .l-top li a {
  display: inline-block;
  padding: 10px;
  border-radius: 50%;
  background-color: #fff;
  border: 1px solid #FF90AA;
}
#answer .top {
  background-color: rgb(39, 118, 223);
}
#answer .item {
  color: #fff;
  display: flex;
  padding: 20px;
  font-size: 20px;
}
#answer .top .item li:nth-child(1) {
  margin-right: 10px;
}
#answer .top .item li:nth-child(3) {
  position: relative;
  margin-left: auto;
}
#answer {
  padding-bottom: 30px;
}
.icon20 {
  font-size: 20px;
  font-weight: bold;
}
.item .msg {
  padding: 10px 15px;
  border-radius: 4px;
  top: 47px;
  right: -30px;
  color: #6c757d;
  position: absolute;
  border: 1px solid rgba(0,0,0,.15);
  background-color: #fff;
}
.item .msg p {
  font-size: 16px;
  width: 200px;
  text-align: left;
}
</style>