def minproduct(a,b):
    a.sort()
    b.sort()
    res = 0
    for i in range(len(a)):
        res += a[i]*b[len(b)-1-i]
    return res

def solution():
    fin = open("/Users/dixu/Desktop/codejam/input","r")
    fout = open("/Users/dixu/Desktop/codejam/result","w")
    n = int(fin.readline())
    for i in range(n):
        length = fin.readline()
        a = map(int,fin.readline().split(" "))
        b = map(int,fin.readline().split(" "))
        res = minproduct(a,b)
        fout.write("Case #"+str(i+1)+": "+str(res)+"\n")

solution()
