/* Validation Messages*/
/* Declaration start*/
var employee_Id="Please Enter ID.";
var confi_device="Please select Device.";
var group_name="Please Enter Name.";
var group_Id="Please Enter GroupId.";
var eas_group="Please select Group.";
var eas_level="Please select Level.";
var eas_type="Please select Type.";
var admin_page="Please select Page.";
var admin_menu="Please select Menu.";
var admin_role="Please Select Role.";
var admin_loc="Please Select Location.";
var admin_add="Please Enter Name.";
var admin_alphabates="Use Only Alphabets.";  
var loc_address="Please Select Location.";
var admin_AddIpaddress="Please Enter IP Address.";
var admin_Ipaddress="Use Valid IP Address Format.";


var config_Id="Please Enter Name/ID."; 
var config_alphabatesNumaric="Use Alphabets and Numeric values."
	
var device_id="Please Enter Id.";
var device_name="Please Enter Device Name.";
var ip_address="Please Enter IP Address.";
var earthpit_count="Please Enter EarthpitCount.";
var timein_tervel="Please Enter TimeInterval.";
var date_msg="Please Enter Date(yyyy-mm-dd) Format.";
var serial_no="Please Enter Serial No.";

var device_Id="Use only Numeric values."
var eascalation_id="Please Enter  Escalation Id.";
var nextalert="Please Enter next alert time in minutes. ";
var repeatduration="Please Enter repeate duration in minutes.";
var textmsg="Please Enter Text Message.";	
var mail="Please Enter E-Mail Message.";	
var config_numbers="Please Enter Numerics.";
var config_numbersandfloat="Please Enter Numerics or Decimals.";
var admin_alphabatesandspl="Use only Alphabets,Numerics and Special char(/-,).";
var admin_mixed="Use atleast 1 Alphabet & 1 Numeric.";


/* Declaration End*/     

function AlphabetsNumericAndSplchar(element,message)
{
    //var regexp = /^[a-zA-Z 0-9/-,]+$/; 
	  var regexp =/^[ A-Za-z0-9/,-]*$/
	if(element.value.match(regexp))
	{
	return true;
	}else{
		$(message).empty();
		
		$("<p>"+admin_alphabatesandspl+"</p>").appendTo(message);
		element.focus();
		return false;
	}
    
}

//Aleastonecharandnumbers
function MixedAlphabetsNumeric(element,message)
{
    var regexp =/^(\w*(\d+[a-zA-Z]|[a-zA-Z]+\d)\w*)+$/;           
	if(element.value.match(regexp))
	{
	return true;
	}else{
		$(message).empty();
		$("<p>"+admin_mixed+"</p>").appendTo(message);
		element.focus();
		return false;
	}
    
}

/*check empty and alphabates*/
function formValidation(input,message){
	$(message).empty();
	if($(input).val()==""){
				
		$("<p>"+admin_add+"</p>").appendTo(message);
		return false;
	}else{
		
		return NumbersAlphabates(input,message);
	}
	
	
	
}

/*only alphabates*/

function onlyalphabate(element,message){
	
	var regexp = /^[a-zA-Z ]+$/;
	if(element.value.match(regexp))
	{
	return true;
	}else{
		$(message).empty();
		$("<p>"+admin_alphabates+"</p>").appendTo(message);
		
		element.focus();
		return false;
	}
}


/*check empty and Ipaddress*/
function IPformValidation(input,message){
	$(message).empty();
	if($(input).val()==""){
				
		$("<p>"+admin_AddIpaddress+"</p>").appendTo(message);
		return false;
	}else{
		
		return onlyIpaddress(input,message);
	}
	
}

