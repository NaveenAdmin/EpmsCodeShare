<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Modified Date	:28-12-15
Modified By	    :Prasad-->

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<script>
var actionID;
var actiontempId;
  $(document).ready( function () {
 	 $(".use-address").click(function() {
	     	    var $row = $(this).closest("tr"); // Find the row
	     	    var $roleID = $row.find("#roleID").text(); // Find the text
	     	    var $moduleID = $row.find("#moduleID").text(); // Find the text
	     	 
	     	 var $pageID = $row.find("#pageID").text();
	     	 var $actionID = $row.find("#actionID").text();
	     	
	     	   var $roleName = $row.find("#roleName").text(); // Find the text
	     	   var $moduleName = $row.find("#moduleName").text(); // Find the text
	     	 
	     	 var $pageName = $row.find("#pageName").text();
	     	
	     	 $("#droleID").empty();
	     	 $("#dmenuID").empty(); 
	     	 $("#dpageID").empty();
	     	 
	     	    // Let's test it out
	     	    $("<option>").val($roleID).text($roleName).appendTo('#droleID');
	     	    $("<option>").val($moduleID).text($moduleName).appendTo('#dmenuID');
	     	    $("<option>").val($pageID).text($pageName).appendTo('#dpageID');

 		     $('input:checkbox').attr('checked',false);
             $("input[name=selectedCheckBox][value='"+$actionID+"']").prop("checked", true).attr('disabled', 'disabled');
	     	 $('#uproleID').val($roleID);
	     	});
		   
		   $(".use-confirm").click(function() {
	     	    //var $row = $(this).closest("tr");    // Find the row
	     	    var $text = $row.find("#roleID").text(); // Find the text
	     	    var $module = $row.find("#moduleID").text();
	     	    var $action = $row.find("#actionID").text();
	     	    var $page = $row.find("#pageID").text();
	     	  
	     	   // Let's test it out
	     	   $('#message').val($text);
	     	   $('#modules').val($module);
	     	   $('#action').val($action);
	     	   $('#page').val($page);
	     	    
	     	});
		   $('.close').click(function() {                   
			     $('#alert').empty();
			     $('#action').empty();
			      $('#editaction').empty(); 
	    		 $('#alert').empty();   
	    		 $('#menualert').empty();
	    		 $('#role').val("");
	    		 $('#Menus').val("");
	    		 $('#Pages').empty();
	    		 $('#errorMessage').empty();
	    		 $('#upalert').empty();
	    		
 	    		 $('#Pages').append('<option>Select Pages</option>');
 	    		$("input[name=selectedCheckBox][value='"+actionID+"']").prop("checked", false).attr('disabled', false);
	    		 $('.person_data').each(function() {
	    				this.checked = false;

	    			});
	    		 $('#select_all').each(function() {
	    				this.checked = false;

	    			});
	    		 
	    		     	});
		   $('#cancel').click(function() {                   
			   clear();
	       		 
		     	});
		   $('#editcancel').click(function() { 
			 
			   clear();
	       		 
		     	});
 });
  
 function clear(){
	    $('#alert').empty();
	    $('#action').empty();
	    $('#editaction').empty(); 
		 $('#alert').empty();   
		 $('#upalert').empty();   
		 $('#menualert').empty();
		 $('#role').val("");
		 $('#Menus').val("");
		 $('#Pages').empty();
		 $('#errorMessage').empty();
		 $('#Pages').append('<option>Select Pages</option>');
   		$("input[name=selectedCheckBox][value='"+actionID+"']").prop("checked", false).attr('disabled', false);
  		 $('.person_data').each(function() {
  				this.checked = false;

  			});
  		 $('#select_all').each(function() {
  				this.checked = false;

  			});
 }
 function getPages(){
	 
	 var menu=$("#Menus").val();
	 
	 if(menu==""){

		 $('#Pages').empty();
		 $('#Pages').append('<option>Select Pages</option>');
	 }else{
		 $.ajax({
	 		 type: "GET",
	 		 url: "ajaxActions.htm?page=AM6&status=menu&id="+menu,
	 		  dataType:"json",
	 		 cache:false,
	 	     success: function (data) {
	 	    	 if(menu==''){
	 	    		 $('#Pages').empty();
	 	    		 $('#Pages').append('<option>Select Pages</option>');
	 	    	 }else{
	 	    		 if(data.details==""){
	 	 	    		$('#Pages')
	 	 	    	    .empty()
	 	 	    	    .append('<option  value="0">No Pages</option>');
	 	 	    	 }else{
	 	 	    		$('#Pages')
	 	 	    	    .empty();
	 	 	    		 //$('#Pages').append('<option>Select Pages</option>');
	 		 	    	 $.each(data.details, function(i,data){
	 		 	    		 
	 			 	    	$("<option>").val(data.pageid).text(data.pagedesc).appendTo('#Pages');
	 			 	    	}); 
	 	 	    	    }
	 	 	        } 
	 	    	 }
	 	    	
	 	});
	 }
	        
	 }
 getDetails('A');

 function validate()
 {           	              
   if(roleformValidation(document.getElementById("role"),document.getElementById("alert"),document.getElementById("Menus"),document.getElementById("menualert"),document.getElementById("Pages"),document.getElementById("pagealert"))){
	   var cnt = $("input[name='selectedCheckBox']:checked").length;
	   
	        if (cnt < 1)
       {
	        	 $('#errorMessage').empty();   
       $("<p>Select at least one Page Action.</p>").appendTo("#errorMessage");
	            e.preventDefault();
         }

	        else{
	   var roles = $('#role').val();
	   var menus = $('#Menus').val();
	   var pages = $('#Pages').val();
	   var actions;

	   if(document.getElementById('selectedCheckBox1').checked) {
		  actions = $('#selectedCheckBox1').val();
		}
	   if(document.getElementById('selectedCheckBox2').checked) {
			  actions = $('#selectedCheckBox2').val();
			}
	   if(document.getElementById('selectedCheckBox3').checked) {
			  actions = $('#selectedCheckBox3').val();
			}
	   if(document.getElementById('selectedCheckBox4').checked) {
			  actions = $('#selectedCheckBox4').val();
			}
	   $.ajax({
		     url:"validation.htm?page=AMUD5&RoleID="+roles+"&moduleID="+menus+"&pageID="+pages+"&actionID="+actions,  
		   	 dataType:"json",
		   	 cache:false,
		     success: function (data) {
		    	
		    	 if(data.result=='valid'){
		    		 formSubmit();
		        
		    	 }else{
		    		 $('#errorMessage').empty();
  		    		$("<p>"+data.result+"</p>").appendTo("#errorMessage");
  		    		
  		    	 }
		     
		     }
		     });
	   
	        }
	   
	   
   }else{
	   
	   return false;
   }
 }
 function upvalidate()
 {       $("#upalert").empty();           
	 var roles = $('#droleID').val();
	   var menus = $('#dmenuID').val();
	   var pages = $('#dpageID').val();

       var i=0;
       var myCheckboxes = new Array();
       var cnt = $("input[name='selectedCheckBox']:checked").length;
	   
	   if(cnt<3){
		   $("#upalert").empty();           
		   $("<p>Please select at least one action</p>").appendTo("#upalert");
		   return false;
	   }else{

		   $("input[name='selectedCheckBox']:checked").each(function (){
	    	   if(i!=0){
	    		   
	    		   if(actiontempId!=$(this).val()){
	    			   myCheckboxes.push($(this).val());
	    		   }
	    		   
	    	   }
	    	   i++;
	    	}); 
		   
		   var hint;
		   $.ajax({
			     url:"validation.htm?page=AMUD6&roleID="+roles+"&menuID="+menus+"&pageID="+pages+"&pageActions="+myCheckboxes,
			   	 dataType:"json",
			   	 async : false,
			   	cache:false,
			     success: function (data) {
			    	 if(data.result=='valid'){
			    		hint=data.result;
			    	 }else{
			    		hint=data.result;
			    		$("<p>"+data.result+"</p>").appendTo("#upalert");
			    	 }
			   }
			}); 
		   if(hint=='valid')
		   return true;
		   else 
		  return false;
	   }
       
	   
     	
   
 }
 
 function formSubmit(){
	 $("#multiCheckBox").submit(function(e)
			 { 
			     var postData = $(this).serializeArray();
			     var formURL = $(this).attr("action");
			     $.ajax(
			     {
			         url : formURL,
			         type: "POST",
			         data : postData,
			         cache:false,
			    	 async:false,
			         success:function(data, textStatus, jqXHR) 
			         {
			         	 $(".modal-backdrop").remove();
			         	 $("body").removeClass("modal-open");
			         	$("#myModal").modal('hide'); 
			         	 
			         	 $('#role').val('');
			         	 $('#Menus').val('');
			         	 $('#Pages').val('');
			             getDetails("A");
			             
			             $('#alert').empty();
					     $('#action').empty();
					      $('#editaction').empty(); 
			    		 $('#alert').empty();   
			    		 $('#menualert').empty();
			    		 $('#role').val("");
			    		 $('#Menus').val("");
			    		 $('#Pages').empty();
			    		 $('#errorMessage').empty();
			    		 
		 	    		 $('#Pages').append('<option>Select Pages</option>');
		 	    		$("input[name=selectedCheckBox][value='"+actionID+"']").prop("checked", false).attr('disabled', false);
			    		 $('.person_data').each(function() {
			    				this.checked = false;

			    			});
			    		 $('#select_all').each(function() {
			    				this.checked = false;

			    			});
			     
			         },
			         error: function(jqXHR, textStatus, errorThrown) 
			         {
			             //if fails      
			         }
			     });
			     e.preventDefault(); //STOP default action
			     e.unbind(); //unbind. to stop multiple form submit.
			 });			  
			 $("#multiCheckBox").submit();

	}
 getDetails("A");
 function getDetails(status){
	   
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=AM7&status="+status,
	   dataType:"json",
	   cache:false,
	   success: function(data)
	   {

		   if(data.details==""){
			   
		   $("#bootstrap-tablepages").empty();
		   var head_data="<p><h3><center><font size='5' color='red'> No Data Available</font><h3></center></p>";
		   $(head_data).appendTo("#bootstrap-tablepages");
		   
	   }else{
		   if(status == 'I' || status=='All'){
			   $("#bootstrap-tablepages").empty();
			   var head_data="<thead>  <tr><th width='10%'>SNo</th><th class='column-hide'>Role ID</th><th>Role Name</th><th class='column-hide'>Module ID</th><th>Application Pages</th><th class='column-hide'>Page ID</th><th>Page Name</th><th class='column-hide'>Action ID</th><th>Page Actions</th><th>Status</th></tr></thead>";
			   var head_data1="<thead>  <tr><th width='10%'>SNo</th><th class='column-hide'>Role ID</th><th>Role Name</th><th class='column-hide'>Module ID</th><th>Application Pages</th><th class='column-hide'>Page ID</th><th>Page Name</th><th class='column-hide'>Action ID</th><th>Page Actions</th><th>Status</th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-tablepages");
			   $(head_data1).appendTo("#bootstrap-tablepages");
			   
			   
			   $("#bootstrap-tablepages").dataTable().fnDestroy();
			   $("#bootstrap-tablepages").empty();
			   var head_data="<thead>  <tr><th width='10%'>SNo</th><th class='column-hide'>Role ID</th><th>Role Name</th><th class='column-hide'>Module ID</th><th>Application Pages</th><th class='column-hide'>Page ID</th><th>Page Name</th><th class='column-hide'>Action ID</th><th>Page Actions</th><th>Status</th></tr></thead>";
			   var head_data1="<thead>  <tr><th width='10%'>SNo</th><th class='column-hide'>Role ID</th><th>Role Name</th><th class='column-hide'>Module ID</th><th>Application Pages</th><th class='column-hide'>Page ID</th><th>Page Name</th><th class='column-hide'>Action ID</th><th>Page Actions</th><th>Status</th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-tablepages");
			   $(head_data1).appendTo("#bootstrap-tablepages");
			   
			   $.each(data.details, function(i,data){
				  var status;
				  
				   if(data.status=='A'){
					   status="Active";
				  }else{
					  status="Inactive";
				  }
		
				   var msg_data="<tr ><td id='snid'>"+(parseInt(i)+1)+"</td><td class='column-hide' id='roleID'>"+data.roleID+"</td><td id='roleName'>"+data.roleDesc+"</td><td id='moduleID' class='column-hide'>"+data.moduleID+"</td><td id='moduleName'>"+data.moduleDesc+"</td><td id='pageID' class='column-hide'>"+data.pageID+"</td><td id='pageName'>"+data.pageDesc+"</td><td id='actionID' class='column-hide'>"+data.actionID+"</td><td id='actionName'>"+data.actionDesc+"</td><td>"+status+"</td></tr>";
				   $(msg_data).appendTo("#bootstrap-tablepages");
				  
			   });
			   $('#bootstrap-tablepages').dataTable().columnFilter({ sPlaceHolder: "head:after",
	               aoColumns: [
	                           { type: "text" },
	                           { type: "text" },
	                           { type: "text" },
	 						   { type: "text" },
	 						   { type: "text" },
	                           { type: "text" },
	                           { type: "text" },
							   { type: "text" },
							   { type: "text" },
							   { type: "text" },
							   { type: "text" },
	 						  ]
	                 });
			 	 $(".use-address").click(function() {
			     	    var $row = $(this).closest("tr");    // Find the row
			     	    var $roleID = $row.find("#roleID").text(); // Find the text
			     	    var $moduleID = $row.find("#moduleID").text(); // Find the text
			     	 
			     	    var $pageID = $row.find("#pageID").text();
			     	    var $actionID = $row.find("#actionID").text();
			     	
			     	   var $roleName = $row.find("#roleName").text(); // Find the text
			     	   var $moduleName = $row.find("#moduleName").text(); // Find the text
			     	 
			     	   var $pageName = $row.find("#pageName").text();
			     	 
			     	 $("#droleID").empty();
			     	 $("#dmenuID").empty();
			     	 $("#dpageID").empty();
			     	 
			     	    // Let's test it out
			     	    $("<option>").val($roleID).text($roleName).appendTo('#droleID');
			     	    $("<option>").val($moduleID).text($moduleName).appendTo('#dmenuID');
			     	    $("<option>").val($pageID).text($pageName).appendTo('#dpageID');
		 
		 		   $('input:checkbox').attr('checked',false);

		 		
			     
			     	 $("input[name=selectedCheckBox][value='"+$actionID+"']").prop("checked", true).attr('disabled', 'disabled');
			     	    $('#uproleID').val($roleID);
			     	});
				   
				   $(".use-confirm").click(function() {
			     	    //var $row = $(this).closest("tr");// Find the row
			     	    var $text = $row.find("#roleID").text(); // Find the text
			     	    var $module = $row.find("#moduleID").text();
			     	    var $action = $row.find("#actionID").text();
			     	    var $page = $row.find("#pageID").text();
			     	  
			     	 
			           // Let's test it out
			     	   $('#message').val($text);
			     	   $('#modules').val($module);
			     	   $('#action').val($action);
			     	   $('#page').val($page);
			     	    
			     	});
				  
			   
		   }else{
			   $("#bootstrap-tablepages").empty();
			   var head_data="<thead>  <tr><th width='10%'>SNo</th><th class='column-hide'>Role ID</th><th>Role Name</th><th>Module ID</th><th>Application Pages</th><th>Page ID</th><th>Page Name</th><th>Action ID</th><th>Page Actions</th><th>Status</th><th>Actions</th></tr></thead>";
			   var head_data1="<thead>  <tr><th width='10%'>SNo</th><th class='column-hide'>Role ID</th><th>Role Name</th><th>Module ID</th><th>Application Pages</th><th>Page ID</th><th>Page Name</th><th>Action ID</th><th>Page Actions</th><th>Status</th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-tablepages");
			   $(head_data1).appendTo("#bootstrap-tablepages");
			   
			   
			   $("#bootstrap-tablepages").dataTable().fnDestroy();
			   $("#bootstrap-tablepages").empty();
			   var head_data="<thead>  <tr><th width='10%'>SNo</th><th class='column-hide'>Role ID</th><th>Role Name</th><th class='column-hide'>Module ID</th><th>Application Pages</th><th class='column-hide'>Page ID</th><th>Page Name</th><th class='column-hide'>Action ID</th><th>Page Actions</th><th>Status</th><th>Actions</th></tr></thead>";
			   var head_data1="<thead>  <tr><th width='10%'>SNo</th><th class='column-hide'>Role ID</th><th>Role Name</th><th class='column-hide'>Module ID</th><th>Application Pages</th><th  class='column-hide'>Page ID</th><th>Page Name</th><th class='column-hide'>Action ID</th><th>Page Actions</th><th>Status</th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-tablepages");
			   $(head_data1).appendTo("#bootstrap-tablepages");
			   
			   $.each(data.details, function(i,data){
				  var status;
				  
				   if(data.status=='A'){
					   status="Active";
				  }else{
					  status="Inactive";
				  }
		
				   var msg_data="<tr ><td id='snid'>"+(parseInt(i)+1)+"</td><td id='roleID' class='column-hide'>"+data.roleID+"</td><td id='roleName'>"+data.roleDesc+"</td><td id='moduleID' class='column-hide'>"+data.moduleID+"</td><td id='moduleName'>"+data.moduleDesc+"</td><td id='pageID' class='column-hide'>"+data.pageID+"</td><td id='pageName'>"+data.pageDesc+"</td><td id='actionID' class='column-hide'>"+data.actionID+"</td><td id='actionName'>"+data.actionDesc+"</td><td>"+status+"</td><td><a href='#' class='use-address fa fa-pencil' data-toggle='modal' data-target='#myModalEdit'  data-backdrop='static' data-keyboard='false'> Edit </a>&nbsp;|&nbsp;<a href='#' class='use-confirm fa fa-trash' data-toggle='modal' data-target='#myModaldel'  data-backdrop='static' data-keyboard='false'> Delete </a></td></tr>";
				   $(msg_data).appendTo("#bootstrap-tablepages");
				  
			   });

			           
			 	 $(".use-address").click(function() {

			     	    var $row = $(this).closest("tr");    // Find the row
			     	    var $roleID = $row.find("#roleID").text(); // Find the text
			     	    var $moduleID = $row.find("#moduleID").text(); // Find the text
			     	 
			     	    var $pageID = $row.find("#pageID").text();
			     	    var $actionID = $row.find("#actionID").text();
			     	   	actiontempId=$actionID;
			     	   var $roleName = $row.find("#roleName").text(); // Find the text
			     	   var $moduleName = $row.find("#moduleName").text(); // Find the text
			     	   var $pageName = $row.find("#pageName").text();
			     	  
			     	 $("#droleID").empty();
			     	 $("#dmenuID").empty();
			     	 $("#dpageID").empty();
			     	 
			     	    // Let's test it out
			     	    $("<option>").val($roleID).text($roleName).appendTo('#droleID');
			     	    $("<option>").val($moduleID).text($moduleName).appendTo('#dmenuID');
			     	    $("<option>").val($pageID).text($pageName).appendTo('#dpageID');
		 
		 		   $('input:checkbox').attr('checked',false);

		 		
			       actionID=$actionID;
			     	 $("input[name=selectedCheckBox][value='"+$actionID+"']").prop("checked", true).attr('disabled', 'disabled');
			     	    $('#uproleID').val($roleID);
			     	});
				   
				   $(".use-confirm").click(function() {
			     	    var $row = $(this).closest("tr");// Find the row
			     	    var $text = $row.find("#roleID").text(); // Find the text
			     	    var $module = $row.find("#moduleID").text();
			     	    var $action = $row.find("#actionID").text();
			     	    var $page = $row.find("#pageID").text();
			     	  
			     	 
			           // Let's test it out
			           $('#message').val($row);
			     	   $('#message').val($text);
			     	   $('#modules').val($module);
			     	   $('#action').val($action);
			     	   $('#page').val($page);
			     	    
			     	});
				   $('#bootstrap-tablepages').dataTable().columnFilter({ sPlaceHolder: "head:after",
		               aoColumns: [
		                           { type: "text" },
		                           { type: "text" },
		                           { type: "text" },
		 						   { type: "text" },
		 						   { type: "text" },
		                           { type: "text" },
		                           { type: "text" },
								   { type: "text" },
								   { type: "text" },
								   { type: "text" },
		 						  ]
		                 });
				  
			   }
		   
		     }
		   
	        }
	   });
	   
 }
 function selectAllCheckBox() {
	
		if (document.getElementById('select_all').checked == true) {
			$('.person_data').each(function() {
				this.checked = true;
			});
		} else {
			$('.person_data').each(function() {
				this.checked = false;
			});
		}

	}
 function clearData(){
	  $("#alert").empty();
	  $("#menualert").empty();
	  $("#menualert").empty();
	  $("#pagealert").empty();
	 // $("#role").empty();
	  $("#upalert").empty();
	
	}
  
 </script>
