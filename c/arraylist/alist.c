/*->
___________________________________________________________________
 
   File name:  alist.c
          by:  Paul Nguyen
    Revision:  1.1
    Contents:
               Function        Revision
               ________        ________
 
               ALae            rev 1.0
               ALaes           rev 1.0
               ALcon           rev 1.0
               ALdel           rev 1.0
               ALdes           rev 1.0
               ALis            rev 1.0
               ALne            rev 1.0
               ALnes           rev 1.0
               ALnew           rev 1.0
               ALses           rev 1.0
               ALbsrh          rev 1.0
               ALlsrh          rev 1.0
 
___________________________________________________________________
 
<-*/
 
 
 
/*===================== BEGIN ALAE ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALae
         Purpose:  Add an element to the ALIST array
           Input:  Pointer to ALIST object and pointer to element
          Output:  N/A
       Algorithm:
 
       1.  Copy into the element cell returned by the "new element"
           method "element size" bytes from the "element pointer"
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
void ALae(ALIST *obj, char *elementPtr)
{       char *ptr;
 
        ptr = ALIST_newElement(obj);
 
        if( ptr == NULL ) return;
 
/*1*/   memcpy( ptr,
                (elementPtr),
                ALIST_elementSize(obj));
}
 
/*===================== END ALAE =====================================*/
 
 
/*===================== BEGIN ALAES ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALaes
         Purpose:  Add a group of elements into the ALIST array
           Input:  ALIST object pointer, pointer to elements to add,
                   and number of elements in the group
          Output:  N/A
       Algorithm:
 
       1.  Copy into the start of the new memory block allocated
           in the ALIST array for the group "num elements" of
           "element size" byte blocks from the start of "element list
            pointer".
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
void ALaes(ALIST *obj, char *elementListPtr, int numElements)
{
 
/*1*/    memcpy( ALIST_newElements(obj,(numElements)),
                 (elementListPtr),
                 ALIST_elementSize(obj)*(numElements) );
 
}
/*===================== END ALAES =====================================*/
 
 
/*===================== BEGIN ALCON ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALcon
         Purpose:  Initialize an ALIST object's properties
           Input:  Pointer to ALIST obj, and initial number of elements
          Output:  N/A
       Algorithm:
 
       1.  Init properties
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
void ALcon(ALIST *obj, int elementSize)
{
/*1*/  ALIST_buffer(obj) = 0;
       ALIST_maxSize(obj) = 0;
       ALIST_tailPointer(obj) = 0;
       ALIST_tailIndex(obj) = 0;
       ALIST_elementSize(obj) = elementSize;
       ALIST_errorCondition(obj) = ALIST_OK;
       ALIST_errorMessage(obj) = "No Error.";
}
 
/*===================== END ALCON =====================================*/
 
 
/*===================== BEGIN ALDEL ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALdel
         Purpose:  Delete an ALIST object from the heap
           Input:  Pointer to an ALIST object
          Output:  Free memory occupied by the ALIST object
       Algorithm:
 
       1.  Call ALIST destruct method
       2.  Free object
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
void ALdel(ALIST *obj)
{
/*1*/  ALIST_destruct(obj);
/*2*/  free(obj);
}
 
 
/*===================== END ALDEL =====================================*/
 
 
/*===================== BEGIN ALDES ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALdes
         Purpose:  ALIST destructor
           Input:  Pointer to ALIST object
          Output:  ALIST object's buffer is freed
       Algorithm:
 
       1.  Free the ALIST buffer
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
void ALdes(ALIST *obj)
{
 
/*1*/  if( ALIST_buffer(obj) ) free( ALIST_buffer(obj) );
 
}
 
/*===================== END ALDES =====================================*/
 
 
/*===================== BEGIN ALIS ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALis
         Purpose:  Increase the size of the ALIST array
           Input:  ALIST obj ptr, size to increase to
          Output:  Array Size increased
       Algorithm:
 
       1.  Check for invalid requests
       2.  Make sure requested size to increase is reasonable
       3.  Set tail offset and new size
       4.  If buffer not allocated, allocate now,
       4a  Else reallocate the buffer with new size
       5.  Make sure ALIST props are updated
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
void ALis(ALIST *obj, int increaseSize)
{
  int newByteSize,      /*  number of bytes to allocate for new space */
      tailOffset;       /*  number of bytes offset from start of buffer for tail pointer */
 
/*1*/  if( increaseSize < ALIST_maxSize(obj) ) return;
 
/*2*/  if( increaseSize < (2*ALIST_maxSize(obj)) )
         increaseSize = 2*ALIST_maxSize(obj);
 
