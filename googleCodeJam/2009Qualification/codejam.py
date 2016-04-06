
    # welcome to code jam
    # 0123456789012345678
    # [w,we,wel,welc,welco,welcom,welcome,welcome ,welcome t,welcome to,welcome to ,welcome to c,welcome to co, welcome to cod, welcome to cod,]
dic = {}
for i,l in enumerate("welcome to code jam"):
    if l in dic:
        dic[l].append(i+1)
    else:
        dic[l] = [i+1]
        
word = "wweellccoommee to code qps jam"

import sys
f = sys.stdin
n = int(f.readline())
fout = open("/Users/dixu/Desktop/Algorithms/googleCodeJam/2009Qualification/result.txt","w")
for line in range(1,n+1):
    word = f.readline()
    dp = [1]+[0] * 19
    for letter in word:
        if letter in dic:
            for index in dic[letter]:
                dp[index] += dp[index-1]
    res = str(dp[-1])[-4:]
    res = int(res)
    fout.write("Case #%d: %04d\n" % (line,res))


        
    
    
