package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.ITaskExamService;
import com.qing.www.entity.TaskExam;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/task-exam")
public class TaskExamController {

    @Resource
    private ITaskExamService taskExamService;

    @PostMapping
    public Boolean save(@RequestBody TaskExam taskExam) {
        return taskExamService.saveOrUpdate(taskExam);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return taskExamService.removeById(id);
    }

    @GetMapping
    public List<TaskExam> findAll() {
        return taskExamService.list();
    }

    @GetMapping("/{id}")
    public List<TaskExam> findOne(@PathVariable Integer id) {
        return taskExamService.list();
    }

    @GetMapping("/page")
    public Page<TaskExam> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return taskExamService.page(new Page<>(pageNum, pageSize));
    }

}

