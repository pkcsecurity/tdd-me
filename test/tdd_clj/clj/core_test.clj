(ns tdd-clj.clj.core-test
  (:require [clojure.test :refer :all]
            [tdd-clj.clj.core :as core]
            [clojure.data.json :as json]
            [ring.mock.request :as mock]))

(deftest simple-get-test
  (testing "simple get with no params"
    (let [response (core/app (mock/request :get "/"))]
      (is (= "Hello World" (response :body))
          (= 200 (response :status)))))
  (testing "simple get with params"
    (let [response (core/app (mock/request :get "/square/5"))]
      (is (= "Square is 25" (response :body))
          (= 200 (response :status))))))


(deftest simple-post-test
  (testing "simple post with params"
    (let [params {:param1 "one one" :another 7}
          request (-> (mock/request :post "/posthere" )
                      (mock/content-type "application/json")
                      (mock/query-string {:param1 "one one" :another 7}))
          response (core/app request)]
      (is (= true ((json/read-str (:body response)) "received?")))
      (= 200 (response :status)))))

(deftest not-found-test
  (testing "not found"
    (let [response (core/app (mock/request :get "/lol"))]
      (is (= "Page Not Found" (response :body))
          (= 200 (response :status))))
    (let [response (core/app (mock/request :post "/lol"))]
      (is (= "Page Not Found" (response :body))
          (= 200 (response :status))))))
