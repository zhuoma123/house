package cn.kgc.service.impl;

import cn.kgc.domain.Users;
import cn.kgc.domain.UsersExample;
import cn.kgc.mapper.UsersMapper;
import cn.kgc.service.UserService;
import cn.kgc.util.MD5Utils;
import cn.kgc.util.UserCondition;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/26 15:21
 * @Content:
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UsersMapper usersMapper;
    @Override
    //根据姓名和电话模糊查询
    public PageInfo<Users> selectUsers(UserCondition userCondition) {
        UsersExample usersExamle=new UsersExample();
        PageHelper.startPage(userCondition.getPage(),userCondition.getRows());
        //封装查询条件
        UsersExample.Criteria criteria = usersExamle.createCriteria();
        if(userCondition.getName()!=null){
            criteria.andNameLike("%"+userCondition.getName()+"%");
        }
        if(userCondition.getTelePhone()!=null){
            criteria.andTelephoneLike("%"+userCondition.getTelePhone()+"%");
        }
        List<Users> usersList = usersMapper.selectByExample(usersExamle);
        PageInfo pageInfo=new PageInfo(usersList);
        return pageInfo;
    }

    @Override
    //检查用户名是否可用
    public boolean isExist(String name) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer("0"));//保证是房东用户
        criteria.andNameEqualTo(name);
        List<Users> usersList = usersMapper.selectByExample(usersExample);
        //如果查询得到的集合为空，说明用户名不存在，可以用
        if(usersList!=null&&usersList.size()!=0){
            return false;
        }else {
            return true;
        }
    }
    //用户注册
    @Override
    public int addUser(Users users) {
        //出于系统安全考虑，需要对密码进行加密
        //使用MD5工具
        System.out.println("未加密的密码:"+users.getPassword());
        String newPassword= MD5Utils.md5Encrypt(users.getPassword());
        System.out.println("加密后的密码："+newPassword);
        users.setPassword(newPassword);
        //设置房东用户
        users.setIsadmin(0);
        int i = usersMapper.insertSelective(users);
        return i;
    }
    //用户登录
    @Override
    public Users loginUser(String username, String password) {
        UsersExample usersExample=new UsersExample();
        UsersExample.Criteria criteria = usersExample.createCriteria();
        criteria.andIsadminEqualTo(new Integer("0"));//保证房东用户
        criteria.andNameEqualTo(username);//用户名
        criteria.andPasswordEqualTo(MD5Utils.md5Encrypt(password));//密码，同样进行加密
        List<Users> list = usersMapper.selectByExample(usersExample);
        if(list!=null&&list.size()>0){
            return list.get(0);//返回登录的人
        }
        return null;
    }
}
