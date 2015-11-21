
# REF: https://www.ruby-lang.org/en/documentation/quickstart/3/

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

# Object
g = Greeter.new("Pat")
g.say_hi
g.say_bye

# Instance variables are private!
# g.@name

# So what methods do exist for Greeter objects?
Greeter.instance_methods

# we don’t want methods defined by ancestors 
Greeter.instance_methods(false)

g.respond_to?("name")
g.respond_to?("say_hi")
g.respond_to?("to_s")

# Object is the Root Object
Object.instance_methods


