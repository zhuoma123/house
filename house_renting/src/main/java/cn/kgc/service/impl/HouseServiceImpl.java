package cn.kgc.service.impl;

import cn.kgc.domain.House;
import cn.kgc.mapper.HouseMapper;
import cn.kgc.service.HouseService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/29 16:06
 * @Content:
 */
@Service
public class HouseServiceImpl implements HouseService {
    @Autowired
    private HouseMapper houseMapper;
    @Override
    public int addHouse(House house) {
        int i = houseMapper.insertSelective(house);
        return i;
    }

    @Override
    public PageInfo<House> findHouseByUser(Integer userId, PageUtil pageUtil) {
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询数据
        List<House> houseList = houseMapper.selectHouseByUser(userId);
        //封装数据
        PageInfo pageInfo=new PageInfo(houseList);
        return pageInfo;
    }

    @Override
    public House findHouseById(String id) {

        return houseMapper.selectHouseById(id);
    }

    @Override
    public int updateHouse(House house) {

        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public int deleteHouse(String houseId, Integer delstate) {
        House house=new House();
        house.setId(houseId);//条件
        house.setIsdel(delstate);//状态
        return houseMapper.updateByPrimaryKeySelective(house);
    }

    @Override
    public PageInfo<House> findAllHouse(PageUtil pageUtil) {
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        List<House> houseList = houseMapper.selectAllHouse();
        PageInfo pageInfo=new PageInfo(houseList);
        return pageInfo;
    }
}
