class Engine
  def initialize(map, start)
    @map = map
    @start = start
  end

  def play
    next_room = @start

    while true
      puts "\n-------"
      room = @map.method(next_room)
      begin
        next_room = room.call
      rescue DeathException => e
        Util.death
      end
    end
  end
end
