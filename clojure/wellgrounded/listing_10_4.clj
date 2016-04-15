(defn schwartz [x f]
  (map #(nth %1 0)
    (sort-by #(nth %1 1)
      (map #(let [w %1]
        (list w (f w))) x)
    )))
