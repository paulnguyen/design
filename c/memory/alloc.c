/* FILE:  alloc.c 
** DESC:  Functions for Memory Manager
** BY:    Paul Nguyen
** Email: phn20@ras.amdahl.com
*/

#include "alloc.h"
#include <stdio.h>
#include <stdlib.h>
 

/* memory manager */
MEM GMM[1];
 
 
 
/**************** Display info about a memory block *************************/ 
void memcinfo( MEMCELL *pMEMCELL )
{
  if( MEMCELL_name( pMEMCELL )[0] != 0 )
  {
    printf( "     <MEMORY CELL> size:%d address:%Xh (%s)\n",
            MEMCELL_size(pMEMCELL),
            MEMCELL_pointer(pMEMCELL),
            MEMCELL_name(pMEMCELL)
          );
  }
  else
  {
    printf( "     <MEMORY CELL> size:%d address:%Xh\n",
            MEMCELL_size(pMEMCELL),
            MEMCELL_pointer(pMEMCELL)
          );
  } 
}
 

/********** Display info about the status of the Memory Manager *************/ 
void meminfo( MEM *pMEM )
{
  MEMCELL *pCell;
 
  if( !MEM_initialized(pMEM) )
  {
    printf( "Memory Manager Not Initialized!\n" );
    return;
  }    

  printf( "\n***********************************************\n" );
  printf( "<MEMORY MANAGER>  usage:%d  \n",
          MEM_usage(pMEM) );
 
  pCell =  MEM_cells( pMEM );
  while( pCell != NULL )
  {
    MEMCELL_info( pCell );
    pCell = MEMCELL_next(pCell);
  }
  printf( "***********************************************\n" );
 
}
 


/********************* Find a cell infront of another **********************/ 
MEMCELL *findPrev( MEMCELL *head, MEMCELL *cur )
{
  MEMCELL *pCell;
 
  pCell = head;
  while( pCell != NULL )
  {
    if( MEMCELL_next(pCell) == cur )
    {
 
#ifdef DEBUG
  printf( "Found: \n" );
  memcinfo( pCell );
#endif
 
      return( pCell );
    }
    pCell = MEMCELL_next(pCell);
  }
 
#ifdef DEBUG
  printf( "Not Found!\n" );
#endif
 
  return( NULL );
}


/****************************** Label a memory block ***********************/ 
void memtag(MEM *pMEM, void *ptr, char *tag )
{
  MEMCELL *pCell;
 
  if( !MEM_initialized(pMEM) )
    return;
 
  if( ptr == NULL )
    return;
 
  /* search for pointer */
  pCell = MEM_cells(pMEM);
  while( pCell != NULL )
  {
    if( MEMCELL_pointer(pCell) == ptr )
    {
      if( strlen(tag) < 10 )
      {
        strcpy( MEMCELL_name(pCell), tag );
      }
      else
      {
        memcpy( MEMCELL_name(pCell), tag, 9 );
      }
 
    }
    pCell = MEMCELL_next(pCell);
  }
}


/****************** Free a block ********************************************/ 
void memfree(MEM *pMEM, void *ptr )
{
  MEMCELL *pCell;
  MEMCELL *prev;
  MEMCELL *next;

 
  if( !MEM_initialized(pMEM) )
    return;
 

 
#ifdef DEBUG
  printf( "Trying to free cell for address: %x \n", ptr );
  printf( "Memory Manager Before Free: \n" );
  meminfo( pMEM );
#endif
 
 
  if( ptr == NULL )
  {
    return;
  }
 
  /* search for pointer */
  pCell = MEM_cells(pMEM);
  while( pCell != NULL )
  {
    if( MEMCELL_pointer(pCell) == ptr )
    {
      free(MEMCELL_pointer(pCell));
      MEM_usage(pMEM) -= MEMCELL_size(pCell);
      MEMCELL_pointer( pCell ) = NULL;
 
      if( pCell == MEM_cells(pMEM) )
      {
        MEM_cells(pMEM) = MEMCELL_next( pCell );
	free( pCell );
      }
      else
      {
        prev = findPrev( MEM_cells(pMEM), pCell );
        next = MEMCELL_next( pCell );
        if( prev != NULL )
        {
          MEMCELL_next( prev ) = next;
	  free( pCell );
        }
      }
 
      break;  /* from while loop */
    }
    pCell = MEMCELL_next(pCell);
  }
 
#ifdef DEBUG
  printf( "Memory Manager After Free: \n" );
  meminfo( pMEM );
#endif

}
 
