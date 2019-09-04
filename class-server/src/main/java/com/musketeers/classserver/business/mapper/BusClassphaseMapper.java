package com.musketeers.classserver.business.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.musketeers.common.entity.business.BusClassphase;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author fanjiahao
 * @since 2019-04-05
 */
public interface BusClassphaseMapper extends BaseMapper<BusClassphase> {

    List<BusClassphase>  getClassphaseByClassid(@Param("classiID") int classiID);

    BusClassphase  getClassphaseByPhaseid(@Param("phaseid") int phaseid);

    int insertClassphase(@Param("busClassphaseList") List<BusClassphase> busClassphaseList);

    int updateClassphase(@Param("busClassphaseList") List<BusClassphase> busClassphaseList);

    int deleteClassphaseByID(BusClassphase busClassphase);

    int updateClassphaseByID(BusClassphase busClassphase);

    int insertClassphaseByID(BusClassphase busClassphase);

}
