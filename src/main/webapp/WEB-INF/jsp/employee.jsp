
<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:28-12-15
Modified By	    :Prasad-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
var deleteLinks = document.querySelectorAll('.delete');

for (var i = 0; i < deleteLinks.length; i++) {
deleteLinks[i].addEventListener('click', function(event) {
event.preventDefault();

var choice = confirm(this.getAttribute('data-confirm'));

if (choice) {
  window.location.href = this.getAttribute('href');
}
});
}
function formSubmit(){
	var page=$("#page").val();
	var operation=$("#operation").val();
	var text=$("#text").val();
	var role=$("#role").val();
	
		     $.ajax({
		     url:"configactions.htm?page="+page+"&operation="+operation+"&employeeID="+text+"&roleID="+role,
		     data: $("#form1").serialize(),
		     cache:false,
	    	 async:false,
		     success: function (data) {
		    	 
		     	     $(".modal-backdrop").remove();
                	 $("body").removeClass("modal-open");
                	 $("#myModal").modal('hide'); 
                	
                	 $('#text').val("");
    	     		 $('#role').val("");
    	     		 $('#role_error').empty();
    	     		 getAjaxDetails('A');
		    	 
		   }
		}); 
	
		}
		
function updateAll(){
	var page=$("#page").val();
	var operation=$("#operation").val();
	var text=$("#text").val();
	
		 $.ajax({
		     url:"configactions.htm?page=CP1&operation=2",
		     data: $("#form1").serialize(),
		     cache:false,
		     async:false,
		     success: function (data) {
		     getAjaxDetails('A');
		   }
		});
		}
     
       getAjaxDetails('A');
       function getAjaxDetails(status){
    	   
    	   $.ajax
    	   ({
    	   type: "GET",
    	   url: "ajaxActions.htm?page=CP1&status="+status,
    	   dataType:"json",
    	   cache:false,
    	   success: function(data)
    	   {
    		   if(data.details==""){
    		   $("#bootstrap-table").empty();
			   var head_data="<p><h3><center><font size='5' color='red'> No Data Available</font><h3></center></p>";
			   $(head_data).appendTo("#bootstrap-table");
			   
		   }else{
			   if(status == 'I' || status=='all'){
					  
	    		   $("#bootstrap-table").empty();
	    		   var head_data="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='25%'>Employee Name</th><th width='25%'>Location</th><th width='20%'>Designation</th><th width='10%'>Status</th></thead>";
	    		   var head_data1="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='25%'>Employee Name</th><th width='25%'>Location</th><th width='20%'>Designation</th><th width='10%'>Status</th></thead>";
	    		   $(head_data).appendTo("#bootstrap-table");
	    		   $(head_data1).appendTo("#bootstrap-table");
	    		   
	    		   $("#bootstrap-table").dataTable().fnDestroy();
	    		   $("#bootstrap-table").empty();
	    		   var head_data="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='25%'>Employee Name</th><th width='25%'>Location</th><th width='20%'>Designation</th><th width='10%'>Status</th></thead>";
	    		   var head_data1="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='25%'>Employee Name</th><th width='25%'>Location</th><th width='20%'>Designation</th><th width='10%'>Status</th></thead>";
	    		   $(head_data).appendTo("#bootstrap-table");
	    		   $(head_data1).appendTo("#bootstrap-table");
	    		   
	    		   $.each(data.details, function(i,data){
	    			  var status;
	    			  
	    			   if(data.empstat=='A'){
	    				   status="Active";
	    			  }else{
	    				  
	    			  status="Inactive";
	    			  }
	    			   var tempemp=data.empname;
	    			   if(tempemp==null){
	    				   data.empname="";
	    			   }
	    			   var temploc=data.emploc;
	    			   if(temploc==null){
	    				   data.emploc="";
	    			   }
	    			   var temprole=data.emprole;
	    			   if(temprole==null){
	    				   data.emprole="";
	    			   }
	    			  
	    			      var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='id'>"+data.empid+"</td><td id='empname'>"+data.empname+"</td><td id='emploc'>"+data.emploc+"</td><td id='emprole'>"+data.emprole+"</td><td id='status'>"+status+"</td></tr>";
	    			   $(msg_data).appendTo("#bootstrap-table");
	    			  
	    			   });
	    		   
	              $("#bootstrap-table").append("</tbody>");
				   
				   $('#bootstrap-table').dataTable().columnFilter({ sPlaceHolder: "head:after",
			              aoColumns: [
			                          { type: "text" },
			                          { type: "text" },
			                          { type: "text" },
									  { type: "text" },
			                          { type: "text" },
									  { type: "text" },
									                                                      
			                          ]
			          });
	    	     	$(".use-confirm").click(function() {
	    	     	    var $row = $(this).closest("tr");// Find the row
	    	     	    var $text = $row.find("#id").text(); // Find the text
	    	     	  
	    	     	    // Let's test it out
	    	     	   $('#message').val($text);
	    	     	    
	    	     	});
	    	     	$('.btnDelete').on('click', function (e) {
	    	     	    e.preventDefault();
	    	     	    var id = $(this).closest('tr').find(".nr").text();
	    	     	   
	    	     	    $('#myModal').data('id', id).modal('show');
	    	     	});
	    	     	$('#btnDelteYes').click(function () {
	    	     	    var id = $('#myModal').data('id');
	    	     	  
	    	     	    alert(id+" deleted");
	    	     	    $('#myModal').modal('hide');
	    	     	    
	    	     	});
	    	     	$('.close').click(function() {                   
	    	     		
	    	     		 $('#alert').empty();
	    	     		 $('#rolealert').empty();
	    	     		 $('#text').val("");
	    	     		 $('#role').val("");
	    	     	    	     		     	});
	    	     		     
	    	     }else{
				   
			  
    		   $("#bootstrap-table").empty();
    		   var head_data="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='23%'>Employee Name</th><th width='24%'>Location</th><th width='13%'>Designation</th><th width='8%'>Status</th><th width='15%'>Action </th></thead>";
    		   var head_data1="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='23%'>Employee Name</th><th width='24%'>Location</th><th width='13%'>Designation</th><th width='8%'>Status</th><th width='15%'>Action </th></thead>";
    		   $(head_data).appendTo("#bootstrap-table");
    		   $(head_data1).appendTo("#bootstrap-table");
    		   
    		   $("#bootstrap-table").dataTable().fnDestroy();
    		   $("#bootstrap-table").empty();
    		   var head_data="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='20%'>Employee Name</th><th width='22%'>Location</th><th width='13%'>Designation</th><th width='8%'>Status</th><th width='15%'>Action </th></thead>";
    		   var head_data1="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='20%'>Employee Name</th><th width='22%'>Location</th><th width='13%'>Designation</th><th width='8%'>Status</th><th width='15%'> </th></thead>";
    		   $(head_data).appendTo("#bootstrap-table");
    		   $(head_data1).appendTo("#bootstrap-table");
    		   
    		   $.each(data.details, function(i,data){
    			  var status;
    			  
    			   if(data.empstat=='A'){
    				   status="Active";
    			  }else{
    				  
    			  status="Inactive";
    			  }
    			   var tempemp=data.empname;
    			   if(tempemp==null){
    				   data.empname="";
    			   }
    			   var temploc=data.emploc;
    			   if(temploc==null){
    				   data.emploc="";
    			   }
    			   var temprole=data.emprole;
    			   if(temprole==null){
    				   data.emprole="";
    			   }
    			     
    			   var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='id'>"+data.empid+"</td><td id='empname'>"+data.empname+"</td><td id='emploc'>"+data.emploc+"</td><td id='emprole'>"+data.emprole+"</td><td id='status'>"+status+"</td><td><a href='configactions.htm?employeeID="+data.empid+"&page=CP1&operation=5' class='fa fa-refresh'> update</a> | <a class='use-confirm fa fa-trash' href=''#' data-toggle='modal' data-target='#myModaldel'  data-backdrop='static' data-keyboard='false'> Delete</a></tr>";
    			   $(msg_data).appendTo("#bootstrap-table");
    			  
    			   });
    		   
              
    	     	$(".use-confirm").click(function() {
    	     	    var $row = $(this).closest("tr");// Find the row
    	     	    var $text = $row.find("#id").text(); // Find the text
    	     	  
    	     	    // Let's test it out
    	     	   $('#message').val($text);
    	     	    
    	     	});
    	     	$('.btnDelete').on('click', function (e) {
    	     	    e.preventDefault();
    	     	    var id = $(this).closest('tr').find(".nr").text();
    	     	   
    	     	    $('#myModal').data('id', id).modal('show');
    	     	});
    	     	$('#btnDelteYes').click(function () {
    	     	    var id = $('#myModal').data('id');
    	     	  
    	     	    alert(id+" deleted");
    	     	    $('#myModal').modal('hide');
    	     	    
    	     	});
    	     	$('.close').click(function() {                   
    	     		
    	     		 $('#alert').empty();
    	     		 $('#rolealert').empty();
    	     		 $('#text').val("");
    	     		 $('#role').val("");
    	     	  });
    	     	    $('#cancel').click(function() {                   
    	     		$('#alert').empty();
   	     		    $('#rolealert').empty();
   	     		    $('#text').val("");
   	     		    $('#role').val("");
    	       		 
   		     	});
    	     	
    	     	$("#bootstrap-table").append("</tbody>");
 			   
 			   $('#bootstrap-table').dataTable().columnFilter({ sPlaceHolder: "head:after",
 		              aoColumns: [
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
							<a href="menu4.htm?url=configuration.htm">Configuration</a>/
							<a href="configactions.htm?page=CP1&operation=0&status=A" class="left-breadcrumb-last-child">Employee</a> 
							</h5>
						</li>

						<!-- /.dropdown-messages -->
					</h5>
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

	<div class="col-lg-12 col-md-12 location" id="divEMSnavCenter">
		<h2>
			<center>
				<%=session.getAttribute("location").toString() %>
			</center>
		</h2>
	</div>

</div>

<div class="clearfix"></div>
<div class="row page-title-bg">
	<div class="col-lg-12">
		<h1 class="page-header-title">Employee</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>
<div id="role_error" onload="clearData();"><center><font color="red"><b>${error}</b></font></center></div>

<div class="">
	<div class="col-lg-12 col-md-12 search-config-align">
		<div class="" style="float: right; width: 100%;">
			<form class="form-inline" role="form">
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Search</legend>
					<div class="form-group status-input-style ">

						<div class="">
							<div class="" style="">									
								</label> 
								<label class="radio-inline check-all">
									<fieldset class="scheduler-border config-status-align inner-field-set ">
										<legend class="scheduler-border">Status</legend>

										<div class="radio1">
											<input type="radio" name="optradio"
												onclick="getAjaxDetails('all')" />All
										</div>

										<div class="radio2">
											<input type="radio" name="optradio" checked="checked"
												onclick="getAjaxDetails('A')" />Active
										</div>

										<div class="radio3">
											<input type="radio" name="optradio"
												onclick="getAjaxDetails('I')" />Inactive
										</div>
									</fieldset>
								</label>
							</div>
						</div>
					</div>

					<label class="search-submit-style search-btn-earthpit pull-right">
						<button type="button" class="btn-hp btn-warning" data-toggle="modal"
							data-target="#myModal" data-backdrop="static" data-keyboard="false">
							<i class="fa fa-plus"> </i> Add</button>
						<button type="button" class="btn-hp btn-success"
							onclick="updateAll()">Update All</button>
					</label>

				</fieldset>
			</form>
		</div>
		<br>
		<table class="table table-hover table-striped" id="bootstrap-table">
		</table>
		<br>
		<br>
		<br>
		<br>
	</div>
</div>

<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Add Employee</h4>
				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png" />" width="35"></a>
			</div>
			<div class="modal-body">
				<form:form name="form1" class="form-inline" role="form">
					<div class="form-group hpclusers-popup">

							<div class="devices-test">
								<label for="name"> Employee ID  <b>*</b></label> <input
							type="text" name="employeeID" class="form-control" required
							id="text" placeholder="Enter Employe ID" onkeydown="clearData()" maxlength="8">
								<div id="alert" class="alert-message"></div>
							</div>
							<div class="clear"> </div>
							<div class="devices-test">
								<label for="name"> Role  <b>*</b></label> <select
								class="form-control " name="roleID" id="role" onchange="clearData()">
								 <option value="">Select Roles</option>
								 <c:forEach var="role" items="${role}" varStatus="sno" >
									<option value="${role.roleID}">${role.description}</option>
								</c:forEach> 
							</select>
							<div id="rolealert" class="alert-message"></div>
							</div>
							<div class="clear"> </div>
							
							<input type="hidden" id="page" name="page" value="CP1">
							<input type="hidden" id="operation" name="operation" value="4">
							<button type="button" data-dismiss="modal"
							class="btn  btn-sm btn-primary popup-form-button-align" id="cancel" onclick="clearData()">Cancel</button>
						    <button type="button"
							class="btn pull-right  btn-sm btn-primary popup-form-button-align"
							data-target="#myModal" onclick="return validate();">
							Save</button>

							<!-- <button type="button" class="btn  btn-sm btn-primary popup-form-button-align" data-toggle="modal" data-target="#myModal">Save</button> -->
						</div>
				</form:form>
			</div>

		</div>
	</div>
</div>

<!-- Delete Popup -->
<div class="modal fade" id="myModaldel" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png"/>" width="35"></a>
			</div>
			<div class="modal-body">
				<div class="form-group  popup-form-align del-popup-button">
					<label>Are you sure you want to delete ? </label>
					<form:form class="form-inline" action="configactions.htm"
						method="post" modelAttribute="EmployeRoles" role="form">
						<input type="hidden" name="page" value="CP1">
						<input type="hidden" name="operation" value="3">
						<input type="hidden" id="message" name="employeeID">
						<button type="button" data-dismiss="modal"
							class="btn  btn-sm btn-primary popup-form-button-align">No</button>
						<button type="submit"
							class="btn  btn-sm btn-primary popup-form-button-align">Yes</button>
					</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Delete Popup End -->
<div class="modal fade" id="myModalupdate" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Update Employee</h4>

				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png" />" width="35"></a>

		</div>
		<div class="modal-body">

			<form:form class="form-inline" action="configactions.htm"
				method="post" role="form">
				<div class="form-group  popup-form-align">
					<label for="name"> <span>Employe Name </span> <input
						type="text" name="description" class="form-control" id="usertext"
						placeholder="Employerole" onload="getValue()"></label> <input
						type="hidden" name="page" value="CP1"> <input
						type="hidden" name="operation" value="2"> <input
						type="hidden" name="employeeID" id="employeeID">
					  <button type="submit"
						class="btn  btn-sm btn-primary popup-form-button-align"
						data-toggle="modal" data-target="#myModal">Update</button>
				</div>
			</form:form>
			</div>

		</div>
	</div>
</div>

<script>
         
            getDetails('A');
            function getDetails(status){
          	   
          	   $.ajax
          	   ({
          	   type: "GET",
          	   url: "ajaxActions.htm?page=EM1&status="+status,
          	   dataType:"json",
          	   cache:false,
          	   async:false,
          	   success: function(data)
          	   {
          		 
                  $.each(data.location, function(i,data){
          			var optionsAsString = "<option value='" + data.locationName + "'>" + data.locationName+ "</option>";
          			$( 'select[name="location"]' ).append( optionsAsString ); 
          			  });
          	      }
          	      });
                }
            function validate()
            {                 
              if(employeeformValidation(document.getElementById("text"),document.getElementById("alert"),document.getElementById("role"),document.getElementById("rolealert"))){
           	   var text=$("#text").val();
           	   $.ajax({
           		     url:"validation.htm?page=CPUD1&employeeID="+text,  
           		   	 dataType:"json",
           		   	 cache:false,
           		   	 async:false,
           		     success: function (data) {
           		    	 if(data.result=='valid'){
                                    formSubmit();
                               
           	                 	 $('#role').val('');
           		    	 }else{
           		    		$("<p>"+data.result+"</p>").appendTo("#alert");
           		    	 }
           		   }
           		});
             }else{
           	  
           	   return false;
           	  
              }
            }
            function upvalidate()
            {                  
              if(employeeformValidation(document.getElementById("usertext"),document.getElementById("upalert"))){
           		return true;
                	
              }else{
           	   return false;
              }
            }
            function clearData(){
          	  $("#alert").empty();
          	  $("#rolealert").empty();
          	}
            
          $(document).ready(function(){
       	$(".use-address").click(function() {
    	    var $row = $(this).closest("tr"); // Find the row
    	    var $text = $row.find("#id").text(); // Find the text
    	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
    	    
    	    // Let's test it out
    	   
    	   $('#usertext').val($textDesc);
    	   $('#employeeID').val($text);
    	    
    	});
    	$(".use-confirm").click(function() {
    	    var $row = $(this).closest("tr");// Find the row
    	    var $text = $row.find("#id").text(); // Find the text
    	  
    	    // Let's test it out
    	   $('#message').val($text);
    	    
    	});
    	$('.btnDelete').on('click', function (e) {
    	    e.preventDefault();
    	    var id = $(this).closest('tr').find(".nr").text();
    	   
    	    $('#myModal').data('id', id).modal('show');
    	});
    	$('#btnDelteYes').click(function () {
    	    var id = $('#myModal').data('id');
    	  
    	    alert(id+" deleted");
    	    $('#myModal').modal('hide');
    	});
    });

                  
           $(document).ready( function () {
            
          });
           
    
          function getLocationDetails(){
       		  var locid=$("#location").val();
       		 if(locid=='0'){
       			 
       		 }else{
       			 
       			 
       		   $.ajax
       		   ({
       		   type: "GET",
       		   url: "ajaxActions.htm?page=CP1&locationID="+locid+"&status=emploc",
       		   dataType:"json",
       		   async:false,
       		   cache:false,
       		   success: function(data)
       		   {   
       			  
       			   $("#bootstrap-table").dataTable().fnDestroy();
       	 		   $("#bootstrap-table").empty();
       	 		   
       	 		   var head_data="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='20%'>Employee Name</th><th width='22%'>Location</th><th width='13%'>Designation</th><th width='8%'>Status</th><th width='15%'>Action </th></thead>";
       	 		   var head_data1="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Employee ID</th><th width='20%'>Employee Name</th><th width='22%'>Location</th><th width='13%'>Designation</th><th width='8%'>Status</th><th width='15%'>Action </th></thead>";
       			   
       	 		   $(head_data).appendTo("#bootstrap-table");
       			   $(head_data1).appendTo("#bootstrap-table"); 
       			   
       			   $("#device").empty();
       			   var optionsAsString = "<option value='0'>Select Device</option>";
       		     	 
       		       $( 'select[name="device"]' ).append( optionsAsString );
       			   $.each(data.details, function(i,data){
       				   var status;
       	    		   if(data.empstat=='A'){
       	  				   status="Active";
       	  			  }else{
       	  				  status="Inactive";
       	  			  }
       				   
       				   var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='id'>"+data.empid+"</td><td id='empname'>"+data.empname+"</td><td id='emploc'>"+data.emploc+"</td><td id='emprole'>"+data.emprole+"</td><td id='status'>"+status+"</td><td><a href='configactions.htm?employeeID="+data.empid+"&page=CP1&operation=5' class='fa fa-refresh'> Update</a> | <a class='use-confirm fa fa-trash' href=''#' data-toggle='modal' data-target='#myModaldel'> Delete</a></tr>";
       				   $(msg_data).appendTo("#bootstrap-table"); 
       				
       			   });

 					 $("#bootstrap-table").append("</tbody>");
 				   
 				   $('#bootstrap-table').dataTable().columnFilter({ sPlaceHolder: "head:after",
 			              aoColumns: [
 			                          { type: "text" },
 			                          { type: "text" },
 			                          { type: "text" },
 									  { type: "text" },
 			                          { type: "text" },
 									  { type: "text" },
 									                                                      
 			                          ]
 			});
       			   
       			      $.each(data.device, function(i,data){
       				   var optionsAsString = "<option value='" + data.deviceName + "'>" + data.deviceName + "</option>";
       					$( 'select[name="device"]' ).append( optionsAsString ); 
       					})
       				}
       		   });
       		 }
       	     }
           $('.close').click(function() {                   
       	$('#role_error').empty();
       	
     		     	});
           $('#role_error').empty();
  </script>
