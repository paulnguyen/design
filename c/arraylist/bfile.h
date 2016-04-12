/*      Object:         Binary File (BFILE)
**
**      File:           bfile.h
**
**      Contents:       BFILE object and methods.
**
**      By:             Paul H. Nguyen
**
**      Update History:
**
**      ver 1.0         Initial Release
**
*/
 
 
#ifndef _BFILE_H
#define _BFILE_H
 
/* Includes */
#include <stdio.h>
#include "alist.h"
 
/* Defines for binary file object */
#define BFILE_READ_ONLY   "rb"
#define BFILE_WRITE_ONLY  "wb"
#define BFILE_OK 0
#define BFILE_NOT_OK -1
#define BFILE_BLOCK_SIZE 50
 
/* Define Binary File Object */
#ifdef OSVS
 
typedef struct {
  char *cpFileName;         /* file name string */
  char *mode;               /* file access mode */
  ALIST buffer[1];          /* buffer containing file's data */
  FILE *fpFileHandle;       /* file handle returned from fopen() */
  int   errState;           /* return error code from function call */
  char  *errMsg;            /* return error message */
} BFILE; 
 
#else
 
typedef struct {
  char *cpFileName;         /* file name string */
  char *mode;               /* file access mode */
  ALIST buffer[1];          /* buffer containing file's data */
  FILE *fpFileHandle;       /* file handle returned from fopen() */
  int   errState;           /* return error code from function call */
  char  *errMsg;            /* return error message */
} BFILE; 
 
#endif /* #ifdev OSVS */
 
/* Create a new BFILE Object */
#ifdef OSVS
#define BFILE_New(name) name(|1|)
#else
#define BFILE_New(name) name[1]
#endif /* #ifdef OSVS */
 
/* Access methods (private) */
#define BFILE_FileName(bf)      ((bf)->cpFileName)
#define BFILE_FileSize(bf)      ( ALIST_size( BFILE_Buffer(bf) ) )
#define BFILE_Buffer(bf)        ((bf)->buffer)
#define BFILE_FileHandle(bf)    ((bf)->fpFileHandle)
#define BFILE_ErrorState(bf)    ((bf)->errState)
#define BFILE_ErrorMessage(bf)  ((bf)->errMsg)
#define BFILE_FileMode(bf)      ((bf)->mode)
 
/* Display BFILE properties */
#define BFILE_Print(F) \
{                                               \
  printf("\n*** BFILE Object ***\n"); \
  printf("\tFile Name: %s\n",BFILE_FileName(F)); \
  printf("\tFile Mode: %s\n",BFILE_FileMode(F)); \
  printf("\tFile Size: %d\n",BFILE_FileSize(F)); \
  printf("\tFile Handle: %d\n",BFILE_FileHandle(F)); \
  printf("\tFile Error Flag: %d\n",BFILE_ErrorState(F)); \
  printf("\n"); \
  ALIST_print(BFILE_Buffer(F)); \
}
 
/* Construct and Destroy methods (public) */
#define BFILE_Construct(fn,mode) Bf_con((fn),(mode))
#define BFILE_Destroy(bf) Bf_des((bf))
 
/* Initialize and Undo methods (public) */
#define BFILE_Init(bf,fn,mode) Bf_Init((bf),(fn),(mode))
#define BFILE_Undo(bf) Bf_Undo((bf))
 
/* I/O methods (public) */
#define BFILE_Open(bf) Bf_Open((bf))
#define BFILE_Get(bf) Bf_Get((bf))
#define BFILE_Put(bf,buf,cnt) Bf_Put((bf),(buf),(cnt))
#define BFILE_Save(bf) Bf_Save((bf))
#define BFILE_Close(bf)                 \
{                                       \
  fclose(BFILE_FileHandle(bf));         \
  BFILE_FileHandle(bf) = 0;             \
  BFILE_ErrorState(bf) = BFILE_OK;      \
}
 
/* Getting a pointer to the data buffer (public) */
#define BFILE_DataPointer(bf) (ALIST_buffer(BFILE_Buffer(bf)))
 
 
/* Function Prototypes */
extern BFILE *Bf_Con(char *fn, char *mode);
extern void   Bf_Des(BFILE *bf);
extern void   Bf_Init(BFILE *bf, char *fn, char *mode);
extern void   Bf_Undo(BFILE *bf);
extern void   Bf_Open(BFILE *bf);
extern void   Bf_Get(BFILE *bf);
extern void   Bf_Put(BFILE *bf, char *dataBuffer, int count);
extern void   Bf_Save(BFILE *bf);
 
 
/* SYNONYMS ... */
#define BFILE_new(name)          BFILE_New(name)
#define BFILE_fileName(bf)       BFILE_FileName(bf)
#define BFILE_fileSize(bf)       BFILE_FileSize(bf)
#define BFILE_buffer(bf)         BFILE_Buffer(bf)
#define BFILE_fileHandle(bf)     BFILE_FileHandle(bf)
#define BFILE_errorState(bf)     BFILE_ErrorState(bf)
#define BFILE_notOk(bf)          BFILE_ErrorState(bf)
#define BFILE_ok(bf)             (!BFILE_ErrorState(bf))
#define BFILE_errorMessage(bf)   BFILE_ErrorMessage(bf)
#define BFILE_fileMode(bf)       BFILE_FileMode(bf)
#define BFILE_print(F)           BFILE_Print(F)
#define BFILE_construct(fn,mode) BFILE_Construct(fn,mode)
#define BFILE_destroy(bf)        BFILE_Destroy(bf)
#define BFILE_init(bf,fn,mode)   BFILE_Init(bf,fn,mode)
#define BFILE_undo(bf)           BFILE_Undo(bf)
#define BFILE_open(bf)           BFILE_Open(bf)
#define BFILE_get(bf)            BFILE_Get(bf)
#define BFILE_put(bf,buf,cnt)    BFILE_Put(bf,buf,cnt)
#define BFILE_save(bf)           BFILE_Save(bf)
#define BFILE_close(bf)          BFILE_Close(bf)
#define BFILE_dataPointer(bf)    BFILE_DataPointer(bf)
 
 
#endif
