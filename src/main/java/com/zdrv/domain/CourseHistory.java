package com.zdrv.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CourseHistory {
	
	private int courseId;
	private int historyId;
	private int memberId;
	private String memberName;
	private Date browsingDate;
	private Course course;
	
}
