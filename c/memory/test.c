/* FILE:  test.c 
** DESC:  Test program to demo Memory Manager
** BY:    Paul Nguyen
*/

#include "alloc.h"

main()
{

  char *bptr;  
  int  i;
  char label[11];

  /* Initialize Memory Manager */
  MEM_INIT();

  /* Allocate and label 10 blocks */
  for(i=1;i<=10;i++)
  {
    sprintf( label, "Blk #%d", i );
    bptr = (char*) MALLOC( i*10 );
    TAG(bptr,label);

    /* Note: We will lose track of all but the last
    ** block since the pointer 'bptr' is reused
    ** with each new allocation--Opps! A memory Leak!
    */

  }

  /* Display (We should have 10 blocks) */
  MEM_INFO();

  /* Reallocate the last block (the 100 byte block) */
  bptr = (char*) REALLOC(bptr,2000);

  /* Display (the 100 bytes block is now 2000 bytes) */
  MEM_INFO();

  /* Free reallocated block */
  FREE( bptr );

  /* Display (we should have 9 blocks) */
  MEM_INFO();

  /* Release all blocks */
  MEM_RELEASE();

  /* Display (we should have 0 blocks) */
  MEM_INFO();

}


