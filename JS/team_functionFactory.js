/* *****************************************************************************************************************
 *        Name: team_functionFactory.js  
 *    
 * 
 *      Author: Nathan M. Abbey
 * 
 *     Purpose: Manipulate a 2 lists (players and games) by creating a function factory for both Objects to add
 *              objects
 * 
 *      Object : team
 *   Parameters: _players[], _games[]
 *          
 *               METHODS
 * 
 *               1. addPlayer(firstName, lastName, age)
 *               2. addGame(opponent, teamPoints, opponentPoints)
 * 
 * *****************************************************************************************************************
 */

const team = {
  _players : [],
  _games : [],


  // Getters ----
  get players() {
    return this._players;
  },
  get games() {
    return this._games;
  },


  // 1 ------
  addPlayer(firstName, lastName, age) {
    let player = 
    {
      firstName : firstName,
      lastName : lastName,
      age : age
    }

    team._players.push(player);
  },



  // 2 ------
  addGame(opponent, teamPoints, opponentPoints) {
    let game = 
    {
      opponent : opponent,
      teamPoints : teamPoints,
      opponentPoints : opponentPoints
    }
    team._games.push(game);
  },
};




// Populates _players array
team.addPlayer('Steph', "Curry", 28);
team.addPlayer('Lisa', "Leslie", 44);
team.addPlayer('Bugs', "Bunny", 76);


// Populates _games array
team.addGame('Habs', 69, 420);
team.addGame('Leafs', 9, 40);
team.addGame('Raptors', 10, 22)


// Prints both out to ensure it is all functioning
console.log(team.games);
console.log(team.games);