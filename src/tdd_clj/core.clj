(ns tdd-clj.core
  (:require [compojure.core :refer :all]
            [cheshire.core :as cheshire]
            [compojure.route :as route]))

(defn parse-body [body]
  (cheshire/parse-string (slurp body) true))

(defn handleposthere [body]
  (if body
    {:body (merge {:received? true} (parse-body body))}
    "No params. That's OK"))

(defn handlehome [query-string]
  (if query-string
    (str "Hello query string:" query-string)
    "Hello World"))

(defroutes app
  (GET "/" {:keys [query-string] :as request} (handlehome query-string))
  (POST "/posthere" {:keys [body] :as request} (handleposthere body))
  (route/not-found "Page Not Found"))
