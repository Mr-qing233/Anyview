//查询所有考试
<template>
  <div class="exam">
    <el-table :data="state.pagination.records" border>
      <el-table-column fixed="left" prop="source" label="试卷名称" width="120"></el-table-column>
      <el-table-column prop="description" label="介绍" width="200"></el-table-column>
      <el-table-column prop="institute" label="所属学院" width="120"></el-table-column>
      <el-table-column prop="major" label="所属专业" width="200"></el-table-column>
      <el-table-column prop="grade" label="年级" width="100"></el-table-column>
      <el-table-column prop="startTime" label="考试起始时间" width="120"></el-table-column>
      <el-table-column prop="endTime" label="考试结束时间" width="120"></el-table-column>
      <el-table-column prop="totalScore" label="总分" width="120"></el-table-column>
      <el-table-column prop="type" label="试卷类型" width="120"></el-table-column>
      <el-table-column prop="tips" label="考生提示" width="400"></el-table-column>
      <el-table-column fixed="right" label="考试状态" width="100">
        <template #default="scope">
          <div v-if="scope.row.state === -1" style="color: green"><el-icon class="is-loading" color="green"><Loading /></el-icon>准备中</div>
          <div v-if="scope.row.state === 0" style="color: #873800"><el-icon color="#873800"><Lock /></el-icon>未开始</div>
          <div v-if="scope.row.state === 1" style="color: #1890ff"><el-icon color="#1890ff"><Unlock /></el-icon>进行中</div>
          <div v-if="scope.row.state === 2" style="color: red"><el-icon color="red"><CircleClose /></el-icon>已结束</div>
        </template>
      </el-table-column>
      <el-table-column fixed="right" label="操作" width="150">
        <template #default="scope">
          <el-button v-if="scope.row.state===0" @click="edit(scope.row.examCode)" type="primary" size="small">编辑</el-button>
          <el-button v-if="scope.row.state===0" @click="confirm(scope.row.examCode,-1)" type="primary" size="small">准备</el-button>
          <el-button v-if="scope.row.state===-1" @click="confirm(scope.row.examCode,1)" type="primary" size="small">开始</el-button>
          <el-button v-if="scope.row.state===1" @click="confirm(scope.row.examCode,2)" type="primary" size="small">结束</el-button>
          <el-button v-if="scope.row.state===1" @click="delay(scope.row.examCode)" type="primary" size="small">延时</el-button>
          <el-button v-if="scope.row.state===2" @click="" type="primary" size="small">批改</el-button>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="state.pagination.current"
        :page-sizes="[4, 8, 10, 20]"
        :page-size="state.pagination.size"
        layout="total, sizes, prev, pager, next, jumper"
        :total="state.pagination.total" class="page">
    </el-pagination>
    <!-- 确认对话框-->
    <el-dialog
      title="确认操作"
      v-model="dialogVisibleConfirm"
      width="30%"
      :before-close="handleClose">
      <div class="confirm">是否确认执行当前操作？</div>
      <div class="buttons">
        <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleConfirm = false">取 消</el-button>
        <el-button type="primary" @click="submitState()">确 定</el-button>
      </span>
      </div>
    </el-dialog>
    <!-- 编辑对话框-->
    <el-dialog
        title="编辑试卷信息"
        v-model="dialogVisibleEdit"
        width="30%"
        :before-close="handleClose">
      <section class="update">
        <el-form ref="state.form" :model="state.form" label-width="80px">
          <el-form-item label="试卷名称">
            <el-input v-model="state.form.source"></el-input>
          </el-form-item>
          <el-form-item label="介绍">
            <el-input v-model="state.form.description"></el-input>
          </el-form-item>
          <el-form-item label="所属学院">
            <el-input v-model="state.form.institute"></el-input>
          </el-form-item>
          <el-form-item label="所属专业">
            <el-input v-model="state.form.major"></el-input>
          </el-form-item>
          <el-form-item label="年级">
            <el-input v-model="state.form.grade"></el-input>
          </el-form-item>
          <el-form-item label="考试起始时间">
            <el-col :span="11">
              <el-date-picker format="YYYY/MM/DD HH:mm:ss" value-format="YYYY-MM-DD h:m:s" type="datetime" placeholder="选择起始时间" v-model="state.form.startTime" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
