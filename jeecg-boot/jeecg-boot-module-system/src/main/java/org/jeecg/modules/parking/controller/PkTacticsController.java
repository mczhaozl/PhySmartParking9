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
import org.jeecg.modules.parking.entity.PkTactics;
import org.jeecg.modules.parking.service.IPkTacticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
* @Description: 收费策略
* @Author: jeecg-boot
* @Date:   2020-07-11
* @Version: V1.0
*/


@Api(tags="收费策略")
@RestController
@RequestMapping("/parking/PkTactics")
@Slf4j
public class PkTacticsController extends JeecgController<PkTactics, IPkTacticsService> {
   @Autowired
   private IPkTacticsService pkTacticsService;

   /**
    * 分页列表查询
    *
    * @param PkTactics
    * @param pageNo
    * @param pageSize
    * @param req
    * @return
    */
   @AutoLog(value = "收费策略-分页列表查询")
   @ApiOperation(value="收费策略-分页列表查询", notes="收费策略-分页列表查询")
   @GetMapping(value = "/list")
   public Result<?> queryPageList(PkTactics PkTactics,
                                  @RequestParam(name="pageNo", defaultValue="1") Integer pageNo,
                                  @RequestParam(name="pageSize", defaultValue="10") Integer pageSize,
                                  HttpServletRequest req) {
       QueryWrapper<PkTactics> queryWrapper = QueryGenerator.initQueryWrapper(PkTactics, req.getParameterMap());
       Page<PkTactics> page = new Page<PkTactics>(pageNo, pageSize);
       IPage<PkTactics> pageList = pkTacticsService.page(page, queryWrapper);
       return Result.ok(pageList);
   }

   /**
    *   添加
    *
    * @param PkTactics
    * @return
    */
   @AutoLog(value = "收费策略-添加")
   @ApiOperation(value="收费策略-添加", notes="收费策略-添加")
   @PostMapping(value = "/add")
   public Result<?> add(@RequestBody PkTactics PkTactics) {
       pkTacticsService.save(PkTactics);
       return Result.ok("添加成功！");
   }

   /**
    *  编辑
    *
    * @param PkTactics
    * @return
    */
   @AutoLog(value = "收费策略-编辑")
   @ApiOperation(value="收费策略-编辑", notes="收费策略-编辑")
   @PutMapping(value = "/edit")
   public Result<?> edit(@RequestBody PkTactics PkTactics) {
       pkTacticsService.updateById(PkTactics);
       return Result.ok("编辑成功!");
   }

   /**
    *   通过id删除
    *
    * @param id
    * @return
    */
   @AutoLog(value = "收费策略-通过id删除")
   @ApiOperation(value="收费策略-通过id删除", notes="收费策略-通过id删除")
   @DeleteMapping(value = "/delete")
   public Result<?> delete(@RequestParam(name="id",required=true) String id) {
       pkTacticsService.removeById(id);
       return Result.ok("删除成功!");
   }

   /**
    *  批量删除
    *
    * @param ids
    * @return
    */
   @AutoLog(value = "收费策略-批量删除")
   @ApiOperation(value="收费策略-批量删除", notes="收费策略-批量删除")
   @DeleteMapping(value = "/deleteBatch")
   public Result<?> deleteBatch(@RequestParam(name="ids",required=true) String ids) {
       this.pkTacticsService.removeByIds(Arrays.asList(ids.split(",")));
       return Result.ok("批量删除成功!");
   }

   /**
    * 通过id查询
    *
    * @param id
    * @return
    */
   @AutoLog(value = "收费策略-通过id查询")
   @ApiOperation(value="收费策略-通过id查询", notes="收费策略-通过id查询")
   @GetMapping(value = "/queryById")
   public Result<?> queryById(@RequestParam(name="id",required=true) String id) {
       PkTactics PkTactics = pkTacticsService.getById(id);
       if(PkTactics==null) {
           return Result.error("未找到对应数据");
       }
       return Result.ok(PkTactics);
   }

   /**
   * 导出excel
   *
   * @param request
   * @param PkTactics
   */
   @RequestMapping(value = "/exportXls")
   public ModelAndView exportXls(HttpServletRequest request, PkTactics PkTactics) {
       return super.exportXls(request, PkTactics, PkTactics.class, "收费策略");
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
       return super.importExcel(request, response, PkTactics.class);
   }

}
