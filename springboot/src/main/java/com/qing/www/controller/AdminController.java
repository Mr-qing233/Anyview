package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IAdminService;
import com.qing.www.po.Admin;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private IAdminService adminService;

    @PostMapping
    public Boolean save(@RequestBody Admin admin) {
        return adminService.saveOrUpdate(admin);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return adminService.removeById(id);
    }

    @GetMapping
    public List<Admin> findAll() {
        return adminService.list();
    }

    @GetMapping("/{id}")
    public List<Admin> findOne(@PathVariable Integer id) {
        return adminService.list();
    }

    @GetMapping("/page")
    public Page<Admin> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return adminService.page(new Page<>(pageNum, pageSize));
    }

}

