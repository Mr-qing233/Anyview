package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IMessageService;
import com.qing.www.entity.Message;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/message")
public class MessageController {

    @Resource
    private IMessageService messageService;

    @PostMapping
    public Boolean save(@RequestBody Message message) {
        return messageService.saveOrUpdate(message);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return messageService.removeById(id);
    }

    @GetMapping
    public List<Message> findAll() {
        return messageService.list();
    }

    @GetMapping("/{id}")
    public List<Message> findOne(@PathVariable Integer id) {
        return messageService.list();
    }

    @GetMapping("/page")
    public Page<Message> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return messageService.page(new Page<>(pageNum, pageSize));
    }

}

