<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application menu page.
Modified Date	:24-12-15
Modified By	    :Prasad-->

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
 
        <div id="page-wrapper">
               <div class="col-lg-12 col-md-6 content-top-header" >
               		<div class="row ">
             		  <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-left" id="divEMSnavLeft">
                     		  <ul class="nav navbar-top-links navbar-left">
                                 <li class="dropdown">
                                     <h5>  <a href="home.htm">Home</a>/
                                     <a href="menu5.htm?url=homeadmin.htm" >Administration</a>/
                                     <a href="actions.htm?page=AM9&operation=0&status=A" class="left-breadcrumb-last-child">Menus</a> </h5>
                                    
                              </li>
                              </ul>
                     </div>
                     <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-right" id="divEMSnavRight">
                       		 <ul class="nav navbar-top-links navbar-right">
                                 
                                   <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                      <i class="fa fa-user"> </i>  User:<%=session.getAttribute("userName").toString() %>
                                    </a>
                                   </li>
                               
                                <li class="dropdown">
                                    <a href="logout.htm">  <i class="fa fa-sign-out"> </i> Logout</a>
                                </li>
                             </ul>
                     </div>
                  
            <!-- /.navbar-top-links -->
            </div>
              <div class="row">
                <div class="col-lg-12 col-md-12 location" id="divEMSnavCenter">
                   <h2> <center> <%=session.getAttribute("location").toString() %></center> </h2>
                 </div>
             </div>
              </div>
            <div class="clearfix"> </div>
            <div class="row" id="divEMSPageHeaderTitle">
                <div class="col-lg-12">
                    <!-- <name="form1" class="page-header-title">Roles</h1> -->
                    <h1 class="page-header-title"> Menus</h1>
                </div>
                <!-- /.col-lg-12 -->
            </div>           
           <div class=" ">
	   		<div class="col-lg-12 col-md-12">
				<%-- <div class="" style="float:right; width:100%;"> 				
					<form class="form-inline" role="form">
						<fieldset class="scheduler-border">
							<legend class="scheduler-border">Search</legend>
				   		    <div class="form-group status-input-style">
						   		    <div class="form-group status-input-style">
										<div class="sam search-scheduler" style="height:45px;"> </div>					
									</div>	
							</div>
					  	</fieldset>
					</form>
				</div> --%>
				<br>
				  <table class="table table-hover table-striped " id="bootstrap-table">
			        <thead>
			          <tr>
			            <th width="10%">SNo</th>
			            <th width="40%">Menu Name</th>
			            <th width="40%">Page Name</th>
			          </thead>
			      <tbody>
			   <script>
			   $("#bootstrap-table").empty();
    		   var head_data="<thead><tr><th width='20%'>SNo</th><th width='40%'>Menu Name</th><th width='40%'>Page Name</th></thead>";
    		   var head_data1="<thead><tr><th width='20%'>SNo</th><th width='10%'>Menu Name</th><th width='10%'>Page Name</th></thead>";
    		   $(head_data).appendTo("#bootstrap-table");
    		   $(head_data1).appendTo("#bootstrap-table");
    		   var sno=1;
    		   <c:forEach items="${menulist}" var="sno"> 		   
    		   var desc='${sno.description}';
    		   var tempdesc='${sno.tempDescription}';
    		   var msg_data="<tr><td>"+sno+"</td><td id='id'>"+desc+"</td><td id='empname'>"+tempdesc+"</td></tr>";
			  //alert(msg_data);
    		   $(msg_data).appendTo("#bootstrap-table");
    		   sno++;
    		   </c:forEach>
                 $("#bootstrap-table").append("</tbody>");
			   
			   $('#bootstrap-table').dataTable().columnFilter({ sPlaceHolder: "head:after",
		              aoColumns: [
		                          { type: "text" },
		                          { type: "text" },
		                          { type: "text" },
								                                                      
		                          ]
		});
    		   </script>
			      <%-- <c:forEach var="menulist" items="${menulist}" varStatus="sno" > 
			      <tr id="rows"> 
			      <td>${sno.index+1}</td>
			      <td id="menuDesc" >${menulist.description}</td>  
			      <td id="pageDesc">${menulist.tempDescription}</td>  
			      </tr>  
			      </c:forEach> --%> 
			      </tbody>
			   </table>
				<br><br>
			</div>         
		</div>   
	 </div>

 <%-- <script src="<c:url value="resources/js/jquery.bdt.js"/>"></script>--%>
   <!-- <script>
    $(document).ready( function () {
        $('#bootstrap-table').bdt();
    });
</script>   -->
     