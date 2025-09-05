package klu.ex8.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import klu.ex8.repository.StudentRepo;

@Service
public class StudentManager {
	
	@Autowired
	StudentRepo repo1;
	
	public String insertData(Student student)
	{
		repo1.save(student);
		return "Inserted Successfully";
	}
	
	public String updateData(Student student) {
		if(repo1.findById(student.getId()) != null)
			repo1.save(student);
		repo1.save(student);
		return "updated successfully";
	}
	
	public String deleteData(int id) {
	      repo1.delete(repo1.findById(id).get());
	      return "deleted successfully";
	      
	    }
	
	public List<Student> retrieveData(){
		return repo1.findAll();
	}

}
