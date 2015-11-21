
# REF: https://www.ruby-lang.org/en/documentation/quickstart/3/

# Altering Classes—It’s Never Too Late
# But what if you want to be able to view or change the name? 
# Ruby provides an easy way of providing access to an object’s variables.
#
# In Ruby, you can open a class up again and modify it. The changes will 
# be present in any new objects you create and even available in existing 
# objects of that class. So, let’s create a new object and play with its @name property.


# Class
class Greeter
  def initialize(name = "World")
    @name = name
  end
  def say_hi
    puts "Hi #{@name}!"
  end
  def say_bye
    puts "Bye #{@name}, come back soon."
  end
end

# Add Accessor
# Using attr_accessor defined two new methods for us, 
# name to get the value, and name= to set it.
class Greeter
  attr_accessor :name
end

# Now, has new methods
g = Greeter.new("Andy")
g.respond_to?("name=")
g.respond_to?("name")

# Now, test it...
g = Greeter.new("Andy")
g.respond_to?("name")
g.respond_to?("name=")
g.say_hi
g.name
g.name="Betty"
g.name
g.say_hi