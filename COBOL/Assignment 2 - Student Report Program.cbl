       program-id. assign2 as "assign2".

       environment division.
           input-output section.
           file-control.
           
           SELECT STUDENT-FILE
           ASSIGN "C:\Users\Nay-thwan\Desktop\STUFILE.txt"
           ORGANIZATION IS LINE SEQUENTIAL.

           SELECT STUDENT-REPORT
           ASSIGN "C:\Users\Nay-thwan\Desktop\STURPT.txt"
           ORGANIZATION IS LINE SEQUENTIAL.


       data division.
           file section.
           FD  STUDENT-FILE.
               01  STUDENT-RECORD.
                   05  STUDENT-NAME    PIC X(20).
                   05  STUDENT-NUMBER  PIC X(9).
                   05  PROGRAM-NUMBER  PIC X(3).
                   05  COURSE-NUMBER   PIC X(7).
                   05  MARK-1          PIC 9(3).
                   05  MARK-2          PIC 9(3).
                   05  MARK-3          PIC 9(3).
                   05  MARK-4          PIC 9(3).
             
             FD    STUDENT-REPORT.
                   01 REPORT-RECORD PIC x(80).
                   
                      
       working-storage section.
       
           01  OUTPUT-RECORD-HEADER.
               05  NAME    PIC X(20) VALUE "NAME".
               05  FILLER  PIC X(4) VALUE SPACES.
               05  PRGRM   PIC X(7) VALUE "PROGRAM". 
               05  FILLER  PIC X(2) VALUE SPACES.
               05  AVRG    PIC X(7) VALUE "AVERAGE".
           
           
           01  OUTPUT-RECORD-STRUCTURE.
               05  STU-NAME    PIC X(20).
               05  FILLER      PIC X(4) VALUE SPACES.
               05  STU-PRGRM   PIC X(7).
               05  FILLER      PIC X(2) VALUE SPACES.
               05  STU-AVRG    PIC 9(3).
           
           01  FLAGS.
               05 EOF-FLAG     PIC A(3) VALUE "NO".
               

       procedure division.
       
       
           PERFORM 201-INITIALIZE-STUDENT-REPORT-PRGRM.
           DISPLAY "YOOOO".
           PERFORM 202-CREATE-STUDENT-REPORT.
           DISPLAY "OY".
           PERFORM 203-TERMINATE-STUDENT-REPORT-PRGRM.
           
           
           

           


     



      * 200 LEVEL ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
      
           201-INITIALIZE-STUDENT-REPORT-PRGRM.
               301-OPEN-PROGRAM-FILES.
               302-READ-PROGRAM-FILES.
               303-WRITE-HEADER-TO-FILE.
           
           202-CREATE-STUDENT-REPORT.
               304-CALCULATE-AVERAGE.
               305-WRITE_STUDENT-REPORT.
               302-READ-PROGRAM-FILES.
           
           203-TERMINATE-STUDENT-REPORT-PRGRM.
                CLOSE STUDENT-FILE.
                CLOSE STUDENT-REPORT.
      * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|     
           
           
           
           
      * 300 LEVEL ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|
           301-OPEN-PROGRAM-FILES.
               OPEN INPUT STUDENT-FILE.
               OPEN OUTPUT STUDENT-REPORT.
               
               
           302-READ-PROGRAM-FILES.
               READ STUDENT-FILE AT END MOVE "YES" TO EOF-FLAG.
           
           
           303-WRITE-HEADER-TO-FILE.
               WRITE REPORT-RECORD FROM OUTPUT-RECORD-HEADER.
           
           
           304-CALCULATE-AVERAGE.
              COMPUTE STU-AVRG =(MARK-1 + MARK-2 + MARK-3 + MARK-4) / 3.
           
           
           305-WRITE-STUDENT-REPORT.
               MOVE STUDENT-NAME TO STU-NAME.
               MOVE PROGRAM-NUMBER TO STU-PRGRM.
               WRITE REPORT-RECORD FROM OUTPUT-RECORD-STRUCTURE.
      * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~|         

           
           
           
           

          
           
           
           goback.

       end program assign2.
