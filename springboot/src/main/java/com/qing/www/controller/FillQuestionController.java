package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IFillQuestionService;
import com.qing.www.po.FillQuestion;


/**
 * <p>
 * 填空题题库 前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-11
 */
@RestController
@RequestMapping("/fill-question")
public class FillQuestionController {

    @Resource
    private IFillQuestionService fillQuestionService;

    @PostMapping
    public Boolean save(@RequestBody FillQuestion fillQuestion) {
        return fillQuestionService.saveOrUpdate(fillQuestion);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return fillQuestionService.removeById(id);
    }

    @GetMapping
    public List<FillQuestion> findAll() {
        return fillQuestionService.list();
    }

    @GetMapping("/{id}")
    public List<FillQuestion> findOne(@PathVariable Integer id) {
        return fillQuestionService.list();
    }

    @GetMapping("/page")
    public Page<FillQuestion> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return fillQuestionService.page(new Page<>(pageNum, pageSize));
    }

}

