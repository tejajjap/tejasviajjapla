package in.tejasviajjapla.springbootmongodb.model;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "employees")
public class EmployeeDTO {

	@Id
	private String id;
	private String empId;
	private String firstName;
	private String lastName;
	private String contactNumber;
	private Boolean currentEmployee;
	private Date joinedDate;
}
