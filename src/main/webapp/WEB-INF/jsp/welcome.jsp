<!--
Author		    :Kshamashree
creation Date	:30-09-2015
Descripition	:HPCL EMS application device page.
Modified Date	:28-12-15
Modified By	    :Prasad-->

<div id="page-wrapper">
		 <!-- navigation start-->
		<div class="col-lg-12 col-md-12 content-top-header" >
				<div class="row">
             		 <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" id="divEMSnavLeft">
                     		  <ul class="nav navbar-top-links navbar-left">
                                <li class="dropdown">
                               </li> 
                                <!-- /.dropdown -->
                              </ul>
                     </div>
                      
                      
                       <div class="col-lg-6 col-md-6 col-sm-6 col-xs-12" id="divEMSnavRight">
                       		<div class="row">
                       		 <ul class="nav navbar-top-links pull-right ">
                        
                                <!-- /.dropdown -->
                                <li class="dropdown">
                                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                       <i class="fa fa-user"> </i>  User:<%=session.getAttribute("userName").toString() %>
                                    </a>
                                    
                                    <!-- /.dropdown-alerts -->
                                </li>
                                <!-- /.dropdown -->
                                <li class="dropdown">
                                  <a href="logout.htm"> <i class="fa fa-sign-out"> </i>Logout</a>
                                       
                                   <!-- /.dropdown-user -->
                                </li>
                                <!-- /.dropdown -->
                            </ul>
                           </div>
                       </div>
                   </div>
                   
                 <div class="row">
                   <div class="col-lg-12 col-md-12 location" id="divEMSnavCenter">
                      		<h3> <%=session.getAttribute("location").toString() %> </h3>
                      </div>
                 <div class="row">
                  
            <!-- /.navbar-top-links -->
        </div>
         <!-- navigation end-->
        <div class="home-ems-text">
                 <div class="col-lg-12" style="text-align:center;margin-top:10%;">
                    <h1>Welcome <br> to<br> <br> <span>Earth Pit Monitoring System<br>(EMS)</span></h1>
                 </div>
        </div>
           
	</div>
	</div>
	</div>
	<script>
function maximize() {
  window.moveTo(0, 0);
  window.resizeTo(screen.width, screen.height);
}
maximize();
</script>
