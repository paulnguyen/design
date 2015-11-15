from_file, to_file = ARGV

puts "Copying from #{from_file} to #{to_file}."

# we could do these two on one line, how?
input = File.open(from_file)
indata = input.read()

output = File.open(to_file, 'w')
output.write(indata)

puts "Alright, all done."

output.close()
input.close()
