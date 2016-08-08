(ns playground-coffee-mailer.routes.api
  (:require [mailgun.mail :as mail]
            [compojure.core :refer [defroutes POST]]
            [compojure.api.sweet :refer [context]]
            [ring.util.http-response :refer :all]))

(def creds {:key    (System/getenv "PLAYGROUND_MAILGUN_API_KEY")
            :domain "playgroundcoffeeshop.com"})

(defn handle-posting-via-mailgun [req]
  (let [{:keys [body subject to from]} (:params req)
        mail-transact!
        (mail/send-mail creds
                        {:from    from
                         :to      to
                         :subject subject
                         :html    body})]
    (if (= (:status mail-transact!) 200)
      (ok mail-transact!)
      (internal-server-error mail-transact!))))

(defroutes api-routes
           (context "/api" []
             (POST "/mail" {params :params} handle-posting-via-mailgun)))
