root = {}

def store(string):
    cur = root
    for i in string:
        if i not in cur:
            cur[i] = {}
        cur = cur[i]

def create(string):
    count, cur = 0, root
    for i in string:
        if i not in cur:
            cur[i] = {}
            count += 1
        cur = cur[i]
    return count
    

import sys
f = sys.stdin
n = int(f.readline())
for case in range(1,n+1):
    nexist,ncreate = map(int,f.readline().split(" "))
    root = {}
    for i in range(nexist):
        string = f.readline().split("/")
        string = string[1:]
        string[-1] = string[-1].rstrip("\n")
        store(string)
    count = 0
    for i in range(ncreate):
        string = f.readline().split("/")
        string = string[1:]
        string[-1] = string[-1].rstrip("\n")
        count += create(string)

    print "Case #%d: %d" % (case,count) 
