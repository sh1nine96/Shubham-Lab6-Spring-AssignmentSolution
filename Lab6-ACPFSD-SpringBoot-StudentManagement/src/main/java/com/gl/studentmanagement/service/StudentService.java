package com.gl.studentmanagement.service;

import java.util.List;

import com.gl.studentmanagement.entity.Student;

public interface StudentService {
	
	List<Student> listAll();

	void save(Student student);
	
	Student findById(int theId);
	void deleteById(int theId);

}
