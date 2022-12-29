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
    ;   3. When an empty line is encountered, compare that to a "greatest-seen"
    ;      running total, updating that total accordingly
    ;
    ; At the end, the "greatest-seen" total will be the maximum Calories that
    ; any elf is carrying.
    (defn calc-max-total
        "Returns the caloric maximum in the given collection"
        [calorie-list]
        (loop
            [
                calorie-list calorie-list
                max-total 0
                curr-total 0
            ]
            (let [line (first calorie-list)]
                (if (empty? calorie-list)
                    ; Return the largest total calculated.
                    max-total
                    ; Else, recur with the rest of the list and updated totals.
                    (if (not (empty? line))
                        ; On a non-empty line, add to the current total.
                        (recur
                            (rest calorie-list)
                            max-total
                            (+ curr-total (Integer/parseInt line))
                        )
                        ; On an empty line, compare current total and reset it.
                        (recur
                            (rest calorie-list)
                            (max max-total curr-total)
                            0
                        )
                    )
                )
            )
        )
    )

    ; Read input file as a lazy sequence and print the maximum Calories.
    (with-open [rdr (clojure.java.io/reader input-file)]
        (println (str "Day 1: " (calc-max-total (line-seq rdr))))
    )
)

(defn -main
    "Runs all attempted puzzles"
    [& args]
    (day-1 "resources/day_1/input")
)
