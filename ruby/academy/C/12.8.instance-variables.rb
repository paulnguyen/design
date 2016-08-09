
=begin 


For Instance...

Good! A caveat, though: global variables can be changed from anywhere in your program, and they are generally not a very good idea. It's much better to create variables with limited scope that can only be changed from a few places!

For example, instance variables belong to a particular object (or "instance"). Let's get in some practice with instance variables! We've added our Person class from before to the editor.

Instructions

Go ahead and add age and profession parameters to the initialize method, then set these equal to instance variables in the body of the method. Use the name/@name example as a guide.

class Person
  def initialize(name)
    @name = name
  end
end

=end


class Person
  def initialize(name, age, profession)
    @name = name
    @age = age
    @profession = profession
  end
end
