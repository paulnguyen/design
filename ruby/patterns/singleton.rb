# REF: http://ruby-doc.com/docs/ProgrammingRuby/html/lib_patterns.html

# Library: singleton
#
# The Singleton design pattern ensures that only one instance of a particular class may be created.
#
# The singleton library makes this simple to implement. Mix the Singleton module into each class 
# that is to be a singleton, and that class's new method will be made private. In its place, 
# users of the class call the method instance, which returns a singleton instance of that class.
#
# In this example, the two instances of MyClass are the same object. 

require 'singleton'
class MyClass
  include Singleton
end
a = MyClass.instance 
b = MyClass.instance 

