package com.muthii.secret;

import org.apache.commons.lang.math.NumberUtils;

import java.util.ArrayList;
import java.util.Scanner;
 
public class Secret { 
public void calculate() { 		
		Scanner scanner = new Scanner(System.in);
        System.out.print("Please enter an integer value greater than 1 :");
        int limit = 0;
        String input; 
        
        input = scanner.nextLine();
        boolean num = NumberUtils.isDigits(input);
        if (num) { limit = Integer.parseInt(input);}
 
        if (limit < 2) {
            System.out.println(input + " is invalid input. An integer value greater than 1 must be entered.");
            System.exit(0);
        }
        
        Integer[] primes = null;
        try{
        	primes = primeSieve(limit);            
            
            System.out.format("The number of primes <= %d is %d \n",  limit , primes.length);
            
            if(isAdditive(primes)){
            	System.out.format("The function secret() is an additive function for all prime numbers under %d" , limit );
            }else{
            	System.out.format("The function secret() is not an additive function for all prime numbers under %d" , limit);
            }
        }catch(OutOfMemoryError e){
        	//let user know if calculation cannot be completed due to not having enough memory
        	System.out.format("More is memory needed to perform calculation for primes <= %d" , limit);
        }              
	}
	
	private Integer[] primeSieve(int limit) {

	      int sqrtLimit = (int) Math.sqrt(limit);

	      boolean[] isPrime = new boolean[limit + 1];
      	      
	      ArrayList<Integer> primes = new ArrayList<>();

	      for (int s = 2; s <= sqrtLimit; s++) {
	            if (!isPrime[s]) {
	            	primes.add(s);

	                  for (int i = s * s; i <= limit; i += s){
	                	  isPrime[i] = true;
	                  }                  
	            }
	      }

	      for (int s = sqrtLimit; s <= limit; s++){
	    	  if (!isPrime[s]){
	            	primes.add(s);
	            }
	      }
	            
		return primes.toArray(new Integer[primes.size()]);
	}
	
	private boolean isAdditive(Integer[] primes) {      
        for (Integer x : primes) {
            for (Integer y : primes) {            	
                //secret(x+y) = secret(x) + secret(y)
                if (secret(x+y) != secret(x) + secret(y)) {
                    return false;
                }
            }            
        }
        return true;
    }
	
	private int secret(int number) {
        return number;
    }	
}


