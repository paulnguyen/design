
=begin 


When Good isn't Good Enough

On the flip side, sometimes you'll be working with a derived class (or subclass) and realize that you've overwritten a method or attribute defined in that class' base class (also called a parent or superclass) that you actually need. Have no fear! You can directly access the attributes or methods of a superclass with Ruby's built-in super keyword.

The syntax looks like this:

	class DerivedClass < Base
	  def some_method
	    super(optional args)
	      # Some stuff
	    end
	  end
	end

When you call super from inside a method, that tells Ruby to look in the superclass of the current class and find a method with the same name as the one from which super is called. If it finds it, Ruby will use the superclass' version of the method.
Instructions

We decided we want to do some chops-punching after all! Let's do two things:

    1. 	Add a puts "Instead of breathing fire..." 
		as the first line in our Dragon's fight method.
    
	2.	Replace the return statement inside Dragon's 
		definition of fight with the keyword super. 
		(No need to pass any arguments to super, since 
		Creature's fight method doesn't take any.)


=end

class Creature
  def initialize(name)
    @name = name
  end
  
  def fight
    return "Punch to the chops!"
  end
end

# Add your code below!

class Dragon < Creature
   
   def fight
        puts "Instead of breathing fire..." 
        super
   end
    
end