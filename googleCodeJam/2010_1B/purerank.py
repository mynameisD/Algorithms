import sys
import math

visited = {}
c = [[0]*501 for _ in range(501)]
c[0][0] = 1

def calcom():
    for i in range(1,501):
        c[i][0] = 1
        for j in range(1,i+1):
            c[i][j] = (c[i-1][j-1] + c[i-1][j]) % 100003

calcom()

def combination(n,l):
    # l has to be in the subset, possible location at index 1,2,3,.l-1
    # assume n = 10, l=4, if we put l at index 1. then [3,x,x,10]
    # x has to be filled with any number between (3,10), total choice C(10-3-1,4-1-1)
    # if we put l at index  2, then [x,3,x,10], it equals combination(3,2) * choice of second x
    # which is C(10-3-1,4-2-1)
    
    if (n,l) in visited: return visited[(n,l)]
    count = 0
    if l == 1 or l == n-1:
        return 1
    for i in range(1,l):
        count += ((combination(l,i) * c[n-l-1][l-i-1]) % 100003)
    visited[(n,l)] = count % 100003
    return visited[(n,l)]
        
    
f = sys.stdin
cases = int(f.readline())
for case in range(1,cases+1):
    count = 0
    n = int(f.readline())
    for i in range(1,n):
        count += combination(n,i)
    print "Case #%d: %d" % (case,count % 100003)
    
