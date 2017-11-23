

<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application login page for accessing the application.
Modified Date	:02-10-15
Modified By	    :Prasad
-->

<div id="page-wrapper">
	<!-- navigation start-->
	<div class="col-lg-12 col-md-6 content-top-header">
		<div class="col-lg-3 col-md-6" id="divEMSnavLeft">
			<ul class="nav navbar-top-links navbar-left">
				<li class="dropdown"></li>
				<!-- /.dropdown -->
			</ul>
		</div>
		<div class="col-lg-4 col-md-6 location" id="divEMSnavCenter">
			<ul class="nav navbar-top-links navbar-right">

				<!-- /.dropdown -->
				<li class="dropdown"><a class="location-font"
					data-toggle="dropdown" href="#"> <%=session.getAttribute("location").toString() %>

				</a> <!-- /.dropdown-tasks --></li>


			</ul>
		</div>
		<div class="col-lg-5 col-md-6" id="divEMSnavRight">
			<ul class="nav navbar-top-links navbar-right">

				<!-- /.dropdown -->
				<li class="dropdown"><a class="dropdown-toggle"
					data-toggle="dropdown" href="#"> User:<%=session.getAttribute("userName").toString() %>
				</a> <!-- /.dropdown-alerts --></li>
				<!-- /.dropdown -->
				<li class="dropdown"><a href="logout.htm">Logout</a> <!-- /.dropdown-user -->
				</li>
				<!-- /.dropdown -->
			</ul>
		</div>

		<!-- /.navbar-top-links -->
	</div>
	<!-- navigation end-->
	<div class="row home-ems-text">
		<div class="col-lg-12" style="text-align: center; margin-top: 10%;">
			<%-- <h1>Exception Occures..</h1>
			<h2 th:text="${error}+' Occurred'">We couldn't find what you
				were looking for</h2> --%>
			<h1>SORRY Error Occured</h1>
			<h2 th:text="${error}+' Occurred'">We encountered an error while processing your request. <br>
			Please  <a href="logout.htm">click here</a> to try again</h2>
		</div>
	</div>

</div>