/*only Ipaddress*/
function onlyIpaddress(element,message){
	
	var regexp =  /^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/;
	if(element.value.match(regexp))
	{
	return true;
	}else{
		$(message).empty();
		$("<p>"+admin_Ipaddress+"</p>").appendTo(message);
		
		element.focus();
		return false;
	}
}
/*check empty and NumbersAlphabates*/
function AlphaNumaricformValidation(input,message,role,rolemsg){
	$(message).empty();
	if($(input).val()==""){
				
		$("<p>"+config_Id+"</p>").appendTo(message);
		return false;
		
	 }else if($(role).val()==''){
				
		$("<p>"+admin_role+"</p>").appendTo(rolemsg);
		return false;
	}{
		
		return NumbersAlphabates(input,message);
	}
	
}
/*check empty and NumbersAlphabates*/
function NumbersAlphabates(input,message,role,rolemsg){
	$(message).empty();
	if($(input).val()==""){
				
		$("<p>"+config_Id+"</p>").appendTo(message);
		return false;
		
	 }else if($(role).val()==''){
				
		$("<p>"+admin_role+"</p>").appendTo(rolemsg);
		return false;
	}{
		
		return NumbersAlphabates(input,message);
	}
	
}
/*only numbers and alphabates*/

function NumbersAlphabates(element,message){
	
	var regexp = /^[a-zA-Z 0-9]+$/;  
	if(element.value.match(regexp))
	{
	return true;
	}else{
		$("#alert").empty();
		$("<p>"+config_alphabatesNumaric+"</p>").appendTo(message);
		
		element.focus();
		return false;
	}
}
/*only numbers */

function Numbers(element,message){
	
	var regexp = /^([0-9]+)$/; 
	if(element.value.match(regexp))
	{
	return true;
	}else{
		$(message).empty();
		$("<p>"+config_numbers+"</p>").appendTo(message);
		
		element.focus();
		return false;
	}
}
/*only numbers and decimal */

function NumbersAndDecimal(element,message){
	
	var regexp = /^[0-9]|[-+]?[0-9]+\.[0-9]+$/;   
	if(element.value.match(regexp))
	{
	return true;
	}else{
		$(message).empty();
		$("<p>"+config_numbersandfloat+"</p>").appendTo(message);
		
		element.focus();
		return false;
	}
}

/*only dateformate*/

function DateFormate(element,message){
	
	var regexp = /[0-9]{4}-(0[1-9]|1[0-2])-(0[1-9]|[1-2][0-9]|3[0-1])/;
	if(element.value.match(regexp))
	{
	return true;
	}else{
		
		$(message).empty();
		$("<p>"+date_msg+"</p>").appendTo(message);
		
		element.focus();
		return false;
	}
}
/*For device*/
function DeviceformValidation(deviceid,message,deviceName,devmessage,ipaddress,ipmessage,location,locmessage,earthpitcount,earmessage,timeintervel,timemessage,date,datemessage,serialno,serMmessage){
	$(message).empty();
	$(devmessage).empty();
	$(ipmessage).empty();
	$(locmessage).empty();
	$(earmessage).empty();
	$(timemessage).empty();
	$(datemessage).empty();
	$(serMmessage).empty();
	
	var hint=true;
	var temphint=true;
	if($(deviceid).val()==""){
				
		//$("<p>"+device_id+"</p>").appendTo(message);
		$(deviceid).css('border-color', 'red');
		 hint= false;
		
	}
	if($(deviceName).val()==""){
		
		//$("<p>"+device_name+"</p>").appendTo(devmessage);
		$(deviceName).css('border-color', 'red');
		 hint= false;
	}else
		if(AlphabetsNumericAndSplchar(deviceName,devmessage)!=true){
			$(deviceName).css('border-color', 'red');
			 hint= false;
			 temphint=false;
		}
	
	if($(ipaddress).val()==""){
				
		//$("<p>"+ip_address+"</p>").appendTo(ipmessage);
		$(ipaddress).css('border-color', 'red');
		 hint= false;
	}else
	if(onlyIpaddress(ipaddress,ipmessage)!=true){
		$(ipaddress).css('border-color', 'red');
		 hint= false;
		 temphint=false;
	}
	if($(location).val()==''){
		
		//$("<p>"+loc_address+"</p>").appendTo(locmessage);
		$(location).css('border-color', 'red');
		 hint= false;
	}
	
	if($(earthpitcount).val()==""){
		
		//$("<p>"+earthpit_count+"</p>").appendTo(earmessage);
		$(earthpitcount).css('border-color', 'red');
		 hint= false;
	}else
	if(Numbers(earthpitcount,earmessage)!=true){
		$(earthpitcount).css('border-color', 'red');
		 hint= false;
		 temphint=false;
	}
	
	
     if($(timeintervel).val()==""){
		
		//$("<p>"+timein_tervel+"</p>").appendTo(timemessage);
    	 $(timeintervel).css('border-color', 'red');
		 hint= false;
	}else
	if(Numbers(timeintervel,timemessage)!=true){
		$(timeintervel).css('border-color', 'red');
		 hint= false;
		 temphint=false;
	}
	
	
   if($(date).val()==""){
		
		//$("<p>"+date_msg+"</p>").appendTo(datemessage);
	   $(date).css('border-color', 'red');
		 hint= false;
	}else
	if(DateFormate(date,datemessage)!=true){
		$(date).css('border-color', 'red');
		 hint= false;
		 temphint=false;
	}
	
if($(serialno).val()==""){
		
		//$("<p>"+serial_no+"</p>").appendTo(serMmessage);
	$(serialno).css('border-color', 'red');
	 hint= false;
	}else
	if(NumbersAlphabates(serialno,serMmessage)!=true){
		$(NumbersAlphabates).css('border-color', 'red');
		 hint= false;
		 temphint=false;
		}
	
	
	if(hint){
		return true;
	}else{
		if(temphint)
		 $("<p>( * )  Mandatory</p>").appendTo(serMmessage);
	}
	
	
}




