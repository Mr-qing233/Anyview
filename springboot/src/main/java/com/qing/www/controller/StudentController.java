package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IStudentService;
import com.qing.www.po.Student;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@RestController
@RequestMapping("/student")
public class StudentController {

    @Resource
    private IStudentService studentService;

    @PostMapping
    public Boolean save(@RequestBody Student student) {
        return studentService.saveOrUpdate(student);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return studentService.removeById(id);
    }

    @GetMapping
    public List<Student> findAll() {
        return studentService.list();
    }

    @GetMapping("/{id}")
    public List<Student> findOne(@PathVariable Integer id) {
        return studentService.list();
    }

    @GetMapping("/page")
    public Page<Student> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return studentService.page(new Page<>(pageNum, pageSize));
    }

}

