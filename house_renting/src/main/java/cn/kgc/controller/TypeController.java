package cn.kgc.controller;

import cn.kgc.domain.Type;
import cn.kgc.service.TypeService;
import cn.kgc.util.PageUtil;
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
public class TypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("/getTypeData")
    @ResponseBody
   public Map<String,Object> getTypeData(PageUtil pageUtil){
        PageInfo<Type> pageInfo = typeService.selectType(pageUtil);
        //封装数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
    @RequestMapping("/addType")
    @ResponseBody
    public Map<String,Object> addType(Type type){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = typeService.addType(type);
            map.put("i",i);
        }catch (Exception e){
           e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("/getType")
    @ResponseBody
    public Type getType(Integer id){
        try {
            Type type = typeService.showById(id);
            return type;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    @RequestMapping("/modifyType")
    @ResponseBody
    public Map<String,Object> modifyType(Type type){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = typeService.updateType(type);
            map.put("i",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
    @RequestMapping("/delType")
    @ResponseBody
    public Map<String,Object> delType(Integer id){
        Map<String,Object> map=new HashMap<>();
        try{
            int i = typeService.deleteType(id);
            map.put("i",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
//批量删除数据
    @RequestMapping("/delManyType")
    @ResponseBody
    //前台传递的数据格式是ids=1,2,3    后台：string ids变量接收数据
    public Map<String,Object> delManyType(String ids){
        Map<String,Object> map=new HashMap<>();
        try{
           //将字符串转换成数组
            String [] strlist=ids.split(",");//先将字符串分割成字符串数组
            Integer [] idlist=new Integer[strlist.length];//再将字符串数组转换成整数数组
            for (int i = 0; i <strlist.length ; i++) {//遍历输出
                idlist[i]=new Integer(strlist[i]);
            }
            int i = typeService.removeManyType(idlist);
            map.put("i",i);
        }catch (Exception e){
            e.printStackTrace();
        }
        return map;
    }
}
