/* *****************************************************************************************************************
 *        Name: simple_functions.js  
 * 
 *      Author: Nathan M. Abbey
 * 
 *     Purpose: Numerous simple functions to practice JS logic
 * 
 *               METHODS
 * 
 *               1. anagramFunction(firstWord, secondWord)
 *               2. sumLimit (arrayNums, sumLimit) 
 *               3. decimalToHex(num)
 * 			     4. vowelChecker(str) 
 *               5. palindromeChecker(word)
 * 
 * *****************************************************************************************************************
 */



// 1.
let  anagramFunction = (firstWord, secondWord) =>{

	if(firstWord.length !== secondWord.length){
		return false;
	}
	const sortedWord1 = firstWord.split('').sort();
	const sortedWord2 = secondWord.split('').sort();
		
	if (sortedWord1.join() === sortedWord2.join()){
		return true;
	} else {
		return false;
	}
}



// 2.
let sumLimit = (arrayNums, sumLimit) => {
let bool = false;
	for(let i = 0; i < arrayNums.length; i++) {
		for(let k = i+1; k < arrayNums.length; k++){
			if((arrayNums[i] + arrayNums[k]) === sumLimit){
				return true;
			}
		}
	}
return false;
}



// 3.
let decimalToHex = num => {
  if (num < 0) {
    num = 0xFFFFFFFF + num + 1;
  }
  return num.toString(16).toUpperCase();
}




// 4.
let vowelChecker = str => {
	if (typeof str !== string){
		console.log('vowelChecker() only takes type STRING');
		return -1;
	}
}



// 5.
let  palindromeChecker = word => {
	let forwards = word.replace(' ', '').split('');
	let backwards = word.replace(' ', '').split('').reverse();
	if (forwards.join() === backwards.join()){
		return true
	} else {
		return false;
	}
}


