(ns get-page-title-of-block.1
  (:require [roam.datascript.reactive :as dr]))

(defn get-daily-note-of-block [block-uid]
  (let [*pull (dr/pull '[{:block/page [:node/title]}]
    [:block/uid block-uid])]
    (get-in @*pull [:block/page :node/title])))

(defn get-date-of-dnp [dnp]
  (js/roamAlphaAPI.util.pageTitleToDate dnp))

(def weekdays
  {0 "Sunday"
   1 "Monday"
   2 "Tuesday"
   3 "Wednesday"
   4 "Thursday"
   5 "Friday"
   6 "Saturday"})

(defn main [{:keys [block-uid]}]
  (let [today (->> block-uid
                     get-daily-note-of-block
                     get-date-of-dnp
                     .getDay
                     weekdays)]
    [:div "Today is " today]))
