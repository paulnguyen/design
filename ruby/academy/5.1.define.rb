=begin 

	Methods are defined using the keyword def (short for "define"). 

	Methods have three parts:

    (1) The header, which includes the def keyword, the name of the method, and any arguments the method takes. 

	(2) The body, which is the code block that describes the procedures the method carries out. 
	    The body is indented two spaces by convention (as with for, if, elsif, and else statements)
    
	(3) The method ends with the end keyword.

=end

def welcome
  puts "Welcome to Ruby!"
end
welcome


def puts_1_to_10
  (1..10).each { |i| puts i }
end
puts_1_to_10 