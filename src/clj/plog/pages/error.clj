(ns plog.pages.error
  (:require [hiccup.page :refer :all])
  (:gen-class))

(defn error
  [{:keys [status title message]}]
  (html5
   [:head
    [:title "Something bad happened"]
    [:meta {:http-equiv "Content-Type" :content "text/html; charset=UTF-8"}]
    [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
    (include-css "/assets/bootstrap/css/bootstrap.min.css")
    (include-css "/assets/bootstrap/css/bootstrap-theme.min.css")
    [:style {:type "text/css"}
     "html {
          height: 100%;
          min-height: 100%;
          min-width: 100%;
          overflow: hidden;
          width: 100%;
      }
      html body {
          height: 100%;
          margin: 0;
          padding: 0;
          width: 100%;
      }
      html .container-fluid {
          display: table;
          height: 100%;
          padding: 0;
          width: 100%;
      }
      html .row-fluid {
          display: table-cell;
          height: 100%;
          vertical-align: middle;
      }"]]
   [:body
    [:div.container-fluid
     [:div.row-fluid
      [:div.col-lg-12
       [:div.centering.text-center
        [:div.text-center
         [:h1 [:span.text-danger "Error: " status]]
         [:hr]
         (if title [:h2.without-margin title])
         (if message [:h4.text-danger message])]]]]]]))
