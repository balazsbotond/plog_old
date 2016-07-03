(ns plog.pages.home
  (:require [plog.pages.base :refer [base]]
            [hiccup.page :refer :all]
            [markdown.core :refer [md-to-html-string]])
  (:gen-class))

(defn pretty-date
  [date]
  (.format
   (java.text.SimpleDateFormat.
    "d MMM yyyy HH:mm"
    (java.util.Locale/getDefault))
   date))

(defn post-form
  [{:keys [servlet-context]}]
  [:form {:method "POST" :action (str servlet-context "/")}
   [:div.form-group
    [:textarea#text.form-control
     {:rows 3 :placeholder "Write something in Markdown..."}]]
   [:div.input-group
    [:input#tags.form-control.input-sm {:placeholder "tag1, tag2, tag3..."}]
    [:span.input-group-btn
     [:button.btn.btn-primary.btn-sm {:type "submit"} "Post"]]]])

(defn tag-item
  [tag]
  (list
   [:a {:href (str "/search?tag=" tag)}
    [:span.label.label-info tag]] " "))

(defn post-date
  [post]
  [:p.text-muted
   {:title (str "Last modified: " (pretty-date (:last_modified post)))}
    [:small (pretty-date (:created post))]])

(defn post-controls
  [post]
  [:small.text-muted {:style "float:right"}
   [:span.glyphicon.glyphicon-pencil
    {:data-id (:id post) :aria-hidden "true" :aria-label "Edit Post"}] " "
   [:span.glyphicon.glyphicon-trash
    {:data-id (:id post) :aria-hidden "true" :aria-label "Delete Post"}]])

(defn post-item
  [post]
  (list
   [:hr]
   (post-date post)
   (md-to-html-string (:text post))
   [:p
    (for [tag (:tags post)] (tag-item tag))
    (post-controls post)]))

(defn pager
  []
  [:nav  
   [:ul {:class "pager"}
    [:li {:class "previous disabled"}
     [:a {:href "#"}
      [:span {:aria-hidden "true"} "←"]" Newer"]]
    [:li {:class "next"}
     [:a {:href "#"} "Older " 
      [:span {:aria-hidden "true"} "→"]]]]])

(defn home
  [{:keys [posts] :as params}]
  (base
   params
   nil
   (list
    (post-form params)
    (for [post posts] (post-item post))
    (pager))))

