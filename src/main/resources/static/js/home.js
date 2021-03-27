function showModal(o, n) {

	// hide previous modal
	var o = document.getElementById(o);
	if(o != null) {
		o.style.display="none";
	}
	
	// open new modal
	var n = document.getElementById(n);
	n.style.display="block";
	document.getElementById("covidForm").reset();
} 

function check() {

   covidCheck.style.display="block";

   var positiveTest = document.getElementById("y1");
   var contact = document.getElementById("y2");
   var symptoms = document.getElementById("y3");
      
   if(positiveTest.checked || contact.checked || symptoms.checked) {
      document.getElementById("errorMsg").innerHTML = "We are unable to schedule a vaccine for you at this time due to your potential risk for spreading COVID-19. Please contact your primary care provider to discuss vaccine options available to you.";
   	  return false;
   } else if (!positiveTest.checked && !contact.checked && !symptoms.checked){
   	  document.getElementById("errorMsg").style.display = "none";
   	  return true;
   }  
}

window.onclick = function(event) {
  if (event.target == covidCheck) {
      covidCheck.style.display = "none";
      document.getElementById("errorMsg").style.display = "none";
  }
}