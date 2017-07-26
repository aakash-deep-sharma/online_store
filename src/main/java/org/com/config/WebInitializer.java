package org.com.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class WebInitializer implements WebApplicationInitializer{

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext applicationContext = 
									new AnnotationConfigWebApplicationContext();
		applicationContext.register(SpringInitialzer.class);
		
		
		DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
		dispatcherServlet.setThrowExceptionIfNoHandlerFound(true);
		
		servletContext.addListener(new ContextLoaderListener(applicationContext));
		ServletRegistration.Dynamic servlet = 
				servletContext.addServlet("dispatcher-servlet",dispatcherServlet);
		servlet.addMapping("/");
		servlet.setLoadOnStartup(1);
	}

}
