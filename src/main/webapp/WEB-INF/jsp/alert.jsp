<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:28-12-15
Modified By	    :Prasad-->


<style type="text/css">
.action {}
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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div id="page-wrapper">
	<div class="col-lg-12 col-md-12 content-top-header">
		<div class="row">
		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12  breadcrumbs-left"
			id="divEMSnavLeft">
			<div class="row">
				<ul class="nav navbar-top-links navbar-left">
				    <li class="dropdown">
						<h5>
						<a href="home.htm">Home</a>/
						<a href="menu3.htm?url=alert.htm" class="left-breadcrumb-last-child">Alert Management</a>
						</h5> 
				    </li>
				</ul>
			</div>
		</div>
        <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12  breadcrumbs-right"
			id="divEMSnavRight">
			<div class="row">
				<ul class="nav navbar-top-links pull-right ">
					
					<li class="dropdown"><a class="dropdown-toggle"
						data-toggle="dropdown" href="#"> <i class="fa fa-user"> </i>
							User:<%=session.getAttribute("userName").toString() %>
					</a></li>
                          <li class="dropdown"><a href="logout.htm"> <i
							class="fa fa-sign-out"> </i> Logout
					</a></li>
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
    <div class="row" id="divEMSPageTitle">
	<div class="col-lg-12">
		<h1 class="page-header-title">Alert Management</h1>
	</div>
   </div>
	<div class="clearfix"> </div>
	
	<div class="">
		<div class="col-lg-12 col-md-12">
			<div class="" style="float: right; width: 100%;">
				<form class="form-inline " role="form">
					<fieldset class="scheduler-border">
						<legend class="scheduler-border">Search</legend>
						<div class="form-group status-input-style">
						<div class="">
							<div class="" style="">									
								</label> 
								<label class="radio-inline check-all">
									<fieldset class="scheduler-border config-status-align inner-field-set ">
										<legend class="scheduler-border">Status</legend>

										<div class="radio1">
											<input type="radio" name="optradio" checked="checked" onclick="getDetails('all')" />All							
										</div>

										<div class="radio2">
											<input type="radio" name="optradio"  onclick="getDetails('O')" />Open
										</div>

										<div class="radio3">
											<input type="radio" name="optradio"	onclick="getDetails('E')" />Escalated
										</div>
										
										<div class="radio3">
											<input type="radio" name="optradio"	onclick="getDetails('C')" />Closed
										</div>
									</fieldset>
								</label>
							</div>
							</div>
						<div class="form-group">
						<%if(session.getAttribute("userName").toString().equals("admin") || (session.getAttribute("role").toString().equals("0"))){
								  %>
								  <label for="name"> Location <br>
							<select class="form-control select-box-style"
								onchange="getLocationDetails()" name="location" id="location">
									<option value="0">Select Location</option>
							</select>
							</label> 
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
									<%-- <label for="name"> Location <br>
							<select class="form-control select-box-style"
								onchange="getLocationDetails()" name="location" id="location" disabled="disabled">
									<option value="0"><%=session.getAttribute("location").toString() %></option>
							</select> --%>
							</label> 
									 <%
								       }
									%>
							   <label for="device">Failure Type<br> <select class="form-control select-box-style"
								name="failureType" id="failureType">
									<option value="0">Select Failure Type</option>
									<option value="Device">Device</option>
									<option value="Earthpit">Earthpit</option>

							</select>
							</label> <label class="datepicker-input-style"><strong>From</strong><br>
							<input type="text" class="date-txt-cls" name="fromdatetime"
								id="fromdatetime">
							<p class="date-icon">
									<i class="fa fa-calendar"> </i>
								</p></label> <label class="datepicker-input-style"><strong>To</strong><br>
							<input type="text" class="date-txt-cls" name="todatetime"
								id="todatetime">
							<p class="date-icon">
									<i class="fa fa-calendar"> </i>
								</p></label> <input type="hidden" name="page" id="page"
								value="DATESEARCHFORALERT"> <label
								class="search-submit-style search-btn-earthpit"> <input
								type="button" class="btn-hp btn-info" value="Submit"
								onclick="getSearhDateDetails()"></label>
						</div>
						</div>
						<script>
         jQuery(document).ready(function ($) {
        	 getLocationDetailsByZoneId();
        	 var d = new Date();
            var monthNames = ["January", "February", "March", "April", "May", "June",
                "July", "August", "September", "October", "November", "December"];
            today = monthNames[d.getMonth()] + ' ' + d.getDate() + ' ' + d.getFullYear();

          
            $('#todatetime').datepicker({
                defaultDate: "+1d",
                maxDate: 0,
                dateFormat: 'yy-mm-dd',
                showOtherMonths: true,
                changeMonth: true,
                selectOtherMonths: true,
                required: true,
                showOn: "focus",
                numberOfMonths: 1,
            }).datepicker("setDate", new Date());           

        
                var from = $('#todatetime').datepicker('getDate');
                var date_diff = Math.ceil((from.getTime() - Date.parse(today)) / 86400000);
                var maxDate_d = date_diff - 0 + 'd';
                date_diff = date_diff - 'd';
                $('#fromdatetime').val('').removeAttr('disabled').removeClass('hasDatepicker').datepicker({
                    dateFormat: 'yy-mm-dd',
                    minDate: date_diff,
                    maxDate: maxDate_d
                }).datepicker("setDate",new Date());
           

            $('#fromdatetime').keyup(function () {
                $(this).val('');
                alert('Please select date from Calendar');
            });
            $('#todatetime').keyup(function () {
                $('#todatetime,#fromdatetime').val('');
                 alert('Please select date from Calendar');
            });

        });
       </script>
					</fieldset>
				</form>
			</div>
			<br>
	<table id="bootstrap-table" class="display" cellspacing="0" width="100%">
    </table>
	</div>
	</div>
