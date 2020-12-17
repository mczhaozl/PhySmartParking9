package org.jeecg.modules.parking.service.impl;

import org.jeecg.modules.parking.entity.PkOrder;
import org.jeecg.modules.parking.mapper.PkOrderMapper;
import org.jeecg.modules.parking.service.IPkOrderService;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

@Service
public class PkOrderServiceImpl extends ServiceImpl<PkOrderMapper, PkOrder> implements IPkOrderService{

}
