(ns plog.routes.home
  (:require [plog.layout :as layout]
            [plog.pages.home :refer [home]]
            [plog.pages.about :refer [about]]
            [plog.db.core :as db]
            [bouncer.core :as b]
            [bouncer.validators :as v]
            [compojure.core :refer [defroutes GET POST]]
            [ring.util.http-response :as response]
            [ring.util.response :refer [redirect]]
            [hiccup.page :as hiccup]
            [clojure.java.io :as io]))

(defn validate-post [post]
  (first
   (b/validate post :text v/required)))

(defn save-post! [{:keys [params]}]
  (if-let [errors (validate-post params)]
    (-> (response/found "/")
        (assoc :flash (assoc params :errors errors)))
    (do (db/create-post! (:text params))
        (response/found "/"))))

(defn home-page [{:keys [flash]}]
  (layout/render
   home
   (merge {:posts (db/get-posts {:limit 10 :offset 0})
           :page "home"}
          (select-keys flash [:name :message :errors]))))

(defn about-page []
  (layout/render about {:name "Botond" :page "about"}))

(defroutes home-routes
  (GET "/" request (home-page request))
  (POST "/" request (save-post! request))
  (GET "/about" [] (about-page)))

