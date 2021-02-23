package in.tejasviajjapla.springbootmongodb.service;

import java.util.List;

import in.tejasviajjapla.springbootmongodb.exception.EmployeeException;
import in.tejasviajjapla.springbootmongodb.model.EmployeeDTO;

public interface EmployeeService {

	public List<EmployeeDTO> getAllTodos();

	public EmployeeDTO getSingleTodo(String id) throws EmployeeException;

	public void createTodo(EmployeeDTO employee) throws EmployeeException;

	public void updateTodo(String id, EmployeeDTO employee) throws EmployeeException;

	public void deleteTodoById(String id) throws EmployeeException;

}
