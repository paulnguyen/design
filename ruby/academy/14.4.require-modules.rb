
=begin 


A Few Requirements

Perfect!

Some modules, like Math, are already present in the interpreter. Others need to be explicitly brought in, however, and we can do this using require. We can do this simply by typing

require 'module'

We want to use the Ruby Date module to show today's date, but we haven't required it yet!
Instructions

Go ahead and require 'date' on line 1 so the code on line 3 will work.


=end


require 'date'

puts Date.today