package com.app.config;

import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.app.constants.Constants;


/**
 * @author SHUBHAM
 *
 */
@EnableWebMvc
@Configuration
@PropertySource(value = { "classpath:application.properties" })
@ComponentScan(basePackages = "com.app.*")
public class JdbcConfiguration extends WebMvcConfigurerAdapter{

	@Autowired
	private Environment environment;

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		
		dataSource.setDriverClassName(environment.getRequiredProperty(Constants.JDBC_DRIVER));
		dataSource.setUrl(environment.getRequiredProperty(Constants.JDBC_URL));
		dataSource.setUsername(environment.getRequiredProperty(Constants.JDBC_USER));
		dataSource.setPassword(environment.getRequiredProperty(Constants.JDBC_PWD));
		
		return dataSource;
	}
	
	@Bean
    public JdbcTemplate jdbcTemplate() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource());
        jdbcTemplate.setResultsMapCaseInsensitive(true);
        
        return jdbcTemplate;
    }
}
