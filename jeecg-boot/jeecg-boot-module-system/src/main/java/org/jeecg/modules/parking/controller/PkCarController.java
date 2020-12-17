package org.jeecg.modules.parking.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.common.util.oConvertUtils;
import org.jeecg.modules.parking.entity.PkCar;
import org.jeecg.modules.parking.service.IPkCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

 /**
 * @Description: 车辆
 * @Author: jeecg-boot
 * @Date:   2020-07-11
 * @Version: V1.0
 */
@Api(tags="车辆")
@RestController
@RequestMapping("/parking/pkCar")
@Slf4j
public class PkCarController extends JeecgController<PkCar, IPkCarService> {
	@Autowired
	private IPkCarService pkCarService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pkCar
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "车辆-分页列表查询")
	@ApiOperation(value="车辆-分页列表查询", notes="车辆-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PkCar pkCar,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PkCar> queryWrapper = QueryGenerator.initQueryWrapper(pkCar, req.getParameterMap());
		Page<PkCar> page = new Page<PkCar>(pageNo, pageSize);
		IPage<PkCar> pageList = pkCarService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param pkCar
	 * @return
	 */
	@AutoLog(value = "车辆-添加")
	@ApiOperation(value="车辆-添加", notes="车辆-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PkCar pkCar) {
		pkCarService.save(pkCar);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param pkCar
	 * @return
	 */
	@AutoLog(value = "车辆-编辑")
	@ApiOperation(value="车辆-编辑", notes="车辆-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PkCar pkCar) {
		pkCarService.updateById(pkCar);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "车辆-通过id删除")
	@ApiOperation(value="车辆-通过id删除", notes="车辆-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pkCarService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "车辆-批量删除")
	@ApiOperation(value="车辆-批量删除", notes="车辆-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pkCarService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "车辆-通过id查询")
	@ApiOperation(value="车辆-通过id查询", notes="车辆-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PkCar pkCar = pkCarService.getById(id);
		if(pkCar==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(pkCar);
	}
	
	
	/**
	 * 车辆-通过车牌号查询
	 *
	 * @param carNumber
	 * @return
	 */
	@AutoLog(value = "车辆-通过车牌号查询")
	@ApiOperation(value="车辆-通过车牌号查询", notes="车辆-通过车牌号查询")
	@GetMapping(value = "/getCarByCarNumber")
	public Result<?> getCarByCarNumber(@RequestParam(name="carNumber",required=true) String carNumber) {
		PkCar pkCar = pkCarService.getCarByCarNumber(carNumber);
		if(pkCar==null) {
			return Result.ok("未绑定车辆");
		}
		return Result.error("该车辆已绑定");
	}
	
	/**
	 * 车辆-根据类别查询车辆数
	 *
	 * @param 
	 * @return
	 */
	@AutoLog(value = "车辆-根据类别查询车辆数")
	@ApiOperation(value="车辆-根据类别查询车辆数", notes="车辆-根据类别查询车辆数")
	@GetMapping(value = "/findCarCountByType")
	public Result<List<Map<String,Object>>> findCarCountByType() {
		Result<List<Map<String,Object>>> result = new Result<List<Map<String,Object>>>();
		List<Map<String,Object>> list=pkCarService.findCarCountByType();
		result.setResult(oConvertUtils.toLowerCasePageList(list));
		return result;
		
	}
	
	
    /**
    * 导出excel
    *
    * @param request
    * @param pkCar
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PkCar pkCar) {
        return super.exportXls(request, pkCar, PkCar.class, "车辆");
    }

    /**
      * 通过excel导入数据
    *
    * @param request
    * @param response
    * @return
    */
    @RequestMapping(value = "/importExcel", method = RequestMethod.POST)
    public Result<?> importExcel(HttpServletRequest request, HttpServletResponse response) {
        return super.importExcel(request, response, PkCar.class);
    }

}
