import sys
import math
f = sys.stdin
n = int(f.readline())
ratio = (1.0+math.sqrt(5))/2.0
for i in range(n):
    count = 0
    a,b,c,d = map(int,f.readline().split(" "))
    for j in range(a,b+1):
        lower = j/ratio
        upper = j*ratio
        if c >= upper or d <= lower:
            count += d - c + 1
        else:
            if c <= lower:
                count += int(lower-c+1)
            if d >= upper:
                count += int(d - upper+1)
    print "Case #%d: %d" % (i+1,count)
