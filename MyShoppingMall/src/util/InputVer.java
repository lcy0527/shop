package util;

import java.util.Scanner;

public class InputVer {
	public static Scanner input = new Scanner(System.in);

	public static int inputVerify(int num2, int num3) { // 防止选择项录入字母
		int num1 = 0;
		while (true) {
			if (input.hasNextInt()) {
				num1 = input.nextInt();
				input.nextLine();
				if (num1 <= num3 && num1 >= num2)
					break;
				else {
					System.out.println("请输入：" + num2 + "至" + num3 + "之间的整数");
					continue;
				}
			} else {
				System.out.println("录入错误，请输入整数:");
				input.nextLine();
				continue;
			}
		}
		return num1;
	}
}
