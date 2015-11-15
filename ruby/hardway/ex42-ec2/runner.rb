#!/usr/bin/env ruby

$: << File.expand_path(File.dirname(__FILE__))

require 'util'
require 'map'
require 'engine'

include Util

a_game = Engine.new(Map.new, :central_corridor)
a_game.play
