/* *************************************************************
 *
 *	   Name: linkedList.js
 *	 Author: Nathan M. Abbey
 *
 *	 Purpose: Displays singular linked list functionality
 *  Required: Node.js
 *
 * *************************************************************
 */
const ListNode = require('./Node');

class LinkedList {

    constructor(){
        this.head = null; 
    }


    addNodeToHead(dataIn) {
        let currentHead = this.head;
        let newHead = new ListNode(dataIn);
        this.head = newHead;
        newHead.next = currentHead;
    }

    removeHead() {
        let nodeHead = this.head;
        if(nodeHead === null){
            return;
        }
        this.head = nodeHead.getNext();
    }

    addNodeToTail(dataIn) {
        let nodeTail = this.head;
        let node = new ListNode(dataIn);  
        
        if (nodeTail) {
            while (nodeTail.getNext() !== null) {
                nodeTail = nodeTail.getNext();
              }
              nodeTail.setNext(node);
        } else {
            this.head = node;
      }
    }       




    printListData() {
        let headNode = this.head;
        let out = '';

        while (headNode !== null){
            out += headNode.data + ' ';
            headNode = headNode.getNext();
        }
        console.log(out);
    }
}



const list = new LinkedList();



list.addNodeToTail('Cold');
list.addNodeToTail('Steve');
list.addNodeToTail('Austin')
list.addNodeToHead('Stone');





list.printListData();


