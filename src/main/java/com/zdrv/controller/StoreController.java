package com.zdrv.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdrv.domain.Store;
import com.zdrv.service.StoreService;

@Controller
@RequestMapping("/store")
public class StoreController {
	
	@Autowired
	private StoreService service;
	
	@GetMapping
	public String list(Model model) {
		model.addAttribute("store",service.selectAll());
		return "storeList";
	}
	
	
	
	@GetMapping("/add")
	public String add(Model model) {
		Store store = new Store();
		model.addAttribute("store",store);
		return "addStore";
	}
	
	@PostMapping("/add")
	public String addPost(@Valid Store store,Errors errors,HttpSession session) {
		if(errors.hasErrors()) {
			return "addStore";
		}
		session.setAttribute("addStore",store);
		return "redirect:/store/add/verification";
	}
	
	@GetMapping("/add2")
	public String add2(Model model,HttpSession session) {
		Store store=(Store)session.getAttribute("addStore");
		model.addAttribute("store",store);
		return "addStore2";
		
	}
	
	@PostMapping("/add2")
	public String addPost2(@Valid Store store,Errors errors,HttpSession session) {
		if(errors.hasErrors()) {
			return "addStore2";
		}
		session.setAttribute("addStore",store);
		
		 return "redirect:/store/add/verification";
	}
	
	@GetMapping("/add/verification")
	public String addVerification(Model model,HttpSession session) {
		Store store=(Store)session.getAttribute("addStore");
		model.addAttribute("store",store);
		return "addStoreVerification";
	}

	
	@GetMapping("/add/done")
	public String done(HttpSession session) {
		Store store=(Store)session.getAttribute("addStore");
		service.addStore(store);
		
		session.removeAttribute("addStore");
		return "addStoreDone";
	}
	
	
	@GetMapping("/edit/{storeId}")
	public String edit(@PathVariable Integer storeId,Model model) {
		Store store = service.selectById(storeId);
		System.out.println(store);
		model.addAttribute("store",store);
		return "storeEdit";
	}
	
	@PostMapping("/edit/{storeId}")
	public String editPost(@Valid Store store,Errors errors,HttpSession session) {
		
		if(errors.hasErrors()) {
			return "storeEdit";
		}
		
		session.setAttribute("editStore", store);
		return "storeEditVerification";
	}
	
	@GetMapping("/edit2/{id}")
	public String storeEdit2(Model model,HttpSession session) {
		Store store=(Store) session.getAttribute("editStore");
		model.addAttribute("store",store);
		return "storeEdit2";
	}
	
	@PostMapping("/edit2/{id}")
	public String edit2Post(@Valid Store store,Errors errors,HttpSession session) {
		if(errors.hasErrors()) {
			return "storeEdit2";
		}
		session.setAttribute("editStore", store);
		return "redirect:/store/edit/verification";
	}
	
	@GetMapping("/edit/verification")
	public String storeEditVerification(Model model,HttpSession session) {
		Store store=(Store) session.getAttribute("editStore");
		model.addAttribute("store",store);
		return "storeEditVerification";
	}
	
	
	@GetMapping("/edit/done")
		public String editVerification(HttpSession session) {
		Store store = (Store) session.getAttribute("editStore");
			service.update(store);
			
			session.removeAttribute("editStore");
			return "storeEditDone"; 
		}
	
	@GetMapping("/delete/{storeId}")
	public String delete(@PathVariable int storeId,Store store,Model model,HttpSession session) {
		Store store1 = service.selectById(storeId);
		model.addAttribute("store",store1);
		return "storeDelete";
		
	}
	
	@PostMapping("/delete/{storeId}")
	public String deleteDone(@PathVariable int storeId ) {
		
		service.delete(storeId);
		
		
		return "storeDeleteDone";
	}
	
		
	}
	
	
