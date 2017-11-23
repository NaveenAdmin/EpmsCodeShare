<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application Administration page.
Modified Date	:30-10-15
Modified By	    :Prasad
-->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
								<a href="menu5.htm?url=homeadmin.htm" class="left-breadcrumb-last-child">Administration</a>
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
						<li class="dropdown"><a class="dropdown-toggle"
							data-toggle="dropdown" href="#"> <i class="fa fa-user"> </i> User:<%=session.getAttribute("userName").toString() %>
						</a></li>

						<li class="dropdown"><a href="logout.htm"> <i
								class="fa fa-sign-out"> </i> Logout</a></li>
						<!-- /.dropdown -->
					</ul>
				</div>
			</div>
		</div>
		
		<div class="row">
		<div class="col-lg-12 col-md-12 location" id="divEMSnavCenter">
				<h2>
				<center>	<%=session.getAttribute("location").toString() %> </center>
				</h2>
			</div>
		</div>

		<!-- /.navbar-top-links -->
	</div>

	<div class="row page-title-bg" id="divEMSPageTitle">
		<div class="col-lg-12">
			<h1 class="page-header-title">Admin Module</h1>
		</div>
		<!-- /.col-lg-12 -->
	</div>
	<!-- /.row -->
	<div class="">
		<br>
		<div class="col-lg-3 col-md-6">
			<div class="panel panel-primary">
				<div class="panel-heading">
					<div class="row">
						<div class="col-xs-3">
							<i class="fa fa-users fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">${roles}</div>
							<div>Roles</div>
						</div>
					</div>
				</div>
				<a href="<c:url value="actions.htm?page=AM1&operation=0&status=A"/>">
					<div class="panel-footer">
						<span class="pull-left">Roles</span>
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
							<div class="huge">${category}</div>
							<div>Categories</div>
						</div>
					</div>
				</div>
				<a href="<c:url value="actions.htm?page=AM4&operation=0&status=A"/>">
					<div class="panel-footer">
						<span class="pull-left">Category</span>
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
							<i class="fa fa-users fa-5x"></i>
						</div>
						<div class="col-xs-9 text-right">
							<div class="huge">${zone}</div>
							<div>Zones</div>
						</div>
					</div>
				</div>
				<a href="<c:url value="actions.htm?page=AM5&operation=0&status=A"/>">
					<div class="panel-footer">
						<span class="pull-left">Zone</span>
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
							<div class="huge">${usertype}</div>
							<div>UserTypes</div>
						</div>
					</div>
				</div>
				<a href="<c:url value="actions.htm?page=AM6&operation=0&status=A"/>">
					<div class="panel-footer">
						<span class="pull-left">User Type</span>
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
							<div class="huge">${rolepermission}</div>
							<div>Role-Permissions</div>
						</div>
					</div>
				</div>

				<a href="<c:url value="pagepermissions.htm"/>">
					<div class="panel-footer">
						<span class="pull-left">Role-Permissions</span>
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
							<div class="huge">${menu}</div>
							<div>Menus</div>
						</div>
					</div>
				</div>

				<a href="<c:url value="actions.htm?page=AM9&operation=0&status=A"/>">
					<div class="panel-footer">
						<span class="pull-left">Menus</span>
						<div class="clearfix"></div>
					</div>
				</a>
			</div>
		</div>
		
		
		<%--     <div class="col-lg-3 col-md-6">
                    <div class="panel panel-primary">
                        <div class="panel-heading">
                            <div class="row">
                                <div class="col-xs-3">
                                    <i class="fa fa-users fa-5x"></i>
                                </div>
                                <div class="col-xs-9 text-right">
                                    <div class="huge">${locationip}</div>
                                    <div>LocationIP Count</div>
                                </div>
                            </div>
                        </div>
                        
                         <a href="<c:url value="actions.htm?page=AM8&operation=0&status=A"/>">
                             <div class="panel-footer">
                                <span class="pull-left">Location-IP</span>
                                <div class="clearfix"></div>
                            </div>
                        </a>
                    </div>
                </div> --%>
	</div>
	<!-- /.row -->

	<!-- /.row -->
</div>
<!-- /#page-wrapper -->

<div class="clearfix"> </div> <br>