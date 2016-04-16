
def circle(bff,ids,pairs):
    maxcircle = 0
    for i in range(1,len(ids)):
        if ids[i] == 1 and i not in pairs:
            cur = i
            path = {cur}
            while bff[cur] not in pairs and bff[cur] not in path and ids[bff[cur]] <= len(path):
                cur = bff[cur]
                path.add(cur)
                ids[cur] = len(path)
            if bff[cur] in pairs:
                pairs[bff[cur]] = max(pairs[bff[cur]], 1 + len(path))
            elif bff[cur] in path:
                maxcircle = max(maxcircle,ids[cur]-ids[bff[cur]]+1)
    total = 0
    for key in pairs:
        total += pairs[key]
    return max(total,maxcircle)
            

import sys
f = sys.stdin
cases = int(f.readline())
for case in range(cases):
    n = int(f.readline())
    line = map(int, f.readline().split(" "))
    bff = {}
    for index in range(len(line)):
        bff[index+1] = line[index]
    ids = [1]*(1+len(line))
    pairs = {}
    for key in bff:
        if bff[bff[key]] == key:
            pairs[key] = 1
            pairs[bff[key]] = 1
    ans = circle(bff,ids,pairs)
    print "Case #%d: %d" % (case+1,ans)
