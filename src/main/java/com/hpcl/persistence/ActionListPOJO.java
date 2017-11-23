/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02.
*/
package com.hpcl.persistence;

import java.util.List;

public class ActionListPOJO {
String id;
String description;
List<String> selectedCheckBox;
List<ActionListPOJO> actionsList;
public String getId() {
	return id;
}
public void setId(String id) {
	this.id = id;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public List<String> getSelectedCheckBox() {
	return selectedCheckBox;
}
public void setSelectedCheckBox(List<String> selectedCheckBox) {
	this.selectedCheckBox = selectedCheckBox;
}
public List<ActionListPOJO> getActionsList() {
	return actionsList;
}
public void setActionsList(List<ActionListPOJO> actionsList) {
	this.actionsList = actionsList;
}



}
