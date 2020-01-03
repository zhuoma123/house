package cn.kgc.util;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import java.io.File;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2020/1/1 20:31
 * @Content: 文件上传的工具类，把文件上传的代码进行封装
 */
public class FileUploadUtil {

    private static final String savePosition="E:\\images\\";
    //上传图片的方法
    public static String upload(CommonsMultipartFile pfile){
        try{
            String fname=pfile.getOriginalFilename();

            String subname = fname.substring(fname.lastIndexOf("."));
            //生成新的文件名
            String uniqueName=System.currentTimeMillis()+"";
            String saveFileName=uniqueName+subname;
            //创建文件保存的路径
            String savePath=savePosition+saveFileName;
            File saveFile=new File(savePath);
            pfile.transferTo(saveFile);//上传
            return saveFileName;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    //删除图片的方法
    public  static boolean deleteFile(String fileName){
        File file=new File(savePosition+fileName);
        return file.delete();
    }

}
