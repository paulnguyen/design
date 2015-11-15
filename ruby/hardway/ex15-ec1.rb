# Grab the filename from the command line.
filename = ARGV.first

# Here's our prompt.
prompt = "> "

# Open that file for reading.
txt = File.open(filename)

# Print out the filename.
puts "Here's your file: #{filename}"

# Print the file's contents.
puts txt.read()

# Why?
puts "I'll also ask you to type it again:"
print prompt

# Grab the filename...again.
file_again = STDIN.gets.chomp()

# Open it.
txt_again = File.open(file_again)

# Print it.
puts txt_again.read()
