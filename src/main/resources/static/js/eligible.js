function showModal(o, n) {

	// hide previous modal
	var o = document.getElementById(o);
	if(o != null) {
		o.style.display="none";
	}
	
	// open new modal
	var n = document.getElementById(n);
	n.style.display="block";
} 

window.onclick = function(event) {
  if (event.target == warningModal) {
      warningModal.style.display = "none";
  }
}