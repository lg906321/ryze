package own.ryze.application.weixin.config.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;

import lombok.extern.slf4j.Slf4j;

/**
 * 数据源管理
 * 
 * @author LCY
 *
 */
@Slf4j
@Configuration
@EnableJpaRepositories(basePackages = "own.ryze.application.weixin.persistent.dao", entityManagerFactoryRef = "entityManager")
public class DataSourceConfig
{
	@Value("${datasource.type}")
	private Class<? extends DataSource> dataSourceType;
	@Value("${datasource.readSize}")
	private String dataSourceSize;
	@Autowired
	private JpaProperties jpaProperties;

	@Bean
	@ConfigurationProperties(prefix = "datasource.write")
	public DataSource writeDataSource()
	{
		log.info("主库初始化");
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	@Bean
	@ConfigurationProperties(prefix = "datasource.read")
	public DataSource readDataSource()
	{
		log.info("从库初始化");
		return DataSourceBuilder.create().type(dataSourceType).build();
	}

	@Bean
	@Primary
	public AbstractRoutingDataSource dynamicDataSource()
	{
		log.info("动态数据源初始化");
		int size = Integer.parseInt(dataSourceSize);

		DataSourceRemote dataSourceRemote = new DataSourceRemote(size);

		Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
		DataSource writeDataSource = writeDataSource();
		DataSource readDataSource = readDataSource();
		// 写主
		targetDataSources.put(DataSourceType.write, writeDataSource);
		// 读从
		targetDataSources.put(DataSourceType.read, readDataSource);

		// 默认主写库
		dataSourceRemote.setDefaultTargetDataSource(writeDataSource);
		dataSourceRemote.setTargetDataSources(targetDataSources);

		return dataSourceRemote;
	}

	@Bean
	public LocalContainerEntityManagerFactoryBean entityManager(EntityManagerFactoryBuilder builder)
	{
		log.info("实体管理器初始化");
		AbstractRoutingDataSource dynamicDataSource = dynamicDataSource();
		LocalContainerEntityManagerFactoryBean entityManager = builder.dataSource(dynamicDataSource)
				.properties(jpaProperties.getHibernateProperties(dynamicDataSource))
				.packages("own.ryze.application.weixin.persistent.bean").persistenceUnit("entityManager").build();

		return entityManager;
	}

}
