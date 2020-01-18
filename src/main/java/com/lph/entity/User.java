package com.lph.entity;

import java.time.LocalDateTime;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;

import lombok.Data;
@Data
public class User {
  //id ����MybatisĬ����Ϊid�ֶξ�������������������������ǽ�id����Ҫ�ô�ע����߿��ˣ����ﲻ��Ҳ��
  @TableId
  private long id;
  //����,��Ӧ�����name�ֶ�
  @TableField("name")
  private String realName;
  private Integer age;
  private String email;
  private long managerId;
  private LocalDateTime createTime;
  //�Ӵ�ע�⣬remark�ֶβ���������
  @TableField(exist=false)
  private String remark;
}
