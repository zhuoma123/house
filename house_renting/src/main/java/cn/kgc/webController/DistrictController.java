package cn.kgc.webController;

import cn.kgc.domain.District;
import cn.kgc.service.DistrictService;
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
@Controller(value ="DistrictController2" )
@RequestMapping("/page")
public class DistrictController {
    @Autowired
    private DistrictService districtService;
    @RequestMapping("/getDistricts")
    @ResponseBody
   public List<District> getDistricts(){
        List<District> districtList = districtService.selectAllDistrict();
        return districtList;
    }
}
