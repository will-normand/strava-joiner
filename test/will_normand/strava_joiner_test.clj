(ns will-normand.strava-joiner-test
  (:require [clojure.test :refer :all]
            [will-normand.strava-joiner :refer :all]
            [clojure.string :as str]))

(defn count-in-coll [s coll] (reduce + (map #(if (str/includes? % s) 1 0) coll)))

(deftest earlier-part-test
  (let [file (str/split-lines (slurp "resources/activity1.tcx"))
        earlier (earlier-part file)]
    (testing "earlier-part ends with </Trackpoint>"
      (is (= "</Trackpoint>" (str/trim (last earlier)))))
    (testing "no trackpoints are lost"
      (is (= (count-in-coll "Trackpoint" file)
             (count-in-coll "Trackpoint" earlier))))))