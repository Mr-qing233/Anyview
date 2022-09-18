package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IQuestionService;
import com.qing.www.entity.Question;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Resource
    private IQuestionService questionService;

    @PostMapping
    public Boolean save(@RequestBody Question question) {
        return questionService.saveOrUpdate(question);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return questionService.removeById(id);
    }

    @GetMapping
    public List<Question> findAll() {
        return questionService.list();
    }

    @GetMapping("/{id}")
    public List<Question> findOne(@PathVariable Integer id) {
        return questionService.list();
    }

    @GetMapping("/page")
    public Page<Question> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return questionService.page(new Page<>(pageNum, pageSize));
    }

}

