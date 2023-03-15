/**
*
*/


const SwitchCheck = document.querySelector("#SwitchCheck");
const SwitchCheckLabel = document.querySelector("#SwitchCheckLbl");

// Add a number of days to the current date  
Date.prototype.addDays = function (days) {
    let date = new Date(this.valueOf());
    date.setDate(date.getDate() + days);
    return date;
}

let today = new Date();

const roundTrip = function () {
    $("#daterange").daterangepicker({
        opens: 'center'
    }, function (start, end, label) {
        console.log("Start date: " + start.format('YYYY-MM-DD'));
        console.log("End date: " + end.format('YYYY-MM-DD'));
    });
    $('#daterange').data('daterangepicker').setEndDate(today.addDays(5));
}

const oneWay = function () {
    $('#daterange').daterangepicker({
        singleDatePicker: true,
        showDropdowns: true,
        minYear: 1901,
        maxYear: parseInt(moment().format('YYYY'), 10)
    }, function (start, end, label) {
        var years = moment().diff(start, 'years');
        // console.log(years);
        console.log("Date: " + end.format('YYYY-MM-DD'));
    });
    $('#daterange').data('daterangepicker').setEndDate(today);
}

SwitchCheck.addEventListener("change", (event) => {
    if (event.target.checked) {
        SwitchCheckLabel.textContent = "Roundtrip";
        roundTrip();
    } else {
        SwitchCheckLabel.textContent = "One way";
        oneWay();
    }
});

// Default
oneWay();