<div id="page-wrapper">
	<div class="col-lg-12 col-md-12 content-top-header">
		<div class="row">
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-left"
				id="divEMSnavLeft">
				<div class="row">
					<ul class="nav navbar-top-links navbar-left">
						<li class="dropdown">
							<h5>
							<a href="home.htm">Home</a>/
							<a href="menu5.htm?url=homeadmin.htm">Administration</a>/
							<a href="pagepermissions.htm" class="left-breadcrumb-last-child">Role Permissions</a>
							</h5> <!-- /.dropdown-messages -->
						</li>
						<!-- /.dropdown -->
					</ul>
				</div>
			</div>


			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-right"
				id="divEMSnavRight">
				<div class="row">
					<ul class="nav navbar-top-links pull-right ">
						<!-- /.dropdown -->
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#"> <i class="fa fa-user"> </i>
								User:<%=session.getAttribute("userName").toString() %>
						</a></li>

						<li class="dropdown"><a href="logout.htm"> <i
								class="fa fa-sign-out"> </i> Logout
						</a></li>
						<!-- /.dropdown -->
					</ul>
				</div>
			</div>
		</div>

		<div class="row">
			<div class="col-lg-12 col-md-12 location" id="divEMSnavCenter">
				<h2>
					<center>
						<%=session.getAttribute("location").toString() %>
					</center>
				</h2>
			</div>
		</div>
		<!-- /.navbar-top-links -->
	</div>
	<div class="clearfix"></div>
	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header-title">Page Permissions</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!--s  -->
	<div class="">
		<div class="col-lg-12 col-md-12 search-config-align">
			<div class="" style="float: right; width: 100%;">
				<form class="form-inline" role="form">
					<fieldset class="scheduler-border">
						 <legend class="scheduler-border">Search</legend> 
						<div class="form-group status-input-style ">
							<div class="form-group status-input-style">
								<div class="sam search-scheduler" style="height: 45px;">

									<label class="radio-inline check-all">
										<fieldset class="scheduler-border  mst-search-status">
											<legend class="scheduler-border status-cont">Status</legend>

											<div class="radio1">
												<input type="radio" name="optradio"
													onclick="getDetails('All')">All
											</div>

											<div class="radio2">
												<input type="radio" name="optradio" checked="checked"
													onclick="getDetails('A')">Active
											</div>

											<div class="radio3">
												<input type="radio" name="optradio"
													onclick="getDetails('I')">Inactive
											</div>
										</fieldset>
									</label>

							</div>
							</div> 

						</div>
						<label class="config-search-submit-style pull-right">
							<button type="button" class="btn-hp btn-warning"
								data-toggle="modal" data-target="#myModal"  data-backdrop="static" data-keyboard="false">
								<i class="fa fa-plus"></i> Add
							</button>
						</label>
					</fieldset>

				</form>

			</div>
			<br>
			<!-- <table class="table table-hover table-striped" id="bootstrap-tablepages">

			</table> -->
			<table id="bootstrap-tablepages" class="table table-striped " data-columnFilter='{"aoColumns": [ null, {"type": "text"}, {"type": "text"}, {"type": "text"}, { "type": "select" } ]}' data-columnFilter-select2="true">
			</table>  
			<br><br>
								
								
			
		</div>
	</div>
