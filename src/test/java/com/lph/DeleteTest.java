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
 * ����ɾ������
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class DeleteTest {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * ���Ը���id����ɾ��������Ӱ���¼��
	 */
	@Test
	public void deleteById() {
		int rows = userMapper.deleteById(1104221411195232258L);
		System.out.println(rows);
	}
	
	/**
	 * ����deleteByMap,���ݶ�����ɾ��������Ӱ������
	 * ����ɾ������Ϊ���������Ϊ25�ļ�¼
	 */
	@Test
	public void deleteByMap() {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("name", "���");
		hashMap.put("age", "25");
		
		int rows = userMapper.deleteByMap(hashMap);
		System.out.println(rows);
	}
	
	/**
	 * ����deleteBatchIds,����id����ɾ��
	 */
	@Test
	public void deleteBatchIds() {
		Map<String, Object> hashMap = new HashMap<String,Object>();
		hashMap.put("name", "���");
		hashMap.put("age", "25");
		List arrayList = new ArrayList();
		arrayList.add(1104220508505546754L);
		arrayList.add(1104216373458722818L);
		int rows = userMapper.deleteBatchIds(arrayList);
		System.out.println(rows);
	}
	
	/**
	 * ���Դ���ѯ������QueryWrapper��ɾ��
	 * ���󣺰ѽ������겢��41���ɾ��
	 */
	@Test
	public void deleteByWrapper() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.eq("name", "������").eq("age", 41);
		
		int rows = userMapper.delete(queryWrapper);//����ɾ��������
		System.out.println(rows);
	}
	
	/**
	 * ���Դ���ѯ������LambdaQueryWrapper��ɾ��
	 * ����ɾ���������27�����������41������
	 */
	@Test
	public void deleteByWrapperLambda() {
		LambdaQueryWrapper<User> queryWrapper = Wrappers.<User>lambdaQuery();
		queryWrapper.eq(User::getAge,27).or().gt(User::getAge, 41);
		
		int rows = userMapper.delete(queryWrapper);//����ɾ��������
		System.out.println(rows);
	}
}
