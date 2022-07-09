package com.zdrv.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdrv.domain.Course;
import com.zdrv.domain.CourseHistory;
import com.zdrv.domain.User;
import com.zdrv.service.CourseHistoryService;
import com.zdrv.service.CourseService;

@Controller
@RequestMapping("/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@Autowired
	private CourseHistoryService courseHistoryservice;
	
	
	@InitBinder
	public void initBinderForm(WebDataBinder binder) {
		var sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	
	}
	
	
	@GetMapping
	public String courseList(Model model,HttpSession session) {
		User loginUser =(User)session.getAttribute("loginUser");
		
		//研修コース一覧と研修履歴一覧。関係は1対多。履歴は重複してるものもある
		List<CourseHistory> courseAll = courseHistoryservice.historyAll();
		Map<Integer,Date> map= new HashMap<Integer,Date>();
		
		
		for(CourseHistory course:courseAll) {
			if(course.getMemberId() == loginUser.getMember().getMemberId()){
				map.put(course.getCourseId(), course.getBrowsingDate());
			}
		
		};
		
		
		//Infomation用の設定
		
		
		
		
		
		//研修フラグ確認用リスト
		
		System.out.println(map);
		model.addAttribute("map",map);
		model.addAttribute("course",courseAll);
		return "courseList";
	}
	
	
		
	
	
	@GetMapping("/add")
	public String add(Model model) {
		Course course = new Course();
			model.addAttribute("course",course);
			return "addCourse";
			
	}
	
	@PostMapping("/add")
	public String addPost(@Valid Course course,Errors errors,HttpSession session) {
		if(errors.hasErrors()) {
			return "addCourse";
		}
		
		session.setAttribute("course",course);
		return "addCourseVerification";
	}
	
	@GetMapping("/add2")
	public String add2(Model model,HttpSession session) {
		Course course= (Course) session.getAttribute("course");
		model.addAttribute(course);
		return "addCourse2.html";
	}
	
	@PostMapping("/add2")
	public String add2Post(Model model,HttpSession session,@Valid Course course,Errors errors) {
		if(errors.hasErrors()) {
			return "addCourse2";
		}
		
		Course course1 = course;
		model.addAttribute("course",course1);
		session.setAttribute("course", course1);
		
		return "addCourseVerification";
	}
	
	@GetMapping("/add/done")
	public String addDone(HttpSession session) {
		Course course = (Course) session.getAttribute("course");
		Date date = new Date();
		course.setCreateDate(date);
		
		courseService.add(course);
		return "courseAddDone";
	}
	
	@GetMapping("/{id}")
	public String courseId(@PathVariable int id,Model model) {
		Course course = courseService.selectById(id);
		model.addAttribute("course",course);
		return "courseContents";
	}
	
	@GetMapping("/attend/{id}")
	public String courseAttend(@PathVariable int id,HttpSession session) {
		CourseHistory ch = new CourseHistory();
		User user = (User) session.getAttribute("loginUser");
		System.out.println(user);
		ch.setMemberId(user.getMember().getMemberId());
		ch.setMemberName(user.getMember().getName());
		ch.setCourseId(id);
		ch.setBrowsingDate(new Date());
		System.out.println(ch);
		courseHistoryservice.add(ch);
		return "courseAttend";
	}
	
	
	@GetMapping("/edit/{id}")
	public String courseEdit(@PathVariable int id,Model model) {
		Course course = courseService.selectById(id);
		model.addAttribute("course",course);
		return "courseEdit";
	}
	
	@PostMapping("/edit/{id}")
	public String courseEditPost(@PathVariable int id,@Valid Course course,Errors errors,HttpSession session) {
		if(errors.hasErrors()) {
			return "courseEdit";
		}
		
		session.setAttribute("course", course);
		return "courseEditVerification";
	}
	
	@GetMapping("/edit2/{id}")
	public String courseEdit2(Model model,HttpSession session) {
		Course course = (Course) session.getAttribute("course");
		model.addAttribute("course",course);
		return "courseEdit2";
	}
	
	@PostMapping("/edit2/{id}")
	public String courseEditPost2(@PathVariable int id,@Valid Course course,Errors errors,HttpSession session,Model model) {
		if(errors.hasErrors()) {
			return "courseEdit2";
		}
		Course course1 = course;
		model.addAttribute("course",course1);
		session.setAttribute("course", course1);
		return "courseEditVerification";
	}
	
	
	@GetMapping("/edit/done")
	public String courseEditDone(HttpSession session) {
		Course course = (Course) session.getAttribute("course");
		System.out.println(course);
		courseService.courseEdit(course);
		return "courseEditDone";
	}
	
	@GetMapping("/delete/{id}")
	public String courseDelete(@PathVariable int id,Model model) {
		Course course = courseService.selectById(id);
		model.addAttribute("course",course);
		return "courseDelete";
	}
	
	@PostMapping("/delete/{id}")
	public String courseDeleteDone(@PathVariable int id) {
		courseService.deleteById(id);
		return "courseDeleteDone";
	}
	


}
