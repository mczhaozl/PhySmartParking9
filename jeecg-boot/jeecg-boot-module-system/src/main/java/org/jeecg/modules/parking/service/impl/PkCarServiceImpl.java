package org.jeecg.modules.parking.service.impl;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.jeecg.modules.parking.entity.PkCar;
import org.jeecg.modules.parking.mapper.PkCarMapper;
import org.jeecg.modules.parking.service.IPkCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

/**
 * @Description: 车辆
 * @Author: jeecg-boot
 * @Date:   2020-07-11
 * @Version: V1.0
 */
@Service
public class PkCarServiceImpl extends ServiceImpl<PkCarMapper, PkCar> implements IPkCarService {
	
	@Autowired
	private PkCarMapper pkCarMapper;
	
	@Override
	public PkCar getCarByCarNumber(String carNumber) {
		return pkCarMapper.getCarByCarNumber(carNumber);
	}
	
	@Autowired
	public List<Map<String,Object>> findCarCountByType(){
		return pkCarMapper.findCarCountByType();
	};
	
}
