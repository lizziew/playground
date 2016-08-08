//SORT 1 MILLION DISTINCT POSTIVE INTEGERS, EACH LESS THAN 10 MILLION

#include <iostream> 
#include <algorithm> 
#include <vector>
#include <set> 
#include <ctime> 
#include <fstream> 

#define BITSPERWORD 32
#define SHIFT 5
#define MASK 0x1F
#define N 10000000
#define K 1000000

using namespace std;

vector<int> input (N, 0); 

void swap(int i, int j) {
	int t = input[i];
	input[i] = input[j];
	input[j] = t; 
}

//GENERATES RANDOM NUMBER FROM RANGE [LO, HI]
int randint(int lo, int hi) {
	return rand() % (hi-lo+1) + lo; 
}

//GENERATE K DISTINCT RANDOM INTEGERS BETWEEN 0 AND N-1
void get_rand_set() {
	for(int i = 0; i < N; i++) input[i] = i; 
	for(int i = 0; i < K; i++) swap(i, randint(i, N-1));
	input.erase(input.end()-(N-K), input.end()); 
}

//SYSTEM SORT

void sort1() {
	sort(input.begin(), input.end()); 
	for(int i = 0; i < K; i++)
		cout << input[i] << endl;
}

//C++ / STL (SET)

void sort2() {
	set<int> s; 
	for(int i = 0; i < K; i++) s.insert(input[i]);
	for(set<int>::iterator j = s.begin(); j != s.end(); j++)
		cout << *j << endl;
}

//C / qsort

int b[K]; 

int cmp(const void* x, const void* y) {
	return *(int*) x - *(int*) y; 
}

void sort3() {
	for(int i = 0; i < K; i++) b[i] = input[i]; 
	qsort(b, K, sizeof(int), cmp); 
	for(int i = 0; i < K; i++) 
		cout << b[i] << endl;
}

//C / BITMAPS

//BIT VECTOR IMPLEMENTATION

int a[1 + N/BITSPERWORD]; 

void st(int i) {
	a[i >> SHIFT] |= (1 << (i & MASK));
}

void clr(int i) {
	a[i >> SHIFT] &= ~(1 << (i & MASK));
}

int test(int i) {
	return a[i >> SHIFT] & (1 << (i & MASK));
}

void sort4() {
	for(int i = 0; i < N; i++) clr(i);
	for(int i = 0; i < K; i++) st(input[i]);
	for(int i = 0; i < N; i++) 
		if(test(i))
			cout << i << endl; 
}

int main() {
	ofstream output; 
	output.open("column1_times.txt");
	output << "CPU TIMES IN SECONDS" << endl;

	get_rand_set();
	//CPU TIME FOR SYSTEM SORT
	clock_t begin = clock();
	sort1();
	clock_t	end = clock(); 
	output << "SYSTEM SORT: " << (double) (end - begin) / CLOCKS_PER_SEC << endl; 

	get_rand_set(); 
	//CPU TIME FOR C++ / STL (SET)
	begin = clock();
	sort2();
	end = clock();
	output << "C++ / STL (SET): " << (double) (end - begin) / CLOCKS_PER_SEC << endl; 

	get_rand_set(); 
	//CPU TIME FOR C++ / STL (SET)
	begin = clock();
	sort3();
	end = clock();
	output << "C / qsort: " << (double) (end - begin) / CLOCKS_PER_SEC << endl; 

	get_rand_set(); 
	//CPU TIME FOR C++ / STL (SET)
	begin = clock();
	sort4();
	end = clock();
	output << "C / BITMAPS: " << (double) (end - begin) / CLOCKS_PER_SEC << endl; 

	output.close();
}