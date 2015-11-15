filename = ARGV.first

prompt = "> "
txt = File.open(filename)

puts "Here's your file: #{filename}"

txt.readlines.each do |line|
  p line
end
