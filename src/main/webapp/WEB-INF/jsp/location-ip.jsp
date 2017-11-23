<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page for accessing the application.
Modified Date	:30-10-15
Modified By	    :Prasad
-->
 <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <script>
function formSubmit(){
	 var page=$("#page").val();
	 var operation=$("#operation").val();
	 var text=$("#text").val();
	 var loc=$("#location").val();
	
	 if(text==""){
	 	
	 }else{
	 	  $.ajax({
	 	     url:"actions.htm?page="+page+"&operation="+operation+"&ipAddress="+text+"&status=A&location="+loc,
	 	     data: $("#form1").serialize(),
	 	     success: function (data) {
	 	    	 
	 	    	 getDetails('A');
	 	   }
	 	}); 
	 }
}
 
 function getDetails(status){
	   
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxActions.htm?page=AM8&status="+status,
	   dataType:"json",
	   success: function(data)
	   {
		   
		   $("#bootstrap-table").empty();
		   var head_data="<thead><tr><th width='10%'>ID</th><th width='20%'>Location</th><th width='20%'>IP Address</th><th width='20%'>Status</th><th width='20%'>Action </th></thead>";
		   $(head_data).appendTo("#bootstrap-table");
		   $.each(data.details, function(i,data){
			  var status;
			   if(data.status=='A'){
				   status="Active";
			  }else{
				  status="Inactive";
			  }
			 
			   var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='id'>"+data.lid+"</td><td id='roleDesc'>"+data.ipadd+"</td><td>"+status+"</td><td><a href='#' class='use-address' data-toggle='modal' data-target='#myModalupdate' >Edit</a> | <a class='use-confirm' href=''#' data-toggle='modal' data-target='#myModaldel'>Delete</a></td></tr>";
			   $(msg_data).appendTo("#bootstrap-table");
			  
		   });
		   
		   $(".use-address").click(function() {
	     	    var $row = $(this).closest("tr");    // Find the row
	     	    var $text = $row.find("#id").text(); // Find the text
	     	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
	     	    
	     	    // Let's test it out
	     	   $('#usertext').val($textDesc);
	     	   $('#locationID').val($text);
	     	    
	     	});
		   
		   $(".use-confirm").click(function() {
	     	    var $row = $(this).closest("tr");    // Find the row
	     	    var $text = $row.find("#id").text(); // Find the text
	     	    
	     	    // Let's test it out
	     	   $('#message').val($text);
	     	});
	       }
	   });
}
 </script>
 <div id="page-wrapper">
			<div class="col-lg-12 col-md-6 content-top-header" >
             		 <div class="col-lg-3 col-md-6" id="divEMSnavLeft">
                     		  <ul class="nav navbar-top-links navbar-left">
                                <li class="dropdown">
                                   <h5>  <a href="home.htm">Home&nbsp;/&nbsp;</a><a href="menu4.htm?url=homeadmin.htm" >Administration&nbsp;/&nbsp;</a><a href="actions.htm?page=AM8&operation=0&status=A">Location IP</a> </h5>
                                    <!-- /.dropdown-messages -->
                                </li>
                                <!-- /.dropdown -->
                              </ul>
                     </div>
                      <div class="col-lg-4 col-md-6 location" id="divEMSnavCenter">
                      		 <ul class="nav navbar-top-links navbar-right">
                       
                                <!-- /.dropdown -->
                                <li class="dropdown">
                                    <a class="location-font" data-toggle="dropdown" href="#">
                                         <%=session.getAttribute("location").toString() %>
                                    </a>
                                    <!-- /.dropdown-tasks -->
                                </li>
                            </ul>
                      </div>
                       <div class="col-lg-5 col-md-6" id="divEMSnavRight">
                       		 <ul class="nav navbar-top-links navbar-right">
                        
                                <!-- /.dropdown -->
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                      User:<%=session.getAttribute("userName").toString() %>
                                    </a>
                                    <!-- /.dropdown-alerts -->
                                </li>
                                <!-- /.dropdown -->
                                <li class="dropdown">
                                     <a href="logout.htm">Logout</a>
                                    <!-- /.dropdown-user -->
                                </li>
                                <!-- /.dropdown -->
                            </ul>
                       </div>
            <!-- /.navbar-top-links -->
            </div>
            <div class="row " id="divEMSPageTitle">
                <div class="col-lg-12">
                    <h1 class="page-header-title">Location IP </h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>
            <div class="row">
                <div class="col-lg-12 col-md-6">
                <div class="sam" style="float:right; width:80%; padding-top:17px;"> 
                <form class="form-inline" role="form">
                    <div class="form-group status-radio">
                     <label for="name"> Status:</label>
                    <label class="radio-inline">
                      <input type="radio" name="optradio" onclick="getDetails('All')">All
                    </label>
                    <label class="radio-inline">
                      <input type="radio" name="optradio" checked="checked" onclick="getDetails('A')">Active
                    </label>
                    <label class="radio-inline" onclick="getDetails('I')">
                      <input type="radio" name="optradio">Inactive 
                    </label>
                    </div>
                    <div class="pull-right">
					 <button type="button" class="btn btn-primary btn-sm popup-form-button-align" data-toggle="modal" data-target="#myModal">Add</button>
                     </div>
					 <div class="modal fade" id="myModal" role="dialog">
                        <div class="modal-dialog modal-sm">
                          <div class="modal-content">
                            <div class="content-header">
                            	<h4> Add Location IP </h4>
                              <a class="close" data-dismiss="modal"><img src="<c:url value="/resources/images/close.png"/>" width="35"></a>
                            </div>
                             <div class="modal-body">
								<form:form class="form-inline" name="form1" role="form">
                               	    <div class="form-group hpclusers-popup">
										<div>
											<label for="name"> Location </label>
											<select class="form-control" id="location" >
												<c:forEach var="location" items="${location}" varStatus="sno" > 
      											<option value="${location.locationID}">${location.locationName}</option>
												</c:forEach>
											</select>
										</div><br>
										</div>
									
								 		<div class="form-group  hpclusers-popup">
                                       <label for="name"> <span> IpAddress</span> 
                                       <input type="text"  name="ipaddress" id="text" required placeholder="Enter Ipaddress" onkeydown="clearData()"></label>
                                       <div id="alert"></div>
                                       <input type="hidden" id="page" name="page" value="AM8">
                                       <input type="hidden" id="operation" name="operation" value="4"> 
                                       <button type="button"  class="btn  btn-sm btn-primary popup-form-button-align"  data-target="#myModal" onclick="return validate();">Save</button>
                                    </div>
                                </form:form>
								</div><br>										
								</div>
								</div>
								</div>
                               </form>
                               
                            </div>
                           
                          </div>
                        </div>
                      </div>
                </form>
              
                <div class="modal fade" id="myModaldel" role="dialog">
                    <div class="modal-dialog modal-sm">
                        <div class="modal-content">
								<div class="content-header">
								<a class="close" data-dismiss="modal"><img src="<c:url value="/resources/images/close.png"/>" width="35"></a>
                                  </div>
								<div class="modal-body popup-delete-align">
								<div class="form-group  popup-form-align del-popup-button">
											<label>Are you sure you want to delete ? </label>
									        <form class="form-inline" action="actions.htm" method="post"  role="form">
											<input type="hidden" name="page" value="AM8">
                                       		<input type="hidden" name="operation" value="3">
											<input type="hidden" id="message" name="locationID">
											<button type="submit" class="btn  btn-sm btn-primary popup-form-button-align" >Yes</button>
								 			<button type="button"  data-dismiss="modal" class="btn  btn-sm btn-primary popup-form-button-align" >No</button>
								 			</form>
								     </div>
                               </div>
                         </div>
                    </div>
                </div>    
      <table class="table table-hover table-striped" id="bootstrap-table">
        <thead>
          <tr>
            <th width="10%">ID</th>
            <th width="20%">Location</th>
            <th width="20%">IP Address</th>
            <th width="20%">Status</th>
            <th width="20%">Action </th>
          </tr>
        </thead>
        <tbody>
          <c:forEach var="list" items="${list}" varStatus="sno" > 
      <tr id="rows"> 
      <td>${sno.index+1}</td>
      <td id="id">${list.locationID}</td>  
      <td id="roleDesc">${list.ipAddress}</td> 
      <td>Active</td> 
      <td ><a href="#" class="use-address" data-toggle="modal" data-target="#myModalupdate" >Edit</a> | <a class="use-confirm" href="#" data-toggle="modal" data-target="#myModaldel">Delete</a></td>
      </tr>  
      </c:forEach> 
	  </tbody>
      </table>
        </div>         
        </div>
         <div class="modal fade" id="myModalupdate" role="dialog">
                        <div class="modal-dialog modal-sm">
                          <div class="modal-content">
                            <div class="content-header">
                            	<h4> Update Role </h4>
                             
                              <a class="close" data-dismiss="modal"><img src="<c:url value="/resources/images/close.png" />" width="35" ></a>
                              
                            </div>
                             <div class="modal-body">
                                   <form class="form-inline" action="actions.htm" method="post" modelAttribute="EmployeRoles" role="form" onsubmit="return upvalidate()">
                                    <div class="form-group  popup-form-align">
                                       <label for="name"> <span>Location ip </span>
                                       <input type="text" name="ipaddress" class="form-control" id="usertext" placeholder="Ip Address"></label>
                                       <div id="upalert"></div>
                                       <input type="hidden" name="page" value="AM8">
                                       <input type="hidden" name="operation" value="2">
                                       <input type="hidden" name="locationID" id="locationID">
                                       <button type="submit" class="btn  btn-sm btn-primary popup-form-button-align" data-target="#myModal">Update</button>
                                    </div>
                               </form>
                            </div>
                           
                          </div>
                        </div>
                      </div>
                     </div> 
                 </div>
             <script>
             function validate()
             {           	              
               if(IPformValidation(document.getElementById("text"),document.getElementById("alert"))){
            	   formSubmit();
                 	 $(".modal-backdrop").remove();
                 	 $("body").removeClass("modal-open");
                 	 $("#myModal").css("display","none");
                 	 location.reload();
                 	 document.form1.ipaddress.value="";   
               }else{
            	   return false;
               }
             }
             function upvalidate()
             {                  
               if(IPformValidation(document.getElementById("usertext"),document.getElementById("upalert"))){
            		return true;
                 	
               }else{
            	   return false;
               }
             }
             function clearData(){
           	  $("#alert").empty();
           	}
           $(document).ready(function(){
        	$(".use-address").click(function() {
     	    var $row = $(this).closest("tr");// Find the row
     	    var $text = $row.find("#id").text(); // Find the text
     	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
     	    
     	    // Let's test it out
     	  $('#usertext').val($textDesc);
     	   $('#locationID').val($text);
     	    
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
     	    /* $('[data-id=' + id + ']').remove(); */
     	   $('#myModal').modal('hide');
     	});
     });

     </script>
  
  
  