package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IClazzService;
import com.qing.www.entity.Clazz;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-10-22
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Resource
    private IClazzService clazzService;

    @PostMapping
    public Boolean save(@RequestBody Clazz clazz) {
        return clazzService.saveOrUpdate(clazz);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return clazzService.removeById(id);
    }

    @GetMapping
    public List<Clazz> findAll() {
        return clazzService.list();
    }

    @GetMapping("/{id}")
    public List<Clazz> findOne(@PathVariable Integer id) {
        return clazzService.list();
    }

    @GetMapping("/page")
    public Page<Clazz> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return clazzService.page(new Page<>(pageNum, pageSize));
    }

}

