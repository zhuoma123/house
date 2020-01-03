package cn.kgc.controller;

import cn.kgc.domain.Users;
import cn.kgc.service.UserService;
import cn.kgc.util.UserCondition;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/20 19:43
 * @Content:
 * @restController=@controller+@responseBody,使用了该注释，就不用每个方法添加responseBody注释
 */
@Controller
//@restController
@RequestMapping("/static")
public class UserController {
    @Autowired
    private UserService userService;
    @RequestMapping("/getUserData")
    @ResponseBody
   public Map<String,Object> getUserData(UserCondition userCondition){
        PageInfo<Users> pageInfo = userService.selectUsers(userCondition);
        //封装数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }

}
