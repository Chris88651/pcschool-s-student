package com.study.springcore.case08;

import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/*
 * 功能:
 * 1. 建立 Person 資料
 * 		-> 輸入 name, birth
 * 2. 取得 Person 全部資料
 * 		-> 不用輸入參數
 * 3. 根據姓名取得 Person
 * 		-> 輸入 name
 * 4. 取得今天生日的 Person
 * 		-> 輸入今天日期
 * 5. 取得某一歲數以上的 Person
 * 		-> 輸入 age
 * 6. 根據姓名來修改Person的生日
 * 		-> 輸入 name, birth
 * 7. 根據姓名來刪除Person
 * 		-> 輸入 name
 * */

@Controller
public class PersonController {
	
	@Autowired
	private PersonService personService;
	
	//-----------------------------------------
	@Autowired
	private Gson gson;
	
	// Json file 資料庫存放地
	private static final Path PATH = Paths.get("src/main/java/com/study/springcore/case08/person.json");
	//-----------------------------------------
	
	public void addPerson(String name, int yyyy, int mm, int dd) {
		// 1. 判斷 name, yyyy, mm 與 dd 是否有資料?
		// 2. 將 yyyy/mm/dd 轉日期格式
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date birth = sdf.parse(yyyy + "/" + mm + "/" + dd);
			addPerson(name, birth);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void addPerson(String name, Date birth) {
		// 1. 判斷 name 與 birth 是否有資料?
		// 2. 建立 Person 資料
		boolean check = personService.append(name, birth);
		System.out.println("add person: " + check);
	}
	
	public void printAllPersons() {
		//System.out.println(personService.findAllPersons());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 資料呈現
		List<Person> people = personService.findAllPersons();
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
		System.out.println("+--------------+---------+--------------+");
		for(Person p : people) {
			String birthday = sdf.format(p.getBirth());
			System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
			System.out.println("+--------------+---------+--------------+");
		}
	}
	
	//-----------------------------------------
	public void printAllPersonsBirthToday() {
		//System.out.println(personService.findAllPersons());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 資料呈現
		List<Person> people = personService.findAllPersons();
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
		System.out.println("+--------------+---------+--------------+");
		
		Date d = new Date();
		String td = sdf.format(d);
		
		for(Person p : people) {
			String birthday = sdf.format(p.getBirth());
			/*
			 * 調整系統時間至 1992/08/22 也無法顯示 Molly 的資料,不知為何日期一致 if判斷 無法為 true ?
			 */
			if(td == birthday) {
				System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
				System.out.println("+--------------+---------+--------------+");
			}
		}
	}
	
	public void printPersonsOverOld(int age) {
		//System.out.println(personService.findAllPersons());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		// 資料呈現
		List<Person> people = personService.findAllPersons();
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
		System.out.println("+--------------+---------+--------------+");
		for(Person p : people) {
			if(p.getAge() > age) {
				String birthday = sdf.format(p.getBirth());
				System.out.printf("| %-12s | %7d | %12s |\n", p.getName(), p.getAge(), birthday);
				System.out.println("+--------------+---------+--------------+");
			}
		}
	}
	public void setPersonsBirth(String name, int yyyy, int mm, int dd) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		try {
			Date birth = sdf.parse(yyyy + "/" + mm + "/" + dd);
			
			String jsonStr = Files.readAllLines(PATH).stream().collect(Collectors.joining());
			Type type = new TypeToken<ArrayList<Person>>() {}.getType();
			List<Person> people = gson.fromJson(jsonStr, type);
			
			for(Person p : people) {
				/*
				 * 不知為何名稱一致 if判斷 無法為 true ?
				 */
				if(p.getName() == name) {
					p.setBirth(birth);
				}
			}
			
			String newJsonString = gson.toJson(people);
			Files.write(PATH, newJsonString.getBytes("UTF-8"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void delPersons(String name) {
		try {
			String jsonStr = Files.readAllLines(PATH).stream().collect(Collectors.joining());
			Type type = new TypeToken<ArrayList<Person>>() {}.getType();
			List<Person> people = gson.fromJson(jsonStr, type);
			Person newpeople = new Person();
			
			for(Person p : people) {
				/*
				 * 不知為何名稱一致 if判斷 無法為 true ?
				 */
				if(p.getName() == name) {
					
				}else {
					newpeople.setName(p.getName());
					newpeople.setAge(p.getAge());
					newpeople.setBirth(p.getBirth());
				}
			}
			
			String newJsonString = gson.toJson(newpeople);
			Files.write(PATH, newJsonString.getBytes("UTF-8"));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	//-----------------------------------------
	
	// 根據姓名取得 Person
	public Person getPersonByName(String name) {
		// 1. 判斷 name ?
		// 2. 根據姓名取得 Person
		Person person = personService.getPerson(name);
		return person;
	}
}
