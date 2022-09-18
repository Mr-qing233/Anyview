package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.ITextContentService;
import com.qing.www.entity.TextContent;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/text-content")
public class TextContentController {

    @Resource
    private ITextContentService textContentService;

    @PostMapping
    public Boolean save(@RequestBody TextContent textContent) {
        return textContentService.saveOrUpdate(textContent);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return textContentService.removeById(id);
    }

    @GetMapping
    public List<TextContent> findAll() {
        return textContentService.list();
    }

    @GetMapping("/{id}")
    public List<TextContent> findOne(@PathVariable Integer id) {
        return textContentService.list();
    }

    @GetMapping("/page")
    public Page<TextContent> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return textContentService.page(new Page<>(pageNum, pageSize));
    }

}

