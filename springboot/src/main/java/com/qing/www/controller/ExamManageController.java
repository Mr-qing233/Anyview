package com.qing.www.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qing.www.dto.common.CommonEnum;
import com.qing.www.dto.common.CommonResult;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IExamManageService;
import com.qing.www.po.ExamManage;


/**
 * <p>
 * 考试管理表 前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@RestController
@RequestMapping("/exam-manage")
public class ExamManageController {

    @Resource
    private IExamManageService examManageService;

    @PostMapping
    public Boolean save(@RequestBody ExamManage examManage) {
        return examManageService.saveOrUpdate(examManage);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return examManageService.removeById(id);
    }

    @GetMapping
    public List<ExamManage> findAll() {
        return examManageService.list();
    }

    @GetMapping("/exams/{page}/{size}")
    public CommonResult findAll(@PathVariable("page") Integer page, @PathVariable("size") Integer size){
        System.out.println("分页查询所有试卷");
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findAll(examManage);
        return CommonResult.Success(all);
    }

    @GetMapping("/exams/{page}/{size}/{clazzId}")
    public CommonResult findAllByClazz(@PathVariable("page") Integer page, @PathVariable("size") Integer size,@PathVariable("clazzId") Integer clazzId){
        System.out.println("分页查询该班级所有试卷");
        Page<ExamManage> examManage = new Page<>(page,size);
        IPage<ExamManage> all = examManageService.findAllByClazz(examManage,clazzId);
        return CommonResult.Success(all);
    }

    @GetMapping("/exam/{examCode}")
    public CommonResult findById(@PathVariable("examCode") Integer examCode){
        System.out.println("根据ID查找");
        ExamManage res = examManageService.getById(examCode);
        if(res == null) {
            return CommonResult.Error(CommonEnum.EXAM_NOT_FOUND);
        }
        return CommonResult.Success(res);
    }


    @GetMapping("/{id}")
    public List<ExamManage> findOne(@PathVariable Integer id) {
        return examManageService.list();
    }

    @GetMapping("/page")
    public Page<ExamManage> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return examManageService.page(new Page<>(pageNum, pageSize));
    }
    @PutMapping("/examUpdate")
    public CommonResult update(@RequestBody ExamManage examManage){
//        Date start,end;
//        Date now = new Date();
//        try {
//            start = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(examManage.getStartTime());
//            end = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(examManage.getEndTime());
//        }catch (Exception e){
//            throw new ServiceException(CommonEnum.DATETIME_FORMAT_ERROR);
//        }
//        if(examManage.getState()!=-1){
//            if(DateUtil.isIn(now, start, end)){//判断当前时间是否在此之间，并改变state为进行中，state=-1时除外
//                examManage.setState(1);
//            }else if(now.before(start)){//若在开始之前，则state为未开始
//                examManage.setState(0);
//            }else if(now.after(end)){//若在结束之后，则state为已结束
//                examManage.setState(2);
//            }
//        }

        UpdateWrapper<ExamManage> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("examCode",examManage.getExamCode());

        boolean res = examManageService.update(examManage,updateWrapper);
        if (res == false) {
            return CommonResult.Error(CommonEnum.EXAM_UPDATE_ERROR);
        }
        System.out.print("更新操作执行---");
        return CommonResult.Success();
    }

    @DeleteMapping("/exam/{examCode}")
    public CommonResult deleteById(@PathVariable("examCode") Integer examCode){
        boolean res = examManageService.removeById(examCode);
        if(res==false){
            return CommonResult.Error(CommonEnum.EXAM_DELETE_ERROR);
        }
        return CommonResult.Success();
    }

}

