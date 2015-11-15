$:.push(File.join(File.dirname(__FILE__), "lib/"))

require "version"

Gem::Specification.new do |s|
  s.name = 'exercise_43'
  s.version = EX42_VERSION
  s.date = '2012-05-26'

  s.summary = 'exercise 43 of Ruby the Hard Way'
  s.description = 'I am the greatest.'

  s.authors = ["Matt Blair"]
  s.email = 'me@matthewblair.net'
  s.homepage = 'https://github.com/mblair/ruby_the_hard_way/tree/master/ex43/'

  s.require_paths = %w[lib]

  s.bindir = %w[bin]
  s.executables = %w[play]

  s.platform = Gem::Platform::RUBY
  # Not really.
  s.required_ruby_version = '>= 1.9.3'

  s.license = 'MIT'

  s.files = `git ls-files`.split("\n")
end
