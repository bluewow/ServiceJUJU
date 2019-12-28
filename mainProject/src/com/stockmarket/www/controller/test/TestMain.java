package com.stockmarket.www.controller.test;

import java.util.Scanner;

public class TestMain {
	public static void main(String[] args) {
		int testIndex = 0;

		while (true) {
			Scanner sc = new Scanner(System.in);
			viewPrint();
			testIndex = sc.nextInt();

			switch (testIndex) {
			case 1:
				SystemServiceTest();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
			case 5:
				break;
			case 6:
				break;
			}
		}

	}

	private static void viewPrint() {
		clearScreen();
		System.out.println("-----------------------------");
		System.out.println("1.SystemService Test");
		System.out.println("2. ");
		System.out.println("3. ");
		System.out.println("4. ");
		System.out.println("5. ");
		System.out.println("6. ");
		System.out.println("7. ");
		System.out.println("8. ");
		System.out.println("9. ");
		System.out.println("10. ");
		System.out.println("-----------------------------");
		System.out.println("숫자를 입력하시오");
	}

	public static void clearScreen() {
		for (int i = 0; i < 80; i++)
			System.out.println("");
	}

	private static void SystemServiceTest() {
		TestSystemService service = new TestSystemService();
		service.test();
	}

}
