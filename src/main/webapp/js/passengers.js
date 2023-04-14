import Passenger from "./passenger.js";

const passengersForm = document.querySelector("#passengers-form");
const passengersDOM = document.querySelectorAll(".passenger");
const passengers = document.querySelector("#passengers");


passengersForm.addEventListener("submit", (e) =>{
  let passengersList = [];
  passengersDOM.forEach((passengerDOM) => {
    const firstName = passengerDOM.querySelector(".first-name").value;
    const lastName = passengerDOM.querySelector(".last-name").value;
    const dateOfBirth = passengerDOM.querySelector(".date-of-birth").value; // LocalDate
    const gender = passengerDOM.querySelector(".gender").value; //char
    const bags = Number(passengerDOM.querySelector(".bags").value);
    const passenger = new Passenger(
      0,
      firstName,
      lastName,
      dateOfBirth,
      gender,
      bags
    );
    passengersList.push(passenger);
  });
  console.log(JSON.stringify(passengersList))
  passengers.value = JSON.stringify(passengersList);
});