=begin 

Besides using .to_sym, you can also use .intern. 
This will internalize the string into a symbol 
and works just like .to_sym: 

=end


# Quick Example - Convert Array of Strings to Array of Symbols

strings = ["HTML", "CSS", "JavaScript", "Python", "Ruby"]
symbols = [ ]

strings.each do |s| 
    symbols.push s.intern
end

puts symbols