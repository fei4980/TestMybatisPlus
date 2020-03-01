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
 * �����߼�ɾ����ע������
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MyVersionTest {
	
	@Autowired
	private UsergjMapper usergjMapper;
	
	/**
	 * ��id��ɾ��,����Ӱ���¼��
	 */
	@Test
	public void deleteById() {
		int deleteById = usergjMapper.deleteById(1094592041087729666L);
		System.out.println(deleteById);
	}
	
	/**
	 * �����ٲ�ѯ�Ͳ����ٲ鵽���߼�ɾ���������ˣ����Զ��������
	 * where deleted = 0
	 */
	@Test
	public void select() {
		List<Usergj> selectList = usergjMapper.selectList(null);
		System.out.println(selectList);
	}
	
	// ���Ҹ��²���Ҳֻ�ܸ����߼�δɾ����
	
	/**
	 * ���Ը����Ѿ��߼�ɾ�������ݣ�����Ӱ���¼��Ϊ0����Ϊ�Զ����������
	 * 	 * �����ٲ�ѯ�Ͳ����ٲ鵽���߼�ɾ���������ˣ����Զ��������
	 * where deleted = 0
	 */
	@Test
	public void updateById() {
		Usergj usergj = new Usergj();
		usergj.setAge(26);
		usergj.setId(1094592041087729666L);
		int updateById = usergjMapper.updateById(usergj);
		System.out.println(updateById);//0���ѱ�ɾ�����Ҳ�����
	}
	
	/**
	 * �����Զ����ѯ��mapper���Լ��ӵģ�������25���
	 * �����Զ����ѯ����߼���ɾ���Ĳ����
	 * 
	 */
	@Test
	public void mySelect() {
		//�����ɾ���Ĳ����
		List<Usergj> mySelectList = usergjMapper.mySelectList(Wrappers.<Usergj>lambdaQuery()
				.gt(Usergj::getAge,25));
		
		//����취һ����������(deletedΪ0��)
		List<Usergj> mySelectList2 = usergjMapper.mySelectList(Wrappers.<Usergj>lambdaQuery()
				.gt(Usergj::getAge,25).eq(Usergj::getDeleted, 0));
		
		
		for(Usergj user : mySelectList) {
			System.out.println(user);
		}
	}
}
