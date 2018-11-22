package com.xy.configration.mybatis;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import javax.sql.DataSource;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import com.xy.configration.datasource.DataSourceType;
import com.xy.configration.datasource.DynamicDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import com.github.pagehelper.PageHelper;

@Configuration
@EnableTransactionManagement(proxyTargetClass=true)
@Slf4j
public class MybatisConfig {

	@Bean(name = "writeDataSource",initMethod = "init", destroyMethod = "close")
	@ConfigurationProperties(prefix = "spring.datasource.write")
	public DataSource writeDataSource(){
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "readDataSource",initMethod = "init", destroyMethod = "close")
	@ConfigurationProperties(prefix = "spring.datasource.read")
	public DataSource readDataSource(){
		return DruidDataSourceBuilder.create().build();
	}

	@Bean(name = "dynamicDataSource")
	@Primary
	public DynamicDataSource dynamicDataSource(){
		Map<Object, Object> targetDataSources = new HashMap<>(16);
		targetDataSources.put(DataSourceType.Master, writeDataSource());
		targetDataSources.put(DataSourceType.Slave, readDataSource());
		DynamicDataSource dataSource = new DynamicDataSource();
		//该方法是AbstractRoutingDataSource的方法
		dataSource.setTargetDataSources(targetDataSources);
		dataSource.setDefaultTargetDataSource(writeDataSource());
		return dataSource;
	}
	
	@Bean(name="pageHelper")
	public PageHelper getPageHelper() {
		PageHelper pageHelper = new PageHelper();
		Properties properties = new Properties();
		properties.setProperty("reasonable", "true");
		properties.setProperty("supportMethodsArguments", "true");
		properties.setProperty("returnPageInfo", "true");
		properties.setProperty("params", "count=countSql");
		pageHelper.setProperties(properties);
	    return pageHelper;
	}
	
	@Bean(name="sqlSessionFactory")
	public SqlSessionFactory getSqlSessionFactory(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
		SqlSessionFactoryBean sqlSessionFactoryBean=new SqlSessionFactoryBean();
		//指定别名包
		sqlSessionFactoryBean.setTypeAliasesPackage("com.xy.entity");
		sqlSessionFactoryBean.setDataSource(dynamicDataSource);
		try {
			ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
			//指定mapper文件的位置
			sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:mapper/*.xml"));
			return sqlSessionFactoryBean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("mybatis初始化异常！", e);
			throw new RuntimeException("mybatis初始化异常！",e);
		}
		
	}
	
	@Bean(name="sqlSessionTemplate")
	public SqlSessionTemplate getSqlSessionTemplate(@Qualifier("sqlSessionFactory")SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
	}

	@Bean(name="transactionManager")
	@Primary
	public DataSourceTransactionManager testTransactionManager(@Qualifier("dynamicDataSource") DynamicDataSource dynamicDataSource) {
		return new DataSourceTransactionManager(dynamicDataSource);
	}
	
}
