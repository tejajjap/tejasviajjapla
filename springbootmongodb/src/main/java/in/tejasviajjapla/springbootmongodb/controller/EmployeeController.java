package in.tejasviajjapla.springbootmongodb.controller;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import in.tejasviajjapla.springbootmongodb.exception.EmployeeException;
import in.tejasviajjapla.springbootmongodb.model.EmployeeDTO;
import in.tejasviajjapla.springbootmongodb.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/getallEmployees")
	public ResponseEntity<?> getAllEmployees() {
		List<EmployeeDTO> todos = employeeService.getAllTodos();
		return new ResponseEntity<>(todos, todos.size() > 0 ? HttpStatus.OK : HttpStatus.NOT_FOUND);
	}

	@PostMapping("/createEmployee")
	public ResponseEntity<?> createEmployee(@RequestBody EmployeeDTO employeeDto) {
		try {
			employeeService.createTodo(employeeDto);
			return new ResponseEntity<EmployeeDTO>(employeeDto, HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (EmployeeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.CONFLICT);
		}

	}

	@GetMapping("/employee/{id}")
	public ResponseEntity<?> getSingleTodo(@PathVariable("id") String id) {
		try {
			return new ResponseEntity<>(employeeService.getSingleTodo(id), HttpStatus.OK);
		} catch (EmployeeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/employee/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") String id) throws EmployeeException {
		try {
			employeeService.deleteTodoById(id);
			return new ResponseEntity<>("Successfully deleted todo with id " + id, HttpStatus.OK);
		} catch (EmployeeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping("/employee/{id}")
	public ResponseEntity<?> updateById(@PathVariable("id") String id, @RequestBody EmployeeDTO employeeDTO) {
		try {
			employeeService.updateTodo(id, employeeDTO);
			return new ResponseEntity<>("Updated movie with id " + id + "", HttpStatus.OK);
		} catch (ConstraintViolationException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.UNPROCESSABLE_ENTITY);
		} catch (EmployeeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
