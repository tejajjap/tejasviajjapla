package in.tejasviajjapla.springbootmongodb.exception;

public class EmployeeException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public EmployeeException(String message) {
		super(message);
	}

	public static String NotFoundException(String id) {
		return "Employee with " + id + " not found!";
	}

	public static String TodoAlreadyExists() {
		return "Employee with given name already exists";
	}

}
