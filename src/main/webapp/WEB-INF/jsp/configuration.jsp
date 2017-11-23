<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application configuration page.
Modified Date	:30-10-15
Modified By	    :Prasad
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="page-wrapper">
           <div class="col-lg-12 col-md-12 content-top-header" >
				<div class="row">
             		<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12 breadcrumbs-left"
					id="divEMSnavLeft">
					<div class="row">
						<ul class="nav navbar-top-links navbar-left">
								<li class="dropdown">
								<h5>
								<a href="home.htm">Home</a>/
								<a href="menu4.htm?url=configuration.htm" class="left-breadcrumb-last-child">Configuration</a>
								</h5> <!-- /.dropdown-messages -->
						</li>
						<!-- /.dropdown -->
					</ul>
				</div>
			</div>
                     <div class="col-lg-6 col-md-6" id="divEMSnavRight">
                      		<div class="row">
                      		 <ul class="nav navbar-top-links pull-right ">
                               <!-- /.dropdown -->
                               <li class="dropdown">
                                   <a class="dropdown-toggle " data-toggle="dropdown" href="#">
                                       <i class="fa fa-user"> </i> User:<%=session.getAttribute("userName").toString() %>
                                   </a>
                                </li>
                           
                               <li class="dropdown">
                                 <a href="logout.htm" class=""> <i
							class="fa fa-sign-out"> </i>  Logout</a>
                               </li>
                               <!-- /.dropdown -->
                           </ul>
                          </div>
                      </div>
                  </div>
                  
                <div class="row">
                <div class="col-lg-12 col-md-12 location" id="divEMSnavCenter">
                     		<h2> <center> <%=session.getAttribute("location").toString() %> </center> </h2>
                     </div>
                </div>
                 
           <!-- /.navbar-top-links -->
       </div>
           <div class="clearfix"> </div>
          <div class="row page-title-bg">
               <div class="col-lg-12">
                   <h1 class="page-header-title">Configuration Module</h1>
               </div>
               <!-- /.col-lg-12 -->
           </div>
           <!-- /.row -->
           <div class="">  <br>
               <div class="col-lg-3 col-md-6">
                   <div class="panel panel-primary">
                       <div class="panel-heading">
                           <div class="row">
                               <div class="col-xs-3">
                                   <i class="fa fa-user fa-5x"></i>
                               </div>
                                  <div class="col-xs-9 text-right">
                                   <div class="huge">${employe}</div>
                                   <div>Employee</div>
                                   
                               </div>
                           </div>
                       </div>
                          <a href="<c:url value="configactions.htm?page=CP1&operation=0"/>">
                           <div class="panel-footer">
                               <span class="pull-left">Employee</span>
                               <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                               <div class="clearfix"></div>
                           </div>
                       </a>
                   </div>
               </div>
               <div class="col-lg-3 col-md-6">
                   <div class="panel panel-primary">
                       <div class="panel-heading">
                           <div class="row">
                               <div class="col-xs-3">
                                   <i class="fa fa-globe fa-5x"></i>
                               </div>
                               <div class="col-xs-9 text-right">
                                   <div class="huge">${locations}</div>
                                   <div>Locations</div>
                                   
                               </div>
                           </div>
                       </div>
                        <a href="<c:url value="configactions.htm?page=CP2&operation=0"/>">
                           <div class="panel-footer">
                               <span class="pull-left">Locations</span>
                               <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                               <div class="clearfix"></div>
                           </div>
                       </a>
                   </div>
               </div>
               <div class="col-lg-3 col-md-6">
                   <div class="panel panel-primary">
                       <div class="panel-heading">
                          <div class="row">
                               <div class="col-xs-3">
                                   <i class="fa fa-tablet fa-5x"></i>
                               </div>
                               <div class="col-xs-9 text-right">
                                   <div class="huge">${device}</div>
                                   <div>Devices</div>
                                   
                               </div>
                           </div>
                       </div>
                       
                        <a href="<c:url value="configactions.htm?page=CP3&operation=0"/>">
                           <div class="panel-footer">
                               <span class="pull-left">Device</span>
                               <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                               <div class="clearfix"></div>
                           </div>
                       </a>
                   </div>
               </div>
               <div class="col-lg-3 col-md-6">
                   <div class="panel panel-primary">
                       <div class="panel-heading">
                          <div class="row">
                               <div class="col-xs-3">
                                   <i class="fa fa-bolt fa-5x"></i>
                               </div>
                               <div class="col-xs-9 text-right">
                                   <div class="huge">${earthpit}</div>
                                   <div>Earthpits</div>
                               </div>
                           </div>
                       </div>
                      <a href="<c:url value="configactions.htm?page=CP4&operation=0&status=A"/>">
                           <div class="panel-footer">
                               <span class="pull-left">Earthpits</span>
                               <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                               <div class="clearfix"></div>
                           </div>
                       </a>
                   </div>
               </div>
           </div>
           <div class="">
               <div class="col-lg-3 col-md-6">
                   <div class="panel panel-primary">
                       <div class="panel-heading">
                           <div class="row">
                               <div class="col-xs-3">
                                   <i class="fa fa-sitemap fa-5x"></i>
                               </div>
                               <div class="col-xs-9 text-right">
                                   <div class="huge">${escalation}</div>
                                   <div>Escalations</div>
                               </div>
                           </div>
                       </div>
                        <a href="<c:url value="configactions.htm?page=CP5"/>">
                           <div class="panel-footer">
                               <span class="pull-left">Escalation</span>
                               <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                               <div class="clearfix"></div>
                           </div>
                       </a>
                   </div>
               </div>
               <div class="col-lg-3 col-md-6">
                   <div class="panel panel-primary">
                       <div class="panel-heading">
                          <div class="row">
                               <div class="col-xs-3">
                                   <i class="fa fa-empire fa-5x"></i>
                               </div>
                               <div class="col-xs-9 text-right">
                                   <div class="huge">${deviearth}</div>
                                   <div>Devices-Earthpits</div>
                               </div>
                           </div>
                       </div>
                     <a href="<c:url value="configactions.htm?page=CP6"/>">
                           <div class="panel-footer">
                               <span class="pull-left">Devices Earthpit</span>
                               <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                               <div class="clearfix"></div>
                           </div>
                       </a>
                   </div>
               </div>
                   <div class="col-lg-3 col-md-6">
                   <div class="panel panel-primary">
                       <div class="panel-heading">
                           <div class="row">
                               <div class="col-xs-3">
                                   <i class="fa fa-users fa-5x"></i>
                               </div>
                               <div class="col-xs-9 text-right">
                                   <div class="huge">${group}</div>
                                  <div>Groups</div>
                                   
                               </div>
                           </div>
                       </div>
                        <a href="<c:url value="configactions.htm?page=CP7&operation=0"/>">
                           <div class="panel-footer">
                               <span class="pull-left">Groups</span>
                               <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                               <div class="clearfix"></div>
                           </div>
                       </a>
                   </div>
               </div> 
          </div>
       </div>
       
       <div class="clearfix"></div> <br>
      
