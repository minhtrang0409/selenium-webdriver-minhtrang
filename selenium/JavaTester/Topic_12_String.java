package JavaTester;

import java.util.Scanner;

public class Topic_12_String {
	//Nhập dữ liệu từ bàn phím
	Scanner scanner = new Scanner(System.in);
	public static void main(String[] args) {
		String schoolName = "Automation Testing Advanced";
		String courseName = "automation testing advanced";
		String schooladdress = "Ho Chi Minh";
		
		
		System.out.println("Do dai "+ schoolName.length());
		System.out.println("Lấy ra 1 kí tự "+ schoolName.charAt(0));
		System.out.println("Nối 2 chuỗi = "+ schoolName.concat(schooladdress));
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tuyệt đối = "+ schoolName.equals("Automation Testing Advanced"));
		System.out.println("Kiểm tra 2 chuỗi bằng nhau tương đối = "+ schoolName.equalsIgnoreCase(courseName));
		System.out.println(schoolName.length());
	}
	public void TC_02() {
		Scanner numberA = scanner.reset();
				
	}
}
