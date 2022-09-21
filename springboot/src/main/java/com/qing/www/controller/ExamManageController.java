package com.qing.www.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.qing.www.dao.ExamManageMapper;
import com.qing.www.service.impl.ExamManageServiceImpl;
import com.qing.www.util.common.CommonEnum;
import com.qing.www.util.common.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
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

}

