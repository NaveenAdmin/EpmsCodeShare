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
function updateAll(){
	var page=$("#page").val();
	var operation=$("#operation").val();
	var text=$("#text").val();
	
		 $.ajax({
		     url:"configactions.htm?page=CP2&operation=2",
		     data: $("#form1").serialize(),
		     success: function (data) {
		    	 
		    	 getAjaxDetails('A');
		   }
		});
		}
getAjaxDetails('A');
function formSubmit(){
	var page=$("#page").val();
	var operation=$("#operation").val();
	var text=$("#txtlocation").val();
		 $.ajax({
		     url:"configactions.htm?page="+page+"&operation="+operation+"&locationID="+text,
		     data: $("#form1").serialize(),
		     cache:false,
	    	 async:false,
		     success: function (data) {
		    	 $(".modal-backdrop").remove();
            	 $("body").removeClass("modal-open");
            	 $("#myModal").modal('hide'); 
                 getAjaxDetails('A');
		   }
		}); 
	
       }
       
       function getAjaxDetails(status){
    	 
    	   $.ajax
    	   ({
    	   type: "GET",
    	   url: "ajaxActions.htm?page=CP2&status="+status,
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
	    		   var head_data="<thead><tr><th width='8%'>Sl. No</th><th width='10%'>Location ID</th><th width='40%'>Location Name</th><th width='20%'>Zone</th><th width='10%'>Status</th></thead>";
	    		   var head_data1="<thead><tr><th width='8%'>Sl. No</th><th width='10%'>Location ID</th><th width='40%'>Location Name</th><th width='20%'>Zone</th><th width='10%'>Status</th></thead>";
	    		   $(head_data).appendTo("#bootstrap-table");
	    		   $(head_data1).appendTo("#bootstrap-table");
	    		   
	    		   $("#bootstrap-table").dataTable().fnDestroy();
	    		   $("#bootstrap-table").empty();
	    		   var head_data="<thead><tr><th width='8%'>Sl. No</th><th width='10%'>Location ID</th><th width='40%'>Location Name</th><th width='20%'>Zone</th><th width='10%'>Status</th></thead>";
	    		   var head_data1="<thead><tr><th width='8%'>Sl. No</th><th width='10%'>Location ID</th><th width='40%'>Location Name</th><th width='20%'>Zone</th><th width='10%'>Status</th></thead>";
	    		   $(head_data).appendTo("#bootstrap-table");
	    		   $(head_data1).appendTo("#bootstrap-table");
	    		   $.each(data.details, function(i,data){
	    			  var status;
	    			   if(data.locstat=='A'){
	    				   
	    				   status="Active";
	    			  }else{
	    				  
	    				 status="Inactive";
	    			  }
	    			   var tempemp=data.locname;
	    			   if(tempemp==null){
	    				   data.locname="";
	    			   }
	    			   var temploc=data.loczones;
	    			   if(temploc==null){
	    				   data.loczones="";
	    			   }
	    			   
	    			   var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='id'>"+data.locid+"</td><td id='roleDesc'>"+data.locname+"</td><td>"+data.loczones+"</td><td>"+status+"</td></tr>";
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
	    							                                                                          
	    	                          ]
	    	                });
	    		   
	    		   $(".use-confirm").click(function() {
	    	     	    var $row = $(this).closest("tr");
	    	     	// Find the row
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
	    	     	     $('#myModal').modal('hide');
	    	     	});
	    	     	$('.close').click(function() {                   
	    	     		
	    	     		 $('#alert').empty();
	    	     		 $('#txtlocation').val("");
	    	     		     	});
	    		  	   

			   }else{
			  
    		   $("#bootstrap-table").empty();
    		   var head_data="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Location ID</th><th width='35%'>Location Name</th><th width='20%'>Zone</th><th width='10%'>Status</th><th width='15%'>Action </th></thead>";
    		   var head_data1="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Location ID</th><th width='35%'>Location Name</th><th width='20%'>Zone</th><th width='10%'>Status</th><th width='15%'> </th></thead>";
    		   $(head_data).appendTo("#bootstrap-table");
    		   $(head_data1).appendTo("#bootstrap-table");
    		   
    		   $("#bootstrap-table").dataTable().fnDestroy();
    		   $("#bootstrap-table").empty();
    		   var head_data="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Location ID</th><th width='35%'>Location Name</th><th width='20%'>Zone</th><th width='10%'>Status</th><th width='15%'>Action </th></thead>";
    		   var head_data1="<thead><tr><th width='8%'>Sl. No</th><th width='12%'>Location ID</th><th width='35%'>Location Name</th><th width='20%'>Zone</th><th width='10%'>Status</th><th width='15%'> </th></thead>";
    		   $(head_data).appendTo("#bootstrap-table");
    		   $(head_data1).appendTo("#bootstrap-table");
    		   $.each(data.details, function(i,data){
    			  var status;
    			   if(data.locstat=='A'){
    				   
    				   status="Active";
    			  }else{
    				  
    				 status="Inactive";
    			  }
    			   var tempemp=data.locname;
    			   if(tempemp==null){
    				   data.locname="";
    			   }
    			   var temploc=data.loczones;
    			   if(temploc==null){
    				   data.loczones="";
    			   }
    			   
    			   var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='id'>"+data.locid+"</td><td id='roleDesc'>"+data.locname+"</td><td>"+data.loczones+"</td><td>"+status+"</td><td><a href='configactions.htm?locationID="+data.locid+"&page=CP2&operation=5' class='fa fa-refresh '> Update </a> | <a class='use-confirm fa fa-trash' href='#' data-toggle='modal' data-target='#myModaldel'   data-backdrop='static' data-keyboard='false'> Delete </a></tr>";
    			   $(msg_data).appendTo("#bootstrap-table");
    			  
    		   });
    		   
    		   
    		   
    		   $(".use-confirm").click(function() {
    	     	    var $row = $(this).closest("tr");
    	     	// Find the row
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
    	     	     $('#myModal').modal('hide');
    	     	});
    	     	$('.close').click(function() {                   
    	     		
    	     		 $('#alert').empty();
    	     		 $('#txtlocation').val("");
    	     		 $('#role_error').empty();
    	     		     	});
    	     	$('#cancel').click(function() {                   
             		
    	     		$('#alert').empty();
   	     		 $('#txtlocation').val("");
   	     		 $('#role_error').empty(); 
    	        });
    	     	     	
    	     	$("#bootstrap-table").append("</tbody>");
     		   
     		   $('#bootstrap-table').dataTable().columnFilter({ sPlaceHolder: "head:after",
     	              aoColumns: [
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
							<a href="home.htm">Home</a> /
							<a href="menu4.htm?url=configuration.htm">Configuration</a>/
							<a href="configactions.htm?page=CP2&operation=0" class="left-breadcrumb-last-child">Locations</a>
							</h5> <!-- /.dropdown-messages -->
						</li>
						<!-- /.dropdown -->
					</ul>
				</div>
			</div>

			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-right"
				id="divEMSnavRight">
				<div class="row">
					<ul class="nav navbar-top-links navbar-right">

						<!-- /.dropdown -->
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#"> <i class="fa fa-user"> </i>
								User:<%=session.getAttribute("userName").toString() %>
						</a></li>
						<!-- /.dropdown -->
						<li class="dropdown"><a href="logout.htm"> <i
								class="fa fa-sign-out"> </i> Logout
						</a> </a> <!-- /.dropdown-user --></li>
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
	<div class="row page-title-bg">
		<div class="col-lg-12">
			<h1 class="page-header-title">Locations</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
   
	<div class="">
		<div class="col-lg-12 col-md-12 search-config-align">
			<div class="" style="float: right; width: 100%;">
				<form class="form-inline" role="form">
					<fieldset class="scheduler-border">
						<legend class="scheduler-border">Search</legend>
						<div class="form-group status-input-style ">

							<div class=" ">
								<div class=" " style="">
									<label class="radio-inline check-all">
										<fieldset
											class="scheduler-border config-status-align inner-field-set ">
											<legend class="scheduler-border">Status</legend>


											<div class="radio1">
												<input type="radio" name="optradio"
													onclick="getAjaxDetails('all')">All
											</div>

											<div class="radio2">
												<input type="radio" name="optradio" checked="checked"
													onclick="getAjaxDetails('A')">Active
											</div>

											<div class="radio3">
												<input type="radio" name="optradio"
													onclick="getAjaxDetails('I')">Inactive
											</div>
										</fieldset>
									</label>
								</div>
							</div>
						</div>

						<label class="search-submit-style search-btn-earthpit pull-right">
							<button type="button" class="btn-hp btn-warning"
								data-toggle="modal" data-target="#myModal"  data-backdrop="static" data-keyboard="false">
								<i class="fa fa-plus"> </i> Add</button>
							<button type="button" class="btn-hp btn-success"
								onclick="updateAll()">Update All</button>
						</label>
					</fieldset>
				</form>
			</div>
			<br>
			<table class="table table-hover table-striped" id="bootstrap-table">
				<thead>
					<tr>
						<th width="10%">SNo</th>
						<th width="10%">Location ID</th>
						<th width="10%">Location Name</th>
						<th width="10%">Zone</th>
						<th width="10%">Status</th>
						<th width="10%">Action</th>
				</thead>
				<tbody>
					<c:forEach var="list" items="${list}" varStatus="sno">
						<tr id="rows">
							<td>${sno.index+1}</td>
							<td id="id">${list.locationID}</td>
							<td id="roleDesc">${list.locationName}</td>
							<td>${list.zones}</td>
							<td><a
								href="configactions.htm?locationID=${list.locationID}&page=CP2&operation=5">Update</a>
								|<a class="use-confirm" href="#" data-toggle="modal" data-target="#myModaldel">Delete</a></td>
						</tr>
					</c:forEach>
				</tbody>
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
					<h4>Add Location</h4>

					<a class="close" data-dismiss="modal"><img
						src="<c:url value="/resources/images/close.png" />" width="35"></a>

				</div>
				<div class="modal-body">
					<form:form name="form1" class="form-inline" role="form">
						<div class="form-group hpclusers-popup">

								<div class="devices-test">
									<label for="name"> Location Id <b>*</b> </label><input
								type="text" name="locationID" class="form-control"
								id="txtlocation" placeholder="Enter LocationID"
								onkeydown="clearData()" maxlength="6">
									<div id="alert"></div>
								</div>
								<div class="clear"> </div>
								
								<input type="hidden" id="page" name="page" value="CP2"> <input
								type="hidden" id="operation" name="operation" value="4">
								<button type="button" data-dismiss="modal" class="btn  btn-sm btn-primary popup-form-button-align" id="cancel" onclick="clearData()">Cancel</button>
							<button type="button"
								class="btn  btn-sm btn-primary popup-form-button-align"
								data-target="#myModal" onclick="return validate();">Save</button>
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
							method="post" role="form">
							<input type="hidden" name="page" value="CP2">
							<input type="hidden" name="operation" value="3">
							<input type="hidden" id="message" name="locationID">
							<button type="button" data-dismiss="modal" class="btn  btn-sm btn-primary popup-form-button-align">No</button>
							<button type="submit" class="btn  btn-sm btn-primary popup-form-button-align">Yes</button>
							
						</form:form>
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
					<h4>trfer Update LocationId</h4>

					<a class="close" data-dismiss="modal"><img
						src="<c:url value="/resources/images/close.png" />" width="35"></a>

				</div>
				<div class="modal-body">

					<form:form class="form-inline" action="configactions.htm"
						method="post" role="form">
						<div class="form-group  popup-form-align">
							<label for="name"> <span>Location Id </span> <input
								type="text" name="description" class="form-control"
								id="usertext" placeholder="Location id" onload="getValue()"></label>
							<input type="hidden" name="page" value="CP2"> <input
								type="hidden" name="operation" value="2"> <input
								type="hidden" name="locationID" id="locationID">
							<button type="submit"
								class="btn  btn-sm btn-primary popup-form-button-align"
								data-toggle="modal" data-target="#myModal">Update</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
</div>

<script>
             function validate()
             { 
               	 if(AlphaNumaricformValidation(document.getElementById("txtlocation"),document.getElementById("alert"))){
               		var text=$("#txtlocation").val();
               	   $.ajax({
          		     url:"validation.htm?page=CPUD2&locationID="+text,  
          		   	 dataType:"json",
          		   	 cache:false,
          		   	 async:false,
          		     success: function (data) {
          		    	 
          		    	 if(data.result=='valid'){
          		    		formSubmit();
                          	
                          	 document.form1.locationID.value=""; 
          		    	 }else{
          		    		$("<p>"+data.result+"</p>").appendTo("#alert");
          		    	 }
          		    }
          		 });
               }
               	
               }
           
             function clearData(){
            		
              $("#alert").empty();
           	  $("#rolealert").empty();
           	}
           $(document).ready(function(){
        	$(".use-address").click(function() {
     	    var $row = $(this).closest("tr");    // Find the row
     	    var $text = $row.find("#id").text(); // Find the text
     	    
     	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
     	    
     	    // Let's test it out
     	     $('#usertext').val($textDesc);
     	    $('#locationID').val($text);
     	    
     	});
     	$(".use-confirm").click(function() {
     	    var $row = $(this).closest("tr");
     	// Find the row
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
     	     $('#myModal').modal('hide');
     	});
     });
           $('.close').click(function() {                   
           	$('#role_error').empty();
           	
         		     	});
           
              </script>