function UpDeviceformValidation(deviceid,message,deviceName,devmessage,ipaddress,ipmessage,location,locmessage,earthpitcount,earmessage,timeintervel,timemessage,date,datemessage,serialno,serMmessage){
	
	$(message).empty();
	$(devmessage).empty();
	$(ipmessage).empty();
	$(locmessage).empty();
	$(earmessage).empty();
	$(timemessage).empty();
	$(datemessage).empty();
	$(serMmessage).empty();
	var hint=true;
	var temphint=true;
	if($(deviceid).val()==""){
				
		//$("<p>"+device_id+"</p>").appendTo(message);
		$(deviceid).css('border-color', 'red');
		 hint= false;
	}
   if($(deviceName).val()==""){
		
		//$("<p>"+device_name+"</p>").appendTo(devmessage);
		$(deviceName).css('border-color', 'red');
		 hint= false;
	}else
		if(AlphabetsNumericAndSplchar(deviceName,devmessage)!=true){
			$(deviceName).css('border-color', 'red');
			 hint= false;
			 temphint=false;
		}
	
	if($(ipaddress).val()==""){
				
		//$("<p>"+ip_address+"</p>").appendTo(ipmessage);
		$(ipaddress).css('border-color', 'red');
		 hint= false;
	}else
	if(onlyIpaddress(ipaddress,ipmessage)!=true){
		$(ipaddress).css('border-color', 'red');
		 hint= false;
		 temphint=false;
	}
	if($(location).val()==''){
		
		$(location).css('border-color', 'red');
		 hint= false;
	}
	
	if($(earthpitcount).val()==""){
		
		$(earthpitcount).css('border-color', 'red');
		 hint= false;
	}else
	if(Numbers(earthpitcount,earmessage)!=true){
		$(earthpitcount).css('border-color', 'red');
		 hint= false;
		 temphint=false;
	}
	
	
     if($(timeintervel).val()==""){
		
    	 $(timeintervel).css('border-color', 'red');
		 hint= false;
	}else
	if(Numbers(timeintervel,timemessage)!=true){
		$(timeintervel).css('border-color', 'red');
		 hint= false;
		 temphint=false;
	}
	
	
   if($(date).val()==""){
	   $(date).css('border-color', 'red');
		 hint= false;
	}else
	if(DateFormate(date,datemessage)!=true){
		$(date).css('border-color', 'red');
		 hint= false;
		 temphint=false;
	}
	
if($(serialno).val()==""){
		
	$(serialno).css('border-color', 'red');
	 hint= false;
	}else
	if(NumbersAlphabates(serialno,serMmessage)!=true){
		
		$(serialno).css('border-color', 'red');
		 hint= false;
		 temphint=false;
	}
if(hint){
	return true;
}else{
	if(temphint)
	$("<p>( * )  Mandatory</p>").appendTo("#upsnoalert");
}
	
}
/*For Esacalation*/
function EsacalationformValidation(esacalationid,message,type,typemsg,level,levelmsg,group,groupmsg,text,textmessage,email,emailmessage,next,nextmessage,repeatation,repeatemessage){
	$(message).empty();
	$(typemsg).empty();
	$(levelmsg).empty();
	$(groupmsg).empty();
	$(textmessage).empty();
	$(emailmessage).empty();
	$(nextmessage).empty();
	$(repeatemessage).empty();
	
	var hint=true;
	var temphint=true;
	if($(esacalationid).val()==""){
				
		//$("<p>"+eascalation_id+"</p>").appendTo(message);
		$(esacalationid).css('border-color', 'red');
		 hint= false;
	}else
	if(MixedAlphabetsNumeric(esacalationid,message)!=true){
		$(esacalationid).css('border-color', 'red');
		hint= false;
		temphint=false;
	}
	
   if($(type).val()==''){
	   $(type).css('border-color', 'red');
		//$("<p>"+eas_type+"</p>").appendTo(typemsg);
		hint= false;
	}
   
    if($(level).val()==''){
    	$(level).css('border-color', 'red');
		//$("<p>"+eas_level+"</p>").appendTo(levelmsg);
		hint= false;
	}
    
     if($(group).val()==''){
    	 $(group).css('border-color', 'red');
		//$("<p>"+eas_group+"</p>").appendTo(groupmsg);
		hint= false;
	}
     
	if($(text).val()==""){
		$(text).css('border-color', 'red');
		//$("<p>"+textmsg+"</p>").appendTo(textmessage);
		hint= false;
		temphint=false;
	}else
	/*if(NumbersAlphabates(text,textmessage)!=true){
		$(text).css('border-color', 'red');
		hint= false;
		temphint=false;
	}*/
	
	if($(email).val()==""){
		$(email).css('border-color', 'red');
		//$("<p>"+mail+"</p>").appendTo(emailmessage);
		hint= false;
		temphint=false;
	}else
	/*if(NumbersAlphabates(email,emailmessage)!=true){
		$(email).css('border-color', 'red');
		hint= false;
		temphint=false;
	}*/
	
     if($(next).val()==""){
    	 $(next).css('border-color', 'red');
		//$("<p>"+nextalert+"</p>").appendTo(nextmessage);
		hint= false;
		temphint=false;
	}else
	if(NumbersAndDecimal(next,nextmessage)!=true){
		 $(next).css('border-color', 'red');
		hint= false;
		temphint=false;
	}
	
	
   if($(repeatation).val()==""){
	   $(repeatation).css('border-color', 'red');
		//$("<p>"+repeatduration+"</p>").appendTo(repeatemessage);
		hint= false;
		temphint=false;
	}else
	if(Numbers(repeatation,repeatemessage)!=true){
		$(repeatation).css('border-color', 'red');
		hint= false;
		temphint=false;
	}
	
 if(hint)
	return true;
 else {
	 if(temphint)
	 $("<p>( * )  Mandatory</p>").appendTo(repeatemessage);
	 return false;	 
 }
}



