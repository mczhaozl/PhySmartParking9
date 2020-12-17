package org.jeecg.modules.parking.service.impl;

import java.util.List;

import org.jeecg.modules.parking.entity.PkParkingLot;
import org.jeecg.modules.parking.mapper.PkParkingLotMapper;
import org.jeecg.modules.parking.service.IPkParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 停车场
 * @Author: jeecg-boot
 * @Date:   2020-07-11
 * @Version: V1.0
 */
@Service
public class PkParkingLotServiceImpl extends ServiceImpl<PkParkingLotMapper, PkParkingLot> implements IPkParkingLotService {

	@Autowired
	private PkParkingLotMapper pkParkingLotMapper;
	
	@Override
	public PkParkingLot getTotalAndFreeNum() {
		return pkParkingLotMapper.getTotalAndFreeNum();
	}
	
	@Override
	public List<PkParkingLot> getFreeLotRank() {
		return pkParkingLotMapper.getFreeLotRank();
	}
	
}
