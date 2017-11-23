
<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:28-12-15
Modified By	    :Prasad-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
  
  $( "#multiCheckBox" ).submit(function( event ) {
	  alert( "Handler for .submit() called." );
	  event.preventDefault();
	});
  var count;
  $(document).ready( function () {

 	 $(".use-address").click(function() {
	     	    var $row = $(this).closest("tr");// Find the row
	     	   
	     	    var $textloc = $row.find("#locName").text(); // Find the text
	     	    var $grpName = $row.find("#grpName").text();
	     	    var $textlev = $row.find("#escalevel").text(); // Find the text
	     	    var $grpID = $row.find("#grpID").text(); // Find the text  gName
	     	   //var $grpID = $row.find("#grpID").text(); // Find the text
	     	    $('#groupId').val($grpID);
	     	    $('#upgName').val($grpName);
	     	   var $locId = $row.find("#locId").text(); 
	     	   $('#uplocations').val($locId);
	     	    $('#escalevels').val($textlev);
	     	    

	     	 });
		   
		      $(".use-confirm").click(function() {
			     var $row = $(this).closest("tr");// Find the row
	     	     var $grpID = $row.find("#grpID").text();// Find the text
				

	     	    // Let's test it out
	     	    $('#groupid').val($grpID);

	     	    
	     	    });
		      $('.close').click(function() {                   
		    	     $('#addescalevel').val(""); 
		     		 $('#alert').empty();
		     		 $('#gId').val("");
		     		 $('#gName').val("");  
		     		 $('#locations').val(""); 
		     		 $('#levelalert').empty();
		     		 $('#localert').empty();
		     		 $('#gnamealert').empty();  
		     		 $('#checkalert').empty();
		     		 $("#uuplevelalert").empty();
		     		 $("#checkedid").empty();   
		     		 $("#Myuptable").empty();
		     		 $("#Mytable").empty();  
		     		 $("#uplocalert").empty();
		     		 $("#uplevelalert").empty();
		     		
		     		$('#gId').css('border-color', '');
		      		$('#gName').css('border-color', '');
		      		$('#locations').css('border-color', '');
		      		$('#addescalevel').css('border-color', '');
		     		     	});
		      $('#cancel').click(function() {                   
		    	     $('#addescalevel').val(""); 
		     		 $('#alert').empty();
		     		 $('#gId').val("");
		     		 $('#gName').val("");  
		     		 $('#locations').val(""); 
		     		 $('#levelalert').empty();
		     		 $('#localert').empty();
		     		 $('#gnamealert').empty();  
		     		 $('#checkalert').empty();
		     		 $("#uuplevelalert").empty();
		     		 $("#checkedid").empty();   
		     		 $("#Myuptable").empty();
		     		 $("#Mytable").empty();	
		     		 $("#uplocalert").empty()
		     		 $("#uplevelalert").empty();
		     		
		     		$('#gId').css('border-color', '');
		      		$('#gName').css('border-color', '');
		      		$('#locations').css('border-color', '');
		      		$('#addescalevel').css('border-color', '');
		      		
		       		 
		      });
              });
 function formSubmit(){
	 var pits = new Array();
	 $("input[name='pits']:checked").each(function(i) {
		 pits.push($(this).val());
	 });
	var groupID=$("#gId").val();
	var groupName=$("#gName").val();
	var locationID=$("#locations").val();
	var level=$("#addescalevel").val();

   	var page="CP71";
   	var operation="4";
	 	  $.ajax({
	 	     url:"ajaxActions.htm?pits="+pits+"&groupId="+groupID+"&groupName="+groupName+"&locationID="+locationID+"&level="+level+"&page="+page+"&operation="+operation,
	 	     data: $("#form1").serialize(),
	 	     cache:false,
	    	 async:false,
	 	     success: function (data) {
	 	    	    $(".modal-backdrop").remove();
            	    $("body").removeClass("modal-open");
	 	     	    $("#myModal").modal('hide'); 
	 	     	  getDetails('A');
	 	     	 $('#addescalevel').val(""); 
	     		 $('#alert').empty();
	     		 $('#gId').val("");
	     		 $('#gName').val("");  
	     		 $('#locations').val(""); 
	     		 $('#levelalert').empty();
	     		 $('#localert').empty();
	     		 $('#gnamealert').empty();  
	     		 $('#checkalert').empty();
	     		 $("#uuplevelalert").empty();
	     		 $("#checkedid").empty();   
	     		 $("#Myuptable").empty();
	     		 $("#Mytable").empty();  
	     		 $("#uplocalert").empty();
	     		 $("#uplevelalert").empty();
	 	    
	 	   }
	 	}); 
	 
 }
 function upformSubmit(){
	 var pits = new Array();
	 $("input[name='uppits']:checked").each(function(i) {
		 pits.push($(this).val());
	 });
	var groupID=$("#uptext").val();
	var groupName=$("#upgName").val();
	var locationID=$("#uplocations").val();
	var level=$("#escalevels").val();

   	var page="CP71";
   	var operation="4";
	 	  $.ajax({
	 	     url:"ajaxActions.htm?pits="+pits+"&groupId="+groupID+"&groupName="+groupName+"&locationID="+locationID+"&level="+level+"&page="+page+"&operation="+operation,
	 	     data: $("#form1").serialize(),
	 	     cache:false,
	 	     async:false,
	 	     success: function (data) {
	 	    	    $(".modal-backdrop").remove();
          	        $("body").removeClass("modal-open");
	 	     	    $("#myModalupdate").modal('hide');
	 	     getDetails('A');
	 	    
	 	   }
	 	}); 
	 
 }
 function getDevicesList(){
	 var location=$("#uplocations").val();
	    $.ajax({
	 		 type: "GET",
	 		 url: "ajaxActions.htm?page=CP7&status=location&locationID="+location,
	 		 dataType:"json", 
	 		 cache:false,
	 	     success: function (data) {
                $("#MytableUp").empty();
	 	    	$(head_data).appendTo("#MytableUp");
	 	    	if(data.pits==""){
	 	    		var head_data="<thead><tr>	<th><font color='red'>No Employees</font></th></tr>	</thead>";
	 	    		$(head_data).appendTo("#MytableUp");
	 	    	}else{
	 	    	var head_data="<thead><tr>	<th>Employees</th></tr>	</thead>";
	 	    	$(head_data).appendTo("#MytableUp");
	 	    	$.each(data.pits, function(i,data){
	 	    	var msg_data="<tr><td><input type='checkbox' id='checked'  name='uppits' value="+data.eId+">  "+data.eName+"</td></tr>";
	 			   $(msg_data).appendTo("#MytableUp");
	 	    	 });
	 	       }
	 	      }
	     	}); 
	      }
 function getDevices(){
	 var location=$("#locations").val();
	 $("#Mytable").empty();
	 if(location!=""){
		 $.ajax({
	 		 type: "GET",
	 		 url: "ajaxActions.htm?page=CP7&locationID="+location,
	 		 dataType:"json", 
	 		 cache:false,
	 	     success: function (data) {

	 	    	 if(data.pits==""){
	 	    		var head_data="<thead><tr>	<th><font color='red'>No Employees</font></th></tr>	</thead>";
	 	    		$(head_data).appendTo("#Mytable");
	 	    	}else{
	 	    	var head_data="<thead><tr>	<th>Employees</th></tr>	</thead>";
	 	    	$(head_data).appendTo("#Mytable");
	 	    	$.each(data.pits, function(i,data){
	 	    	var msg_data="<tr><td><input type='checkbox' id='checkedid' name='pits' onclick='clearData()' value="+data.eId+">  "+data.eName+"</td></tr>";
	 	    	 $(msg_data).appendTo("#Mytable");
	 	    	 
	 	         });
	 	        }
	 	      }
	 	   }); 
	 }
             
 }

 function getUpDevices(){
	 $("#Myuptable").empty();
	 var location=$("#uplocations").val();
	 var groupId=$("#uptext").val();
	 var level=$("#escalevels").val();
             $.ajax({
	 		 type: "GET",
	 		 url: "ajaxActions.htm?page=CP72&locationID="+location+"&level="+level+"&groupId="+groupId,
	 		 dataType:"json", 
	 		 cache:false,
	 	     success: function (data) {
	 	    	 $("#Myuptable").empty();
	 
	 	    	 if(data.emp==null){
	 	    		var head_data="<thead><tr>	<th><font color='red'>No Employees</font></th></tr>	</thead>";
	 	    		$(head_data).appendTo("#Myuptable");
	 	    		
	 	    	}else{
	 	    		if(data.emp.length>0){
	 	    			$('#upsubmit').show();
	 		 	    	var head_data="<thead><tr>	<th>Employees</th></tr>	</thead>";
	 		 	    	$(head_data).appendTo("#Myuptable");
	 		 	    	$.each(data.emp, function(i,data){
	 		 	    	var msg_data="<tr><td><input type='checkbox' id='upcheckedid' name='uppits' onclick='clearData()' value="+data.eId+">  "+data.eName+"</td></tr>";
	 		 			   $(msg_data).appendTo("#Myuptable");
	 		 	         });
	 	    		}else{
	 	    			$('#upsubmit').hide();
	 	    			var head_data="<thead><tr>	<th><font color='red'>No Employees</font></th></tr>	</thead>";
		 	    		$(head_data).appendTo("#Myuptable");
	 	    		}
	 	    
	 	        }
	 	      }
	 	   }); 
 }
  getDetails('A');
 function getDetails(status){
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=CP7&status="+status,
	   dataType:"json",
	   cache:false,
	   success: function(data)
	   {

		    if(data.details==""){
		    	
		    $("#bootstrap-table").empty();
		   var head_data="<p><h3><center><font size='5' color='red'> No Data Available</font><h3></center></p>";
		   $(head_data).appendTo("#bootstrap-table");
		   
	   }else{  
		   if(status == 'I' || status=='All'){
			   $("#bootstrap-table").empty();
			   var head_data="<thead><tr><th>Group Id</th><th>Group Name</th><th>Location Id</th><th>Location</th><th>Level</th><th>Employee Id</th><th>Employee Name</th><th>Phone No</th><th>Email Id</th><th>Role</th><th>Status</th><th>Actions</th></tr></thead>";
			   var head_data1="<thead><tr><th>Group Id</th><th>Group Name</th><th>Location Id</th><th>Location</th><th>Level</th><th>Employee Id</th><th>Employee Name</th><th>Phone No</th><th>Email Id</th><th>Role</th><th>Status</th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-table");
			   $(head_data1).appendTo("#bootstrap-table");
			   
			   $("#bootstrap-table").dataTable().fnDestroy();
			   $("#bootstrap-table").empty();
			   var head_data="<thead><tr><th>Group Id</th><th>Group Name</th><th>Location Id</th><th>Location</th><th>Level</th><th>Employee Id</th><th>Employee Name</th><th>Phone No</th><th>Email Id</th><th>Role</th><th>Status</th><th>Actions</th></tr></thead>";
			   var head_data1="<thead><tr><th>Group Id</th><th>Group Name</th><th>Location Id</th><th>Location</th><th>Level</th><th>Employee Id</th><th>Employee Name</th><th>Phone No</th><th>Email Id</th><th>Role</th><th>Status</th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-table");
			   $(head_data1).appendTo("#bootstrap-table");
			   
			   
			   $.each(data.details, function(i,data){
				  var status;
				   if(data.status=='A'){
					   status="Active";
				  }else{
					  status="Inactive";
				  }
				   var msg_data="<tr><td id='grpID'>"+data.grpID+"</td><td id='grpName'>"+data.grpName+"</td><td id='locId'>"+data.locId+"</td><td id='locName'>"+data.location+"</td><td id='escalevel'>"+data.escalevel+"</td><td id='empId'>"+data.empId+"</td><td id='empName'>"+data.empName+"</td><td id='emphno'>"+data.emphno+"</td><td id='empemail'>"+data.empemail+"</td><td id='emprole'>"+data.emprole+"</td><td>"+status+"</td><td><a href='#' class='use-address fa fa-pencil' data-toggle='modal' data-target='#myModalupdate'   data-backdrop='static' data-keyboard='false' > Edit</a> | <a class='use-confirm fa fa-trash' href='#' data-toggle='modal' data-target='#myModaldel'   data-backdrop='static' data-keyboard='false'> Delete </a></td></tr>";
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
	                          { type: "text" },
	                          { type: "text" },
							  { type: "text" },
	                          { type: "text" },
	                          { type: "text" },
	                          
							                                                                          
	                          ]
	                });
			       $(".use-address").click(function() {
		     	    var $row = $(this).closest("tr");    // Find the row
		     	    var $text = $row.find("#id").text(); // Find the text
		     	    var $textDesc = $row.find("#grpID").text(); // Find the text
		     	    var $textloc = $row.find("#locName").text(); // Find the text
		     	    var $textlev = $row.find("#escalevel").text(); // Find the text
		     	   var $locId = $row.find("#locId").text(); 
		     	 var $grpID = $row.find("#grpID").text(); // Find the text
		     	 
		     	    $('#uptext').val($grpID);
		     	    // Let's test it out
		     	    $('#gName').val($textDesc);
		     	    $('#earthpitID').val($text);
		     	   $('#uplocations').val($locId);
		     	   
		     	    $('#escalevels').val($textlev);
		     	   getUpDevices();
		     	  });
			   
			   $(".use-confirm").click(function() {
				     var $row = $(this).closest("tr");// Find the row
		     	     var $grpID = $row.find("#grpID").text();// Find the text
                    // Let's test it out
		     	    $('#groupid').val($grpID);
			   
	              });  
		   }else{
			   
		
		   $("#bootstrap-table").empty();
		   var head_data="<thead><tr><th>Group Id</th><th>Group Name</th><th>Location Id</th><th>Location</th><th>Level</th><th>Employee Id</th><th>Employee Name</th><th>Phone No</th><th>Email Id</th><th>Role</th><th>Status</th><th>Actions</th></tr></thead>";
		   var head_data1="<thead><tr><th>Group Id</th><th>Group Name</th><th>Location Id</th><th>Location</th><th>Level</th><th>Employee Id</th><th>Employee Name</th><th>Phone No</th><th>Email Id</th><th>Role</th><th>Status</th></tr></thead>";
		   $(head_data).appendTo("#bootstrap-table");
		   $(head_data1).appendTo("#bootstrap-table");
		   
		   $("#bootstrap-table").dataTable().fnDestroy();
		   $("#bootstrap-table").empty();
		   var head_data="<thead><tr><th>Group Id</th><th>Group Name</th><th>Location Id</th><th>Location</th><th>Level</th><th>Employee Id</th><th>Employee Name</th><th>Phone No</th><th>Email Id</th><th>Role</th><th>Status</th><th>Actions</th></tr></thead>";
		   var head_data1="<thead><tr><th>Group Id</th><th>Group Name</th><th>Location Id</th><th>Location</th><th>Level</th><th>Employee Id</th><th>Employee Name</th><th>Phone No</th><th>Email Id</th><th>Role</th><th>Status</th></tr></thead>";
		   $(head_data).appendTo("#bootstrap-table");
		   $(head_data1).appendTo("#bootstrap-table");
		   
		   
		   $.each(data.details, function(i,data){
			   var status;
			   if(data.status=='A'){
				   status="Active";
			  }else{
				  status="Inactive";
			  }
			   var msg_data="<tr><td id='grpID'>"+data.grpID+"</td><td id='grpName'>"+data.grpName+"</td><td id='locId'>"+data.locId+"</td><td id='locName'>"+data.location+"</td><td id='escalevel'>"+data.escalevel+"</td><td id='empId'>"+data.empId+"</td><td id='empName'>"+data.empName+"</td><td id='emphno'>"+data.emphno+"</td><td id='empemail'>"+data.empemail+"</td><td id='emprole'>"+data.emprole+"</td><td>"+status+"</td><td><a href='#' class='use-address fa fa-pencil' data-toggle='modal' data-target='#myModalupdate'   data-backdrop='static' data-keyboard='false' > Edit</a> | <a class='use-confirm fa fa-trash' href='#' data-toggle='modal' data-target='#myModaldel'   data-backdrop='static' data-keyboard='false'> Delete </a></td></tr>";
			   $(msg_data).appendTo("#bootstrap-table");
			  });
		   
		       $(".use-address").click(function() {
	     	    var $row = $(this).closest("tr");    // Find the row
	     	    var $text = $row.find("#id").text(); // Find the text
	     	    var $textDesc = $row.find("#grpID").text(); // Find the text
	     	    var $textloc = $row.find("#locName").text(); // Find the text
	     	    var $grpName = $row.find("#grpName").text(); // Find the text
	     	    var $textlev = $row.find("#escalevel").text(); // Find the text

	     	    var $grpID = $row.find("#grpID").text(); // Find the text
	     	   var $locId = $row.find("#locId").text(); 
	     	    $('#uptext').val($grpID);
	     	    // Let's test it out
	     	    $('#upgName').val($grpName);
	     	    $('#earthpitID').val($text);
	     	    $('#uplocations').val($locId);
	     	   
	     	    $('#escalevels').val($textlev);
	     	   getUpDevices();
	     	  });
		   
		   $(".use-confirm").click(function() {
			    var $row = $(this).closest("tr");// Find the row
	     	     var $grpID = $row.find("#grpID").text();// Find the text
	     	  
	     	     
	     	     var $grpName = $row.find("#grpName").text();
	     	     var $locId = $row.find("#locId").text();
	     	     var $escalevel = $row.find("#escalevel").text();
	     	     var $empId = $row.find("#empId").text();
			
			 $('#grpname').val($grpName);
			 $('#grplevel').val($escalevel);
			 $('#grplocationid').val($locId);
			 $('#grpemployeeid').val($empId);
	     	    // Let's test it out
	     	    $('#groupid').val($grpID);
		   
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
 function getcount(locid,devid){
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=CP7&status=count&locationID="+locid+"&deviceId="+devid,
	   dataType:"json",
	   cache:false,
	   success: function(data)
	   {  
		   $.each(data.details, function(i,data){
			   $("#connect").empty();
			   if(data.count<5){
				   getDevicesList()
				   $("<p>No Of Devices Connected: "+data.count+" </p> ").appendTo('#connect');
			   }else{
				   $("<p><font color='red'>Maximum Devices Reached </font></p> ").appendTo('#connect');
			   }
			  });
		    }
	   });
 };
 function validate()
 {      
	 
   if(groupformValidation(document.getElementById("gId"),document.getElementById("alert"),document.getElementById("gName"),document.getElementById("gnamealert"),document.getElementById("locations"),document.getElementById("localert"),document.getElementById("addescalevel"),document.getElementById("levelalert"))){
	   $("#checkalert").empty();
	   var cnt = $("input[name='pits']:checked").length;
	   
	   	        if (cnt < 1)
	            {
	   	        
	            $("<p>Select at least one employee.</p>").appendTo("#checkalert");
	   	            e.preventDefault();
	              }
	   
	   	        else{
	   	       var groupID=$("#gId").val();
	   	  	   var groupName=$("#gName").val();
	   	  	   $.ajax({
	   	  		     url:"validation.htm?page=CPUD6&groupId="+groupID+"&groupName="+groupName,
	   	  		   	 dataType:"json",
	   	  		     cache:false,
		    	     async:false,
	   	  		     success: function (data) {
	   	  		    	 if(data.result=='valid'){
	   	  		    		 formSubmit();
	   	  		    	   	  		        
	   	  		         }else{
	   	  		    		$("<p>"+data.result+"</p>").appendTo("#alert");
	   	  		    		$("<p>"+data.result+"</p>").appendTo("#gnamealert");
	   	  		    	    
	   	  		    	    $("<p>"+data.result+"</p>").appendTo("#checkalert");
	   	  		    	 }
	   	  		   }
	   	  		}); 
	   	        }

	}else{
	   return false;
   }
 }
 function upvalidate()
 {                  
   if(upgroupformValidation(document.getElementById("uplocations"),document.getElementById("uplocalert"),document.getElementById("escalevels"),document.getElementById("uplevelalert"))){
	   var cnt = $("input[name='uppits']:checked").length;
	   
	        if (cnt < 1)
       {
	   $("#uuplevelalert").empty();  
       $("<p>Select at least one employee.</p>").appendTo("#uuplevelalert");
	           
         }else{
        	 upformSubmit();
        	 location.reload();
         }
	        
	}else{
	   return false;
   }
 }
 function clearData(){
	  $("#alert").empty();
	  $("#localert").empty();
	  $("#levelalert").empty();
	  $("#gnamealert").empty();
	  $("#gName").empty();   
	  $("#checkalert").empty();
	  $("#checkalert").empty();
	  $("#uuplevelalert").empty();
	  $("#uplocalert").empty();
	  $("#uplevelalert").empty();
	  
	}
 function enableTxt(elem) {
	 $("#alert").empty();
	  $("#localert").empty();
	  $("#levelalert").empty();
	  $("#gnamealert").empty();
	  $("#gName").empty();   
	  $("#checkalert").empty();
	  $("#checkalert").empty();
	  $("#uuplevelalert").empty();
   var id = $(elem).attr("id");
   $("#"+id).css('border-color', '');
}
 </script>
 
<div id="wrapper">
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
								<a href="configactions.htm?page=CP7&operation=0&status=A" class="left-breadcrumb-last-child">Alert Group</a>
								</h5> <!-- /.dropdown-messages -->
						</li>
						<!-- /.dropdown -->
					</ul>
				</div>
			</div>
			<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-right"
				id="divEMSnavRight">

				<ul class="nav navbar-top-links pull-right ">
					<!-- /.dropdown -->
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> <i class="fa fa-user"> </i>
							User:<%=session.getAttribute("userName").toString() %>
					</a></li>

					<li class="dropdown"><a href="logout.htm"> <i
							class="fa fa-sign-out"></i> Logout
					</a></li>
					<!-- /.dropdown -->
				</ul>

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
	</div>

	<div class="clearfix"></div>

	<div class="row">
		<div class="col-lg-12">
			<h1 class="page-header-title">Alert Group List</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>

	<div class="">
		<div class="col-lg-12 col-md-12 search-config-align">
			<div class="" style="float: right; width: 100%;">
				<form class="form-inline" role="form">
					<fieldset class="scheduler-border">
						<legend class="scheduler-border">Search</legend>
						<div class="form-group status-input-style">

							<div class="form-group">
								<label class="radio-inline check-all">
									<fieldset class="scheduler-border config-status-align">
										<legend class="scheduler-border">Status</legend>

										<div class="radio1">
											<input id="rbtnAll" type="radio" name="optradio"
												onclick="getDetails('All')">All
										</div>

										<div class="radio2">
											<input id="rbtnAlctive" type="radio" name="optradio"
												checked="checked" onclick="getDetails('A')">Active
										</div>

										<div class="radio3">
											<input id="rbtnInactive" type="radio" name="optradio"
												onclick="getDetails('I')">Inactive
										</div>

									</fieldset>
								</label>
							</div>
						</div>

						<label class="config-search-submit-style-right pull-right">
							<button type="button" class="btn-hp btn-warning"
								data-toggle="modal" data-target="#myModal"  data-backdrop="static" data-keyboard="false">
								<i class="fa fa-plus"> </i> Add
							</button>
						</label>

					</fieldset>
				</form>
			</div>
			<br>
			<table class="table table-hover table-striped" id="bootstrap-table">
				<thead>
					<tr>
                        <th>Group ID</th>
						<th>Group Name</th>
						<th>Location</th>
						<th>Level</th>
						<th>Employee Name</th>
						<th>Phone No</th>
						<th>Email Id</th>
						<th>Role</th>
						<th>Status</th>
						<th>Actions</th> 
					</tr>
				</thead>
				<tbody>
					<c:forEach var="list" items="${list}" varStatus="sno">
						<tr id="rows">
							<%--  <td id="id">${list.SeqNo}</td> --%>
							<td id="grpID">${list.groupId}</td>
							<td id="grpName">${list.groupName}</td>
							<td id="locName">${list.location}</td>
							<td id="escalevel">${list.level}</td>
							<td id="empName">${list.employeeName}</td>
							<td id="emphno">${list.phoneNo}</td>
							<td id="empemail">${list.emailId}</td>
							<td id="emprole">${list.roleDesc}</td>
							<td> <a href="#" class="use-address" data-toggle="modal"
								data-target="#myModalupdate">Edit</a> | <a class="use-confirm"
								href="#" data-toggle="modal" data-target="#myModaldel">Delete</a>
							</td>
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
		<div class="modal-dialog  modal-sm">
			<div class="modal-content">
				<div class="content-header">
					<h4>Add Groups</h4>
					<a class="close" data-dismiss="modal"><img
						src="<c:url value="/resources/images/close.png"/>" width="35"></a>
				</div>
				<div class="modal-body">
					<form:form id="multiCheckBox" commandName="person"
						class="form-inline" role="form" method="post"
						action="groupmultiCheckbox.htm">
						<div class="form-group hpclusers-popup ">
							<label for="name"> <span> Group ID <b>*</b></span> <input
								type="text" name="groupId" class="form-control" id="gId"
								placeholder="Enter GroupId" onkeydown="enableTxt(this)" maxlength="4"></label>
							   <div id="alert" class="alert-message" ></div>
						</div>
						<div class="form-group hpclusers-popup">
							<label for="name"> <span> Group Name <b>*</b></span> <input
								type="text" name="groupName" class="form-control" id="gName"
								placeholder="Enter GroupName" onkeypress="enableTxt(this)"></label>
							<div id="gnamealert" class="alert-message"></div>
						</div>
						<div class="form-group hpclusers-popup">
							<div>
								<label for="name"> <span> Location <b>*</b></span> <select
									class="form-control" name="locationID" id="locations"
									onchange="getDevices()" onclick="enableTxt(this)">
										<option value="">Select Location</option>
										<c:forEach var="location" items="${location}" varStatus="sno">
											<%-- <option value="${location.locationID}">${location.locationName} </option> --%>
											<option value="${location.locationID}">${location.locationName}
											</option>
										</c:forEach>
								</select>
								<div id="localert" class="alert-message"></div>
								</label>
							</div>
							
							<div class="form-group hpclusers-popup">
								<label for="name"> <span style="width:100%;"> Escalation Level <b>*</b></span> <select
									class="form-control" id="addescalevel" name="level" onchange="enableTxt(this)">
									     <option value="">Select Level</option>
										<option value="1st level">1st Level</option>
										<option value="2nd level">2nd Level</option>
										<option value="3rd level">3rd Level</option>
								</select>
								<div id="levelalert" class="alert-message"></div>
								</label>
							</div>
							<div>
								<table class="table  table-striped" style="float: left;"
									id="Mytable">
								</table>
								<div id="checkalert" class="alert-message"></div>
							</div>
							<input type="hidden" name="page" value="CP7"> 
							<input type="hidden" name="operation" value="4">
							<button type="button" data-dismiss="modal"
							class="btn  btn-sm btn-primary popup-form-button-align" id="cancel"  onclick="clearData()">Cancel</button>
							<button type="button" onclick="return validate()"
                     class="btn pull-right btn-sm btn-primary popup-form-button-align"
                    >Save</button>
						</div>
					</form:form>
				</div>
			</div>
		</div>
	</div>
	<div class="modal fade" id="myModalupdate" role="dialog">
		<div class="modal-dialog  modal-sm">
			<div class="modal-content">
				<div class="content-header">
					<h4>Edit Groups</h4>
					<a class="close" data-dismiss="modal"><img
						src="<c:url value="/resources/images/close.png"/>" width="35"></a>
				</div>
				<div class="modal-body">
					<form:form id="multiCheckBox" commandName="person"
						class="form-inline" role="form" method="post"
						action="groupupdatemultiCheckbox.htm">
						<div class="form-group  popup-form-align group-cont">
							<div class="devices-test group-name">
								<label for="name"> Group ID <b>*</b> </label> <input type="hidden"
									id="groupId" name="groupId" > <input type="text"
									 name="groupnames" class="form-control"
									id="uptext" placeholder="Enter Category"
									onkeydown="clearData()" readonly="readonly">
								<div id="upalert" class="alert-message" ></div>
							</div>

						</div>
						<div class="form-group  popup-form-align group-cont">
							<div class="devices-test group-name">
								<label for="name"> Group Name <b>*</b> </label>  <input type="text"
									 name="groupnames" class="form-control"
									id="upgName" placeholder="Enter Category"
									onkeydown="clearData()" readonly="readonly">
								<div id="upalert" class="alert-message" ></div>
							</div>

						</div>
						<div class="form-group device-earthpit-popup">
							<div class="devices-test group-name">
								<label for="name"> Location <b>*</b></label> <select
									class="form-control" name="locationID" id="uplocations"
									onchange="getUpDevices()">
									<option value="0">Select Location</option>
									<c:forEach var="location" items="${location}" varStatus="sno">
										<%-- <option value="${location.locationID}">${location.locationName} </option> --%>
										<option value="${location.locationID}">${location.locationName}
										</option>
									</c:forEach>
								</select>
								<div id="uplocalert" class="alert-message" ></div>
							</div>
							
							<div class="devices-test group-name">
								<label for="name"> Escalation Level <b>*</b> </label> <select
									class="form-control" id="escalevels" name="level" onchange="getUpDevices()">
									<option value="">Select Level</option>
									<option value="1st level">1st Level</option>
									<option value="2nd level">2nd Level</option>
									<option value="3rd level">3rd Level</option>
								</select>
							</div>
							<div id="uplevelalert" class="alert-message" ></div>
							
						</div>
						
						<div id="connect"></div>
						<div>
							<table class="table  table-striped" style="float: left;"
								id="Myuptable">
							</table>
							
						</div>
						
						<input type="hidden" name="page" value="CP7">
						<input type="hidden" name="operation" value="4">
						  <button type="button" data-dismiss="modal"
							class="btn  btn-sm btn-primary popup-form-button-align" onclick="clearData()">Cancel</button>
							<button type="button" id="upsubmit" onclick="return upvalidate()"
							class="btn  btn-sm btn-primary popup-form-button-align up-load1"
							>Update</button>
							<div id="uuplevelalert" class="alert-message" ></div>
				</div>
				
				</form:form>
			</div>
		</div>
	</div>
	<!--  </div>
				</div> -->
	<div class="modal fade" id="myModaldel" role="dialog">
		<div class="modal-dialog modal-sm">
			<div class="modal-content">
				<div class="content-header">
					<a class="close" data-dismiss="modal"><img
						src="<c:url value="/resources/images/close.png" />" width="35"></a>
				</div>
				<div class="modal-body">
					<form:form class="form-inline form-border"
						action="configactions.htm" method="post" role="form">
						<div class="form-group  popup-form-align del-popup-button">
							<label>Are you sure you want to delete ? </label> <input
								type="hidden" name="page" value="CP7"> <input
								type="hidden" name="operation" value="3"> <input
								type="hidden" id="groupid" name="groupId">
								
								<input type="hidden" name="groupName" id="grpname">
								<input type="hidden" name="level" id="grplevel">
								<input type="hidden" name="locationID" id="grplocationid">
								<input type="hidden" name="employeeID" id="grpemployeeid">
								<button type="button" data-dismiss="modal"
								class="btn  btn-sm btn-primary popup-form-button-align">No</button>
							<button type="submit"
								class="btn  btn-sm btn-primary popup-form-button-align">Yes</button>
							
						   </div>
					</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>



