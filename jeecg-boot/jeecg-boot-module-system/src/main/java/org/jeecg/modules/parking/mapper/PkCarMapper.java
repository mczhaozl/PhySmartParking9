package org.jeecg.modules.parking.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jeecg.modules.parking.entity.PkCar;

import java.util.List;
import java.util.Map;

/**
 * @Description: 车辆
 * @Author: jeecg-boot
 * @Date:   2020-07-11
 * @Version: V1.0
 */
public interface PkCarMapper extends BaseMapper<PkCar> {

	@Select("select * from pk_car where car_number = #{carNumber}")
	public PkCar getCarByCarNumber(@Param("carNumber") String carNumber);
	
	List<Map<String,Object>> findCarCountByType();
	
}
