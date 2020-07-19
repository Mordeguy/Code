       IDENTIFICATION DIVISION.
           program-id. ASSIGN-1.
           author. Nathan M Abbey.
           date-written. September 23, 2018
           security. None.
       
       
       ENVIRONMENT DIVISION.
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
     
              
       DATA DIVISION.
       FILE SECTION.
  
       
       WORKING-STORAGE SECTION.
                   01  NUM-VALUES.
                       05   FLD-1   PIC	9(3)	VALUE	123.
   	                   05   FLD-2   PIC	9(3)	VALUE	222.
   	                   05   FLD-3   PIC	9(2)	VALUE	10.
   	                   05   FLD-4   PIC	9(3)	VALUE	100.
   	                   05   FLD-5   PIC	9(2)	VALUE	22.
	                   05   FLD-6   PIC	9(4)	VALUE	1111.

       
       PROCEDURE DIVISION.
       
           MULTIPLY FLD-1  BY  FLD-3  GIVING  FLD-2 .
           DISPLAY FLD-2.


           DIVIDE  FLD-5  BY  FLD-3  GIVING  FLD-6  ROUNDED.  
           DISPLAY FLD-6.
           
           ADD FLD-6   FLD-4    TO   FLD-3.
           DISPLAY FLD-3.

           SUBTRACT  FLD-4   FLD-2   FLD-1  FROM FLD-5.
           DISPLAY FLD-5.
           
           COMPUTE  FLD-5   ROUNDED  = ( FLD-2 +  FLD-4)  / 3
           DISPLAY FLD-5.
     
           SUBTRACT  FLD-3  FLD-4   FLD-5  FROM  FLD-2.
           DISPLAY FLD-2.
       
       
      
           
       

        
       TERMINATE-RTN.
           
            STOP RUN.
           