dic = {}

def power(n):
    if n in dic: return dic[n]
    if n == 0:
        return 1,0
    a,b = power(n-1)
    dic[n] = [(3*a+5*b)%1000,(a+3*b)%1000]
    return dic[n]

seen = {}
def findrepeat():
    for i in range(100000):
        a,b = power(i)
        if (a,b) in seen:
            return seen[(a,b)],i
            break
        seen[(a,b)] = i

start, end = findrepeat()
cycle = end - start
print cycle

import sys

f = sys.stdin
nline = int(f.readline())

fout = open("/Users/dixu/Desktop/Algorithms/googleCodeJam/2008_1A/result","w")

for i in range(nline):
    ind = int(f.readline())
    if ind > cycle:
        ind %= cycle 
        ind += cycle
    res = (2*power(ind)[0] - 1)%1000
    fout.write("Case #%d: %03d\n" % (i+1, res))
    