</div>
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Add Page Permissions</h4>
				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png" />" width="35"></a>
			</div>
			<div class="modal-body  popup-pageaction">
				<div class="modal-body">
					<form:form id="multiCheckBox" name="ajaxform" commandName="person"
						method="post" action="multiCheckbox.htm">
						<div class="form-group hpclusers-popup-md">
						
							<div>
								<label>Role <b>*</b> </label> <select
									class="form-control  " name="roleID" id="role" onchange="clearData()">
									<option value="">Select Roles</option>
									<c:forEach var="role" items="${role}" varStatus="sno" >
										<option value="${role.roleID}">${role.description}</option>
									</c:forEach>
								</select>
								<label> </label><div id="alert" class="alert-message"></div>
							</div>
							<div class="clear"> </div>
							
						<div class="clear"></div>
							<div>
								<label for="name"> Menus <b>*</b> </label> <select
									class="form-control  " name="menuID"
									onchange="getPages()" id="Menus" onclick="clearData()">
									<option value="">Select Menu</option>
									<c:forEach var="menu" items="${menu}" varStatus="sno">
										<option value="${menu.id}">${menu.description}</option>
									</c:forEach>
								</select>
								<label> </label> <div id="menualert" class="alert-message"></div>
							</div>
						<div class="clear"></div>
							<div>
								<label for="name"> Pages <b>*</b> </label> <select
									class="form-control " id="Pages" name="pageID">
									<option value="">Select Pages</option>
								</select>
								<label> </label> <div id="pagealert" class="alert-message"></div>
							</div>
						</div>
					<div class="clear"></div>
						<table class="table  table-striped">
							<thead>
								<tr>
									<th colspan="2" align="left" style="text-align: left; padding:0px;">Page
										Actions</th>
								</tr>
							</thead>
							<tbody style="border: 1px solid #c1c1c1;">
								<tr>
									<td><input id="select_all" onclick="selectAllCheckBox();"
										id="action" type="checkbox" name="myTextEditBox"
										value="checked" /></td>
									<td>Select All</td>
								</tr>
								<c:forEach items="${person.personsList}" var="elements"
									varStatus="loop">
									<tr>
										<td><form:checkbox path="selectedCheckBox" 
												class="person_data" value="${elements.id}" /></td>
										<td><c:out value='${elements.department}' /></td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="clear"></div>
						<div class="alert-message1" id="errorMessage"></div>
						<div class="modal-body">
							<div class="form-group" style="margin-bottom: 10px;">								
							<button type="button" data-target="#myModal" onclick="return validate();" class="btn  btn-sm btn-primary">Save</button>
								<button type="button" data-dismiss="modal" id="cancel" class="btn  btn-sm btn-primary popup-form-button-align" onclick="clearData()">Cancel</button>
				
							</div>
							<input type="hidden" name="page" value="AM7"> 
							<input type="hidden" name="operation" value="4">
							
						</div>
					</form:form>

				</div>
			</div>
		</div>
	</div>
