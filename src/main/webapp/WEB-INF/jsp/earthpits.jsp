<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:28-12-15
Modified By	    :Prasad-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
var maxDevice; 
var deviceCount;
var earthpitCount;
var maxearthpit;
var toatalearthpit;
</script>
<script>
$(document).ready(function(){
 	$(".use-address").click(function() {
 	    var $row = $(this).closest("tr");    // Find the row
 	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
 	    
 	    // Let's test it out
 	    $('#usertext').val($textDesc); 
 	    $('#earthpitID').val($text);
 	   });
    	$(".use-confirm").click(function() {
 	    var $row = $(this).closest("tr");    // Find the row
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
function formSubmit(){
 
 page=$("#page").val();
 var operation=$("#operation").val();
 var text=$("#text").val();
 var loc=$("#adlocation").val();
 
 
 if(toatalearthpit>earthpitCount){
 if(text==""){
 	
 }else{
	     $.ajax({
 	     url:"configactions.htm?page="+page+"&operation="+operation+"&earthpitName="+text+"&status=A&locationID="+loc,
 	     data: $("#form1").serialize(),
 	    cache:false,
   	    async:false,
 	     success: function (data) {
 	     
 	         $(".modal-backdrop").remove();
   	         $("body").removeClass("modal-open");
   	         $("#myModal").modal('hide'); 
   	      	getDetails('A');
   	      	$('#alert').empty();
  		 	$('#alert').val("");
  		 	$('#text').val("");
  		 	$('#adlocation').val("0");
  		 	$('#locationalert').empty();
  		 	$('#uplocation').empty();
  		 	$('#upalert').empty();    
 	   }
 	}); 
	  } 
 }else{
	   alert("Maximum Earthpit Count Reached...");
   }

}


function getDetails(status){

  $.ajax
   ({
   type: "GET",
   url: "ajaxActions.htm?page=CP4&status="+status,      
   dataType:"json",
   cache:false,
   success: function(data)
   { 
	  
	    maxDevice=data.maxdevice;
	    maxearthpit=data.maxearthpit;
	    //deviceCount=devcount
	    earthpitCount=data.earthcount;
	    toatalearthpit=maxDevice*maxearthpit;
	    
	  
	  
	    if(data==""){
		$("#bootstrap-table").empty();
	   var head_data="<p><h3><center><font size='5' color='red'> No Data Available</font><h3></center></p>";
	   $(head_data).appendTo("#bootstrap-table");
	   
   }else{
	    
	      if(status == 'I' || status=='All'){
	       
		   $("#bootstrap-table").empty();
		   var head_data="<thead><tr><th width='10%'>Earthpit ID</th><th width='35%'>Earthpit Name</th><th width='15%' class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th></thead>";
		   $(head_data).appendTo("#bootstrap-table");
		   var head_data1="<thead><tr><th width='10%'>Earthpit ID</th><th width='35%'>Earthpit Name</th><th width='15%' class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th></thead>";
		   $(head_data1).appendTo("#bootstrap-table");
 		    
 		   $("#bootstrap-table").dataTable().fnDestroy();
 		   
 		   $("#bootstrap-table").empty();
		   var head_data="<thead><tr><th width='10%'>Earthpit ID</th><th width='35%'>Earthpit Name</th><th width='15%'  class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th></thead>";
		   $(head_data).appendTo("#bootstrap-table");
		   var head_data1="<thead><tr><th width='10%'>Earthpit ID</th><th width='35%'>Earthpit Name</th><th width='15%' class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th></thead>";
		   $(head_data1).appendTo("#bootstrap-table");
		   
		   $.each(data.details, function(i,data){
			  var status;
			   if(data.earthstat=='A'){
				   status="Active";
			  }else{
				  status="Inactive";
			  }
			   var msg_data="<tr><td id='id'>"+data.earthid+"</td><td  id='roleDesc' >"+data.earthname+"</td><td id='earthloc' class='column-hide'>"+data.earthloc+"</td><td id=''>"+data.earthlocname+"</td><td>"+status+"</td></tr>";
			   $(msg_data).appendTo("#bootstrap-table");
			   
               
			   var optionsAsString = "<option value='" + data.earthlocname + "'>" + data.earthlocname+ "</option>";		
	 			$( 'select[name="location"]' ).append( optionsAsString );
	 			
	 		   var code = {};
	 		   $("select[name='location'] > option").each(function () {
	 		    if(code[this.text]) {
	 		        $(this).remove();
	 		    } else {
	 		        code[this.text] = data.earthlocname;
	 		    }
	 		});
	 		  			   
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
 		   

		   $(".use-address").click(function() {
	     	    var $row = $(this).closest("tr"); // Find the row
	     	    var $text = $row.find("#id").text(); // Find the text
	     	    var $textloc = $row.find("#earthloc").text(); // Find the text
	     	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
	     	    // Let's test it out
	     	    $('#usertext').val($textDesc);
	     	    $('#locations').val($textloc);
	     	    $('#earthpitID').val($text);
	     	   
	     	    
	     	});
		   
		   $(".use-confirm").click(function() {
	     	    var $row = $(this).closest("tr");    // Find the row
	     	    var $text = $row.find("#id").text(); // Find the text
	     	     var $textloc = $row.find("#earthloc").text();
	     	    // Let's test it out
	     	    $('#message').val($text);
	     	    $('#locmessage').val($textloc);
	     	    
	     	});
		   $('.close').click(function() {                   
	     		
	     		 $('#alert').empty();
	     		 $('#alert').val("");
	     		 $('#text').val("");
	     		 $('#adlocation').empty();
	     		 $('#locationalert').empty();
	     		 $('#uplocation').empty();
	     		 $('#upalert').empty();
	     		}); 
		      

		   
	   }else{
		
		   $("#bootstrap-table").empty();
	   var head_data="<thead><tr><th width='10%'>Earthpit ID</th><th width='35%'>Earthpit Name</th><th width='15%' class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th></thead>";
	   $(head_data).appendTo("#bootstrap-table");
	   var head_data1="<thead><tr><th width='10%'>Earthpit ID</th><th width='35%'>Earthpit Name</th><th width='15%' class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th></thead>";
	   $(head_data1).appendTo("#bootstrap-table");
		    
	   $("#bootstrap-table").dataTable().fnDestroy();
		   $("#bootstrap-table").empty();
	   var head_data="<thead><tr><th width='10%'>Earthpit ID</th><th width='35%'>Earthpit Name</th><th width='15%' class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th><th width='20%'>Action </th></thead>";
	   $(head_data).appendTo("#bootstrap-table");
	   var head_data1="<thead><tr><th width='10%'>Earthpit ID</th><th width='35%'>Earthpit Name</th><th width='15%' class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th></thead>";
	   $(head_data1).appendTo("#bootstrap-table");
	   
	   $.each(data.details, function(i,data){
		  var status;
		   if(data.earthstat=='A'){
			   status="Active";
		  }else{
			  status="Inactive";
		  }
		   var msg_data="<tr><td id='id'>"+data.earthid+"</td><td  id='roleDesc' >"+data.earthname+"</td><td id='earthloc' class='column-hide'>"+data.earthloc+"</td><td id=''>"+data.earthlocname+"</td><td>"+status+"</td><td><a href='#' class='use-address fa fa-pencil' data-toggle='modal' data-target='#myModalupdate'   data-backdrop='static' data-keyboard='false'> Edit </a> | <a class='use-confirm fa fa-trash' href=''#' data-toggle='modal' data-target='#myModaldel'   data-backdrop='static' data-keyboard='false'> Delete </a></td></tr>";
		   $(msg_data).appendTo("#bootstrap-table");
		   
              
		   var optionsAsString = "<option value='" + data.earthlocname + "'>" + data.earthlocname+ "</option>";		
 			$( 'select[name="location"]' ).append( optionsAsString );
 			
 		   var code = {};
 		   $("select[name='location'] > option").each(function () {
 		    if(code[this.text]) {
 		        $(this).remove();
 		    } else {
 		        code[this.text] = data.earthlocname;
 		    }
 		});
 		  			   
		 });
	   
		   $(".use-address").click(function() {
     	    var $row = $(this).closest("tr"); // Find the row
     	    var $text = $row.find("#id").text(); // Find the text
     	    var $textloc = $row.find("#earthloc").text(); // Find the text
     	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
     	    
     	    $('#usertext').val($textDesc);
     	    $('#locations').val($textloc);
     	    $('#earthpitID').val($text);
     	    $('#locationsID').val($textloc);
     	    
     	});
	   
	   $(".use-confirm").click(function() {
     	    var $row = $(this).closest("tr");    // Find the row
     	    var $text = $row.find("#id").text(); // Find the text
     	   var $textloc = $row.find("#earthloc").text();
     	    // Let's test it out
     	    $('#message').val($text);
     	    $('#locmessage').val($textloc);
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
	   $('.close').click(function() {                   
     		
     		 $('#alert').empty();
     		 $('#text').val("");
     		 $('#adlocation').val("0");
     		 $('#locationalert').empty();
     		 $('#uplocation').empty();
     		 $('#upalert').empty();
     		     	});
	   $('#cancel').click(function() {                   
    	     $('#alert').empty();
     		 $('#alert').val("");
     		 $('#text').val("");
     		 $('#adlocation').val("0");
     		 $('#locationalert').empty();
     		 $('#uplocation').empty();
     		 $('#upalert').empty();
       		 
  	     });
	   }    }
      }
   });
  }
getDetails('A');
function validate()  
{           	              
  if(earthformValidation(document.getElementById("text"),document.getElementById("alert"),document.getElementById("adlocation"),document.getElementById("locationalert"))){
     var text=$("#text").val();
	 var loc=$("#adlocation").val();
	   $.ajax({                   
  		     url:"validation.htm?page=CPUD4&earthpitName="+text+"&locationID="+loc,
  		   	 dataType:"json",
  		   cache:false,
 		   	 async:false,
  		     success: function (data) {
  		    	 if(data.result=='valid'){
                       
  	                 formSubmit();
  	              }else{
  		    		$("<p>"+data.result+"</p>").appendTo("#alert");
  		    		$("<p>"+data.result+"</p>").appendTo("#locationalert");
  		    	 }
  		   }
  		});
}else{
   return false;
  }
}
function upvalidate()
{                  
  if(earthformValidation(document.getElementById("usertext"),document.getElementById("upalert"),document.getElementById("locations"),document.getElementById("uplocation"))){
     var text=$("#usertext").val();
	 var loc=$("#locations").val();
	 var hint;
	   $.ajax({                   
  		     url:"validation.htm?page=CPUD4&earthpitName="+text+"&locationID="+loc,
  		   	 dataType:"json",
  		     cache:false,
  	   	     async:false,
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
  }else{
   return false;
  }
}
function clearData(){
  $("#alert").empty();
  $("#locationalert").empty();
  $("#upalert").empty();
  $("#uplocation").empty();
 
}

function getLocationDetails(){
	  var locid=$("#location").val();
	 
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=CP4&locationID="+locid+"&status=earthlocname",
	   dataType:"json",
	   success: function(data)
	   {
		   $("#bootstrap-table").dataTable().fnDestroy();
		   $("#bootstrap-table").empty();
		
		  var head_data="<thead><tr><th width='10%'>EarthpitID</th><th width='35%'>Earthpit Name</th><th width='15%' class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th><th width='20%'>Action </th></thead>";
		  var head_data1="<thead><tr><th width='10%'>EarthpitID</th><th width='35%'>Earthpit Name</th><th width='15%' class='column-hide'>Location ID</th><th width='30%'>Location Name</th><th width='10%'>Status</th><th width='20%'>Action </th></thead>";
		   $(head_data).appendTo("#bootstrap-table");
		   $(head_data1).appendTo("#bootstrap-table");
		   
		   $("#device").empty();
		   var optionsAsString = "<option value='0'>Select Device</option>";
	     	 
	       $( 'select[name="device"]' ).append( optionsAsString );
		   $.each(data.details, function(i,data){
			  var status;
	  		 	
		   if(data.earthstat=='A'){
			   status="Active";
		  }else{
			  status="Inactive";
		  }
			   var msg_data="<tr><td id='id'>"+data.earthid+"</td><td  id='roleDesc' >"+data.earthname+"</td><td class='column-hide'>"+data.earthloc+"</td><td>"+data.earthlocname+"</td><td>"+status+"</td><td><a href='#' class='use-address' data-toggle='modal' data-target='#myModalupdate' >Edit</a> | <a class='use-confirm' href=''#' data-toggle='modal' data-target='#myModaldel'>Delete</a></td></tr>";
			   var msg_data1="<tr><td id='id'>"+data.earthid+"</td><td  id='roleDesc' >"+data.earthname+"</td><td class='column-hide'>"+data.earthloc+"</td><td>"+data.earthlocname+"</td><td>"+status+"</td><td><a href='#' class='use-address' data-toggle='modal' data-target='#myModalupdate' >Edit</a> | <a class='use-confirm' href=''#' data-toggle='modal' data-target='#myModaldel'>Delete</a></td></tr>";
			   $(msg_data).appendTo("#bootstrap-table"); 
			   $(msg_data1).appendTo("#bootstrap-table"); 
			
		   });
		  
		   $.each(data.device, function(i,data){
			   var optionsAsString = "<option value='" + data.deviceName + "'>" + data.deviceName + "</option>";
				$( 'select[name="device"]' ).append( optionsAsString ); 
				})
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
							<a href="configactions.htm?page=CP4&operation=0&status=A" class="left-breadcrumb-last-child">Earthpit</a>
							</h5> <!-- /.dropdown-messages -->
					</li>
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

<div class="row " id="divEMSPageTitle">
	<div class="col-lg-12">
		<h1 class="page-header-title">Earthpit</h1>
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
						<label class="radio-inline check-all">
							<fieldset
								class="scheduler-border config-status-align inner-field-set ">
								<legend class="scheduler-border">Status</legend>

								<div class="radio1">
									<input type="radio" name="optradio"
										onclick="getDetails('All')">All
								</div>

								<div class="radio2">
									<input type="radio" name="optradio" checked="checked"
										onclick="getDetails('A')">Active
								</div>

								<div class="radio3">
									<input type="radio" name="optradio" onclick="getDetails('I')">Inactive
								</div>
                          </fieldset>
						</label>
                  </div>

					<label class="config-search-submit-style pull-right">
						<button type="button" class="btn-hp btn-warning"
							data-toggle="modal" data-target="#myModal" data-backdrop="static" data-keyboard="false">
							<i class="fa fa-plus"> </i> Add
						</button>
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
<div class="row">
	<div class="col-lg-12 col-md-6">

		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="content-header">
						<h4>Add Earthpit</h4>
						<a class="close" data-dismiss="modal"><img
							src="<c:url value="/resources/images/close.png"/>" width="35"></a>
					</div>
					<div class="modal-body">
						<form:form name="form1" class="form-inline" role="form">
							<div class="form-group hpclusers-popup">
								<div class="devices-test">
									<label for="name"> Location <b>*</b> </label> 
									 <select onchange="clearData()"
										class="form-control" id="adlocation">
								             <option value="0">Select Location</option>
								             <c:forEach var="location" items="${location}"
												varStatus="sno" >
												<option value="${location.locationID}">${location.locationName}</option>
											</c:forEach>
									</select>
									<div id="locationalert" class="alert-message"></div>
									
								</div>
								<div class="clear"> </div>
								
								<div class="devices-test">
									
										<label for="name">  Earthpit Name <b>*</b></label>
										<input	type="text" name="earthpitname" id="text" required
											placeholder="Enter Earthpitname" onkeydown="clearData()">
										<div id="alert"  class="alert-message"></div>
										
									</div>
									
									<div class="clear"> </div>
									<input type="hidden" id="page" name="page" value="CP4">
										<input type="hidden" id="operation" name="operation"
											value="4">
											<button type="button" data-dismiss="modal" class="btn  btn-sm btn-primary popup-form-button-align" id="cancel" onclick="clearData()">Cancel</button>
										<button type="button"
											class="btn pull-right btn-sm btn-primary popup-form-button-align"
											data-target="#myModal" onclick="return validate();">Save</button>
									</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
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
							type="hidden" name="page" value="CP4"> <input  
							type="hidden" name="operation" value="3"> <input
							type="hidden" id="message" name="locationID">
							<input type="hidden" id="locmessage" name="earthpitID">
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

	<div class="clearfix"></div>

</div>
</div>
<div class="modal fade" id="myModalupdate" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Update Earthpit</h4>

				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png" />" width="35"></a>

		</div>
		<div class="modal-body">
			<form class="form-inline" action="configactions.htm" method="post"
				modelAttribute="EmployeRoles" role="form"
				onsubmit="return upvalidate()">
				<div class="form-group  hpclusers-popup">
					<div class="devices-test">
						<label for="name"> Location </label> <select class="form-control"
							id="locations" name="uplocationID"  disabled="true" onchange="clearData()" >
							  <option value="0">Select Location</option>
							<c:forEach var="location" items="${location}" varStatus="sno" >
								<option value="${location.locationID}">${location.locationName}</option>
							</c:forEach>
						</select>
						<div id="uplocation" class="alert-message"></div>
					</div>
					<div class="clear"> </div>
					
					<div class="devices-test">
					<label for="name"> <span>Eathpit Name <b>*</b> </span></label> <input
						type="text" name="earthpitName" class="form-control"
						id="usertext" placeholder="EarthpitName"  onkeydown="clearData()">
					    <div id="upalert" class="alert-message"></div>
					</div>
					<div class="clear"> </div>
					
					<input type="hidden" name="page" value="CP4"> <input
						type="hidden" name="operation" value="2"> <input
						type="hidden" name="earthpitID" id="earthpitID">
					<input type="hidden" name="locationID" id="locationsID">
					<button type="button" data-dismiss="modal" class="btn  btn-sm btn-primary popup-form-button-align" id="cancel" onclick="clearData()">Cancel</button>
					<button type="submit"
						class="btn  btn-sm btn-primary popup-form-button-align up-load"
						data-target="#myModal">Update</button>
				</div>
			</form>
			</div>

		</div>
	</div>
</div>
</div>
</div>


