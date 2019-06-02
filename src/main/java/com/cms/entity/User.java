package com.cms.entity;

import java.io.Serializable;
import java.util.Date;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import com.alibaba.fastjson.annotation.JSONField;

import javax.persistence.Table;

@Data
@Table(name = "c_user")
public class User implements Serializable{//至于实现接口与否，不影响实现
	@Excel(name="编号")
	private int userId;
	@Excel(name="电话号码")
	private String phone;
	@Excel(name="密码")
	private String password;
	@Excel(name="名字")
	private String name;
	@Excel(name="头像地址")
	private String headPath;
	@Excel(name="法名")
	private String dharma;
	@Excel(name="性别")
	private String gender;
	@Excel(name="省份")
	private String province;
	@Excel(name="城市")
	private String city;
	@Excel(name="标签")
	private String sign;
	@Excel(name="状态")
	private int status;
	@Excel(name="注册时间")
	@JSONField(format="yyyy-MM-dd")//ajax输出
	@DateTimeFormat(pattern="yyyy-MM-dd")//输入
	private Date registerDate;
	@Excel(name="加密编码")
	private String salt;
	
}
