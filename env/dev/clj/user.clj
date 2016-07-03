(ns user
  (:require [mount.core :as mount]
            plog.core))

(defn start []
  (mount/start-without #'plog.core/repl-server))

(defn stop []
  (mount/stop-except #'plog.core/repl-server))

(defn restart []
  (stop)
  (start))


