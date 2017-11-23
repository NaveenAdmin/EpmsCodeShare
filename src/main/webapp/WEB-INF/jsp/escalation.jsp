<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:28-12-15
Modified By	    :Prasad-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
								<a href="configactions.htm?page=CP5" class="left-breadcrumb-last-child">Escalation</a>
								</h5> <!-- /.dropdown-messages -->
							</li>
							<!-- /.dropdown -->
						</ul>
					</div>
				</div>
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-right"
					id="divEMSnavRight">
					<ul class="nav navbar-top-links navbar-right">

						<!-- /.dropdown -->
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#"> <i class="fa fa-user"> </i>
								User:<%=session.getAttribute("userName").toString() %>
						</a> <!-- /.dropdown-alerts --></li>
						<!-- /.dropdown -->

						<li class="dropdown"><a href="logout.htm"> <i
								class="fa fa-sign-out"> </i> Logout
						</a> <!-- /.dropdown-user --></li>
						<!-- /.dropdown -->
					</ul>
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
		</div>

		<div class="row ">
			<div class="col-lg-12">
				<h1 class="page-header-title">Escalation List</h1>
			</div>
		</div>
		<div id="role_error" onload="clearData();"><center><font color="red"><b>${error}</b></font></center></div>

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
												<input type="radio" name="optradio"
													onclick="getDetails('all')">All
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

							<label class="config-search-submit-style pull-right">
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

				</table>
				<br>
				<br>
				<br>
				<br>
			</div>
		</div>
		<!-- Add popup start  -->
		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="content-header">
						<h4>Add Escalation</h4>

						<a class="close" data-dismiss="modal"><img
							src="<c:url value="/resources/images/close.png" />" width="35"></a>

					</div>
					<div class="modal-body">
						<form name="form1"  class="form-inline" role="form">
							<div class="form-group hpclusers-popup-md">
								<div>
									<label for="name"> Escalation ID <b>*</b> </label> <input type="text"
										id="escalationID" onkeydown="enableTxt(this)" placeholder="Enter Escalation ID" maxlength="4"><span class="alert-message"></span>
									<label></label> <div id="alert" class="alert-message"></div>
								</div>
								<br>
								<div class="clear"> </div>
								<div>
									<label for="name"> Escalation Type <b>*</b> </label> <select
										class="form-control" id="escalationTypes"
										name="escalationTypes" onchange="enableTxt(this)">
										<option value="">Select Escalation type </option>
										<option value="Device">Device</option>
										<option value="Earthpit">Earthpit</option>

									</select><span class="alert-message"></span>
									<label></label> <div id="typealert" class="alert-message"></div>
								</div>
								<br>
								<div class="clear"> </div>
								<div>
									<label for="name">Escalation Level <b>*</b> </label> <select
										class="form-control" id="escalationLevels" onchange="enableTxt(this)">
										<option value="">Select Escalation Level</option>
										<option value="1">1st Level</option>
										<option value="2">2nd Level</option>
										<option value="3">3rd Level</option>
									</select><span class="alert-message"></span>
									<label></label> <div id="levesalert" class="alert-message"></div>
								</div>
								<br>
								<div class="clear"> </div>
								<div>
									<label for="name">Escalation Group <b>*</b> </label> <select
										class="form-control" id="escalationgroup"
										name="escalationgroup" onchange="enableTxt(this)">
										 <option value="">Select Escalation Group</option>
										<c:forEach var="group" items="${group}" varStatus="sno">
											<option value="${group.groupId}">${group.groupName}</option>
										</c:forEach>

									</select><span class="alert-message"></span>
									<label></label> <div id="groupalert" class="alert-message"></div>
								</div>
								<br>
								<div class="clear"> </div>
								<div>
									<label for="name"> SMS Text <b>*</b></label>
									<textarea maxlength="160" style="width: 100%;" text="smstext"
										class="form-control" rows="2" cols="50" id="sMSMessage"
										onkeydown="enableTxt(this)" placeholder="Enter SMS Text"></textarea><span class="alert-message"></span>
									 <p style="text-align: right; font-weight: normal;">(Max 160
										characters)</p>
									<label></label>	<div id="smsalert" class="alert-message"></div>
								</div>
								<div class="clear"> </div>
								<div>
									<label for="name"> Email Message <b>*</b></label>
									<textarea style="width: 100%;" text="emailtext"
										class="form-control" rows="2" cols="50" maxlength="500"
										id="eMailMessage" onkeydown="enableTxt(this)" placeholder="Enter Email Message"></textarea><span class="alert-message"></span>
									<label></label> <div id="emailalert" class="alert-message"></div>

								</div>
								<br>
								<div class="clear"> </div>
								<div>
									<label for="name"> Next Alert Duration <b>*</b></label> <input
										type="eastext" id="escalationInterval" onkeydown="enableTxt(this)" placeholder="Enter Alert Duration in minutes"><span class="alert-message"></span>
									<label></label> <div id="easalert" class="alert-message"></div>
								</div>
								<br>
								<div class="clear"> </div>
								<div>
									<label for="name"> Repeat Duration <b>*</b> </label> <input
										type="reptext" id="repeatInterval" onkeydown="enableTxt(this)" placeholder="Enter Repeat Duration in minutes"><span class="alert-message"></span>
									<div id="repalert" class="alert-message"></div>
								</div>
								<br> <input type="hidden" id="page" name="page" value="CP5">
								<input type="hidden" id="operation" name="operation" value="4">
								<div id="allfields" class="alert-message"></div>
								<button type="button" data-dismiss="modal" class="btn  btn-sm btn-primary popup-form-button-align" id="cancel" onclick="clearData()">Cancel</button>
								<button type="button"
									class="btn  btn-sm btn-primary popup-form-button-align"
									data-target="#myModal" onclick="return validate();">Save</button>

							</div>
						</form>

					</div>

				</div>
			</div>
		</div>


		<!-- ---Add pop up end  -->
		<!-- ---Edit pop up start  -->

		<div class="modal fade" id="myModalEdit" role="dialog">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="content-header">
						<h4>Edit Escalation</h4>
						<a class="close" data-dismiss="modal"><img
							src="<c:url value="/resources/images/close.png" />" width="35"></a>
					</div>
					<div class="modal-body">
						<form name="form1" class="form-inline" role="form">
							<div class="form-group hpclusers-popup-md">
								<div>
									<label for="name"> Escalation ID</label> <input type="text"
										id="escalationids" name="escalationIDs" disabled="disabled" >
								</div>
								<br>
								<div>
									<div>
										<label for="name"> Escalation type </label> <select
											class="form-control" id="deviceTypes" onchange="clearData()" disabled="disabled">
											<option value="">Select Escalation type </option>
											<option value="Device">Device</option>
											<option value="Earthpit">Earthpit</option>

										</select>
										<div id="uptypealert" class="alert-message"></div>
									</div>
									<br> <label for="name"> Escalation Level </label> <select
										class="form-control" id="escalationLevelss"
										name="escalationLevels" onchange="clearData()" disabled="disabled">
										<option value="">Select Escalation Level</option>
										<option value="1">1st Level</option>
										<option value="2">2nd Level</option>
										<option value="3">3rd Level</option>
									</select>
									<div id="uplevesalert" class="alert-message"></div>
								</div>
								<br>
								<div>
									<label for="name">Escalation Group</label> <select
										class="form-control" id="groupIds" name="groupId" onchange="clearData()" disabled="disabled">
										<option value="">Select Escalation Group</option>
										<c:forEach var="group" items="${group}" varStatus="sno">
										  <option value="${group.groupId}">${group.groupName}</option>
										</c:forEach>

									</select>
									<div id="upgroupalert" class="alert-message"></div>
								</div>
								<br>
								<div>
									<label for="name"> SMS Text <b>*</b></label>
									<textarea style="width: 100%;" class="form-control" rows="2"
										cols="50" maxlength="160" id="sMSMessages"
										name="sMSMessages" onkeydown="enableTxt(this)"></textarea><span class="alert-message"></span>
									<p style="text-align: right; font-weight: normal;">(Max 160
										characters)</p>
									<div id="upsmsalert" class="alert-message"></div>
								</div>
								<div class="clear"> </div>
								<div>
									<label for="name"> Email Message  <b>*</b></label>
									<textarea style="width: 100%;" class="form-control" rows="2"
										cols="50" maxlength="160" type="emailtext" id="eMailMessages"
										name="eMailMessages" onkeydown="enableTxt(this)"></textarea><span class="alert-message"> </span>
								<div id="upemailalert" class="alert-message"></div>

								</div>
								<br>
								<div class="clear"> </div>
								<div>
									<label for="name"> Next Alert Duration <b>*</b></label> <input
										type="text" id="escalationIntervals"
										name="escalationIntervals" onkeydown="enableTxt(this)"><span class="alert-message"></span>
								 <div id="upnextalert" class="alert-message"></div>
								</div>
								<br>
								<div class="clear"> </div>
								<div>
									<label for="name"> Repeat Duration <b>*</b></label> <input
										type="repeattext" id="repeatIntervals" name="repeatIntervals"
										onkeydown="enableTxt(this)"><span class="alert-message"></span>
									<div id="uprepeatalert" class="alert-message"></div>
								</div>
								<div class="clear"> </div>
								<br> <input type="hidden" id="uppage" name="page"
									value="CP5"> <input type="hidden" id="upoperation"
									name="operation" value="2">
									<button type="button" data-dismiss="modal" class="btn  btn-sm btn-primary popup-form-button-align" id="cancel" onclick="clearData()">Cancel</button>
								<button type="button"
									class="btn  btn-sm btn-primary popup-form-button-align"
									data-target="#myModalEdit" onclick="return upvalidate()">Update</button>

							</div>
						</form>

					</div>
				</div>
			</div>
		</div>

		<!-- ---Edit pop up end  -->
		<!-- ---Delete pop up start  -->

		<div class="modal fade" id="myModaldel" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="content-header">
						<a class="close" data-dismiss="modal"><img
							src="<c:url value="/resources/images/close.png"/>" width="35"></a>
					</div>
					<div class="modal-body popup-delete-align">
						<div class="form-group  popup-form-align del-popup-button">
							<label>Are you sure you want to delete </label>
							<form:form class="form-inline" action="configactions.htm"
								method="post" role="form">
								<input type="hidden" name="page" value="CP5">
								<input type="hidden" name="operation" value="3">
								<input type="hidden" id="message" name="escalationIDs">
								
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
		<!-- ---Delete pop up end  -->
	</div>
