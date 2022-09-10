package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IMultiQuestionService;
import com.qing.www.po.MultiQuestion;


/**
 * <p>
 * 选择题题库表 前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@RestController
@RequestMapping("/multi-question")
public class MultiQuestionController {

    @Resource
    private IMultiQuestionService multiQuestionService;

    @PostMapping
    public Boolean save(@RequestBody MultiQuestion multiQuestion) {
        return multiQuestionService.saveOrUpdate(multiQuestion);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return multiQuestionService.removeById(id);
    }

    @GetMapping
    public List<MultiQuestion> findAll() {
        return multiQuestionService.list();
    }

    @GetMapping("/{id}")
    public List<MultiQuestion> findOne(@PathVariable Integer id) {
        return multiQuestionService.list();
    }

    @GetMapping("/page")
    public Page<MultiQuestion> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return multiQuestionService.page(new Page<>(pageNum, pageSize));
    }

}

