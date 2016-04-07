
def search(q, color, k):
    if not q: return False
    for i in range(len(q)):
        if not q[i]: continue
        for j in range(len(q[i])):
            if q[i][j] == color:
                if any([dfs(q,i,j,k,color,0,1),dfs(q,i,j,k,color,1,-1),dfs(q,i,j,k,color,1,1),dfs(q,i,j,k,color,1,0)]): return True
    return False


def dfs(q,i,j,n,col,dx,dy):
    if n == 0:
        return True
    if i >= len(q) or not q[i] or  j < 0 or j >=len(q[i]) or q[i][j] != col:
        return False
    return dfs(q,i+dx,j+dy,n-1,col,dx,dy)
    


import sys
f = sys.stdin
fout = open("/Users/dixu/Desktop/Algorithms/googleCodeJam/2010_1A/result.txt","w")
n = int(f.readline())
for i in range(n):
    dim,k = f.readline().split(" ")
    dim,k = int(dim),int(k)
    board = []
    for _ in range(dim):
        board.append(f.readline())
    stack=[[] for _ in range(dim)]
    for j in range(dim):
        for l in range(dim-1,-1,-1):
            if board[j][l] != ".":
                stack[j].append(board[j][l])
    red = search(stack,"R",k)
    blue = search(stack,"B",k)
    if not red and not blue: fout.write("Case #%d: Neither\n" % (i+1))
    elif red and blue: fout.write("Case #%d: Both\n" % (i+1))
    elif red: fout.write("Case #%d: Red\n" % (i+1))
    elif blue: fout.write("Case #%d: Blue\n" % (i+1))
