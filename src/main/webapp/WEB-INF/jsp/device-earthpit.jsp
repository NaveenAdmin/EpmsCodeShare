
<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:28-12-15
Modified By	    :Prasad-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<script>
	var count;
	var cpits;
	var maxpits
	$(document)
			.ready(
					function() {
						$('#Mytable').empty();
						$(".use-address")
								.click(
										function() {

											var $row = $(this).closest("tr"); // Find the row
											var $locID = $row.find("#locID")
													.text(); // Find the text
											var $devID = $row.find("#devID")
													.text();
											var $earthID = $row
													.find("#earthID").text();
											var $locName = $row
													.find("#locName").text(); // Find the text
											var $devName = $row
													.find("#devName").text();
											var $earthName = $row.find(
													"#earthName").text();
											getcount($locID, $devID);

											// Let's test it out
											var optionsAsString = "<option value='" + $locID + "'>"
													+ $locName + "</option>";
											var optionsAsStrings = "<option value='" + $devID + "'>"
													+ $devName + "</option>";

											$('select[name="locationID"]')
													.append(optionsAsString);
											$('select[name="deviceId"]')
													.append(optionsAsStrings);
											var loc = $("#uplocations").val();

										});

						$(".use-confirm").click(function() {
							var $row = $(this).closest("tr"); // Find the row
							var $locID = $row.find("#locID").text(); // Find the text
							var $devID = $row.find("#devID").text();
							var $earthID = $row.find("#earthID").text();

							// Let's test it out
							$('#loc').val($locID);
							$('#dev').val($devID);
							$('#earth').val($earthID);

						});
						$('.close').click(function() {
							$("#upcheckalert").empty();
							$('#Mytable').empty();
							$('#alert').empty();
							$('#uplocations').empty();
							$('#updevices').empty();
							$('#locations').val('0');
							$('#checkalert').empty();
							$('#devicealert').empty();
	
							$('#devices').val('0');
						});
						$('#cancel').click(function() {

							$("#upcheckalert").empty();
							$('#Mytable').empty();
							$('#alert').empty();
							$('#uplocations').empty();
							$('#updevices').empty();
							$('#locations').val('0');
							$('#checkalert').empty();
							$('#devicealert').empty();
							$('#devices').val('0');
						});

					});

	function formSubmit() {
		var page = $("#page").val();
		var operation = $("#operation").val();
		var text = $("#text").val();
		var loc = $("#location").val();

		if (text == "") {

		} else {
			$.ajax({
				url : "configactions.htm?page=" + page + "&operation="
						+ operation + "&earthpitName=" + text
						+ "&status=A&location=" + loc,
				data : $("#form1").serialize(),
				cache : false,
				async : false,
				success : function(data) {
					$(".modal-backdrop").remove();
					$("body").removeClass("modal-open");
					$("#myModal").modal('hide');
					getDetails('A');
					$("#upcheckalert").empty();
					$('#Mytable').empty();
					$('#alert').empty();
					$('#uplocations').empty();
					$('#updevices').empty();
					$('#locations').val('0');
					$('#checkalert').empty();
					$('#devicealert').empty();
					$('#devices').val('0');

				}
			});
		}

	}
	function getDevicesList(locid, devid) {
		var location = locid;
		$
				.ajax({
					type : "GET",
					url : "ajaxActions.htm?page=CP6&status=location&locationID="
							+ location,
					dataType : "json",
					cache : false,
					success : function(data) {
						$("#MytableUp").empty();
						$(head_data).appendTo("#MytableUp");
						if (data.pits == "") {
							var head_data = "<thead><tr>	<th><font color='red'>No Earthpits</font></th></tr>	</thead>";
							$(head_data).appendTo("#MytableUp");
							$('#upsubmit').hide();
						} else {
							$('#upsubmit').show();
							var head_data = "<thead><tr>	<th>Earthpits</th></tr>	</thead>";
							$(head_data).appendTo("#MytableUp");
							$
									.each(
											data.pits,
											function(i, data) {
												var msg_data = "<tr><td><input type='checkbox' onClick='clearData()' name='uppits' value="
														+ data.eId
														+ ">  "
														+ data.eName
														+ "</td></tr>";
												$(msg_data).appendTo(
														"#MytableUp");

											});
						}

					}
				});
	}
	function getDevices() {
		var location = $("#locations").val();
		$
				.ajax({
					type : "GET",
					url : "ajaxActions.htm?page=CP6&status=location&locationID="
							+ location,
					dataType : "json",
					cache : false,
					success : function(data) {

						$('#devices').empty().append(
								'<option  value="0">Select Device</option>');
						$.each(data.details, function(i, data) {
							$("<option>").val(data.devId).text(data.devName)
									.appendTo('#devices');

						});

						$("#Mytable").empty();
						$(head_data).appendTo("#Mytable");
						if (data.pits == "") {
							var head_data = "<thead><tr>	<th><font color='red'>No Earthpits</font></th></tr>	</thead>";
							$(head_data).appendTo("#Mytable");
						} else {
							var head_data = "<thead><tr>	<th>Earthpits</th></tr>	</thead>";
							$(head_data).appendTo("#Mytable");
							$
									.each(
											data.pits,
											function(i, data) {
												var msg_data = "<tr><td><input type='checkbox' name='pits' onClick='clearData()' value="
														+ data.eId
														+ ">  "
														+ data.eName
														+ "</td></tr>";
												$(msg_data)
														.appendTo("#Mytable");

											});
						}

					}
				});
	}
	getDetails('A');

	function getDetails(status) {
		$
				.ajax({
					type : "GET",
					url : "ajaxActions.htm?page=CP6&status=" + status,
					dataType : "json",
					cache : false,
					success : function(data) {

						if (data.details == "") {

							$("#bootstrap-table").empty();
							var head_data = "<p><h3><center><font size='5' color='red'> No Data Available</font><h3></center></p>";
							$(head_data).appendTo("#bootstrap-table");

						} else {

							if (status == 'I' || status == 'All') {
								$("#bootstrap-table").empty();
								var head_data = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th></tr></thead>";
								var head_data1 = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th></tr></thead>";
								$(head_data).appendTo("#bootstrap-table");
								$(head_data1).appendTo("#bootstrap-table");

								$("#bootstrap-table").dataTable().fnDestroy();
								$("#bootstrap-table").empty();
								var head_data = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th></tr></thead>";
								var head_data1 = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th> </tr></thead>";
								$(head_data).appendTo("#bootstrap-table");
								$(head_data1).appendTo("#bootstrap-table");
								$
										.each(
												data.details,
												function(i, data) {
													var status;
													if (data.status == 'A') {
														status = "Active";
													} else {
														status = "Inactive";
													}
													var msg_data = "<tr><td>"
															+ (parseInt(i) + 1)
															+ "</td><td id='locID' class='column-hide'>"
															+ data.loc
															+ "</td><td id='locName'>"
															+ data.locName
															+ "</td><td id='devID' class='column-hide'>"
															+ data.device
															+ "</td><td id='devName'>"
															+ data.deviceName
															+ "</td><td id='earthID' class='column-hide'>"
															+ data.earthpit
															+ "</td><td id='earthName'>"
															+ data.earthpitName
															+ "</td><td>"
															+ status
															+ "</td></tr>";
													$(msg_data).appendTo(
															"#bootstrap-table");

													var optionsAsString = "<option value='" + data.locName + "'>"
															+ data.locName
															+ "</option>";
													$('select[name="location"]')
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
																			code[this.text] = data.locName;
																		}
																	});

												});
								$("#bootstrap-table").append("</tbody>");

								$('#bootstrap-table').dataTable().columnFilter(
										{
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
											},

											]
										});

								$(".use-address")
										.click(
												function() {

													var $row = $(this).closest(
															"tr"); // Find the row
													var $locID = $row.find(
															"#locID").text(); // Find the text
													var $devID = $row.find(
															"#devID").text();
													var $earthID = $row.find(
															"#earthID").text();
													var $locName = $row.find(
															"#locName").text(); // Find the text
													var $devName = $row.find(
															"#devName").text();
													var $earthName = $row.find(
															"#earthName")
															.text();
													getcount($locID, $devID);

													// Let's test it out
													var optionsAsString = "<option value='" + $locID + "'>"
															+ $locName
															+ "</option>";
													var optionsAsStrings = "<option value='" + $devID + "'>"
															+ $devName
															+ "</option>";

													$(
															'select[name="locationID"]')
															.append(
																	optionsAsString);
													$('select[name="deviceId"]')
															.append(
																	optionsAsStrings);
													/* $('#uplocations').attr('disabled', 'disabled');
													$('#updevices').attr('disabled', 'disabled'); */
													var loc = $("#uplocations")
															.val();

												});

								$(".use-confirm").click(
										function() {
											var $row = $(this).closest("tr"); // Find the row
											var $locID = $row.find("#locID")
													.text(); // Find the text
											var $devID = $row.find("#devID")
													.text();
											var $earthID = $row
													.find("#earthID").text();

											// Let's test it out

											$('#loc').val($locID);
											$('#dev').val($devID);
											$('#earth').val($earthID);

										});

								$('.close').click(function() {
									$("#upcheckalert").empty();
									$('#alert').empty();
									$('#uplocations').empty();
									$('#updevices').empty();

								});

							} else {

								$("#bootstrap-table").empty();
								var head_data = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th>	<th>Actions</th> </tr></thead>";
								var head_data1 = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th>	<th>Actions</th> </tr></thead>";
								$(head_data).appendTo("#bootstrap-table");
								$(head_data1).appendTo("#bootstrap-table");

								$("#bootstrap-table").dataTable().fnDestroy();
								$("#bootstrap-table").empty();
								var head_data = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th>	<th>Actions</th> </tr></thead>";
								var head_data1 = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th>	<th></th> </tr></thead>";
								$(head_data).appendTo("#bootstrap-table");
								$(head_data1).appendTo("#bootstrap-table");
								$
										.each(
												data.details,
												function(i, data) {
													var status;
													if (data.status == 'A') {
														status = "Active";
													} else {
														status = "Inactive";
													}
													var msg_data = "<tr><td>"
															+ (parseInt(i) + 1)
															+ "</td><td id='locID' class='column-hide'>"
															+ data.loc
															+ "</td><td id='locName'>"
															+ data.locName
															+ "</td><td id='devID' class='column-hide'>"
															+ data.device
															+ "</td><td id='devName'>"
															+ data.deviceName
															+ "</td><td id='earthID' class='column-hide'>"
															+ data.earthpit
															+ "</td><td id='earthName'>"
															+ data.earthpitName
															+ "</td><td>"
															+ status
															+ "</td><td><a href='#' class='use-address fa fa-pencil' data-toggle='modal' data-target='#myModalupdate'   data-backdrop='static' data-keyboard='false' > Edit </a> | <a class='use-confirm fa fa-trash' href='#' data-toggle='modal' data-target='#myModaldel'   data-backdrop='static' data-keyboard='false'> Delete </a></td></tr>";
													$(msg_data).appendTo(
															"#bootstrap-table");

													var optionsAsString = "<option value='" + data.locName + "'>"
															+ data.locName
															+ "</option>";
													$('select[name="location"]')
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
																			code[this.text] = data.locName;
																		}
																	});

												});

								$(".use-address")
										.click(
												function() {

													var $row = $(this).closest(
															"tr"); // Find the row
													var $locID = $row.find(
															"#locID").text(); // Find the text
													var $devID = $row.find(
															"#devID").text();
													var $earthID = $row.find(
															"#earthID").text();
													var $locName = $row.find(
															"#locName").text(); // Find the text
													var $devName = $row.find(
															"#devName").text();
													var $earthName = $row.find(
															"#earthName")
															.text();
													getcount($locID, $devID);

													// Let's test it out
													var optionsAsString = "<option value='" + $locID + "'>"
															+ $locName
															+ "</option>";
													var optionsAsStrings = "<option value='" + $devID + "'>"
															+ $devName
															+ "</option>";

													$(
															'select[name="locationID"]')
															.append(
																	optionsAsString);
													$('select[name="deviceId"]')
															.append(
																	optionsAsStrings);
													/* $('#uplocations').attr('disabled', 'disabled');
													$('#updevices').attr('disabled', 'disabled'); */
													var loc = $("#uplocations")
															.val();

												});

								$(".use-confirm").click(
										function() {
											var $row = $(this).closest("tr"); // Find the row
											var $locID = $row.find("#locID")
													.text(); // Find the text
											var $devID = $row.find("#devID")
													.text();
											var $earthID = $row
													.find("#earthID").text();

											// Let's test it out

											$('#loc').val($locID);
											$('#dev').val($devID);
											$('#earth').val($earthID);

										});
								$("#bootstrap-table").append("</tbody>");

								$('#bootstrap-table').dataTable().columnFilter(
										{
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
											}, ]
										});

								$('.close').click(function() {
									$("#upcheckalert").empty();
									$('#alert').empty();
									$('#uplocations').empty();
									$('#updevices').empty();

								});

							}
						}
					}
				});

	}
	function getcount(locid, devid) {
		getDevicesList(locid, devid);
	};
	function getLocationDetails() {
		var locid = $("#location").val();

		$
				.ajax({
					type : "GET",
					url : "ajaxActions.htm?page=CP6&locationID=" + locid
							+ "&status=location",
					dataType : "json",
					cache : false,
					success : function(data) {
						$("#device").empty();
						var optionsAsString = "<option value=0>Select Device</option>";
						$('select[name="device"]').append(optionsAsString);
						$
								.each(
										data.location,
										function(i, data) {
											var optionsAsString = "<option value='" + data.deviceName + "'>"
													+ data.deviceName
													+ "</option>";
											$('select[name="device"]').append(
													optionsAsString);

											var code = {};
											$("select[name='device'] > option")
													.each(
															function() {

																if (code[this.text]) {
																	$(this)
																			.remove();
																} else {
																	code[this.text] = data.deviceName;
																}

															});

										})

					}
				});
	}
	function getDeviceDetails() {
		var divid = $("#device").val();
		var locid = $("#location").val();
		var ajaxUrl;

		if (locid != 'select' && divid != 'select') {

			ajaxUrl = "ajaxActions.htm?page=CP6&deviceId=" + divid
					+ "&locationID=" + locid + "&status=device";
		} else if (locid != 'select' && divid == 'select') {

			ajaxUrl = "ajaxActions.htm?page=CP6&locationID=" + locid
					+ "&status=location";
		} else {
			alert("Please Select Location");
		}
		if (ajaxUrl != "") {
			$
					.ajax({
						type : "GET",
						url : ajaxUrl,
						dataType : "json",
						cache : false,
						success : function(data) {

							$("#bootstrap-table").dataTable().fnDestroy();
							$("#bootstrap-table").empty();
							var head_data = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th>	<th>Actions</th> </tr></thead>";
							var head_data1 = "<thead> <tr><th>Sl. No</th><th class='column-hide'>Location ID</th><th>Location Name</th><th class='column-hide'>Device ID</th><th>Device Name</th><th class='column-hide'>Earthpit ID</th><th>Earthpit Name</th><th>Status</th>	<th>Actions</th> </tr></thead>";
							$(head_data).appendTo("#bootstrap-table");
							$(head_data1).appendTo("#bootstrap-table");

							$
									.each(
											data.location,
											function(i, data) {

												//var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='locID'>"+data.loc+"</td><td id='locName'>"+data.locName+"</td><td id='devID'>"+data.device+"</td><td id='devName'>"+data.deviceName+"</td><td id='earthID'>"+data.earthpit+"</td><td id='earthName'>"+data.earthpitName+"</td><td>"+status+"</td><td><a href='#' class='use-address' data-toggle='modal' data-target='#myModalupdate' >Edit</a> | <a class='use-confirm' href='#' data-toggle='modal' data-target='#myModaldel'>Delete</a></td></tr>";
												var msg_data = "<tr><td>"
														+ (parseInt(i) + 1)
														+ "</td><td id='locID' class='column-hide'>"
														+ data.loc
														+ "</td><td id='locName'>"
														+ data.locName
														+ "</td><td id='devID' class='column-hide'>"
														+ data.device
														+ "</td><td id='devName'>"
														+ data.deviceName
														+ "</td><td id='earthID' class='column-hide'>"
														+ data.earthpit
														+ "</td><td id='earthName'>"
														+ data.earthpitName
														+ "</td><td>"
														+ data.status
														+ "</td><td><a href='#' class='use-address fa fa-pencil' data-toggle='modal' data-target='#myModalupdate' >Edit</a> | <a class='use-confirm fa fa-trash' href='#' data-toggle='modal' data-target='#myModaldel'> Delete </a></td></tr>";
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
								},

								]
							});

						}
					});
		}

	}
	getDetails('A');
	function validate() {
		if (DeviceEarthpitformValidation(document.getElementById("locations"),
				document.getElementById("alert"), document
						.getElementById("devices"), document
						.getElementById("devicealert"))) {
			var cnt = $("input[name='pits']:checked").length;
			if (cnt < 1) {
				$('#checkalert').empty();
				$("<p>Select at least one earthpits..</p>").appendTo(
						"#checkalert");

				return false;
			} else {

				var leftpits = maxpits - cpits;
				if (leftpits > 0) {

					if (cnt <= leftpits) {
						var locations = $("#locations").val();
						var devices = $("#devices").val();
						$.ajax({
							url : "validation.htm?page=CPUD7&locations="
									+ locations + "&devices=" + devices,
							dataType : "json",
							cache : false,
							async : false,
							success : function(data) {
								if (data.result == 'valid') {
									formSubmit();
								} else {
									$("<p>" + data.result + "</p>").appendTo(
											"#alert");

									$("<p>" + data.result + "</p>").appendTo(
											"#checkalert");
								}
							}
						});
					} else {
						$(
								"<p>Please select only " + leftpits
										+ " earth pits</p>").appendTo(
								"#checkalert");
						return false;
					}
				} else {
					$("#checkalert").empty();
					$("<p>Maximum Earthpits Reached.</p>").appendTo(
							"#checkalert");
					return false;
				}

			}
		} else {
			return false;
		}
	}
	function clearData() {

		$("#checkalert").empty();
		$("#alert").empty();
		$("#devicealert").empty();
		$("#upcheckalert").empty();
	}

	function getEarthpitDetails() {
		$("#devicealert").empty();
		var locations = $("#locations").val();
		var devices = $("#devices").val();
		$.ajax({
			url : "validation.htm?page=CPUD8&locationID=" + locations
					+ "&deviceId=" + devices,
			dataType : "json",
			cache : false,
			success : function(data) {
				cpits = data.current;
				maxpits = data.max;
			}
		});

	}
	function upvalidate() {
		$("#upcheckalert").empty();
		var cnt = $("input[name='uppits']:checked").length;
		if (cnt < 1) {

			$("<p>Select at least one earthpits..</p>").appendTo(
					"#upcheckalert");

			return false;
		} else {
			$("#upcheckalert").empty();
			var locations = $("#uplocations").val();
			var devices = $("#updevices").val();
			$.ajax({
				url : "validation.htm?page=CPUD8&locationID=" + locations
						+ "&deviceId=" + devices,
				dataType : "json",
				async : false,
				cache : false,
				success : function(data) {
					cpits = data.current;
					maxpits = data.max;
				}
			});
			var leftpits = maxpits - cpits;
			if (leftpits > 0) {

				if (cnt <= leftpits) {
					return true;
				} else {
					$("#upcheckalert").empty();
					$("<p>Please select only " + leftpits + " earth pits</p>")
							.appendTo("#upcheckalert");
					return false;
				}
			} else {
				$("#checkalert").empty();
				$("<p>Maximum Earthpits Reached.</p>")
						.appendTo("#upcheckalert");
				return false;
			}
		}

	}
