package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.ITaskExamCustomerAnswerService;
import com.qing.www.entity.TaskExamCustomerAnswer;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/task-exam-customer-answer")
public class TaskExamCustomerAnswerController {

    @Resource
    private ITaskExamCustomerAnswerService taskExamCustomerAnswerService;

    @PostMapping
    public Boolean save(@RequestBody TaskExamCustomerAnswer taskExamCustomerAnswer) {
        return taskExamCustomerAnswerService.saveOrUpdate(taskExamCustomerAnswer);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return taskExamCustomerAnswerService.removeById(id);
    }

    @GetMapping
    public List<TaskExamCustomerAnswer> findAll() {
        return taskExamCustomerAnswerService.list();
    }

    @GetMapping("/{id}")
    public List<TaskExamCustomerAnswer> findOne(@PathVariable Integer id) {
        return taskExamCustomerAnswerService.list();
    }

    @GetMapping("/page")
    public Page<TaskExamCustomerAnswer> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return taskExamCustomerAnswerService.page(new Page<>(pageNum, pageSize));
    }

}

