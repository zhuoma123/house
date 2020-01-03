package cn.kgc.service;

import cn.kgc.domain.District;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/20 19:41
 * @Content:
 */

public interface DistrictService {

    /**
     * //分页查询区域信息
     * @param pageUtil page属性接收页码，rows属性接收页大小
     * @return
     */
    public PageInfo<District> selectDistrict(PageUtil pageUtil);

    /**
     * 添加区域信息
     * @param district 实体
     * @return 影响行数
     */
    public int addDistrict(District district);

    /**
     *
     * @param id 通过id查询到需要修改的单条信息
     * @return 实体
     */
    public District showById(Integer id);

    /**
     *修改功能实现
     * @param district 查询出的单条实体
     * @return 影响行数
     */
    public int updateDistrict(District district);

    /**
     * 删除
     * @param id 根据id删除
     * @return 影响的行数
     */
    public int deleteDistrict(Integer id);

    /**
     * 区域信息的批量删除
     * @param ids 要删除的id
     * @return 影响的行数
     */
    public int removeManyDistrict(Integer []ids);

    /**
     * 查询所有的区域信息
     * @return
     */
    public List<District> selectAllDistrict();
}
