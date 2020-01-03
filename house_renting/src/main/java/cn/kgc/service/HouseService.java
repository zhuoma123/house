package cn.kgc.service;

import cn.kgc.domain.House;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/29 16:06
 * @Content:
 */
public interface HouseService {
    /**
     * 添加出租房
     * @param house　出租房实体
     * @return
     */
    public int addHouse(House house);

    /**
     * 查询当前用户发布额出租房信息
     * @param userId
     * @return 分页查询数据
     */
   PageInfo<House> findHouseByUser(Integer userId, PageUtil pageUtil);

    /**
     * 修改出租房信息，先回显单条信息
     * @param id
     * @return 实体
     */
    House findHouseById(String id);

    /**
     * 再进行修改
     * @param house
     * @return
     */
    int updateHouse(House house);

    /**
     * 删除出租房:这里的删除不是真正删除，只是更改出租房删除状态
     * @param houseId 出租房id
     * @param delstate 出租房状态（更新）1表示删除，0表示不删除
     * @return 影响的行数
     */
    int deleteHouse(String houseId,Integer delstate);

    /**
     * 在运营商后台查询所有房屋信息
     * @return
     */
    PageInfo<House> findAllHouse(PageUtil pageUtil);
}
