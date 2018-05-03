(ns tdd-clj.cljs.core-test
  (:require [tdd-clj.cljs.core :refer :all]
            [cljs.test :as t :include-macros true]
            [devcards.core :as devcards]
            [cljs-react-test.utils :as tu]
            [cljs-react-test.simulate :as sim]
            [dommy.core :as dommy :refer-macros [sel1]]
            [reagent.core :as r]))

(def ^:dynamic c)

(devcards/deftest click-login-test
  (t/testing "can click"
    (let [app-state (r/atom {:logged-in? false})
          _ (r/render [log-in-button app-state] c)
          node (sel1 c [:button])]
      (t/is (= false (:logged-in? @app-state)))
      (sim/click node nil)
      (t/is (= true (:logged-in? @app-state))))))

