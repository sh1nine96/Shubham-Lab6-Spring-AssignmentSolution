package com.gl.studentmanagement.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.gl.studentmanagement.entity.Student;
import com.gl.studentmanagement.service.StudentService;

@Controller
@RequestMapping("/students")
public class StudentsController {
	
	@Autowired
	StudentService studentService;
	
	@RequestMapping("/list")
	public String handleStudentsListing(Model theModel) {		
		List<Student> students = studentService.listAll();
		theModel.addAttribute("students", students);
		return "students-lister";
	}
	
	@RequestMapping("/403")
	public ModelAndView accessDenied(Principal user) {
		ModelAndView model = new ModelAndView();
		
		if(user!= null) {
			model.addObject("msg", "Hi " + user.getName() 
			+", You don't have the permission to perform this operation!");
						
		} else {
			model.addObject("msg", "You don't have access to perform this operation!");
		}
		
		    model.setViewName("403");
		    return model;
	}

}
