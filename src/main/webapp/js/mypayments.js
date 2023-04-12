const button = document.querySelector('#editButton');
const payment = document.querySelector('#paymentId');

button.addEventListener('click', enableFields);

function enableFields() {
	  document.querySelector("#card-number").disabled = false;
	  document.querySelector("#card-name").disabled = false;
	  document.querySelector("#expiry-date").disabled = false;
	  document.querySelector("#cvc").disabled = false;
	  document.querySelector("#saveButton").disabled = false;
	  button.disabled = true;
};

window.addEventListener('load', (event) => {
	if (payment.value == ""){
	  document.querySelector("#card-number").disabled = false;
	  document.querySelector("#card-name").disabled = false;
	  document.querySelector("#expiry-date").disabled = false;
	  document.querySelector("#cvc").disabled = false;
	  document.querySelector("#saveButton").disabled = false;
	  button.disabled = true;
	}
});