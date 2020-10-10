const { builtinModules } = require("module");
/* *************************************************************
 *
 *	   Name:   Node.js
 *	 Author:   Nathan M. Abbey
 *
 *	 Purpose:   Node class for linked list
 *  Required:   Node.js
 *
 * *************************************************************
 */
class ListNode {


    // Creates the HEAD in list, sets data, and next to NULL ---
    constructor(dataIn){
        this.data = dataIn;
        this.next = null;
    }

    // Setter and Getter for next ---
    setNext(nodeIn) { 
        this.next = nodeIn; 
    }
    getNext(){ 
        return this.next; 
    }
}

module.exports = ListNode;