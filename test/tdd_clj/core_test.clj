(ns tdd-clj.core-test
  (:require [clojure.test :refer :all]
            [tdd-clj.core :as core]
            [ring.mock.request :as mock]))

(deftest simple-get-test
  (testing "simple get"
    (let [response (core/app (mock/request :get "/"))]
      (is (= "Hello World" (response :body))
          (= 200 (response :status))
          ))))
