# Anyview
# 1、项目流程

> 可视为以下阶段
> 	1.可行性分析
> 	2.需求分析
> 	3.概要设计
> 	4.详细设计
> 	5.实际编写
> 	6.测试

# 2、项目需求

> 1.课堂实时测验功能
> 表单格式:名称-状态-操作[0:准备,1:开始|结束,2:结束|延时,3:批改]
>
> | 测验状态 | 可用的操作 |
> | -------- | ---------- |
> | 未开始0  | 准备       |
> | 准备1    | 开始；结束 |
> | 进行中2  | 结束；延时 |
> | 已结束3  | 批改       |
>
> | 操作 | 具体设计                                                     |
> | ---- | ------------------------------------------------------------ |
> | 准备 | 教师端弹出一个带可输入多少分钟后自动开始测验的确认/取消框。用户输入分钟数并确认后，测验表可用，通知学生进入/强制跳转至测验表界面。（且所设置的班级的考试时间不可以冲突（某个班级在一个时间段有测验，不允许这个时间段有其他测验，要提示用户）。） |
> | 开始 | 通知学生端的测验表界面中的“操作”列所有按钮变为可用；更新学生端的测验表界面中的“开始时间”和“当前状态”；倒计时开始。（用户如果手动点开始，则所设置的班级的考试时间不可以冲突（某个班级在一个时间段有测验，不允许这个时间段有其他测验，要提示用户）。 |
> | 结束 | 教师端弹出一个确认框。教师点击确认后，通知学生端保存所有学生的代码，并跳转出测验表界面。 |
> | 延时 | 教师端弹出一个带可输入延时多少分钟的确认/取消框。用户输入延时分钟并确认后，更新学生端的测验表界面和做题界面中的倒计时。（需检查延时之后的时间会不会影响该班级其他测验的时间，如果影响要提示用户重新设置延时时间） |
>
> 2.添加测验编排
> 	1.允许选择多个班
> 	2.选择题目表
> 	3.新建题目跳转页面
> 	4.返回主界面
>
> 3.添加测验题目表、选择题库
> 	1.展示所有题库
> 	2.点击查看题目题目列表
> 	3.返回测验编排页面
>
> > 1、用户可以同时选择多道题目。2、点击确定时，保存用户的选择返回“添加测验题目表”页面；点击返回时，不保存用户的选择返回“选择题库”页面。 
>
> 4.修改测验编排
>
> 5.查看测验编排
>
> 6.班级、学生管理
>
> 7.新增学校、学院、课程

# 3、项目研发

> 1.数据库设计
> 2.前端页面模板
> 3.项目整体框架搭建[两种方式，主要使用3.1]
> 	3.1独立业务模块
> 	3.2独立代码层次
> 4.业务功能开发
> 	Apifox测试

## 3.1、数据库设计

> 管理员cardId:6位[100000起始]
>
> 老师cardId:8位[10000000起始]
>
> 学生cardId:10位[1000000000起始]

## 3.2、前端页面

1. 学生端

   > 登录页
   > 选择测验页
   > 测验中页

2. 教师端

   > 实时测验页
   > 测验编排页
   > 查看测验编排页
   > 修改编排页
   > 添加测验题目表|选择题库[组卷]

3. 管理员端[暂且搁置]

   > 学校学院等数据库信息管理

# 4、数据库建表

> 本地数据库登录:
> mysql -uroot -p
> root
>
> 数据库结构查看:
> select column_name,column_type,is_nullable,column_default,column_comment from information_schema.columns where table_schema='preface' and table_name='表名';
>
> 数据库建表语句查看:
> SHOW CREATE TABLE 表名;

## 4.1 管理员表

| Field      | Type              | Comment    |
| ---------- | ----------------- | ---------- |
| adminId    | int NOT NULL      | 管理员id   |
| adminName  | char(30) NOT NULL | 管理员名字 |
| cardId     | int NOT NULL      | 账号       |
| pwd        | char(20) NOT NULL | 密码       |
| permission | int NOT NULL      | 管理员权限 |

## 4.2 学生表

| Field       | Type              | Comment  |
| ----------- | ----------------- | -------- |
| studentIde  | int NOT NULL      | 学生id   |
| studentName | char(10) NOT NULL | 学生姓名 |
| grade       | int NOT NULL      | 学生年级 |
| institue    | char(20) NOT NULL | 学院     |
| major       | char(20) NOT NULL | 专业     |
| class       | int NOT NULL      | 班级     |
| cardId      | int NOT NULL      | 账号     |
| pwd         | char(20) NOT NULL | 密码     |
| sex         | char(2) NOT NULL  | 性别     |
| permission  | int NOT NULL      | 权限     |

## 4.3 老师表

| Field       | Type              | Comment  |
| ----------- | ----------------- | -------- |
| teacherId   | int NOT NULL      | 老师id   |
| teacherName | char(10) NOT NULL | 老师名字 |
| institute   | char(20) NOT NULL | 学院     |
| course      | char(20) NOT NULL | 课程     |
| cardId      | int NOT NULL      | 账号     |
| pwd         | char(20) NOT NULL | 密码     |
| permission  | int NOT NULL      | 权限     |

## 4.4 各题目表

## 4.5 测验编排表

## 4.6 题库表







# 5、项目规划

>1:前端vue:localhost:8080
>
>2:后端springboot:localhost:8081
>
>3:数据库mysql:localhost:3306
>
>4:分包:
>
>- com.qing.www.po（与数据库表一一对应的实体类）
>
>- com.qing.www.util（工具类，与业务逻辑无关，通常为各个项目都可以通用的代码）
>
>- com.qing.www.dao（放置数据库或文件读写相关的代码）
>
>- com.qing.www.service（放置处理业务逻辑的代码，隔离dao层与controller层）
>
>- com.qing.www.controller（负责接受前端参数，分发请求） yourname 换成你的名字的拼音 
