package com.lph.entity;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class User {
  //id
  private long id;
  private String name;
  private Integer age;
  private String email;
  private long managerId;
  private LocalDateTime createTime;
  
}
