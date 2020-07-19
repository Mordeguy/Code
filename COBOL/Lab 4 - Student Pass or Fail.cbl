       
       
       environment division.
       input-output section.
       file-control.
         
         SELECT STUDENT-FILE
              ASSIGN  TO  "C:\Users\Nay-thwan\Desktop\STU-FILE.txt"
              ORGANIZATION IS LINE SEQUENTIAL.
	      
          SELECT  STUDENT-REPORT
	           ASSIGN TO "C:\Users\Nay-thwan\Desktop\STU-REPORT.TXT"
	           ORGANIZATION IS LINE SEQUENTIAL.
       
       
       data division.
       file section.
           FD  STUDENT-FILE.
           01  STUDENT-RECORD.
      	       05   STUDENT-NAME-IN PIC X(25).
         	   05   EXAM-1			PIC 9(3).
        	   05   EXAM-2			PIC 9(3).
           
           FD  STUDENT-REPORT.
           01  STUDENT-REPORT-RECORD.
               05    STUDENT-NAME-OUT    PIC X(15).
               05     STUDENT-AVERAGE     PIC 9(3).
               05     PASS-FAIL		       PIC X(1).

       WORKING-STORAGE SECTION.
          01   PASS-OR-FAIL               PIC X(9).
 

       PROCEDURE DIVISION.
          CREATE-STUDENT-REPORTS.
          INITIALIZE-STUDENT-REPORT.
          PRODUCE-STUDENT-REPORT.
          TERMINATE-STUDENT-REPORTS.
     


       CREATE-STUDENT-REPORTS.
           OPEN INPUT STUDENT-FILE.
           OPEN OUTPUT STUDENT-REPORT.
           PERFORM READ STUDENT-FILE
           END-READ.
          
       
       INITIALIZE-STUDENT-REPORTS.
           PERFORM EXTRACT-DATA-RTN.
           PERFORM FAILING-MARK-RTN.
           PERFORM PASSING-MARK-RTN.
       

       EXTRACT-DATA-RTN. 
           CALCULATE-AVERAGE-RTN.
           EXTRACT-NAME-RTN.
           
       CALCULATE-AVERAGE-RTN.
           COMPUTE STUDENT-AVERAGE = EXAM-1 + EXAM-2 / 2.
           
           
       EXTRACT-NAME-RTN.
           MOVE STUDENT-NAME-IN TO STUDENT-NAME-OUT. 
           
  
       PASSING-MARK-RTN.
           IF STUDENT-AVERAGE > 50 PERFORM 
           MOVE "P" TO PASS-FAIL.
           
       FAILING-MARK-RTN.
           if STUDENT-AVERAGE < 50 PERFORM
           MOVE "F" TO PASS-FAIL.
    

       PRODUCE-STUDENT-REPORT.
           WRITE STUDENT-REPORT-RECORD.


       TERMINATE-STUDENT-REPORTS.
           CLOSE STUDENT-FILE.
           CLOSE STUDENT-REPORT.
           STOP RUN.
