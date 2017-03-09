package com.niit.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;



public class AppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

	
	@Override
	protected Class[] getRootConfigClasses() {
		
		return new Class[] { AppConfig.class, WebSocket.class };
	}

	@Override
	protected Class[] getServletConfigClasses() {
		
		return new Class[] { AppConfig.class, WebSocket.class };
	}

	@Override
	protected String[] getServletMappings() {
		
		return new String[] { "/" };
	}

}