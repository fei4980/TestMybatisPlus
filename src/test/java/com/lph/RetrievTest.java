package com.lph;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lph.dao.UserMapper;
import com.lph.entity.User;
/**
 * ����mapper�еĲ�ѯ�÷�
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetrievTest {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * ��id��ѯ selectById�÷�
	 */
	@Test
	public void selectById() {
		long id = 1088248166370832385L;
		User selectById = userMapper.selectById(id);
		System.out.println(selectById);
	}
	
	/**
	 * ��id���ϲ�ѯ selectBatchIds�÷�
	 * ����List�����
	 */
	@Test
	public void selectids() {
		List<Long> list = new ArrayList<Long>();
		list.add(1087982257332887553L);
		list.add(1088250446457389058L);
		list.add(1088248166370832385L);
		List<User> userList = userMapper.selectBatchIds(list);
		for(User user : userList) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��������ѯ selectByMap�÷�
	 * ����List�����
	 */
	@Test
	public void selectByMap() {
		HashMap<String, Object> map = new HashMap<String,Object>();
		//Ҫ��������������
		map.put("name", "�����");
		map.put("age", 25);
		//where name = "�����" and age = 30
		List<User> selectByMap = userMapper.selectByMap(map);
		for(User user : selectByMap) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��ѯ���� selectList�÷�
	 * ���������а����겢������С��40������
	 * name like '%��%' and age < 40
	 * ����List�����
	 */
	@Test
	public void selectList() {
		//QueryWrapper������������
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "��").lt("age", 40);
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��ѯ���� selectList�÷�
	 * ���������а����겢��������ڵ���20��С�ڵ���40����email��Ϊ��
	 * name like '%��%' and age between 20 and 40 and email is not null
	 * ����List�����
	 */
	@Test
	public void selectList2() {
		//QueryWrapper������������
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "��").between("age", 20, 40).isNotNull("email");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��ѯ���� selectList�÷�
	 * ��������Ϊ���ջ���������ڵ���25���������併�����У�������ͬ����id��������
	 * name like '��%' or age >= 25 order by age desc,id asc
	 * ����List�����
	 */
	@Test
	public void selectList3() {
		//QueryWrapper������������,Ĭ����and���ӣ��Ӹ�or()�ͻ���or
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.likeRight("name","��").or().ge("age", 25).orderByDesc("age")
						.orderByAsc("id");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	/**
	 * ��ѯ���� selectList�÷�
	 * ���󣺲�ѯ��������Ϊ2019��2��14�ղ���ֱ���ϼ�����Ϊ���յ�����
	 * date_format(create_time,'%Y-%m-%d%') and manager_id in (select id from user where name like '��%')
	 * ����List�����
	 */
	@Test
	public void selectList4() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//apply��ƴ��sql���÷������������ݿ⺯�� ��̬��ε�params��Ӧǰ��applySql�ڲ���{index}����.�����ǲ�����sqlע����յ�,��֮����!��������ռλ��{index}����ȫ
		queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
		   .inSql("manager_id","select id from user where name like '��%'");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	/**
	 * ��ѯ���� selectList�÷�
	 * ���󣺲�ѯ����Ϊ���ղ��ң�����С��40�����䲻Ϊ�գ�
	 * name like '��%' and (age<40 or email is not null)
	 * ����List�����
	 */
	@Test
	public void selectList5() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//apply��ƴ��sql���÷������������ݿ⺯�� ��̬��ε�params��Ӧǰ��applySql�ڲ���{index}����.�����ǲ�����sqlע����յ�,��֮����!��������ռλ��{index}����ȫ
		queryWrapper.likeRight("name","��").and(wq->wq.lt("age",40).or().isNotNull("email"));
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	/**
	 * ��ѯ���� selectList�÷�
	 * ���󣺲�ѯ����Ϊ���ջ��ߣ�����С��40���Ҵ���20�������䲻Ϊ�գ�
	 * name like '��%' or (age<40 and age > 20 and email is not null)
	 * ����List�����
	 */
	@Test
	public void selectList6() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//apply��ƴ��sql���÷������������ݿ⺯�� ��̬��ε�params��Ӧǰ��applySql�ڲ���{index}����.�����ǲ�����sqlע����յ�,��֮����!��������ռλ��{index}����ȫ
		queryWrapper.likeRight("name","��")
		   .or(wq->wq.lt("age",40).gt("age", 20).isNotNull("email"));
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��ѯ���� selectList�÷�
	 * ���󣺲�ѯ������С��40�����䲻Ϊ�գ���������Ϊ����
	 * (age<40 or email is not null) and name like '��%'
	 * ����List�����
	 */
	@Test
	public void selectList7() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//nestedǶ�ף������ŵ�����
		queryWrapper.nested(wq->wq.lt("age", 40).or().isNotNull("email"))
			.likeRight("name", "��");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��ѯ���� selectList�÷�
	 * ���󣺲�ѯ����Ϊ30��31��34��35
	 * age in(30��31��34��35)
	 * ����List�����
	 */
	@Test
	public void selectList8() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//nestedǶ�ף������ŵ�����
		List list = new ArrayList();
		list.add(30);
		list.add(31);
		list.add(34);
		list.add(35);
		//in��ʹ��
		queryWrapper.in("age", list);
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * ��ѯ���� selectList�÷�
	 * ���󣺲�ѯ����Ϊ30��31��34��35�����ݣ�ֻ����һ������
	 * age in(30��31��34��35)
	 * ����List�����
	 */
	@Test
	public void selectList9() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//nestedǶ�ף������ŵ�����
		List list = new ArrayList();
		list.add(30);
		list.add(31);
		list.add(34);
		list.add(35);
		//in��ʹ��
		//last:�����Ż�����ֱ��ƴ�ӵ�sql�����ֻ�ܵ���һ�ζ�ε��������һ��Ϊ׼����sqlע��ķ��գ�����Ԥ��
		queryWrapper.in("age", list).last("limit 1");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * ָ����ѯ�ֶ� select
	 * ���������а����겢������С��40�����ݣ�ֻ�践��id��name�ֶ�
	 * name like '%��%' and age < 40
	 * ����List�����
	 */
	@Test
	public void selectListSupper() {
		//QueryWrapper������������
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//��һ���÷�����selectָ��Ҫ��ѯ���ص��ֶ�
		queryWrapper.select("id","name").like("name", "��").lt("age", 40);
		//�ڶ����÷�:�ų�����������create_time��manager_id�ֶ�
		//queryWrapper.like("name", "��").lt("age", 40).select(User.class,info->!info.getColumn().equals("create_time")&&!info.getColumn().equals("manager_id"));
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * like���ж���ȷ���Ƿ���Ӳ�ѯ����
	 * ���󣺲�ѯname��email���ϵ�����
	 * 
	 * ����List�����
	 */
	@Test
	public void selectListLikes() {
		//QueryWrapper������������
		List<User> selectListLike = selectListLike(null,null);
		for(User user : selectListLike) {
			System.out.println(user);
		}
	}
	
	public List<User> selectListLike(String name,String email) {
		//QueryWrapper������������
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//like��������һ���������Ϊtrue,�ͻ�Ӧ�ô˲�ѯ����������ʹ�ã��������nameΪ�գ��Ͳ�������name������ѯ�������ڲ�ѯ����
		queryWrapper.like(StringUtils.isNotEmpty(name), "real_name",name)
			.like(StringUtils.isNotEmpty(email), "email",email);
		List<User> selectList = userMapper.selectList(queryWrapper);
		return selectList;

	}
	
	/**
	 * ͨ��ʵ��������ѯ����
	 * 
	 * ����List�����
	 */
	@Test
	public void selectListByBean() {
		User user = new User();
		user.setName("������");
		user.setAge(32);
		//��ʵ������룬�ͻ����realName��age��Ӧ�����ݣ�����ֻ�е��ڵĲ�ѯ���ã�����鿴https://www.imooc.com/video/19505/0  ��3-8����
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
		//������ƴ��������ѯ
		queryWrapper.select("id","name").like("name", "��").lt("age", 40);
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User users : selectList) {
			System.out.println(users);
		}
	}
	
	/**
	 * allEq
	 * 
	 * ����List�����
	 */
	@Test
	public void selectListByallEq() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("name", "�����");
		params.put("age", 25);
		//params.put("age", null);
		queryWrapper.allEq(params);//ͨ��eq,���ϵľͻ�����
		//queryWrapper.allEq(params,false);//����Ϊnull�Ļᱻ���Ե�
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User users : selectList) {
			System.out.println(users);
		}
	}
	
	/**
	 * selectMaps: ����Ҫ��ѯ���ֶ�
	 * selectObjs:���ز�ѯ�ĵ�һ���ֶ�
	 * 
	 * ����List�����
	 */
	@Test
	public void selectByselectMaps() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//ֻ��id��name�ֶ�
		queryWrapper.select("id","name").like("name", "��").lt("age", 40);
		
		List<Map<String, Object>> selectMaps = userMapper.selectMaps(queryWrapper);
		//List<Object> selectObj = userMapper.selectObjs(queryWrapper);//����ֻ����id�ļ��ϣ���Ϊֻ���һ��

		for(Map users : selectMaps) {
			System.out.println(users);
		}
	}
	
	/**
	 * selectMaps: ����Ҫ��ѯ���ֶ�
	 * ���󣺲�ѯname����ģ�����С��40������id��name�ֶ�
	 * ����List�����
	 */
	@Test
	public void selectByWrapperMaps2() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//ֻ��id��name�ֶ�
		queryWrapper.select("id","name").like("name", "��").lt("age", 40);
		
		List<Map<String, Object>> selectMaps = userMapper.selectMaps(queryWrapper);
		for(Map users : selectMaps) {
			System.out.println(users);
		}
	}
	/**
	 * selectCount: ���ܼ�¼��
	 */
	@Test
	public void selectByCount() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//ֻ��id��name�ֶ�
		queryWrapper.like("name", "��").lt("age", 40);
		
		 Integer selectCount = userMapper.selectCount(queryWrapper);
		 System.out.println(selectCount);
	}
	/**
	 * selectOne: ��ѯһ����¼��Ҫ��ֻ����һ������Ϊ�գ�������������Ͼͻᱨ��
	 */
	@Test
	public void selectByOne() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//ֻ��id��name�ֶ�
		queryWrapper.like("name", "������").lt("age", 40);
		
		 User selectOne = userMapper.selectOne(queryWrapper);
		 System.out.println(selectOne);
	}
	
	
	/**
	 * lambda������������ʹ��
	 * ���󣺲�ѯname����ģ�����С��40������id��name�ֶ�
	 * ����List�����
	 */
	@Test
	public void selectLambda() {
		//�����ֵ��õķ�ʽ��
		//LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
		//LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.like(User::getName,"��").lt(User::getAge, 40);
		//�൱��: where name like '%��%'
		
		List<Map<String, Object>> userlist = userMapper.selectMaps(lambdaQuery);
		userlist.forEach(System.out::println);
	}
	
	/**
	 * lambda������������ʹ��
	 * ���󣺲�ѯ����Ϊ���ղ��ң�����С��40�����䲻Ϊ�գ�
	 * ����List�����
	 */
	@Test
	public void selectLambda2() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "��")
			.and(lqw->lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));
		
		List<Map<String, Object>> userlist = userMapper.selectMaps(lambdaQuery);
		userlist.forEach(System.out::println);
	}
	
	/**
	 * �Զ���sql��ע��ķ�ʽ
	 * ���󣺲�ѯ����Ϊ���ղ��ң�����С��40�����䲻Ϊ�գ�
	 * ����List�����
	 */
	@Test
	public void selectZdySql() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "��")
			.and(lqw->lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));
		
		List<User> userlist = userMapper.selectAll(lambdaQuery);
		userlist.forEach(System.out::println);
	}
	
	/**
	 * �Զ���sql�ô�ͳxml����ʽ
	 * ���󣺲�ѯ����Ϊ���ղ��ң�����С��40�����䲻Ϊ�գ�
	 * ����List�����
	 */
	@Test
	public void selectZdySqlByXml() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "��")
			.and(lqw->lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));
		
		List<User> userlist = userMapper.selectAllByXml(lambdaQuery);
		userlist.forEach(System.out::println);
	}
	
	/**
	 * page ��ҳ��ѯselectPage
	 * ����
	 * ����List�����
	 */
	@Test
	public void selectPage() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Page<User> page = new Page<User>(1,2,true);//ҳ�롢ҳ��СpageSize,�����������Ƿ���ܼ�¼����true���ǲ�,Ĭ��true��
		
		IPage<User> selectPage = userMapper.selectPage(page,queryWrapper);
		System.out.println("��ҳ��:"+selectPage.getPages());
		System.out.println("�ܼ�¼��:"+selectPage.getTotal());
		List<User> userList = selectPage.getRecords();
		userList.forEach(System.out::println);
	}
	
	/**
	 * page ��ҳ��ѯselectPage
	 * ����
	 * ����List�����
	 */
	@Test
	public void selectMapsPage() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Page<User> page = new Page<User>(1,2,true);//ҳ�롢ҳ��СpageSize,�����������Ƿ���ܼ�¼����true���ǲ飩
		
		IPage<Map<String,Object>> selectPage = userMapper.selectMapsPage(page,queryWrapper);
		System.out.println("��ҳ��:"+selectPage.getPages());
		System.out.println("�ܼ�¼��:"+selectPage.getTotal());
		List<Map<String,Object>> userList = selectPage.getRecords();
		userList.forEach(System.out::println);
	}
	
	/**
	 * page �Զ���sql��ѯ�����ҳ
	 * ����
	 * ����List�����
	 */
	@Test
	public void selectPageZdySql() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Page<User> page = new Page<User>(1,2,true);//ҳ�롢ҳ��СpageSize,�����������Ƿ���ܼ�¼����true���ǲ飩
		
		IPage<User> selectPage = userMapper.selectUserPage(page,queryWrapper);
		System.out.println("��ҳ��:"+selectPage.getPages());
		System.out.println("�ܼ�¼��:"+selectPage.getTotal());
		List<User> userList = selectPage.getRecords();
		userList.forEach(System.out::println);
	}
}
