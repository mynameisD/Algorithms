
import sys
import math
fin = sys.stdin
cases = int(fin.readline())
for case in range(1,cases+1):
    n = int(fin.readline())
    x,y,z,dx,dy,dz = 0.0,0.0,0.0,0.0,0.0,0.0
    for i in range(n):
        a,b,c,d,e,f = map(int, fin.readline().split(" "))
        x += a
        y += b
        z += c
        dx += d
        dy += e
        dz += f
    if dx == 0 and dy == 0 and dz == 0:
        t = 0
    else:
        t = -(x*dx+y*dy+z*dz)/(dx*dx+dy*dy+dz*dz)
    if t < 0:
        t = 0
    xc = (x+dx*t)/n
    yc = (y+dy*t)/n
    zc = (z+dz*t)/n
    print "Case #%d: %.8f %.8f" % (case,math.sqrt(xc*xc+yc*yc+zc*zc), t)
