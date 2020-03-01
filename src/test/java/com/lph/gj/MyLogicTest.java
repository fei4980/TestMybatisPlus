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
 * �����ֹ���
 * version�ֶε�����ֻ֧������
     ֧�ֵ�mapper����ֻ�У�updateById��update
     ���²���������������ִ��
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyLogicTest {
	
	@Autowired
	private UsergjMapper usergjMapper;
	

	/**
	 * �����ֹ�������
	 */
	@Test
	public void mySelect() {
	   
		Usergj user = usergjMapper.selectById(1094592041087729666L);
		user.setAge(99);
		int rows = usergjMapper.updateById(user);//���²������ɹ�version+1
		System.out.println(rows);//�޸ĳɹ����أ�1,���version�����˸ı䣬�ͻ����ʧ�ܣ�����0
		
		//user.setAge(20);
		//int rows2 = usergjMapper.updateById(user);//ע�ⲻ�ܸ��ã���������updateById�ᱨ��
		//System.out.println(rows2);//����0������ʧ��
	}
}
