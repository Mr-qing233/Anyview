package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IUserEventLogService;
import com.qing.www.entity.UserEventLog;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/user-event-log")
public class UserEventLogController {

    @Resource
    private IUserEventLogService userEventLogService;

    @PostMapping
    public Boolean save(@RequestBody UserEventLog userEventLog) {
        return userEventLogService.saveOrUpdate(userEventLog);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userEventLogService.removeById(id);
    }

    @GetMapping
    public List<UserEventLog> findAll() {
        return userEventLogService.list();
    }

    @GetMapping("/{id}")
    public List<UserEventLog> findOne(@PathVariable Integer id) {
        return userEventLogService.list();
    }

    @GetMapping("/page")
    public Page<UserEventLog> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return userEventLogService.page(new Page<>(pageNum, pageSize));
    }

}

