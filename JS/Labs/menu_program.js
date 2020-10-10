/* *****************************************************************************************************************
 *        Name: menu_program.js  
 * 
 *      Author: Nathan M. Abbey
 * 
 *     Purpose: Manipulate a menu with 3 courses by populating the selections, randomizing the dishes per course, 
 *              adding up the total, and presenting to user a msg in plain text format.
 * 
 *      Object : menu
 *   Parameters: _courses[appetizers[], mains[], desserts[]]
 *          
 *               METHODS
 * 
 *               1. addDishToCourse(courseName, dishName, dishPrice);
 *               2. getRandomDishFromCourse(courseName)
 *               3. generateRandomDishFromCourse()
 * 
 * *****************************************************************************************************************
 */


const menu = {


  // Setters and Getters ---
  _courses : {
      appetizers : [],
      mains : [],
      desserts : []
  },
  set appetizers(appetizers) {
      appetizers;
  },
  get appetizers() {
    return appetizers;
  },
  set mains(mains) {
      mains;
  },
  get mains() {
    return mains;
  },
  set desserts(desserts) {
      desserts;
  },
  get desserts() {
    return desserts;
  },
  get courses () {
   return {
     appetizer : this.appetizer,
     mains: this.mains,
     desserts : this.dessert
   }
  },


  // 1 ------
  addDishToCourse(courseName, dishName, dishPrice) {

    // Takes in params and sets that of Object
    let dish = {
      name: dishName,
      price: dishPrice
    }
    // Pushes to correct array based on courseName
    this._courses[courseName].push(dish);
  },



  // 2 ------
  getRandomDishFromCourse(courseName) {
    // Save course dishes to variable
    let dishes = menu._courses[courseName]

    // Create random number based on array size
    let number = Math.floor(Math.random() * dishes.length);

    // Returns the randomized dish from the course
    return dishes[number];
  },



  // 3 ------
  generateRandomDishFromCourse() {

    // Creates a random dish from each course
    let appetizer = this.getRandomDishFromCourse('appetizers');
    let main = this.getRandomDishFromCourse('mains');
    let dessert = this.getRandomDishFromCourse('desserts');

    //Calculates the total cost
    let total = appetizer.price + main.price + dessert.price;

    // Returns formatted string
    return `A ${appetizer.name}, ${main.name}, and ${dessert.name} comes out to $${total}.00.`
  }
};


// Populates the menu courses
let app = menu.addDishToCourse('appetizers', 'nachos', 8);
let app2 = menu.addDishToCourse('appetizers', 'bread', 4);
let app3 = menu.addDishToCourse('appetizers', 'potato skins', 6);
let mn = menu.addDishToCourse('mains', 'steak', 15);
let mn2 = menu.addDishToCourse('mains', 'mash', 9);
let mn3 = menu.addDishToCourse('mains', 'lasagna', 17);
let dess = menu.addDishToCourse('desserts', 'ice cream', 5);
let dess2 = menu.addDishToCourse('desserts', 'pudding', 4);
let dess3 = menu.addDishToCourse('desserts', 'Creme Brule', 7);



// Finally, prints out the formatted message
console.log(menu.generateRandomDishFromCourse());