function upEsacalationformValidation(text,textmessage,email,emailmessage,nextalet,nextmessage,repeatation,repeatemessage){
	
	$(textmessage).empty();
	$(emailmessage).empty();
	$(nextmessage).empty();
	$(repeatemessage).empty();
	
	
	if($(text).val()==""){
				
		$("<p>"+textmsg+"</p>").appendTo(textmessage);
		return false;
	}
	if(NumbersAlphabates(text,textmessage)!=true){
		return false;
	}
	
	if($(email).val()==""){
		
		$("<p>"+mail+"</p>").appendTo(emailmessage);
		return false;
	}
	if(NumbersAlphabates(email,emailmessage)!=true){
		return false;
	}
	
	
     if($(nextalet).val()==""){
		
		$("<p>"+nextalert+"</p>").appendTo(nextmessage);
		return false;
	}
	if(Numbers(nextalet,nextmessage)!=true){
		return false;
	}
	
	
   if($(repeatation).val()==""){
		
		$("<p>"+repeatduration+"</p>").appendTo(repeatemessage);
		return false;
	}
	if(Numbers(repeatation,repeatemessage)!=true){
		return false;
	}
	
 
	return true;
	
}

//pageRole validations
function roleformValidation(input,message,menu,menumsg,page,pagemsg){
	$(message).empty();
	$(menumsg).empty();
	
	if($(input).val()==""){
	$("<p>"+admin_role+"</p>").appendTo(message);
	
	 return false;
	}else if($(menu).val()==""){
		$("<p>"+admin_menu+"</p>").appendTo(menumsg);
		return false;
	}else if($(page).val()==""){
		$("<p>"+admin_page+"</p>").appendTo(pagemsg);
		return false;
	}else{
		return true;
	}
	
}

