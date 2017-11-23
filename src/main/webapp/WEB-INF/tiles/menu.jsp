
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<script>

loadMenus();
  function loadMenus()
  {
	   $.ajax
	   ({
	   type: "GET",
	   url: "ajaxGetDynamicMenus.htm",
	   dataType:"json",
	   success: function(data)
	   {
			   $.each(data.menus, function(i,data){
			      
				var url="menu"+data.id+".htm";
			   var msg_data="<li><a href='<c:url value='"+url+"?url="+data.url+"'/>'>"+data.desc+"</a></li>";
			  // alert(msg_data);
			   $(msg_data).appendTo("#side-menu");
			  
		   }); 
	   }
	   });
  }
</script>

<script>

$(document).ready(function() {
	
	var i = false;
	
	$('.menu-icon').click(function() {
		
		$('.sidebar').slideToggle(200);

	});	

});

</script>

<div class="menu-icon">
				<div class="bar"> </div>
				<div class="bar"> </div>
				<div class="bar"> </div>
</div>
			

<div class="navbar-default sidebar" role="navigation" >
                <div class="sidebar-nav navbar-collapse">
					<ul class="nav" id="side-menu">
                       

                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>