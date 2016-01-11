
# REF:  http://ruby-doc.com/docs/ProgrammingRuby/html/tut_modules.html

# Modules are a way of grouping together methods, classes, and constants. 
#
# Modules give you two major benefits:
#
#    Modules provide a namespace and prevent name clashes.
#    Modules implement the mixin facility.
#

# There are times when you want to group things together that don't naturally form a class. 
# The answer is the module mechanism. Modules define a namespace, a sandbox in which your 
# methods and constants can play without having to worry about being stepped on by other 
# methods and constants. The trig functions can go into one module: 

module Trig
  PI = 3.141592654
  def Trig.sin(x)
   # ..
  end
  def Trig.cos(x)
   # ..
  end
end

module Action
  VERY_BAD = 0
  BAD      = 1
  def Action.sin(badness)
    # ...
  end
end

# require "./trig.rb"
# require "./action.rb"

y = Trig.sin(Trig::PI/4)
wrongdoing = Action.sin(Action::VERY_BAD)


#
# Modules have another, wonderful use. At a stroke, they pretty much eliminate the need 
# for multiple inheritance, providing a facility called a mixin.
#
# A module can't have instances, because a module isn't a class. 
# However, you can include a module within a class definition. When this happens, 
# all the module's instance methods are suddenly available as methods in the class as well. 
# They get mixed in. In fact, mixed-in modules effectively behave as superclasses. 
#

module Debug
  def whoAmI?
    "#{self.to_s}"
  end
end

class Song
  include Debug
  def initialize(name, artist, duration)
    @name     = name
    @artist   = artist
    @duration = duration
  end
end

class EightTrack
  include Debug
  def initialize(name)
    @name     = name
  end
end

a = Song.new("Bicylops", "Fleck", 260)
b = EightTrack.new("Surrealistic Pillow")
a.whoAmI? 	
b.whoAmI? 	


# Instance Variables in Mixins
#
# For a mixin, this means that the module that you mix into your client class (the mixee?) 
# may create instance variables in the client object and may use attr and friends to define 
# accessors for these instance variables. For instance: 
#

module Notes
  attr  :concertA
  def tuning(amt)
    @concertA = 440.0 + amt
  end
end

module Notes2
  attr  :concertA
  def updateConcert
      @concertA = "Hello"
  end
end

class Trumpet
  include Notes
  include Notes2
  def initialize(tune)
    tuning(tune)
    puts "Instance method returns #{concertA}"
    puts "Instance variable is #{@concertA}"
  end
end


# The piano is a little flat, so we'll match it
a = Trumpet.new(-5.3)
a.updateConcert

# Not only do we have access to the methods defined in the mixin, but we get access to 
# the necessary instance variables as well. There's a risk here, of course, that different 
# mixins may use an instance variable with the same name and create a collision.