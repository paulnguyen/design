=begin 

Converting between strings and symbols is a snap.
The .to_s and .to_sym methods are what you're 
looking for 

=end

:sasquatch.to_s
# ==> "sasquatch"

"sasquatch".to_sym
# ==> :sasquatch


# Quick Example - Convert Array of Strings to Array of Symbols

strings = ["HTML", "CSS", "JavaScript", "Python", "Ruby"]
symbols = [ ]

strings.each do |s| 
    symbols.push s.to_sym
end

puts symbols