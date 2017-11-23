/**
* @author  :Raj
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02
*/
package com.hpcl.persistence;

import java.util.List;

public class Person {
	
	private String id;
	private String name;
	private String department;
	private String location;
	private String zip;
	private String salary;
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	List<String> selectedCheckBox;
	List<Person> personsList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getSalary() {
		return salary;
	}
	public void setSalary(String salary) {
		this.salary = salary;
	}
	public List<String> getSelectedCheckBox() {
		return selectedCheckBox;
	}
	public void setSelectedCheckBox(List<String> selectedCheckBox) {
		this.selectedCheckBox = selectedCheckBox;
	}
	public List<Person> getPersonsList() {
		return personsList;
	}
	public void setPersonsList(List<Person> personsList) {
		this.personsList = personsList;
	}
	
	

}
