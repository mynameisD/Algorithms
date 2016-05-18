# Sieve of Eratosthenes
# Code by David Eppstein, UC Irvine, 28 Feb 2002
# http://code.activestate.com/recipes/117119/

def gen_primes():
    D = {}
    q = 2
    while True:
        if q not in D:
            yield q
            D[q*q] = [q]
        else:
            for p in D[q]:
                D.setdefault(p+q,[]).append(p)
            del D[q]
        q += 1

for prime in gen_primes():
    print prime
    if prime > 100:
        break
