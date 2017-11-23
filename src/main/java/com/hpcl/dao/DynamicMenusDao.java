/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-12 
*/
package com.hpcl.dao;

import java.util.List;

import com.hpcl.persistence.AppParameters;
import com.hpcl.persistence.DynamicMenus;


public interface DynamicMenusDao {

	public List<DynamicMenus> getAllDynamicMenus(int roleID);
	public List<DynamicMenus> getAllDynamicPages(String tablename,AppParameters appParameters);
}