/*3*/  tailOffset = ALIST_tailPointer(obj) - ALIST_buffer(obj);
       newByteSize = increaseSize * ALIST_elementSize(obj);
 
       if( !ALIST_buffer(obj) )
/*4*/  { ALIST_buffer(obj) = (char *) malloc( newByteSize );
 
         if( ALIST_buffer(obj) == 0 )
         { ALIST_errorCondition(obj) = ALIST_ERROR;
           ALIST_errorMessage(obj) =
             "Could not allocate space for ALIST object.";
           return;
         }
       } 
       else
/*4a*/ { ALIST_buffer(obj) = (char *) realloc( ALIST_buffer(obj), newByteSize );
 
         if( ALIST_buffer(obj) == 0 )
         { ALIST_errorCondition(obj) = ALIST_ERROR;
           ALIST_errorMessage(obj) =
             "Could not reallocate more space for ALIST object.";
           return;
         }
       } 
 
/*5*/  ALIST_maxSize(obj) = increaseSize;
       ALIST_tailPointer(obj) = ALIST_buffer(obj) + tailOffset;
}
 
 
/*===================== END ALIS =====================================*/
 
 
/*===================== BEGIN ALNE ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALne
         Purpose:  Get pointer to next ALIST element
           Input:  ALIST object ptr
          Output:  Pointer to next vacant ALIST element
       Algorithm:
 
       1. Check for previous error
       2. Increase array size if neccessary
       3. Set pointer to next ALIST array cell
       4. Return pointer
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
char *ALne(ALIST *obj)
{
/*1*/  if( ALIST_notOk(obj) )
          return NULL;
 
/*2*/  if( ALIST_size(obj) == ALIST_maxSize(obj) )
          ALIST_increaseSize(obj, ALIST_size(obj)+1);
 
/*1*/  if( ALIST_notOk(obj) )
          return NULL;
 
/*3*/  ALIST_tailPointer(obj) += ALIST_elementSize(obj);
       ALIST_tailIndex(obj)++;
 
/*4*/  return( ALIST_tailPointer(obj) - ALIST_elementSize(obj) );
}
 
/*===================== END ALNE =====================================*/
 
 
/*===================== BEGIN ALNES ===================================*/
/*->
_______________________________________________________________________
 
   Function name: ALnes
         Purpose: Get a pointer to a new set of ALIST elements
           Input: ALIST obj ptr and number of new elements
          Output: pointer to set of ALIST elements
       Algorithm:
 
       1.  Check error state
       2.  Increase size if necessary
       3.  Set pointer to first cell of new set
       4.  Return pointer
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
char *ALnes(ALIST *obj, int numElements)
{
 
/*1*/  if( ALIST_notOk(obj) )
         return NULL;
 
/*2*/  if( ALIST_maxSize(obj) <= ALIST_size(obj)+numElements )
         ALIST_increaseSize(obj, ALIST_size(obj)+numElements);
 
/*3*/  ALIST_tailPointer(obj) += (ALIST_elementSize(obj)*numElements);
       ALIST_tailIndex(obj)+= numElements;
 
/*4*/  return( ALIST_tailPointer(obj) -
               (ALIST_elementSize(obj)*numElements) );
}
 
 
/*===================== END ALNES =====================================*/
 
 
/*===================== BEGIN ALNEW ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALnew
         Purpose:  Create an ALIST object on the heap
           Input:  Number of elements for the new ALIST
          Output:  Pointer to the ALIST object if successful
       Algorithm:
 
       1.  Check for malloc error
       2.  Call ALIST constructor
       3.  Return pointer to object
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
ALIST *ALnew(int elementSize)
{
  ALIST *obj = (ALIST *) malloc( sizeof(ALIST) );
 
/*1*/  if( !obj ) return NULL;
 
/*2*/  ALIST_construct(obj,elementSize);
 
/*3*/  return(obj);
}
 