</script>
<div id="wrapper">
	<div id="page-wrapper">
		<div class="col-lg-12 col-md-12 content-top-header ">

			<div class="row">
				<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-left"
					id="divEMSnavLeft">
					<div class="row">
						<ul class="nav navbar-top-links navbar-left">
							<li class="dropdown">
								<h5>
									<a href="home.htm">Home</a>/ <a
										href="menu4.htm?url=configuration.htm">Configuration</a>/ <a
										href="configactions.htm?page=CP6&operation=0&status=A"
										class="left-breadcrumb-last-child">Device Earthpits</a>
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
								data-toggle="dropdown" href="#"> <i class="fa fa-user">
								</i> User:<%=session.getAttribute("userName").toString()%>
							</a></li>

							<li class="dropdown"><a href="logout.htm"> <i
									class="fa fa-sign-out"> </i> Logout
							</a></li>
							<!-- /.dropdown -->
						</ul>
					</div>
				</div>


				<!-- /.navbar-top-links -->
				<div class="row">
					<div class="col-lg-12 col-md-12 location" id="divEMSnavCenter">
						<h2>
							<center>
								<%=session.getAttribute("location").toString()%>
							</center>
						</h2>
					</div>
				</div>
			</div>
		</div>

		<div class="clearfix"></div>
		<div class="row">
			<div class="col-lg-12">
				<h1 class="page-header-title">Devices Earthpit</h1>
			</div>
			<!-- /.col-lg-12 -->
		</div>
		<!-- ------------------------------------------------------------------------ Code written by Prasad Begins ------------------------------------------------------------------------ -->
		<div class="">
			<div class="col-lg-12 col-md-12 search-config-align">
				<div class="" style="float: right; width: 100%;">
					<form class="form-inline" role="form">
						<fieldset class="scheduler-border">
							<legend class="scheduler-border">Search</legend>
							<div class="form-group status-input-style ">
								<div class="form-group">
									<div class="sam search-scheduler" style="">
										<!-- 	<label for="name"> Location <br>
									<select class="form-control select-box-style"
										onchange="getLocationDetails()" name="location" id="location">
											<option value="0">Select Location</option>
									</select>
									</label> <label for="device"> Device<br> <select
										class="form-control select-box-style" name="device"
										id="device">
											<option value="0">Select Device</option>
									</select>
									</label>  -->
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
												
										</label>
									</div>

									<div class="radio3">
										<input id="rbtnInactive" type="radio" name="optradio"
											onclick="getDetails('I')">Inactive
									</div>
						</fieldset>
				</div>
			</div>


		</div>

		<label class="config-search-submit-style pull-right">
			<button type="button" class="btn-hp btn-warning" data-toggle="modal"
				data-target="#myModal" data-backdrop="static" data-keyboard="false">
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

