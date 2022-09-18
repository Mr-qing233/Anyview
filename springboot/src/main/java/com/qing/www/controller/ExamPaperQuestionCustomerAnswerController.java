package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IExamPaperQuestionCustomerAnswerService;
import com.qing.www.entity.ExamPaperQuestionCustomerAnswer;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/exam-paper-question-customer-answer")
public class ExamPaperQuestionCustomerAnswerController {

    @Resource
    private IExamPaperQuestionCustomerAnswerService examPaperQuestionCustomerAnswerService;

    @PostMapping
    public Boolean save(@RequestBody ExamPaperQuestionCustomerAnswer examPaperQuestionCustomerAnswer) {
        return examPaperQuestionCustomerAnswerService.saveOrUpdate(examPaperQuestionCustomerAnswer);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return examPaperQuestionCustomerAnswerService.removeById(id);
    }

    @GetMapping
    public List<ExamPaperQuestionCustomerAnswer> findAll() {
        return examPaperQuestionCustomerAnswerService.list();
    }

    @GetMapping("/{id}")
    public List<ExamPaperQuestionCustomerAnswer> findOne(@PathVariable Integer id) {
        return examPaperQuestionCustomerAnswerService.list();
    }

    @GetMapping("/page")
    public Page<ExamPaperQuestionCustomerAnswer> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return examPaperQuestionCustomerAnswerService.page(new Page<>(pageNum, pageSize));
    }

}

