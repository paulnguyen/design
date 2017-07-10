
=begin 


There Can Be Only One!

Any given Ruby class can have only one superclass. Some languages allow a class to have more than one parent, which is a model called multiple inheritance. This can get really ugly really fast, which is why Ruby disallows it.

However, there are instances where you want to incorporate data or behavior from several classes into a single class, and Ruby allows this through the use of mixins. We'll learn about mixins in a later lesson! For now, we'll demonstrate what happens if you try to do multiple inheritance in Ruby.

The demo code we're about to show you includes a fancy trick: if you want to end a Ruby statement without going to a new line, you can just type a semicolon. This means you can write something like

class Monkey
end

on just one line: class Monkey; end. This is a time saver when you're writing something very short, like an empty class or method definition.
Instructions

Check out the code in the editor. See how we're trying to get Dragon to inherit from Creature and Person? We'll get a superclass mismatch for class Dragon error if we try this. Click Save & Submit Code to see for yourself!

=end


class Creature
  def initialize(name)
    @name = name
  end
end

class Person
  def initialize(name)
    @name = name
  end
end

class Dragon < Creature; end
class Dragon < Person; end

# Should get error:  superclass mismatch for class Dragon