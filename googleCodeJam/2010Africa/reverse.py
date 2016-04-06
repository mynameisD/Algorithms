def rev(string):
    string = string.split(" ")
    string[-1] = string[-1][:-1]
    string = string[::-1]
    return " ".join(string)

def handler():
    fin = open("/Users/dixu/Desktop/CodeJam/input","r")
    fout = open("/Users/dixu/Desktop/CodeJam/result.txt","w")
    Ncase = int(fin.readline())
    for i in range(Ncase):
        items = fin.readline()
        res = rev(items)
        line = "Case #"+str(i+1)+": "+ res + "\n"
        fout.write(line)
        
handler()
