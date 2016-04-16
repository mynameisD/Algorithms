
import sys
import collections
f = sys.stdin
cases = int(f.readline())
for case in range(1,1+cases):
    N = int(f.readline())
    count = collections.defaultdict(int)
    for i in range(2*N-1):
        line = map(int,f.readline().split(" "))
        for j in line:
            count[j] += 1
    res = []
    for key in count:
        if count[key]%2==1:
            res.append(key)
    res.sort()
    res = map(str,res)
    print "Case #%d: %s" % (case," ".join(res))
        
