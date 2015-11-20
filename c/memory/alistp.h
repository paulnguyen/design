#ifndef ALISTP_H
#define ALISTP_H
 
/*->
___________________________________________________________________
 
     File name: alistp.h
      Contents:
 
      1.    Includes
      2.    Misc Private Methods
      3.    Function Prototypes
 
 
 Revision hist:
 
      1.0  -  Initial release
___________________________________________________________________
 
<-*/
 
 
/* ----- 1.  Includes ----- */
#include "alist.h"
 
 
/* ----- 2.  Misc Private Methods ----- */
#define ALIST_newElement(o)     ALne(o)
#define ALIST_newElements(o,c)  ALnes(o,c)
#define ALIST_increaseSize(o,s) ALis(o,s)
 
 
/* ----- 3.  Function Prototypes ----- */
extern char  *ALne(ALIST    *obj);
extern char  *ALnes(ALIST *obj, int numElements);
extern void   ALis(ALIST *obj, int increaseSize);
 
 
#endif
