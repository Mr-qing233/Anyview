package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.ISubjectService;
import com.qing.www.entity.Subject;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/subject")
public class SubjectController {

    @Resource
    private ISubjectService subjectService;

    @PostMapping
    public Boolean save(@RequestBody Subject subject) {
        return subjectService.saveOrUpdate(subject);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return subjectService.removeById(id);
    }

    @GetMapping
    public List<Subject> findAll() {
        return subjectService.list();
    }

    @GetMapping("/{id}")
    public List<Subject> findOne(@PathVariable Integer id) {
        return subjectService.list();
    }

    @GetMapping("/page")
    public Page<Subject> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return subjectService.page(new Page<>(pageNum, pageSize));
    }

}

