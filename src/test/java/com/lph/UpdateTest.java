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
 * ����update���²���
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UpdateTest {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * updateById��id����update
	 */
	@Test
	public void updateById() {
		User user = new User();
		user.setId(1088248166370832385L);
		user.setAge(26);
		user.setEmail("wtf2@baomidou.com");
		//����Id����update
		int rows = userMapper.updateById(user);
		System.out.println("Ӱ���¼��:"+rows);
	}
	
	/**
	 * update ͨ��UpdateWrapper��ѯ����update
	 */
	@Test
	public void updateByWrapper() {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		updateWrapper.eq("name", "����ΰ").eq("age", 28);
		User user = new User();
		user.setAge(29);
		user.setEmail("lyw2019@baomidou.com");
		//��ͨ����Ӳ��ҵ���Ȼ���޸�
		int rows = userMapper.update(user,updateWrapper);
		System.out.println("Ӱ���¼��:"+rows);
	}
	
	/**
	 * update ͨ��UpdateWrapper��ѯ����update
	 * ���ʵ���ֶ�̫�࣬�����µ����ԱϾ��٣������Ż�ֵ�ù�����set������ʵ��
	 * �磺�����Ȳ��ң�Ȼ��������޸ĳ�29
	 */
	@Test
	public void updateByWrapper2() {
		UpdateWrapper<User> updateWrapper = new UpdateWrapper<User>();
		updateWrapper.eq("name", "����ΰ").eq("age", 28).set("age", 29);

		//��ͨ����Ӳ��ҵ���Ȼ���޸�
		int rows = userMapper.update(null,updateWrapper);
		System.out.println("Ӱ���¼��:"+rows);
	}
	
	/**
	 * ʹ��lambda�����������Է�ֹ��д
	 * ���󣺲��ҵ�����ΰ����30��ģ��޸ĳ�31��
	 */
	@Test
	public void updateByWrapperLambda() {
		 LambdaUpdateWrapper<User> lambdaUpdate = Wrappers.<User>lambdaUpdate();
		 lambdaUpdate.eq(User::getName, "����ΰ").eq(User::getAge,30).set(User::getAge, 31);
		 

		//��ͨ����Ӳ��ҵ���Ȼ���޸�
		int rows = userMapper.update(null,lambdaUpdate);
		System.out.println("Ӱ���¼��:"+rows);
	}
	
	/**
	 * LambdaUpdateChainWrapperд��������boolean����
	 * ���󣺲��ҵ�����ΰ����30��ģ��޸ĳ�31��
	 */
	@Test
	public void updateByWrapperLambdaChain() {
		boolean update = new LambdaUpdateChainWrapper<User>(userMapper)
		 .eq(User::getName, "����ΰ").eq(User::getAge,30).set(User::getAge, 31).update();

		System.out.println("Ӱ���¼��:"+update);
	}
}
