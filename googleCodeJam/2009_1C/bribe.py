import sys
f = sys.stdin
cases = int(f.readline())
for case in range(1,1+cases):
    np,n = map(int,f.readline().split(" "))
    stack = map(int,f.readline().split(" "))
    stack = [0]+stack+[np+1] # store the index of prison need to realease
    l,r = 0,len(stack)-1
    dp = [[0]*len(stack) for _ in range(len(stack))]
    for j in range(len(stack)):
        for i in range(j)[::-1]:
            if i + 1 >= j:
                dp[i][j] = 0
            else:
                dp[i][j] = 1<<31
                for k in range(i,j):
                    dp[i][j] = min(dp[i][j], stack[j]-stack[i]-2 + dp[i][k] + dp[k][j])
    print "Case #%d: %d" % (case, dp[0][-1])
            
             
    
