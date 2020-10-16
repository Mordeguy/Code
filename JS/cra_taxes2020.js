/* *****************************************************************************************************************
 *        Name: cra_taxes2020.js
 * 
 *      Author: Nathan M. Abbey
 *        Date: October 16, 2020
 * 
 *     Purpose: Takes in a user's annual salary and calculates what they should be generally paying in tax for the
 *              year 2020.
 *      
 *       Function(s):
 * 
 *          1. clearTaxCalc()                   -->     Clears all inputs for tax calculator
 *          2. setFeedbackText(text, color)     -->     Sets the text and color for user feedback
 *          3. calcTaxes(bracket, cutOff))      -->     Takes in tax rate and max amount in tax bracket, calculates
 *                                                      taxes owed for each tax bracket
 *          4. runTaxProgram()                  -->     Uses the calcTaxes() function to calculate total taxes owed
 *          5. checkTaxInput()                  -->     Ensures salary: isn't empty, below 0, non-numerical.
 *                                                      Displays error message and does not cotinue.
 *                                                 
 * *****************************************************************************************************************
 */

let leftover;
const salary = document.getElementById('salary');
const firstBracket = document.getElementById('firstBracket');
const secondBracket = document.getElementById('secondBracket');
const thirdBracket = document.getElementById('thirdBracket');
const fourthBracket = document.getElementById('fourthBracket');
const fifthBracket = document.getElementById('fifthBracket');
const amountOwed = document.getElementById('amountOwed');




// 1. ---
let clearTaxCalc = () => {
    salary.value = ''
    firstBracket.value = '';
    secondBracket.value = '';
    thirdBracket.value = '';
    fourthBracket.value = '';
    fifthBracket.value = '';
    amountOwed.innerText = ''
}




// 2. ---
let setFeedbackText = (text, color) => {
    amountOwed.style.color = color;
    amountOwed.innerText = text;
} 




// 3. ---
let calcTaxes = (bracket, cutOff) => {

    let taxes = 0;
    if(leftover < cutOff){
        taxes = leftover * bracket;
        leftover = 0;
    } else {
        taxes = cutOff * bracket
        leftover -= cutOff;
    }
    return taxes;
}




// 4. ---
let runTaxProgram = () => {
    let firstTaxes = calcTaxes(0.15, 48535);
    let secondTaxes = calcTaxes(0.205, 48534);
    let thirdTaxes = calcTaxes(0.26, 53404);
    let fourthTaxes = calcTaxes(0.29, 63895);
    let fifthTaxes =leftover * 0.33;

    firstBracket.value = firstTaxes.toFixed(2);
    secondBracket.value = secondTaxes.toFixed(2);
    thirdBracket.value = thirdTaxes.toFixed(2);
    fourthBracket.value = fourthTaxes.toFixed(2);
    fifthBracket.value = fifthTaxes.toFixed(2);

    let str= String('$ '+ (firstTaxes + secondTaxes + thirdTaxes + fourthTaxes + fifthTaxes).toFixed(2));
    setFeedbackText(str, 'black');
}




// 5. ---
let checkTaxInput = () => {

    leftover = salary.value;

   if (isNaN(leftover)){
        setFeedbackText('Salary must be numeric.', 'red');
   } else if (leftover < 0) {
        setFeedbackText('Salary must be above 0.', 'red');
   } else if (String(leftover).trim().length === 0) {
    setFeedbackText('Salary must be not be empty.', 'red');
   } else {
       runTaxProgram();
   }
}

