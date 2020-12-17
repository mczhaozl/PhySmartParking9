package org.jeecg.modules.parking.entity;

import java.io.Serializable;

import org.jeecg.common.aspect.annotation.Dict;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.springframework.format.annotation.DateTimeFormat;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


@Data
@TableName("pk_order")
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = false)
@ApiModel(value="pk_order对象", description="订单")
public class PkOrder implements Serializable{
	
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
	/**所属部门*/
    @ApiModelProperty(value = "所属部门")
    private java.lang.String sysOrgCode;
	/**车位编号*/
	@Excel(name = "车位编号", width = 15)
    @ApiModelProperty(value = "车位编号")
    private java.lang.String lotNum;
	
	
	
	/**停车时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "停车时间")
    private java.util.Date parkTime;
	
	/**离开时间*/
	@JsonFormat(timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "离开时间")
    private java.util.Date leaveTime;
	
	
	/**停车时长*/
	@Excel(name = "停车时长", width = 15)
    @ApiModelProperty(value = "停车时长")
    private java.lang.String parkDuration;
	/**车牌号*/
	@Excel(name = "车牌号", width = 15)
    @ApiModelProperty(value = "车牌号")
    private java.lang.String carNumber;
	/**收费标准*/
	@Excel(name = "收费标准", width = 15)
    @ApiModelProperty(value = "收费标准")
    private java.lang.String feeScale;
	/**抓拍照片*/
	@Excel(name = "抓拍照片", width = 64)
    @ApiModelProperty(value = "抓拍照片")
    private java.lang.String photos;
	/**订单号*/
	@Excel(name = "订单号", width = 32)
    @ApiModelProperty(value = "订单号")
    private java.lang.String orderId;
	/**费用*/
	@Excel(name = "费用", width = 15)
    @ApiModelProperty(value = "费用")
    private java.lang.String cost;
	/**订单状态*/
	@Excel(name = "订单状态", width = 15)
    @ApiModelProperty(value = "订单状态")
	@Dict(dicCode = "order_status")
    private java.lang.String status;
	/**缴费状态*/
	@Excel(name = "缴费状态", width = 15)
    @ApiModelProperty(value = "缴费状态")
	@Dict(dicCode = "fee_status")
    private java.lang.String feeStatus;
	
	
	/**用户姓名*/
	@Excel(name = "用户姓名", width = 15)
    @ApiModelProperty(value = "用户姓名")
    private java.lang.String userName;
	/**用户id*/
	@Excel(name = "用户id", width = 15)
    @ApiModelProperty(value = "用户id")
    private java.lang.String userId;
	/**用户手机号码*/
	@Excel(name = "用户手机号码", width = 15)
    @ApiModelProperty(value = "用户手机号码")
    private java.lang.String phone;
	
	
}
