package com.lph.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
/**
 * 用于mp高级基础测试
 * @author 86152
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Usergj extends Model<Usergj>{

 private static final long serialVersionUID = 1L;
//id 主键Mybatis默认认为id字段就是主键，但如果表中主键不是叫id，就要用此注解告诉库了，这里不加也行
  @TableId
  private long id;
  //姓名,realName对应库里的name字段
  //@TableField("name")
  //private String realName;
  private String name;
  private Integer age;
  private String email;
  private long managerId;
  //设置自动填充，这里是插入时自动填充，下面的更新时
  @TableField(fill=FieldFill.INSERT)
  private LocalDateTime createTime;
  @TableField(fill=FieldFill.UPDATE)
  private LocalDateTime updateTime;
  @Version
  private Integer version;
  //加此注解，remark字段不参与库操作
  @TableField(exist=false)
  private String remark;
  //TableLogic注解逻辑删除标识, @TableField(select=false)查询时不需要查此属性
  @TableLogic
  @TableField(select=false)
  private Integer deleted;
}
