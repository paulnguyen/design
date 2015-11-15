ROOMS is a hash that maps a room name to a proc for that room's code.

The while loop in the runner method runs a room, then assigns its return value to the next\_one var. The room methods each return the next room, and it loops back.
