(ns plog.pages.about
  (:require [plog.pages.base :refer [base]]))

(defn about
  [{:keys [name] :as params}]
  (base
   params
   nil
   (list
    [:h2 "What is plog and what makes it different?"]
    [:p "A plog is just like a blog, except it is private."]
    [:p "It is a place to record your thoughts, notes and experiences. "]
    [:p "Plog "
     "discourages structuring your posts into an artificial hierarchy using "
     "&quot;categories&quot; or &quot;notebooks&quot;, it instead "
     "makes past entries easily accessible using search, and allows "
     "you to connect similar entries by tagging them. "]
    [:p "If you are "
     "a neat freak like me, you will surely appreciate this subtle "
     "difference from mainstream tools like Evernote or OneNote &ndash; "
     "it allows you to concentrate on writing a note instead of "
     "finding its perfect place inside a convoluted hierarchy "
     "before starting to write anything."]
    [:p "Hope you'll like it!"]
    [:h2 "What technology is plog built on?"]
    [:p "Plog is built using the excellent "
     [:a {:href "http://clojure.org"} "Clojure"] " programming "
     "language and the " [:a {:href "http://luminusweb.net"} "Luminus"]
     " framework. Data is stored in "
     [:a {:href "http://postgresql.org"} "PostgreSQL"] " &ndash; the "
     "world's most advanced open source database."])))
