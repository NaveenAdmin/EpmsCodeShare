<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application category page.
Modified Date	:24-12-15
Modified By	    :Prasad
-->
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
		     cache: false,
		     async:false,
		     success: function (data) {	
		    	 $(".modal-backdrop").remove();
             	 $("body").removeClass("modal-open");
             	 $("#myModal").modal('hide');    
             	$('#text').val("");
		    	 getDetails('A');
		   }
		});  
	
		}
       function getDetails(status){
    	   
    	   $.ajax
    	   ({
    	   type: "GET",
    	   url: "ajaxActions.htm?page=AM4&status="+status,
    	   dataType:"json",
    	   cache: false,
		   async:false,
    	   success: function(data)
    	   {
               if(data.details==""){
    		   $("#bootstrap-table").empty();
			   var head_data="<p><h3><center><font size='5' color='red'> No Data Available</font><h3></center></p>";
			   $(head_data).appendTo("#bootstrap-table");
			   
		   }else{
			   if(status == 'I' || status=='all'){
				   $("#bootstrap-table").empty();
	    		   var head_data="<thead><tr><th width='10%'>SNo</th><th width='20%'>Category ID</th><th width='20%'>Category Name</th><th width='20%'>Status</th></thead>";
	    		   var head_data1="<thead><tr><th width='10%'>SNo</th><th width='20%'>Category ID</th><th width='20%'>Category Name</th><th width='20%'>Status</th></thead>";
	    		   $(head_data).appendTo("#bootstrap-table");
	    		   $(head_data1).appendTo("#bootstrap-table");
	    		   
	    		   $("#bootstrap-table").dataTable().fnDestroy();
	    		   $("#bootstrap-table").empty();
	    		   var head_data="<thead><tr><th width='10%'>SNo</th><th width='20%'>Category ID</th><th width='20%'>Category Name</th><th width='20%'>Status</th></thead>";
	    		   var head_data1="<thead><tr><th width='10%'>SNo</th><th width='20%'>Category ID</th><th width='20%'>Category Name</th><th width='20%'>Status</th></thead>";
	    		   $(head_data).appendTo("#bootstrap-table");
	    		   $(head_data1).appendTo("#bootstrap-table");
	    		   
	    		   $.each(data.details, function(i,data){
	    			   var status;
	    			   if(data.status=='A'){
	    				   status="Active";
	    			  }else{
	    				  status="Inactive";
	    			  }
	    			   var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='id'>"+data.id+"</td><td id='roleDesc'>"+data.desc+"</td><td>"+status+"</td></tr>";
	    			   $(msg_data).appendTo("#bootstrap-table");
	    			  
	    		   });
	               $("#bootstrap-table").append("</tbody>");
	     		   
	    	       $('#bootstrap-table').dataTable().columnFilter({ sPlaceHolder: "head:after",
	                  aoColumns: [
	                              { type: "text" },
	                              { type: "text" },
	                              { type: "text" },
	    						  { type: "text" },
	                              
	    						  ]
	                    });
	    		   $(".use-address").click(function() {
	    	     	    var $row = $(this).closest("tr");    // Find the row
	    	     	    var $text = $row.find("#id").text(); // Find the text
	    	     	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
	    	     	    
	    	     	    // Let's test it out
	    	     	   $('#usertext').val($textDesc);
	    	     	   $('#categoryID').val($text);
	    	     	    
	    	     	});
	    	     	$(".use-confirm").click(function() {
	    	     	    var $row = $(this).closest("tr");    // Find the row
	    	     	    var $text = $row.find("#id").text(); // Find the text
	    	     	    
	    	     	    // Let's test it out
	    	     	   $('#message').val($text);
	    	     	    
	    	     	});
	    	     	
	        }else{
				   
			  
    		   $("#bootstrap-table").empty();
    		   var head_data="<thead><tr><th width='10%'>SNo</th><th width='20%'>Category ID</th><th width='20%'>Category Name</th><th width='20%'>Status</th><th width='20%'>Action </th></thead>";
    		   var head_data1="<thead><tr><th width='10%'>SNo</th><th width='20%'>Category ID</th><th width='20%'>Category Name</th><th width='20%'>Status</th><th width='20%'>Action </th></thead>";
    		   $(head_data).appendTo("#bootstrap-table");
    		   $(head_data1).appendTo("#bootstrap-table");
    		   
    		   $("#bootstrap-table").dataTable().fnDestroy();
    		   $("#bootstrap-table").empty();
    		   var head_data="<thead><tr><th width='10%'>SNo</th><th width='20%'>Category ID</th><th width='20%'>Category Name</th><th width='20%'>Status</th><th width='20%'>Action </th></thead>";
    		   var head_data1="<thead><tr><th width='10%'>SNo</th><th width='20%'>Category ID</th><th width='20%'>Category Name</th><th width='20%'>Status</th><th width='20%'> </th></thead>";
    		   $(head_data).appendTo("#bootstrap-table");
    		   $(head_data1).appendTo("#bootstrap-table");
    		   
    		   $.each(data.details, function(i,data){
    			   var status;
    			   if(data.status=='A'){
    				   status="Active";
    			  }else{
    				  status="Inactive";
    			  }
    			   var msg_data="<tr><td>"+(parseInt(i)+1)+"</td><td id='id'>"+data.id+"</td><td id='roleDesc'>"+data.desc+"</td><td>"+status+"</td><td><a href='#' class='use-address fa fa-pencil' data-toggle='modal' data-target='#myModalupdate'  data-backdrop='static' data-keyboard='false' id='open'> Edit </a> | <a class='use-confirm fa fa-trash' href='#' data-toggle='modal' data-target='#myModaldel'  data-backdrop='static' data-keyboard='false'> Delete </a></td></tr>";
    			   $(msg_data).appendTo("#bootstrap-table");
    			  
    		   });
              
    		   $(".use-address").click(function() {
    	     	    var $row = $(this).closest("tr");    // Find the row
    	     	    var $text = $row.find("#id").text(); // Find the text
    	     	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
    	     	    
    	     	    // Let's test it out
    	     	   $('#usertext').val($textDesc);
    	     	   $('#categoryID').val($text);
    	     	    
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
                                
      						  ]
                      });
	        }
            }
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
							<a href="menu5.htm?url=homeadmin.htm">Administration</a>/
							<a href="actions.htm?page=AM4&operation=0&status=A" class="left-breadcrumb-last-child">Category</a>
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
<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header-title">Category</h1>
	</div>
</div>
 
<!--s  -->
<div class=" ">
	<div class="col-lg-12 col-md-12 search-config-align">
		<div class="" style="float: right; width: 100%;">
			<form class="form-inline" role="form">
				<fieldset class="scheduler-border">
					<legend class="scheduler-border">Search</legend>
					<div class="form-group status-input-style">
						<div class="form-group status-input-style">
							<div class="sam search-scheduler" style="height: 45px;">
								<label class="radio-inline check-all">
									<fieldset class="scheduler-border  mst-search-status">
										<legend class="scheduler-border status-cont">Status</legend>
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
		<table class="table table-hover table-striped " id="bootstrap-table">

		</table>

		<br>
		<br>
		<br>
		<br>
	</div>
</div>

<!----------Add UserType start------------>
<div class="modal fade" id="myModal" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Add Category</h4>

				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png" />" width="35"></a>
			</div>
			
			<div class="modal-body">
				<%-- <form:form name="form1" class="form-inline"  role="form"> --%>
				
				
				
				<div class="form-group hpclusers-popup">

							<div class="devices-test">
								<label for="name"> Category Name <b>*</b> </label> <input
						type="text" name="description" class="form-control" id="text"
						placeholder="Enter Category" onkeydown="clearData()">
								<div id="alert" class="alert-message"></div>
							</div>
							
							<div class="clear"></div>
					<input type="hidden" id="page" name="page" value="AM4"> <input
						type="hidden" id="operation" name="operation" value="4">
						<button type="button" data-dismiss="modal"
							class="btn  btn-sm btn-primary popup-form-button-align" id="cancel" onclick="clearData()">Cancel</button>
					<button type="button"
						class="btn pull-right btn-sm btn-primary popup-form-button-align"
						data-target="#myModal" onclick="return validate();">Save</button>

							<!-- <button type="button" class="btn  btn-sm btn-primary popup-form-button-align" data-toggle="modal" data-target="#myModal">Save</button> -->
				</div>
				<%-- </form:form> --%>
			</div>
		</div>
	</div>
</div>
<!----------Add UserType end------------>
<!-- Delete Popup start-->
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
						<input type="hidden" name="page" value="AM4">
						<input type="hidden" name="operation" value="3">
						<input type="hidden" id="message" name="categoryID">
						
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
<!-- Delete Popup End -->
<!-- Edit Popup start -->
<div class="modal fade" id="myModalupdate" role="dialog">
	<div class="modal-dialog modal-sm">
		<div class="modal-content">
			<div class="content-header">
				<h4>Update Category</h4>
				<a class="close" data-dismiss="modal"><img
					src="<c:url value="/resources/images/close.png" />" width="35"></a>
			</div>
			<div class="modal-body">
				<form:form class="form-inline" action="actions.htm" method="post"
					modelAttribute="EmployeRoles" role="form"
					onsubmit="return upvalidate()">
					<div class="form-group hpclusers-popup">
                         <div class="devices-test">
							<label for="name">Category Name <b>*</b> </label><input
							type="text" name="description" class="form-control"
							id="usertext" placeholder="Action" onload="getValue()" onkeydown="clearData()"></label>
						<div id="upalert" class="alert-message"></div>
							</div>
							<div class="clear"> </div>
							
							<input type="hidden" name="page" value="AM4"> <input
							type="hidden" name="operation" value="2"> <input
							type="hidden" name="categoryID" id="categoryID">
							<button type="button" data-dismiss="modal"
							class="btn  btn-sm btn-primary popup-form-button-align" onclick="clearData()">Cancel</button>
						<button type="submit"
							class="btn  btn-sm btn-primary popup-form-button-align"
							data-target="#myModal">Update</button>
				       <!-- <button type="button" class="btn  btn-sm btn-primary popup-form-button-align" data-toggle="modal" data-target="#myModal">Save</button> -->
						</div>
				</form:form>
			</div>
		</div>
	</div>
</div>
<!-- Edit Popup End -->
</div>
<script>
             getDetails('A');
             function validate()
             {           	              
               if(formValidation(document.getElementById("text"),document.getElementById("alert"))){
               	 var text=$("#text").val();
            	   $.ajax({
           		     url:"validation.htm?page=AMUD2&description="+text,
           		   	 dataType:"json",
					cache: false,
     		     	async:false,
           		     success: function (data) {
           		    	 if(data.result=='valid'){
           		    		 formSubmit();                             	
                             	 $('#text').val("");
           		    	 }else{
           		    		$("<p>"+data.result+"</p>").appendTo("#alert");
           		    	 }
           		   }
           		});
                }else{
            	   return false;
               }
             }
             function upvalidate()
             {                  
               if(formValidation(document.getElementById("usertext"),document.getElementById("upalert"))){
               	   var text=$("#usertext").val();
             	   var hint;
             	   $.ajax({
             		     url:"validation.htm?page=AMUD2&description="+text,
             		   	 dataType:"json",
             		   	 cache:false,
             		   	 async : false,
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
           	  $("#upalert").empty();
           	}
    $(document).ready(function(){
    	$(".use-address").click(function() {
    	    var $row = $(this).closest("tr"); // Find the row
    	    var $text = $row.find("#id").text(); // Find the text
    	    var $textDesc = $row.find("#roleDesc").text(); // Find the text
    	    
    	    // Let's test it out
    	   
    	    $('#usertext').val($textDesc);
    	   $('#categoryID').val($text);
    	    
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
    	$('.close').click(function() {                   
    		
   		 $('#alert').empty();
   		 $('#upalert').empty();
   		 $('#text').val("");
   		 $('#role_error').empty();
   		     	});
    	$('#cancel').click(function() {                   
    		 $('#alert').empty();
   		 $('#upalert').empty();
   		 $('#text').val("");
   		 $('#role_error').empty();
     		     	});
    });
</script>
