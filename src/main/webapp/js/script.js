/**
*
*/
const getInputDate = function(input) {
    const inputDate = document.querySelector(input);
    return new Date(inputDate.value); 
}

const isValidDepartDate = function() {
    let departDate = getInputDate("input[name=depart]");
    let today = new Date();
    return departDate.getDate() >= today.getDate() - 1;
 }

 const isValidReturnDate = function() {
    let returnDate = getInputDate("input[name=return]");
    let departDate = getInputDate("input[name=depart]");
    return returnDate > departDate
 }

const isRoundTrip = function() {
    const roundChk = document.querySelector("input[name=round]");
    return roundChk.checked;
}

function dateValidation () {
    if (!isValidDepartDate()) {
        alert('Please choose a valid departure date');
        return false;
    }
    if (isRoundTrip() && !isValidReturnDate()) {
        alert('Please choose a valid return date');
        return false;
    }
    return true;
}

function FormValidation() {

    let origin = document.querySelector("select[name=from]");
    let destination = document.querySelector("select[name=to]");


    if(origin.value == "") {
        alert('Please select a origin');
        return false;
    }
    if(isRoundTrip() && destination.value == "") {
        alert('Please select a destination');
        return false;
    }
    if(origin.value == destination.value) {
        alert('Origin and destination cannot be the same');
        return false;
    }
    
    return true
}

document.querySelector("form").onsubmit = function() {
    if (!(dateValidation())){
        return false;
    }
    return FormValidation();
};

document.querySelector("input[name=round]").addEventListener("click", function(){
    const inputDepartDate = document.querySelector("input[name=return]");
    if(!isRoundTrip()) {
        inputDepartDate.value = "";
        inputDepartDate.disabled = true;
    } else {
        inputDepartDate.disabled = false;
    }
});