</div>

<script>
getDetails('A');
function validate()
{        	              
  if(EsacalationformValidation(document.getElementById("escalationID"),document.getElementById("alert"),document.getElementById("escalationTypes"),document.getElementById("typealert"),document.getElementById("escalationLevels"),document.getElementById("levesalert"),document.getElementById("escalationgroup"),document.getElementById("groupalert"),document.getElementById("sMSMessage"),document.getElementById("smsalert"),document.getElementById("eMailMessage"),document.getElementById("emailalert"),document.getElementById("escalationInterval"),document.getElementById("easalert"),document.getElementById("repeatInterval"),document.getElementById("repalert"))){
	  var escalationID=$("#escalationID").val();
	   $.ajax({
		     url:"validation.htm?page=CPUD5&escalationIDs="+escalationID,  
		   	 dataType:"json",
		   	 cache:false,
 	   	     async:false,
		     success: function (data) {
		    	 if(data.result=='valid'){
                         formSubmit();
	          
		    	 }else{
		    		$("<p>"+data.result+"</p>").appendTo("#alert");
		    	 }
		   }
		});

    	 }else{
	   return false;
  }
}
getDetails('A');
function upvalidate()
{           
	
  if(EsacalationformValidation(document.getElementById("escalationids"),document.getElementById("upalert"),document.getElementById("deviceTypes"),document.getElementById("uptypealert"),document.getElementById("escalationLevelss"),document.getElementById("uplevesalert"),document.getElementById("groupIds"),document.getElementById("upgroupalert"),document.getElementById("sMSMessages"),document.getElementById("upsmsalert"),document.getElementById("eMailMessages"),document.getElementById("upemailalert"),document.getElementById("escalationIntervals"),document.getElementById("upnextalert"),document.getElementById("repeatIntervals"),document.getElementById("uprepeatalert"))){
	  updateformSubmit();
	  location.reload();
		return true;
    	
  }else{
	   return false;
  }
}

