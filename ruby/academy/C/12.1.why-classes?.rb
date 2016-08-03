
=begin 


Why Classes?

Ruby is an object-oriented programming language, which means it manipulates programming constructs called objects. (Almost) everything in Ruby is an object! You've been using them all along, so they should be very familiar. Objects have methods, which you've seen before, and attributes, which are just data. For instance, in

"Matz".length
# ==> 4

the "Matz" object is a string with a .length method and a length attribute of 4. We'll learn how to build our own objects with their own methods and internal variables in the next few exercises.

But what exactly makes "Matz" a string? The fact that it's an instance of the String class. A class is just a way of organizing and producing objects with similar attributes and methods.

=end

class Language
  def initialize(name, creator)
    @name = name
    @creator = creator
  end
	
  def description
    puts "I'm #{@name} and I was created by #{@creator}!"
  end
end

ruby = Language.new("Ruby", "Yukihiro Matsumoto")
python = Language.new("Python", "Guido van Rossum")
javascript = Language.new("JavaScript", "Brendan Eich")

ruby.description
python.description
javascript.description