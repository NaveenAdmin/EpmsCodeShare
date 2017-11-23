<!-- "src/main/webapp/WEB-INF/jsp/earthpit.jsp" -->
<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:28-12-15
Modified By	    :Prasad-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<style> /* d3js graph css */
	

body
        {
            font-family: Arial;
            font-size: 10pt;
        }
        .modal
        {
            position: fixed;
            z-index: 999;
            height: 100%;
            width: 100%;
            top: 0;
            left: 0;

        }
        .center
        {
            z-index: 1000;
            margin: 300px auto;
            padding: 10px;
            width: 130px;
            
            border-radius: 10px;
            filter: alpha(opacity=100);
            opacity: 1;
            -moz-opacity: 1;
        }
        .center img
        {
            height: 180px;
            width: 180px;
        }
</style>
<script>
function getLocationDetailsByZoneId(){
	//alert($("#zones :selected").attr('value'))
	var zId=$("#zones :selected").attr('value');
	var optionsAsString ="";
	var select = document.getElementById("location1");
	select.options.length = 1;
	if(zId==1)
		{
		 optionsAsString = "<option value='12921'>Gujarath</option><option value='11981'>Maharastra</option>";
		}
	else if(zId==2)
		{
		 optionsAsString = "<option value='11833'>Orissa</option><option value='14957'>Assam</option><option value='11856'>Madhya Pradash</option>";
		}
	else if(zId==3)
	{
		 optionsAsString = "<option value='12844'>Punjab</option><option value='12982'>Jammu</option>";
	}
	else if(zId==4)
	{
		 optionsAsString = "<option value='11990'>Vizag</option><option value='12731'>Rajahmundary</option><option value='11979'>Hyderabad</option>";
	}
	
	
	 
    $( 'select[name="location1"]' ).append( optionsAsString );
}
function getLocationDetailsST(){
	  var locid=$("#location1").val();
	 
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=EM1&locationID="+locid+"&status=location",
	   dataType:"json",
	   success: function(data)
	   {
		   $("#device").empty();
		   var optionsAsString = "<option value='0'>Select Device</option>";
	     	 
	       $( 'select[name="device"]' ).append( optionsAsString );
	       $.each(data.device, function(i,data){
			   var optionsAsString = "<option value='" + data.deviceId + "'>" + data.deviceName + "</option>";
				$( 'select[name="device"]' ).append( optionsAsString ); 
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
								<a href="menu2.htm?url=earthpit.htm" class="left-breadcrumb-last-child">Earthpit Monitoring</a>
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
<div class="row " id="divEMSPageTitle">
	<div class="col-lg-12">
		<h1 class="page-header-title">Earthpit Monitoring</h1>
	</div>
	<!-- /.col-lg-12 -->
</div>

<div class="col-lg-12 col-md-12">
	<div class="" style="float: right; width: 100%;">
		<form class="form-inline" role="form">
			<fieldset class="scheduler-border">
				<legend class="scheduler-border">Search</legend>
				<div class="form-group status-input-style">
					<div class="search-location">
						
							<%if(session.getAttribute("userName").toString().equals("admin") || (session.getAttribute("role").toString().equals("0"))){
							  %>
							  	
				   <label for="name"> Location <br>
					<select class="form-control select-box-style"
						onchange="getLocationDetails()" name="location" id="location">
							<option value="0">Select Location</option>
					</select>
					</label> <label for="device"> Device<br> <select
						class="form-control select-box-style" name="device" id="device">
							
							<option value="0">Select Device</option>
					</select>
				 </div>
				
				<div class="date-pic-cls">
					</label> <label class="datepicker-input-style"><strong>
							From </strong><br>
					<input type="text" class="date-txt-cls" name="fromdatetime"
						id="fromdatetime">
					<p class="date-icon">
							<i class="fa fa-calendar"> </i>
						</p></label> <label class="datepicker-input-style"><strong>
							To </strong><br>
					<input type="text" class="date-txt-cls" name="todatetime"
						id="todatetime">
					<p class="date-icon">
							<i class="fa fa-calendar"> </i>
						</p></label> <input type="hidden" name="page" id="page" value="DATESEARCH">
					<label class="search-submit-style search-btn-earthpit"><input
						type="button" class="btn-hp btn-info" value="Submit"
						onclick="getSearhDateDetails()" class="radio-online-sub"></label> 
							  	
								<%
							}else{
								%>
								<label for="name"> Zones <br> <select
								class="form-control select-box-style"
								onchange="getLocationDetailsByZoneId()" name="zones" id="zones" <%if(!session.getAttribute("zoneStatic").toString().equals("0")){%>disabled="disabled"  <% } %>
								>
									<option value="0">Select</option> 
									<option value="1" <%if(session.getAttribute("zoneStatic").toString().equals("1")){%>Selected  <% } %>>East</option> 
									<option value="2" <%if(session.getAttribute("zoneStatic").toString().equals("2")){%>Selected  <% } %>>West</option> 
									<option value="3" <%if(session.getAttribute("zoneStatic").toString().equals("3")){%>Selected  <% } %>>North</option> 
									<option value="4" <%if(session.getAttribute("zoneStatic").toString().equals("4")){%>Selected  <% } %>>South</option>
							</select>
							</label>
								
								 <label for="name"> Location <br>
								 
								 <select
								class="form-control select-box-style"
								onchange="getLocationDetailsST()" name="location1" id="location1"
								>
									<option value="">Select</option> 
									
							</select>
		
					<%-- <select class="form-control select-box-style"
						 name="location" id="location" disabled="disabled">
							<option value="<%=session.getAttribute("locid").toString() %>"><%=session.getAttribute("location").toString() %></option>
					</select> --%>
					<script>
					
					getSelLocDevDetails();
					function getSelLocDevDetails(){
						
						  var locid=$("#location").val();
						   $.ajax
						   ({
						   type: "GET",
						   url: "ajaxActions.htm?page=EM1&locationID="+locid+"&status=location",
						   dataType:"json",
						   success: function(data)
						   {
  
							   $("#device").empty();
							   var optionsAsString = "<option value='0'>Select Device</option>";
						     	 
						       $( 'select[name="device"]' ).append( optionsAsString );
							   $.each(data.details, function(i,data){
							
							   });
							
							   $.each(data.device, function(i,data){
								   var optionsAsString = "<option value='" + data.deviceId + "'>" + data.deviceName + "</option>";
									$( 'select[name="device"]' ).append( optionsAsString ); 
									})
							   }
						   });
					} 
					</script>
					</label> <label for="device"> Device<br> <select
						class="form-control select-box-style" name="device" id="device" onload="getDeviceDetails()">
							
							<option value="0">Select Device</option>
					</select>
				 </div>
				
				<div class="date-pic-cls">
					</label> <label class="datepicker-input-style"><strong>
							From </strong><br>
					<input type="text" class="date-txt-cls" name="fromdatetime"
						id="fromdatetime">
					<p class="date-icon">
							<i class="fa fa-calendar"> </i>
						</p></label> <label class="datepicker-input-style"><strong>
							To </strong><br>
					<input type="text" class="date-txt-cls" name="todatetime"
						id="todatetime">
					<p class="date-icon">
							<i class="fa fa-calendar"> </i>
						</p></label> <input type="hidden" name="page" id="page" value="DATESEARCH">
					<label class="search-submit-style search-btn-earthpit"><input
						type="button" class="btn-hp btn-info" value="Submit"
						onclick="getSearhDateDetails()" class="radio-online-sub"></label> 
								 <%
							       }
								%>
	
				   </div>
				
				</div>

				<script>
       $(document).ready(function () {
    	   getLocationDetailsByZoneId();
           var d = new Date();
           var monthNames = ["January", "February", "March", "April", "May", "June",
               "July", "August", "September", "October", "November", "December"];
           today = monthNames[d.getMonth()] + ' ' + d.getDate() + ' ' + d.getFullYear();

          // $('#from').attr('disabled', 'disabled');
           $('#todatetime').datepicker({
               defaultDate: "+1d",
               //minDate: 1,
               maxDate: 0,
               //dateFormat: 'yy-mm-dd',
               dateFormat: 'yy-mm-dd '+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds(),
               showOtherMonths: true,
               changeMonth: true,
               selectOtherMonths: true,
               required: true,
               showOn: "focus",
               numberOfMonths: 1,
               onSelect: function(datetext){
            	   //$('#fromdatetime').val('');
                   var d = new Date(); // for now                  
                   $('#fromdatetime').val(datetext);
               },
           }).datepicker("setDate", new Date());           

        //  $('#todatetime').change(function () {
               var from = $('#todatetime').datepicker('getDate');
               var date_diff = Math.ceil((from.getTime() - Date.parse(today)) / 86400000);
               var maxDate_d = date_diff - 1 + 'd';
               date_diff = date_diff - 'd';
               $('#fromdatetime').val('').removeAttr('disabled').removeClass('hasDatepicker').datepicker({
                   //dateFormat: 'yy-mm-dd',
                   dateFormat: 'yy-mm-dd '+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds(),
                   minDate: date_diff,
                   maxDate: maxDate_d,
                   onSelect: function(datetext){
                	   //$('#fromdatetime').val('');
                       var d = new Date(); // for now                      
                       $('#fromdatetime').val(datetext);
                   },
               }).datepicker("setDate",new Date());
           //});

           $('#fromdatetime').keyup(function () {
        	   if($(this).val()=="")
               alert('Please select date from Calendar');
           });
           $('#todatetime').keyup(function () {
               $('#todatetime,#fromdatetime').val('');
               //$('#fromdatetime').attr('disabled', 'disabled');
               alert('Please select date from Calendar');
           });

       });
   </script>
				</fieldset>
			</form>
			<div class="modal" style="display: none">
        <div class="center">
            <img  src="<c:url value="/resources/images/loader.gif" />" />
        </div>
    </div>
		</div>
		<br>
		<table class="table table-hover table-striped" id="bootstrap-table">
		</table>
	</div>
</div>


<script>


<!-- testing -->

function fromDate(date) {
return date.getFullYear()+"-"+(date.getMonth()+1)+"-" +(date.getDate()-1) + " " +date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	}
function toDate(date) {
	return date.getFullYear()+"-"+(date.getMonth()+1)+"-" +date.getDate() + " " +date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
		}
	var d = new Date();
<!--    -->
 getDetails('A');
 function getDetails(status){
	 var sfromdate=fromDate(d);
	 var stodate=toDate(d); 
	 
   $.ajax
   ({
   type: "GET",
   url: "ajaxActions.htm?page=EM1&status="+status+"&fromdatetime="+sfromdate+"&todatetime="+stodate,
   dataType:"json",
   success: function(data)
   {   
	   $("#bootstrap-table").empty();
	   var head_data="<thead><tr><th width='20%'>Location Name</th><th width='20%'>Device Name</th><th width='20%'>Earthpit Name</th><th width='20%'>Voltage </th><th width='20%'>Date </th></tr></thead>";
	   var head_data1="<thead><tr><th width='20%'>Location Name</th><th width='20%'>Device Name</th><th width='20%'>Earthpit Name</th><th width='20%'>Voltage </th><th width='20%'>Date </th></tr></thead>";
	   $(head_data).appendTo("#bootstrap-table");
	   $(head_data1).appendTo("#bootstrap-table");
	   
	   $.each(data.details, function(i,data){
		  
		    if(data.voltage >'07.00'){
			   
		    	var msg_data="<tr bgcolor='red'><td id='locationName'>"+data.locationName+"</td><td id='devicename'>"+data.deviceName+"</td><td id='earthpitname'>"+data.earthpitName+"</td><td id='voltage'>"+data.voltage+"</td><td id='date'>"+data.receivedDate+"</td>";
			   $(msg_data).appendTo("#bootstrap-table");
		   }else{
			
			   var msg_data="<tr><td id='locationName'>"+data.locationName+"</td><td id='devicename'>"+data.deviceName+"</td><td id='earthpitname'>"+data.earthpitName+"</td><td id='voltage'>"+data.voltage+"</td><td id='date'>"+data.receivedDate+"</td>";
			   $(msg_data).appendTo("#bootstrap-table");
		   }
          
		  
	   });
	   
	     $.each(data.location, function(i,data){
		  var optionsAsString = "<option value='" + data.locationId + "'>" + data.locationName + "</option>";
		  $( 'select[name="location"]' ).append( optionsAsString );
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
   });
     }
 function getLocationDetails(){
  
  var locid=$("#location").val();
   $.ajax
   ({
   type: "GET",
   url: "ajaxActions.htm?page=EM1&locationID="+locid+"&status=location",
   dataType:"json",
   success: function(data)
   {
	   $("#device").empty();
	   var optionsAsString = "<option value='0'>Select Device</option>";
     	 
       $( 'select[name="device"]' ).append( optionsAsString );
	   $.each(data.details, function(i,data){
		});
	
	   $.each(data.device, function(i,data){
		   var optionsAsString = "<option value='" + data.deviceId + "'>" + data.deviceName + "</option>";
			$( 'select[name="device"]' ).append( optionsAsString ); 
			})
	   }
   });
    }
 function getDeviceDetails(){
   var divid=$("#device").val();
   var locid=$("#location").val();
   $.ajax
   ({
   type: "GET",
   url: "ajaxActions.htm?page=EM1&deviceId="+divid+"&locationID="+locid+"&status=device",
   dataType:"json",
   success: function(data)
   {
	   $("#bootstrap-table").empty();
	 
      }
   });
   }
 function getSearhDateDetails(status){
  var locid=$("#location").val();
  var divid=$("#device").val();
  var fromdate=$("#fromdatetime").val();
  var todate=$("#todatetime").val();
  var page=$("#page").val();
    
   $.ajax
   ({
	   global: false,
   type: "GET",
  
	url: "ajaxActions.htm?status='A'&page="+page+"&locationID="+locid+"&deviceId="+divid+"&fromdatetime="+fromdate+"&todatetime="+todate,	  
   dataType:"json",
   beforeSend: function () {
       $(".modal").show();
   },
   complete: function () {
       $(".modal").hide();
   },
   success: function(data)
   {
	   $("#bootstrap-table").dataTable().fnDestroy();
	   $("#bootstrap-table").empty();
	   var head_data="<thead><tr><th width='20%'>Location Name</th><th width='20%'>Device Name</th><th width='20%'>Earthpit Name</th><th width='20%'>Voltage </th><th width='20%'>Date </th></thead>";
	   var head_data1="<thead><tr><th width='20%'>Location Name</th><th width='20%'>Device Name</th><th width='20%'>Earthpit Name</th><th width='20%'>Voltage </th><th width='20%'>Date </th></thead>";
	   $(head_data).appendTo("#bootstrap-table");
	   $(head_data1).appendTo("#bootstrap-table");
	       
		   $.each(data.details, function(i,data){
			   if(data.voltage >'07.00'){
				
				   var msg_data="<tr bgcolor='red'><td id='locationName'>"+data.locationName+"</td><td id='devicename'>"+data.deviceName+"</td><td id='earthpitname'>"+data.earthpitName+"</td><td id='voltage'>"+data.voltage+"</td><td id='date'>"+data.receivedDate+"</td>";
				   $(msg_data).appendTo("#bootstrap-table");
			   
			   }else{
		          var msg_data="<tr><td id='locationName'>"+data.locationName+"</td><td id='devicename'>"+data.deviceName+"</td><td id='earthpitname'>"+data.earthpitName+"</td><td id='voltage'>"+data.voltage+"</td><td id='date'>"+data.receivedDate+"</td>";
	              $(msg_data).appendTo("#bootstrap-table");
			   }
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
                  })  
	
      }
   });
    }
 </script>

