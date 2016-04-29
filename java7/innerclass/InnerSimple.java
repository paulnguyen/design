/* 
   INNER CLASSES
*/


class HasStatic 
{ 
   static int j = 0 ;
}

class Outer {

   // Inner Class decl in static context have no encl. instance
   // It is contained within an enclosing block
   // This is also a Local Class 
   static void classMethod() 
   {
      final int i = 0 ;
      int j = 0 ;
   
      class LocalInStaticContext
      {
         //int k = varA ; // ERROR, can't access instance fields
         int m = i ; // OK, can access enclosing block's final variables
         //int n = j ; // ERROR, needs to be final!
      }
   }

   // Local class in non-static context
   void foo( int arg1, final int arg2 )
   {
      int foo2 ;
      final int foo3 = 0 ;
      final int foo4 ;

      class Local
      {
         //int a = arg1 ; // ERROR, must access a constant
         int b = foo3 ; // OK
         //int c = arg1 ; // ERROR, not a final argument
         int d = arg2 ; // OK, final argument
         //int e = foo4 ; // Error, final, but not assigned!
      }

      foo4 = 0 ; // assigned after body of inner class
   }

   // Inner class can extend a class with static members
   // This is also a Non-Static Member Class
   class Inner extends HasStatic 
   {
      //static int y = 4 ; // ERROR! Can't decl. static members
      static final int x = 3 ; // OK, can have constants

      public int methodA() 
      {
         //varC = 0 ; // ERROR!  Can't set an enclosing instance final field
         varA = 0 ;  // OK to access enclosing instance fields
         Outer myEnclosingInstance = Outer.this ;  // implicit reference 
         return varA ;
      }
   }

   // Nested class (delc as Static) is not an inner class
   // These are simply for name space management and
   // behaves like a top-level class with name:  Outer.NestedButNotInner
   // This is also a Static Member Class
   static class NestedButNotInner 
   {
      static int z = 5 ; // OK, not an inner class!
   }

   // Member Interface - Implicitly static
   interface NeverInner {} 

   // An Anonymous class delclared and created within a local block
   void bar( final int arg1, final int arg2 )
   {
      final int var = 0 ;
      NeverInner anon1 = new NeverInner()
      {
         int anonMethodA() 
         { return varB+arg1+arg2+var ; // can access instance members & final variable/arguments
         }
      } ;

   }
   

   // Instance Vars can be access by inner classes
   private int varA ;
   private int varB ;

   // blank final fields can not be assigned in inner class
   //private final int varC ;
}


class Tester {

   Outer.NestedButNotInner obj1 = new Outer.NestedButNotInner() ; // OK
   //Outer.Inner obj2 = new Outer.Inner() ; // ERROR
   Outer.NeverInner obj3 = new Outer.NeverInner() {} ;  // OK - Anonymous Object
}


/*

F:\PROJECTS\Research\java.inner>javap -private Outer
Compiled from InnerSimple.java
class Outer extends java.lang.Object {
    private int varA;
    private int varB;
    Outer();
    static void classMethod();
    void foo(int, int);
    void bar(int, int);
    static int access$002(Outer, int);    <-- generated for access to instance vars from inner classes
    static int access$000(Outer);         <-- ditto
    static int access$100(Outer);         <-- ditto
    static interface Outer. NeverInner {}
    static class Outer. NestedButNotInner extends java.lang.Object
      {
        static int z;
        Outer.NestedButNotInner();
        static {};
      }
    class Outer. Inner extends HasStatic
      {
        private final Outer this$0;
        static final int x;
        Outer.Inner(Outer);               <-- constructor generated with ref to enclosing instance
        public int methodA();
      }


F:\PROJECTS\Research\java.inner>javap -private Outer$1
Compiled from InnerSimple.java
class Outer$1 extends java.lang.Object implements Outer. NeverInner {
    private final Outer this$0;           <-- generated ref to enclosing instance
    private final int val$arg2;           <-- copy of local vars
    private final int val$arg1;           <-- copy of local vars
    Outer$1(Outer,int,int);               <-- constructor generated with outer inst 
                                             pointer and bar()'s arg list.
                                             (the arg list of method enclosing creation 
                                             of anon object)
    int anonMethodA();
}


*/


