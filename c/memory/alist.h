#ifndef ALIST_H
#define ALIST_H
 
/*->
___________________________________________________________________
 
     File name: alist.h
            by: Paul Nguyen
      Contents:
 
      1.  Includes
      2.  Constants
      3.  ALIST object decl
      4.  Access Methods
      5.  Error Methods
      6.  New and Delete methods
      7.  Construct and Destruct methods
      8.  Adding elements
      9.  Getting a pointer to an element in the list
      10. Looping thru all elements in the list
      11. Print ALIST properties
      12. Set extra buffer size
      13. Sorting the ALIST array
      14. Searching for an element in the ALIST array
 
 Revision hist:
 
      1.0  -  Initial release
      1.1  -  Add sort flag property
           -  Add sorting method
           -  Add sorting method
           -  Modified adding elements methods to clear sorted flag
___________________________________________________________________
 
<-*/
 
 
/* ----- 1.  Includes ----- */
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
 
/* ----- 2.  Constants ----- */
#define ALIST_OK                0
#define ALIST_WARNING           4
#define ALIST_ERROR             8
 
/* ----- 3.  ALIST object decl ----- */
typedef struct alist {
        char *buffer;           /* pointer to allocated buffer */
        int  maxSize;           /* current capacity of buffer */
        char *tailPtr;          /* pointer to tail of buffer */
        int elmSize;            /* size of array elements */
        int tailIndx;           /* index of last cell */
        int sorted;             /* sorted flag */
        int errState;           /* error condition */
        char *errMsg;           /* error message */
} ALIST; 
 
extern ALIST *ALnew(int elementSize);
extern void   ALdel(ALIST *obj);
extern void   ALcon(ALIST *obj, int elementSize);
extern void   ALdes(ALIST *obj);
extern void   ALae(ALIST *obj, char *elementPtr);
extern void   ALaes(ALIST *obj, char *elementListPtr, int numElements);
extern void   ALses(ALIST *obj, int size);
extern void  *ALbsrh(ALIST *obj, void *k, int (*cmp)(void *, void *));
extern void  *ALlsrh(ALIST *obj, void *k, int (*cmp)(void *, void *));
 
/* ----- 4.  Access Methods ----- */
#define ALIST_buffer(o)         ( (o)->buffer )
#define ALIST_maxSize(o)        ( (o)->maxSize )
#define ALIST_tailPointer(o)    ( (o)->tailPtr )
#define ALIST_elementSize(o)    ( (o)->elmSize )
#define ALIST_tailIndex(o)      ( (o)->tailIndx )
#define ALIST_sorted(o)         ( (o)->sorted   )
#define ALIST_errorCondition(o) ( (o)->errState )
#define ALIST_errorMessage(o)   ( (o)->errMsg )
#define ALIST_size(o)           ( (o)->tailIndx )
 
/* ----- 5.  Error Methods ----- */
#define ALIST_ok(o)               ( !(o)->errState )
#define ALIST_notOk(o)            ( (o)->errState == ALIST_ERROR )
#define ALIST_warning(o)          ( (o)->errState == ALIST_WARNING )
 
/* ----- 6.  New and Delete methods ----- */
#define ALIST_new(eSize)  ALnew(eSize)
#define ALIST_delete(o)   ALdel(o)
 
/* ----- 7.  Construct and Destruct methods ----- */
#define ALIST_construct(o,eSize) ALcon(o,eSize)
#define ALIST_destruct(o)        ALdes(o)
#define ALIST_init(o,eSize)      ALcon(o,eSize)
#define ALIST_undo(o)            ALdes(o)
 
/* ----- 8.  Adding elements -----*/
#define ALIST_addElement(o,pE)      {ALIST_sorted(o)=0; ALae(o,pE);}
#define ALIST_addElements(o,pE,cE)  {ALIST_sorted(o)=0; ALaes(o,pE,cE);}
 
/* -----  9.  Getting a pointer to an element in the list ----- */
/* ----- Note:  returns a (char *) and must be casted     ----- */
/* ----- to the appropriate type                          ----- */
 
#define ALIST_pointerToElement(o,i) \
        ( (char *) ( ALIST_buffer(o) + (i)*ALIST_elementSize(o) ))
 
/* ----- 10.  Looping thru all elements in the list ----- */
#define ALIST_forEachElement(o,curElementPtr) \
        for ( (curElementPtr) = (char *) ALIST_buffer(o); \
              (curElementPtr) < ALIST_tailPointer(o); \
              (curElementPtr) = ( (char *)(curElementPtr) + ALIST_elementSize(o)) )
 
/* ----- 11.  Print ALIST properties ----- */
#define ALIST_print(o)                                                          \
{                                                                               \
        printf("\n*** ALIST Object ***\n");                                     \
        printf("Buffer Address: %x\n", ALIST_buffer(o));                        \
        printf("Current Max Size: %d\n", ALIST_maxSize(o));                     \
        printf("Tail Pointer Address: %x\n", ALIST_tailPointer(o));             \
        printf("Size of elements (in bytes): %d\n", ALIST_elementSize(o));      \
        printf("Tail index: %d\n", ALIST_tailIndex(o));                         \
        printf("Sorted: %d\n", ALIST_sorted(o));                                \
        printf("Error Condition: %d\n", ALIST_errorCondition(o));               \
        printf("Error Message: %s\n", ALIST_errorMessage(o));                   \
}
 
/* ----- 12.  Set extra buffer size ----- */
/* Contructor or new method should be called before this one */
#define ALIST_setExtraSize(o,size) ALses(o,size)
 
 
/* ----- 13.  Sort the ALIST array ----- */
/*
** Use the Quick Sort Algorithm to sort the array.
** compare function must return for cmp(a,b):
**     0  -  if a = b
**    neg -  if a < b
**    pos -  if a > b
*/
#define ALIST_sort(o,cmp)                                        \
{                                                                \
  qsort(ALIST_buffer(o),ALIST_size(o),ALIST_elementSize(o),cmp); \
  ALIST_sorted(o) = 1;                                           \
}
 
/* ----- Searching for an element in the ALIST array ----- */
/*
** compare function must return for cmp(a,b):
**     0  -  if a = b
**    neg -  if a < b
**    pos -  if a > b
*/
 
/*
** Binary search on a sorted ALIST array
** Returns: NULL if not found or list not sorted
*/
#define ALIST_bSearch(o,key,cmp)   ALbsrh(o,key,cmp)
 
/*
** Linear search on a sorted or unsorted ALIST array
** Returns: NULL if not found
*/
#define ALIST_lSearch(o,key,cmp)   ALlsrh(o,key,cmp)
 
 
#endif
