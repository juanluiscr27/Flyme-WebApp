/**
*
*/

const message = document.querySelector("#message");
const userContainer = document.querySelector("#users");

const URL = "../api/v1/countries";

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
    if (data)  {
		var input = document.getElementById("nationality");
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