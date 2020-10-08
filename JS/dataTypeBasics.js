/* ***************************************************|
 *
 *	   Name: dataTypeBasics.js
 *	 Author: Nathan M. Abbey
 *
 *
 *	Purpose: Display basic functionalities of different
 *			 data types (methods, properties...)
 *
 *
 *
 * ****************************************************|
 */

let arrayMax = (num) => {
  		return Math.max.apply(null, num);
}

let arrayMin = (num) => {
  		return Math.max.apply(null, num);
}


let formatPrint = (str) => console.log(str + '\n');


let fisherYates = () => {
	let tempArry = [40, 100, 1, 5, 25, 10];

	for (i = tempArry.length -1; i > 0; i--) {
  		j = Math.floor(Math.random() * i)
  		k = tempArry[i]
  		tempArry[i] = tempArry[j]
  		tempArry[j] = k
	}
	return tempArry;
}



let stringManipulator = () => {
	const str = 'This is a string'
	formatPrint('\n' + 'STRING MANIPULATOR -------');
	formatPrint('var.length - The string inputted is ' + str.length + ' chars long.');
	formatPrint('indexOf(var) - string is @ index - ' + str.indexOf('string'));	//lastIndexOf(word, index to start @)
	formatPrint('search(var) - string is @ index' + str.search('string'));	
	formatPrint('slice(#, #) - the last word off! --> ' + str.slice(str.indexOf('string'), str.length))	
	formatPrint('substring(#, #)   - no negatives! ' + str.substring(str.indexOf('string'), str.length));
	formatPrint('substr(#, #) - Here is using --> ' + str.substr(10, 6));	// Second variable the is length
	formatPrint('replace(word, replacement) -  replaces word --> ' + str.replace('string', 'STRING'));
	formatPrint('toUpperCase() - uppercase: + ' + str.toUpperCase() + ' - and toLowerCase(): ' + str.toLowerCase());
	formatPrint('trim()-  gets rid of leading/traling whitespace        '.trim() + '!');
	formatPrint('charAt[#] - Print out or select a single char, first one in this case  --> ' + str.charAt(0));
	formatPrint('splice() - Converting a string to an array using splice() --> ' + str.split(''));
}



let arrayManipulator = () => {

	console.clear();
	let arry = [1, 24, 4, 55, 209, 2];
	const charArry = ['a', 'b', 'c']

	formatPrint('toString() - joins array to a string --> ' + arry.toString());
	formatPrint('join() - joins array on specifed char --> ' + arry.join('_'));
	formatPrint('getElementById() - index of 55 --> ' + arry.indexOf(55)); 

	arry.pop();
	formatPrint('pop() - removes the last element of array --> ' + arry);

	formatPrint('shift() - shows element shifted out --> ' + arry.shift());

	arry.push(69);
	formatPrint('push(69) - adds last element to array --> ' + arry);
	
	arry.unshift(420);
	formatPrint('unshift(var) - adds first element to array --> ' + arry);

	delete arry[0];
	formatPrint('delete - arry[0] - note "hole" --> ' + arry);

	arry.splice(2, 1, 44, 400);
	formatPrint('splice() - Added 2, removed 1 --> ' + arry);

	arry = [1, 24, 4, 55, 209, 2];
	formatPrint('splice() - Can remove element with splice --> ' + arry.splice(0, 4));

	let wholeArry = arry.concat(charArry);
	formatPrint('concat() - Put 2 arrays together --> ' + wholeArry);

	arry = ['s', 'f', 'r', 'd'];
	formatPrint('sort() - Sort an array alphabetically -->' + arry.sort());
}



let arraySorter = () => {
	console.log('\nARRAY SORTER ------|\n')
	let arry = [1, 24, 4, 55, 209, 2];
	arry.sort(function(num1, num2)  {return num1 - num2})
	formatPrint('sort() - Numerical ascending --> ' + arry);

	arry.sort(function(num1, num2)  {return num2 - num1})
	formatPrint('sort() - Numerical descending --> ' + arry);

	formatPrint('reverse() - Reverses and array --> ' + arry.reverse());

	let randomMix = fisherYates()
	formatPrint('Fisher Yates algorithm to randomly mix array --> ' + randomMix);

	let highestNumber = arry.sort((num1, num2) => {return num2 - num1})
	formatPrint('Highest number in ' + arry + ' is ' + highestNumber[0]);

	let lowestNumber = arry.sort((num1, num2) => {return num1 - num2})
	formatPrint('Fidher Yates highest number in ' + arry + ' is ' + highestNumber[0]);

	let maxNum = arrayMax(arry);

	formatPrint('arrayMax function - Max in array --> ' + maxNum);

	let minNum = arrayMin(arry);
	formatPrint('arrayMin function - Min in array --> ' + maxNum);
}



let arrayIteration = () => {

	let arry = ['s', 'f', 's', 'h', 'q'];

	console.log('forEach()');
	arry.forEach((letter => formatPrint(letter)));


	formatPrint('map() - makes a new array --> ' + arry.map(word => word + 'x'));


	formatPrint('filter() - Returns array based on criteria --> ' + arry.filter(value => { return value === 's'}));


	let nums = [1, 545, 2, 4, 66, 2]
	formatPrint('reduce() - Sums all numbers in an array --> ' + nums.reduce((num1, num2) => { return num1 + num2}));


	formatPrint('every() - Will return T or F for spec for whole array --> ' + nums.every(() => {return typeof number}));

	formatPrint('find() - Finds 1rst value greater than 10 --> ' + nums.find(value => {return value > 10 }));

}




//stringManipulator();
//arrayManipulator();
//arraySorter();
//arrayIteration();


