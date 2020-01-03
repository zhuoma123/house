package cn.kgc.webController;


import cn.kgc.domain.House;
import cn.kgc.domain.Users;
import cn.kgc.service.HouseService;
import cn.kgc.util.FileUploadUtil;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.servlet.http.HttpSession;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/29 16:16
 * @Content:
 */
@Controller
@RequestMapping("/page")
public class HouseController {
    @Autowired
    private HouseService houseService;
    @RequestMapping("/addHouse")
    //CommonsMultipartFile是MultipartFile接口的实现类，使用它必须标注@RequestParam
    public String addHouse(HttpSession session, House house, @RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        try {
            //1.实现文件上传
            /*System.out.println("上传文件名称："+pfile.getOriginalFilename());
            System.out.println("上传文件大小："+pfile.getSize());
            System.out.println("上传文件类型："+pfile.getContentType());*/
            //获取文件的扩展名
            String saveFileName = FileUploadUtil.upload(pfile);

            //2.将数据添加到数据库
            /*修改house实体，添加额外的属性值*/
            house.setId(System.currentTimeMillis()+"");//设置编号
            //设置所属用户
            Users userInfo = (Users) session.getAttribute("userInfo");
            System.out.println("id"+userInfo.getId());
            house.setUserId(userInfo.getId());
            //设置图片路径
            house.setPath(saveFileName);

            //调用业务，实现发布
            houseService.addHouse(house);
            return "redirect:showHouse";

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "error";
    }
    //查询展示当前用户出租房
    @RequestMapping("/showHouse")
    public String showHouse(PageUtil pageUtil, HttpSession session, Model model){//这里参数不用传递用户id，而是通过session取

        //设置初始页大小
        pageUtil.setRows(3);
        /*项目情形：
        * 如果不能通过用户登录，获取session保存的userid
        * 那么可以通过固定法获取
        * String userid=1008；
        * */

        Users userInfo = (Users) session.getAttribute("userInfo");
        PageInfo<House> pageInfo = houseService.findHouseByUser(userInfo.getId(), pageUtil);
        model.addAttribute("pageInfo",pageInfo);
        return "guanli";
    }
    /*修改房屋信息*/
    @RequestMapping("/editHouse")
    public String editHouse(String id,Model model){
        House house = houseService.findHouseById(id);
        model.addAttribute("house",house);
        return "upfabu";
    }
    @RequestMapping("/updateHouse")
    public String updateHouse(House house,String oldFile,@RequestParam(value = "pfile",required = false) CommonsMultipartFile pfile){
        try{
            //1.判断有没有选择修改图片
            if(pfile.getOriginalFilename().equals("")){
                System.out.println("没有上传图片");
            }else{
                //修改图片
                String saveFileName = FileUploadUtil.upload(pfile);
                //设置修改实体图片的路径
                house.setPath(saveFileName);

                //删除旧图
                FileUploadUtil.deleteFile(oldFile);
            }
            //2.修改数据库信息
            houseService.updateHouse(house);
            return "redirect:showHouse";//跳控制器要重定向
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
    //删除出租房
    @RequestMapping("/deleteHouse")
    public String deleteHouse(String id){
        try{
            int i = houseService.deleteHouse(id, 1);//1表示删除
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }
    //恢复出租房
    @RequestMapping("/backHouse")
    public String backHouse(String id){
        try{
            int i = houseService.deleteHouse(id, 0);//1表示删除
            return "redirect:showHouse";
        }catch (Exception e){
            e.printStackTrace();
        }
        return "error";
    }


}
