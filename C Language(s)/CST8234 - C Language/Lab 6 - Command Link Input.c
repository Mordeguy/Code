#include <ctype.h>
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

int main (int argc, char **argv) {
  
 	int cflag = 0;
  	int wflag = 0;
  	int lflag = 0;
  	int hflag = 0;
  	int index, count, newline, words;
  	int c, i;
  	char str;
  	static const char *optString = "cwlh";
  	opterr = 0;
  
  
  	// Displays proper suntax example if program call is misused -------------|
  	if (argc >4 || argc == 1) {
  		printf("\nSyntax ERROR: iCount [options] [filename]\n\n");
  		return;
  	}
  

	// Cycles through the input checking for valid flags. --------------------|
	while ((c = getopt (argc, argv, optString)) != -1) 
    		switch (c) {
    			case 'c':
    	   			cflag = 1;
       	    			break;
      			case 'w':
       	    			wflag = 1;
            			break;
      			case 'l':
      	    			lflag = 1;
      	    			break;
      			case'h' :
      	    			hflag = 1;
      	    			break;	
      			default:
            			printf("%d" , optind);
            			return (0);      
   		} 



	// Opens the file and counts everything ----------------------------------|
	for (i = 1; i < argc; i++) {

		FILE *fp = fopen(argv[i], "r");

		// Start of IF
		if (fp != NULL) {

		count =0;
		newline =0;
		words =1;	
		int r = NULL;

			while (r != EOF) {

				r = fscanf(fp, "%c", &str);

				count++;

				if (str == '\n'){
					newline++;
				} 

				if (str == ' ') {
					words++;
				} 	
				
			} // ENd of WHILE 
			
 	if (cflag == 0 && wflag == 0 && lflag == 0 && wflag == 0) { 
 	 
 	 	printf("%d %d %d %s\n", (newline -2), (words), (count -2), argv[i]);
  	
  	} else {
			
		if (lflag == 1) {
  
	 	 	printf("%d ", (newline -2));
  		} 		
  	
 		if (wflag == 1) {

  			printf("%d ", (words));
	  	}
  
  		if (cflag == 1) {  	
  			printf("%d ", (count-2));
  		}
  		
  		printf("%s\n", argv[i]);   
		
	}
     }
  } 

  return 0;
}

