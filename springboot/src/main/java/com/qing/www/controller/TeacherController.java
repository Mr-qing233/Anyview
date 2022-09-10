package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.ITeacherService;
import com.qing.www.po.Teacher;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@RestController
@RequestMapping("/teacher")
public class TeacherController {

    @Resource
    private ITeacherService teacherService;

    @PostMapping
    public Boolean save(@RequestBody Teacher teacher) {
        return teacherService.saveOrUpdate(teacher);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return teacherService.removeById(id);
    }

    @GetMapping
    public List<Teacher> findAll() {
        return teacherService.list();
    }

    @GetMapping("/{id}")
    public List<Teacher> findOne(@PathVariable Integer id) {
        return teacherService.list();
    }

    @GetMapping("/page")
    public Page<Teacher> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return teacherService.page(new Page<>(pageNum, pageSize));
    }

}

