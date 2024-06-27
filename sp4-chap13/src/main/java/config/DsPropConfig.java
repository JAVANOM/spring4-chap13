package config;

import java.beans.PropertyVetoException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Configuration
public class DsPropConfig {
    
	// @Value가 플레이스 홀더의 값을 프로퍼티의 값으로 치환
	@Value("${db.driver}")
	private String driver;
	@Value("${db.jdbcUrl}")
	private String jdbcUrl;
	@Value("${db.user}")
	private String user;
	@Value("${db.password}")
	private String password;
	
	// 특수 목적의 빈이기 때문에 정적 메서드로 지정하지 않으면 동작하지 않음
	@Bean
	public static PropertySourcesPlaceholderConfigurer properties() {
		PropertySourcesPlaceholderConfigurer configurer 
		    = new PropertySourcesPlaceholderConfigurer();
		// setLoctaion()이나 setLocations() 메서드를 직업 구현 시 해당 리소스 클래스를 주로 사용
		// 클래스패스에 위치한 자원으로부터 데이터를 읽음
		configurer.setLocation(new ClassPathResource("db/db.preperties")); 
		
		return configurer;
	}
	
	@Bean
	public DataSource dataSource() {
		
		ComboPooledDataSource ds = new ComboPooledDataSource();
		
		try {
			ds.setDriverClass(driver);
		} catch (PropertyVetoException e) {
			e.printStackTrace();
		}
		ds.setJdbcUrl(jdbcUrl);
		ds.setUser(user);
		ds.setPassword(password);
		
		return ds;
	}
}
