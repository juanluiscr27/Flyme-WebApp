/**
*
*/

const URL = "api/v1/countries";
const emailInput = document.querySelector("#email");
const signUpForm = document.querySelector("#sign-up");
let isEmailAvailable = false;

const setErrorMessage = function(element, message) {
    element.classList.add("alert");
    element.classList.add("alert-dangers");
    element.textContent = message;
}

fetch(URL).then(response => {
    if (response.ok) {
        return response.json();
    } else {
        setErrorMessage(message,"Request unsuccessful");
    }
}).then(data => {
    if (data) {
		var input = document.getElementById("nationality");
		data.filter(country => country.id!="CA");
        data.forEach(country => {
    		let option = document.createElement("option");
        	option.value = country.id;
        	option.text = country.name;
        	input.add(option);
        });
    } else {
        setErrorMessage(message,"<p>Error - No User Found</p>");
    }
});


const getEmailAvailability = function(URL) {
  fetch(URL).then(response => {
    if (response.ok) {
        return response.json();
    } else {
        setErrorMessage(message,"Request unsuccessful");
    }
}).then(email => {
    if (email) {
      console.log(email);
      isEmailAvailable = email.isAvailable;
    }
});
}

emailInput.addEventListener("focusout", (e) =>{
  let url = `api/v1/emails?search=${emailInput.value}`;
  getEmailAvailability(url);
});

signUpForm.addEventListener("submit", (e) =>{
  if(!isEmailAvailable) {
    e.preventDefault();
    alert("Email address is not available");
  }
});