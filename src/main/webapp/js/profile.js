const button = document.querySelector('#editButton');

button.addEventListener('click', enableFields);

function enableFields() {
	  document.querySelector("#phone-number").disabled = false;
	  document.querySelector("#password").disabled = false;
	  document.querySelector("#confirm-password").disabled = false;
	  document.querySelector("#saveButton").disabled = false;
};