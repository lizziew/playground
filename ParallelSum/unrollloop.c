#include <stdio.h>
#include <time.h>
#include <stdlib.h> 

int N = 100000000; //size of data
double data[100000001]; 
double sum; 

float unroll4() {
  clock_t start = clock();

  int M = (N>>2)<<2; //greatest multiple of 4 less than N
  double sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0; 
  int index;

  for(index = 0; index < M; index += 4) {
    sum1 += data[index];
    sum2 += data[index+1];
    sum3 += data[index+2];
    sum4 += data[index+3]; 
  }
  index -= 4;

  switch(N-M) {
  case 1: sum1 += data[index];
  case 2: sum1 += (data[index] + data[index+1]);
  case 3: sum1 += (data[index] + data[index+1] + data[index+2]);
  default: break; 
  }

  sum = sum1 + sum2 + sum3 + sum4; 

  clock_t diff = clock() - start; 
  float elapsed = diff/(double) CLOCKS_PER_SEC; 
  return elapsed;
}

float unroll2() {
  clock_t start = clock();
  int M = (N>>1)<<1; //greatest multiple of 2 less than N
  double sum1 = 0, sum2 = 0;

  int index;
  for(index = 0; index < M; index += 2) {
    sum1 += data[index];
    sum2 += data[index+1];
  }
  index -= 2;

  if(N-M == 1)
    sum1 += data[index];

  sum = sum1 + sum2; 

  clock_t diff = clock() - start; 
  float elapsed = diff/(double) CLOCKS_PER_SEC; 
  return elapsed; 
}

float naive() {
  clock_t start = clock();

  int i;
  for(i = 0; i < N; i++) 
    sum += data[i];

  clock_t diff = clock() - start; 
  float elapsed = diff/(double) CLOCKS_PER_SEC; 
  return elapsed;
}

main() {
  FILE *fin = fopen("data.txt", "r"); 

  int i;
  for(i = 0; i < N; i++) 
    fscanf(fin, "%lf\n", &data[i]);   

  float avgtime = 0; 
  for(i = 0; i < 5; i++) {
      avgtime += unroll4();  
  } 
  avgtime /= 5.0; 
  printf("unroll 4: %f\n", avgtime);

  avgtime = 0; 
  sum = 0;
  for(i = 0; i < 5; i++) {
      avgtime += unroll2();  
  } 
  avgtime /= 5.0; 
  printf("unroll 2: %f\n", avgtime);

  avgtime = 0; 
  sum = 0;
  for(i = 0; i < 5; i++) {
    avgtime += naive();  
  } 
  avgtime /= 5.0; 
  printf("naive version: %f\n", avgtime);
}
