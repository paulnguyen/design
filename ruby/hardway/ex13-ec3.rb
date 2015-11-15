# Not a very creative program, but this is a lame exercise.

first, second, third = ARGV

puts "Your first argument was #{first}"

print "What is your name? "

# Lovely:
# http://groups.google.com/group/ruby-talk-google/browse_thread/thread/298fc833a82932b7
name = STDIN.gets.chomp()
puts "Hi #{name}!"
