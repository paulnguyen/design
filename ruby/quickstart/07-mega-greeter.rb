#!/usr/bin/env ruby

# REF:  https://www.ruby-lang.org/en/documentation/quickstart/4/

class MegaGreeter
  attr_accessor :names

  # Create the object
  def initialize(names = "World")
    @names = names
  end

  # Say hi to everybody
  def say_hi
    # It now looks at the @names instance variable to make decisions. 
    # If it’s nil, it just prints out three dots. No point greeting nobody, right?
    if @names.nil?
      puts "..."
    # If the @names object responds to each, it is something that you can iterate 
    # over, so iterate over it and greet each person in turn. Finally, if @names 
    # is anything else, just let it get turned into a string automatically and do 
    # the default greeting.
    elsif @names.respond_to?("each")
      # @names is a list of some kind, iterate!
      @names.each do |name|
        puts "Hello #{name}!"
      end
    else
      puts "Hello #{@names}!"
    end
  end

  # each is a method that accepts a block of code then runs that block of code for 
  # every element in a list, and the bit between do and end is just such a block. 
  # A block is like an anonymous function or lambda. The variable between pipe characters 
  # is the parameter for this block.
  #
  # What happens here is that for every entry in a list, name is bound to that list element, 
  # and then the expression puts "Hello #{name}!" is run with that name.
  #
  # Most other programming languages handle going over a list using the for loop, 
  # which in C looks something like:
  # 
  #   for (i=0; i<number_of_elements; i++)
  #   {
  #     do_something_with(element[i]);
  #   }

  # Say bye to everybody
  def say_bye
    if @names.nil?
      puts "..."
    elsif @names.respond_to?("join")
      # Join the list elements with commas
      puts "Goodbye #{@names.join(", ")}.  Come back soon!"
    else
      puts "Goodbye #{@names}.  Come back soon!"
    end
  end

end

#######################################################################################
# Kicking Off the Script
#
# So, that’s the MegaGreeter class, the rest of the file just calls methods on 
# that class. There’s one final trick to notice, and that’s the line:
#
# if __FILE__ == $0
#
# __FILE__ is the magic variable that contains the name of the current file. 
# $0 is the name of the file used to start the program. This check says 
# “If this is the main file being used…” This allows a file to be used as a library, 
# and not to execute code in that context, but if the file is being used as an executable, 
# then execute that code.
#######################################################################################

if __FILE__ == $0
  mg = MegaGreeter.new
  mg.say_hi
  mg.say_bye

  # Change name to be "Zeke"
  mg.names = "Zeke"
  mg.say_hi
  mg.say_bye

  # Change the name to an array of names
  mg.names = ["Albert", "Brenda", "Charles", "Dave", "Engelbert"]
  mg.say_hi
  mg.say_bye

  # Change to nil
  mg.names = nil
  mg.say_hi
  mg.say_bye
end