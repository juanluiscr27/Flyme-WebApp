import Passenger from "./passenger.js";

const seatsForm = document.querySelector("#seats");
const seatsDOM = document.querySelectorAll(".select");
const passengersDOM = document.querySelector("#passengers");

seatsDOM.forEach((select) => {
  select.addEventListener("change", (e) =>{
    console.log(e.target.value);
  });
});


const findDuplicates = (array) => array.filter(
  (item, index) => array.indexOf(item) !== index
);

function isRepeated(selects) {
  let seatsArray = [];
  selects.forEach((select) => {
    seatsArray.push(select.value);
  });

  return findDuplicates(seatsArray).length > 0;
}

function isNotSelected(selects) {
  let seatsArray = [];
  selects.forEach((select) => {
    seatsArray.push(select.value);
  });

  return seatsArray.filter((value) => value == "select a seat").length > 0
}

seatsForm.addEventListener("submit", (e) =>{
  if(isRepeated(seatsDOM) || isNotSelected(seatsDOM)) {
	alert("Check seats selection");
    e.preventDefault();
    return;
  }

  let passengersList = JSON.parse(passengersDOM.value);

  for (const [i, seat] of seatsDOM.entries()) {
    passengersList[i].seatId = seat.value;
  }

  passengersDOM.value = JSON.stringify(passengersList);
  passengers.value = JSON.stringify(passengersList);
});