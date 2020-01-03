package cn.kgc.webController;

import cn.kgc.domain.Street;
import cn.kgc.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/28 16:54
 * @Content:
 */
@Controller(value ="StreetController2" )
@RequestMapping("/page")
public class StreetController {
    @Autowired
    private StreetService streetService;
    @RequestMapping("/getStreetByDistrict")
    @ResponseBody
    public List<Street> getStreetByDistrict(Integer did){
        List<Street> streetList = streetService.selectStreetByDistrict(did);
        return streetList;
    }
}

