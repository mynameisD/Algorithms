def milk(n,line):
    line.sort()
    flag = [0]*n
    i = 0
    while i < len(line):
        cur = [-1] * n
        for j in range(len(line[i]))[1::2]:
            cur[line[i][j]-1] = line[i][j+1]
        if any(flag[k] == cur[k] for k in range(n)):
            i += 1
            continue
        else:
            if 1 not in cur: return []
            index = cur.index(1)
            flag[index] = 1
            i = 0
    return flag
            
            
    return [visited[i] if i in visited else 0 for i in range(1,n+1)]
    
def solution():
    fin = open("/Users/dixu/Desktop/codejam/input","r")
    fout = open("/Users/dixu/Desktop/codejam/result","w")
    n = int(fin.readline())
    for i in range(n):
        nmilk = int(fin.readline())
        nman = int(fin.readline())
        line = []
        for j in range(nman):
            line.append(map(int,fin.readline().split(" ")))
        res = milk(nmilk,line)
        if res: fout.write("Case #"+str(i+1)+": "+ " ".join(map(str,res))+"\n")
        else: fout.write("Case #"+str(i+1)+": "+"IMPOSSIBLE\n")
solution()