</div>
<!----------------pop up start--------------->
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog modal-popup-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Edit Alert Details</h4>
				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png" />" width="35"></a>
			</div>
			<div class="modal-body">
				<form class="form-inline form-border" role="form">
					
				<div class="modal-popup-sm-div">
						<label for="name"><span>ID</span>
							<div class="popup-input">
								<input type="text" class="form-control popup-input-align"
									id="id" disabled="disabled" style="">
							</div>
						</label>
				  </div>

				<div class="modal-popup-sm-div">
					<label for="name"><span>Location Name </span>
						<div class="popup-input">
							<input type="text" class="form-control popup-input-align"
								id="locationname" disabled="disabled" style="">
						</div> 
					</label>
				</div>
					
				<div class="clear"> </div>
				
				<div class="modal-popup-sm-div">
					<label for="name"><span>Failure Type</span>
						<div class="popup-input">
							<input type="text" class="form-control popup-input-align"
								id="failuretype" disabled="disabled" style="">
						</div> 
					</label>
				</div>

				<div class="modal-popup-sm-div">
					<label for="name"><span> Failure Date </span>
						<div class="popup-input">
							<input type="text" class="form-control popup-input-align"
								id="failuredate" disabled="disabled" style="">
						</div> 
					</label>
				</div>
				<div class="clear"> </div>

				<div class="modal-popup-sm-div">
					<label for="name"><span>Escalation Id </span>
						<div class="popup-input">
							<input type="text" class="form-control popup-input-align"
								id="escalationid" disabled="disabled" style="">
						</div> 
					</label>
				</div>

					<div class="modal-popup-sm-div">
						<label for="name"><span> Escalation Level </span>
							<div class="popup-input">
								<input type="text" class="form-control popup-input-align"
									id="escalationlevel" disabled="disabled" style="">
							</div> 
						</label>
					</div>
					<div class="clear"> </div>

					<div class="modal-popup-sm-div">
						<label for="name"><span> Group </span>
							<div class="popup-input">
								<input type="text" class="form-control popup-input-align"
									id="group" disabled="disabled" style="">
							</div> 
						</label>
					</div>

					<div class="modal-popup-sm-div">
						<label for="name"><span> Action Taken <b>*</b></span>
							<div class="popup-input">
								<input type="text" class="form-control popup-input-align"
									id="actiontaken" style="">
							</div> 
						</label>
					</div>
					<div class="clear"> </div>

					<div class="modal-popup-sm-div">
						<label for="name"><span>Down Time </span>
							<div class="popup-input">
								<input type="text" class="form-control popup-input-align"
									id="downtime" disabled="disabled" style="">
							</div> 
						</label>
					</div>

					<div class="modal-popup-sm-div">
						<label for="name"><span> Repeat Time </span>
							<div class="popup-input">
								<input type="text" class="form-control popup-input-align"
									id="repeattime" disabled="disabled" style="">
							</div> 
						</label>
					</div>

				<div class="clear"> </div>
				
					<div class="modal-popup-sm-div">

						<label> <span> Status <b>*</b></span> 
						<select class="form-control select-box-style"
							name="status" id="statuss">
