/* FILE:  alloc.h
** DESC:  Definition of Memory Manager
** BY:    Paul Nguyen
*/


#ifndef ALLOC_H
#define ALLOC_H

#include <stdlib.h>
#include <stdio.h>
 
/* Memory Info Object */ 
typedef struct tagMEMCELL
{
  void              *ptr;      /* Address of block */
  unsigned int      size;      /* Size of block */
  char              name[10];  /* 10 character block tag */
  struct tagMEMCELL *next;
} MEMCELL;
#define MEMCELL_size(pMEMCELL)    ((pMEMCELL)->size)
#define MEMCELL_pointer(pMEMCELL) ((pMEMCELL)->ptr)
#define MEMCELL_name(pMEMCELL)    ((pMEMCELL)->name)
#define MEMCELL_next(pMEMCELL)    ((pMEMCELL)->next)
#define MEMCELL_info(pMEMCELL)    memcinfo(pMEMCELL)

 
/* Memory Manager Object */
typedef struct tagMEM
{
  int      usage;    /* current memory usage in bytes */
  int      init;     /* init flag */
  MEMCELL *cells;    /* Singly-linked list of memory cells */
} MEM;
#define MEM_usage(pMEM)          ((pMEM)->usage)
#define MEM_cells(pMEM)          ((pMEM)->cells)
#define MEM_initialized(pMEM)    ((pMEM)->init)
#define MEM_init(pMEM)           meminit(pMEM)
#define MEM_release(pMEM)        memrel(pMEM)
#define MEM_alloc(pMEM,sz)       memalloc(pMEM,sz)
#define MEM_realloc(pMEM,ptr,sz) memralloc(pMEM,ptr,sz)
#define MEM_free(pMEM,ptr)       memfree(pMEM,ptr)
#define MEM_info(pMEM)           meminfo(pMEM)
#define MEM_tag(pMEM,ptr,tag)    memtag(pMEM,ptr,tag)
 
 
/* Exports */ 
extern MEM  GMM[1];
extern void meminfo( MEM *pMEM );
extern void memfree(MEM *pMEM, void *ptr );
extern void *memalloc( MEM *pMEM, unsigned int sz );
extern void memrel(MEM *pMEM);
extern int  meminit(MEM *pMEM);
extern void memtag(MEM *pMEM, void *ptr, char *tag );
extern void memcinfo( MEMCELL *pMEMCELL );
extern void *memralloc( MEM *pMEM, void *ptr, unsigned int sz );
 

/* Malloc, Realloc, Free replacements, and misc macros */
#define MALLOC(sz)          MEM_alloc(GMM,sz)
#define FREE(ptr)           MEM_free(GMM,ptr)
#define REALLOC(ptr,sz)     MEM_realloc(GMM,ptr,sz)
#define TAG(ptr,name)       MEM_tag(GMM,ptr,name)
#define MEM_INIT()          MEM_init(GMM)
#define MEM_RELEASE()       MEM_release(GMM)
#define MEM_INFO()          MEM_info(GMM)
  

#endif




