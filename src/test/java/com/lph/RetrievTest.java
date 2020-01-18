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
 * ����mapper�еĲ�ѯ�÷�
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetrievTest {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * ��id��ѯ selectById�÷�
	 */
	@Test
	public void selectById() {
		long id = 1088248166370832385L;
		User selectById = userMapper.selectById(id);
		System.out.println(selectById);
	}
	
	/**
	 * ��id���ϲ�ѯ selectBatchIds�÷�
	 * ����List�����
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
	 * ��������ѯ selectByMap�÷�
	 * ����List�����
	 */
	@Test
	public void selectByMap() {
		HashMap<String, Object> map = new HashMap<String,Object>();
		//Ҫ��������������
		map.put("name", "�����");
		map.put("age", 25);
		//where name = "�����" and age = 30
		List<User> selectByMap = userMapper.selectByMap(map);
		for(User user : selectByMap) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��ѯ���� selectList�÷�
	 * ���������а����겢������С��40������
	 * name like '%��%' and age < 40
	 * ����List�����
	 */
	@Test
	public void selectList() {
		//QueryWrapper������������
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "��").lt("age", 40);
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��ѯ���� selectList�÷�
	 * ���������а����겢��������ڵ���20��С�ڵ���40����email��Ϊ��
	 * name like '%��%' and age between 20 and 40 and email is not null
	 * ����List�����
	 */
	@Test
	public void selectList2() {
		//QueryWrapper������������
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "��").between("age", 20, 40).isNotNull("email");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��ѯ���� selectList�÷�
	 * ��������Ϊ���ջ���������ڵ���25���������併�����У�������ͬ����id��������
	 * name like '��%' or age >= 25 order by age desc,id asc
	 * name like '%��%' and age between 20 and 40 and email is not null
	 * ����List�����
	 */
	@Test
	public void selectList3() {
		//QueryWrapper������������,Ĭ����and���ӣ��Ӹ�or()�ͻ���or
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.likeRight("name","��").or().ge("age", 25).orderByDesc("age")
						.orderByAsc("id");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
}
