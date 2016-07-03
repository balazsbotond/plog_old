(ns plog.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [plog.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[plog started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[plog has shut down successfully]=-"))
   :middleware wrap-dev})
