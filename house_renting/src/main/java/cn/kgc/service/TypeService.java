package cn.kgc.service;

import cn.kgc.domain.Type;
import cn.kgc.util.PageUtil;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * @Version 1.0
 * @Author:dama zhuo
 * @Date:2019/12/20 19:41
 * @Content:
 */

public interface TypeService {

    /**
     * //分页查询区域信息
     * @param pageUtil page属性接收页码，rows属性接收页大小
     * @return
     */
    public PageInfo<Type> selectType(PageUtil pageUtil);

    /**
     * 添加区域信息
     * @param type 实体
     * @return 影响行数
     */
    public int addType(Type type);

    /**
     *
     * @param id 通过id查询到需要修改的单条信息
     * @return 实体
     */
    public Type showById(Integer id);

    /**
     *修改功能实现
     * @param type 查询出的单条实体
     * @return 影响行数
     */
    public int updateType(Type type);

    /**
     * 删除
     * @param id 根据id删除
     * @return 影响的行数
     */
    public int deleteType(Integer id);

    /**
     * 区域信息的批量删除
     * @param ids 要删除的id
     * @return 影响的行数
     */
    public int removeManyType(Integer[] ids);

    /**
     * 查询所有房屋类型
     * @return
     */
    public List<Type> selectAllType();
}