<!-- 								<option value="0">Select Status</option>
								<option value="O">Open</option>
								<option value="C">Close</option> -->
						</select>
						</label>
					</div>
					<br>
					<div class="modal-popup-sm-div"></div>
					<div class="modal-popup-sm-div">
					<button type="button" data-dismiss="modal" id="cancel"
								class="btn  btn-sm btn-primary popup-form-button-align" onclick="clearData()">Cancel</button>
						<button type="button"
							class="btn  btn-sm btn-primary popup-form-button-align"
							data-toggle="modal" data-target="#myModal" onclick="updateAll()">Update</button>
					</div>
				</form>
			</div>			
		</div>
	</div>
</div>
<!-- </div> -->

<div class="clearfix"></div>
<script src="<c:url value="resources/js/jquery.dataTables.min.js"/>"></script>
<script src="<c:url value="resources/js/jquery.dataTables.columnFilter.js"/>"></script> 

  <script>

  getDetails('A');
 function getDetails(status){
	 
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=ALTM&status="+status,
	   dataType:"json",
	   success: function(data)
	   {

		   $.each(data.location, function(i,data){
			var optionsAsString = "<option value='" + data.locationId + "'>" + data.locationName + "</option>";
			$( 'select[name="location"]' ).append( optionsAsString ); 
		   })
		   $("#bootstrap-table").empty();
		   var head_data="<thead><tr><th width='4%'>ID</th><th width='18%'>Location Name</th><th width='18%'>Equipment Name</th><th width='15%'>Failure Date </th><th width='20%'>Failure Type</th><th width='20%'>Escalated Date </th><th width='26%'>Escalated Level </th><th width='10%'>Group</th><th width='10%'>Action Taken</th><th width='10%'>Fixed Date </th><th width='10%'>Down Time</th><th width='10%'>Repeat Time</th><th width='10%'>Status</th><th width='10%'>Action</th></thead>";
		   var head_data1="<thead><tr><th width='4%'>ID</th><th width='18%'>Location Name</th><th width='18%'>Equipment Name</th><th width='15%'>Failure Date </th><th width='20%'>Failure Type </th><th width='20%'>Escalated Date </th><th width='26%'>Escalated Level </th><th width='10%'>Group</th><th width='10%'>Action Taken</th><th width='10%'>Fixed Date </th><th width='10%'>Down Time</th><th width='10%'>Repeat Time</th><th width='10%'>Status</th> </thead><tbody>";
		   $(head_data).appendTo("#bootstrap-table");
		   $(head_data1).appendTo("#bootstrap-table");
		   
		   $("#bootstrap-table").dataTable().fnDestroy();
		   $("#bootstrap-table").empty();

		   var head_data="<thead><tr><th width='4%'>ID</th><th width='18%'>Location Name</th><th width='18%'>Equipment Name</th><th width='15%'>Failure Date </th><th width='20%'>Failure Type</th><th width='20%'>Escalated Date </th><th width='26%'>Escalated Level </th><th width='10%'>Group</th><th width='10%'>Action Taken</th><th width='10%'>Fixed Date </th><th width='10%'>Down Time</th><th width='10%'>Repeat Time</th><th width='10%'>Status</th><th width='10%'>Action</th></thead>";
		   var head_data1="<thead><tr><th width='4%'>ID</th><th width='18%'>Location Name</th><th width='18%'>Equipment Name</th><th width='15%'>Failure Date </th><th width='20%'>Failure Type </th><th width='20%'>Escalated Date </th><th width='26%'>Escalated Level </th><th width='10%'>Group</th><th width='10%'>Action Taken</th><th width='10%'>Fixed Date </th><th width='10%'>Down Time</th><th width='10%'>Repeat Time</th><th width='10%'>Status</th> </thead><tbody>";
		   $(head_data).appendTo("#bootstrap-table");
		   $(head_data1).appendTo("#bootstrap-table");
		   
		   $.each(data.details, function(i,data){
			    var status;
			    var setting=false;
				if(data.Status=='O'){
					status='Open';
					
				}else if(data.Status=='L'){
					status='1st Level';
				}else if(data.Status=='M'){
					status='2nd Level';
				}else if(data.Status=='H'){
					status='3rd Level';
				}else if(data.Status=='C'){
					status='Closed';
					setting=true;
				}else{
					status=data.Status;
					setting=true;
				}
				
				if(setting){
					var msg_data="<tr><td id='tid' >"+data.Id+"</td><td id='tloc'>"+data.location+"</td><td id='tdiv'>"+data.devicename+"</td><td id='tdateoffail'>"+data.DOF+"</td><td id='tescaid'>"+data.EscalationType+"</td><td id='tmsd'>"+data.MSD+"</td><td id='tescalevel'>"+data.EscalationLevel+"</td><td id='tgroup'>"+data.GroupName+"</td><td id='taction'>"+data.ActionTaken+"</td><td id='tactiondate'>"+data.ActionDate+"</td><td id='tdiff'>"+data.diffTime+"</td><td id='trepeate'>"+data.RepeatTime+"</td><td id='tstatus'>"+status+"</td><td id='date'></td></tr>";
				}else
			   var msg_data="<tr><td id='tid' >"+data.Id+"</td><td id='tloc'>"+data.location+"</td><td id='tdiv'>"+data.devicename+"</td><td id='tdateoffail'>"+data.DOF+"</td><td id='tescaid'>"+data.EscalationType+"</td><td id='tmsd'>"+data.MSD+"</td><td id='tescalevel'>"+data.EscalationLevel+"</td><td id='tgroup'>"+data.GroupName+"</td><td id='taction'>"+data.ActionTaken+"</td><td id='tactiondate'>"+data.ActionDate+"</td><td id='tdiff'>"+data.diffTime+"</td><td id='trepeate'>"+data.RepeatTime+"</td><td id='tstatus'>"+status+"</td><td id='date'><a href='#' class='use-address fa fa-pencil-square-o' data-toggle='modal' data-target='#myModal'></a></td></tr>";
			   $(msg_data).appendTo("#bootstrap-table");
			   
			  
		   });
		   $("#bootstrap-table").append("</tbody>");
		   $(".use-address").click(function() {
				 $("#statuss").empty();
	 		        var $row = $(this).closest("tr");    // Find the row
	 		       
		     	    var $text = $row.find("#tid").text(); // Find the text
		     	    var $textloc = $row.find("#tloc").text(); // Find the text
		     	    var $textftype = $row.find("#tdiv").text(); // Find the text
		     	    var $textfdate = $row.find("#tdateoffail").text(); // Find the text
		     	  
		     	    
		     	    var $textesid = $row.find("#tescaid").text(); // Find the text
		     	    var $textlevel = $row.find("#tescalevel").text(); // Find the text
		     	    var $textgroup = $row.find("#tgroup").text(); // Find the text
		     	    
		     	    var $textactiontaken = $row.find("#taction").text(); // Find the text
		     	    var $textactiondate = $row.find("#tactiondate").text(); // Find the text
		     	    var $textdntm = $row.find("#tdiff").text(); // Find the text
		     	    var $textrpt = $row.find("#trepeate").text(); // Find the text
		     	    var $textsts = $row.find("#tstatus").text(); // Find the text
		     	 
		     	    var status;
					if($textsts=='Open'){
						status='O';
					}else if($textsts=='1st Level'){
						status='L';
					}else if($textsts=='2nd Level'){
						status='M';
					}else if($textsts=='3rd Level'){
						status='H';
					}else if($textsts=='Closed'){
						status='C';
					}
					
					
					$("#statuss").append(new Option("Close", "C"));
					$("#statuss").append(new Option($textsts, status));
					$("#statuss").val(status);
		     	    // Let's test it out
		     	    $('#id').val($text);
		     	    $('#locationname').val($textloc);
		     	    $('#failuretype').val($textftype);
		     	    $('#failuredate').val($textfdate);
		     	    
		     	    $('#escalationid').val($textesid);
		     	    $('#escalationlevel').val($textlevel);
		     	    $('#group').val($textgroup);
		     	    
		     	    $('#actiontaken').val($textactiontaken);
		     	    $('#fixeddate').val($textactiondate);
		     	    $('#downtime').val($textdntm);
		     	    $('#repeattime').val($textrpt);
		     	    //$('#statuss').val(status);
		     	 
		          });
			   
			        $(".use-confirm").click(function() {
		     	    var $row = $(this).closest("tr");    // Find the row
		     	    var $text = $row.find("#id").text(); // Find the text
		     	   
		     	   
		     	    // Let's test it out
		     	    $('#message').val($text);
		     	   });
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
							  { type: "text" },  
							  { type: "text" },  
							  { type: "text" }, 
							  { type: "text" }, 
								  
	                          ]
	         });
		 	  
			 
			        //$(".pull-left").remove();
					  
	       }
	   });
      }
  function getLocationDetails(){
	  var locid=$("#location").val();
	 
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=EM1&locationID="+locid+"&status=location&status="+status,
	   dataType:"json",
	   success: function(data)
	   {
 $( 'select[name="device"]' ).append( optionsAsString );
      }
	   });
     }
  function getDeviceDetails(){
	   var divid=$("#device").val();
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=ALTM&deviceId="+divid+"&status=device",
	   dataType:"json",
	   success: function(data)
	   {
		  
		   $("#bootstrap-table").empty();
		   var head_data="<thead><tr><th width='10%'>ID</th><th width='10%'>Location Name</th><th width='10%'>Equipment Name</th><th width='10%'>Failure Date </th><th width='10%'>Failure Type </th><th width='10%'>Escalated Date </th><th width='10%'>Escalated Level </th><th width='10%'>Group</th><th width='10%'>Action Taken</th><th width='10%'>Fixed Date </th><th width='10%'>Down Time</th><th width='10%'>Repeat Time</th><th width='10%'>Status</th><th width='10%'>Action</th></thead>";
		   $(head_data).appendTo("#bootstrap-table");
		   $.each(data.details, function(i,data){
           var msg_data="<tr><td id='tid' >"+data.Id+"</td><td id='tloc'>"+data.location+"</td><td id='tdiv'>"+data.devicename+"</td><td id='tdateoffail'>"+data.DOF+"</td><td id='tescaid'>"+data.EscalationType+"</td><td id='tmsd'>"+data.MSD+"</td><td id='tescalevel'>"+data.EscalationLevel+"</td><td id='tgroup'>"+data.GroupName+"</td><td id='taction'>"+data.ActionTaken+"</td><td id='tactiondate'>"+data.ActionDate+"</td><td id='tdiff'>"+data.diffTime+"</td><td id='trepeate'>"+data.RepeatTime+"</td><td id='tstatus'>"+data.Status+"</td><td id='date'><a href='#' class='use-address' data-toggle='modal' data-target='#myModal'><span class='fa fa-pencil-square-o'></span></a>  ";

			   $(msg_data).appendTo("#bootstrap-table");
			   
			   var temactiontaken=data.ActionTaken;
			   if(temactiontaken==null){
				   data.ActionTaken="";
				   
			   }
			   var temactiondate=data.ActionDate;
			   if(temactiondate==null){
				   data.ActionDate="";
			   }
			   var tempdifftime=data.diffTime;
			   if(tempdifftime==null){
				   data.diffTime="";
			   }
			   
		   });
	     }
	   });
   }
  function getFilterDetails(status){
	   
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=EM1&status="+status,
	   dataType:"json",
	   success: function(data)
	   {
             $.each(data.location, function(i,data){
			  // alert(data.locationName);
		   var optionsAsString = "<option value='" + data.locationId + "'>" + data.locationName + "</option>";
  		   $( 'select[name="location"]' ).append( optionsAsString );  
  		   }); 
	      }
	   });
     }
  $(document).ready( function () {
 	 $(".use-address").click(function() {
 	
 		        var $row = $(this).closest("tr");    // Find the row
 		        
	     	    var $text = $row.find("#tid").text(); // Find the text
	     	    var $textloc = $row.find("#tloc").text(); // Find the text
	     	    var $textftype = $row.find("#tdiv").text(); // Find the text
	     	    var $textfdate = $row.find("#tdateoffail").text(); // Find the text
	     	  
	     	    
	     	    var $textesid = $row.find("#tescaid").text(); // Find the text
	     	    var $textlevel = $row.find("#tescalevel").text(); // Find the text
	     	    var $textgroup = $row.find("#tgroup").text(); // Find the text
	     	    
	     	    var $textactiontaken = $row.find("#taction").text(); // Find the text
	     	    var $textactiondate = $row.find("#tactiondate").text(); // Find the text
	     	    var $textdntm = $row.find("#tdiff").text(); // Find the text
	     	    var $textrpt = $row.find("#trepeate").text(); // Find the text
	     	    var $textsts = $row.find("#tstatus").text(); // Find the text
	     	    
	     	    // Let's test it out
	     	  
	     	    $('#id').val($text);
	     	    $('#locationname').val($textloc);
	     	    $('#failuretype').val($textftype);
	     	    $('#failuredate').val($textfdate);
	     	    
	     	    $('#escalationid').val($textesid);
	     	    $('#escalationlevel').val($textlevel);
	     	    $('#group').val($textgroup);
	     	    
	     	    $('#actiontaken').val($textactiontaken);
	     	    $('#fixeddate').val($textactiondate);
	     	    $('#downtime').val($textdntm);
	     	    $('#repeattime').val($textrpt);
	     	    $('#tstatus').val($textsts);
	     	 
	          });
		   
		        $(".use-confirm").click(function() {
	     	    var $row = $(this).closest("tr");    // Find the row
	     	    var $text = $row.find("#id").text(); // Find the text
	     	   
	     	   
	     	    // Let's test it out
	     	    $('#message').val($text);
	     	   });
              });
  function updateformSubmit(){
  	var page=$("#uppage").val();
  	var operation=$("#upoperation").val();
  	
  	var device_ID = $('#deviceid').val();
  	var ip_Address = $('#ipaddress').val();
  	var deviceSeletc_Id = $('#deviceloc').val();
    var earthpit_Count = $('#earthpitCount').val();
  	var time_Interval = $('#timeinterval').val();
    var serial_No = $('#serialno').val();

  	if(device_ID == "" || ip_Address == "" || deviceSeletc_Id == "" || 
  	   earthpit_Count == "" || time_Interval == "" || installed_Date == "" || serial_No == "") {
  		
  	}else{
  		 $.ajax({
  		     url:"configactions.htm?page="+page+"&operation="+operation+"&deviceId="+device_ID+"&ipAddress="+ip_Address+"&locationID="+deviceSeletc_Id+
  		    		 "&earthPitCount="+earthpit_Count+"&timeInterval="+time_Interval+
  		    		 "&deviceSno="+serial_No,
  		     data: $("#form").serialize(),
  		     success: function (data) {
  		    	 
  		    	 getDetails('A');
  		   }
  		});	
  	}
}
  function updateAll(){
		var page=$("#page").val();
		var id=$("#id").val();
		var text=$("#actiontaken").val();
		var text1=$("#statuss").val();

			 $.ajax({
			     url:"ajaxActions.htm?page=ALTMUP&id="+id+"&actionTaken="+text+"&status="+text1,
			     data: $("#form1").serialize(),
			     success: function (data) {
			    	 
			    	  getDetails('A');
			   }
			});
			}
  function getSearhDateDetails(){
	  var locid=$("#location").val();
	  var divid=$("#failureType").val();
	  var fromdate=$("#fromdatetime").val();
	   var todate=$("#todatetime").val();
	   var page=$("#page").val();
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?status='A'&page="+page+"&locationID="+locid+"&failureType="+divid+"&fromdatetime="+fromdate+"&todatetime="+todate,	  
       dataType:"json",
	   success: function(data)
	   {
		   $("#bootstrap-table").dataTable().fnDestroy();
		   $("#bootstrap-table").empty();		  
		   var head_data="<thead><tr><th width='10%'>ID</th><th width='10%'>Location Name</th><th width='10%'>Equipment Name</th><th width='10%'>Failure Date </th><th width='10%'>Failure Type </th><th width='10%'>Escalated Date </th><th width='10%'>Escalated Level </th><th width='10%'>Group</th><th width='10%'>Action Taken</th><th width='10%'>Fixed Date </th><th width='10%'>Down Time</th><th width='10%'>Repeat Time</th><th width='10%'>Status</th><th width='10%'>Action</th></tr></thead>";
		   var head_data1="<thead><tr><th width='10%'>ID</th><th width='10%'>Location Name</th><th width='10%'>Equipment Name</th><th width='10%'>Failure Date </th><th width='10%'>Failure Type </th><th width='10%'>Escalated Date </th><th width='10%'>Escalated Level </th><th width='10%'>Group</th><th width='10%'>Action Taken</th><th width='10%'>Fixed Date </th><th width='10%'>Down Time</th><th width='10%'>Repeat Time</th><th width='10%'>Status</th></tr></thead><tbody>";
		    $(head_data).appendTo("#bootstrap-table");
		    $(head_data1).appendTo("#bootstrap-table");
		   
 		   $.each(data.details, function(i,data){
 			  var status;
			    var setting=false;
				if(data.Status=='O'){
					status='Open';
					
				}else if(data.Status=='L'){
					status='1st Level';
				}else if(data.Status=='M'){
					status='2nd Level';
				}else if(data.Status=='H'){
					status='3rd Level';
				}else if(data.Status=='C'){
					status='Closed';
					setting=true;
				}else{
					status=data.Status;
					setting=true;
				}
				
				if(setting){
					var msg_data="<tr><td id='tid' >"+data.Id+"</td><td id='tloc'>"+data.location+"</td><td id='tdiv'>"+data.devicename+"</td><td id='tdateoffail'>"+data.DOF+"</td><td id='tescaid'>"+data.EscalationType+"</td><td id='tmsd'>"+data.MSD+"</td><td id='tescalevel'>"+data.EscalationLevel+"</td><td id='tgroup'>"+data.GroupName+"</td><td id='taction'>"+data.ActionTaken+"</td><td id='tactiondate'>"+data.ActionDate+"</td><td id='tdiff'>"+data.diffTime+"</td><td id='trepeate'>"+data.RepeatTime+"</td><td id='tstatus'>"+status+"</td><td id='date'></td></tr>";
				}else
			   var msg_data="<tr><td id='tid' >"+data.Id+"</td><td id='tloc'>"+data.location+"</td><td id='tdiv'>"+data.devicename+"</td><td id='tdateoffail'>"+data.DOF+"</td><td id='tescaid'>"+data.EscalationType+"</td><td id='tmsd'>"+data.MSD+"</td><td id='tescalevel'>"+data.EscalationLevel+"</td><td id='tgroup'>"+data.GroupName+"</td><td id='taction'>"+data.ActionTaken+"</td><td id='tactiondate'>"+data.ActionDate+"</td><td id='tdiff'>"+data.diffTime+"</td><td id='trepeate'>"+data.RepeatTime+"</td><td id='tstatus'>"+status+"</td><td id='date'><a href='#' class='use-address fa fa-pencil-square-o' data-toggle='modal' data-target='#myModal'></a></td></tr>";
			   $(msg_data).appendTo("#bootstrap-table");
			  
		   });
		  $("#bootstrap-table").append("</tbody>");
	     	 $(".use-address").click(function() {
	              	
	 		        var $row = $(this).closest("tr");    // Find the row
	 		        
		     	    var $text = $row.find("#tid").text(); // Find the text
		     	    var $textloc = $row.find("#tloc").text(); // Find the text
		     	    var $textftype = $row.find("#tdiv").text(); // Find the text
		     	    var $textfdate = $row.find("#tdateoffail").text(); // Find the text
		     	  
		     	    
		     	    var $textesid = $row.find("#tescaid").text(); // Find the text
		     	    var $textlevel = $row.find("#tescalevel").text(); // Find the text
		     	    var $textgroup = $row.find("#tgroup").text(); // Find the text
		     	    
		     	    var $textactiontaken = $row.find("#taction").text(); // Find the text
		     	    var $textactiondate = $row.find("#tactiondate").text(); // Find the text
		     	    var $textdntm = $row.find("#tdiff").text(); // Find the text
		     	    var $textrpt = $row.find("#trepeate").text(); // Find the text
		     	    var $textsts = $row.find("#tstatus").text(); // Find the text

		     	   var status;
					if($textsts=='Open'){
						status='O';
					}else if($textsts=='1st Level'){
						status='L';
					}else if($textsts=='2nd Level'){
						status='M';
					}else if($textsts=='3rd Level'){
						status='H';
					}else if($textsts=='Closed'){
						status='C';
					}
		     	    // Let's test it out
		     	    $('#id').val($text);
		     	    $('#locationname').val($textloc);
		     	    $('#failuretype').val($textftype);
		     	    $('#failuredate').val($textfdate);
		     	    
		     	    $('#escalationid').val($textesid);
		     	    $('#escalationlevel').val($textlevel);
		     	    $('#group').val($textgroup);
		     	    
		     	    $('#actiontaken').val($textactiontaken);
		     	    $('#fixeddate').val($textactiondate);
		     	    $('#downtime').val($textdntm);
		     	    $('#repeattime').val($textrpt);
		     	    $('#statuss').val(status);
		     	 
		          });
			   
			        $(".use-confirm").click(function() {
		     	    var $row = $(this).closest("tr");    // Find the row
		     	    var $text = $row.find("#id").text(); // Find the text
		     	   
		     	   
		     	    // Let's test it out
		     	    $('#message').val($text);
		     	   });
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

