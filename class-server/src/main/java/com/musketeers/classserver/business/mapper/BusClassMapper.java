package com.musketeers.classserver.business.mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.musketeers.common.entity.business.BusClass;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fanjiahao
 * @since 2019-04-05
 */
public interface BusClassMapper extends BaseMapper<BusClass> {

    BusClass  getClassByID(@Param("classID") int classID);

    int insertNewClass(BusClass newClass);

    int updateClass(BusClass newClass);

    int alterStatus(BusClass status);

    IPage<BusClass> querySummeryBusClass(IPage page, @Param("ew") QueryWrapper<BusClass> queryWrapper);

    int increaseFocus(@Param("classID") int classID);
}
