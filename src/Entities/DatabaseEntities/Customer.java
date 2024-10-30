package Entities.DatabaseEntities;

public class Customer extends User {

	public Customer(int userID, String userDOB, String userEmail, String userPassword, String userGender,
			String userRole) {
		super(userID, userDOB, userEmail, userPassword, userGender, userRole);
	}

}