</div>
<!----------myModalAdd end------------->
<!----------myModalEdit------------->
<div class="modal fade" id="myModalEdit" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Edit Page Permissions</h4>
				<a class="close" data-dismiss="modal" onclick="clearData()"><img
					src="<c:url value="/resources/images/close.png" />" width="35"></a>
			</div>
			<div class="modal-body hpclusers-popup-md">
				<div class="modal-body">
					<form:form id="multiCheckBox" commandName="person" method="post"
						action="updatemultiCheckbox.htm" onsubmit="return upvalidate()">
						<div style="width: 100%;">
							<div>
								<label>Role <b>*</b></label> <select
									class="form-control popup-form-select" name="roleID"
									id="droleID" >
								</select>
							</div>
							<div class="clear"></div>
							<div>
								<label for="name"> Menus <b>*</b></label> <select
									class="form-control popup-form-select" name="moduleID"
									id="dmenuID" onchange="getPages()">
									<option value="0">Select menu</option>
								</select>
							</div>
							<div class="clear"></div>
							<div>
								<label for="name"> Pages <b>*</b></label> <select
									class="form-control popup-form-select" name="pageID"
									id="dpageID">
									<option value="0">Select Pages</option>
								</select>
							</div>
							<div class="clear"></div> <br>
							<table class="table  table-striped"
								style="width: 99%; float: left;">
								<thead>
									<tr>
										<th colspan="2" align="left" style="text-align: left; padding:0px;">Page
											Actions</th>
									</tr>
								</thead>
								<tbody style="border: 1px solid #c1c1c1;">
									<c:forEach items="${person.personsList}" var="elements"
										varStatus="loop">
										<tr>
											<td><form:checkbox path="selectedCheckBox" 
													id="editaction" onclick="clearData()" class="person_data" value="${elements.id}" /></td>
											<td><c:out value='${elements.department}' /><input
												type="hidden" name="operation" value="2"></td>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						<div class="clear"></div>
						</div>
						
						<div class="modal-body">
							<input type="hidden" name="roleID" id="uproleID"> <input
								type="hidden" name="operation" value="2">
								<div id="upalert" class="alert-message1"></div>
								<div class="clear"></div>
								<button type="button" data-dismiss="modal"
								class="btn  btn-sm btn-primary popup-form-button-align" id="editcancel">Cancel</button>
							<div class="form-group  popup-form-align"
								style="float: right; margin-bottom: 10px;">
								<button type="submit"
									class="btn  btn-sm btn-primary popup-form-button-align"
									data-target="#myModal">Update</button>
							</div>
							
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>
<!----------myModalEdit end------------->
<!----------myModalDelete Start------------->
<div class="modal fade" id="myModaldel" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Delete Page Actions</h4>
				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png"/>" width="35"></a>
			</div>
			<div class="modal-body popup-delete-align">
				<div class="form-group  popup-form-align del-popup-button">
					<label>Are you sure you want to delete ? </label>
					<form:form class="form-inline" action="deletemultiCheckbox.htm"
						method="post" modelAttribute="EmployeRoles" role="form">
						<input type="hidden" name="operation" value="3">
						<input type="hidden" id="message" name="roleID">
						<input type="hidden" id="modules" name="moduleID">
						<input type="hidden" id="action" name="actionID">
						<input type="hidden" id="page" name="pageID">
						
						<button type="button" data-dismiss="modal"
							class="btn  btn-sm btn-primary popup-form-button-align">
							No</button>
						<button type="submit"
							class="btn  btn-sm btn-primary popup-form-button-align">
							Yes</button>
					
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>


	</script>