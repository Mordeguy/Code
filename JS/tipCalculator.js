/* *****************************************************************************************************************
 *        Name: tipCalculator.js  
 * 
 *      Author: Nathan M. Abbey
 *        Date: October 14, 2020
 * 
 *     Purpose: Handles logic for a browser based tip calculator. User can choose between: 10%. 15%. and 20% radio
 *              buttons. Program will calculate appropriate tip - will not accept empty or non-numerical input.
 *      
 * 
 *   Functions: 1. setTipErrorMsg(msg)      -->     Sets the error messgae on tip caculator
 *              2. checkInput()             -->     Ensures input it not empty, and numerical
 *              3. checkRadio()             -->     Checks which radio buttono is checked and proceeds
 *              4. getTip(num)              -->     Used to calculate tip based on param input
 *              5. clearTipCalc()           -->     Clears all of the fiels on the tip calculator
 * 
 * *****************************************************************************************************************
 */


// Global Variabes ---
const total = document.getElementById('total');
const tip = document.getElementById('tip');
const error = document.getElementById('error');


// 1. ---
let setTipErrorMsg = (msg) => {
    error.innerText =  msg;
}


// 2. ---
let checkInput = () => {
    if(total.value.trim().length === 0) {
        setTipErrorMsg('Total cannot be empty');
        return false;
    } else if (isNaN(total.value)) {
        setTipErrorMsg('Total must be numeric');
        return false;
    } else {
        setTipErrorMsg('');
        return true;
    }
};


// 3. ---
let checkRadio = () => {

    const tenRadio = document.getElementById('tenRadio');
    const fifteenRadio = document.getElementById('fifteenRadio');
    const twentyRadio = document.getElementById('twentyRadio');
 
    if (!checkInput()) {
        return;
    }
    if (tenRadio.checked) {
        getTip(0.10);
    } else if (fifteenRadio.checked){
        getTip(0.15);
    } else if (twentyRadio.checked) {
        getTip(0.20);
    }
};


// 4. ---
let getTip = (num) => {
    let tip15 = total.value * num
    tip.value = tip15.toFixed(2);
};


// 5. ---
let clearTipCalc = () => {
    total.value = '';
    tip.value = '';
    error.innerText='';
}
