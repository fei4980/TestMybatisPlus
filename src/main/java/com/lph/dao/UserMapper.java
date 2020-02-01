package com.lph.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lph.entity.User;

public interface UserMapper extends BaseMapper<User>{
	//自定义sql   ${ew.customSqlSegment}是固定写法，用于动态拼接传参
	@Select("select * from user ${ew.customSqlSegment}")
	List<User> selectAll(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
	
	//用传统xml的形式
	List<User> selectAllByXml(@Param(Constants.WRAPPER) Wrapper<User> wrapper);
	
	//自定义sql也可以加入分页
	IPage<User> selectUserPage(Page<User> page,@Param(Constants.WRAPPER) Wrapper<User> wrapper);
}
