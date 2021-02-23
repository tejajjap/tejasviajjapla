package in.tejasviajjapla.springbootmongodb.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.tejasviajjapla.springbootmongodb.exception.EmployeeException;
import in.tejasviajjapla.springbootmongodb.model.EmployeeDTO;
import in.tejasviajjapla.springbootmongodb.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	@Override
	public List<EmployeeDTO> getAllTodos() {
		List<EmployeeDTO> employeeList = employeeRepository.findAll();
		if (employeeList.size() > 0) {
			return employeeList;
		} else {
			return new ArrayList<EmployeeDTO>();
		}
	}

	@Override
	public EmployeeDTO getSingleTodo(String id) throws EmployeeException {
		Optional<EmployeeDTO> todoOptional = employeeRepository.findByEmpId(id);
		if (!todoOptional.isPresent()) {
			throw new EmployeeException(EmployeeException.NotFoundException(id));
		} else {
			return todoOptional.get();
		}
	}

	@Override
	public void createTodo(EmployeeDTO employee) throws EmployeeException {
		Optional<EmployeeDTO> employeeOptional = employeeRepository.findByEmpId(employee.getEmpId());
		if (employeeOptional.isPresent()) {
			throw new EmployeeException(EmployeeException.TodoAlreadyExists());
		} else {
			employee.setJoinedDate(new Date(System.currentTimeMillis()));
			employeeRepository.save(employee);
		}

	}

	@Override
	public void updateTodo(String id, EmployeeDTO employee) throws EmployeeException {
		Optional<EmployeeDTO> todoWithId = employeeRepository.findByEmpId(id);
		Optional<EmployeeDTO> todoWithSameName = employeeRepository.findByEmpId(employee.getFirstName());
		if (todoWithId.isPresent()) {
			if (todoWithSameName.isPresent() && !todoWithSameName.get().getId().equals(id)) {

				throw new EmployeeException(EmployeeException.TodoAlreadyExists());
			}
			EmployeeDTO employeeDtoToUpdate = todoWithId.get();

			employeeDtoToUpdate.setFirstName(employee.getFirstName());
			employeeDtoToUpdate.setLastName(employee.getLastName());
			employeeDtoToUpdate.setContactNumber(employee.getContactNumber());
			employeeDtoToUpdate.setCurrentEmployee(employee.getCurrentEmployee());

			employeeRepository.save(employeeDtoToUpdate);
		} else {
			throw new EmployeeException(EmployeeException.NotFoundException(id));
		}

	}

	@Override
	public void deleteTodoById(String id) throws EmployeeException {
		Optional<EmployeeDTO> todoOptional = employeeRepository.findByEmpId(id);
		if (!todoOptional.isPresent()) {
			throw new EmployeeException(EmployeeException.NotFoundException(id));
		} else {
			employeeRepository.deleteById(id);
		}

	}

}
