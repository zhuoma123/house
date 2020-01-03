package cn.kgc.service;

import cn.kgc.domain.Users;
import cn.kgc.util.UserCondition;
import com.github.pagehelper.PageInfo;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/20 19:41
 * @Content:
 */

public interface UserService {

    /**
     * //分页动态查询用户信息
     * @param userCondition 继承了pageUtil
     * @return
     */
    public PageInfo<Users> selectUsers(UserCondition userCondition);

    /**
     * 检查用户名是否存在
     * @param name
     * @return true表示不存在，可以用，false表示不可用
     */
    public boolean isExist(String name);

    /**
     * 实现用户注册功能
     * @param users 用户实体
     * @return 影响行数
     */
    public int addUser(Users users);

    /**
     * 实现用户登录功能
     * @param username
     * @param password
     * @return 一定要返回用户实体信息，因为后面的房屋发布等业务还需要用到用户实体
     */
    public Users loginUser(String username,String password);
}
