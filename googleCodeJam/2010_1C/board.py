import heapq

def process(board):
    row,col = len(board),len(board[0])
    heap = []
    for i in range(row):
        for j in range(col):
            n = 1
            while validate(board,n+1,i,j):
                n += 1
            heapq.heappush(heap,[-n,i,j])

    res = {}
    while heap:
        heap = []
        
        for i in range(row):
            for j in range(col):
                if board[i][j] != ".":
                    n = 1
                    while validate(board,n+1,i,j):
                        n += 1
                    heapq.heappush(heap,[-n,i,j])

        while heap:    
            size,x,y = heapq.heappop(heap)
            if x < 0 or y < 0:
                break
            size = -size
            if not validate(board,size,x,y):
                for i in range(x,x+size):
                    for j in range(y,y+size):
                        if board[i][j] != ".":
                            n = 1
                            while validate(board,n+1,i,j):
                                n += 1
                            heapq.heappush(heap,[-n,-i,-j]) # use negative index to postpone reconstruct

            else:
                if size not in res:
                    res[size] = 1
                else:
                    res[size] += 1
                for i in range(size):
                    board[x+i][y:y+size] = ["."]*size
                #destroy
    return res
    
def validate(board,n,i,j):
    if board[i][j] == ".": return False
    if i+n-1 >= len(board) or j+n-1 >= len(board[0]):
        return False
    even = ["1"]*n
    odd  = ["1"]*n
    for k in range(n):
        if k%2 == 0:
            even[k] = "1" if board[i][j] =="1" else "0"
            odd[k] = "0" if board[i][j] == "1" else "1"
        else:
            even[k] = "0" if board[i][j] == "1" else "1"
            odd[k] = "1" if board[i][j] == "1" else "0"
    for k in range(n):
        if k % 2 == 0 and board[i+k][j:j+n] != even:
            return False
        if k % 2 == 1 and board[i+k][j:j+n] != odd:
            return False
    return True

import sys
f = sys.stdin
cases = int(f.readline())
for case in range(1,cases+1):
    M,N = map(int,f.readline().split(" "))
    board = []
    for i in range(M):
        string = bin(int(f.readline(),16))[2:]
        string = "0"*(N-len(string))+string
        board.append([i for i in string])
    res = process(board)
    print "Case #%d: %d" % (case,len(res))
    for key in sorted(res)[::-1]:
        print "%d %d" % (key,res[key])
        
