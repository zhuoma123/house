package cn.kgc.mapper;

import cn.kgc.domain.House;
import cn.kgc.domain.HouseExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HouseMapper {
    int deleteByPrimaryKey(String id);

    int insert(House record);

    int insertSelective(House record);

    List<House> selectByExample(HouseExample example);

    House selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(House record);

    int updateByPrimaryKey(House record);

    //分页查询当前用户发布的出租房信息
    List<House> selectHouseByUser(Integer userId);

    //分页查询当前用户已删除的出租房信息
    //List<House> selectHaveDelHouse(Integer userId);

    /*修改出租房信息*/
    //先查询回显出需要修改的单条出租房信息
    House selectHouseById(@Param("id")String id);
    //再删除，dao层不用写

    //在运营商后台查询所有出租房信息
    List<House> selectAllHouse();

}