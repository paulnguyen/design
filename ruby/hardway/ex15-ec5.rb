# This way sucks because we can't tab-complete.

puts "What file are we printing today?"
prompt = "> "
print prompt
filename = STDIN.gets.chomp()
txt = File.open(filename)

puts "Here's your file: #{filename}"
puts txt.read()
