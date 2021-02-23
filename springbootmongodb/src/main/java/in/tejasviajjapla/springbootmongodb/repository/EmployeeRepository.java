package in.tejasviajjapla.springbootmongodb.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import in.tejasviajjapla.springbootmongodb.model.EmployeeDTO;

@Repository
public interface EmployeeRepository extends MongoRepository<EmployeeDTO, String> {

	@Query("{'empId': ?0}")
	Optional<EmployeeDTO> findByEmpId(String empId);

}
