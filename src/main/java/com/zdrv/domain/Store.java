package com.zdrv.domain;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;
@Data
public class Store {
	
	
	private Integer storeId;
	
	@NotBlank(message="店舗名を入力してください")
	private String storeName;
	private String storeType;
	
	@NotBlank(message="住所を入力してください")
	@Size(max = 40)
	private String address;
	
	@NotNull(message="電話番号を入力してください")
	private Integer telephoneNumber;
	
	@NotBlank(message="営業時間を選択してください")
	private String openingHours;
	
	@NotBlank(message="終業時間を選択してください")
	private String closingHours;
	
	@NotBlank(message="休業日を選択してください")
	private String regularHoliday;
	
	
	
	
	
}
