package com.lph.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
@Data
public class User {
  //id 主键Mybatis默认认为id字段就是主键，但如果表中主键不是叫id，就要用此注解告诉库了，这里不加也行
  @TableId
  private long id;
  //姓名,对应库里的name字段
  @TableField("name")
  private String realName;
  private Integer age;
  private String email;
  private long managerId;
  private LocalDateTime createTime;
  //加此注解，remark字段不参与库操作
  @TableField(exist=false)
  private String remark;
}