//Device earthpit validations
function DeviceEarthpitformValidation(input,message,menu,menumsg){

	$(message).empty();
	$(menumsg).empty();
	
	if($(input).val()==""){
	$("<p>"+admin_loc+"</p>").appendTo(message);
	
	 return false;
	}else if($(menu).val()=="0"){
		$("<p>"+confi_device+"</p>").appendTo(menumsg);
		return false;
	}else{
		return true;
	}
	
}


//EARTHPITS   
function earthformValidation(input,message,location,locmessage){

	$(message).empty();
	$(locmessage).empty();
	
	
   if($(location).val()=='0'){
		
		$("<p>"+loc_address+"</p>").appendTo(locmessage);
		return false;
	}else if($(input).val()==""){
				
		$("<p>"+admin_add+"</p>").appendTo(message);
		return false;
	}else{
	  
		return AlphabetsNumericAndSplchar(input,message);
		 
	}
	
	
}

//Employee   
function employeeformValidation(input,message,roles,rolemsg){
	$(message).empty();
	$(rolemsg).empty();
	
	if($(input).val()==""){
		
		$("<p>"+employee_Id+"</p>").appendTo(message);
		return false;
	}
	if(NumbersAlphabates(input,message)!=true){
		return false;
	}
	if($(roles).val()==''){
	
		$("<p>"+admin_role+"</p>").appendTo(rolemsg);
		return false;
	}
	return true;
}
//grouppage validation
function groupformValidation(input,message,name,namemessage,location,locmsg,level,levelmsg){
	$(message).empty();
	$(namemessage).empty();
	$(locmsg).empty();
	$(levelmsg).empty();
	
	var hint=true;
	var temphint=true;
	
	if($(input).val()==""){
		
		//$("<p>"+group_Id+"</p>").appendTo(message);
		$(input).css('border-color', 'red');
		 hint= false;
		
	}else
	if(MixedAlphabetsNumeric(input,message)!=true){
		$(input).css('border-color', 'red');
		hint= false;
		temphint=false;
	}
	
   if($(name).val()==""){
		
		//$("<p>"+group_name+"</p>").appendTo(namemessage);
	   $(name).css('border-color', 'red');
		 hint= false;
	}else
	if(NumbersAlphabates(name,namemessage)!=true){
		$(name).css('border-color', 'red');
		hint= false;
		temphint=false;
	}
  
  if($(location).val()==""){
	//$("<p>"+loc_address+"</p>").appendTo(locmsg);
	
	  $(location).css('border-color', 'red');
		 hint= false;
	}
		
    if($(level).val()==""){
	//$("<p>"+eas_level+"</p>").appendTo(levelmsg);
		
    	$(level).css('border-color', 'red');
		 hint= false;
		
		}
    if(hint)
    	return true;
     else {
    	 if(temphint)
    	 $("<p>( * )  Mandatory</p>").appendTo(levelmsg);
    	 return false;	 
     }
    }

function upgroupformValidation(location,locmsg,level,levelmsg){
	
	
	$(locmsg).empty();
	$(levelmsg).empty();
	
	
  
  if($(location).val()=="0"){
	$("<p>"+loc_address+"</p>").appendTo(locmsg);
	
	 return false;
	}else 
		
    if($(level).val()==""){
	$("<p>"+eas_level+"</p>").appendTo(levelmsg);
		
		 return false;
		}
		
	return true;
}

function droupdownValidate()
{
var e = document.getElementById("ddlView");
var strUser = e.options[e.selectedIndex].value;
//if you need text to be compared then use
var strUser1 = e.options[e.selectedIndex].text;
if(strUser==0) //for text use if(strUser1=="Select")
{
alert("Please select a user");
}
}













































 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 








