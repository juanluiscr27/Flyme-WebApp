function myFunction() {
    var x = document.getElementById("nationality");
    var option = document.createElement("option");
    option.value = "KW";
    option.text = "Kiwi";
    x.add(option, x[0]);
}