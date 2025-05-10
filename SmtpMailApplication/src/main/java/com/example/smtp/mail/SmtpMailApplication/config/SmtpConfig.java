/**
 * @Description : smtp 相關設定檔
 * @ClassName : EmailConfig.java
 * @Copyright : Copyright (c) 2025 
 * @ModifyHistory : 
 *  v1.00, 2025/05/10, frankchang
 *   1) First Release.
 */

package com.example.smtp.mail.SmtpMailApplication.config;

import lombok.Data;

@Data
public class SmtpConfig {
	
	/** smtp 帳號 */
	private String userName = "";

	/** smtp 密碼 */
	private String userPxxd = "";
	
}
