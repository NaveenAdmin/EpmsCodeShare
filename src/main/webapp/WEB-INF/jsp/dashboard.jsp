<!-- 
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application dashboard page.
Modified Date	:10-12-2015
Modified By	    :shree designer
 -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


 <script src="<c:url value="/resources/amcharts/amcharts.js"/>"></script>
 <script src="<c:url value="/resources/amcharts/serial.js"/>"></script> 
<script src="<c:url value="/resources/amcharts/themes/light.js"/>"></script>

 

<style> /* d3js graph css */

 #line_graph {
	width	: 100%;
	height	: 500px;
}
#chartdiv{
	width	: 100%;
	height	: 500px;
}	

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
</script>

	
<div id="page-wrapper">
	<div class="col-lg-12 col-md-12 content-top-header" >
				<div class="row">
             		 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-left" id="divEMSnavLeft">
             		 	<div class="row">
                     		  <ul class="nav navbar-top-links navbar-left">
                               <li class="dropdown">
								<h5>
								<a href="home.htm">Home</a>/
								<a href="menu1.htm?url=dashboard.htm" class="left-breadcrumb-last-child">Dashboard</a>
								</h5> <!-- /.dropdown-messages -->
						</li>
                               <!-- /.dropdown -->
                             </ul>
                             </div>
                    </div>
                    
                     
                      <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-right" id="divEMSnavRight">
                      		<div class="row">
                      		 <ul class="nav navbar-top-links pull-right ">
                               <!-- /.dropdown -->
                               <li class="dropdown">
                                   <a class="dropdown-toggle" data-toggle="dropdown" href="#"> <i class="fa fa-user"> </i>
                                         User:<%=session.getAttribute("userName").toString() %>
                                   </a>
                               </li>
                           
                               <li class="dropdown">
                                 <a href="logout.htm"> <i class="fa fa-sign-out"> </i> Logout</a>
                               </li>
                               <!-- /.dropdown -->
                           </ul>
                          </div>
                      </div>
                  </div>
                  
                  <div class="row">
                   <div class="col-lg-12 col-md-12 location" id="divEMSnavCenter">
                     		<h2> <center>  <%=session.getAttribute("location").toString() %> </center> </h2>
                     </div>
                   </div>
                 
           <!-- /.navbar-top-links -->
       </div>
       <div class="col-lg-12">
		<div class="row">
			<h1 class="page-header-title"> Dashboard</h1> <h3 align="right" style="color: #696969;" class="timer" data-seconds-left=900 ></h3>
		</div>
	</div>
	<div class="col-lg-12 col-md-12">
				<div class="" style="float:right; width:100%;"> 				
					<form class="form-inline" role="form">
						<fieldset class="scheduler-border">
							<legend class="scheduler-border">Search</legend>
							
							<div class="form-group status-input-style">
								<div class="search-location">
								
							<%if(session.getAttribute("userName").toString().equals("admin") || (session.getAttribute("role").toString().equals("0"))){
							  %>
								
								 <label for="name"> Location <br>
									<select class="form-control select-box-style" onchange="getLocationDetails()" name="location" id="location" >
											<option value="0">Select Location</option>
									</select>
									</label>
									
									<label for="device"> Device<br>	
									<select class="form-control select-box-style"  name="device" id="device" > 									
										<option value="0">Select Device</option>
									</select>
									</label>
									 <!-- onchange="getDeviceDetails()" this function is removed by pg1084 -->
									
									<div class="date-pic-cls">
									<label class="datepicker-input-style"><strong>From</strong><br><input type="text" class="date-txt-cls" name="fromdatetime" id="fromdatetime"> <p class="date-icon">  <i class="fa fa-calendar"></i> </p> </label>
									<label class="datepicker-input-style"><strong>To</strong><br>  <input type="text" class="date-txt-cls" name="todatetime" id="todatetime"> <p class="date-icon">  <i class="fa fa-calendar"></i> </p> </label>
									</div>
									<input type="hidden" name="page" id="page" value="DATESEARCH">
									<label class="search-submit-style"> <input type="button" class="btn-hp btn-info" value="Submit" onclick="getSearhDateDetails()" class="radio-online-sub"> &nbsp 
									 <input type="button" class="btn-hp btn-warning" value="CSV file Generation" onclick="pdfDownload()"></label>	
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
									<%-- <select class="form-control select-box-style" onchange="getLocationDetails()" name="location" id="location" disabled="disabled" >
									<option value="<%=session.getAttribute("locid").toString()%>"><%=session.getAttribute("location").toString() %></option>
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
						       $.each(data.device, function(i,data){
								   var optionsAsString = "<option value='" + data.deviceId + "'>" + data.deviceName + "</option>";
									$( 'select[name="device"]' ).append( optionsAsString ); 
							   });
							     }
						          });
					            } 
					          </script>
									</label>
									
									<label for="device"> Device<br>	
									<select class="form-control select-box-style"  name="device" id="device">  
												<option value="0">Select Device</option>
									</select>
									</label>
									
									<div class="date-pic-cls">
									<label class="datepicker-input-style"><strong>From</strong><br><input type="text" class="date-txt-cls" name="fromdatetime" id="fromdatetime"> <p class="date-icon">  <i class="fa fa-calendar"></i> </p> </label>
									<label class="datepicker-input-style"><strong>To</strong><br>  <input type="text" class="date-txt-cls" name="todatetime" id="todatetime"> <p class="date-icon">  <i class="fa fa-calendar"></i> </p> </label>
									</div>
									<input type="hidden" name="page" id="page" value="DATESEARCH">
									<label class="search-submit-style"> <input type="button" class="btn-hp btn-info" value="Submit" onclick="getSearhDateDetails()" class="radio-online-sub"> &nbsp 
									 <input type="button" class="btn-hp btn-warning" value="CSV file Generation" onclick="pdfDownload()"></label>	
									
								 <%
							       }
								%>
									
								</div>
								
								 <script>
           $(document).ready(function () {
        	   getLocationDetailsByZoneId();
           var d = new Date();
           var monthNames = ["January", "February", "March", "April", "May", "June",
               "July", "August", "September", "October", "November", "December"];
           today = monthNames[d.getMonth()] + ' ' + d.getDate() + ' ' + d.getFullYear();
          
         
           $('#todatetime').datepicker({
               defaultDate: "+1d",
               //minDate: 1,
               maxDate: 0,
               dateFormat: 'yy-mm-dd '+d.getHours()+":"+d.getMinutes()+":"+d.getSeconds(),
               //dateFormat: 'yy-mm-dd',
               showOtherMonths: true,
               changeMonth: true,
               selectOtherMonths: true,
               required: true,
               showOn: "focus",
               numberOfMonths: 1,             
               onSelect: function(datetext){
            	var d = new Date(); // for now
                  $('#todatetime').val(datetext);
               },
               
           }).datepicker("setDate", new Date());           
             
               var from = $('#todatetime').datepicker('getDate');
               var date_diff = Math.ceil((from.getTime() - Date.parse(today)) / 86400000);
               var maxDate_d = date_diff - 0 + 'd';
               date_diff = date_diff - 'd';
               $('#fromdatetime').val('').removeAttr('disabled').removeClass('hasDatepicker').datepicker({
                   dateFormat: 'yy-mm-dd '+(d.getHours()-d.getHours())+"0:"+(d.getMinutes()-d.getMinutes())+"0:"+(d.getSeconds()-d.getSeconds())+"0",
                   minDate: date_diff,
                   maxDate: maxDate_d,
                   onSelect: function(datetext){
                	 var d = new Date(); // for now
                       $('#fromdatetime').val(datetext);
                   },
               }).datepicker("setDate",new Date());
               
                             
           $('#fromdatetime').keyup(function () {
              if($(this).val()=="")
               alert('Please select date from Calendar');
           });
           $('#todatetime').keyup(function () {
               $('#todatetime,#fromdatetime').val('');
           
               alert('Please select date from Calendar');
           });
           $('.timer').startTimer({
           	  onComplete: function(element){
           	    element.addClass('is-complete');
           	  },
           	  loop: true,
           	 
           	});
           getSearhDateDetails()
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
</div>
<div class="clearfix"></div>
<div class="row">
<div class="infobox" style="left: 0px; top: 0px; display: none;"></div>
<div id="line_graph" class="line-graph-cls" style="cursor:default;"></div>
<div id="chartdiv"></div>
</div>
  </div>   	   
  <script>
  var epdata;
	 function pdfDownload(){
		 var locid=$("#location").val();
		  var divid=$("#device").val();
		  var fromdate=$("#fromdatetime").val();
		   var todate=$("#todatetime").val();
		   var page=$("#page").val();

		   window.location.replace("downloadPDF.pdf?status='A'&page="+page+"&locationID="+locid+"&deviceId="+divid+"&fromdatetime="+fromdate+"&todatetime="+todate);
	 }
	 
	  function getSearhDateDetails(){
			
		  var locid=$("#location").val();
		  var divid=$("#device").val();
		  var fromdate=$("#fromdatetime").val();
		   var todate=$("#todatetime").val();
		   var page=$("#page").val();

		   $.ajax
		   ({
			global: false,
		   type: "GET",
	       url: "ajaxActions.htm?status='A'&page=DATESEARCH&locationID="+locid+"&deviceId="+divid+"&fromdatetime="+fromdate+"&todatetime="+todate,	  
		   dataType:"json",
		   beforeSend: function () {
               $(".modal").show();
               $("#center").hide();
           },
           complete: function () {
               $(".modal").hide();
           },
		   success: function(data)
		   {
			   $("#line_graph").empty();
			   histData=data.details;
			  epdata=data.epname;
			  console.log(histData.length);
			   initializeLineGraph(histData);
		      }
		   });
		   setTimeout(reloadPage, 900000);
	     }
		function reloadPage(){
			location.reload();
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
		       $.each(data.device, function(i,data){
				   var optionsAsString = "<option value='" + data.deviceId + "'>" + data.deviceName + "</option>";
					$( 'select[name="device"]' ).append( optionsAsString ); 
			   });
			   

		   }
		   });
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
	  function getDeviceDetails(){
		   var divid=$("#device").val();
		
		   $.ajax
		   ({
		   type: "GET",
		   url: "ajaxActions.htm?page=EM1&deviceId="+divid+"&status=device",
		   dataType:"json",
		   success: function(data)
		   {
			   $("#bootstrap-table").empty();
			   var head_data="<thead><tr><th width='20%'>Location</th><th width='20%'>Device Name</th><th width='20%'>Earthpit Name</th><th width='20%'>Voltage </th><th width='20%'>Date </th></thead>";
			   $(head_data).appendTo("#bootstrap-table");

			   $.each(data.details, function(i,data){
				  
				    var msg_data="<tr><td>"+data.locationName+"</td><td id='location'>"+data.deviceName+"</td><td id='devicename'>"+data.earthpitName+"</td><td id='earthpitname'>"+data.voltage+"</td><td id='voltage'>"+data.receivedDate+"</td><td id='date'>";
				   $(msg_data).appendTo("#bootstrap-table");
				  
			   });
			 }
		   });
	    }
	  function getDetails(status){
		   
		   $.ajax
		   ({
		   type: "GET",
		   url: "ajaxActions.htm?page=EM1&status="+status,
		   dataType:"json",
		   success: function(data)
		   {
			   histData=data.details;
			   initializeLineGraph(histData);
			 } 
		   });
		   
	}
	  
	 // <!-- testing -->

	  function fromDate(date) {
	  return date.getFullYear()+"-"+(date.getMonth()+1)+"-" +(date.getDate()-1) + " " +(date.getHours()-date.getHours())+"0:"+(date.getMinutes()-date.getMinutes())+"0:0"+(date.getSeconds()-date.getSeconds());
	  	}
	  function toDate(date) {
	  	return date.getFullYear()+"-"+(date.getMonth()+1)+"-" +date.getDate() + " " +date.getHours()+":"+date.getMinutes()+":"+date.getSeconds();
	  		}
	  	var d = new Date();
	  //<!--    -->
	  
	  function getFilterDetails(status){
		  var sfromdate=fromDate(d);
			 var stodate=toDate(d);
		   $.ajax
		   ({
		   type: "GET",
		   url: "ajaxActions.htm?page=EM1&status="+status+"&fromdatetime="+sfromdate+"&todatetime="+stodate,
		   dataType:"json",
		   success: function(data)
		   {
			        $.each(data.location, function(i,data){
					var optionsAsString = "<option value='" + data.locationId + "'>" + data.locationName + "</option>";
					$( 'select[name="location"]' ).append( optionsAsString ); 
			     });
		      }
		   });
	      }

 getFilterDetails('A');

