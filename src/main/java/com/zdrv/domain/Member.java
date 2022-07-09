package com.zdrv.domain;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import lombok.Data;

@Data
public class Member {
	
	
	private Integer memberId;
	
	@NotBlank(message="名前を入力してください")
	private String name;
	
	
	@NotBlank(message="フリガナを入力してください")
	private String kana;
	
	@Min(value=18,message="年齢を正しく入力してください")
	@Max(value=100,message="年齢を正しく入力してください")
	private int age;
	
	@NotNull(message="生年月日を選択してください")
	private Date birthday;
	
	
	private String positionName;
	
	@NotNull(message="入社日を選択してください")
	private Date hireDate;
	
	
	private String workStore;

}