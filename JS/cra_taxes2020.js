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
 *          1. calcTaxes(bracket, cutOff)   -->     Takes in tax bracket and cut off point and calculates tax owed 
 *                                                  for speific tax bracket
 *          2. runTaxProgram()              -->     Runs through the fixe tax brackets and calculates overal taxes
 *                                                  owed.
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
function calcTaxes(bracket, cutOff) {

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



// 2. ---
function runTaxProgram() {
   
    leftover = salary.value;
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

    amountOwed.innerText = '$ '+ (firstTaxes + secondTaxes + thirdTaxes + fourthTaxes + fifthTaxes).toFixed(2);
}


// 3. ---
let clearTaxCalc = () => {
    firstBracket.value = 0;
    secondBracket.value = 0;
    thirdBracket.value = 0;
    fourthBracket.value = 0;
    fifthBracket.value = 0;
}
