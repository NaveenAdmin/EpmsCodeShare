<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:24-12-15
Modified By	    :Prasad-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<script>
	var maxDevice;
	var deviceCount;
</script>
<div id="wrapper">
	<div id="page-wrapper">
		<div class="col-lg-12 col-md-6 content-top-header">
			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-left"
					id="divEMSnavLeft">
					<div class="row">
						<ul class="nav navbar-top-links navbar-left">
							<li class="dropdown">
								<h5>
									<a href="home.htm">Home</a>/ <a
										href="menu4.htm?url=configuration.htm">Configuration</a>/ <a
										href="configactions.htm?page=CP3&operation=0&status=A"
										class="left-breadcrumb-last-child">Device</a>
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
								data-toggle="dropdown" href="#"> <i class="fa fa-user">
								</i> User:<%=session.getAttribute("userName").toString()%>
							</a> <!-- /.dropdown-alerts --></li>
							<!-- /.dropdown -->
							<li class="dropdown"><a href="logout.htm"> <i
									class="fa fa-sign-out"> </i> Logout
							</a> <!-- /.dropdown-user --></li>
							<!-- /.dropdown -->
						</ul>
					</div>
				</div>
			</div>

			<div class="row">
				<div class="col-lg-12 col-md-12 location" id="divEMSnavCenter">
					<h2>
						<center>
							<%=session.getAttribute("location").toString()%>
						</center>
					</h2>
				</div>
			</div>

			<!-- /.navbar-top-links -->
		</div>
		<div class="row ">
			<div class="col-lg-12">
				<h1 class="page-header-title">Devices List</h1>
			</div>
		</div>
		<div class="">
			<div class="col-lg-12 col-md-12 search-config-align">
				<div class="" style="float: right; width: 100%;">
					<form class="form-inline" role="form">
						<fieldset class="scheduler-border">
							<legend class="scheduler-border">Search</legend>
							<div class="form-group status-input-style ">
								<div class="form-group">
									<div class="sam search-scheduler" style="">
										<!-- <label for="name"> Location <br> <select
										class="form-control select-box-style" name="location"
										id="location">
											<option value="0">Select Location</option>
									</select></label>  -->
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
													<input type="radio" name="optradio"
														onclick="getDetails('I')">Inactive
												</div>
											</fieldset>
										</label>
									</div>
								</div>
							</div>

							<label class=" config-search-submit-style pull-right">
								<button type="button" class="btn-hp btn-warning"
									data-toggle="modal" data-target="#myModal"
									data-backdrop="static" data-keyboard="false">
									<i class="fa fa-plus"> </i> Add
								</button>
							</label>

						</fieldset>
					</form>
				</div>
				<br>
				<table class="table table-hover table-striped" id="bootstrap-table">

				</table>
				<br> <br> <br> <br>
			</div>
		</div>

		<div class="modal fade" id="myModal" role="dialog">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="content-header">
						<h4>Add Devices</h4>
						<a class="close" data-dismiss="modal"> <img
							src="<c:url value="/resources/images/close.png" />" width="35"></a>

					</div>
					<div class="modal-body">
						<form:form name="form1" action="configactions.htm" method="post"
							class="form-inline" role="form">
							<div class="form-group hpclusers-popup-md">

								<div class="devices-test">
									<label for="name"> Device ID <b>*</b></label> <input
										type="text" id="device_ID" name="deviceId"
										onkeydown="enableTxt(this)"
										placeholder="Enter DeviceID Ex:D001" maxlength="4"> <span
										class="alert-message"></span> <label></label>
									<div id="alert" class="alert-message"></div>
								</div>
								<div class="clear"></div>
								<div class="devices-test">
									<label for="name"> Device Name <b>*</b></label> <input
										type="text" id="device_Name" name="deviceName"
										onkeydown="enableTxt(this)" placeholder="Enter Device Name"><span
										class="alert-message"></span> <label></label>
									<div id="devicealert" class="alert-message"></div>
								</div>
								<div class="clear"></div>
								<div class="devices-test">
									<label for="name"> IP Address <b>*</b></label> <input
										type="text" id="ip_Address" onkeydown="enableTxt(this)"
										placeholder="Enter IP Address" /><span class="alert-message"></span>
									<label></label>
									<div id="ipalert" class="alert-message"></div>
								</div>
								<div class="clear"></div>
								<div class="devices-test">
									<label for="name"> Location <b>*</b></label> <select
										class="form-control" id="deviceSeletc_Id"
										onchange="enableTxt(this)">
										<option value="">Select Location</option>

										<c:forEach var="location" items="${location}" varStatus="sno">
											<option value="${location.locationID}">${location.locationName}</option>
										</c:forEach>
									</select> <span class="alert-message"></span> <label></label>
									<div id="localert" class="alert-message"></div>
								</div>
								<div class="clear"></div>
								<div class="devices-test">
									<label for="name"> Earthpit Count <b>*</b></label> <input
										type="text" id="earthpit_Count" onkeydown="enableTxt(this)"
										placeholder="Enter Earthpit Count"><span
										class="alert-message"></span> <label></label>
									<div id="earthpitalert" class="alert-message"></div>
								</div>
								<div class="clear"></div>
								<div class="devices-test">
									<label for="name"> Time interval <b>*</b>
									</label> <input type="text" id="time_Interval" name="timeInterval"
										onkeydown="enableTxt(this)"
										placeholder="Enter Time interval in minutes"><span
										class="alert-message"></span> <label></label>
									<div id="timealert" class="alert-message"></div>
								</div>
								<div class="clear"></div>
								<div class="devices-test">
									<label for="name"> </label> <input type="button"
										value=" Change TimeInterval" onclick="setTiemIntervales()"><span
										class="alert-message"></span>

								</div>
								<div class="clear"></div>
								<div class="devices-test">
									<label for="name">Installed Date <b>*</b></label> <input
										type="text" id="installed_Date" placeholder="YYYY-MM-DD"
										onkeydown="enableTxt(this)"><span
										class="alert-message"></span> <label></label>
									<div id="installedalert" class="alert-message"></div>
								</div>
								<div class="clear"></div>

								<div class="devices-test">
									<label for="name"> Serial No <b>*</b></label> <input
										type="text" id="serial_No" onkeydown="enableTxt(this)"
										placeholder="Enter Serial No. EX:DX215K"><span
										class="alert-message"></span>
									<div id="snoalert" class="alert-message"></div>
								</div>
								<input type="hidden" id="page" name="page" value="CP3">
								<input type="hidden" id="operation" name="operation" value="4">
								<div class="clearfix"></div>
								<button type="button" data-dismiss="modal"
									class="btn  btn-sm btn-primary popup-form-button-align"
									id="cancel" onclick="clearData()">Cancel</button>
								<button type="button"
									class="btn  btn-sm btn-primary popup-form-button-align"
									data-target="#myModal" onclick="return validate();">
									Save</button>

								<!-- <button type="button" class="btn  btn-sm btn-primary popup-form-button-align" data-toggle="modal" data-target="#myModal">Save</button> -->
							</div>
							<div class="clear"></div>
						</form:form>

					</div>

				</div>
			</div>
		</div>
		<div class="modal fade" id="myModalEdit" role="dialog">
			<div class="modal-dialog modal-md">
				<div class="modal-content">
					<div class="content-header">
						<h4>Edit Device</h4>
						<a class="close" data-dismiss="modal"><img
							src="<c:url value="/resources/images/close.png" />" width="35"></a>
					</div>
					<div class="modal-body">
						<form:form name="form1" action="configactions.htm" method="post"
							class="form-inline" role="form">
							<div class="form-group hpclusers-popup-md">

								<div class="devices-test">
									<label for="name"> Device ID</label> <input type="text"
										name="deviceId" id="deviceid" disabled="disabled"
										onkeydown="enableTxt(this)">
									<div id="upalert" class="alert-message"></div>
								</div>
								<div class="clear"></div>

								<div class="devices-test">
									<label for="name"> Device Nmame <b>*</b></label> <input
										type="text" id="deviceName" name="deviceName"
										onkeydown="enableTxt(this)"><span
										class="alert-message"></span> <label></label>
									<div id="updevicealert" class="alert-message"></div>

								</div>
								<div class="clear"></div>

								<div class="devices-test">
									<label for="name"> IP Address <b>*</b></label> <input
										type="text" id="ipaddress" name="ipAddress"
										onkeydown="enableTxt(this)"><span
										class="alert-message"></span> <label></label>
									<div id="upipalert" class="alert-message"></div>
								</div>
								<div class="clear"></div>

								<div class="devices-test">
									<label for="name"> Location <b>*</b>
									</label> <select class="form-control" id="deviceloc" name="locationID"
										onchange="enableTxt(this)">
										<option value="">Select Location</option>
										<c:forEach var="location" items="${location}" varStatus="sno">
											<option value="${location.locationID}">${location.locationName}</option>
										</c:forEach>
									</select><span class="alert-message"></span> <label></label>
									<div id="uplocalert" class="alert-message"></div>
								</div>
								<div class="clear"></div>

								<div class="devices-test">
									<label for="name"> Earthpit Count <b>*</b></label> <input
										type="text" id="earthpitCount" name="earthPitCount"
										onkeydown="enableTxt(this)"><span
										class="alert-message"></span>
									<!-- 	<div id="earthpitalert" class="alert-message"></div> -->
									<label></label>
									<div id="upearthpitalert" class="alert-message"></div>
								</div>
								<div class="clear"></div>

								<div class="devices-test">
									<label for="name"> Time interval <b>*</b></label> <input
										type="text" id="timeinterval" name="timeInterval"
										onkeydown="enableTxt(this)"><span
										class="alert-message"></span>
									<!-- <input type="button" value=" Change TimeIntervel" > -->
									<!-- <div id="inralert" class="alert-message"></div> -->
									<label></label>
									<div id="uptimealert" class="alert-message"></div>
								</div>
								<div class="clear"></div>
								<div class="devices-test">
									<label for="name"> </label> <input type="button"
										value=" Change TimeInterval" onclick="upsetTiemIntervales()"><span
										class="alert-message"></span>

								</div>
								<div class="clear"></div>

								<div class="devices-test">
									<label for="name">Installed Date</label> <input type="timetext"
										id="installeddate" name="timeInterval" disabled="disabled">
									<div id="upinstalledalert" class="alert-message"></div>
								</div>
								<br>
								<div class="clear"></div>
								<div class="devices-test">
									<label for="name"> Serial No</label> <input type="snotext"
										id="serialno" disabled="disabled" onkeydown="enableTxt(this)">
									<div id="upsnoalert" class="alert-message"></div>
								</div>
								<div class="clear"></div>
								<input type="hidden" id="uppage" name="page" value="CP3">
								<input type="hidden" id="upoperation" name="operation" value="2">
								<button type="button" data-dismiss="modal"
									class="btn  btn-sm btn-primary popup-form-button-align"
									id="cancel" onclick="clearData()">Cancel</button>
								<button type="button"
									class="btn  btn-sm btn-primary popup-form-button-align up-load"
									data-target="#myModal" onclick="return upvalidate();">
									Update</button>
							</div>
						</form:form>

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
									type="hidden" name="page" value="CP3"> <input
									type="hidden" name="operation" value="3"> <input
									type="hidden" id="message" name="deviceId"> <input 
									type="hidden" id="locmessage" name="locationID">

								<button type="button" data-dismiss="modal"
									class="btn  btn-sm btn-primary popup-form-button-align">
									No</button>
								<button type="submit"
									class="btn  btn-sm btn-primary popup-form-button-align">
									Yes</button>

							</div>
						</form:form>
					</div>
				</div>
			</div>
		</div>

		<div class="modal fade" id="myModalPing" role="dialog">
			<div class="modal-dialog modal-sm">
				<div class="modal-content">
					<div class="content-header">
						<h4>Response From Device</h4>
						<a class="close" data-dismiss="modal"><img
							src="<c:url value="/resources/images/close.png" />" width="35"></a>
					</div>
					<div class="modal-body">


						<div class="form-group  popup-form-align del-popup-button">
							<div id="ping" />
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clearfix"></div>

	<script>
		function setTiemIntervales() {

			var timeInterval = $("#time_Interval").val();
			var ip_Address = $("#ip_Address").val();

			if (timeInterval != "" && ip_Address != "") {

				$.ajax({
					url : "validation.htm?page=CPDI&ipAddress=" + ip_Address
							+ "&timeInterval=" + timeInterval,
					dataType : "json",
					async : false,
					cache : false,
					success : function(data) {

					}
				});

			} else {
				alert("please enter ipaddress and time interval");
			}

		}
		function upsetTiemIntervales() {

			var timeInterval = $("#timeinterval").val();
			var ip_Address = $("#ipaddress").val();

			if (timeInterval != "" && ip_Address != "") {

				$.ajax({
					url : "validation.htm?page=CPDI&ipAddress=" + ip_Address
							+ "&timeInterval=" + timeInterval,
					dataType : "json",
					async : false,
					cache : false,
					success : function(data) {

					}
				});

			} else {
				alert("please enter ipaddress and time interval");
			}

		}
		function Deviceping() {

			var deviceId = $("#device_ID").val();

			$.ajax({
				url : "ajaxActions.htm?page=" + DevicePing + "&deviceId="+ deviceId,
				data : $("#form").serialize(),
				async : false,
				cache : false,
				success : function(data) {

				}
			});
		}

		function validate() {
			if (DeviceformValidation(document.getElementById("device_ID"),
					document.getElementById("alert"), document
							.getElementById("device_Name"), document
							.getElementById("devicealert"), document
							.getElementById("ip_Address"), document
							.getElementById("ipalert"), document
							.getElementById("deviceSeletc_Id"), document
							.getElementById("localert"), document
							.getElementById("earthpit_Count"), document
							.getElementById("earthpitalert"), document
							.getElementById("time_Interval"), document
							.getElementById("timealert"), document
							.getElementById("installed_Date"), document
							.getElementById("installedalert"), document
							.getElementById("serial_No"), document
							.getElementById("snoalert"))) {
				var device_ID = $('#device_ID').val();
				$.ajax({
					url : "validation.htm?page=CPUD3&deviceId=" + device_ID,
					dataType : "json",
					cache : false,
					async : false,
					success : function(data) {
						if (data.result == 'valid') {
							formSubmit();
						} else {
							$("<p>" + data.result + "</p>").appendTo("#alert");
						}
					}
				});

			} else {
				return false;
			}

		}
		function upvalidate() {
			if (UpDeviceformValidation(document.getElementById("deviceid"),
					document.getElementById("upalert"), document
							.getElementById("deviceName"), document
							.getElementById("updevicealert"), document
							.getElementById("ipaddress"), document
							.getElementById("upipalert"), document
							.getElementById("deviceloc"), document
							.getElementById("uplocalert"), document
							.getElementById("earthpitCount"), document
							.getElementById("upearthpitalert"), document
							.getElementById("timeinterval"), document
							.getElementById("uptimealert"), document
							.getElementById("installeddate"), document
							.getElementById("upinstalledalert"), document
							.getElementById("serialno"), document
							.getElementById("upsnoalert"))) {

				updateformSubmit();

				location.reload();
				document.form1.deviceid.value = "";
				document.form1.deviceName.value = "";
				document.form1.ipaddress.value = "";
				document.form1.deviceloc.value = '';
				document.form1.earthpitCount.value = "";
				document.form1.timeinterval.value = "";
				document.form1.installeddate.value = "";
				document.form1.serialno.value = "";
				return true;

			} else {
				return false;
			}

		}
		function enableTxt(elem) {
			$("#alert").empty();
			$("#snoalert").empty();
			$("#localert").empty();
			$("#devicealert").empty();
			$("#ipalert").empty();
			$("#earthpitalert").empty();
			$("#timealert").empty();
			$("#installedalert").empty();
			$("#snoalert").empty();

			var id = $(elem).attr("id");
			$("#" + id).css('border-color', '');
		}
		function clearData() {
			$("#alert").empty();
			$("#snoalert").empty();
			$("#localert").empty();
			$("#devicealert").empty();
			$("#ipalert").empty();
			$("#earthpitalert").empty();
			$("#timealert").empty();
			$("#installedalert").empty();
			$("#snoalert").empty();

			$("#upalert").empty();
			$("#upearthpitalert").empty();
			$("#uplocalert").val("");
			$("#updevicealert").empty();
			$("#upipalert").empty();
			$("#earthpitalert").empty();
			$("#uptimealert").empty();
			$("#upinstalledalert").empty();
			$("#upsnoalert").empty();
			$("#uplocalert").empty();

			$('#role_error').empty();

		}

		$(document).ready(
				function() {
					$(".use-address").click(function() {

						var $row = $(this).closest("tr"); // Find the row
						var $text = $row.find("#id").text(); // Find the text
						var $textdevname = $row.find("#devname").text(); // Find the text
						var $textipadd = $row.find("#ipadd").text(); // Find the text
						var $textlocid = $row.find("#locid").text(); // Find the text
						var $textearval = $row.find("#earval").text(); // Find the text
						var $texttimeint = $row.find("#timeint").text(); // Find the text
						var $textinstdate = $row.find("#instdate").text(); // Find the text
						var $textdevsno = $row.find("#devsno").text(); // Find thefill all text

						// Let's test it out
						$('#deviceid').val($text);
						$('#deviceName').val($textdevname);
						$('#ipaddress').val($textipadd);
						$('#deviceloc').val($textlocid);
						$('#earthpitCount').val($textearval);
						$('#timeinterval').val($texttimeint);
						$('#installeddate').val($textinstdate);
						$('#serialno').val($textdevsno);
					});

					$(".use-confirm").click(function() {
						var $row = $(this).closest("tr"); // Find the row
						var $text = $row.find("#id").text(); // Find the text
						// Let's test it out
						$('#message').val($text);

					});

					$(".ping").click(
							function() {
								var $row = $(this).closest("tr"); // Find the row
								var $ipadd = $row.find("#ipadd").text(); // Find the text
								// Let's test it out
								$("#ping").empty();
								$.ajax({
									url : "validation.htm?page=CPD&ipAddress="
											+ $ipadd,
									dataType : "json",
									async : false,
									success : function(data) {
										if (data.color == 'red') {
											$(
													"<p><font color='red'>"
															+ data.result
															+ "</font></p>")
													.appendTo("#ping");
										} else {
											$("<p>" + data.result + "</p>")
													.appendTo("#ping");
										}

									}
								});

							});
				});

		function formSubmit() {

			var page = $("#page").val();
			var operation = $("#operation").val();

			var device_ID = $('#device_ID').val();
			var device_Name = $('#device_Name').val();
			var ip_Address = $('#ip_Address').val();
			var deviceSeletc_Id = $('#deviceSeletc_Id').val();
			var earthpit_Count = $('#earthpit_Count').val();
			var time_Interval = $('#time_Interval').val();
			var installed_Date = $('#installed_Date').val();
			var serial_No = $('#serial_No').val();
            
			if (maxDevice >= deviceCount) {

				$.ajax({
					url : "configactions.htm?page=" + page + "&operation="
							+ operation + "&deviceId=" + device_ID
							+ "&deviceName=" + device_Name + "&ipAddress="
							+ ip_Address + "&locationID=" + deviceSeletc_Id
							+ "&earthPitCount=" + earthpit_Count
							+ "&timeInterval=" + time_Interval
							+ "&installedDate=" + installed_Date
							+ "&deviceSno=" + serial_No,
					data : $("#form").serialize(),
					cache : false,
					async : false,
					success : function(data) {
						$(".modal-backdrop").remove();
						$("body").removeClass("modal-open");
						$("#myModal").modal('hide');
						getDetails('A');
						$('#device_ID').val("");
						$('#device_Name').val("");
						$('#ip_Address').val("");
						$('#deviceSeletc_Id').val("");
						$('#earthpit_Count').val("");
						$('#time_Interval').val("");
						$('#installed_Date').val("");
						$('#serial_No').val("");
						$('#deviceloc').val("");
						$('#uplocalert').empty();
						$('#uplocalert').val("");
						$('#updevicealert').empty();
						$('#uptimealert').empty();
						$('#snoalert').empty();
						$('#role_error').empty();
						$('#upipalert').empty();
						$('#earthpitCount').empty();

					}
				});

			} else {

				$("<p>Maximum Device Count Reached..</p>")
						.appendTo("#snoalert");

			}
		}

		function updateformSubmit() {

			var page = $("#uppage").val();
			var operation = $("#upoperation").val();

			var device_ID = $('#deviceid').val();
			var device_Name = $('#deviceName').val();
			var ip_Address = $('#ipaddress').val();
			var deviceSeletc_Id = $('#deviceloc').val();
			var earthpit_Count = $('#earthpitCount').val();
			var time_Interval = $('#timeinterval').val();
			var serial_No = $('#serialno').val();

			$.ajax({
				url : "configactions.htm?page=" + page + "&operation="
						+ operation + "&deviceId=" + device_ID + "&deviceName="
						+ device_Name + "&ipAddress=" + ip_Address
						+ "&locationID=" + deviceSeletc_Id + "&earthPitCount="
						+ earthpit_Count + "&timeInterval=" + time_Interval
						+ "&deviceSno=" + serial_No,
				data : $("#form").serialize(),
				cache : false,
				async : false,
				success : function(data) {
					getDetails('A');
				}
			});
		}

		function getDetails(status) {

			$
					.ajax({
						type : "GET",
						url : "ajaxActions.htm?page=CP3&status=" + status,
						dataType : "json",
						cache : false,
						success : function(data) {
							maxDevice = data.maxdevice;
							deviceCount = data.devicecount;

							if (data == "") {
								$("#bootstrap-table").empty();
								var head_data = "<p><h3><center><font size='5' color='red'> No Data Available</font><h3></center></p>";
								$(head_data).appendTo("#bootstrap-table");

							} else {
								if (status == 'I' || status == 'All') {

									$("#location").empty();
									var option = $(this).find('category')
											.text();
									$('#location').append(
											'<option>Select Location</option>');
									$("#bootstrap-table").empty();

									$("#bootstrap-table").empty();
									var head_data = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='10%'>Status</th></tr></thead>";
									$(head_data).appendTo("#bootstrap-table");
									var head_data1 = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='10%'>Status</th></tr></thead>";
									$(head_data1).appendTo("#bootstrap-table");

									$("#bootstrap-table").dataTable()
											.fnDestroy();
									$("#bootstrap-table").empty();
									var head_data = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='10%'>Status</th></tr></thead>";
									$(head_data).appendTo("#bootstrap-table");
									var head_data1 = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='10%'>Status</th></tr></thead>";
									$(head_data1).appendTo("#bootstrap-table");

									$
											.each(
													data.details,
													function(i, data) {
														var status;
														if (data.devStatus == 'A') {
															status = "Active";
														} else {
															status = "Inactive";
														}
														var msg_data = "<tr><td id='locid'>"
															    + data.devLoc
															    + "</td><td id=''>"
															    + data.devLocname
															    + "</td><td id='id'>"
																+ data.devId
																+ "</td><td id='devname'>"
																+ data.devname
																+ "</td><td id='ipadd'>"
																+ data.devIp
																+ "</td><td id='earval'>"
																+ data.devEarthPit
																+ "</td><td id='timeint'>"
																+ data.devInter
																+ "</td><td id='instdate'>"
																+ data.devInstall
																+ "</td><td id='devsno'>"
																+ data.devSno
																+ "</td><td>"
																+ status
																+ "</td></tr>";
														$(msg_data)
																.appendTo(
																		"#bootstrap-table");

														var optionsAsString = "<option value='" + data.devLocname + "'>"
																+ data.devLocname
																+ "</option>";
														$(
																'select[name="location"]')
																.append(
																		optionsAsString);

														var code = {};
														$(
																"select[name='location'] > option")
																.each(
																		function() {
																			if (code[this.text]) {
																				$(
																						this)
																						.remove();
																			} else {
																				code[this.text] = data.devLocname;
																			}
																		});

													});
									$("#bootstrap-table").append("</tbody>");

									$('#bootstrap-table').dataTable()
											.columnFilter({
												sPlaceHolder : "head:after",
												aoColumns : [ {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												},

												]
											});

									$(".use-address")
											.click(
													function() {

														var $row = $(this)
																.closest("tr"); // Find the row
														var $text = $row.find(
																"#id").text(); // Find the text
														var $textdevname = $row
																.find(
																		"#devname")
																.text(); // Find the text
														var $textipadd = $row
																.find("#ipadd")
																.text(); // Find the text
														var $textlocid = $row
																.find("#locid")
																.text(); // Find the text
														var $textearval = $row
																.find("#earval")
																.text(); // Find the text
														var $texttimeint = $row
																.find(
																		"#timeint")
																.text(); // Find the text
														var $textinstdate = $row
																.find(
																		"#instdate")
																.text(); // Find the text
														var $textdevsno = $row
																.find("#devsno")
																.text(); // Find thefill all text

														// Let's test it out
														$('#deviceid').val(
																$text);
														$('#deviceName').val(
																$textdevname);
														$('#ipaddress').val(
																$textipadd);
														$('#deviceloc').val(
																$textlocid);
														$('#earthpitCount')
																.val(
																		$textearval);
														$('#timeinterval').val(
																$texttimeint);
														$('#installeddate')
																.val(
																		$textinstdate);
														$('#serialno').val(
																$textdevsno);
													});

									$(".use-confirm").click(function() {
										var $row = $(this).closest("tr"); // Find the row
										var $text = $row.find("#id").text(); // Find the text
										// Let's test it out
										$('#message').val($text);

									});
									$(".ping")
											.click(
													function() {
														var $row = $(this)
																.closest("tr"); // Find the row
														var $ipadd = $row.find(
																"#ipadd")
																.text(); // Find the text
														// Let's test it out
														$("#ping").empty();
														$
																.ajax({
																	url : "validation.htm?page=CPD&ipAddress="
																			+ $ipadd,
																	dataType : "json",
																	async : false,
																	success : function(
																			data) {
																		if (data.color == 'red') {
																			$(
																					"<p><font color='red'>"
																							+ data.result
																							+ "</font></p>")
																					.appendTo(
																							"#ping");
																		} else {
																			$(
																					"<p>"
																							+ data.result
																							+ "</p>")
																					.appendTo(
																							"#ping");
																		}
																	}
																});

													});
									//refresh for alert messages and data
									$('.close').click(
											function() {

												$('#alert').empty();
												$('#devicealert').empty();
												$('#ipalert').empty();
												$('#localert').empty();
												$('#earthpitalert').empty();
												$('#timealert').empty();
												$('#upearthpitalert').empty();
												$('#installedalert').empty();
												$('#devicealert').empty();
												$('#installedalert').empty();
												$('#earalert').empty();
												$('#inralert').empty();

												$('#device_ID').css(
														'border-color', '');
												$('#device_Name').css(
														'border-color', '');
												$('#ip_Address').css(
														'border-color', '');
												$('#deviceSeletc_Id').css(
														'border-color', '');
												$('#earthpit_Count').css(
														'border-color', '');
												$('#time_Interval').css(
														'border-color', '');
												$('#installed_Date').css(
														'border-color', '');
												$('#serial_No').css(
														'border-color', '');
												$('#deviceloc').css(
														'border-color', '');
												$('#uplocalert').css(
														'border-color', '');
												$('#uplocalert').css(
														'border-color', '');
												$('#updevicealert').css(
														'border-color', '');
												$('#uptimealert').css(
														'border-color', '');
												$('#snoalert').css(
														'border-color', '');
												$('#role_error').css(
														'border-color', '');
												$('#upipalert').css(
														'border-color', '');

												$('#device_ID').val("");
												$('#device_Name').val("");
												$('#ip_Address').val("");
												$('#deviceSeletc_Id').val("");
												$('#earthpit_Count').val("");
												$('#time_Interval').val("");
												$('#installed_Date').val("");
												$('#serial_No').val("");
												$('#deviceloc').val("");
												$('#uplocalert').empty();
												$('#uplocalert').val("");
												$('#updevicealert').empty();
												$('#uptimealert').empty();
												$('#snoalert').empty();
												$('#role_error').empty();
												$('#upipalert').empty();
											});
									$('#cancel').click(
											function() {

												$('#alert').empty();
												$('#devicealert').empty();
												$('#ipalert').empty();
												$('#localert').empty();
												$('#earthpitalert').empty();
												$('#timealert').empty();
												$('#upearthpitalert').empty();
												$('#installedalert').empty();
												$('#devicealert').empty();
												$('#installedalert').empty();
												$('#earalert').empty();
												$('#inralert').empty();

												$('#device_ID').css(
														'border-color', '');
												$('#device_Name').css(
														'border-color', '');
												$('#ip_Address').css(
														'border-color', '');
												$('#deviceSeletc_Id').css(
														'border-color', '');
												$('#earthpit_Count').css(
														'border-color', '');
												$('#time_Interval').css(
														'border-color', '');
												$('#installed_Date').css(
														'border-color', '');
												$('#serial_No').css(
														'border-color', '');
												$('#deviceloc').css(
														'border-color', '');
												$('#uplocalert').css(
														'border-color', '');
												$('#uplocalert').css(
														'border-color', '');
												$('#updevicealert').css(
														'border-color', '');
												$('#uptimealert').css(
														'border-color', '');
												$('#snoalert').css(
														'border-color', '');
												$('#role_error').css(
														'border-color', '');
												$('#upipalert').css(
														'border-color', '');

												$('#earthpitCount').css(
														'border-color', '');
												$('#device_ID').val("");
												$('#device_Name').val("");
												$('#ip_Address').val("");
												$('#deviceSeletc_Id').val("");
												$('#earthpit_Count').val("");
												$('#time_Interval').val("");
												$('#installed_Date').val("");
												$('#serial_No').val("");
												$('#deviceloc').val("");
												$('#uplocalert').empty();
												$('#uplocalert').val("");
												$('#updevicealert').empty();
												$('#uptimealert').empty();
												$('#snoalert').empty();
												$('#role_error').empty();
												$('#upipalert').empty();
												$('#earthpitCount').empty();
											});

								} else {

									//maxDevice=data.maxdevice;
									//deviceCount=data.devicecount;
									$("#location").empty();
									var option = $(this).find('category')
											.text();
									$('#location').append(
											'<option>Select Location</option>');
									$("#bootstrap-table").empty();

									$("#bootstrap-table").empty();
									var head_data = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='10%'>Status</th><th width='20%'>Actions</th></tr></thead>";
									$(head_data).appendTo("#bootstrap-table");
									var head_data1 = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='10%'>Status</th><th width='20%'>Actions</th></tr></thead>";
									$(head_data1).appendTo("#bootstrap-table");

									$("#bootstrap-table").dataTable()
											.fnDestroy();
									$("#bootstrap-table").empty();
									var head_data = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='10%'>Status</th><th width='20%'>Actions</th></tr></thead>";
									$(head_data).appendTo("#bootstrap-table");
									var head_data1 = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='10%'>Status</th><th width='20%'></th></tr></thead>";
									$(head_data1).appendTo("#bootstrap-table");

									$
											.each(
													data.details,
													function(i, data) {
														var status;
														if (data.devStatus == 'A') {
															status = "Active";
														} else {
															status = "Inactive";
														}
														var msg_data = "<tr><td id='locid'>"
															    + data.devLoc
															    + "</td><td id=''>"
															    + data.devLocname
															    + "</td><td id='id'>"
																+ data.devId
																+ "</td><td id='devname'>"
																+ data.devname
																+ "</td><td id='ipadd'>"
																+ data.devIp
																+ "</td><td id='earval'>"
																+ data.devEarthPit
																+ "</td><td id='timeint'>"
																+ data.devInter
																+ "</td><td id='instdate'>"
																+ data.devInstall
																+ "</td><td id='devsno'>"
																+ data.devSno
																+ "</td><td>"
																+ status
																+ "</td><td><a class='use-address fa  fa-pencil' href='#' data-toggle='modal' data-target='#myModalEdit'   data-backdrop='static' data-keyboard='false'> Edit </a> | <a class='use-confirm fa fa-trash' href='#' data-toggle='modal' data-target='#myModaldel'   data-backdrop='static' data-keyboard='false' > Delete</a> | <a href='#' data-target='#myModalPing' data-toggle='modal' class='ping' > Ping</a></td></tr>";
														$(msg_data)
																.appendTo(
																		"#bootstrap-table");

														var optionsAsString = "<option value='" + data.devLocname + "'>"
																+ data.devLocname
																+ "</option>";
														$(
																'select[name="location"]')
																.append(
																		optionsAsString);

														var code = {};
														$(
																"select[name='location'] > option")
																.each(
																		function() {
																			if (code[this.text]) {
																				$(
																						this)
																						.remove();
																			} else {
																				code[this.text] = data.devLocname;
																			}
																		});

													});

									$(".use-address")
											.click(
													function() {

														var $row = $(this)
																.closest("tr"); // Find the row
														var $text = $row.find(
																"#id").text(); // Find the text
														var $textdevname = $row
																.find(
																		"#devname")
																.text(); // Find the text
														var $textipadd = $row
																.find("#ipadd")
																.text(); // Find the text
														var $textlocid = $row
																.find("#locid")
																.text(); // Find the text
														var $textearval = $row
																.find("#earval")
																.text(); // Find the text
														var $texttimeint = $row
																.find(
																		"#timeint")
																.text(); // Find the text
														var $textinstdate = $row
																.find(
																		"#instdate")
																.text(); // Find the text
														var $textdevsno = $row
																.find("#devsno")
																.text(); // Find thefill all text

														// Let's test it out
														$('#deviceid').val(
																$text);
														$('#deviceName').val(
																$textdevname);
														$('#ipaddress').val(
																$textipadd);
														$('#deviceloc').val(
																$textlocid);
														$('#earthpitCount')
																.val(
																		$textearval);
														$('#timeinterval').val(
																$texttimeint);
														$('#installeddate')
																.val(
																		$textinstdate);
														$('#serialno').val(
																$textdevsno);
													});

									$(".use-confirm").click(function() {
										var $row = $(this).closest("tr"); // Find the row
										var $text = $row.find("#id").text();
										var $textlocid = $row.find("#locid").text();// Find the text
										
										$('#message').val($text);
										$('#locmessage').val($textlocid);

									});
									$(".ping")
											.click(
													function() {
														var $row = $(this)
																.closest("tr"); // Find the row
														var $ipadd = $row.find(
																"#ipadd")
																.text(); // Find the text
														// Let's test it out
														$("#ping").empty();
														$
																.ajax({
																	url : "validation.htm?page=CPD&ipAddress="
																			+ $ipadd,
																	dataType : "json",
																	async : false,
																	success : function(
																			data) {
																		if (data.color == 'red') {
																			$(
																					"<p><font color='red'>"
																							+ data.result
																							+ "</font></p>")
																					.appendTo(
																							"#ping");
																		} else {
																			$(
																					"<p><font color='blue'>"
																							+ data.result
																							+ "</font></p>")
																					.appendTo(
																							"#ping");
																		}
																	}
																});
													});
									$("#bootstrap-table").append("</tbody>");

									$('#bootstrap-table').dataTable()
											.columnFilter({
												sPlaceHolder : "head:after",
												aoColumns : [ {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												}, {
													type : "text"
												},

												]
											});

									$('.close').click(
											function() {

												$('#alert').empty();
												$('#devicealert').empty();
												$('#ipalert').empty();
												$('#localert').empty();
												$('#earthpitalert').empty();
												$('#timealert').empty();
												$('#installedalert').empty();

												$('#devicealert').empty();
												$('#installedalert').empty();
												$('#earalert').empty();
												$('#inralert').empty();

												$('#device_ID').css(
														'border-color', '');
												$('#device_Name').css(
														'border-color', '');
												$('#ip_Address').css(
														'border-color', '');
												$('#deviceSeletc_Id').css(
														'border-color', '');
												$('#earthpit_Count').css(
														'border-color', '');
												$('#time_Interval').css(
														'border-color', '');
												$('#installed_Date').css(
														'border-color', '');
												$('#serial_No').css(
														'border-color', '');
												$('#deviceloc').css(
														'border-color', '');
												$('#uplocalert').css(
														'border-color', '');
												$('#uplocalert').css(
														'border-color', '');
												$('#updevicealert').css(
														'border-color', '');
												$('#uptimealert').css(
														'border-color', '');
												$('#snoalert').css(
														'border-color', '');
												$('#role_error').css(
														'border-color', '');
												$('#upipalert').css(
														'border-color', '');

												$('#device_ID').val("");
												$('#device_Name').val("");
												$('#ip_Address').val("");
												$('#deviceSeletc_Id').val("");
												$('#earthpit_Count').val("");
												$('#time_Interval').val("");
												$('#installed_Date').val("");
												$('#serial_No').val("");
												$('#uplocalert').empty();
												$('#updevicealert').empty();
												$('#uptimealert').empty();
												$('#snoalert').empty();
												$('#upipalert').empty();
												$('#upearthpitalert').empty();
											});
									$('#cancel').click(
											function() {

												$('#alert').empty();
												$('#devicealert').empty();
												$('#ipalert').empty();
												$('#localert').empty();
												$('#earthpitalert').empty();
												$('#timealert').empty();
												$('#upearthpitalert').empty();
												$('#installedalert').empty();
												$('#devicealert').empty();
												$('#installedalert').empty();
												$('#earalert').empty();
												$('#inralert').empty();

												$('#device_ID').css(
														'border-color', '');
												$('#device_Name').css(
														'border-color', '');
												$('#ip_Address').css(
														'border-color', '');
												$('#deviceSeletc_Id').css(
														'border-color', '');
												$('#earthpit_Count').css(
														'border-color', '');
												$('#time_Interval').css(
														'border-color', '');
												$('#installed_Date').css(
														'border-color', '');
												$('#serial_No').css(
														'border-color', '');
												$('#deviceloc').css(
														'border-color', '');
												$('#uplocalert').css(
														'border-color', '');
												$('#uplocalert').css(
														'border-color', '');
												$('#updevicealert').css(
														'border-color', '');
												$('#uptimealert').css(
														'border-color', '');
												$('#snoalert').css(
														'border-color', '');
												$('#role_error').css(
														'border-color', '');
												$('#upipalert').css(
														'border-color', '');

												//$('#earthpitCount').css('border-color', '');
												$('#device_ID').val("");
												$('#device_Name').val("");
												$('#ip_Address').val("");
												$('#deviceSeletc_Id').val("");
												$('#earthpit_Count').val("");
												$('#time_Interval').val("");
												$('#installed_Date').val("");
												$('#serial_No').val("");
												$('#deviceloc').val("");
												$('#uplocalert').empty();
												$('#uplocalert').val("");
												$('#updevicealert').empty();
												$('#uptimealert').empty();
												$('#snoalert').empty();
												$('#role_error').empty();
												$('#upipalert').empty();
												$('#earthpitCount').empty();
											});
								}
							}
						}
					});

		}
		getDetails('A');
		function getLocationDetails() {
			var locid = $("#location").val();

			$
					.ajax({
						type : "GET",
						url : "ajaxActions.htm?page=CP3&locationID=" + locid
								+ "&status=devLocname",
						dataType : "json",
						async : false,
						cache : false,
						success : function(data) {

							$("#bootstrap-table").dataTable().fnDestroy();
							$("#bootstrap-table").empty();

							var head_data = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='4%'>Status</th><th width='20%'>Actions</th></tr></thead>";
							var head_data1 = "<thead> <tr><th width='9%'>Location Id</th><th width='12%'>Location Name</th><th width='7%'>Device ID</th><th width='10%'>Device Name</th><th width='10%'>IP Address</th><th width='9%'>Earthpit Count </th><th width='9%'>Time interval</th><th width='10%'>Installed Date</th><th width='10%'>Serial No</th><th width='4%'>Status</th><th width='20%'>Actions</th></tr></thead>";

							$(head_data).appendTo("#bootstrap-table");
							$(head_data1).appendTo("#bootstrap-table");

							$("#device").empty();
							var optionsAsString = "<option value='0'>Select Device</option>";

							$('select[name="device"]').append(optionsAsString);
							$
									.each(
											data.details,
											function(i, data) {
												var status;
												if (data.devStatus == 'A') {
													status = "Active";
												} else {
													status = "Inactive";
												}
												var msg_data = "<tr><td id='locid'>"
												        + data.devLoc
												        + "</td><td id=''>"
												        + data.devLocname
												        + "</td><td id='id'>"
														+ data.devId
														+ "</td><td id='devname'>"
														+ data.devname
														+ "</td><td id='ipadd'>"
														+ data.devIp
														+ "</td><td id='earval'>"
														+ data.devEarthPit
														+ "</td><td id='timeint'>"
														+ data.devInter
														+ "</td><td id='instdate'>"
														+ data.devInstall
														+ "</td><td id='devsno'>"
														+ data.devSno
														+ "</td><td>"
														+ status
														+ "</td><td><a class='use-address fa fa-pencil-square-o' href='#' data-toggle='modal' data-target='#myModalEdit'> Edit</a> | <a class='use-confirm fa fa-trash' href='#' data-toggle='modal' data-target='#myModaldel'> Delete</a> |  <a href='#'   data-target='#myModalPing' data-toggle='modal' class='ping'> Ping</a></td></tr>";
												$(msg_data).appendTo(
														"#bootstrap-table");

											});
							$("#bootstrap-table").append("</tbody>");

							$('#bootstrap-table').dataTable().columnFilter({
								sPlaceHolder : "head:after",
								aoColumns : [ {
									type : "text"
								}, {
									type : "text"
								}, {
									type : "text"
								}, {
									type : "text"
								}, {
									type : "text"
								}, {
									type : "text"
								}, {
									type : "text"
								}, {
									type : "text"
								}, {
									type : "text"
								},

								]
							});

							$
									.each(
											data.device,
											function(i, data) {
												var optionsAsString = "<option value='" + data.deviceName + "'>"
														+ data.deviceName
														+ "</option>";
												$('select[name="device"]')
														.append(optionsAsString);
											})

						}
					});
		}
	</script>