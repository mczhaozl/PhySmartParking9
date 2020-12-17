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
import org.jeecg.modules.parking.entity.PkParkingLot;
import org.jeecg.modules.parking.service.IPkParkingLotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;
import java.util.List;

 /**
 * @Description: 停车场
 * @Author: jeecg-boot
 * @Date:   2020-07-11
 * @Version: V1.0
 */
@Api(tags="停车场")
@RestController
@RequestMapping("/parking/pkParkingLot")
@Slf4j
public class PkParkingLotController extends JeecgController<PkParkingLot, IPkParkingLotService> {
	@Autowired
	private IPkParkingLotService pkParkingLotService;
	
	/**
	 * 分页列表查询
	 *
	 * @param pkParkingLot
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "停车场-分页列表查询")
	@ApiOperation(value="停车场-分页列表查询", notes="停车场-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PkParkingLot pkParkingLot,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PkParkingLot> queryWrapper = QueryGenerator.initQueryWrapper(pkParkingLot, req.getParameterMap());
		Page<PkParkingLot> page = new Page<PkParkingLot>(pageNo, pageSize);
		IPage<PkParkingLot> pageList = pkParkingLotService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param pkParkingLot
	 * @return
	 */
	@AutoLog(value = "停车场-添加")
	@ApiOperation(value="停车场-添加", notes="停车场-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PkParkingLot pkParkingLot) {
		pkParkingLotService.save(pkParkingLot);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param pkParkingLot
	 * @return
	 */
	@AutoLog(value = "停车场-编辑")
	@ApiOperation(value="停车场-编辑", notes="停车场-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PkParkingLot pkParkingLot) {
		pkParkingLotService.updateById(pkParkingLot);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "停车场-通过id删除")
	@ApiOperation(value="停车场-通过id删除", notes="停车场-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pkParkingLotService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "停车场-批量删除")
	@ApiOperation(value="停车场-批量删除", notes="停车场-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pkParkingLotService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	/**
	 * 通过id查询
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "停车场-通过id查询")
	@ApiOperation(value="停车场-通过id查询", notes="停车场-通过id查询")
	@GetMapping(value = "/queryById")
	public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
		PkParkingLot pkParkingLot = pkParkingLotService.getById(id);
		if(pkParkingLot==null) {
			return Result.error("未找到对应数据");
		}
		return Result.ok(pkParkingLot);
	}

    /**
    * 导出excel
    *
    * @param request
    * @param pkParkingLot
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PkParkingLot pkParkingLot) {
        return super.exportXls(request, pkParkingLot, PkParkingLot.class, "停车场");
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
        return super.importExcel(request, response, PkParkingLot.class);
    }
    
    
    /**查询停车场总车位数及空闲车位总数
     * @return
     */
    @RequestMapping(value = "/getTotalAndFreeNum", method = RequestMethod.GET)
    public Result<PkParkingLot> getTotalAndFreeNum() {
        Result<PkParkingLot> result = new Result<PkParkingLot>();
        PkParkingLot pklot = pkParkingLotService.getTotalAndFreeNum();
        if (pklot == null) {
            result.error500("未找到对应实体");
        } else {
            result.setResult(pklot);
            result.setSuccess(true);
        }
        return result;
    }
    
    /**查询空闲车位数最多的七处停车场
     * @return
     */
    @RequestMapping(value = "/getFreeLotRank", method = RequestMethod.GET)
    public Result<?> getFreeLotRank() {
        
        List<PkParkingLot> pklotList = (List<PkParkingLot>) pkParkingLotService.getFreeLotRank();
       
        return Result.ok(pklotList);
    }

}
