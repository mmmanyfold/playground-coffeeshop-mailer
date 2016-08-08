(ns playground-coffee-mailer.env
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [playground-coffee-mailer.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[playground-coffee-mailer started successfully using the development profile]=-"))
   :stop
   (fn []
     (log/info "\n-=[playground-coffee-mailer has shut down successfully]=-"))
   :middleware wrap-dev})
