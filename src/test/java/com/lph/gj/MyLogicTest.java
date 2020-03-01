package com.lph.gj;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.lph.dao.UsergjMapper;
import com.lph.entity.User;
import com.lph.entity.Usergj;
/**
 * 测试乐观锁
 * version字段的类型只支持如上
     支持的mapper方法只有：updateById和update
     更新操作不能连续复用执行
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyLogicTest {
	
	@Autowired
	private UsergjMapper usergjMapper;
	

	/**
	 * 测试乐观锁更新
	 */
	@Test
	public void mySelect() {
	   
		Usergj user = usergjMapper.selectById(1094592041087729666L);
		user.setAge(99);
		int rows = usergjMapper.updateById(user);//更新操作，成功version+1
		System.out.println(rows);//修改成功返回：1,如果version发生了改变，就会更新失败，返回0
		
		//user.setAge(20);
		//int rows2 = usergjMapper.updateById(user);//注意不能复用！，连续的updateById会报错
		//System.out.println(rows2);//返回0，更新失败
	}
}
