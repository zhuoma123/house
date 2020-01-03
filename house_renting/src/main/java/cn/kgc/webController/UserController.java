package cn.kgc.webController;

import cn.kgc.domain.Users;
import cn.kgc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/27 13:59
 * @Content:
 */
//专门处理前段的控制器,后面要备注value，因为后台已经有一个一样的控制器
@Controller(value = "UserController2")
@RequestMapping("/page")
public class UserController {
    @Autowired
    private UserService userService;

   /* public String checkUname(String name){
        boolean b = userService.isExist(name);
        return "{\"result\":"+b+"}";
    }*/
   @RequestMapping("/checkUname")
   @ResponseBody
    public Map<String,Object> checkUname(String username){
        Map<String,Object> map=new HashMap<>();
        try{
            boolean b = userService.isExist(username);
            map.put("b",b);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    //注册
    @RequestMapping("/registerUser")
    public String registerUser(Users users){
        int i = userService.addUser(users);
        if(i>0){
            return "redirect:login.jsp";
        }else{
            return "redirect:regs.jsp";
        }
    }
    //登录
    @RequestMapping("/loginUser")
    public String loginUser(String username, String password, HttpSession session){
        Users user = userService.loginUser(username, password);
        if(user!=null){
            session.setAttribute("userInfo",user);//保存登陆者信息，
           // session.setMaxInactiveInterval(10*60);//设置最大存活时间
            return "redirect:showHouse";
        }else {
            return "redirect:login";
        }
    }
}
