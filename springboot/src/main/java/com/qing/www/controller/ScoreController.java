package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IScoreService;
import com.qing.www.po.Score;


/**
 * <p>
 * 成绩管理表 前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-10
 */
@RestController
@RequestMapping("/score")
public class ScoreController {

    @Resource
    private IScoreService scoreService;

    @PostMapping
    public Boolean save(@RequestBody Score score) {
        return scoreService.saveOrUpdate(score);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return scoreService.removeById(id);
    }

    @GetMapping
    public List<Score> findAll() {
        return scoreService.list();
    }

    @GetMapping("/{id}")
    public List<Score> findOne(@PathVariable Integer id) {
        return scoreService.list();
    }

    @GetMapping("/page")
    public Page<Score> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return scoreService.page(new Page<>(pageNum, pageSize));
    }

}

