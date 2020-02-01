package com.lph;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lph.dao.UserMapper;
import com.lph.entity.User;
/**
 * 测试删除操作
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 测试根据id进行删除，返回影响记录数
	 */
	@Test
	public void deleteById() {
		int rows = userMapper.deleteById(1104221411195232258L);
		System.out.println(rows);
	}
	
	/**
	 * 测试deleteByMap,根据多条件删除，返回影响条数
	 * 需求：删除名字为向后并且年龄为25的记录
	 */
	@Test
	public void deleteByMap() {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("name", "向后");
		hashMap.put("age", "25");
		
		int rows = userMapper.deleteByMap(hashMap);
		System.out.println(rows);
	}
	
	/**
	 * 测试deleteBatchIds,根据id批量删除
	 */
	@Test
	public void deleteBatchIds() {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("name", "向后");
		hashMap.put("age", "25");
		List arrayList = new ArrayList();
		arrayList.add(1104220508505546754L);
		arrayList.add(1104216373458722818L);
		int rows = userMapper.deleteBatchIds(arrayList);
		System.out.println(rows);
	}
	
	/**
	 * 测试带查询构造器QueryWrapper的删除
	 * 需求：把叫刘红雨并且41岁的删掉
	 */
	@Test
	public void deleteByWrapper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("name", "刘红雨").eq("age", 41);
		
		int rows = userMapper.delete(queryWrapper);//返回删除的条数
		System.out.println(rows);
	}
	
	/**
	 * 测试带查询构造器LambdaQueryWrapper的删除
	 * 需求：删除年龄等于27或者年龄大于41的数据
	 */
	@Test
	public void deleteByWrapperLambda() {
		LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery();
		queryWrapper.eq(User::getAge,27).or().gt(User::getAge, 41);
		
		int rows = userMapper.delete(queryWrapper);//返回删除的条数
		System.out.println(rows);
	}
}
