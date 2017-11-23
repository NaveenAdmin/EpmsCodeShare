<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application module page.
Modified Date	:15-10-15
Modified By	    :Prasad
-->
<link rel="stylesheet"
	href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css"
	type="text/css" media="all" />

<!-- First get JQuery -->
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<!-- Then get JQuery UI -->
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

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
function formSubmit(){
	var page=$("#page").val();
	var operation=$("#operation").val();
	var text=$("#text").val();
		 $.ajax({
		     url:"actions.htm?page="+page+"&operation="+operation+"&description="+text,
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
    	   url: "ajaxActions.htm?page=AM2&status="+status,
    	   dataType:"json",
    	   success: function(data)
    	   {
    		   $("#bootstrap-table").empty();
    		   var head_data="<thead><tr><th width='10%'>SNo</th><th width='20%'>Page ID</th><th width='20%'>Page Name</th><th width='20%'>Status</th><th width='20%'>Action </th></thead>";
    		   $(head_data).appendTo("#bootstrap-table");
    		   $.each(data.details, function(i,data){
    			  var status;
    			   if(data.status=='A'){
    				   status="Active";
    			  }else{
    				  status="Inactive";
    			  }
    			   var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='id'>"+data.id+"</td><td id='roleDesc'>"+data.desc+"</td><td>"+status+"</td><td><a href='#' class='use-address' data-toggle='modal' data-target='#myModalupdate' id='open'>Edit</a> | <a class='use-confirm' href='#' data-toggle='modal' data-target='#myModaldel'>Delete</a></td></tr>";
    			   $(msg_data).appendTo("#bootstrap-table");
    			  
    		   });
    		   $(".use-address").click(function() {
    	     	    var $row = $(this).closest("tr");    // Find the row
    	     	    var $text = $row.find("#id").text(); // Find the text
    	     	   var $textDesc = $row.find("#roleDesc").text(); // Find the text
    	     	    
    	     	    // Let's test it out
    	     	    $('#usertext').val($textDesc);
    	     	    $('#moduleID').val($text);
    	     	    
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
	<div class="row ">
		<div class="col-lg-12">
			<!-- Here Madule Name is changed to Page Name,but total functionality is module -->
			<h5>
				<a href="home.htm">Home&nbsp;/&nbsp;</a><a
					href="menu4.htm?url=homeadmin.htm">Administration&nbsp;/&nbsp;</a><a
					href="actions.htm?page=AM2&operation=0&status=A">Pages</a>
			</h5>
			<div class="col-lg-4 col-md-6 location" id="divEMSnavCenter">
				<ul class="nav navbar-top-links navbar-right">

					<!-- /.dropdown -->
					<li class="dropdown"><a class="location-font"
						data-toggle="dropdown" href="#"> <%=session.getAttribute("location").toString() %>
					</a> <!-- /.dropdown-tasks --></li>
				</ul>
			</div>

		</div>
		<!-- /.col-lg-12 -->

	</div>
	<h1 class="page-header-title">Pages</h1>
	<div class="row">
		<div class="col-lg-12 col-md-6">
			<div class="sam" style="float: left; width: 80%; padding-top: 17px;">
				<label for="name"> Status:</label> <label class="radio-inline">
					<input type="radio" name="optradio" onclick="getDetails('All')">All
				</label> <label class="radio-inline"> <input type="radio"
					name="optradio" checked="checked" onclick="getDetails('A')">Active
				</label> <label class="radio-inline"> <input type="radio"
					name="optradio" onclick="getDetails('I')">Inactive
				</label>
			</div>
			<div class="pull-right">
				<button type="button" class="btn btn-primary btn-sm"
					data-toggle="modal" data-target="#myModal">Add</button>
			</div>
			<div class="modal fade" id="myModal" role="dialog">
				<div class="modal-dialog modal-sm">
					<div class="modal-content">
						<div class="content-header">
							<h4>Add Page</h4>

							<a class="close" data-dismiss="modal"><img
								src="<c:url value="/resources/images/close.png" />" width="35"></a>

						</div>
						<div class="modal-body">

							<form:form name="form1" class="form-inline" role="form">
								<div class="form-group  popup-form-align">
									<label for="name"> <span> Module Name </span> <input
										type="text" name="description" class="form-control" id="text"
										placeholder="Enter page Name" onkeydown="clearData()"></label>
									<div id="alert"></div>
									<input type="hidden" id="page" name="page" value="AM2">
									<input type="hidden" id="operation" name="operation" value="4">
									<button type="button"
										class="btn  btn-sm btn-primary popup-form-button-align"
										data-target="#myModal" onclick="return validate();">Save</button>
								</div>
							</form:form>
						</div>

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
							<form:form class="form-inline" action="actions.htm" method="post"
								modelAttribute="EmployeRoles" role="form">
								<input type="hidden" name="page" value="AM2">
								<input type="hidden" name="operation" value="3">
								<input type="hidden" id="message" name="moduleID">
								<button type="submit"
									class="btn  btn-sm btn-primary popup-form-button-align">Yes</button>
								<button type="button" data-dismiss="modal"
									class="btn  btn-sm btn-primary popup-form-button-align">No</button>
							</form:form>
						</div>

					</div>

				</div>
			</div>
		</div>

		<!-- Delete Popup End -->


		<table class="table table-hover table-striped" id="bootstrap-table">
			<thead>
				<tr>
					<th width="10%">SNo</th>
					<th width="20%">Page ID</th>
					<th width="20%">Page Name</th>
					<th width="20%">Status</th>
					<th width="20%">Action</th>
			</thead>
			<tbody>
				<c:forEach var="list" items="${list}" varStatus="sno">
					<tr id="rows">
						<td>${sno.index+1}</td>
						<td id="id">${list.moduleID}</td>
						<td id="roleDesc">${list.description}</td>
						<td>Active</td>
						<td><a href="#" class="use-address" data-toggle="modal"
							data-target="#myModalupdate" id="open">Edit</a> | <a
							class="use-confirm" href="#" data-toggle="modal"
							data-target="#myModaldel">Delete</a></td>
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
				<h4>Update Page</h4>

				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png" />" width="35"></a>

			</div>
			<div class="modal-body">

				<form:form class="form-inline" action="actions.htm" method="post"
					modelAttribute="EmployeRoles" role="form"
					onsubmit="return upvalidate()">
					<div class="form-group  popup-form-align">
						<label for="name"> <span>Employe Role </span> <input
							type="text" name="description" class="form-control" id="usertext"
							placeholder="Page Name" onload="getValue()"></label>
						<div id="upalert"></div>
						<input type="hidden" name="page" value="AM2"> <input
							type="hidden" name="operation" value="2"> <input
							type="hidden" name="moduleID" id="moduleID">
						<button type="submit"
							class="btn  btn-sm btn-primary popup-form-button-align"
							data-target="#myModal">Update</button>
					</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<script>
           function validate()
           {           	              
             if(formValidation(document.getElementById("text"),document.getElementById("alert"))){
          	   formSubmit();
               	 $(".modal-backdrop").remove();
               	 $("body").removeClass("modal-open");
               	 $("#myModal").css("display","none");
               	 location.reload();
               	 document.form1.description.value="";   
             }else{
          	   return false;
             }
           }
           function upvalidate()
           {                  
             if(formValidation(document.getElementById("usertext"),document.getElementById("upalert"))){
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
     	    var $row = $(this).closest("tr");    // Find the row
     	    var $text = $row.find("#id").text(); // Find the text
     	   var $textDesc = $row.find("#roleDesc").text(); // Find the text
     	    
     	    // Let's test it out
     	    //alert($text);
     	    $('#usertext').val($textDesc);
     	   $('#moduleID').val($text);
     	    
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
     	    alert(id+" deleted");
     	    $('#myModal').modal('hide');
     	});
     });

 </script>


