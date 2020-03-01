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
import com.lph.entity.Usergj;

public interface UsergjMapper extends BaseMapper<Usergj>{
	//�Զ���sql   ${ew.customSqlSegment}�ǹ̶�д�������ڶ�̬ƴ�Ӵ���
	@Select("select * from usergj ${ew.customSqlSegment}")
	List<Usergj> mySelectList(@Param(Constants.WRAPPER) Wrapper<Usergj> wrapper);
}
