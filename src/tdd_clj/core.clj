(ns tdd-clj.core
  (:require [compojure.core :refer :all]
            [compojure.route :as route]))

(defroutes app
  (GET "/" [] "Hello World")
  (route/not-found "<h1>Page not found</h1>"))
