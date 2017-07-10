
=begin


A Matter of Public Knowledge

All right! Let's do a little practice with public methods.
Instructions

We've set up an Application class in the editor. Add a public method called print_status to Application that puts "All systems go!". Don't forget to use the public keyword!

=end

class Application
  
  attr_accessor :status
  
  def initialize; end
  
  # Add your method here!
  public
  def print_status
    puts "All systems go!"    
  end
  
end


=begin 


Private Affairs

Good! Now let's try a private method.
Instructions

Below your public method, add a private method called password that returns the super secret password 12345.

=end

class Application
  
  attr_accessor :status
  
  def initialize; end
  
  # Add your method here!
  public
  def print_status
    puts "All systems go!"    
  end
  
  private
  def password
      return 12345
  end
  
end


=begin 


Module Magic

Super. Let's move on to modules!
Instructions

Create your own module called Languages in the editor to the right. Include a constant called FAVE and set it equal to a string naming your favorite programming language!

=end


# Create your module below!

module Languages
    FAVE = "Ruby"
end


=begin 


Mixin for the Win

Almost there! Our review wouldn't be complete without a little mixin magic.
Instructions

Finally, let's include our Languages module in the Master class (on line 6) so that our favorite language gets printed to the console.
?

=end 


module Languages
  FAVE = "Ruby"  # This is what you typed before, right? :D
end

class Master
  include Languages
  def initialize; end
  def victory
    puts FAVE
  end
end

total = Master.new
total.victory

