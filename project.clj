(defproject tdd-clj "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [org.clojure/clojurescript "1.10.238"]
                 [ring/ring-core "1.6.3"]
                 [ring/ring-mock "0.3.2"]
                 [compojure "1.6.0"]
                 [sablono "0.8.3"]
                 [prismatic/dommy "1.1.0"]
                 [reagent "0.8.0-rc1"]
                 [devcards "0.2.4"]
                 [org.clojure/data.json "0.2.6"]
                 [ring/ring-jetty-adapter "1.6.3"]]
  :ring {:handler tdd-clj.core/app}
  :plugins [[lein-ring "0.12.1"]
            [lein-auto "0.1.3"]
            [lein-figwheel "0.5.15"]
            [lein-cljsbuild "LATEST"]]
  :clean-targets [:target-path ["out/cljs" "resources/public/js"]]
  :cljsbuild {
              :builds [{:id "dev"
                        :source-paths ["src/tdd_clj/cljs"]
                        :figwheel true
                        :compiler {:main "tdd-clj.cljs.core"}}
                       {:id "devcards"
                        :source-paths ["src/tdd_clj/cljs"]
                        :figwheel {:devcards true}
                        :compiler {:main tdd-clj.cljs.core
                                   :asset-path "js/devcards_out"
                                   :output-to "resources/public/js/tdd_clj_devcards.js"
                                   :output-dir "resources/public/js/devcards_out"
                                   :source-map-timestamp true}}]
              }
  )
