import sys
#O(n2)
f = sys.stdin
cases = int(f.readline())
for case in range(1,cases+1):
    ropes = int(f.readline())
    stack = []
    for _ in range(ropes):
        stack.append(map(int,f.readline().split(" ")))
    stack.sort(key = lambda x:x[1])
    RIGHT = {}
    for i,(x,y) in enumerate(stack):
        RIGHT[(x,y)] = i
    stack.sort()
    count = 0
    for i,(x,y) in enumerate(stack):
        for j in range(i):
            a,b = stack[j]
            if RIGHT[(a,b)] > RIGHT[(x,y)]:
                count += 1
    res = count
    print "Case #%d: %d" % (case,res)
