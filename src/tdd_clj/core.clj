(ns tdd-clj.core
  (:require [compojure.core :refer :all]
            [cheshire.core :as cheshire]
            [tdd-clj.backend :refer [square]]
            [compojure.route :as route]))

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(defn handle-post [body]
  (if body
    {:body (merge {:received? true} (parse-body body))}
    "No params. That's OK"))

(defn handle-home [] "Hello World")

(defn handle-square [{:keys [num]}]
  (str "Square is " (square (Integer. num))))

(defroutes app
  (GET "/" [] (handle-home))
  (GET "/square/:num" {:keys [params]} (handle-square params))
  (POST "/posthere" {:keys [body]} (handle-post body))
  (route/not-found "Page Not Found"))
