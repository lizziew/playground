import numpy as np
import time

#repeated squaring, matrix multiplication
def matrixfib(n): 
	a = [[1, 1], [1, 0]]
	t = [[1, 0], [0, 1]]
	b = [1, 1]
		
	while n > 0: 
		if n % 2 == 1:
			t = np.dot(t, a) 
		n /= 2
		a = np.dot(a, a)

	return np.dot(t, b)[1]

def naive(n):
	if n == 0 or n ==1:
		return 1
	else:
		return naive(n-1) + naive(n-2)

def linearfib(n):
	prev = 0
	curr = 1
	res = 1

	while n > 0: 	
		res = curr + prev 
		prev = curr
		curr = res
		n -= 1

	return res 

def test():
	n = 30 
	print "n = ", n

	t0 = time.time()
	ans_matrix = matrixfib(n)
	t1 = time.time() 
	ans_linear = linearfib(n)
	t2 = time.time() 
	ans_naive = naive(n)
	t3 = time.time() 

	print "matrix: ", ans_matrix, "time: ", t1 - t0, " seconds"
	print "linearfib: ", ans_linear, "time: ", t2 - t1, " seconds"
	print "naive:   ", ans_naive,   "time: ", t3-t2, " seconds."

test() 
