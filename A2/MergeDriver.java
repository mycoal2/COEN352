import java.util.Random;
import java.util.Arrays;

// QUESTIONS ANSWERED AT BOTTOM OF THIS FILE

public class MergeDriver {
    public static void main(String[] args) {
        //long fullStart = System.nanoTime();                                   // Timer for full program
        if (args.length == 0) {                                                 //Check for Arguments
            System.out.println("No argument");
            return;
        }
        int mergeType = -1;                                   // 1 = MS       2 = SMS     -1 = not defined
        if (args[0].equals("SMS")) {                                //Check Either SMS or MS
            mergeType = 2;
        } 
        else if(args[0].equals("MS")) {
            mergeType = 1;
        } else {
            System.out.println("Incorrect Argument");
            return;
        }

        if (args.length == 1) {                                                 // Check for data                  
            System.out.println("No data input");
            return;
        }
        else if (args.length == 2) {                                            //Check if the array is given
            int length = Integer.parseInt(args[1]);                             // Random Array to generate
            int[] arr = new int[length];
            Random rand = new Random();
            for (int i = 0; i < length; i++) {
                arr[i] = rand.nextInt(Integer.MAX_VALUE);                               //Integer.MAX_VALUE
            }
            System.out.print("Sorting: ");
            print(arr);  
            long start = System.nanoTime();                                                            //System.out.println(Arrays.toString(arr));
            if(mergeType == 1) {                                                //Check which sort                              
                MergeSort.sort(arr);
            } else {
                StructuredMergeSort.sort(arr);
            }
            System.out.print("Sorting: ");                                   //results:
            print(arr);

            long end = System.nanoTime();
            long time = end - start;
            System.out.println( "Completed in " + time + "ns");             // Time taken to sort
        } 
        else {                                                                  // Array is given
            int length = args.length -1;
            int[] arr = new int[length];
            for (int i = 0; i < length; i++) {
                arr[i] = Integer.parseInt(args[i+1]);                           // parsing arguments
            }
            System.out.print("Sorting: ");
            print(arr);                                                 //System.out.println(Arrays.toString(arr));
            long start = System.nanoTime(); 
            if(mergeType == 1) {                                        //Check which sort                              
                MergeSort.sort(arr);
            } else {
                StructuredMergeSort.sort(arr);
            }
            System.out.print("Sorted: ");                           //results: 
            print(arr);
            long end = System.nanoTime();
            long time = end - start;
            System.out.println( "Completed in " + time + "ns");                     // Time taken to sort
        }

        /* 
        long end = System.nanoTime();
        long time = end - fullStart;
        System.out.println( "Full code execution time is " + time + "ns");                      // Time to fully execute my code
        */
    }
    
    public static void print(int[] arr) {                           // Printing array
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println("");
    }
}


// QUESTION 2
// The main difference is how the runs are added to the JumpList before starting the merge. 
// In the normal MergeSort, each number in the array is considered a run of length 1.
// In the structured MergeSort, the number of runs depends on if the numbers are already ordedred Ascending or Descending.
// The number of comparaisons in the structure MergeSort has a minimum of N - 1 because of the structuring. (N being the number of values in the array).

// QUESTION 3 
// The best case for the normal MergeSort will have a time complexity of Omega(N*log(N)) because it will still need to compare values between run.
// The best case for the structured MergeSort will have a time complexity of Omega(N) because if the whole array is already sorted,
// there will only be 1 run in the JumpList. 
// The structured MergeSort will work better if the array is already sorted or mostly sorted.

// QUESTION 4
// The JumpList hold ARun which has a starting index and a length.
// These runs are used as pointers to the base array to know which index should compare to which from Run1 to Run2.
// When merging two run together, it will create another run with starting index of the lower of the two run.
// The length of the new run will be the sum of both runs merged together.