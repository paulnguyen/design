# REF: http://ruby-doc.com/docs/ProgrammingRuby/html/lib_patterns.html

# Library: observer
#
# The Observer pattern, also known as Publish/Subscribe, provides a simple mechanism 
# for one object to inform a set of interested third-party objects when its state changes.
#
# In the Ruby implementation, the notifying class mixes in the Observable module, which 
# provides the methods for managing the associated observer objects. 

# add_observer(obj) 	
#   Add obj as an observer on this object. obj will now receive notifications.
# delete_observer(obj) 	
#   Delete obj as an observer on this object. It will no longer receive notifications.
# delete_observers 	
#   Delete all observers associated with this object.
# count_observers 	
#   Return the count of observers associated with this object.
# changed(newState=true) 	
#   Set the changed state of this object. Notifications will be sent only if 
#   the changed state is true.
# changed? 	
#   Query the changed state of this object.
# notify_observers(*args) 	
#   If this object's changed state is true, invoke the update method in each 
#   currently associated observer in turn, passing it the given arguments. 
#   The changed state is then set to false.


# The observers must implement the update method to receive notifications. 

require "observer"

  class Ticker # Periodically fetch a stock price
    include Observable

    def initialize(symbol)
      @symbol = symbol
    end

    def run
      lastPrice = nil
      for i in 1..10
        price = Price.fetch(@symbol)
        print "Current price: #{price}\n"
        if price != lastPrice
          changed   # notify observers
          lastPrice = price
          notify_observers(Time.now, price)
        end
      end
    end
  end

  class Price           ### A mock class to fetch a stock price (60 - 140).
    def Price.fetch(symbol)
        60 + rand(80)
    end
  end

  class Warner
    def initialize(ticker, limit)
      @limit = limit
      ticker.add_observer(self)   # all warners are observers
    end
  end

  class WarnLow < Warner
    def update(time, price)       # callback for observer
      if price < @limit
        print "--- #{time.to_s}: Price below #@limit: #{price}\n"
      end
    end
  end

  class WarnHigh < Warner
    def update(time, price)       # callback for observer
      if price > @limit
        print "+++ #{time.to_s}: Price above #@limit: #{price}\n"
      end
    end
  end

ticker = Ticker.new("MSFT")
WarnLow.new(ticker, 80)
WarnHigh.new(ticker, 120)
ticker.run