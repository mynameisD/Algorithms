def number(string):
    dic = {"a":"2","b":"22","c":"222","d":"3","e":"33","f":"333","g":"4","h":"44","i":"444","j":"5", \
           "k":"55","l":"555","m":"6","n":"66","o":"666","p":"7","q":"77","r":"777","s":"7777","t":"8","u":"88",\
           "v":"888","w":"9","x":"99","y":"999","z":"9999"," ":"0"}
    string = string[:-1]
    res, prev = "", None
    for i in string:
        if res and dic[i][0] == prev:
            res += " " 
        prev = dic[i][0]
        res += dic[i]
    return res

    
def handler():
    fin = open("/Users/dixu/Desktop/CodeJam/input")
    fout = open("/Users/dixu/Desktop/CodeJam/result.txt","w")
    n = int(fin.readline())
    for i in range(n):
        line = fin.readline()
        s = number(line)
        fout.write("Case #"+str(i+1)+": " + s +"\n")
handler()
