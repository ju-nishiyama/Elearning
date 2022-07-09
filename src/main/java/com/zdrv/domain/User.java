package com.zdrv.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class User {
	
	public Integer id;
	
	@NotBlank(message="ログインIDを入力してください")
	public String loginId;
	
	@NotBlank(message="ログインパスワードを入力して下さい")
	@Size(min=8,message="パスワードは8文字以上で入力してください")
	public String loginPass;
	
	
	public String loginPassConf;
	
	@Valid
	private Member member;
	
	

}
