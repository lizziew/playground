strip:{x except "[]():,;.-0123456789"} / function to take away punctuation, numbers
us: strip each lower raze " " vs/: read0`:input.txt / take away punctuation, numbers, turn into lowercase 
s: desc count each group us except enlist "" / count word frequency and sort in descending order
`:solution.txt 0: (key s) ,' "|" ,' (string value s) / output to pipe delimited text file
