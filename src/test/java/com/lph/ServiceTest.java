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
		//������������ĳ��������ᱨ���ڶ�����������Ϊfalse���Ͳ��ᱨ���ˣ�ֻ���ص�һ��
		User one = userService.getOne(Wrappers.<User>lambdaQuery().gt(User::getAge,25),false);
		System.out.println(one);
	}
	
	/**
	 * ������������saveBatch�Լ�saveOrUpdateBatch
	 */
	@Test
	public void Batch() {
		User user = new User();
		user.setName("����3");
		user.setAge(28);
		
		User user1 = new User();
		user1.setId(123456L);
		user1.setName("����");
		user1.setAge(30);
		
		List<User> asList = new ArrayList<User>();
		asList.add(user);
		asList.add(user1);
		boolean saveBatch = userService.saveBatch(asList);//���뱣��
		//saveOrUpdateBatch,�Ȳ�ѯ�Ƿ���ڣ��о͸��£�û�þͲ���
		//boolean saveOrUpdateBatch = userService.saveOrUpdateBatch(asList);
		
		System.out.println(saveBatch);
	}
	
	/**
	 * ʹ��lambda��ѯ��������lambdaQuery
	 */
	@Test
	public void lambdaChain() {
		//��ѯ�������25�����ִ����
		List<User> userList = userService.lambdaQuery().gt(User::getAge, 25).like(User::getName, "��").list();
		System.out.println(userList);
	}
	
	/**
	 * ʹ��lambda��ѯ������:lambdaUpdate
	 * 
	 */
	@Test
	public void lambdaChain2() {
		//��ѯ����25��ģ��޸ĳ�26�꣬������Ӱ������
		boolean update = userService.lambdaUpdate().eq(User::getAge, 25).set(User::getAge, 26).update();
		System.out.println(update);
	}
	
	/**
	 * lambdaUpdate�����Բ�ѯ����ִ��ɾ������
	 * 
	 */
	@Test
	public void lambdaChain3() {
		//��ѯ25��ģ�ɾ��������Ӱ������
		boolean update = userService.lambdaUpdate().eq(User::getAge, 24).remove();
		System.out.println(update);
	}
}
