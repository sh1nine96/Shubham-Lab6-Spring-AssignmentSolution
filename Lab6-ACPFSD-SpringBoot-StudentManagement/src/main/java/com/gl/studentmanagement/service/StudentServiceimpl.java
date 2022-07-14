package com.gl.studentmanagement.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.gl.studentmanagement.entity.Student;
import com.gl.studentmanagement.repository.StudentRepository;

@Repository
public class StudentServiceimpl implements StudentService{
	
	@Autowired
    StudentRepository studentRepository;

	@Override
	public List<Student> listAll() {
		List<Student> students = studentRepository.findAll();
		return students;
	}
	
	public void save(Student theStudent) {
		studentRepository.save(theStudent);
	}
	
	public Student findById(int id) {

        return studentRepository.findById(id).get();
    }

	@Override
	public void deleteById(int theId) {
		studentRepository.deleteById(theId);
		
	}

	

}