<!--          <el-form-item label="考试结束时间">-->
<!--            <el-input v-model="state.form.endTime"></el-input>-->
<!--          </el-form-item>-->
          <el-form-item label="考试结束时间">
            <el-col :span="11">
              <el-date-picker format="YYYY/MM/DD HH:mm:ss" value-format="YYYY-MM-DD h:m:s" type="datetime" placeholder="选择结束时间" v-model="state.form.endTime" style="width: 100%;"></el-date-picker>
            </el-col>
          </el-form-item>
          <el-form-item label="总分">
            <el-input v-model="state.form.totalScore"></el-input>
          </el-form-item>
          <el-form-item label="试卷类型">
            <el-input v-model="state.form.type"></el-input>
          </el-form-item>
          <el-form-item label="考生提示">
            <el-input type="textarea" v-model="state.form.tips"></el-input>
          </el-form-item>
        </el-form>
      </section>
      <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleEdit = false">取 消</el-button>
        <el-button type="primary" @click="submit()">确 定</el-button>
        <el-button @click="deleteRecord(state.form.examCode)" type="danger">删除</el-button>
      </span>
    </el-dialog>
    <!-- 延迟对话框-->
    <el-dialog
        title="确认操作"
        v-model="dialogVisibleDelay"
        width="30%"
        :before-close="handleClose">
      <div class="confirm">请输入延迟时间(min)</div>
      <el-input v-model="min" placeholder="Please input" />
      <div class="buttons">
        <span slot="footer" class="dialog-footer">
        <el-button @click="dialogVisibleConfirm = false">取 消</el-button>
        <el-button type="primary" @click="submitDelay(min)">确 定</el-button>
      </span>
      </div>
    </el-dialog>
  </div>
</template>

<script lang="ts" setup>
import {onMounted, reactive, ref} from "vue";
import request from "@/utils/request";
import {ElMessage, ElMessageBox} from "element-plus";

const pagination=reactive<any>({
  current:<number> 1, //当前页
  total:<number|null> null, //记录条数
  size:<number> 6 ,//每页条数
  records:[]
})
const form = reactive<any>({
  examCode:<number|null> null
})
const state=reactive<any>({
  pagination,
  form
})

const dialogVisibleEdit=ref<boolean|null>()
const dialogVisibleConfirm=ref<boolean|null>()
const dialogVisibleDelay=ref<boolean|null>(false)


const getExamInfo=()=> { //分页查询所有试卷信息
  request.get(`/exam-manage/exams/${state.pagination.current}/${state.pagination.size}`).then((res:any)=>{
    state.pagination=res.data
    console.log(state.pagination)
  }).catch(error=>{
    console.log(error)
  })
}

onMounted(()=>{
  getExamInfo()
})

const edit=(examCode:any)=>{ //编辑试卷
  dialogVisibleEdit.value = true
  request.get(`/exam-manage/exam/${examCode}`).then((res:any) => { //根据试卷id请求后台
    if(res.code == '200') {
      state.form = res.data
    }
  })
}

const confirm =(examCode:any,examState:any)=>{
  dialogVisibleConfirm.value = true
  request.get(`/exam-manage/exam/${examCode}`).then((res:any) => { //根据试卷id请求后台
    if(res.code == '200') {
      state.form = res.data
      state.form.state = examState
    }
  })
}
const min = ref<any>('')
const delay = (examCode:any)=>{
  dialogVisibleDelay.value = true
  request.get(`/exam-manage/exam/${examCode}`).then((res:any) => { //根据试卷id请求后台
    if(res.code == '200') {
      state.form = res.data
    }
  })
}

const submitDelay=(time:any)=>{
  dialogVisibleDelay.value = false
  request({
    url:`/exam-manage/examDelay/${time}`,
    method:'put',
    data:{
      ...state.form
    }
  }).then((res:any)=>{
    if(res.code == '200'){
      ElMessage.success("考试状态改变")
    }
    getExamInfo()
  })

}


const submitState=()=>{//更改状态
  dialogVisibleConfirm.value = false
  request({
    url:'/exam-manage/examUpdate',
    method:'put',
    data: {
      ...state.form
    }
  }).then((res:any)=>{
    if(res.code == '200'){
      ElMessage.success("考试状态改变")
    }
    getExamInfo()
  })
}

const handleClose=(done:any)=> { //关闭提醒
  ElMessageBox.confirm('确认关闭？')
      .then(_ => {
        done();
      }).catch(_ => {});
}

const submit=()=> { //提交修改后的试卷信息
  dialogVisibleEdit.value = false
  request({
    url:'/exam-manage/examUpdate',
    method:'put',
    data:{
      ...state.form
    }
  }).then((res:any)=>{
    if(res.code == '200'){
      ElMessage.success("更新考试数据成功")
    }
    getExamInfo()
  })
}

const deleteRecord=(examCode:any)=> {
  ElMessageBox.confirm('确定删除该记录吗,该操作不可逆！！！', '删除提示', {
    confirmButtonText: '确定删除',
    cancelButtonText: '算了,留着',
    type: 'warning'
  }).then(()=> { //确认删除
    request({
      url: `/exam-manage/exam/${examCode}`,
      method: 'delete',
    }).then(res => {
      getExamInfo()
    })
  }).catch(() => {

  })
}

//改变当前记录条数
const handleSizeChange=(val:any)=> {
  state.pagination.size = val
  getExamInfo()
}
//改变当前页码，重新发送请求
const handleCurrentChange=(val:any)=> {
  state.pagination.current = val
  getExamInfo()
}
</script>

<style lang="less" scoped>
.exam {
  padding: 0px 40px;
  .page {
    margin-top: 20px;
    display: flex;
    justify-content: center;
    align-items: center;
  }
  .edit{
    margin-left: 20px;
  }
}
.confirm{
  display: flex;
  justify-content: left;
  align-items: center;
}
.buttons{
  margin-top: 20px;
}
</style>