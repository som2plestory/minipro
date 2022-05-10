package com.javaex.phonebook;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PhoneDBApp {
	
	public static void main(String[] args) throws IOException{
		
		Reader fr = new FileReader("./phoneDB.txt");
		BufferedReader br = new BufferedReader(fr);
		
		List<Person> dbList = new ArrayList<Person>();
		
		while(true) {
			String str = br.readLine();
			if(str == null) {
				break;
			}
			String[] phoneDB = str.split(",");
			
			String name = phoneDB[0];
			String hp = phoneDB[1];
			String company = phoneDB[2];
			
			Person p = new Person(name, hp, company);	
			dbList.add(p);
		}
		
		Writer fw = new FileWriter("./phoneDB.txt");
		BufferedWriter bw = new BufferedWriter(fw);
		
		for(Person p : dbList) {
			String saveStr = p.getName() +"," + p.getHp() +"," +p.getCompany();
			bw.write(saveStr);
			bw.newLine();
		}
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("********************************");
		System.out.println("*       전화번호 관리 프로그램      *");
		System.out.println("********************************");
		while(true) {
			System.out.println("");
			System.out.println("1.리스트 2.등록 3.삭제 4.검색 5.종료");
			System.out.println("--------------------------------");
			System.out.print(">메뉴번호: ");
			int menuN = sc.nextInt();
		
			if(menuN==5) {
				System.out.println("");
				System.out.println("********************************");
				System.out.println("*           감사합니다.           *");
				System.out.println("********************************");
				break;
			}else {
				switch(menuN) {
					case 1:
						System.out.println("<1.리스트>");
						for(int i=0; i<dbList.size(); i++) {
							System.out.println((i+1)+".\t"+dbList.get(i).name+"\t"
												+dbList.get(i).hp+"\t"+dbList.get(i).company);
						}
						break;
					case 2:
						System.out.println("<2.등록>");
						Person p01 = new Person();
						System.out.print(">이름: ");
						p01.name = sc.next();
						System.out.print(">휴대전화: ");
						p01.hp = sc.next();
						System.out.print(">회사전화: ");
						p01.company = sc.next();
						dbList.add(p01);
						System.out.println("등록되었습니다.");
						break;
					case 3:
						System.out.println("<3.삭제>");
						System.out.print(">번호 : ");
						int removeN = sc.nextInt();
						int rmN = removeN -1;
						dbList.remove(rmN);
						break;
					case 4:
						System.out.println("<4.검색>");
						System.out.print(">이름: ");
						String search = sc.next();
						for(int i=0; i<dbList.size(); i++) {
							if((dbList.get(i).name).contains(search)) {
								System.out.println((i+1)+".\t"+dbList.get(i).name+"\t"
										+dbList.get(i).hp+"\t"+dbList.get(i).company);
							}
						}
						break;
					default:
						System.out.println("[다시 입력해 주세요.]");
						break;
				}
			}
			bw.close();
			br.close();
		}
		
		sc.close();
	}
	
}
