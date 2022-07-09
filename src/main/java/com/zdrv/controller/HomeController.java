package com.zdrv.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdrv.domain.Course;
import com.zdrv.domain.CourseUpdate;
import com.zdrv.domain.User;
import com.zdrv.service.CourseService;
import com.zdrv.service.CourseUpdateService;



@Controller
@RequestMapping("/home")
public class HomeController {
	
	@Autowired
	private CourseService courseService;
	
	
	
	@Autowired
	private CourseUpdateService courseUpdateService;
	
	
	@GetMapping("/administrator")
	public String home(HttpSession session,Model model) {
		User loginUser = (User) session.getAttribute("loginUser");
		List<CourseUpdate> courseUpdateSelectAll = courseUpdateService.selectAll();
		model.addAttribute("courseUpdateSelectAll",courseUpdateSelectAll);
		return "homeAdministrator";
	}

	@GetMapping("/staff")
	public String homeStaff(Model model,HttpSession session) {
		User loginUser =(User)session.getAttribute("loginUser");
		session.setAttribute("loginUser",loginUser);
		List<Course> course =courseService.selectRequiredCourse(loginUser.getMember().getPositionName()); 
		return "homeStaff";
	}
	
	@GetMapping("/storeManeger")
	public String homeStoreManeger() {
		return "homeStoreManeger";
		
	}
	@GetMapping("/ariaManeger")
	public String homeAriaManeger() {
		return "homeAriaManeger";
	}
}
