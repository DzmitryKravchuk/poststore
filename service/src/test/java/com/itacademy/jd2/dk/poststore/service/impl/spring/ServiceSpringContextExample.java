package com.itacademy.jd2.dk.poststore.service.impl.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.itacademy.jd2.dk.poststore.dao.api.ICountryDao;
import com.itacademy.jd2.dk.poststore.dao.api.IProductDao;

public class ServiceSpringContextExample {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("dao-context.xml");
		System.out.println(context.getBean(IProductDao.class));
		System.out.println(context.getBean(ICountryDao.class));
		// TODO show multiple candidates with Qualifier

	}
}
