
=begin 


Watch Your Step

Inheritance is a tricky concept, so let's go through it step by step.

Inheritance is the process by which one class takes on the attributes and methods of another, and it's used to express an is-a relationship. For example, a cartoon fox is a cartoon mammal, so a CartoonFox class could inherit from a CartoonMammal class. However, a Wizard is not an Elf , so it shouldn't inherit from the Elf class (even if they have a lot of magical attributes and methods in common). Instead, both Wizard and Elf could ultimately inherit from the same MagicalBeing class.

Check out the code in the editor. We've defined a class, ApplicationError, as well as a SuperBadError class that inherits from ApplicationError. Note that we don't define the display_error method in the body of SuperBadError, but it will still have access to that method via inheritance. Click Save & Submit Code to see for yourself!

=end

class ApplicationError
  def display_error
    puts "Error! Error!"
  end
end

class SuperBadError < ApplicationError
end

err = SuperBadError.new
err.display_error

=begin 


Inheritance Syntax

In Ruby, inheritance works like this:

	class DerivedClass < BaseClass
	# Some stuff!
	end

where the derived class is the new class you're making and the base class is the class from which that new class inherits. You can read "<" as "inherits from."
Instructions

We've created an Application class in the editor to the right. Create your own class, MyApp, that inherits from Application. No need to put anything inside your class definition just yet!

=end


class Application
  def initialize(name)
    @name = name
  end
end

# Add your code below!

class MyApp < Application
   
end

