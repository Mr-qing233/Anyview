package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IExamPaperService;
import com.qing.www.entity.ExamPaper;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/exam-paper")
public class ExamPaperController {

    @Resource
    private IExamPaperService examPaperService;

    @PostMapping
    public Boolean save(@RequestBody ExamPaper examPaper) {
        return examPaperService.saveOrUpdate(examPaper);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return examPaperService.removeById(id);
    }

    @GetMapping
    public List<ExamPaper> findAll() {
        return examPaperService.list();
    }

    @GetMapping("/{id}")
    public List<ExamPaper> findOne(@PathVariable Integer id) {
        return examPaperService.list();
    }

    @GetMapping("/page")
    public Page<ExamPaper> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return examPaperService.page(new Page<>(pageNum, pageSize));
    }

}

