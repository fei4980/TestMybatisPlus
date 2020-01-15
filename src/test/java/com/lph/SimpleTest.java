package com.lph;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.lph.dao.UserMapper;
import com.lph.entity.User;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleTest {
	@Autowired
	private UserMapper userMapper;
	
	@Test
	public void select() {
		User user = new User();
		List<User> selectList = userMapper.selectList(null);
		System.out.println(selectList.size());
	}
}
