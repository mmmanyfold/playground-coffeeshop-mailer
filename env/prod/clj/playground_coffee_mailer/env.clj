(ns playground-coffee-mailer.env
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[playground-coffee-mailer started successfully]=-"))
   :stop
   (fn []
     (log/info "\n-=[playground-coffee-mailer has shut down successfully]=-"))
   :middleware identity})
