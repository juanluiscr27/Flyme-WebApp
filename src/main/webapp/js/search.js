/**
*
*/

const SwitchCheck = document.querySelector("#SwitchCheck");
const SwitchCheckLabel = document.querySelector("#SwitchCheckLbl");
const departureDate = document.querySelector("#departure");
const returnDate = document.querySelector("#return");
const URL = "api/v1/airports";

fetch(URL).then(response => {
    if (response.ok) {
        return response.json();
    } else {
        setErrorMessage(message,"Request unsuccessful");
    }
}).then(data => {
    if (data) {
		  const listFrom = document.getElementById("fromOptions");
        data.forEach(airport => {
    		let option = document.createElement("option");
        	// option.value = airport.city;
          option.value = airport.airportId;
          option.innerHTML = `${airport.city} (${airport.airportId})`;
        	listFrom.appendChild(option);
        });
      const listTo = document.getElementById("toOptions");
        data.forEach(airport => {
    		  let option = document.createElement("option");
          option.value = airport.airportId;
          option.innerHTML = `${airport.city} (${airport.airportId})`;
        	listTo.appendChild(option);
        });
    } else {
        setErrorMessage(message,"<p>Error - No User Found</p>");
    }
});

// Add a number of days to the current date  
Date.prototype.addDays = function (days) {
    let date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}

let today = new Date();

const roundTrip = function () {
  $("#daterange").daterangepicker({
    autoUpdateInput: false,
      locale: {
          cancelLabel: 'Clear'
      }
  });

  $("#daterange").on('apply.daterangepicker', function(ev, picker) {
    $(this).val(picker.startDate.format('YYYY-MM-DD') + ' to ' + picker.endDate.format('YYYY-MM-DD'));
    // console.log("Date: " + picker.endDate.format('YYYY-MM-DD'));
    //console.log("Date: " + picker.endDate.format('YYYY-MM-DD'));
    //console.log("Date: " + picker.startDate.format('YYYY-MM-DD'));
    departureDate.value = picker.startDate.format('YYYY-MM-DD');
    returnDate.value = picker.endDate.format('YYYY-MM-DD');
  });

  $("#daterange").on('cancel.daterangepicker', function(ev, picker) {
      $(this).val('');
  });
}

const oneWay = function () {
  $("#daterange").daterangepicker({
    singleDatePicker: true,
    showDropdowns: true,
    minYear: 1901,
    maxYear: parseInt(moment().format('YYYY'),10),
    autoUpdateInput: false,
      locale: {
          cancelLabel: 'Clear'
      }
  });
  $("#daterange").on('apply.daterangepicker', function(ev, picker) {
    $(this).val(picker.startDate.format('YYYY-MM-DD'));
    // console.log("Date: " + picker.endDate.format('YYYY-MM-DD'));
    departureDate.value = picker.endDate.format('YYYY-MM-DD');
  });

  $("#daterange").on('cancel.daterangepicker', function(ev, picker) {
      $(this).val('');
  });
}

SwitchCheck.addEventListener("change", (event) => {
    if (event.target.checked) {
        SwitchCheckLabel.textContent = "Roundtrip";
        document.querySelector("#dateLabel").innerHTML = "Travel dates"
        roundTrip();
    } else {
        SwitchCheckLabel.textContent = "One way";
        document.querySelector("#dateLabel").innerHTML = "Travel date"
        oneWay();
    }
});

// Default
oneWay();

