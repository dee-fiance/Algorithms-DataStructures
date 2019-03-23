/**
Very important PROBABILITY CONCEPT:

When we generate a random number from 1->n, we are replacing the previous result only when the random no is "n" and this can happen with the probability 1/n
So, the probability of picking the last element is 1/n.

What about the probability of the second last element to get picked ?
When second last element was added (1-> n-1), the probability that it replaced the previous result is 1/n-1, And the probability that the second last element remains the result when the last element got added is (n-1)/n

So, the probability that the second element is picked when the last element was added =
1/(n-1) * (n-1)/n = 1/n
This way all the elements always get picked with the probability of 1/n, n is the no of elements to consider from the stream. 
**/

import java.util.*;
 
//An efficient Java program to randomly select a number from stream of numbers. 
public class RandomFromStream
{ 
    static int res = 0; // The resultant random number 
    static int count = 0; //Count of numbers visited so far in stream 
    
    //A method to randomly select a item from stream[0], stream[1], .. stream[i-1] 
    static int selectRandom(int x) 
    { 
        count++; // increment count of numbers seen so far 
        
        // If this is the first element from stream, return it 
        if (count == 1) 
            res = x; 
        else
        { 
            // Generate a random number from 0 to count - 1 
            Random r = new Random(); 
            int i = r.nextInt(count); 
            
            // Replace the prev random number with new number with 1/count probability 
            if(i == count - 1) 
                res = x; 
        } 
        return res; 
    } 
    
    // Driver program to test above function. 
    public static void main(String[] args) 
    { 
        int stream[] = {1, 2, 3, 4}; 
        int n = stream.length; 
        for(int i = 0; i < n; i++) 
            System.out.println("Random number from first " + (i+1) + 
                            " numbers is " + selectRandom(stream[i])); 
    } 
}