function initializeLineGraph(histData) {
	
	 var qwerty = histData;
	 var linegraph_div = "line_graph";

if(histData.length>0){
	 var arr = [];
	 for(i = 0; i< histData[0].size; i++){    

	 arr.push({ "valueAxis": "v1", 
	               "lineColor": getRandomColor(),
	 		"bullet" : "round",
	 		"bulletBorderThickness" : "1",
	 		"title":title(i),
	 		"valueField":"voltage"+i,
	 		"fillAlphas" : "0"
	 		 });
	 }
}
function title(i){
	return epdata["earthpitName"+i];
}
function getRandomColor() {
    var letters = '0123456789ABCDEF'.split('');
    var color = '#';
    for (var i = 0; i < 6; i++ ) {
        color += letters[Math.floor(Math.random() * 16)];
    }
   
    return color;
}


if(histData.length>0){
	 $("#chartdiv").hide();
	 $("#line_graph").show();
var chart = AmCharts.makeChart(line_graph, {
    "type": "serial",
    "theme": "light",
    "legend": {
        "useGraphSettings": true
    },
    "dataProvider": histData,
    graphs: arr,
    "chartScrollbar": {},
    "chartCursor": {
        "cursorPosition": "mouse"
    },
    "categoryField": "receivedDate",
    
    "categoryAxis": {
    	 "labelRotation": 60,
        "axisColor": "#DADADA",
        "minorGridEnabled": true,
        "title": "Date And Time",
    },
valueAxes: [{
      guides: [{
        value: 7,
        lineAlpha: 0.8,
        lineColor: "#c00",
        label: "",
        position: "right"
      }],
      "title": "Voltage",
      }],
    "export": {
    	"enabled": true,
        "position": "bottom-right"
     }
});
	
	chart.addListener("dataUpdated", zoomChart);
	zoomChart(); 
}
else{
	$("#line_graph").hide();
	$("#chartdiv").show();
	$('#chartdiv').html('<h3 id=center style="color:red; margin-top:30px;"><center>The chart contains no data for the selected parameters</center></h3>');	
}

function zoomChart(){
    chart.zoomToIndexes(0, chart.dataProvider.length - 1);
}
}


function showDataHist(obj, d) {
	  var coord = d3.mouse(obj);
	  var infobox = d3.select(".infobox");
	 // now we just position the infobox roughly where our mouse is
	  infobox.style("left", (coord[0] + 300) + "px" );
	  infobox.style("top", (coord[1])+425 + "px");
	  $(".infobox").html(d);
	  $(".infobox").show();
	  }
	  
	function hideData() {
	 $(".infobox").hide();
	 }
function getCurrentDate() {
	 var currentDate = new Date();
	 var date = currentDate.getDate();
	 var year = currentDate.getFullYear();
	 var month = currentDate.getMonth();
	 var hour    = currentDate.getHours();
	 var minute  = currentDate.getMinutes();
	 var seconds = currentDate.getSeconds();  
	
	 return (month+1)+"/"+date+"/"+year+" "+hour+":"+minute+":"+seconds;
}

</script> 