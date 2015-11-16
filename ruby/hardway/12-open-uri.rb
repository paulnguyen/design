require 'open-uri'

open("http://this-page-intentionally-left-blank.org/") do |f|
  f.each_line {|line| p line }
  puts "\n\n"
  puts "BASE URI: #{f.base_uri}"
  puts "CONTENT TYPE: #{f.content_type}"
  puts "CHARSET: #{f.charset}"
  puts "CONTENT ENCODING: #{f.content_encoding}"
  puts "LAST MODIFIED: #{f.last_modified}"
end