<!-- -------Add popup start--------- -->


<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Add Devices</h4>

				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png"/>" width="35"></a>

			</div>
			<div class="modal-body">
				<form:form id="multiCheckBox" commandName="person"
					class="form-inline" role="form" method="post"
					action="devicemultiCheckbox.htm">
					<div class="form-group hpclusers-popup-md">
						<div class="devices-test">
							<label for="name"> Location <b>*</b></label> <select
								class="form-control" name="locationID" id="locations"
								onchange="getDevices()" onclick="clearData()">
								<option value="0">Select Location</option>
								<c:forEach var="location" items="${location}" varStatus="sno">
									<option value="${location.locationID}">${location.locationName}
									</option>
								</c:forEach>
							</select> <label></label>
							<div id="alert" class="alert-message"></div>

						</div>
						<div class="clear"></div>
						<div class="devices-test">
							<label for="name"> Devices <b>*</b>
							</label> <select class="form-control" id="devices" name="deviceId"
								onchange="getEarthpitDetails()">
								<option value="0">Select Device</option>

							</select> <label></label>
							<div id="devicealert" class="alert-message"></div>
						</div>
						<div class="clear"></div>

						<table class="table  table-striped" style="float: left;"
							id="Mytable">
						</table>
						<div id="checkalert" class="alert-message1"></div>
						<div class="clear"></div>
						<input type="hidden" name="page" value="CP6"> <input
							type="hidden" name="operation" value="4">
						<button type="button" data-dismiss="modal"
							class="btn  btn-sm btn-primary popup-form-button-align"
							id="cancel" onclick="clearData()">Cancel</button>
						<button type="submit"
							class="btn pull-right btn-sm btn-primary popup-form-button-align"
							onclick="return validate();">Save</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<!-- ------Add popup End------------ -->
