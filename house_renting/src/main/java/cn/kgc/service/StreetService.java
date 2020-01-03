package cn.kgc.service;

import cn.kgc.domain.Street;

import java.util.List;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/20 19:41
 * @Content:
 */

public interface StreetService {
    /**
     * 查询对应区域下的街道信息
     * @param did
     * @return
     */
    public List<Street> selectStreetByDistrict(Integer did);
}
