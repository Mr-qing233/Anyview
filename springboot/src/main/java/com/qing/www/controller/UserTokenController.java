package com.qing.www.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

import com.qing.www.service.IUserTokenService;
import com.qing.www.entity.UserToken;


/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Gin
 * @since 2022-09-18
 */
@RestController
@RequestMapping("/user-token")
public class UserTokenController {

    @Resource
    private IUserTokenService userTokenService;

    @PostMapping
    public Boolean save(@RequestBody UserToken userToken) {
        return userTokenService.saveOrUpdate(userToken);
    }

    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id) {
        return userTokenService.removeById(id);
    }

    @GetMapping
    public List<UserToken> findAll() {
        return userTokenService.list();
    }

    @GetMapping("/{id}")
    public List<UserToken> findOne(@PathVariable Integer id) {
        return userTokenService.list();
    }

    @GetMapping("/page")
    public Page<UserToken> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize) {
        return userTokenService.page(new Page<>(pageNum, pageSize));
    }

}

