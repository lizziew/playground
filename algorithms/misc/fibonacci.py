import time

def dot(x, y):
    a=x[0][0]*y[0][0]+x[1][0]*y[0][1]
    b=x[0][0]*y[1][0]+x[1][0]*y[1][1]
    c=x[1][0]*y[0][0]+x[1][1]*y[0][1]
    d=x[1][0]*y[1][0]+x[1][1]*y[1][1]
    return [[a,b],[c,d]]

#repeated squaring, matrix multiplication
def matrixfib(n): 
	a = [[1, 1], [1, 0]]
	t = [[1, 0], [0, 1]]
	b = [1, 1]
		
	while n > 0: 
		if n % 2 == 1:
			t = dot(t, a) 
		n /= 2
		a = dot(a, a)

	return t[1][0]*b[0]+t[1][1]*b[1]

def naive(n):
	if n == 0 or n ==1:
		return 1
	else:
		return naive(n-1) + naive(n-2)

def loopfib(n):
	prev = 0
	curr = 1
	res = 1

	while n > 0: 	
		res = curr + prev 
		prev = curr
		curr = res
		n -= 1

	return res 

def test(n):
	print "n = ", n

	t0 = time.time()
	ans_loop = loopfib(n)
	t1 = time.time() 
	ans_matrix = matrixfib(n)
	t2 = time.time() 
	ans_naive = naive(n)
	t3 = time.time() 

	print "matrix: ", ans_matrix, "time: ", t2 - t1, " seconds"
	print "linearfib: ", ans_loop, "time: ", t1 - t0, " seconds"
	print "naive:   ", ans_naive,   "time: ", t3-t2, " seconds."

test(1000000)
