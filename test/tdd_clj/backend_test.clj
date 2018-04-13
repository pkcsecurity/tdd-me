(ns tdd-clj.backend-test
  (:require [clojure.test :refer [deftest testing is]]
            [tdd-clj.backend :as b]))

(deftest square-test
  (testing "does it right"
    (is (= 9 (b/square 3)))))

