package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IJudgeQuestionService;
import com.qing.www.po.JudgeQuestion;


/**
 * <p>
 * 判断题题库表 前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@RestController
@RequestMapping("/judge-question")
public class JudgeQuestionController {

    @Resource
    private IJudgeQuestionService judgeQuestionService;

    @PostMapping
    public Boolean save(@RequestBody JudgeQuestion judgeQuestion) {
        return judgeQuestionService.saveOrUpdate(judgeQuestion);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return judgeQuestionService.removeById(id);
    }

    @GetMapping
    public List<JudgeQuestion> findAll() {
        return judgeQuestionService.list();
    }

    @GetMapping("/{id}")
    public List<JudgeQuestion> findOne(@PathVariable Integer id) {
        return judgeQuestionService.list();
    }

    @GetMapping("/page")
    public Page<JudgeQuestion> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return judgeQuestionService.page(new Page<>(pageNum, pageSize));
    }

}

