
=begin 


What's in a @name?

All right! Just one more step before we can create a person from our Person class: we have to make sure each person has a @name.

In Ruby, we use @ before a variable to signify that it's an instance variable. This means that the variable is attached to the instance of the class. For example,

	class Car
	  def initialize(make, model)
	    @make = make
	    @model = model
	  end
	end

kitt = Car.new("Pontiac", "Trans Am")

creates an instance, kitt, of the class Car. kitt has his own @make ("Pontiac") and @model ("Trans Am"). Those variables belong to the kitt instance, which is why they're called instance variables.

Give your initialize method a single parameter, name. In the body of your method, set @name = name.

This tells Ruby that whenever it creates a Person, it has to have a name, and each instance of Person will have its own @name.


=end

class Person
    
    def initialize (name)
        @name = name
    end
    
end
