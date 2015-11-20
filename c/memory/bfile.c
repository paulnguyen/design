/*      Object:         Binary File Object
**
**      File:           Bfile.c
**
**      Contents:       Define functions for BFILE methods
**
**      By:             Paul H. Nguyen
**
**      Update History:
**
**      ver 1.0         Initial Release
**
*/
 
 
/* Includes */
#include "bfile.h"
#include <string.h>
#include <stdlib.h>  /* for malloc() */
 
/* construct a BFile */
BFILE *Bf_Con(char *cpFN, char *cpFM)
{
  BFILE *F = (BFILE *) malloc(sizeof(BFILE));
  BFILE_Init(F,cpFN,cpFM);
  return(F);
}
 
/* initialize a BFILE */
void Bf_Init(BFILE *F, char *cpFileName, char *cpFileMode)
{
  int iLen;        /* length of a string */
 
  /* initialize fields */
  ALIST_init( BFILE_Buffer(F),sizeof(char) );
 
  iLen = strlen(cpFileName);
  BFILE_FileName(F) = (char *) malloc(sizeof(char)*iLen+1);
  strcpy(BFILE_FileName(F),cpFileName);
 
  iLen = strlen(cpFileMode);
  BFILE_FileMode(F) = (char *) malloc(sizeof(char)*iLen+1);
  strcpy(BFILE_FileMode(F),cpFileMode);
 
  BFILE_FileHandle(F) = 0;
  BFILE_ErrorState(F) = BFILE_OK;
}
 
/* destruct a BFile */
void Bf_Des(BFILE *F)
{
  if (F) {
    BFILE_Undo(F);
    free(F);
  }
}
 
/* undo (free space taken by BFILE fields) */
void Bf_Undo(BFILE *F)
{
  /* undo the alist */
  ALIST_undo(BFILE_Buffer(F));
 
  /* free space taken by strings */
  if (BFILE_FileName(F)) free( BFILE_FileName(F) );
  if (BFILE_FileMode(F)) free( BFILE_FileMode(F) );
}
 
/* open a BFILE */
void Bf_Open(BFILE *F)
{
  BFILE_FileHandle(F) = fopen( BFILE_FileName(F), BFILE_FileMode(F) );
  if (BFILE_FileHandle(F))
    BFILE_ErrorState(F) = BFILE_OK;
  else
    BFILE_ErrorState(F) = BFILE_NOT_OK;
}
 
/* read the file from disk into BFILE buffer */
void Bf_Get(BFILE *F)
{
  int iBytes=-1;   /* bytes read by fread() */
 
#ifdef OSVS
  char buf[BFILE_BLOCK_SIZE+10]; /* buffer for block reads */
#else
  char buf[BFILE_BLOCK_SIZE+10];  /* buffer for block reads */
#endif
 
  while(iBytes) {
    iBytes = 0;
    iBytes = fread(buf,sizeof(char),BFILE_BLOCK_SIZE,BFILE_FileHandle(F));
    if (iBytes)
      ALIST_addElements(BFILE_Buffer(F), buf, iBytes);
  }
 
  if ( BFILE_FileSize(F) > 0)
    BFILE_ErrorState(F) = BFILE_OK;
  else
    BFILE_ErrorState(F) = BFILE_NOT_OK;
}
 
 
/* append some data to the BFile buffer */
void Bf_Put(BFILE *bf, char *buffer, int count)
{
  int iOrgSize, iNewSize;
 
  iOrgSize = BFILE_FileSize(bf);
  iNewSize = iOrgSize + count;
 
  if (buffer)
      ALIST_addElements(BFILE_Buffer(bf), buffer, count);
 
  if ( BFILE_FileSize(bf) != iNewSize ) {
    BFILE_ErrorState(bf) = BFILE_NOT_OK;
    BFILE_ErrorMessage(bf)  = "Could not write data to buffer.";
  }
  else { 
    BFILE_ErrorState(bf) = BFILE_OK;
  }
}
 
/* save the BFile object to disk */
void Bf_Save(BFILE *bf)
{
  if( fwrite(BFILE_DataPointer(bf), sizeof(char),
        BFILE_FileSize(bf), BFILE_FileHandle(bf))
        == BFILE_FileSize(bf)
    )
    BFILE_ErrorState(bf) = BFILE_OK;
  else { 
    BFILE_ErrorState(bf) = BFILE_NOT_OK;
    BFILE_ErrorMessage(bf) = "Could not save file to disk.";
  }
}
 
 
 
 
 
