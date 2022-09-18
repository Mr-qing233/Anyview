package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IMessageUserService;
import com.qing.www.entity.MessageUser;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/message-user")
public class MessageUserController {

    @Resource
    private IMessageUserService messageUserService;

    @PostMapping
    public Boolean save(@RequestBody MessageUser messageUser) {
        return messageUserService.saveOrUpdate(messageUser);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return messageUserService.removeById(id);
    }

    @GetMapping
    public List<MessageUser> findAll() {
        return messageUserService.list();
    }

    @GetMapping("/{id}")
    public List<MessageUser> findOne(@PathVariable Integer id) {
        return messageUserService.list();
    }

    @GetMapping("/page")
    public Page<MessageUser> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return messageUserService.page(new Page<>(pageNum, pageSize));
    }

}

