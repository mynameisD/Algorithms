
import sys
f = sys.stdin
cases = int(f.readline())
for case in range (1,1+cases):
    K,C,S = map(int,f.readline().split(" "))
    res = []
    for i in range(1,K+1,C):
        p = 1
        for j in range(C):
            p = (p-1)*K + min(i+j,K)
        res.append(p)
    
    if len(res) > S:
        print "Case #%d: IMPOSSIBLE" % (case)
    else:
        print "Case #%d: %s" % (case," ".join(map(str,res)))        