function enableTxt(elem) {
	  $("#uprepeatalert").empty();
	  $("#alert").empty();
	  $("#typealert").empty();
	  $("#levesalert").empty();
	  $("#groupalert").empty();
	  $("#repalert").empty();
	  $("#easalert").empty();
	  $("#emailalert").empty();
	  $("#smsalert").empty();
    var id = $(elem).attr("id");
    $("#"+id).css('border-color', '');
}

function clearData(){

	  $("#alert").empty();
	  $("#typealert").empty();
	  $("#levesalert").empty();
	  $("#groupalert").empty();
	  $("#repalert").empty();
	  $("#easalert").empty();
	  $("#emailalert").empty();
	  $("#smsalert").empty();
	  
	  

		 

	  $("#uptypealert").empty();
	  $("#uplevesalert").empty();
	  $("#upgroupalert").empty();
	  $("#uprepeatalert").empty();
	  $("#upeasalert").empty();
	  $("#upemailalert").empty();
	  $("#upsmsalert").empty(); 
	  $("#upnextalert").empty();
	  $('#role_error').empty();

	}
$(document).ready( function () {
	 $(".use-address").click(function() {
		 
	     	    var $row = $(this).closest("tr");    // Find the row$row
	     	    var $text = $row.find("#id").text(); // Find the text
	     	    
	     	    var $textEscaid= $row.find("#id").text();
	     	    var $textEscatype = $row.find("#eastype").text(); // Find the text
	     	    var $textEscaLevel = $row.find("#easlevel").text();  // Find the text
	     	    var $textEsacalGroup=$row.find("#updategroup").text();
	     	    var $textEscaMsg = $row.find("#escaMsg").text(); // Find the text
	     	    var $textEscaMail = $row.find("#escaMail").text(); // Find the text
	     	    var $textEscaInter = $row.find("#escaInter").text(); // Find the text
	          	var $textEscaRepeat = $row.find("#escarepeat").text(); // Find the text
	          	
	     	   //alert($textEscaid);
	     	    // Let's test it out

	     	    $('#escalationids').val($textEscaid);
	     	    $('#deviceTypes').val($textEscatype);
	     	    $('#escalationLevelss').val($textEscaLevel);
	     	    $('#groupIds').val($textEsacalGroup);
	     	    $('#sMSMessages').val($textEscaMsg);
	     	    $('#eMailMessages').val($textEscaMail);
	     	    $('#escalationIntervals').val($textEscaInter);
	     	    $('#repeatIntervals').val($textEscaRepeat);
	     	 
	        });
		   
		   $(".use-confirm").click(function() {
	     	    var $row = $(this).closest("tr");    // Find the row
	     	    var $text = $row.find("#id").text(); // Find the text
	     	  
	     	    // Let's test it out
	     	  $('#message').val($text);
	     	    
	     	});
		   $('.close').click(function() {                   
	     		
	     		 $('#alert').empty();
	     		 $('#typealert').empty();
	     		 $('#levesalert').empty();
	     		 $('#groupalert').empty();
	     		 $('#repalert').empty();
	     		 $('#easalert').empty();
	     		 $('#emailalert').empty();
	     		 $('#smsalert').empty();
	     		 
	     		 $('#uptypealert').empty();
	     		 $('#uplevesalert').empty();
	     		 $('#upgroupalert').empty();
	     		 $('#upsmsalert').empty();
	     		 $('#upemailalert').empty();
	     		 $('#upnextalert').empty();
	     		 $('#uprepeatalert').empty();
	     		 
	     		$("#escalationID").css('border-color', '');
	     		$("#sMSMessage").css('border-color', '');
	     		$("#eMailMessage").css('border-color', '');
	     		$("#escalationInterval").css('border-color', '');
	     		$("#repeatInterval").css('border-color', '');
	     		$("#escalationLevels").css('border-color', '');
	     		$("#escalationgroup").css('border-color', '');
	     		$("#escalationTypes").css('border-color', '');
	     		
	     		 $('#escalationID').val("");
	     		 $('#sMSMessage').val("");
	     		 $('#eMailMessage').val("");
	     		 $('#escalationInterval').val("");
	     		 $('#repeatInterval').val("");
	     		 $('#escalationTypes').val("");
	     		$('#escalationLevels').val("");
	     		$('#escalationgroup').val("");
	     		 
	     		$('#role_error').empty();
	     		     	});
		   $('#cancel').click(function() {                   
				 $('#alert').empty();
	     		 $('#typealert').empty();
	     		 $('#levesalert').empty();
	     		 $('#groupalert').empty();
	     		 $('#repalert').empty();
	     		 $('#easalert').empty();
	     		 $('#emailalert').empty();
	     		 $('#smsalert').empty();
	     		 
	     		 $('#uptypealert').empty();
	     		 $('#uplevesalert').empty();
	     		 $('#upgroupalert').empty();
	     		 $('#upsmsalert').empty();
	     		 $('#upemailalert').empty();
	     		 $('#upnextalert').empty();
	     		 $('#uprepeatalert').empty();
	     		 
	     		$("#escalationID").css('border-color', '');
	     		$("#sMSMessage").css('border-color', '');
	     		$("#eMailMessage").css('border-color', '');
	     		$("#escalationInterval").css('border-color', '');
	     		$("#repeatInterval").css('border-color', '');
	     		$("#escalationLevels").css('border-color', '');
	     		$("#escalationgroup").css('border-color', '');
	     		$("#escalationTypes").css('border-color', '');
	     		
	     		 $('#escalationID').val("");
	     		 $('#sMSMessage').val("");
	     		 $('#eMailMessage').val("");
	     		 $('#escalationInterval').val("");
	     		 $('#repeatInterval').val("");
	     		 $('#escalationTypes').val("");
	     		$('#escalationLevels').val("");
	     		$('#escalationgroup').val("");
	     		 
	     		$('#role_error').empty();
	       		 
		     });
		   
       });
  getDetails('A');
 function formSubmit(){
	 var page=$("#page").val();
	 var operation=$("#operation").val();
	 
	 var escalationID=$("#escalationID").val();
	 var escalationTypes=$("#escalationTypes").val();
	 var escalationLevel=$("#escalationLevels").val();
	 var escalationgroup=$("#escalationgroup").val();
	 var sMSMessage=$("#sMSMessage").val();
	 var eMailMessage=$("#eMailMessage").val();
	 var escalationInterval=$("#escalationInterval").val();
	 var repeatInterval=$("#repeatInterval").val();
	
	 $.ajax({
	 	     url:"configactions.htm?page="+page+"&operation="+operation+"&escalationIDs="+escalationID+"&escalationTypes="+escalationTypes+"&escalationLevels="+escalationLevel+"&groupId="+escalationgroup+"&sMSMessages="+sMSMessage+"&eMailMessages="+eMailMessage+"&escalationIntervals="+escalationInterval+"&repeatIntervals="+repeatInterval+"&status=A",
	 	     data: $("#form1").serialize(),
	 	     cache:false,
 	   	     async:false,
	 	     success: function (data) {
	 	    	$(".modal-backdrop").remove();
            	 $("body").removeClass("modal-open");
            	 $("#myModal").modal('hide'); 
            	 getDetails('A');
            	 $('#alert').empty();
	     		 $('#typealert').empty();
	     		 $('#levesalert').empty();
	     		 $('#groupalert').empty();
	     		 $('#repalert').empty();
	     		 $('#easalert').empty();
	     		 $('#emailalert').empty();
	     		 $('#smsalert').empty();
	     		$('#escalationID').val("");
	     		 $('#sMSMessage').val("");
	     		 $('#eMailMessage').val("");
	     		 $('#escalationInterval').val("");
	     		 $('#repeatInterval').val("");
	     		 $('#escalationTypes').val("");
	     		$('#escalationLevels').val("");
	     		$('#escalationgroup').val("");
	 	    	 
	 	   }
	 	});  
	  
	 }
   function updateformSubmit(){
	 var page=$("#uppage").val();
	 var operation=$("#upoperation").val();
	 
	 var escalationID=$("#escalationids").val();
	 var escalationTypes=$("#deviceTypes").val();
	 var escalationLevel=$("#escalationLevels").val();
	 var escalationgroup=$("#groupIds").val();
	 var sMSMessage=$("#sMSMessages").val();
	 var eMailMessage=$("#eMailMessages").val();
	 var escalationInterval=$("#escalationIntervals").val();
	 var repeatInterval=$("#repeatIntervals").val();
	 
	
        $.ajax({
	 	     url:"configactions.htm?page="+page+"&operation="+operation+"&escalationIDs="+escalationID+"&escalationTypes="+escalationTypes+"&escalationLevels="+escalationLevel+"&groupId="+escalationgroup+"&sMSMessages="+sMSMessage+"&eMailMessages="+eMailMessage+"&escalationIntervals="+escalationInterval+"&repeatIntervals="+repeatInterval+"&status=A",
	 	     data: $("#form1").serialize(),
	 	     success: function (data) {
	 	    	 
	 	    	 getDetails('A');
	 	   }
	 	}); 
	 }

