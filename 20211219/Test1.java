package com.study.springcore.case05;

import java.util.LinkedHashSet;
import java.util.Set;import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test1 {
	public static void main(String[] args) {
		ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext5.xml");
		
		Student john = ctx.getBean("s1", Student.class);
		System.out.println(john);
		
		Student mary = ctx.getBean("s2", Student.class);
		System.out.println(mary);
		 
		// 請問 mary 的老師有誰 ? 印出 name
		
		Teacher[] teachers = {ctx.getBean("t1", Teacher.class), ctx.getBean("t2", Teacher.class)};
		Set<Teacher> teachers2 = new LinkedHashSet<>();
		for(Teacher teacher : teachers) {
			for(Clazz cla1 : mary.getClazzs()) {
				for(Clazz cla2 : teacher.getClazzs()) {
					if(cla1.getName() == cla2.getName()) {
						System.out.println(teacher.getName());
					}
				}
			}
		}
		
		/*
		System.out.println(mary.getName() + "的課程：" + mary.getClazzs());
		Teacher[] teachers = {ctx.getBean("t1", Teacher.class), ctx.getBean("t2", Teacher.class)};
		Set<Teacher> teachers2 = new LinkedHashSet<>();
		for(Teacher teacher : teachers) {
			clazz_loop:
			for(Clazz cla1 : teacher.getClazzs()) {
				for(Clazz cla2 : mary.getClazzs()) {
					if(cla1.getId() == cla2.getId()) {
						//System.out.println(teacher.getName());
						teachers2.add(teacher);
						break clazz_loop;
					}
				}
			}
		}
		System.out.println(mary.getName() + "的老師(物件)：" + teachers2);
		System.out.println(mary.getName() + "的老師(名字)：" + teachers2.stream()
														.map(Teacher::getName)
														.collect(Collectors.toSet()));
		*/
	}
}
