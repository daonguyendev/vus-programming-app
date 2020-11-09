package com.vus;

import java.util.Scanner;

import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.Version;
import com.restfb.types.FacebookType;
import com.restfb.types.Page;

/**
 * Create post to your new feed page
 **/
public class PagePoster {

	public static void main(String[] args) {
		FacebookClient fbClient = new DefaultFacebookClient(CommonConstant.ACCESS_TOKEN, Version.VERSION_3_1);
		Page page = fbClient.fetchObject(CommonConstant.PAGE_ID, Page.class);
		
		System.out.println("Tên của page là: " + page.getName());
		System.out.println("Id của page là: " + page.getId());
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("\nBạn có muốn đăng bài gì lên trang " + page.getName() + " không (Yes/No)?");
		
		String answer = scanner.nextLine();
		if (answer.equalsIgnoreCase(CommonConstant.ANSWER_YES)) {
			System.err.println("Mời bạn nhập nội dung muốn đăng lên trang này?");
			String message = scanner.nextLine();
			FacebookType response = fbClient.publish(page.getId() + CommonConstant.PAGE_NEW_FEED_URL, 
														FacebookType.class, 
														Parameter.with(CommonConstant.MESSAGE_PARAMETER, message));
			System.out.println(CommonConstant.FACEBOOK_URL + response.getId());
		} else {
			System.err.println("Cảm ơn bạn đã dùng ứng dụng!");
		}
	}

}
