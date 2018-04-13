(ns tdd-clj.clj.core
  (:require [compojure.core :refer :all]
            [clojure.data.json :as json]
            [ring.util.codec :refer [form-decode]]
            [tdd-clj.clj.backend :refer [square]]
            [compojure.route :as route]))

(defn handle-post [req]
  (let [{:keys [query-string]} req]
    (json/write-str (merge {:received? true} (form-decode query-string)))))

(defn handle-home [] "Hello World")

(defn handle-square [{:keys [num]}]
  (str "Square is " (square (Integer. num))))

(defroutes app
  (GET "/" [] (handle-home))
  (GET "/square/:num" {:keys [params]} (handle-square params))
  (POST "/posthere" req (handle-post req))
  (route/not-found "Page Not Found"))
