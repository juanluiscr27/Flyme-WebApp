class Passenger {
  constructor(
    // Defines parameters:
    seatId, // int
    firstName, // String
    lastName, // String
    dateOfBirth, // LocalDate
    gender, //char
    bags, // bags
  ) {
    // Define properties:
    this.seatId = seatId;
    this.firstName = firstName;
    this.lastName = lastName;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.bags = bags;
  }
}

export default Passenger;
