package JavaTester;

import java.util.Scanner;

import org.testng.annotations.Test;

public class Topic_09_While_do_while {
	static Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		TC_01();
		System.out.println("=============");
		TC_01();
		
	}
	@Test
	public static void  TC_01() {
		int number = scanner.nextInt();
		for (int i = number; i < 100; i++) {
			// Chia hết cho 2
			if (i % 2 == 0) {
				System.out.println(i);
			}
		}
	}
//	public static void  TC_02_While() {
//		int number = scanner.nextInt();
//		while(number<100) {
//		if
//		}
//	}
}
