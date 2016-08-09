=begin

Blocks Are Like Nameless Methods.  These are similar 
to anonymous functions in JavaScript or lambdas in Python.

Blocks can be defined with either the keywords 
do and end or with curly braces ({}).

=end

1.times do
  puts "I'm a code block!"
end

1.times { puts "As am I!" }


#
# How Blocks Differ from Methods
#

# method that capitalizes a word
def capitalize(string) 
  puts "#{string[0].upcase}#{string[1..-1]}"
end

capitalize("ryan") # prints "Ryan"
capitalize("jane") # prints "Jane"

# block that capitalizes each string in the array
# prints "Ryan", then "Jane"
["ryan", "jane"].each { 
	|string| 
	puts "#{string[0].upcase}#{string[1..-1]}"
} 
	