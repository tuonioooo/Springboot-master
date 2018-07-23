package com.swagger.controller;

import com.swagger.bean.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by daizhao.
 * User: tony
 * Date: 2018-3-21
 * Time: 13:54
 * info:
 */
@RestController
@RequestMapping("/user")
@Api("UserController API")
public class UserController {

    @ApiOperation("创建用户")
    @PostMapping("/users")
    public User create(@RequestBody @Valid User user) {
        return user;
    }

    @ApiOperation("用户详情")
    @GetMapping("/users/{id}")
    public User findById(@PathVariable Long id) {
        return new User("bbb", 21, "上海", "aaa@bbb.com");
    }

    @ApiOperation("用户列表")
    @GetMapping("/users")
    public List<User> list(@ApiParam("查看第几页") @RequestParam int pageIndex,
                           @ApiParam("每页多少条") @RequestParam int pageSize) {
        List<User> result = new ArrayList<>();
        result.add(new User("aaa", 50, "北京", "aaa@ccc.com"));
        result.add(new User("bbb", 21, "广州", "aaa@ddd.com"));
        return result;
    }

    //@ApiIgnore
    @ApiOperation("删除列表")
    @DeleteMapping("/users/{id}")
    public String deleteById(@PathVariable Long id) {
        return "delete user : " + id;
    }

}