package cn.kgc.service.impl;

import cn.kgc.domain.Street;
import cn.kgc.domain.StreetExample;
import cn.kgc.mapper.StreetMapper;
import cn.kgc.service.StreetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/20 19:42
 * @Content:
 */
@Service
public class StreetServiceImpl implements StreetService {
@Autowired
private StreetMapper streetMapper;


    @Override
    public List<Street> selectStreetByDistrict(Integer did) {
        StreetExample streetExample=new StreetExample();
        StreetExample.Criteria criteria = streetExample.createCriteria();
        criteria.andDistrictIdEqualTo(did);
        List<Street> streetList = streetMapper.selectByExample(streetExample);
        return streetList;
    }
}
