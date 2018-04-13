(ns tdd-clj.core-test
  (:require [clojure.test :refer :all]
            [tdd-clj.core :as core]
            [cheshire.core :as cheshire]
            [ring.mock.request :as mock]))

(deftest simple-get-test
  (testing "simple get with no params"
    (let [response (core/app (mock/request :get "/"))]
      (is (= "Hello World" (response :body))
          (= 200 (response :status)))))
  (testing "simple get with query string"
    (let [response (core/app (mock/request :get "/" {:hi "ok"}))]
      (is (= "Hello query string:hi=ok" (response :body))
          (= 200 (response :status))))))

(deftest simple-post-test
  (testing "simple post with params"
    (let [params {:param1 "one one" :another 7}
          response (core/app
                    (-> (mock/request :post "/posthere" )
                        (mock/content-type "application/json")
                        (mock/body (cheshire/generate-string params))))]
      (is (= true (get-in response [:body :received?])))
      (= 200 (response :status)))))

(deftest not-found-test
  (testing "not found"
    (let [response (core/app (mock/request :get "/lol"))]
      (is (= "Page Not Found" (response :body))
          (= 200 (response :status))))
    (let [response (core/app (mock/request :post "/lol"))]
      (is (= "Page Not Found" (response :body))
          (= 200 (response :status))))))
