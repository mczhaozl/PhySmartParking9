package org.jeecg.modules.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.jeecg.modules.parking.entity.PkParkingLot;

import java.util.List;

/**
 * @Description: 停车场
 * @Author: jeecg-boot
 * @Date:   2020-07-11
 * @Version: V1.0
 */
public interface PkParkingLotMapper extends BaseMapper<PkParkingLot> {
	
	/**查询停车场总车位数及空闲车位总数
	 * @return
	 */
	public PkParkingLot getTotalAndFreeNum();
	
	/**查询空闲车位数最多的七处停车场
	 * @return
	 */
	public List<PkParkingLot> getFreeLotRank();
}
