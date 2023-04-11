const button = document.querySelector('#editButton');

button.addEventListener('click', enableFields);

function enableFields() {
	  document.querySelector("#card-number").disabled = false;
	  document.querySelector("#card-name").disabled = false;
	  document.querySelector("#expiry-date").disabled = false;
	  document.querySelector("#cvc").disabled = false;
	  document.querySelector("#saveButton").disabled = false;
};