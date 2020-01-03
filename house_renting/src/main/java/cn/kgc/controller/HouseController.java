package cn.kgc.controller;

import cn.kgc.domain.House;
import cn.kgc.service.HouseService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2020/1/2 19:44
 * @Content:
 */
@RestController(value = "HouseController2")
@RequestMapping("/static")
public class HouseController {
    @Autowired
    private HouseService houseService;
    //在运营商后台查询所有房屋信息
    @RequestMapping("/getAllHouse")
    public Map<String,Object> getAllHouse(PageUtil pageUtil){
        PageInfo<House> pageInfo = houseService.findAllHouse(pageUtil);
        //封装数据
        Map<String,Object> map=new HashMap<>();
        map.put("total",pageInfo.getTotal());
        map.put("rows",pageInfo.getList());
        return map;
    }
}
