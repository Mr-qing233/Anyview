package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
 * @since 2022-09-10
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

