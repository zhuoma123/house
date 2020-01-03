package cn.kgc.util;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/26 15:16
 * @Content:
 */
//条件实体继承分页工具类去查询，就只用写UserCondition一个参数
public class UserCondition extends PageUtil {
    private String name;
    private String telePhone;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelePhone() {
        return telePhone;
    }

    public void setTelePhone(String telePhone) {
        this.telePhone = telePhone;
    }


}
