package com.study.springcore.case08;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PersonSystem {
	private ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext8.xml");
	private PersonController personController = ctx.getBean("personController", PersonController.class);
	private boolean stop;
	
	private void menu() {
		System.out.println("=========================================");
		System.out.println("1. 建立 Person 資料");
		System.out.println("2. 取得 Person 全部資料");
		// 作業 2:
		System.out.println("3. 根據姓名取得 Person");
		System.out.println("4. 取得今天生日的 Person");
		System.out.println("5. 取得某一歲數以上的 Person");
		System.out.println("6. 根據姓名來修改Person的生日");
		System.out.println("7. 根據姓名來刪除Person");
		System.out.println("0. 離開");
		System.out.println("=========================================");
		System.out.print("請選擇: ");
		
		Scanner sc = new Scanner(System.in);
		int choice = sc.nextInt();
		switch (choice) {
			case 1:
				createPerson();
				break;
			case 2:
				printPersons();
				break;
		//-----------------------------------------
			case 3:
				getPersonsName();
				break;
			case 4:
				getPersonsBirthToday();
				break;
			case 5:
				getPersonsOverOld();
				break;
			case 6:
				setPersonsBirth();
				break;
			case 7:
				delPersons();
				break;
		//-----------------------------------------
			case 0:
				System.out.println("離開系統");
				stop = true;
				break;
		}
	}
	
	private void createPerson() {
		System.out.print("請輸入姓名 生日年 月 日: ");
		// Ex: Jack 1999 4 5
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		int yyyy = sc.nextInt();
		int mm = sc.nextInt();
		int dd = sc.nextInt();
		personController.addPerson(name, yyyy, mm, dd);
	}
	
	private void printPersons() {
		personController.printAllPersons();
	}
	
	//-----------------------------------------
	private void getPersonsName() {
		System.out.print("請輸入姓名: ");
		// Ex: Jack
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		Person result = personController.getPersonByName(name);
		
		System.out.println("+--------------+---------+--------------+");
		System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
		System.out.println("+--------------+---------+--------------+");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		
		String birthday = sdf.format(result.getBirth());
		System.out.printf("| %-12s | %7d | %12s |\n", result.getName(), result.getAge(), birthday);
		System.out.println("+--------------+---------+--------------+");
	}
	
	private void getPersonsBirthToday() {
		personController.printAllPersonsBirthToday();
	}
	
	private void getPersonsOverOld() {
		System.out.print("請輸入年齡: ");
		// Ex: 18
		Scanner sc = new Scanner(System.in);
		int age = sc.nextInt();
		personController.printPersonsOverOld(age);
	}
	
	private void setPersonsBirth() {
		System.out.print("請輸入姓名: ");
		// Ex: Jack
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		if(name != null) {
			System.out.print("請輸入生日: ");
			// Ex: 1999 4 5
			Scanner sc2 = new Scanner(System.in);
			int yyyy = sc2.nextInt();
			int mm = sc2.nextInt();
			int dd = sc2.nextInt();
			personController.setPersonsBirth(name, yyyy, mm, dd);
		}
	}
	
	private void delPersons() {
		System.out.print("請輸入姓名: ");
		// Ex: Jack
		Scanner sc = new Scanner(System.in);
		String name = sc.next();
		if(name != null) {
			Person result = personController.getPersonByName(name);
			
			/*System.out.println("+--------------+---------+--------------+");
			System.out.println("|     name     |   age   |   birthday   |"); // 12, 7, 12
			System.out.println("+--------------+---------+--------------+");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			
			String birthday = sdf.format(result.getBirth());
			System.out.printf("| %-12s | %7d | %12s |\n", result.getName(), result.getAge(), birthday);
			System.out.println("+--------------+---------+--------------+");
			
			System.out.print("請確認是否刪除此筆資料(Y/N): ");
			// Ex: Y
			Scanner sc2 = new Scanner(System.in);
			String confirm = sc2.next();
			
			if(name == "Y") {*/
				personController.delPersons(name);
			/*}else{
				System.out.println("已取消操作...");
			}*/
		}
	}
	//-----------------------------------------
	
	public void start() {
		while (!stop) {
			menu();
		}
	}
	
	
	public static void main(String[] args) {
		new PersonSystem().start();
	}

}
