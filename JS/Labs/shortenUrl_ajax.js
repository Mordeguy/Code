/* *****************************************************************************************************************
 *        Name: shotenUrl_ajax.js
 * 
 *      Author: Nathan M. Abbey
 * 
 *     Purpose: Takes in a long URL from the GUI input, and upon clicking the 'Submit' button the data is
 *              prepared and sent to the rebrandly API. The response is then handled using a series of then()
 *              functions.
 *          
 *               FUNCTIONS
 * 
 *               1. shortenUrl()
 *               2. displayShorteUrl()
 * 
 * *****************************************************************************************************************
 */


// Info needed to communicate w/ API ---
const apiKey = 'APIKEY';
const url = 'https://api.rebrandly.com/v1/links';



// Objects located within the HTML document ---
const inputField = document.querySelector('#input');
const shortenButton = document.querySelector('#shorten');
const responseField = document.querySelector('#responseField');




// 1. ---
// AJAX function to send request to  API and handle the response ---
const shortenUrl = () => {

  // Sets up the information to be sent off to API ---
  const urlToShorten = inputField.value;
  const data = JSON.stringify({destination: urlToShorten});

  // Utilizes the fetch() fucntion, first param is url ---
  fetch(url, 
  {
   method: 'POST',
   headers: {
             'Content-type': 'application/json',
             'apikey': apiKey
            },
   body: data
  })



  // Chained then() ---
  .then(response => {

    // If response is OK return the response
    if(response.ok){
      return response.json();
    }

    // Throws this error if there is and error
    throw new Error('Request failed!');

    // This throws if the server is un-reachable ---
    }, (networkError => {
        console.log(networkError.message);
    })

    // Chained then() - passes in JSON response if everything OK ---
    ).then(jsonResponse => {
        renderResponse(jsonResponse);
    })


} // END : shortenUrl()




// 2. ---
// Handles the event and disables default behaviour ---
const displayShortUrl = (event) => {
  event.preventDefault();

  // Checked to see if input is empty or not ---
  while(responseField.firstChild){

    // If the firstChild is populated, remove it ---
    responseField.removeChild(responseField.firstChild)
  }

  // Once the field is empty, call shortenUrl() to populate the responseField on button click ---
  shortenUrl();
}


// This add the even listened to the shortenButton object iwht action 'click' ---
shortenButton.addEventListener('click', displayShortUrl);
