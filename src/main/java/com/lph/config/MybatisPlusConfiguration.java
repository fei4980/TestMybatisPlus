package com.lph.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;

/**
 * ����߼�ɾ������
 * @author 86152
 *
 */
@Configuration
public class MybatisPlusConfiguration {
	
	/**
	 * ��3.1.1�汾֮��Ͳ���Ҫ���ô�bean,���ﱾҲ����Ҫ
	 * @return
	 */
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}
}
