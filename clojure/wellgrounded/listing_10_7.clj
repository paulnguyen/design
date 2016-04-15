(import '(java.util.concurrent Executors LinkedBlockingQueue TimeUnit))

(def stpe (Executors/newScheduledThreadPool 2))

(def lbq (LinkedBlockingQueue.))

(def msgRdr (proxy [Runnable] []
  (run [] (.toString (.poll lbq)))
))

(def rdrHndl (.scheduleAtFixedRate stpe msgRdr 10 10 TimeUnit/MILLISECONDS))
