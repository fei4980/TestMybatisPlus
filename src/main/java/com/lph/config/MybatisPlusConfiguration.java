package com.lph.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.injector.LogicSqlInjector;

/**
 * 添加逻辑删除配置
 * @author 86152
 *
 */
@Configuration
public class MybatisPlusConfiguration {
	
	/**
	 * 在3.1.1版本之后就不需要配置此bean,这里本也不需要
	 * @return
	 */
	@Bean
	public ISqlInjector sqlInjector() {
		return new LogicSqlInjector();
	}
}
