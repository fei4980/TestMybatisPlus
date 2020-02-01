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
 * 测试mapper中的查询用法
 * @author 86152
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RetrievTest {
	@Autowired
	private UserMapper userMapper;
	
	/**
	 * 按id查询 selectById用法
	 */
	@Test
	public void selectById() {
		long id = 1088248166370832385L;
		User selectById = userMapper.selectById(id);
		System.out.println(selectById);
	}
	
	/**
	 * 用id集合查询 selectBatchIds用法
	 * 返回List结果集
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
	 * 多条件查询 selectByMap用法
	 * 返回List结果集
	 */
	@Test
	public void selectByMap() {
		HashMap<String, Object> map = new HashMap<String,Object>();
		//要符合这两个条件
		map.put("name", "王天风");
		map.put("age", 25);
		//where name = "王天风" and age = 30
		List<User> selectByMap = userMapper.selectByMap(map);
		for(User user : selectByMap) {
			System.out.println(user);
		}
	}
	
	/**
	 * 查询集合 selectList用法
	 * 需求：名字中包含雨并且年龄小于40的数据
	 * name like '%雨%' and age < 40
	 * 返回List结果集
	 */
	@Test
	public void selectList() {
		//QueryWrapper是条件构造器
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "雨").lt("age", 40);
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * 查询集合 selectList用法
	 * 需求：名字中包含雨并且年龄大于等于20且小于等于40并且email不为空
	 * name like '%雨%' and age between 20 and 40 and email is not null
	 * 返回List结果集
	 */
	@Test
	public void selectList2() {
		//QueryWrapper是条件构造器
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.like("name", "雨").between("age", 20, 40).isNotNull("email");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * 查询集合 selectList用法
	 * 需求：名字为王姓或者年龄大于等于25，按照年龄降序排列，年龄相同按照id升序排列
	 * name like '王%' or age >= 25 order by age desc,id asc
	 * 返回List结果集
	 */
	@Test
	public void selectList3() {
		//QueryWrapper是条件构造器,默认是and连接，加个or()就会变成or
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		queryWrapper.likeRight("name","王").or().ge("age", 25).orderByDesc("age")
						.orderByAsc("id");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	/**
	 * 查询集合 selectList用法
	 * 需求：查询创建日期为2019年2月14日并且直属上级名字为王姓的数据
	 * date_format(create_time,'%Y-%m-%d%') and manager_id in (select id from user where name like '王%')
	 * 返回List结果集
	 */
	@Test
	public void selectList4() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//apply是拼接sql，该方法可用于数据库函数 动态入参的params对应前面applySql内部的{index}部分.这样是不会有sql注入风险的,反之会有!，所以用占位符{index}更安全
		queryWrapper.apply("date_format(create_time,'%Y-%m-%d')={0}", "2019-02-14")
		   .inSql("manager_id","select id from user where name like '王%'");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	/**
	 * 查询集合 selectList用法
	 * 需求：查询名字为王姓并且（年龄小于40或邮箱不为空）
	 * name like '王%' and (age<40 or email is not null)
	 * 返回List结果集
	 */
	@Test
	public void selectList5() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//apply是拼接sql，该方法可用于数据库函数 动态入参的params对应前面applySql内部的{index}部分.这样是不会有sql注入风险的,反之会有!，所以用占位符{index}更安全
		queryWrapper.likeRight("name","王").and(wq->wq.lt("age",40).or().isNotNull("email"));
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	/**
	 * 查询集合 selectList用法
	 * 需求：查询名字为王姓或者（年龄小于40并且大于20并且邮箱不为空）
	 * name like '王%' or (age<40 and age > 20 and email is not null)
	 * 返回List结果集
	 */
	@Test
	public void selectList6() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//apply是拼接sql，该方法可用于数据库函数 动态入参的params对应前面applySql内部的{index}部分.这样是不会有sql注入风险的,反之会有!，所以用占位符{index}更安全
		queryWrapper.likeRight("name","王")
		   .or(wq->wq.lt("age",40).gt("age", 20).isNotNull("email"));
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * 查询集合 selectList用法
	 * 需求：查询（年龄小于40或邮箱不为空）并且名字为王姓
	 * (age<40 or email is not null) and name like '王%'
	 * 返回List结果集
	 */
	@Test
	public void selectList7() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//nested嵌套，起括号的作用
		queryWrapper.nested(wq->wq.lt("age", 40).or().isNotNull("email"))
			.likeRight("name", "王");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * 查询集合 selectList用法
	 * 需求：查询年龄为30、31、34、35
	 * age in(30、31、34、35)
	 * 返回List结果集
	 */
	@Test
	public void selectList8() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//nested嵌套，起括号的作用
		List list = new ArrayList();
		list.add(30);
		list.add(31);
		list.add(34);
		list.add(35);
		//in的使用
		queryWrapper.in("age", list);
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * 查询集合 selectList用法
	 * 需求：查询年龄为30、31、34、35的数据，只返回一条即可
	 * age in(30、31、34、35)
	 * 返回List结果集
	 */
	@Test
	public void selectList9() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//nested嵌套，起括号的作用
		List list = new ArrayList();
		list.add(30);
		list.add(31);
		list.add(34);
		list.add(35);
		//in的使用
		//last:无视优化规则直接拼接到sql的最后，只能调用一次多次调用以最后一次为准，有sql注入的风险，做好预防
		queryWrapper.in("age", list).last("limit 1");
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * 指定查询字段 select
	 * 需求：名字中包含雨并且年龄小于40的数据，只需返回id和name字段
	 * name like '%雨%' and age < 40
	 * 返回List结果集
	 */
	@Test
	public void selectListSupper() {
		//QueryWrapper是条件构造器
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//第一种用法，用select指明要查询返回的字段
		queryWrapper.select("id","name").like("name", "雨").lt("age", 40);
		//第二种用法:排除法，不包含create_time和manager_id字段
		//queryWrapper.like("name", "雨").lt("age", 40).select(User.class,info->!info.getColumn().equals("create_time")&&!info.getColumn().equals("manager_id"));
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User user : selectList) {
			System.out.println(user);
		}
	}
	
	/**
	 * like先判断再确认是否添加查询条件
	 * 需求：查询name和email符合的数据
	 * 
	 * 返回List结果集
	 */
	@Test
	public void selectListLikes() {
		//QueryWrapper是条件构造器
		List<User> selectListLike = selectListLike(null,null);
		for(User user : selectListLike) {
			System.out.println(user);
		}
	}
	
	public List<User> selectListLike(String name,String email) {
		//QueryWrapper是条件构造器
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//like方法，第一个参数如果为true,就会应用此查询条件，否则不使用；这里如果name为空，就不会运用name条件查询，常用于查询条件
		queryWrapper.like(StringUtils.isNotEmpty(name), "real_name",name)
			.like(StringUtils.isNotEmpty(email), "email",email);
		List<User> selectList = userMapper.selectList(queryWrapper);
		return selectList;

	}
	
	/**
	 * 通过实体对象传入查询条件
	 * 
	 * 返回List结果集
	 */
	@Test
	public void selectListByBean() {
		User user = new User();
		user.setName("刘红雨");
		user.setAge(32);
		//将实体对象传入，就会查找realName和age对应的数据；这里只有等于的查询运用，更多查看https://www.imooc.com/video/19505/0  （3-8）节
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>(user);
		//还可以拼接其它查询
		queryWrapper.select("id","name").like("name", "雨").lt("age", 40);
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User users : selectList) {
			System.out.println(users);
		}
	}
	
	/**
	 * allEq
	 * 
	 * 返回List结果集
	 */
	@Test
	public void selectListByallEq() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("name", "王天风");
		params.put("age", 25);
		//params.put("age", null);
		queryWrapper.allEq(params);//通过eq,符合的就会查出来
		//queryWrapper.allEq(params,false);//参数为null的会被忽略掉
		List<User> selectList = userMapper.selectList(queryWrapper);
		for(User users : selectList) {
			System.out.println(users);
		}
	}
	
	/**
	 * selectMaps: 设置要查询的字段
	 * selectObjs:返回查询的第一个字段
	 * 
	 * 返回List结果集
	 */
	@Test
	public void selectByselectMaps() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//只查id和name字段
		queryWrapper.select("id","name").like("name", "雨").lt("age", 40);
		
		List<Map<String, Object>> selectMaps = userMapper.selectMaps(queryWrapper);
		//List<Object> selectObj = userMapper.selectObjs(queryWrapper);//这里只返回id的集合，因为只查第一个

		for(Map users : selectMaps) {
			System.out.println(users);
		}
	}
	
	/**
	 * selectMaps: 设置要查询的字段
	 * 需求：查询name带雨的，年龄小于40，返回id和name字段
	 * 返回List结果集
	 */
	@Test
	public void selectByWrapperMaps2() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//只查id和name字段
		queryWrapper.select("id","name").like("name", "雨").lt("age", 40);
		
		List<Map<String, Object>> selectMaps = userMapper.selectMaps(queryWrapper);
		for(Map users : selectMaps) {
			System.out.println(users);
		}
	}
	/**
	 * selectCount: 查总记录数
	 */
	@Test
	public void selectByCount() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//只查id和name字段
		queryWrapper.like("name", "雨").lt("age", 40);
		
		 Integer selectCount = userMapper.selectCount(queryWrapper);
		 System.out.println(selectCount);
	}
	/**
	 * selectOne: 查询一条记录，要求只返回一条或者为空，如果有两条符合就会报错
	 */
	@Test
	public void selectByOne() {
		
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		//只查id和name字段
		queryWrapper.like("name", "刘红雨").lt("age", 40);
		
		 User selectOne = userMapper.selectOne(queryWrapper);
		 System.out.println(selectOne);
	}
	
	
	/**
	 * lambda条件构造器的使用
	 * 需求：查询name带雨的，年龄小于40，返回id和name字段
	 * 返回List结果集
	 */
	@Test
	public void selectLambda() {
		//有三种调用的方式：
		//LambdaQueryWrapper<User> queryWrapper = new QueryWrapper<User>().lambda();
		//LambdaQueryWrapper<User> lambdaQueryWrapper = new LambdaQueryWrapper<User>();
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.like(User::getName,"雨").lt(User::getAge, 40);
		//相当于: where name like '%雨%'
		
		List<Map<String, Object>> userlist = userMapper.selectMaps(lambdaQuery);
		userlist.forEach(System.out::println);
	}
	
	/**
	 * lambda条件构造器的使用
	 * 需求：查询名字为王姓并且（年龄小于40或邮箱不为空）
	 * 返回List结果集
	 */
	@Test
	public void selectLambda2() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "王")
			.and(lqw->lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));
		
		List<Map<String, Object>> userlist = userMapper.selectMaps(lambdaQuery);
		userlist.forEach(System.out::println);
	}
	
	/**
	 * 自定义sql用注解的方式
	 * 需求：查询名字为王姓并且（年龄小于40或邮箱不为空）
	 * 返回List结果集
	 */
	@Test
	public void selectZdySql() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "王")
			.and(lqw->lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));
		
		List<User> userlist = userMapper.selectAll(lambdaQuery);
		userlist.forEach(System.out::println);
	}
	
	/**
	 * 自定义sql用传统xml的形式
	 * 需求：查询名字为王姓并且（年龄小于40或邮箱不为空）
	 * 返回List结果集
	 */
	@Test
	public void selectZdySqlByXml() {
		LambdaQueryWrapper<User> lambdaQuery = Wrappers.<User>lambdaQuery();
		lambdaQuery.likeRight(User::getName, "王")
			.and(lqw->lqw.lt(User::getAge, 40).or().isNotNull(User::getEmail));
		
		List<User> userlist = userMapper.selectAllByXml(lambdaQuery);
		userlist.forEach(System.out::println);
	}
	
	/**
	 * page 分页查询selectPage
	 * 需求：
	 * 返回List结果集
	 */
	@Test
	public void selectPage() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Page<User> page = new Page<User>(1,2,true);//页码、页大小pageSize,第三个参数是否查总记录数（true就是查,默认true）
		
		IPage<User> selectPage = userMapper.selectPage(page,queryWrapper);
		System.out.println("总页数:"+selectPage.getPages());
		System.out.println("总记录数:"+selectPage.getTotal());
		List<User> userList = selectPage.getRecords();
		userList.forEach(System.out::println);
	}
	
	/**
	 * page 分页查询selectPage
	 * 需求：
	 * 返回List结果集
	 */
	@Test
	public void selectMapsPage() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Page<User> page = new Page<User>(1,2,true);//页码、页大小pageSize,第三个参数是否查总记录数（true就是查）
		
		IPage<Map<String,Object>> selectPage = userMapper.selectMapsPage(page,queryWrapper);
		System.out.println("总页数:"+selectPage.getPages());
		System.out.println("总记录数:"+selectPage.getTotal());
		List<Map<String,Object>> userList = selectPage.getRecords();
		userList.forEach(System.out::println);
	}
	
	/**
	 * page 自定义sql查询加入分页
	 * 需求：
	 * 返回List结果集
	 */
	@Test
	public void selectPageZdySql() {
		QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
		Page<User> page = new Page<User>(1,2,true);//页码、页大小pageSize,第三个参数是否查总记录数（true就是查）
		
		IPage<User> selectPage = userMapper.selectUserPage(page,queryWrapper);
		System.out.println("总页数:"+selectPage.getPages());
		System.out.println("总记录数:"+selectPage.getTotal());
		List<User> userList = selectPage.getRecords();
		userList.forEach(System.out::println);
	}
}
