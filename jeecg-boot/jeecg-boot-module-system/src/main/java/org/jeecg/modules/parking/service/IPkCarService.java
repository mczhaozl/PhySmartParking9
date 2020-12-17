package org.jeecg.modules.parking.service;

import java.util.List;
import java.util.Map;

import org.jeecg.modules.parking.entity.PkCar;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @Description: 车辆
 * @Author: jeecg-boot
 * @Date:   2020-07-11
 * @Version: V1.0
 */
public interface IPkCarService extends IService<PkCar> {
	
	public PkCar getCarByCarNumber(String carNumber);
	
	
	List<Map<String,Object>> findCarCountByType();
}