function getDetails(status){
	   
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=CP5&status="+status,
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
			   var head_data="<thead id=''><tr><th width='10%'>Escalation ID</th><th width='10%'>EscalationType</th><th width='10%'>Escalation Level</th><th width='10%'>Escalation Group</th><th width='20%'>SMS Message</th><th width='20%'>Email Message</th><th width='10%'>Escalation Period</th><th width='10%'>Repeat</th><th>Status</th></tr></thead>";
			   var head_data1="<thead id=''><tr><th width='10%'>Escalation ID</th><th width='10%'>EscalationType</th><th width='10%'>Escalation Level</th><th width='10%'>Escalation Group</th><th width='20%'>SMS Message</th><th width='20%'>Email Message</th><th width='10%'>Escalation Period</th><th width='10%'>Repeat</th><th>Status</th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-table");
			   $(head_data1).appendTo("#bootstrap-table");
			   
			   
			   $("#bootstrap-table").dataTable().fnDestroy();
			   $("#bootstrap-table").empty();
			   var head_data="<thead id=''><tr><th width='10%'>Escalation ID</th><th width='10%'>EscalationType</th><th width='10%'>Escalation Level</th><th width='10%'>Escalation Group</th><th width='20%'>SMS Message</th><th width='20%'>Email Message</th><th width='10%'>Escalation Period</th><th width='10%'>Repeat</th><th>Status</th></tr></thead>";
			   var head_data1="<thead id=''><tr><th width='10%'>Escalation ID</th><th width='10%'>EscalationType</th><th width='10%'>Escalation Level</th><th width='10%'>Escalation Group</th><th width='20%'>SMS Message</th><th width='20%'>Email Message</th><th width='10%'>Escalation Period</th><th width='10%'>Repeat</th><th>Status</th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-table");
			   $(head_data1).appendTo("#bootstrap-table");
			   
			   $.each(data.details, function(i,data){
				 
				  var status;
				 
				   if(data.status=='A'){
					   status="Active";
				  }else{
					  status="Inactive";
				  }
				  var msg_data="<tr><td id='id'>"+data.escalationID+"</td><td id='eastype'>"+data.escalationType+"</td><td id='easlevel'>"+data.escalationLevel+"</td><td id='updategroup'>"+data.groupId+"</td><td id='escaMsg'>"+data.sMSMessage+"</td><td id='escaMail'>"+data.eMailMessage+"</td><td id='escaInter'>"+data.escalationInterval+"</td><td id='escarepeat'>"+data.repeatInterval+"</td><td>"+status+"</td></tr>";
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
								                                                                          
		                          ]
		                });
			   
			   $(".use-address").click(function() {
		     	  
                    var $row = $(this).closest("tr");    // Find the row$row
		     	    var $text = $row.find("#id").text(); // Find the text
		     	    
		     	    var $textEscaid= $row.find("#id").text();
		     	    var $textEscatype = $row.find("#eastype").text(); // Find the text
		     	    var $textEscaLevel = $row.find("#easlevel").text(); // Find the text
		     	   var $textEsacalGroup=$row.find("#updategroup").text();
		     	    var $textEscaMsg = $row.find("#escaMsg").text(); // Find the text
		     	    var $textEscaMail = $row.find("#escaMail").text(); // Find the text
		     	    var $textEscaInter = $row.find("#escaInter").text(); // Find the text
		          	var $textEscaRepeat = $row.find("#escarepeat").text(); // Find the text
		           
		     	    // alert($textEsacalGroup);
		     	
		     	     $('#escalationids').val($textEscaid);
		     	    $('#deviceTypes').val($textEscatype);
		     	    $('#escalationLevelss').val($textEscaLevel);
		     	    $('#groupIds').val($textEsacalGroup);
		     	    $('#sMSMessages').val($textEscaMsg);
		     	    $('#eMailMessages').val($textEscaMail);
		     	    $('#escalationIntervals').val($textEscaInter);
		     	    $('#repeatIntervals').val($textEscaRepeat);
		     	    
		     	});
			  
			   
			   $(".use-confirm").click(function() {
		     	    var $row = $(this).closest("tr");    // Find the row
		     	    var $text = $row.find("#id").text(); // Find the text
		     	    
		     	    // Let's test it out
		     	   $('#message').val($text);
		     	    
		     	});
			   
		   }else{
			   $("#bootstrap-table").empty();
			   var head_data="<thead id=''><tr><th width='10%'>Escalation ID</th><th width='10%'>EscalationType</th><th width='10%'>Escalation Level</th><th width='10%'>Escalation Group</th><th width='20%'>SMS Message</th><th width='20%'>Email Message</th><th width='10%'>Escalation Period</th><th width='10%'>Repeat</th><th>Status</th><th>Actions</th></tr></thead>";
			   var head_data1="<thead id=''><tr><th width='10%'>Escalation ID</th><th width='10%'>EscalationType</th><th width='10%'>Escalation Level</th><th width='10%'>Escalation Group</th><th width='20%'>SMS Message</th><th width='20%'>Email Message</th><th width='10%'>Escalation Period</th><th width='10%'>Repeat</th><th>Status</th><th>Actions</th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-table");
			   $(head_data1).appendTo("#bootstrap-table");
			   
			   
			   $("#bootstrap-table").dataTable().fnDestroy();
			   $("#bootstrap-table").empty();
			   var head_data="<thead id=''><tr><th width='10%'>Escalation ID</th><th width='10%'>EscalationType</th><th width='10%'>Escalation Level</th><th width='10%'>Escalation Group</th><th width='20%'>SMS Message</th><th width='20%'>Email Message</th><th width='10%'>Escalation Period</th><th width='10%'>Repeat</th><th>Status</th><th>Actions</th></tr></thead>";
			   var head_data1="<thead id=''><tr><th width='10%'>Escalation ID</th><th width='10%'>EscalationType</th><th width='10%'>Escalation Level</th><th width='10%'>Escalation Group</th><th width='20%'>SMS Message</th><th width='20%'>Email Message</th><th width='10%'>Escalation Period</th><th width='10%'>Repeat</th><th>Status</th><th> </th></tr></thead>";
			   $(head_data).appendTo("#bootstrap-table");
			   $(head_data1).appendTo("#bootstrap-table");
			   
			   $.each(data.details, function(i,data){
				 
				  var status;
				 
				   if(data.status=='A'){
					   status="Active";
				  }else{
					  status="Inactive";
				  }
				  var msg_data="<tr><td id='id'>"+data.escalationID+"</td><td id='eastype'>"+data.escalationType+"</td><td id='easlevel'>"+data.escalationLevel+"</td><td id='updategroup'>"+data.groupId+"</td><td id='escaMsg'>"+data.sMSMessage+"</td><td id='escaMail'>"+data.eMailMessage+"</td><td id='escaInter'>"+data.escalationInterval+"</td><td id='escarepeat'>"+data.repeatInterval+"</td><td>"+status+"</td><td><a class='use-address fa fa-pencil' href='#'><span data-toggle='modal' data-target='#myModalEdit'   data-backdrop='static' data-keyboard='false'> Edit </span></a> |<a class='use-confirm fa fa-trash' href='#'>  <span class='delete'  data-toggle='modal' data-target='#myModaldel'   data-backdrop='static' data-keyboard='false'> Delete </span></a> </td></tr>";
				   $(msg_data).appendTo("#bootstrap-table");
		      });
			      
			   
			   $(".use-address").click(function() {
		     	  

		     	    
		     	    var $row = $(this).closest("tr");    // Find the row$row
		     	    var $text = $row.find("#id").text(); // Find the text
		     	    
		     	    var $textEscaid= $row.find("#id").text();
		     	    var $textEscatype = $row.find("#eastype").text(); // Find the text
		     	    var $textEscaLevel = $row.find("#easlevel").text(); // Find the text
		     	   var $textEsacalGroup=$row.find("#updategroup").text();
		     	    var $textEscaMsg = $row.find("#escaMsg").text(); // Find the text
		     	    var $textEscaMail = $row.find("#escaMail").text(); // Find the text
		     	    var $textEscaInter = $row.find("#escaInter").text(); // Find the text
		          	var $textEscaRepeat = $row.find("#escarepeat").text(); // Find the text
		           
		     	    // alert($textEsacalGroup);
		     	
		     	     $('#escalationids').val($textEscaid);
		     	    $('#deviceTypes').val($textEscatype);
		     	    $('#escalationLevelss').val($textEscaLevel);
		     	    $('#groupIds').val($textEsacalGroup);
		     	    $('#sMSMessages').val($textEscaMsg);
		     	    $('#eMailMessages').val($textEscaMail);
		     	    $('#escalationIntervals').val($textEscaInter);
		     	    $('#repeatIntervals').val($textEscaRepeat);
		     	    
		     	});
			  
			   
			   $(".use-confirm").click(function() {
		     	    var $row = $(this).closest("tr");    // Find the row
		     	    var $text = $row.find("#id").text(); // Find the text
		     	    
		     	    // Let's test it out
		     	   $('#message').val($text);
		     	    
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
	                                                                                                  
	                          ]
	                });
			    
		   }
		   
		  
	      }
		   
	      }
	   });
}

 </script>


