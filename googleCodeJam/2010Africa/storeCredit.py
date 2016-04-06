def buy(items,val):
    things = [[v,i+1] for i,v in enumerate(items)]
    things.sort()
    l,r = 0, len(things)-1
    while l < r:
        total = things[l][0] + things[r][0]
        if total == val: return [things[l][1],things[r][1]]
        elif total < val: l += 1
        elif total > val: r -= 1
    return [0,0]

def handler():
    fin = open("/Users/dixu/Desktop/CodeJam/large","r")
    fout = open("/Users/dixu/Desktop/CodeJam/result.txt","w")
    Ncase = int(fin.readline())
    for i in range(Ncase):
        val = int(fin.readline())
        n = fin.readline()
        items = map(int,fin.readline().split(" "))
        ind1,ind2 = buy(items,val)
        if ind2 < ind1: ind1,ind2 = ind2,ind1
        line = "Case #"+str(i+1)+": "+str(ind1)+" "+str(ind2)+"\n"
        fout.write(line)
        
handler()