<!-- -------Edit popup start------------------------ -->

<div class="modal fade" id="myModalupdate" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Edit Devices</h4>
				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png"/>" width="35"></a>
			</div>
			<div class="modal-body">
				<form:form id="multiCheckBox" commandName="person"
					class="form-inline" role="form" method="post"
					action="deviceupdatemultiCheckbox.htm">
					<div class="form-group hpclusers-popup-md">
						<div class="devices-test">
							<label for="name"> Location <b>*</b></label> <select
								class="form-control" name="locationID" id="uplocations">
							</select>
						</div>

						<div class="clear"></div>
						<br>
						<div class="devices-test">
							<label for="name"> Devices <b>*</b></label> <select
								class="form-control" id="updevices" name="deviceId">
							</select>
						</div>
						<div class="clear"></div>
						<div id="connect"></div>

						<table class="table  table-striped" style="float: left;"
							id="MytableUp">
						</table>

						<div class="clear"></div>
						<div id="upcheckalert" class="alert-message1"></div>
						<input type="hidden" name="page" value="CP6"> <input
							type="hidden" name="operation" value="4">
						<button type="button" data-dismiss="modal"
							class="btn  btn-sm btn-primary popup-form-button-align"
							onclick="clearData()">Cancel</button>
						<button type="submit" id="upsubmit" onclick="return upvalidate()"
							class="btn  btn-sm btn-primary popup-form-button-align">Save</button>
					</div>

				</form:form>
			</div>
		</div>
	</div>
</div>
<!-- --edit pop up end -->
<!--delete popup start --- -->

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
							type="hidden" name="page" value="CP6" /> <input type="hidden"
							name="operation" value="3" /> <input type="hidden" id="loc"
							name="locationID" /> <input type="hidden" id="earth"
							name="earthpitID" /> <input type="hidden" id="dev"
							name="deviceId" />

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
</div>

