filename = ARGV.first

puts "I'm now going to print the contents of '#{filename}'."

contents = File.open(filename)
puts contents.read()
contents.close()
