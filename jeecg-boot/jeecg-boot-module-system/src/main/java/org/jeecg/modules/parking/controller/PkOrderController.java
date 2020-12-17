package org.jeecg.modules.parking.controller;

import java.util.Arrays;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecg.common.api.vo.Result;
import org.jeecg.common.aspect.annotation.AutoLog;
import org.jeecg.common.system.base.controller.JeecgController;
import org.jeecg.common.system.query.QueryGenerator;
import org.jeecg.modules.parking.entity.PkOrder;
import org.jeecg.modules.parking.entity.PkTravelTip;
import org.jeecg.modules.parking.service.IPkOrderService;
import org.jeecg.modules.parking.service.impl.PkOrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Api(tags="订单管理")
@RestController
@RequestMapping("/parking/pkOrder")
@Slf4j
public class PkOrderController extends JeecgController<PkOrder, IPkOrderService>{
	@Autowired
	private IPkOrderService pkOrderService;
	
	
	/**
	 * 分页列表查询
	 *
	 * @param pkTravelTip
	 * @param pageNo
	 * @param pageSize
	 * @param req
	 * @return
	 */
	@AutoLog(value = "订单管理-分页列表查询")
	@ApiOperation(value="订单管理-分页列表查询", notes="订单管理-分页列表查询")
	@GetMapping(value = "/list")
	public Result<?> queryPageList(PkOrder pkOrder,
								   @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
								   @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
								   HttpServletRequest req) {
		QueryWrapper<PkOrder> queryWrapper = QueryGenerator.initQueryWrapper(pkOrder, req.getParameterMap());
		Page<PkOrder> page = new Page<PkOrder>(pageNo, pageSize);
		IPage<PkOrder> pageList = pkOrderService.page(page, queryWrapper);
		return Result.ok(pageList);
	}
	
	/**
	 *   添加
	 *
	 * @param pkTravelTip
	 * @return
	 */
	@AutoLog(value = "订单管理-添加")
	@ApiOperation(value="订单管理-添加", notes="订单管理-添加")
	@PostMapping(value = "/add")
	public Result<?> add(@RequestBody PkOrder pkOrder) {
		pkOrderService.save(pkOrder);
		return Result.ok("添加成功！");
	}
	
	/**
	 *  编辑
	 *
	 * @param pkTravelTip
	 * @return
	 */
	@AutoLog(value = "订单管理-编辑")
	@ApiOperation(value="订单管理-编辑", notes="订单管理-编辑")
	@PutMapping(value = "/edit")
	public Result<?> edit(@RequestBody PkOrder pkOrder) {
		pkOrderService.updateById(pkOrder);
		return Result.ok("编辑成功!");
	}
	
	/**
	 *   通过id删除
	 *
	 * @param id
	 * @return
	 */
	@AutoLog(value = "订单管理-通过id删除")
	@ApiOperation(value="订单管理-通过id删除", notes="订单管理-通过id删除")
	@DeleteMapping(value = "/delete")
	public Result<?> delete(@RequestParam(name="id",required=true) String id) {
		pkOrderService.removeById(id);
		return Result.ok("删除成功!");
	}
	
	/**
	 *  批量删除
	 *
	 * @param ids
	 * @return
	 */
	@AutoLog(value = "订单管理-批量删除")
	@ApiOperation(value="订单管理-批量删除", notes="订单管理-批量删除")
	@DeleteMapping(value = "/deleteBatch")
	public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
		this.pkOrderService.removeByIds(Arrays.asList(ids.split(",")));
		return Result.ok("批量删除成功!");
	}
	
	

    /**
    * 导出excel
    *
    * @param request
    * @param pkTravelTip
    */
    @RequestMapping(value = "/exportXls")
    public ModelAndView exportXls(HttpServletRequest request, PkOrder pkOrder) {
        return super.exportXls(request, pkOrder, PkOrder.class, "订单管理");
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
        return super.importExcel(request, response, PkOrder.class);
    }
	
}
