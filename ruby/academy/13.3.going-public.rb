
=begin 


Going Public

Great work! Now we'll get into the details of public and private methods.

Methods are public by default in Ruby, so if you don't specify public or private, your methods will be public. In this case, however, we want to make it clear to people reading our code which methods are public. We do this by putting public before our method definitions, like so:

 class ClassName
  # Some class stuff
  public
  def public_method
    # public_method stuff
  end
end

Note that everything after the public keyword through the end of the class definition will now be public unless we say otherwise. (We'll say otherwise in the next exercise.)
Instructions

For now, let's add a public method called bark to Dog. The bark method should puts "Woof!".

=end



class Dog
   
    def initialize( name, breed )
        @name = name
        @breed = breed
    end   
    
    public
    
    def bark 
       puts "Woof!" 
    end
    
end

