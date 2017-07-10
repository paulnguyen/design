
=begin 


Naming Your Variables

Recall that instance variables begin with an @. This isn't just a Ruby convention—it's part of the syntax! Always start your instance variables with @.

Class variables are like instance variables, but instead of belonging to an instance of a class, they belong to the class itself. Class variables always start with two @s, like so: @@files.

Global variables can be declared in two ways. The first is one that's already familiar to you: you just define the variable outside of any method or class, and voilà! It's global. If you want to make a variable global from inside a method or class, just start it with a $, like so: $matz.

We'll go through instance and class variables in more detail in a moment. First, let's do a quick review of local and global scope.


Instructions

Take a look at the code to the right. The variable my_variable is inside a class, so it's not reachable by the puts method outside it. But you can fix this! Use either of the two global variable tricks mentioned above. (Check the Hint if you need more help.)

class MyClass
  my_variable = "Hello!"
end

puts my_variable

=end

$my_variable = "Hello!"
class MyClass
  	def printf_global
		puts $my_variable
	end
end

puts $my_variable

# Or...

class MyClass
    $my_variable = "Hello!"
end

puts $my_variable


=begin 

There is a collection of special variables whose names consist of a dollar sign ($) followed by a single character. For example, $$ contains the process id of the ruby interpreter, and is read-only. Here are the major system variables:

$! 	latest error message
$@ 	location of error
$_ 	string last read by gets
$. 	line number last read by interpreter
$& 	string last matched by regexp
$~ 	the last regexp match, as an array of subexpressions
$n 	the nth subexpression in the last match (same as $~[n])
$= 	case-insensitivity flag
$/ 	input record separator
$\ 	output record separator
$0 	the name of the ruby script file
$* 	the command line arguments
$$ 	interpreter's process ID
$? 	exit status of last executed child process

In the above, $_ and $~ have local scope. Their names suggest they should be global, but they are much more useful this way, and there are historical reasons for using these names.

=end
