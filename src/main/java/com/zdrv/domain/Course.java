package com.zdrv.domain;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class Course {
	
	private int courseId;
	
	@NotBlank(message="タイトルを入力してください")
	private String title;
	
	@NotBlank(message="研修の内容を入力してください")
	private String contents;
	
	private String contentsType;
	
	private String requiredSubject;
	
	@NotNull(message="対象者を選択してください")
	private String targetLevel;
	
	private String author;
	
	private Date createDate;
	
	private String courseUrl;
	private List<CourseHistory> courseHistory;

}
