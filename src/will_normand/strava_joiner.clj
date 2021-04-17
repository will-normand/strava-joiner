(ns will-normand.strava-joiner
  (:require [clojure.java.io :as io]
            [clojure.string :as str])
  (:gen-class))

(defn earlier-part [lines]
  (take-while #(not (str/ends-with? % "</Track>")) lines))

(defn later-part [lines]
  (drop-while #(not (str/ends-with? % "<Track>")) lines))

(defn get-file-part [filename part-fn]
  (with-open [r (io/reader filename)]
    (into [] (part-fn (line-seq r)))))

(defn merge-files [first-filename second-filename]
  (concat (get-file-part first-filename earlier-part)
          (get-file-part second-filename later-part)))

(defn write-file [lines] (spit "resources/output.tcx" (str/join "\n" lines)))

(defn strava-join [first-filename second-filename]
  (-> (merge-files first-filename second-filename)
      (write-file)))

(defn validate-args [args]
  (assert (= (count args) 2) "Usage: strava-joiner first-file.tcx second-file.tcx"))

(defn -main
  [& args]
  (validate-args args)
  (let [first-filename (first args)
        second-filename (second args)]
    (strava-join first-filename second-filename)))
