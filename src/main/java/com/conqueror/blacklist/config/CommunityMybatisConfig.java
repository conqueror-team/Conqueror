package com.conqueror.blacklist.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

/**
* @author: Sven
* @E-mail: 1050676672@qq.com
* @version: 创建时间：2018年3月30日 上午11:29:06
* @Description: TODO
*/
@Configuration
@MapperScan(basePackages= {"com.conqueror.blacklist.dao.community"},sqlSessionTemplateRef="communitySqlSessionTemplate")
public class CommunityMybatisConfig {

    //slave数据库
    @Value("${spring.datasource.druid.slave.url:#{null}}")
    private String slavedbUrl;
    @Value("${spring.datasource.druid.slave.username: #{null}}")
    private String slaveusername;
    @Value("${spring.datasource.druid.slave.password:#{null}}")
    private String slavepassword;
    
	@Bean(name = "communityDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.druid.community") // prefix值必须是application.properteis中对应属性的前缀
	public DataSource userDataSource() {
		DruidDataSource datasource = new DruidDataSource();
		datasource.setUrl(slavedbUrl);
		datasource.setUsername(slaveusername);
		datasource.setPassword(slavepassword);
		CommonDruidConfig commonDruidConfig=new CommonDruidConfig();
		return commonDruidConfig.dataSource(datasource);
	}
	
	@Bean
	public SqlSessionFactory communitySqlSessionFactory(@Qualifier("communityDataSource") DataSource dataSource)
			throws Exception {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);
		 //扫描entity包 使用别名
		bean.setTypeAliasesPackage("com.conqueror.blacklist.entity.community");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        //使用jdbc的getGeneratedKeys获取数据库自增主键值
        configuration.setUseGeneratedKeys(true);
        //使用列别名替换列名 select user as User
        configuration.setUseColumnLabel(true);
        //-自动使用驼峰命名属性映射字段   userId    user_id
        configuration.setMapUnderscoreToCamelCase(true);
        bean.setConfiguration(configuration);
        bean.setFailFast(true);
        
		// 添加XML目录
		ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		try {
			bean.setMapperLocations(resolver.getResources("classpath:mapper/community/*.xml"));
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	@Bean
	public SqlSessionTemplate communitySqlSessionTemplate(@Qualifier("communitySqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
			SqlSessionTemplate template = new SqlSessionTemplate(sqlSessionFactory); // 使用上面配置的Factory
			return template;
	}
}
