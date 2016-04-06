def dfs(b,i,j,label, basin):
    if basin[i][j] != "#":
        return basin[i][j]
    stackx,stacky,stackB = [],[],[]
    for dx,dy in [[-1,0],[0,-1],[0,1],[1,0]]:
        x,y = i+dx,j+dy
        if 0 <= x < len(basin) and 0 <= y < len(basin[0]):
            stackx.append(x)
            stacky.append(y)
            stackB.append(b[x][y])
    if stackB:        
        minimum = min(stackB)
        if minimum < b[i][j]:
            index = stackB.index(minimum)
            mark = dfs(b, stackx[index],stacky[index],label,basin)
            basin[i][j] = mark
            return mark
        
    temp = label[0]
    basin[i][j] = temp
    return temp


import string
import sys
f = sys.stdin
fout = open("/Users/dixu/Desktop/Algorithms/googleCodeJam/2009Qualification/result.txt","w")
n = int(f.readline())
for k in range(n):
    x,y = f.readline().split(" ")
    x,y = int(x),int(y)
    board = []
    for i in range(x):
        vals = map(int,f.readline().split(" "))
        board.append(vals)
    basin = [["#"]*y for _ in range(x)]
    label = string.lowercase
    for i in range(x):
        for j in range(y):
            if basin[i][j] == "#":
                m = dfs(board,i,j,label,basin)
                if m in label: label = label[1:]

    fout.write("Case #%d:\n" % (k+1))
    for i in range(x):
        fout.write(" ".join(basin[i])+"\n")
    
    
