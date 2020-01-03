package cn.kgc.webController;

import cn.kgc.domain.Type;
import cn.kgc.service.TypeService;
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
@Controller(value ="TypeController2" )
@RequestMapping("/page")
public class TypeController {
    @Autowired
    private TypeService typeService;
    @RequestMapping("/getTypes")
    @ResponseBody
    public List<Type> getTypes(){
        List<Type> types = typeService.selectAllType();
        return types;
    }
}
