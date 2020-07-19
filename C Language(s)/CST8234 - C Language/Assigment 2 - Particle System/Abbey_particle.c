/*     FILE :  particle.c
*      NAME :  Nathan M. Abbey
*    COURSE :  CST8234 - C Language
*      DATE :  November 18, 2016
*     TOPIC :  Assignment 2
*  CONTENTS :  This file holds the functions to manipulate the particles
*/


/**************************************************************************
 Include Private Library Headers
**************************************************************************/
#include "particle.h"


/**************************************************************************
 FUNCTION : particle_init
  PURPOSE : Initialize the Properties of a Single Particle
    INPUT : Pointer to the particle structure to be initialized
   OUTPUT : Returns 0 on Success
    NOTES : Initializes a particle's color, speed, size, lifespan, and direction   
**************************************************************************/
int particle_init(struct particle* p) {
	
	/* The snow will be white and change slightly how bright/dark it is */	
	p->color.r = 1;
	p->color.g = 1;
	p->color.b = 1;
	p->color.a = (float)rand()/RAND_MAX;
	
	
	/* The snow will fall from the top right of the screen*/
	p->pos.x = (rand() %100);
	p->pos.y = 100;
	p->pos.z = (float)rand()/RAND_MAX;


	/* The initial direction on the x is negative; the snow falls down and to the left */
	p->dir.x = -1 + (float)rand()/RAND_MAX;
	p->dir.y = -1 + (float)rand()/RAND_MAX;
	p->dir.z = 0;
	
	
	/* The snow flakes will fall slowly; some faster than others*/
	p->spd.x = 1 + (float)rand()/RAND_MAX;
	p->spd.y = 3 + (float)rand()/RAND_MAX;
	p->spd.z = 2 + (float)rand()/RAND_MAX;
	
	p-> lifespan = DELTA_LIFE_SPAN;
	
	/* The snow flakes will be small and vary in size.*/
	p-> size = 1 + (float)rand()/RAND_MAX;
	
	return (0);
}


/**************************************************************************
 FUNCTION : particle_add
  PURPOSE : Add a Particle to the Dynamic Particle Linked List
    INPUT : struct particle* headRef. Head of the Particle List
   OUTPUT : Returns -1 on Error, 0 on Success
    NOTES : Calls particle_init()
 **************************************************************************/
int particle_add(struct particle** headRef) {

	/*Create and allocate memory for a new particle in heap*/
	struct particle *new = (struct particle*)malloc(sizeof(struct particle));

	/*Points new's next to the old head and makes "new" the new head*/
	new->next = *headRef;
	*headRef = new;
	
	/*Passes the newly created particle to be initialized*/
	particle_init(new);
	
	return (0);
} 


/**************************************************************************
 FUNCTION : particle_remove
  PURPOSE : Remove a Specific Particle from the Dynamic Particle Linked List
   OUTPUT : Returns -1 on Error, 0 on Success
    INPUT : Pointer to the Particle to Remove
    NOTES : Particle Can be Situated in any Part of the List. Usually Deleted 
            Because the Lifespan Ran Out
 **************************************************************************/
int particle_remove(struct particle* p) {	
	
	struct particle* temp = p;
	struct particle* next;
	
	/* If there is nothing to delete; the function will return -1*/
	if (temp->next == NULL) {
		return (-1);
	}
		
	next = temp->next;
	temp->next = next->next;

	
	return (0);
}




/**************************************************************************
 FUNCTION : particle_destroy
  PURPOSE : Free Memory Used by the Dynamic Particle Linked List
    INPUT : struct particle** headRef. Head of the Particle Linked List
   OUTPUT : Returns -1 on Error, the Number of Particles Destroyed on Success
    NOTES : Removes all Particles from the List. Calls particle_remove();
 **************************************************************************/
int particle_destroy(struct particle** headRef) {	
	
	int numDestroyed;
	struct particle* temp;	
	
	/* If head ref is NULL it means the list is empty; and it returns -1 */	
	if (*headRef == NULL) {
		return -1;
	}
	
	/* This while loop remves all particles from the list */
	while (*headRef != NULL) {
		
		temp = *headRef;
		*headRef = temp->next;
		numDestroyed++;
		particle_remove(temp);				
	}
	
	return numDestroyed;
}


/**************************************************************************
 FUNCTION : particle_update
  PURPOSE : Update the particles properties to be rendered in the next frame
    INPUT : struct particle** headRef. Head of the particle linked list
   OUTPUT : returns -1 on error, 0 on sucesss
    NOTES : creativity and more creativity
 **************************************************************************/
int particle_update(struct particle** headRef) {

	struct particle* p = *headRef;

	if (p == NULL) {
		return (-1);
	
	} else if (p->next == NULL) {
		particle_destroy(&p);
		exit(0);
	} 
				
	while (p != NULL) {
		
		p->pos.x += (p->spd.x * p->dir.x);	
		p->pos.y += (p->spd.y * p->dir.y);
					
		p->lifespan -= 1;
					
		if (p->lifespan <= 0)
			
				particle_remove(p);
			     p = p-> next;
	}	
	return 0;
}

