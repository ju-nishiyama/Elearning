package com.zdrv.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CourseUpdate {
	
	private int courseId;
	private int typeId;
	private String courseTitle;
	private Date updateDate;

}
