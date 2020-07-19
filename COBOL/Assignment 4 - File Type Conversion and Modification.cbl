      ******************************************************************
       IDENTIFICATION DIVISION.
      ******************************************************************
       PROGRAM-ID. CONVERT-INDEX-SEQ-FILE.
       AUTHOR.
      
      ******************************************************************
       ENVIRONMENT DIVISION.
      ******************************************************************
       INPUT-OUTPUT SECTION.
       FILE-CONTROL.
       SELECT MASTER-STUDENT-FILE 
         ASSIGN TO "C:\Users\Nay-thwan\Desktop\STUFILE4.TXT"            
           ORGANIZATION IS LINE SEQUENTIAL.
       SELECT STUDENT-FILE-INDEX   
         ASSIGN TO "C:\Users\Nay-thwan\Desktop\STUFILE5.TXT"
           ORGANIZATION IS INDEXED
           ACCESS MODE IS SEQUENTIAL
           RECORD KEY IS STUDENT-NUMBER-INDEX.
          
       SELECT COURSE-FILE
         ASSIGN TO "C:\Users\Nay-thwan\Desktop\CRSFILE.TXT"             
           ORGANIZATION IS LINE SEQUENTIAL.
       
      ******************************************************************
       DATA DIVISION.
      ******************************************************************
       FD MASTER-STUDENT-FILE.
       01 STUDENT-RECORD-IN.
           05 STUDENT-NAME-IN PIC X(20).
           05 STUDENT-NUMBER PIC 9(9).
           05 PROGRAM-NUMBER PIC X(3).
           05 COURSE-NUMBER PIC X(7).
           05 MARK-1 PIC 9(3).
           05 MARK-2 PIC 9(3).
           05 MARK-3 PIC 9(3).
           05 MARK-4 PIC 9(3).
           
       FD STUDENT-FILE-INDEX.
       01 STUDENT-REPORT-INDEX.
           05 STUDENT-NAME-INDEX PIC X(20).
           05 STUDENT-NUMBER-INDEX PIC 9(9).
           05 PROGRAM-NUMBER-INDEX PIC X(3).
           05 COURSE-NUMBER-INDEX PIC X(7).
           05 MARK-1-INDEX PIC 9(3).
           05 MARK-2-INDEX PIC 9(3).
           05 MARK-3-INDEX PIC 9(3).
           05 MARK-4-INDEX PIC 9(3).
       
       FD COURSE-FILE.
       01 COURSE-RECORD-IN.
           05 COURSE-NUMBER-IN-FILE PIC X(7).
           05 COURSE-NAME-IN-FILE PIC X(10).
        
       
       WORKING-STORAGE SECTION.

       01 FLAGS.
           05 EOF-FLAG PIC X(1).
           05 FOUND-FLAG PIC X(1).
       
       01 COUNTERS.
           05 SUB1 PIC 9(2).
           
       01 VAR-RANGE.
           05 NUM-OF-COURSES PIC 9(2) VALUE 10.

       01 COURSES-WS.
           05 COURSE-TABLE OCCURS 10 TIMES.
               10 COURSE-NUMBER-WS PIC X(7).
               10 COURSE-NAME-WS PIC X(10).
           
      ******************************************************************
       PROCEDURE DIVISION.
      ******************************************************************
       100-CREATE-STUDENT-REPORT.
           PERFORM 201-INITIATE-STUDENT-FILE-JOB.
           PERFORM 202-CREATE-STUDENT-RECORD UNTIL EOF-FLAG = "Y".
		   PERFORM 203-TERMINATE-STUDENT-FILE-JOB.
		   STOP RUN.
           
       201-INITIATE-STUDENT-FILE-JOB.
           PERFORM 301-OPEN-STUDENT-FILES.
           PERFORM 303-INITIATE-COURSE-TABLE.
           PERFORM 304-LOAD-COURSE-TABLE
             VARYING SUB1 FROM 1 BY 1
               UNTIL SUB1 > NUM-OF-COURSES
                 OR EOF-FLAG = "Y".
           PERFORM 305-READ-STUDENT-RECORD.                   
 
       202-CREATE-STUDENT-RECORD.

   
           PERFORM 309-WRITE-STUDENT-REPORT.
           PERFORM 305-READ-STUDENT-RECORD.
       
       203-TERMINATE-STUDENT-FILE-JOB.
           CLOSE MASTER-STUDENT-FILE STUDENT-FILE-INDEX COURSE-FILE.    
       
       301-OPEN-STUDENT-FILES.
           OPEN INPUT MASTER-STUDENT-FILE COURSE-FILE 
            OUTPUT  STUDENT-FILE-INDEX.

           
       303-INITIATE-COURSE-TABLE.
           INITIALIZE COURSES-WS.    
            
       304-LOAD-COURSE-TABLE.
           READ COURSE-FILE 
             AT END MOVE "Y" TO EOF-FLAG
               NOT AT END MOVE COURSE-RECORD-IN TO COURSE-TABLE(SUB1).
       
       305-READ-STUDENT-RECORD.
           READ MASTER-STUDENT-FILE AT 
               END MOVE "Y" TO EOF-FLAG.
           

       309-WRITE-STUDENT-REPORT.
          MOVE STUDENT-RECORD-IN TO STUDENT-REPORT-INDEX.
          WRITE STUDENT-REPORT-INDEX.
        
      

          
          
           
          
             
       
      
          