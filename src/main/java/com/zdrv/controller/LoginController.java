package com.zdrv.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.zdrv.domain.User;
import com.zdrv.service.UserService;

@Controller
public class LoginController {
	
	@Autowired
	private UserService service;
	
	@GetMapping("/login")
	public String login(Model model) {
		User user = new User();
		model.addAttribute("user",user);
		return "login";
	}
	
	@PostMapping("/login")
	public String loginPost(@Valid User user,Errors errors,HttpSession session) {
		User user1 = service.selectByUser(user.getLoginId(),user.getLoginPass());
		System.out.println(user1);
	    if(errors.hasErrors()) {
	    	return "login";
	    }
	    
	    if(user1 == null) {
	    	errors.rejectValue("loginId","wrong_id_or_password");
			return "login";
		}
	
	    session.setAttribute("loginUser", user1);
	    
		if((user1.getMember().getPositionName()).equals("スタッフ")) {
			return "redirect:/home/staff";
		}else if((user1.getMember().getPositionName()).equals("店長")) {
			return "redirect:/home/storeManeger";
		}else if((user1.getMember().getPositionName()).equals("エリアマネージャー")){
			return "redirect:/home/ariaManeger";
		}else if((user1.getMember().getPositionName()).equals("本部管理者")){
			return "redirect:/home/administrator";
		}else {
			System.out.println(user1);
		return "login";	
		}    
		
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return  "redirect:/login";
	}
	
	
}
