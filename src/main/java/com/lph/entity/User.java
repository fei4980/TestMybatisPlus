package com.lph.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;

import lombok.Data;
import lombok.EqualsAndHashCode;
@Data
@EqualsAndHashCode(callSuper=false)
public class User extends Model<User>{

 private static final long serialVersionUID = 1L;
//id ����MybatisĬ����Ϊid�ֶξ�������������������������ǽ�id����Ҫ�ô�ע����߿��ˣ����ﲻ��Ҳ��
  @TableId
  private long id;
  //����,realName��Ӧ�����name�ֶ�
  //@TableField("name")
  //private String realName;
  private String name;
  private Integer age;
  private String email;
  private long managerId;
  private LocalDateTime createTime;
  //�Ӵ�ע�⣬remark�ֶβ���������
  @TableField(exist=false)
  private String remark;
}
