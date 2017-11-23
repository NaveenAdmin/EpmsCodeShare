/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-10-30.
*/
package com.hpcl.persistence;

import org.hibernate.validator.constraints.NotEmpty;

public class LoginPersistance {
@NotEmpty	
private String employeeId;

@NotEmpty
private String password;

private String version;



public String getVersion() {
	return version;
}


public void setVersion(String version) {
	this.version = version;
}


/**
 * @return the employeeId
 */
public String getEmployeeId() {
	return employeeId;
}


/**
 * @param employeeId the employeeId to set
 */
public void setEmployeeId(String employeeId) {
	this.employeeId = employeeId;
}

/**
 * @return the password
 */
public String getPassword() {
	return password;
}

/**
 * @param password the password to set
 */
public void setPassword(String password) {
	this.password = password;
}




}
