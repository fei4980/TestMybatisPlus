package com.lph;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lph.dao.UserMapper;
import com.lph.entity.User;
/**
 * 测试插入一行数据
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void insert() {
		User user = new User();
		user.setRealName("刘明强");
		user.setAge(31);
		user.setManagerId(1087982257332887553L);
		user.setCreateTime(LocalDateTime.now());
		int rows = userMapper.insert(user);
		System.out.println(rows);
		
	}
}
