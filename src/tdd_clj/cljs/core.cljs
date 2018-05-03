(ns tdd-clj.cljs.core
  (:require
   [devcards.core :as dc]
   [reagent.core :as r]
   [sablono.core :as sab])
  (:require-macros
   [devcards.core :refer [defcard]]))

(defonce app-state (r/atom {:logged-in? false}))

(defn logged-in-view []
  (sab/html [:h1 "Logged in!"]))

(defn not-logged-in-view []
  (sab/html [:h1 "Not logged in!"]))

(defn log-in-container [state]
  (if (:logged-in? @state)
    [logged-in-view]
    [not-logged-in-view]))

(defcard logged-in
  (let [state (atom {:logged-in? true})]
    (r/as-element [log-in-container state])))

(defcard not-logged-in
  (let [state (atom {:logged-in? false})]
    (r/as-element [log-in-container state])))

(defn log-in-button [state]
  [:div
   [:input {:type "button" :value "Log in"
            :on-click (fn [e] (swap! state update :logged-in? #(not %)))}]])

(defcard with-btn
  (r/as-element [:div [log-in-container app-state]
   [log-in-button app-state]]))
