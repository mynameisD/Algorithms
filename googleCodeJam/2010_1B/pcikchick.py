def cal(pos,vel,K,T,B):
    count,swap,block  = 0,0,0
    for i in range(len(pos)-1,-1,-1):
        if pos[i]+vel[i]*T >= B:
            swap += block
            count += 1
        else:
            block += 1
        if count >=K:
            break
    if count < K:
        return 1<<31
    else:
        return swap



import sys
f = sys.stdin
line = int(f.readline())
for case in range(1,line+1):
    N,K,B,T = map(int,f.readline().split(" "))
    pos = map(int,f.readline().split(" "))
    vel = map(int,f.readline().split(" "))
    swaps = cal(pos,vel,K,T,B)
    if swaps < (1<<31): print "Case #%d: %d" % (case,swaps)
    else: print "Case #%d: IMPOSSIBLE" % (case)
