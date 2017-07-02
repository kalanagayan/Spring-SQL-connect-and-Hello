package springmvc.example.config;

import javax.naming.NamingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jndi.JndiTemplate;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "springmvc.example")
public class WebConfig extends WebMvcConfigurerAdapter{

	@Autowired
	DataSource datasource;
	
	@Bean
	public NamedParameterJdbcTemplate getNamedParameterJdbcTemplate(){
		return new NamedParameterJdbcTemplate(datasource);
	}
	
	@Bean
	public DataSource getDataSource() throws NamingException{
		
		JndiTemplate jnditemplate = new JndiTemplate();
		DataSource datasource = (DataSource) jnditemplate.lookup("java:/comp/env/jdbc/springmvc");
		return datasource;
		
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	@Bean
	public InternalResourceViewResolver viewResoulver(){
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver(); 
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/jsp");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
		
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
}
