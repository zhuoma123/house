package cn.kgc.service.impl;

import cn.kgc.domain.District;
import cn.kgc.domain.DistrictExample;
import cn.kgc.mapper.DistrictMapper;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.service.DistrictService;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/20 19:42
 * @Content:
 */
@Service
public class DistrictServiceImpl implements DistrictService {
@Autowired
private DistrictMapper districtMapper;
@Autowired
private StreetMapper streetMapper;
    @Override
    public PageInfo<District> selectDistrict(PageUtil pageUtil) {
        DistrictExample districtExample=new DistrictExample();
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询数据
        List<District> districtList = districtMapper.selectByExample(districtExample);
        //封装数据
        PageInfo pageInfo=new PageInfo(districtList);
        return pageInfo;
    }

    @Override
    public int addDistrict(District district) {
        int i = districtMapper.insertSelective(district);
        return i;
    }

    @Override
    public District showById(Integer id) {
        return districtMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateDistrict(District district) {
        return districtMapper.updateByPrimaryKeySelective(district);
    }

    /**
     * 基于事务的删除，同时删除区域信息和街道信息
     * @param id 根据id删除
     * @return
     */
    @Override
    @Transactional//支持事务，要不都成功，要不都不成功
    public int deleteDistrict(Integer id) {
        //第一步：删除街道，编写dao层的删除方法
        streetMapper.delStreetByDistrict(id);

        int i=0,j=1;
         j=j/i;//设置一个错误，测试事务是否回滚
        //第二步：删除区域
        districtMapper.deleteByPrimaryKey(id);
        return 1;//自己定义
    }
//批量删除
    @Override
    public int removeManyDistrict(Integer []ids) {
        return districtMapper.delManyDistrict(ids);
    }

    @Override
    public List<District> selectAllDistrict() {
        return districtMapper.selectByExample(new DistrictExample());
    }
}
