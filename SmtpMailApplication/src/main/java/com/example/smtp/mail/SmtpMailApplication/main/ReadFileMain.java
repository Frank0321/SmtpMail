/**
 * @Description : 讀取檔案
 * @ClassName : ReadFileMain.java
 * @Copyright : Copyright (c) 2025 
 * @ModifyHistory : 
 *  v1.00, 2025/05/10, frankchang
 *   1) First Release.
 */

package com.example.smtp.mail.SmtpMailApplication.main;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import org.springframework.util.ResourceUtils;

public class ReadFileMain {

	private static String filePath = "MailContent.txt";

	/***
	 * 讀取 resources 底下檔案夾
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {

		File file = ResourceUtils.getFile("classpath:" + filePath);
		String fileContent = new String(Files.readAllBytes(file.toPath()), StandardCharsets.UTF_8);

		System.out.println(fileContent);
	}

}
