/**
* @author  :Prasad
* @version :1.0
* @since   2015-10-09
* @ModifiedDate	:2015-11-02.
*/
package com.hpcl.web;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.hpcl.persistence.LoginPersistance;

@Controller
@PropertySource("classpath:login.properties")
@RequestMapping("/logout.htm")
public class LogoutController {
	@Value("${version}")
	private String version;
	  @RequestMapping(method=RequestMethod.GET)
	  public ModelAndView logout(HttpSession session,Map<String, Object> model) {
		  
	    session.invalidate();
	    LoginPersistance loginPersistance=new LoginPersistance();
	    ModelAndView andView=new ModelAndView();
	    andView.addObject("version", version);
	    andView.setViewName("login");
	    model.put("userForm", loginPersistance);
	   
	    return andView;
	  }
	
}