package com.lph.component;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * 处理自动填充的实现方法
 * @author 86152
 *
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	/**
	 * 设计插入时如果填充
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		//为了优化性能，先判断这个bean有没有createTime属性，没有就不用执行后面的了
		boolean hasSetter = metaObject.hasSetter("createTime");
		//判断createTime有没有设置值，有的话就不要再添加填充了
		Object val = getFieldValByName("createTime",metaObject);
		if(hasSetter && val == null) {
			/**
			 * 三个参数：实体类中对应的属性名，要更新的值（这里当前时间）
			 */
			setInsertFieldValByName("createTime",LocalDateTime.now(),metaObject);
		}
	}

	/**
	 * 设计更新时如何填充
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		//为了优化性能，先判断这个bean有没有updateTime属性，没有就不用执行后面的了
		//如果UpdateTime有set值了，有的话就不要再添加填充了
		if(metaObject.hasSetter("updateTime") && getFieldValByName("updateTime",metaObject) == null) {
			/**
			 * 三个参数：实体类中对应的属性名，要更新的值（这里当前时间）
			 */
			setUpdateFieldValByName("updateTime",LocalDateTime.now(),metaObject);
		}
	}

}