/***************************** Allocate a block *****************************/
void *memalloc( MEM *pMEM, unsigned int sz )
{
   MEMCELL *pCell;
   char    *rptr;
 
  if( !MEM_initialized(pMEM) )
    return( NULL );
 

 
#ifdef DEBUG
  printf( "Trying to allocate block of size:%d \n", sz );
  printf( "Memory Manager Before Allocation: \n" );
  meminfo( pMEM );
#endif
 
      /* allocate on heap */
      rptr = (char *) malloc( sz );
      if( rptr == NULL )
      {
        printf( "***Error*** Out of Heap Memory\n" );
        exit( -1 );
      }
      memset(rptr,0,sz);
      pCell = (MEMCELL *) malloc(sizeof(MEMCELL));
      if( pCell )
      {
        memset(MEMCELL_name(pCell),0,sizeof(MEMCELL_name(pCell)));
        MEMCELL_size(pCell) = sz;
        MEMCELL_pointer(pCell) = rptr;
        MEMCELL_next(pCell) = NULL;
        MEM_usage(pMEM) += sz;
        if( MEM_cells(pMEM) == NULL )
        {
          MEM_cells(pMEM) = pCell;
        }
        else
        {
          MEMCELL_next(pCell) = MEM_cells(pMEM);
          MEM_cells(pMEM) = pCell;
        }
      }
      else
      {
        printf( "***Error*** Out of Heap Memory\n" );
        exit( -1 );
      }
 
#ifdef DEBUG
  printf( "Success in allocation of block of size:%d \n", sz );
  printf( "Memory Manager After Allocation: \n" );
  meminfo( pMEM );
#endif
 
   return( rptr );
}


/*************************** Re-Allocate a block *****************************/
void *memralloc( MEM *pMEM, void *ptr, unsigned int sz )
{
   MEMCELL *pCell;
   char    *rptr;
   int      found=0;
 
  if( !MEM_initialized(pMEM) )
    return;
 
#ifdef DEBUG
  printf( "Trying to reallocate block at %x of size:%d \n", ptr, sz );
  printf( "Memory Manager Before Allocation: \n" );
  meminfo( pMEM );
#endif

      /* Find block */
      pCell = MEM_cells(pMEM);
      while( pCell != NULL )
      {
        if( MEMCELL_pointer(pCell) == ptr )
        {
	  /* reallocate */
	  rptr = (void*) realloc(ptr,sz) ;
	  if( rptr )
	    {
	      MEM_usage(pMEM) -= MEMCELL_size(pCell);
	      MEMCELL_size(pCell) = sz;
	      MEMCELL_pointer(pCell) = rptr;
	      MEM_usage(pMEM) += sz;
	    }
	  else
	    {
	      printf( "***Error*** Out of Heap Memory\n" );
	      exit( -1 );
	    }

	  found = 1;
	  break;  /* from while loop */
        
	}
     
        pCell = MEMCELL_next(pCell);
     }

 
#ifdef DEBUG
  printf( "Success in reallocation of block of size:%d new loc: %x\n", sz, rptr );
  printf( "Memory Manager After Allocation: \n" );
  meminfo( pMEM );
#endif
 
   if( !found )
     {
       printf( "***Error*** Invalid pointer passed to be reallocated.\n" );
     }

   return( rptr );
}
 
 
/************************ Release all blocks *******************************/ 
void memrel(MEM *pMEM)
{
  MEMCELL *tofree;
  MEMCELL *pCell;
 
 
  if( !MEM_initialized(pMEM) )
    return;
 
 
#ifdef DEBUG
  printf( "Freeing all Memory Blocks \n" );
  printf( "Memory Manager Before: \n" );
  meminfo( pMEM );
#endif

 
  /* free cells */
  pCell = MEM_cells(pMEM);
  while( pCell != NULL )
  {
      tofree = pCell;
      pCell = MEMCELL_next(pCell); 
      if( MEMCELL_pointer(tofree) != NULL )
      free(MEMCELL_pointer(tofree));
      free(tofree);
  }
  
  MEM_usage(pMEM) = 0;
  MEM_cells(pMEM) = NULL;
  MEM_initialized(pMEM) = 0;
 
 
#ifdef DEBUG
  printf( "Memory Manager After: \n" );
  meminfo( pMEM );
#endif
 
}
 


/***************** Init a Memory Manager ***********************************/ 
int meminit(MEM *pMEM)
{
 
  /* set initial values */
  memset(pMEM,0,sizeof(MEM));
 
  /* OK */
  MEM_initialized( pMEM ) = 1;
  return( 0 );
}
 


