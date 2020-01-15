package com.lph;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lph.dao.UserMapper;
import com.lph.entity.User;

@RunWith(SpringRunner.class)
@SpringBootTest
public class InsertTest {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void insert() {
		User user = new User();
		user.setName("ÁõÃ÷Ç¿");
		user.setAge(31);
		user.setManagerId(12312312323L);
		user.setCreateTime(LocalDateTime.now());
		int rows = userMapper.insert(user);
		System.out.println(rows);
		
	}
}
