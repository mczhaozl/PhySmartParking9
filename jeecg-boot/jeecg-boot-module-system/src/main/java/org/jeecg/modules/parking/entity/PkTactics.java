package org.jeecg.modules.parking.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

@Data
@TableName("pk_tactics")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="pk_tactics对象", description="收费策略")
public class PkTactics {
    private static final long serialVersionUID = 1L;

    /**主键*/
    @TableId(type = IdType.ID_WORKER_STR)
    @ApiModelProperty(value = "主键")
    private java.lang.String id;
    /**创建人*/
    @ApiModelProperty(value = "创建人")
    private java.lang.String createBy;
    /**创建日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "创建日期")
    private java.util.Date createTime;
    /**更新人*/
    @ApiModelProperty(value = "更新人")
    private java.lang.String updateBy;
    /**更新日期*/
    @JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "更新日期")
    private java.util.Date updateTime;

    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;

    @Excel(name = "开始时间", width = 15)
    @ApiModelProperty(value = "开始时间")
    private java.lang.String startTime;
    /**结束时间*/
    @Excel(name = "结束时间", width = 15)
    @ApiModelProperty(value = "结束时间")
    private java.lang.String endTime;

    @Excel(name = "所属地区", width = 15)
    @ApiModelProperty(value = "所属地区")
    private java.lang.String area;
    /**预约人ID*/
    @Excel(name = "停车类型", width = 15)
    @Dict(dicCode = "parking_lot_type")
    @ApiModelProperty(value = "停车类型")
    private java.lang.String type;
    @Excel(name = "价格", width = 15)
    @ApiModelProperty(value = "价格")
    private java.lang.String price;

}

