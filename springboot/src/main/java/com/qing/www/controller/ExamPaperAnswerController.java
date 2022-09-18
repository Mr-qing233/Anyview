package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IExamPaperAnswerService;
import com.qing.www.entity.ExamPaperAnswer;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/exam-paper-answer")
public class ExamPaperAnswerController {

    @Resource
    private IExamPaperAnswerService examPaperAnswerService;

    @PostMapping
    public Boolean save(@RequestBody ExamPaperAnswer examPaperAnswer) {
        return examPaperAnswerService.saveOrUpdate(examPaperAnswer);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return examPaperAnswerService.removeById(id);
    }

    @GetMapping
    public List<ExamPaperAnswer> findAll() {
        return examPaperAnswerService.list();
    }

    @GetMapping("/{id}")
    public List<ExamPaperAnswer> findOne(@PathVariable Integer id) {
        return examPaperAnswerService.list();
    }

    @GetMapping("/page")
    public Page<ExamPaperAnswer> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return examPaperAnswerService.page(new Page<>(pageNum, pageSize));
    }

}

