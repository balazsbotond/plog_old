(ns plog.pages.base
  (:require [hiccup.page :refer :all]))

(defn cond-comment
  [condition & content]
  (list "<!--[" condition "]>" content "<![endif]-->"))

(defn nav-item
  [servlet-context for-page current-page link text]
  [:li {:role "presentation"
        :class (if (= for-page current-page) "active")}
   [:a {:href (str servlet-context "/" link)} text]])

(defn header
  [servlet-context page]
  [:div.header.clearfix
   [:nav
    [:ul.nav.nav-pills.pull-right
     (nav-item servlet-context "home" page "" "Write")
     (nav-item servlet-context "search" page "" "Search")
     (nav-item servlet-context "about" page "about" "About")]]
   [:h3.text-muted "plog"]])

(defn base
  [{:keys [servlet-context page]} page-scripts content]
  (html5
   [:head
    [:meta {:charset "utf-8"}]
    [:meta {:http-equiv "X-UA-Compatible" :content "IE=edge"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
    [:meta {:name "description" :content "plog - Personal Log"}]
    [:meta {:name "author" :content "Botond Bal&aacute;zs"}]
    ;; [:link {:rel "icon" :href "favicon.ico"}]
    [:title "plog - Your Personal Log"]
    (include-css "/assets/bootstrap/css/bootstrap.min.css")
    (include-css "/css/ie10-viewport-bug-workaround.css")
    (include-css "/assets/font-awesome/css/font-awesome.min.css")
    (include-css "/css/screen.css")
    (cond-comment
     "if lt IE 9"
     (include-js "https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js")
     (include-js "https://oss.maxcdn.com/respond/1.4.2/respond.min.js"))]
   [:body
    [:div.container
     (header servlet-context page)]
    [:div.container content]
    (include-js "/assets/jquery/jquery.min.js")
    (include-js "/assets/tether/dist/js/tether.min.js")
    (include-js "/assets/bootstrap/js/bootstrap.min.js")
    [:script {:type "text/javascript"}
     "var context = \"" servlet-context "\";"]
    page-scripts]))
