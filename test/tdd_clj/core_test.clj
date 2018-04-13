(ns tdd-clj.core-test
  (:require [clojure.test :refer :all]
            [tdd-clj.core :as core]
            [ring.mock.request :as mock]))

(deftest simple-get-test
  (is (= (core/handler (mock/request :get "/"))
         {:status 200
          :headers {"Content-Type" "text/html"}
          :body "Hello World"})))
