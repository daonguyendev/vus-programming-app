package com.vus;

import java.util.Scanner;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;

/**
 * Tính năng tạo bài đăng lên facebook page
 **/
public class PagePoster {
	
	// Bước 1: Lấy thông tin chứng thực tài khoản của facebook page và page ID
	private static final String ACCESS_TOKEN = "EAAFC0U3klsQBAKpsYNV3F01NBne70tyPhZCpQLZCOwGyJZCtPWGGYcSj61hHUc3V2fXzu5sDW3xnp3JxcgJS2Gfow7nMLdphdiAWtHP00XZC3zRIOpunbUaZBmQ7qgE7waS4fiRuEMOvLxzBvHX66FVHlMxJZAZBMSzMk058eoyssDVySxrq7xrqqJ9bHaSFU0M3UoloumfSgZDZD";
	private static final String PAGE_ID = "106332297955711";

	public static void main(String[] args) {
		// Bước 2: Thiết lập thông tin chứng thực tài khoản của facebook page và page ID
		// -> để chứng minh với facebook tôi là chủ của facebook page này
		FacebookClient fbClient = new DefaultFacebookClient(ACCESS_TOKEN, Version.VERSION_3_1);
		Page page = fbClient.fetchObject(PAGE_ID, Page.class);
		
		// Bước 3: Kiểm tra việc chứng thực tài khoản thành công hay chưa
		if (page.getId().equals(PAGE_ID)) {
			System.out.println("Bạn chính xác là chủ của trang facebook " + page.getName());
		}
		
		// Bước 4: Hỏi người dùng muốn đăng bài gì lên trang facebook này hay không
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nBạn có muốn đăng bài gì lên trang " + page.getName() + " không (Yes/No)?");
		
		// Bước 5: Đăng bài hoặc thoát chương trình
		String answer = scanner.nextLine();
		
		// Bước 5.1: Nếu người dùng trả lời là Yes (muốn đăng bài) 
		// -> mời người ta nhập nội dung bài đăng và thực hiện đăng bài lên trang facebook này
		if (answer.equalsIgnoreCase("Yes")) {
			System.err.println("Mời bạn nhập nội dung muốn đăng lên trang này?");
			String message = scanner.nextLine();
			FacebookType response = fbClient.publish(page.getId() + "/feed", FacebookType.class, Parameter.with("message", message));
			System.out.println("fb.com/" + response.getId());
		} else {
			// Bước 5.2: Nếu người dùng trả lời là No (không muốn đăng bài) 
			// -> cảm ơn người dùng đã dùng ứng dụng và thoát chương trình
			System.err.println("Cảm ơn bạn đã dùng ứng dụng!");
		}
	}

}
