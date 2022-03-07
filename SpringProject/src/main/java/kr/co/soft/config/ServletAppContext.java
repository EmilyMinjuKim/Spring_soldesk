package kr.co.soft.config;

import javax.annotation.Resource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import kr.co.soft.beans.UserBean;
import kr.co.soft.interceptor.TopMenuInterceptor;
import kr.co.soft.mapper.BoardMapper;
import kr.co.soft.mapper.TopMenuMapper;
import kr.co.soft.mapper.UserMapper;
import kr.co.soft.service.TopMenuService;

//<annotation-driven/>와 같음
@Configuration
//controller어노테이션이 셋팅되어 있는 클래스 로드
@EnableWebMvc 
@PropertySource("/WEB-INF/properties/db.properties")
@ComponentScan({ "kr.co.soft.controller", "kr.co.soft.dao", "kr.co.soft.service" })
public class ServletAppContext implements WebMvcConfigurer{
	
	@Value("${db.classname}")
	private String db_classname;
	@Value("${db.url}")
	private String db_url;
	@Value("${db.username}")
	private String db_username;
	@Value("${db.password}")
	private String db_password;
	
	//데이터베이스 접속 데이터 정보 등록
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource source = new BasicDataSource();
		source.setDriverClassName(db_classname);
		source.setUrl(db_url);
		source.setUsername(db_username);
		source.setPassword(db_password);
		
		return source;
	}
	
	//등록된 접속 정보와 쿼리문 관리
	@Bean
	public SqlSessionFactory factory(BasicDataSource source) throws Exception{
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(source);
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
	}
	
	
	//쿼리문 실행을 위한 객체(Mapper)
    @Bean
    public MapperFactoryBean<TopMenuMapper> testMapper(SqlSessionFactory factory) 
    		throws Exception{
		
    	MapperFactoryBean<TopMenuMapper> factoryBean
    		=new MapperFactoryBean<TopMenuMapper>(TopMenuMapper.class);
    	factoryBean.setSqlSessionFactory(factory);
    	return factoryBean;
    
    } 
    @Bean
    public MapperFactoryBean<UserMapper> getUserMapper(SqlSessionFactory factory) 
    		throws Exception{
    	
    	MapperFactoryBean<UserMapper> factoryBean
    	=new MapperFactoryBean<UserMapper>(UserMapper.class);
    	factoryBean.setSqlSessionFactory(factory);
    	return factoryBean;
    	
    }
    @Bean
    public MapperFactoryBean<BoardMapper> getBoardMapper(SqlSessionFactory factory) 
    		throws Exception{
    	
    	MapperFactoryBean<BoardMapper> factoryBean
    	=new MapperFactoryBean<BoardMapper>(BoardMapper.class);
    	factoryBean.setSqlSessionFactory(factory);
    	return factoryBean;
    	
    }
    
    //===========================================================================
	//jsp의 파일이름 앞 뒤 생략
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		WebMvcConfigurer.super.configureViewResolvers(registry);
		registry.jsp("/WEB-INF/views/", ".jsp");	
	}

	//정적 파일의 경로를 매핑
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		WebMvcConfigurer.super.addResourceHandlers(registry);
		registry.addResourceHandler("/**").addResourceLocations("/resource/");
	}
	
	//========================================================================
	@Autowired
	private TopMenuService topMenuService;
	
	@Resource(name="loginUserBean")
	private UserBean loginUserBean;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		WebMvcConfigurer.super.addInterceptors(registry);
		
		//인터셉터 객체 생성(이때 service가 매개변수로 들어와야 함)
		TopMenuInterceptor topMenuInterceptor = new TopMenuInterceptor(topMenuService, loginUserBean);
		
		//인터셉터 등록
		InterceptorRegistration reg1 = registry.addInterceptor(topMenuInterceptor);
		
		//인터셉터 관심사 등록
		//해당 주소에 대해서 interceptor 적용
		reg1.addPathPatterns("/**");	
	}
	
	//메세지 등록
	@Bean
	public ReloadableResourceBundleMessageSource messageSource() {
		ReloadableResourceBundleMessageSource res=new ReloadableResourceBundleMessageSource();
		res.setBasenames("/WEB-INF/properties/error_message");
		return res;	
	}
	
	//@PropertySource와 메세지 충돌을 분리하는 코드
	@Bean
	public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
		return new PropertySourcesPlaceholderConfigurer();
	}
	
	//첨부파일의 내용이 등록되도록 StandardServletMultipartResolver 호출.
	@Bean
	public StandardServletMultipartResolver multipartResolver() {
		return new StandardServletMultipartResolver();	//객체 생성하여 반환.
	}
	
}
