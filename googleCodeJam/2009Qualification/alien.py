trie = {}

def addtrie(string):
    cur = trie
    for i in string:
        if i not in cur:
            cur[i] = {}
        cur = cur[i]
    if "#" not in cur: cur["#"]="markend"

def search(array,cur,res):
    if not array:
        if "#" in cur: res.append(1)
        return
    for i in array[0]:
        if i in cur:
            search(array[1:],cur[i],res)

        
import sys
f = sys.stdin
fout = open("/Users/dixu/Desktop/Algorithms/googleCodeJam/2009Qualification/result.txt","w")
l,n,nn = map(int,f.readline().split(" "))

for i in range(n):
    string = f.readline().rstrip()
    addtrie(string)
    

for i in range(nn):
    string = f.readline().rstrip()
    stack,temp = [],""
    left = 0
    for j in string:
        if j == "(": left +=1
        elif j == ")":
            left -= 1
            stack.append(temp)
            temp = ""
        elif left > 0:
            temp += j
        else:
            stack.append(j)
    ways = []
    search(stack,trie,ways)
    fout.write("Case #%d: %d\n" % (i+1,len(ways)))     
