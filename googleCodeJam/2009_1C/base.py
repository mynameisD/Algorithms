def process(string):
    dtos,stod = {},{}
    stod[string[0]] = 1
    dtos[1] = string[0]
    for i in string:
        if i not in stod:
            for n in range(62):
                if n not in dtos:
                    stod[i] = n
                    dtos[n] = i
                    break
    base = max(dtos.keys())+1
    return convert(string,stod,base)

def convert(string,stod,base):
    res = 0
    for i in string:
        res *= base
        res += stod[i]
    return res


import sys
f = sys.stdin
cases = int(f.readline())
for case in range(1,cases+1):
    num  = f.readline().rstrip("\n")
    res = process(num)
    print "Case #%d: %d" % (case, res)
