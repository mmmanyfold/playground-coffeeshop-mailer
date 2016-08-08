(ns user
  (:require [mount.core :as mount]
            playground-coffee-mailer.core))

(defn start []
  (mount/start-without #'playground-coffee-mailer.core/repl-server))

(defn stop []
  (mount/stop-except #'playground-coffee-mailer.core/repl-server))

(defn restart []
  (stop)
  (start))


