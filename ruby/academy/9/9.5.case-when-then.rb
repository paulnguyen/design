
=begin 

Ruby provides us with a concise alternative to if-then-else: 
the case statement. The syntax looks like this:

	case language
	when "JS"
	  puts "Websites!"
	when "Python"
	  puts "Science!"
	when "Ruby"
	  puts "Web apps!"
	else
	  puts "I don't know!"
	end

But you can fold it up like so:

	case language
	  when "JS" then puts "Websites!"
	  when "Python" then puts "Science!"
	  when "Ruby" then puts "Web apps!"
	  else puts "I don't know!"
	end

=end

def test
	language = "Ruby"
	case language
	  when "JS" 	then puts "Websites!"
	  when "Python" then puts "Science!"
	  when "Ruby" 	then puts "Web apps!"
	  else 	puts "I don't know!"
	end
end

test()


# Enter your Language
puts "Hello there!"
greeting = gets.chomp

# Add your case statement below!
case greeting
  when "English" then puts "Hello!"
  when "French" then puts "Bonjour!"
  when "German" then puts "Guten Tag!"
  when "Finnish" then puts "Haloo!"
  else puts "I don't know that language!"
end

