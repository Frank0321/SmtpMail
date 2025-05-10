/**
 * @Description : 寄送者相關資訊
 * @ClassName : SenderConfig.java
 * @Copyright : Copyright (c) 2025 
 * @ModifyHistory : 
 *  v1.00, 2025/05/10, frankchang
 *   1) First Release.
 */

package com.example.smtp.mail.SmtpMailApplication.config;

import lombok.Data;

@Data
public class SenderConfig {
	
	/** 寄件者 */
	private String snederEmail = "";
	
	/** 收件者 */
	private String email = "";
	
	/** 標題 */
	private String subject = "測試 Hotmail SMTP";
	
	/** 內容 */
	private String content = "這是從 Java 發出的測試郵件！";

}
