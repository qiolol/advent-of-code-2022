(ns aoc-2022.core
  (:gen-class))

(defn day-1
  "Solves https://adventofcode.com/2022/day/1 with the given input file"
  [input-file]
  ; We want to:
  ;
  ;   1. Read the numeric input line by line
  ;   2. For each non-empty line, add the number to a running total for that
  ;      particular elf's carried Calories
  ;   3. When an empty line is encountered (indicating the running total
  ;      represents the total of all Calories carried by a given elf),
  ;      optionally include the running total into a collection of the `n`
  ;      highest totals seen (if it's big enough compared to the ones in the
  ;      collection), and then reset the running total before continuing
  ;
  ; At the end, we'll have the `n` highest caloric totals. For Part 1, we just
  ; need the highest total; for Part 2, we need to sum the highest three.
  (defn include-if-n-largest
    "Adds `candidate` to `coll` if it's larger than the smallest element or if
    `coll` has less than `n` elements"
    [n coll candidate]
    ; debug TODO
    (if (< (count coll))
      (conj coll candidate)

    )
    ; debug TODO
    (let smaller_than_candidate (some #(and (< % candidate) %) coll)

    )
    (if (or (< (count coll) n) (some #(< % candidate) coll))
      (conj coll candidate)
      coll
    )
  )

  (defn calc-max-n-totals
    "Returns the `n`-largest sums of calories in the given `calorie-list`"
    [n calorie-list]
    (loop
      [
        calorie-list calorie-list
        max-n-totals []
        curr-total 0
      ]
      (let [line (first calorie-list)]
        (if (empty? calorie-list)
          ; Return the largest `n` totals calculated.
          max-n-totals
          ; Else, recur with the rest of the list and updated totals.
          (if (not (empty? line))
            ; On a non-empty line, add to the current total.
            (recur
              (rest calorie-list)
              max-n-totals
              (+ curr-total (Integer/parseInt line))
            )
            ; On an empty line, compare current total and reset it.
            (recur
              (rest calorie-list)
              (max max-n-totals curr-total)
              0
            )
          )
        )
      )
    )
  )

  (def calc-max-3-totals (partial calc-max-n-totals 3))

  ; Read input file as a lazy sequence and print the maximum Calories.
  (with-open [rdr (clojure.java.io/reader input-file)]
    (println (str "Day 1: " (calc-max-3-totals (line-seq rdr))))
  )
)

(defn -main
  "Runs all attempted puzzles"
  [& args]
  (day-1 "resources/day_1/input")
)
