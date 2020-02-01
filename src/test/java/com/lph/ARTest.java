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
 * ARģʽ
 * ARģʽ����ʵ����̳�Model��ʹ��ʵ����������ֱ�Ӳ�����ɾ�Ĳ�
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ARTest {
	
	/**
	 * ͨ��ARʵ�����ֱ�ӵ��ò��룬����boolean����
	 */
	@Test
	public void insert() {
		User user = new User();
		user.setName("�Ų�");
		user.setAge(24);
		user.setEmail("zc@baomidou.com");
		user.setManagerId(1088248166370832385L);
		user.setCreateTime(LocalDateTime.now());
		boolean insert = user.insert();
		System.out.println(insert);
	}
	/**
	 * ͨ��ARʵ�����ֱ�ӵ��ò�ѯ
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
	 * ����id���и��²���������boolean����
	 */
	@Test
	public void updateById() {
		User user = new User();
		user.setId(1094592041087729667L);
		user.setName("�Ų�update");
		boolean updateById = user.updateById();
		System.out.println(updateById);
	}
	/**
	 * insertOrUpdate
	 * ����������id���в��ң�����о͸��£�û�þ����
	 */
	@Test
	public void insertOrUpdate() {
		User user = new User();
		user.setId(1094592041087729667L);
		user.setName("��ǿ");
		user.setEmail("zq@baomidou.com");
		user.setManagerId(1088248166370832385L);
		user.setCreateTime(LocalDateTime.now());
		boolean updateById = user.insertOrUpdate();
		System.out.println(updateById);
	}
	//----���кܶ��ģ���BaseMapperһ�������Բ鿴Model.class
}
