package com.lph;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.util.Arrays;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.query.impl.LambdaQueryChainWrapper;
import com.lph.entity.User;
import com.lph.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void getOne() {
		//如果符合条件的超过两个会报错，第二个参数设置为false，就不会报错了，只返回第一个
		User one = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge,25),false);
		System.out.println(one);
	}
	
	/**
	 * 测试批量插入saveBatch以及saveOrUpdateBatch
	 */
	@Test
	public void Batch() {
		User user = new User();
		user.setName("徐丽3");
		user.setAge(28);
		
		User user1 = new User();
		user1.setId(123456L);
		user1.setName("蓄力");
		user1.setAge(30);
		
		List<User> asList = new ArrayList<User>();
		asList.add(user);
		asList.add(user1);
		boolean saveBatch = userService.saveBatch(asList);//插入保存
		//saveOrUpdateBatch,先查询是否存在，有就更新，没用就插入
		//boolean saveOrUpdateBatch = userService.saveOrUpdateBatch(asList);
		
		System.out.println(saveBatch);
	}
	
	/**
	 * 使用lambda查询构造器：lambdaQuery
	 */
	@Test
	public void lambdaChain() {
		//查询年龄大于25；名字带雨的
		List<User> userList = userService.lambdaQuery().gt(User::getAge, 25).like(User::getName, "雨").list();
		System.out.println(userList);
	}
	
	/**
	 * 使用lambda查询构造器:lambdaUpdate
	 * 
	 */
	@Test
	public void lambdaChain2() {
		//查询年龄25岁的，修改成26岁，并返回影响条数
		boolean update = userService.lambdaUpdate().eq(User::getAge, 25).set(User::getAge, 26).update();
		System.out.println(update);
	}
	
	/**
	 * lambdaUpdate还可以查询到再执行删除操作
	 * 
	 */
	@Test
	public void lambdaChain3() {
		//查询25岁的，删除，返回影响条数
		boolean update = userService.lambdaUpdate().eq(User::getAge, 24).remove();
		System.out.println(update);
	}
}
