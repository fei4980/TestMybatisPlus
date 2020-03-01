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
 * 测试逻辑删除及注意事项
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyVersionTest {
	
	@Autowired
	private UsergjMapper usergjMapper;
	
	/**
	 * 按id假删除,返回影响记录数
	 */
	@Test
	public void deleteById() {
		int deleteById = usergjMapper.deleteById(1094592041087729666L);
		System.out.println(deleteById);
	}
	
	/**
	 * 这是再查询就不会再查到被逻辑删除的内容了，会自动添加条件
	 * where deleted = 0
	 */
	@Test
	public void select() {
		List<Usergj> selectList = usergjMapper.selectList(null);
		System.out.println(selectList);
	}
	
	// 并且更新操作也只能更新逻辑未删除的
	
	/**
	 * 测试更新已经逻辑删除的内容，返回影响记录数为0，因为自动添加了条件
	 * 	 * 这是再查询就不会再查到被逻辑删除的内容了，会自动添加条件
	 * where deleted = 0
	 */
	@Test
	public void updateById() {
		Usergj usergj = new Usergj();
		usergj.setAge(26);
		usergj.setId(1094592041087729666L);
		int updateById = usergjMapper.updateById(usergj);
		System.out.println(updateById);//0，已被删除，找不到了
	}
	
	/**
	 * 测试自定义查询（mapper里自己加的），大于25岁的
	 * 发现自定义查询会把逻辑已删除的查出来
	 * 
	 */
	@Test
	public void mySelect() {
		//会把已删除的查出来
		List<Usergj> mySelectList = usergjMapper.mySelectList(Wrappers.<Usergj>lambdaQuery()
				.gt(Usergj::getAge,25));
		
		//解决办法一：增加条件(deleted为0的)
		List<Usergj> mySelectList2 = usergjMapper.mySelectList(Wrappers.<Usergj>lambdaQuery()
				.gt(Usergj::getAge,25).eq(Usergj::getDeleted, 0));
		
		
		for(Usergj user : mySelectList) {
			System.out.println(user);
		}
	}
}
