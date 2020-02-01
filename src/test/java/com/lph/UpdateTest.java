package com.lph;

import java.time.LocalDateTime;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.additional.update.impl.LambdaUpdateChainWrapper;
import com.lph.dao.UserMapper;
import com.lph.entity.User;
/**
 * 测试update更新操作
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * updateById按id进行update
	 */
	@Test
	public void updateById() {
		User user = new User();
		user.setId(1088248166370832385L);
		user.setAge(26);
		user.setEmail("wtf2@baomidou.com");
		//根据Id进行update
		int rows = userMapper.updateById(user);
		System.out.println("影响记录数:"+rows);
	}
	
	/**
	 * update 通过UpdateWrapper查询到再update
	 */
	@Test
	public void updateByWrapper() {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		updateWrapper.eq("name", "李艺伟").eq("age", 28);
		User user = new User();
		user.setAge(29);
		user.setEmail("lyw2019@baomidou.com");
		//先通过添加查找到，然后修改
		int rows = userMapper.update(user,updateWrapper);
		System.out.println("影响记录数:"+rows);
	}
	
	/**
	 * update 通过UpdateWrapper查询到再update
	 * 如果实体字段太多，而更新的属性毕竟少，可以优化值用构造器set，不用实体
	 * 如：这里先查找，然后把年龄修改成29
	 */
	@Test
	public void updateByWrapper2() {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		updateWrapper.eq("name", "李艺伟").eq("age", 28).set("age", 29);

		//先通过添加查找到，然后修改
		int rows = userMapper.update(null,updateWrapper);
		System.out.println("影响记录数:"+rows);
	}
	
	/**
	 * 使用lambda构造器，可以防止误写
	 * 需求：查找到李艺伟并且30岁的，修改成31岁
	 */
	@Test
	public void updateByWrapperLambda() {
		 LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.<User>lambdaUpdate();
		 lambdaUpdate.eq(User::getName, "李艺伟").eq(User::getAge,30).set(User::getAge, 31);
		 

		//先通过添加查找到，然后修改
		int rows = userMapper.update(null,lambdaUpdate);
		System.out.println("影响记录数:"+rows);
	}
	
	/**
	 * LambdaUpdateChainWrapper写法，返回boolean类型
	 * 需求：查找到李艺伟并且30岁的，修改成31岁
	 */
	@Test
	public void updateByWrapperLambdaChain() {
		boolean update = new LambdaUpdateChainWrapper<User>(userMapper)
		 .eq(User::getName, "李艺伟").eq(User::getAge,30).set(User::getAge, 31).update();

		System.out.println("影响记录数:"+update);
	}
}
