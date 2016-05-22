strip:{x except "[]():,;.-0123456789"}
us: strip each lower raze " " vs/: read0`:input.txt
s: desc count each group us except enlist ""
`:solution.txt 0: (key s) ,' "|" ,' (string value s)