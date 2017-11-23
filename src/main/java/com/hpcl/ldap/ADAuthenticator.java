package com.hpcl.ldap;

import java.util.HashMap;  
import java.util.HashSet;  
import java.util.Hashtable;  
import java.util.Map;  

import javax.naming.AuthenticationException;
import javax.naming.Context;  
import javax.naming.NamingEnumeration;  
import javax.naming.NamingException;  
import javax.naming.directory.Attribute;  
import javax.naming.directory.Attributes;  
import javax.naming.directory.SearchControls;  
import javax.naming.directory.SearchResult;  
import javax.naming.ldap.InitialLdapContext;  
import javax.naming.ldap.LdapContext; 

public class ADAuthenticator {
	private String domain;  
	  private String ldapHost;  
	  private String searchBase;  
	    
	  public ADAuthenticator() {  
	    this.domain = "hpcl.co.in";  
	    this.ldapHost= "ldap://10.90.147.84:389/";
	    //this.ldapHost= "ldap://ADS.srit.com:389/";
	    //this.searchBase = "OU=SRIT,DC=sritads,DC=com"; // YOUR SEARCH BASE IN LDAP
	    this.searchBase = "DC=hpcl,DC=co,DC=in"; // YOUR SEARCH BASE IN LDAP
	  }  
	  //memberOf: CN=DOTNET-Development-GP,OU=SRIT,DC=srit,DC=com
	  //servicePrincipalName: ldap/ADS.srit.com
	 /* domain = "sritads.com";  
      ldapHost= "ldap://192.168.0.10:389/";
      //this.ldapHost= "ldap://SRITADS.srit.com:389/";
      searchBase = "OU=SRIT,DC=sritads,DC=com";*/

	  public ADAuthenticator(String domain, String host, String dn)  
	  {  
	    this.domain = domain;  
	    this.ldapHost = host;  
	    this.searchBase = dn;  
	  }  
	  
	  public Map<String, Object> authenticate(String user, String pass) { 

		  	Map<String, Object> amap = null; 
		  	String returnedAtts[] ={ "name", "department","sAMAccountName","title","mail" };
	        String searchFilter = "(&(objectClass=user)(sAMAccountName="+user+"))";
	          
	        // Create the search controls  
	        SearchControls searchCtls = new SearchControls();  
	        searchCtls.setReturningAttributes(returnedAtts);  
	        // Specify the search scope  
	        searchCtls.setSearchScope(SearchControls.SUBTREE_SCOPE);  
	          
	        Hashtable<String,String> env = new Hashtable<String,String>();  
	        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");  
	        env.put(Context.PROVIDER_URL, ldapHost);  
	        env.put(Context.SECURITY_AUTHENTICATION, "simple");  
	        env.put(Context.SECURITY_PRINCIPAL, user+"@"+domain);  
	        env.put(Context.SECURITY_CREDENTIALS, pass);  
	        //env.put("java.naming.ldap.version", "3");
	        
	        LdapContext ctxGC = null; 
	        
	        try {  
	              
	            // This is the actual Authentication piece. Will throw javax.naming.AuthenticationException  
	            // if the users password is not correct. Other exceptions may include IO (server not found) etc.
	        	ctxGC = new InitialLdapContext(env, null);
	            if(ctxGC.hashCode() > 0){
	            	 // Now try a simple search and get some attributes as defined in returnedAtts  
		            NamingEnumeration<SearchResult> answer = ctxGC.search(searchBase, searchFilter, searchCtls);
		           
		            while (answer.hasMoreElements()) {  
		                SearchResult sr = (SearchResult) answer.next();
		                Attributes attrs = sr.getAttributes();  
		                if (attrs != null) {  
		                    amap = new HashMap<String,Object>();  
		                    NamingEnumeration<?> ne = attrs.getAll();  
		                    while (ne.hasMore()) {  
		                        Attribute attr = (Attribute) ne.next();  
		                        if (attr.size() == 1) {  
		                            amap.put(attr.getID(), attr.get());  
		                        } else {  
		                            HashSet<String> s = new HashSet<String>();  
		                            NamingEnumeration n =  attr.getAll();  
		                            while (n.hasMoreElements()) {  
		                                s.add((String)n.nextElement());  
		                            }  
		                            amap.put(attr.getID(), s);  
		                        }  
		                    }  
		                    ne.close();  
		                }  
		            }
	            }
	        } catch (AuthenticationException au) {  
	        	au.printStackTrace();
	        	return null;
	        } catch (NamingException nex) {  
	            nex.printStackTrace();
	            return null;
	        } catch (Exception ex) {  
	            ex.printStackTrace();
	            return null;
	        }  finally{
	        	try {
					ctxGC.close();
				} catch (NamingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					return null;
				}  // Close and clean up  
	        	return amap;  
	        }
	        //return null;  
	    }  
}