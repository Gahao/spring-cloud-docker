package com.musketeers.classserver.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.musketeers.common.entity.business.BusClassphase;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.musketeers.classserver.business.service.IBusClassphaseService;
import com.musketeers.classserver.business.mapper.BusClassphaseMapper;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author fanjiahao
 * @since 2019-04-05
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class BusClassphaseServiceImpl extends ServiceImpl<BusClassphaseMapper, BusClassphase> implements IBusClassphaseService {

}
