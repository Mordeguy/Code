
/* *****************************************************************************************************************
 *        Name: openweather_api.js
 * 
 *      Author: Nathan M. Abbey
 * 
 *     Purpose: Uses the OpenWeatherMap API to gather information about the current weather in a Canadian city. It
 *              will display the current temprature, what the temperature feels like, and what the current weather
 *              conditions are.
 * 
 *          
 * *****************************************************************************************************************
 */

const apiKey = 'APIKEY';
const city = 'CITY';


const temp = document.getElementById('temp');
const desc = document.getElementById('desc');
const feels = document.getElementById('feelsLike');

const endpoint_url = 'https://api.openweathermap.org/data/2.5/weather?q=' + city + '&appid=' + apiKey + '&units=metric';

fetch(endpoint_url)

.then(response => {
    if (response.ok) {
        return response.json();
    }
    throw new Error('ERROR');

},  networkError => console.log(networkError.message)

).then(jsonResponse => {

    let currentCel = Math.round(jsonResponse.main.temp); 
    let feelsLikeCel = Math.round(jsonResponse.main.feels_like);
    let description = jsonResponse.weather[0].main + ' - ' + jsonResponse.weather[0].description;

temp.innerHTML = currentCel + ' º C' + '<br>-<br>';
feels.innerHTML = feelsLikeCel + ' º C' +  '<br>-<br>';
desc.innerHTML = description;
   
});