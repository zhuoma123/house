package cn.kgc.service.impl;

import cn.kgc.domain.Type;
import cn.kgc.domain.TypeExample;
import cn.kgc.mapper.TypeMapper;
import cn.kgc.service.TypeService;
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
public class TypeServiceImpl implements TypeService {
@Autowired
private TypeMapper typeMapper;

    @Override
    public PageInfo<Type> selectType(PageUtil pageUtil) {
        TypeExample typeExample=new TypeExample();
        //开启分页
        PageHelper.startPage(pageUtil.getPage(),pageUtil.getRows());
        //查询数据
        List<Type> typeList = typeMapper.selectByExample(typeExample);
        //封装数据
        PageInfo pageInfo=new PageInfo(typeList);
        return pageInfo;
    }

    @Override
    public int addType(Type type) {
        int i = typeMapper.insertSelective(type);
        return i;
    }

    @Override
    public Type showById(Integer id) {
        return typeMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateType(Type type) {
        return typeMapper.updateByPrimaryKeySelective(type);
    }

    /**
     * 基于事务的删除，同时删除区域信息和街道信息
     * @param id 根据id删除
     * @return
     */
    @Override
    @Transactional//支持事务，要不都成功，要不都不成功
    public int deleteType(Integer id) {




        typeMapper.deleteByPrimaryKey(id);
        return 1;//自己定义
    }
//批量删除
    @Override
    public int removeManyType(Integer[] ids) {
        return typeMapper.delManyType(ids);
    }

    @Override
    public List<Type> selectAllType() {

        return typeMapper.selectByExample(new TypeExample());
    }
}
