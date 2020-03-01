package com.lph.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.baomidou.mybatisplus.extension.plugins.OptimisticLockerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.PerformanceInterceptor;

@Configuration
public class MybatisPlusConfig {
	
	/**
	 * 分页插件
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
	
	/**
	 * 乐观锁插件
	 * @return
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}
	
	/**
	 * 性能分析配置
	 * 通过profile注解设定只有开发和测试环境下使用
	 * @return
	 */
	@Bean
	@Profile({"dev","test"})
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		performanceInterceptor.setFormat(true);//将执行的sql格式化显示，觉得不好看可以不要
		//performanceInterceptor.setMaxTime(10000L);//执行超过十秒就报错，停止运行，生产环境一定不要用
		return performanceInterceptor;
	}
}
