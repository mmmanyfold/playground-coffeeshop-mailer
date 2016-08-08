(ns playground-coffee-mailer.middleware
  (:require [playground-coffee-mailer.env :refer [defaults]]
            [playground-coffee-mailer.config :refer [env]]
            [ring.middleware.flash :refer [wrap-flash]]
            [immutant.web.middleware :refer [wrap-session]]
            [ring.middleware.cors :refer [wrap-cors]]
            [ring.middleware.format :refer [wrap-restful-format]]
            [ring.middleware.defaults :refer [site-defaults wrap-defaults]]))

(defn wrap-formats [handler]
  (let [wrapped (wrap-restful-format
                  handler
                  {:formats [:json-kw :transit-json :transit-msgpack]})]
    (fn [request]
      ;; disable wrap-formats for websockets
      ;; since they're not compatible with this middleware
      ((if (:websocket? request) handler wrapped) request))))

(defn wrap-base [handler]
  (-> ((:middleware defaults) handler)
      wrap-formats
      wrap-flash
      (wrap-session {:cookie-attrs {:http-only true}})
      (wrap-defaults
        (-> site-defaults
            (assoc-in [:security :anti-forgery] false)
            (dissoc :session)))
      (wrap-cors
        :access-control-allow-origin [#"http://dev.playgroundcoffeeshop.com" #"http://playgroundcoffeeshop.com"]
        :access-control-allow-methods [:get :put :post :delete])))
