/** JavaScript for SignUp Page */

//Create references to the dropdown's
const yearSelect = document.getElementById("year");
const monthSelect = document.getElementById("month");
const daySelect = document.getElementById("day");

const months = ['January', 'February', 'March', 'April', 
'May', 'June', 'July', 'August', 'September', 'October',
'November', 'December'];

//Months are always the same
(function populateMonths(){
    for(let i = 0; i < months.length; i++){
        const option = document.createElement('option');
        option.textContent = months[i];
        option.value = i + 1;
        monthSelect.appendChild(option);
    }
    //monthSelect.value = "January";
})();

let previousDay;

function populateDays(month){
    //Delete all of the children of the day dropdown
    //if they do exist
    while(daySelect.firstChild){
        daySelect.removeChild(daySelect.firstChild);
    }
    //Holds the number of days in the month
    let dayNum;
    //Get the current year
    let year = yearSelect.value;

    if(month === 'January' || month === 'March' || 
    month === 'May' || month === 'July' || month === 'August' 
    || month === 'October' || month === 'December') {
        dayNum = 31;
    } else if(month === 'April' || month === 'June' 
    || month === 'September' || month === 'November') {
        dayNum = 30;
    }else{
        //Check for a leap year
        if(new Date(year, 1, 29).getMonth() === 1){
            dayNum = 29;
        }else{
            dayNum = 28;
        }
    }
    //Insert the correct days into the day <select>
    for(let i = 1; i <= dayNum; i++){
        const option = document.createElement("option");
        option.textContent = i;
        option.value = i;
        daySelect.appendChild(option);
    }
    if(previousDay){
        daySelect.value = previousDay;
        if(daySelect.value === ""){
            daySelect.value = previousDay - 1;
        }
        if(daySelect.value === ""){
            daySelect.value = previousDay - 2;
        }
        if(daySelect.value === ""){
            daySelect.value = previousDay - 3;
        }
    }
}

function populateYears(){
    //Get the current year as a number
    let year = new Date().getFullYear();
    //Make the previous 100 years be an option
    for(let i = 0; i < 101; i++){
        const option = document.createElement("option");
        option.textContent = year - i;
        option.value = year - i;
        yearSelect.appendChild(option);
    }
}

// Assign selected date to the date of birth input field
const setDateOfBirth = function() {
  let year =  yearSelect.value;
  let month = monthSelect.value;
  let day = daySelect.value;

  let date = new Date(`${year}-${month}-${day}`);

  let dateOfBirth = document.querySelector("#date-of-birth");
  dateOfBirth.value = date.toISOString().split('T')[0];
};

populateDays(monthSelect.value);
populateYears();

yearSelect.onchange = function() {
    populateDays(monthSelect.value);
    setDateOfBirth(); 
}
monthSelect.onchange = function() {
    populateDays(monthSelect.value);
    setDateOfBirth(); 
}
daySelect.onchange = function() {
    previousDay = daySelect.value;
    setDateOfBirth();
}

const setChangeEventListener = function(element) {
  element.addEventListener("change", (event) => {
    setDateOfBirth(); 
  });
};

window.addEventListener("load", (event) => {
  setDateOfBirth();
});