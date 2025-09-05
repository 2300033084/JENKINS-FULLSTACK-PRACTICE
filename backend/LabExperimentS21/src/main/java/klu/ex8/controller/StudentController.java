package klu.ex8.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.DeleteExchange;

import klu.ex8.model.Student;
import klu.ex8.model.StudentManager;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
public class StudentController {
	
	@Autowired
	StudentManager SM;
	
	@PostMapping("/student")
	public String InsertStudent(@RequestBody Student student) {
		return SM.insertData(student);
	}
	@PutMapping("/student")
	  public String updateStudent(@RequestBody Student student) {
	    return SM.updateData(student);
	  }
	  @DeleteExchange("/student/{id}")
	  public String deleteStudent(@PathVariable("id") int id) {
	    return SM.deleteData(id);
	  }
	  @GetMapping("/student")
	  public List<Student> retrieveStudent(){
	    return SM.retrieveData();
	  }

}
