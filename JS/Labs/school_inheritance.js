/* *****************************************************************************************************************
 *        Name: school_inheritance.js  
 * 
 *      Author: Nathan M. Abbey
 * 
 *     Purpose: Uses inheritance to extend School class to make Primary and High school objects.
 * 
 *      Classes: School(parent), PrimarySchool(child), HighSchool(child)
 *          
 *               METHODS
 * 
 *               1. toggleCheckoutStatus()
 *               2. getAverageRating()
 *               3. addRating()
 * 
 * *****************************************************************************************************************
 */

// Parent class ---
class School {

  // Constructor ---
  constructor(name, level, numberOfStudents){
    this._name = name;
    this._level = level;
    this._numberOfStudents = numberOfStudents;
  }

  // Getters ---
  get name() {
    return this._name;
  }
  get level() {
    return this._level
  }
  get numberOfStudents() {
    return this._numberOfStudents;
  }

  // Prints out important info - formatted ---
  quickFacts(){
    console.log(`${this.name} educates ${this.numberOfStudents} students at the ${this.level} level.`);
  }

  // Takes in array of substitute teachers and returns a random one ---
  pickSubstituteTeacher(substituteTeachers){
    let randomNumber = Math.floor(Math.random() * substituteTeachers.length);
    return substituteTeachers[randomNumber];
  }

  // Setter, ensures input a number ---
  set numberOfStudents(numStudents) {
    if(typeof numStudents === 'number'){
      this._numberOfStudents = numStudents;
    } else {
      console.log('Invalid input: numberOfStudents must be set to a Number.');
    }
  }
} // End of School class |




// Child class ---
class PrimarySchool extends School {

  // Constructor works as regular, sets level to primary ---
  constructor(name, numOfStudents, pickupPolicy){
    super(name, 'primary', numOfStudents);
    this._pickupPolicy = pickupPolicy;
  }
  // Getter ---
  get pickupPolicy(){
    return this._pickupPolicy;
  }
} // End of PrimarySchool class |




// Child class ---
class HighSchool extends School {
  
  // Constructor works as regular, sets level to high-school ---
  constructor(name, numOfStudents, sportsTeams){
    super(name, 'high-school', numOfStudents);
    this._sportsTeams = sportsTeams;
  }

 // Getter ---
  get sportsTeam(){
    return this._sportsTeam;
  }
}// End of HighSchool class |





// Create a primary school Object ---
let primaryStudent = new PrimarySchool ('Rick Ross', 22, 'Student must be picked up by parent or guardian.');

// Selects a randomized substitute teacher for the Object ---
primaryStudent.pickSubstituteTeacher(['Jamal Crawford', 'Lou Williams', 'J. R. Smith', 'James Harden', 'Jason Terry', 'Manu Ginobli']);

// Creates a highschool Object ---
let highschoolStudent = new HighSchool ('Lisgar', 326, ['Baseball', 'Basketball', 'Volleyball', 'Track and Field']);

// Prints out sports to ensure eveything functioning ---
console.log(highschoolStudent._sportsTeams);
