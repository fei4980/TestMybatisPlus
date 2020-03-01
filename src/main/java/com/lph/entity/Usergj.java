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
 * ����mp�߼���������
 * @author 86152
 *
 */
@Data
@EqualsAndHashCode(callSuper=false)
public class Usergj extends Model<Usergj>{

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
  //�����Զ���䣬�����ǲ���ʱ�Զ���䣬����ĸ���ʱ
  @TableField(fill=FieldFill.INSERT)
  private LocalDateTime createTime;
  @TableField(fill=FieldFill.UPDATE)
  private LocalDateTime updateTime;
  @Version
  private Integer version;
  //�Ӵ�ע�⣬remark�ֶβ���������
  @TableField(exist=false)
  private String remark;
  //TableLogicע���߼�ɾ����ʶ, @TableField(select=false)��ѯʱ����Ҫ�������
  @TableLogic
  @TableField(select=false)
  private Integer deleted;
}
