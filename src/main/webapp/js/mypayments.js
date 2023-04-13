const editButton = document.querySelector('#editButton');
const payment = document.querySelector('#paymentId');
const deleteButton = document.querySelector('#deleteButton');

editButton.addEventListener('click', enableFields);

function enableFields() {
	  document.querySelector("#card-number").disabled = false;
	  document.querySelector("#card-name").disabled = false;
	  document.querySelector("#expiry-date").disabled = false;
	  document.querySelector("#cvc").disabled = false;
	  document.querySelector("#saveButton").disabled = false;
	  editButton.disabled = true;
	  deleteButton.disabled = true;
};

//deleteButton.addEventListener('click', submitDelete);

function submitDelete() {
	if (confirm("Please confirm payment method deletion")) {
		document.querySelector("#deleteAction").value = "1";
		return true;
	}
    else {
        return false;
    }
};

window.addEventListener('load', (event) => {
	if (payment.value == ""){
	  document.querySelector("#card-number").disabled = false;
	  document.querySelector("#card-name").disabled = false;
	  document.querySelector("#expiry-date").disabled = false;
	  document.querySelector("#cvc").disabled = false;
	  document.querySelector("#saveButton").disabled = false;
	  editButton.disabled = true;
	  deleteButton.disabled = true;
	}
});