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
	 * ��ҳ���
	 * @return
	 */
	@Bean
	public PaginationInterceptor paginationInterceptor() {
		return new PaginationInterceptor();
	}
	
	/**
	 * �ֹ������
	 * @return
	 */
	@Bean
	public OptimisticLockerInterceptor optimisticLockerInterceptor() {
		return new OptimisticLockerInterceptor();
	}
	
	/**
	 * ���ܷ�������
	 * ͨ��profileע���趨ֻ�п����Ͳ��Ի�����ʹ��
	 * @return
	 */
	@Bean
	@Profile({"dev","test"})
	public PerformanceInterceptor performanceInterceptor() {
		PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
		performanceInterceptor.setFormat(true);//��ִ�е�sql��ʽ����ʾ�����ò��ÿ����Բ�Ҫ
		//performanceInterceptor.setMaxTime(10000L);//ִ�г���ʮ��ͱ���ֹͣ���У���������һ����Ҫ��
		return performanceInterceptor;
	}
}
