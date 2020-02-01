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
 * AR模式
 * AR模式是让实体类继承Model，使得实体类对象可以直接操作增删改查
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {
	
	/**
	 * 通过AR实体对象直接调用插入，返回boolean类型
	 */
	@Test
	public void insert() {
		User user = new User();
		user.setName("张草");
		user.setAge(24);
		user.setEmail("zc@baomidou.com");
		user.setManagerId(1088248166370832385L);
		user.setCreateTime(LocalDateTime.now());
		boolean insert = user.insert();
		System.out.println(insert);
	}
	/**
	 * 通过AR实体对象直接调用查询
	 */
	@Test
	public void selectById() {
		User user = new User();
		User selectById = user.selectById(1094592041087729667L);
		System.out.println(selectById);
	}
	@Test
	public void selectById2() {
		User user = new User();
		user.setId(1094592041087729667L);
		User selectById = user.selectById();
		System.out.println(selectById);
	}
	/**
	 * 根据id进行更新操作，返回boolean类型
	 */
	@Test
	public void updateById() {
		User user = new User();
		user.setId(1094592041087729667L);
		user.setName("张草update");
		boolean updateById = user.updateById();
		System.out.println(updateById);
	}
	/**
	 * insertOrUpdate
	 * 根据主键如id进行查找，如果有就更新，没用就添加
	 */
	@Test
	public void insertOrUpdate() {
		User user = new User();
		user.setId(1094592041087729667L);
		user.setName("张强");
		user.setEmail("zq@baomidou.com");
		user.setManagerId(1088248166370832385L);
		user.setCreateTime(LocalDateTime.now());
		boolean updateById = user.insertOrUpdate();
		System.out.println(updateById);
	}
	//----还有很多别的，和BaseMapper一样，可以查看Model.class
}
