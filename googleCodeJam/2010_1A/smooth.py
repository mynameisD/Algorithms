#reference: http://blog.csdn.net/yellowxz/article/details/11805893

def solve(nums,D,I,M,N):
    dp = [[0]*256 for _ in range(N+1)]
    for i in range(1,N+1):
        for j in range(256):
            tmp = dp[i-1][j] + D
            if M == 0:
                tmp = min(tmp, dp[i-1][j] + abs(nums[i-1]-j))
            else:
                for k in range(256):
                    diff = abs(j-k)
                    insert = (diff - 1) / M if diff > 0 else 0
                    cost = dp[i-1][k] + insert*I + abs(nums[i-1]-j)
                    if cost < tmp:
                        tmp = cost
            dp[i][j] = tmp
    return min(dp[-1])

    

import sys
f = sys.stdin
fout = open("/Users/dixu/Desktop/Algorithms/googleCodeJam/2010_1A/result.txt","w")
line = int(f.readline())
for i in range(1,line+1):
    D,I,M,N= map(int,f.readline().split(" "))
    nums = map(int,f.readline().split(" "))
    cost = solve(nums,D,I,M,N)
    print i
    fout.write("Case #%d: %d\n" % (i,cost))
