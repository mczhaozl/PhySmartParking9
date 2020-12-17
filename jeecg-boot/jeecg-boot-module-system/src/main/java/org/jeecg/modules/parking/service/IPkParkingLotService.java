package org.jeecg.modules.parking.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.jeecg.modules.parking.entity.PkParkingLot;

import java.util.List;

/**
 * @Description: 停车场
 * @Author: jeecg-boot
 * @Date:   2020-07-11
 * @Version: V1.0
 */
public interface IPkParkingLotService extends IService<PkParkingLot> {
	
	
	public PkParkingLot getTotalAndFreeNum();
	
	public List<PkParkingLot> getFreeLotRank();
}
