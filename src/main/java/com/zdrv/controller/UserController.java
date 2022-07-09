package com.zdrv.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

import com.zdrv.domain.Member;
import com.zdrv.domain.Store;
import com.zdrv.domain.User;
import com.zdrv.service.MemberService;
import com.zdrv.service.StoreService;
import com.zdrv.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private StoreService storeService;
	
	@Autowired
	private MemberService memberService;
	
	
	@InitBinder
	public void initBinderForm(WebDataBinder binder) {
		var sdf = new SimpleDateFormat("yyyy-MM-dd");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(sdf, true));
	}
	
	@GetMapping
	public String userList(Model model) {
		model.addAttribute("user",userService.selectAllUsers());
		model.addAttribute("member",new Member());
		return "userList";
	}
	
	@PostMapping
	public String userListPost(Model model ,Member member,HttpSession session) {
		
		//member型の検索結果格納リスト
		List<Member> selectMember=new ArrayList<>();
		
		//user型の検索結果格納リスト
		List<User> selectUser = new ArrayList<>();
		
		//入力によって条件分岐
		if(member.getName()!=null && member.getPositionName()!=null && !member.getWorkStore().equals("")) {
			selectMember.addAll(memberService.selectByName_Position_Store
					(member.getName(), member.getPositionName(), member.getWorkStore()));
			
		}else if(!member.getName().equals("") && member.getPositionName()!=null ){
			selectMember.addAll(memberService.selectByName_Position
					(member.getName(), member.getPositionName()));
			
		}else if(!member.getName().equals("") &&  !member.getWorkStore().equals("")) {
			selectMember.addAll(memberService.selectByName_Store
					(member.getName(),  member.getWorkStore()));
			
		}else if(member.getPositionName()!=null && !member.getWorkStore().equals("")) {
			selectMember.addAll(memberService.selectByPosition_Store
					( member.getPositionName(), member.getWorkStore()));
			
		}else if( !member.getName().equals("") ) {
			selectMember.addAll(memberService.selectByName(member.getName()));
			
		}else if( member.getPositionName()!=null && member.getName().equals("")) {
			selectMember.addAll(memberService.selectByPosition(member.getPositionName()));
			
		}else if( !member.getWorkStore().equals("") && member.getName().equals("")) {
			selectMember.addAll(memberService.selectByStore(member.getWorkStore()));
			
		}
		
		
		//検索で取り出したmemberリストをuserオブジェクトに変換し画面に出力する
		for(Member member1:selectMember) {
			selectUser.add(userService.selectById(member1.getMemberId()));		
		}
		
		
		model.addAttribute("selectUser",selectUser);
		
		return "userList2";
	}
	
	@GetMapping("/add")
	public String add(Model model,HttpSession session) {
		User user = new User();
		model.addAttribute("user",user);
		List<Store> storeAll=storeService.selectAll();
		System.out.println(storeAll);
		model.addAttribute("storeAll", storeAll);
		
		return "addUser";
	};
	
	@PostMapping("/add")
	public String addPost(@Valid User user,Errors errors,Model model,HttpSession session) {
		if(errors.hasErrors()) {
			return "addUser";
		}
		
		String pass=user.getLoginPass();
		String passConf=user.getLoginPassConf();
		
		if(!pass.equals(passConf)) {
			errors.rejectValue("loginPassConf","login_pass_conf");
			return "addUser";
		}
		
		
		session.setAttribute("addUser", user);
		return "redirect:/user/add/verification";
		
	}
	
	
	@GetMapping("/add2")
	public String addPos2(Model model,HttpSession session) {
		User user = (User) session.getAttribute("addUser");
		model.addAttribute("user",user);
		model.addAttribute("storeAll",storeService.selectAll());
		return "addUser2";
	}
	
	@PostMapping("/add2")
	public String addPost2(@Valid User user,Errors errors,HttpSession session) {
		if(errors.hasErrors()) {
			return "addUser2";
		}
			
		String pass=user.getLoginPass();
		String passConf=user.getLoginPassConf();
		
		if(!pass.equals(passConf)) {
			errors.rejectValue("loginPassConf","login_pass_conf");
			return "addUser2";
		}
		session.setAttribute("addUser",user);
		return "redirect:/user/add/verification";
		
	}
	
	@GetMapping("/add/verification")
	public String addVerification(Model model,HttpSession session) {
		User user= (User) session.getAttribute("addUser");
		model.addAttribute("user",user);
		
		
		return "addUserVerification";
	}
	
	
	
	@GetMapping("/add/done")
	public String addDone(HttpSession session) {
		User user = (User) session.getAttribute("addUser");
		userService.add(user);
		
		session.removeAttribute("addUser");
		return "addUserDone";
	}
	
	
	
	@GetMapping("/edit/{id}")
	public String userEdit(@PathVariable int id,Model model) {
		User user = userService.selectById(id);
		List<Store> store = storeService.selectAll();
		model.addAttribute("user",user);
		model.addAttribute("store",store);
		return "userEdit";
	}
	
	@PostMapping("/edit/{id}")
	public String userEditPost(@Valid User user,Errors errors,HttpSession session) {
		if(errors.hasErrors()) {
			return "userEdit";
		}
		
		String pass=user.getLoginPass();
		String passConf=user.getLoginPassConf();
		
		if(!pass.equals(passConf)) {
			errors.rejectValue("loginPassConf","login_pass_conf");
			return "userEdit";
		}
		
		
		session.setAttribute("editUser", user);
		return "redirect:/user/edit/verification";
	}
	
	@GetMapping("/edit2/{id}")
	public String userEdit2(Model model,HttpSession session) {
	User user = (User) session.getAttribute("editUser");
	List<Store> store = storeService.selectAll();
	model.addAttribute("user",user);
	model.addAttribute("store",store);
		return "userEdit2";	
	}
	
	
	@PostMapping("/edit2/{id}")
	public String userEditPost2(@Valid User user,Errors errors,HttpSession session) {
		if(errors.hasErrors()) {
			return "userEdit2";
		}
		
		String pass=user.getLoginPass();
		String passConf=user.getLoginPassConf();
		
		if(!pass.equals(passConf)) {
			errors.rejectValue("loginPassConf","login_pass_conf");
			return "userEdit";
		}
		
			
		
	return "redirect:/user/edit/verification";
	
	}
	
	@GetMapping("/edit/verification")
	public String editVerification(Model model,HttpSession session) {
		User user = (User) session.getAttribute("editUser");
		model.addAttribute("user",user);
		return "userEditVerification";
	}
	
	@GetMapping("/edit/done")
	public String userEditDone(HttpSession session) {
		User user = (User) session.getAttribute("editUser");
		userService.userEdit(user);
		session.removeAttribute("editUser");
		return "userEditDone";
	}
	
	
	@GetMapping("/delete/{id}")
	public String userDelete(@PathVariable int id,Model model) {
		User user = userService.selectById(id);
		model.addAttribute("user",user);
		return "userDelete";
	}
	
	@PostMapping("/delete/{id}")
	public String userDeleteDone(@PathVariable int id) {
		userService.userDeleteById(id);
		return "userDeleteDone";
	}

}
