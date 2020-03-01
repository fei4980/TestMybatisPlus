package com.lph.component;

import java.time.LocalDateTime;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * �����Զ�����ʵ�ַ���
 * @author 86152
 *
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	/**
	 * ��Ʋ���ʱ������
	 */
	@Override
	public void insertFill(MetaObject metaObject) {
		//Ϊ���Ż����ܣ����ж����bean��û��createTime���ԣ�û�оͲ���ִ�к������
		boolean hasSetter = metaObject.hasSetter("createTime");
		//�ж�createTime��û������ֵ���еĻ��Ͳ�Ҫ����������
		Object val = getFieldValByName("createTime",metaObject);
		if(hasSetter && val == null) {
			/**
			 * ����������ʵ�����ж�Ӧ����������Ҫ���µ�ֵ�����ﵱǰʱ�䣩
			 */
			setInsertFieldValByName("createTime",LocalDateTime.now(),metaObject);
		}
	}

	/**
	 * ��Ƹ���ʱ������
	 */
	@Override
	public void updateFill(MetaObject metaObject) {
		//Ϊ���Ż����ܣ����ж����bean��û��updateTime���ԣ�û�оͲ���ִ�к������
		//���UpdateTime��setֵ�ˣ��еĻ��Ͳ�Ҫ����������
		if(metaObject.hasSetter("updateTime") && getFieldValByName("updateTime",metaObject) == null) {
			/**
			 * ����������ʵ�����ж�Ӧ����������Ҫ���µ�ֵ�����ﵱǰʱ�䣩
			 */
			setUpdateFieldValByName("updateTime",LocalDateTime.now(),metaObject);
		}
	}

}
