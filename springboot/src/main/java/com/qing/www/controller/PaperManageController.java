package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IPaperManageService;
import com.qing.www.po.PaperManage;


/**
 * <p>
 * 试卷管理表 前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@RestController
@RequestMapping("/paper-manage")
public class PaperManageController {

    @Resource
    private IPaperManageService paperManageService;

    @PostMapping
    public Boolean save(@RequestBody PaperManage paperManage) {
        return paperManageService.saveOrUpdate(paperManage);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return paperManageService.removeById(id);
    }

    @GetMapping
    public List<PaperManage> findAll() {
        return paperManageService.list();
    }

    @GetMapping("/{id}")
    public List<PaperManage> findOne(@PathVariable Integer id) {
        return paperManageService.list();
    }

    @GetMapping("/page")
    public Page<PaperManage> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return paperManageService.page(new Page<>(pageNum, pageSize));
    }

}

