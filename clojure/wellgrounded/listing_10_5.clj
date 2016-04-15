(defn next-big-n [n] (let [new-val (+ 1 n)]
  (lazy-seq
    (cons new-val (next-big-n new-val))
  )))

(defn natural-k [k]
(concat [k] (next-big-n k)))
