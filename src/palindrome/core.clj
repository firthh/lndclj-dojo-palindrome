(ns palindrome.core)

(defn is-palindrome?
  [input]
  (->> input
       (reverse)
       (apply str)
       (= (apply str input))))

(defn empty?
  [input]
  (not (pos? (count input))))

(defn break-string
  [input]
  (loop [string input
         acc []]
    (if (empty? string)
      (map (partial apply str) acc)
      (recur (rest string) (conj acc string)))))

(defn generate-combinations
  [input]
  (->> (break-string input)
       (mapcat #(break-string (reverse %)))
       (map #(apply str (reverse %)))))

;; another attempt

(defn largest
  [a b]
  (let [a-count (count a)
        b-count (count b)]
    (if (> a-count b-count)
      a
      b)))

(defn first-palindrome
  [input]
  (if (is-palindrome? input)
    input
    (largest (first-palindrome (rest input))
             (first-palindrome (rest (reverse input))))))

(time (sort-by count (filter is-palindrome? (generate-combinations "abcdefghilkdsafjlkjdsabalkfhdsakjfhlanfkjsadlkfdsjajdsfkjdsjfkjdkjskjfdkjdfkjkjfdkjabcdefghilkdsafjlkjdsabalkfhdsakjfhlanfkjsadlkfdsjajdsfkjdsjf"))))

(first-palindrome "asdfaba")
