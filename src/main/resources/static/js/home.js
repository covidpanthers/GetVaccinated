var eligibilityModal = document.getElementById("eligibilityModal");
var warningModal = document.getElementById("warningModal");

function showEligibilityModal() {
  eligibilityModal.style.display = "block";
}

function showWarning() {
  warningModal.style.display = "block";
}

window.onclick = function(event) {
  if (event.target == eligibilityModal || event.target == warningModal) {
    eligibilityModal.style.display = "none";
    warningModal.style.display = "none";
  }
}