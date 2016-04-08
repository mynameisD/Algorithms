import collections
    
def process(board):
    row,col = len(board),len(board[0])
    dp = [[1]*col for _ in range(row)]
    dic = collections.defaultdict(list)
    for i in range(row):
        for j in range(col):
            if i > 0 and j > 0:
                if board[i][j] == board[i-1][j-1] and board[i][j] != board[i-1][j] and board[i][j] != board[i][j-1]:
                    dp[i][j] = 1 + min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])
            for k in range(1,dp[i][j]+1):
                dic[k].append([i,j])
     
    res = {}
    for key in sorted(dic)[::-1]:
        for x,y in dic[key]:
            if board[x][y] != "." and board[x-key+1][y] != "." and \
              board[x-key+1][y-key+1] != "." and board[x][y-key+1] != ".":
                if key not in res:
                    res[key] = 1
                else:
                    res[key] += 1
                for i in range(x-key+1,x+1):
                    for j in range(y-key+1,y+1):
                        board[i][j] = "."

    return res


    


import sys
f = sys.stdin
cases = int(f.readline())
for case in range(1,cases+1):
    M,N = map(int,f.readline().split(" "))
    board = []
    for i in range(M):
        string = bin(int(f.readline(),16))[2:]
        string = "0"*(N-len(string))+string
        board.append([i for i in string])
    ans = process(board)
    print "Case #%d: %d" % (case,len(ans))
    for key in sorted(ans)[::-1]:
        print "%d %d" % (key,ans[key])
