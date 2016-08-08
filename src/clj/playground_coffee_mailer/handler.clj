(ns playground-coffee-mailer.handler
  (:require [compojure.core :refer [routes wrap-routes]]
            [playground-coffee-mailer.routes.services :refer [service-routes]]
            [compojure.route :as route]
            [playground-coffee-mailer.env :refer [defaults]]
            [playground-coffee-mailer.routes.api :refer [api-routes]]
            [mount.core :as mount]
            [playground-coffee-mailer.middleware :as middleware]))

(mount/defstate init-app
                :start ((or (:init defaults) identity))
                :stop  ((or (:stop defaults) identity)))

(def app-routes
  (routes
    #'service-routes
    #'api-routes
    (route/not-found
      "page not found")))


(defn app [] (middleware/wrap-base #'app-routes))
