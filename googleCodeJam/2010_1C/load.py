import sys
f = sys.stdin
cases = int(f.readline())
for case in range(1,cases+1):
    lower,upper,factor = map(int,f.readline().split(" "))
    n = 0
    while lower*(pow(factor,pow(2,n))) < upper:
        n += 1
    print "Case #%d: %d" % (case,n)
    
