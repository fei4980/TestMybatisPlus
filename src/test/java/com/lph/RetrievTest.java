package com.lph;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lph.dao.UserMapper;
import com.lph.entity.User;
/**
 * 测试mapper中的查询用法
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetrievTest {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 按id查询 selectById用法
	 */
	@Test
	public void selectById() {
		long id = 1088248166370832385L;
		User selectById = userMapper.selectById(id);
		System.out.println(selectById);
	}
	
	/**
	 * 用id集合查询 selectBatchIds用法
	 * 返回List结果集
	 */
	@Test
	public void selectids() {
		List<Long> list = new ArrayList<Long>();
		list.add(1087982257332887553L);
		list.add(1088250446457389058L);
		list.add(1088248166370832385L);
		List<User> userList = userMapper.selectBatchIds(list);
		for(User user : userList) {
			System.out.println(user);
		}
	}
	
	/**
	 * 多条件查询 selectByMap用法
	 * 返回List结果集
	 */
	@Test
	public void selectByMap() {
		HashMap<String, Object> map = new HashMap<String,Object>();
		//要符合这两个条件
		map.put("name", "王天风");
		map.put("age", 25);
		//where name = "王天风" and age = 30
		List<User> selectByMap = userMapper.selectByMap(map);
		for(User user : selectByMap) {
			System.out.println(user);
		}
	}
	
	/**
	 * 查询集合 selectList用法
	 * 需求：名字中包含雨并且年龄小于40的数据
	 * name like '%雨%' and age < 40
	 * 返回List结果集
	 */
	@Test
	public void selectList() {
		//QueryWrapper是条件构造器
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "雨").lt("age", 40);
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * 查询集合 selectList用法
	 * 需求：名字中包含雨并且年龄大于等于20且小于等于40并且email不为空
	 * name like '%雨%' and age between 20 and 40 and email is not null
	 * 返回List结果集
	 */
	@Test
	public void selectList2() {
		//QueryWrapper是条件构造器
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * 查询集合 selectList用法
	 * 需求：名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
	 * name like '王%' or age >= 25 order by age desc,id asc
	 * name like '%王%' and age between 20 and 40 and email is not null
	 * 返回List结果集
	 */
	@Test
	public void selectList3() {
		//QueryWrapper是条件构造器,默认是and连接，加个or()就会变成or
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.likeRight("name","王").or().ge("age", 25).orderByDesc("age")
						.orderByAsc("id");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
}