/*===================== END ALNEW =====================================*/
 
 
/*===================== BEGIN ALSES ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  Alses
         Purpose:  Set the extra buffer size
           Input:  ALIST object, size
          Output:  N/A
       Algorithm:
 
       1. Check for invalid size
       2. Handle compress request (size = 0)
       3. Handle intial size request (empty buffer)
       4. Handle resize request
          (shrink to free unused cells, or increase extra size)
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
void ALses(ALIST *obj, int size)
{
        int  maxSize = ALIST_maxSize(obj);
        int  curSize = ALIST_size(obj);
        int  tailOffset;
 
 /*1*/  if( size < 0 ) return;
 
 /*2*/  if( size == 0 )
        {
          if( ((maxSize - curSize) > 1) && (maxSize > size) )
          {
            tailOffset = ALIST_tailPointer(obj) - ALIST_buffer(obj);
            ALIST_buffer(obj) =
              (char *) realloc( ALIST_buffer(obj),
                               (curSize+1)*ALIST_elementSize(obj) );
 
            if( ALIST_buffer(obj) == NULL )
            { ALIST_errorCondition(obj) = ALIST_ERROR;
              ALIST_errorMessage(obj) =
                "Could not reallocate more space for ALIST object.";
              return;
            }
 
            ALIST_maxSize(obj) = curSize+1;
            ALIST_tailPointer(obj) = ALIST_buffer(obj) + tailOffset;
 
          }
 
          return;
        }
 
 /*3*/  if( ALIST_buffer(obj) == NULL )
        {
          ALIST_buffer(obj) =
              (char *) malloc( size*ALIST_elementSize(obj) );
 
          if( ALIST_buffer(obj) == NULL )
          { ALIST_errorCondition(obj) = ALIST_ERROR;
            ALIST_errorMessage(obj) =
              "Could not allocate space for ALIST object.";
            return;
          }
 
          ALIST_maxSize(obj) = size;
          ALIST_tailPointer(obj) = ALIST_buffer(obj);
          return;
        }
 
 /*4*/  if( size != 0 && ALIST_buffer(obj) != NULL )
        {
          tailOffset = ALIST_tailPointer(obj) - ALIST_buffer(obj);
          ALIST_buffer(obj) =
              (char *) realloc( ALIST_buffer(obj),
                            (curSize+size+1)*ALIST_elementSize(obj) );
 
          if( ALIST_buffer(obj) == NULL )
          { ALIST_errorCondition(obj) = ALIST_ERROR;
            ALIST_errorMessage(obj) =
              "Could not reallocate space for ALIST object.";
            return;
          }
 
          ALIST_maxSize(obj) = curSize+size+1;
          ALIST_tailPointer(obj) = ALIST_buffer(obj) + tailOffset;
        }
}
/*===================== END ALSES =====================================*/
 
 
/*===================== BEGIN ALBSRH ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALbsrh
         Purpose:  Perform a Binary Search on the ALIST array
           Input:  ALIST object, search key, compare function
          Output:  pointer to element if found, NULL of not found
       Algorithm:
 
       1.  Check for sorted list
       2.  Call bsearch in standard library
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
 
static void *myBsearch( void *key, void *buf, int n, int size,
                 int (*cmp)(void *key, void *elm) );
 
void  *ALbsrh(ALIST *obj, void *key, int (*cmp)(void *key, void *elm))
{
 
/*1*/  if( !ALIST_sorted(obj) ) return( NULL );
 
/*2*/  return( myBsearch(key,ALIST_buffer(obj),ALIST_size(obj),
                       ALIST_elementSize(obj),cmp) );
 
}
 
 
static void *myBsearch( void *key, void *buf, int n, int size,
                 int (*cmp)(void *key, void *elm) )
{
  int m = n / 2;
  void *e = (void *)( (char *)buf + m * size );
  int   x;
 
  if( n < 1 ) return( NULL );
  x = cmp( key,e );
  if( x == 0 ) return e;
  if( x < 0 ) return myBsearch( key,buf,m,size,cmp );
  return myBsearch( key,(void*)( (char *)e + size ),n-m-1,size,cmp);
}
 
 
 
/*===================== END ALBSRH =====================================*/
 
 
/*===================== BEGIN ALLSRH ===================================*/
/*->
_______________________________________________________________________
 
   Function name:  ALlsrh
         Purpose:  Perform a Linear Search on the ALIST array
           Input:  ALIST object, search key, compare function
          Output:  pointer to element if found, NULL of not found
       Algorithm:
 
       1.  Loop thru all elements in array
       2.  Check current element to key and return if found
       3.  Increment to next element if not found yet
 
   Revision hist:
 
   1.0  -  Initial release
 
_______________________________________________________________________
 
<-*/
 
#include "alistp.h"
 
void  *ALlsrh(ALIST *obj, void *key, int (*cmp)(void *key, void *elm))
{
       int i;
       char *ptr=ALIST_buffer(obj);
 
/*1*/  for( i=0; i<ALIST_size(obj); i++)
       { 
/*2*/    if( cmp(key,ptr) == 0 ) return( ptr );
         else
/*3*/      ptr += ALIST_elementSize(obj);
       } 
}
/*===================== END ALLSRH =====================================*/
 
 
