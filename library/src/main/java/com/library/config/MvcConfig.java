package com.library.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import com.library.Constants;

@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {
	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		/*default controllers*/
		registry.addViewController(Constants.REF_START).setViewName("index");
		registry.addViewController(Constants.REF_LOGIN).setViewName("login");
		registry.addViewController(Constants.REF_REG_READ).setViewName("addReader");
		registry.addViewController(Constants.REF_NEW_BOOK).setViewName("addBook");
		registry.addViewController(Constants.REF_REG_LIBR).setViewName("addLibrarian");
	}
}