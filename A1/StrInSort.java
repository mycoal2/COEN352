import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

// QUESTION AT THE BOTTOM

public class StrInSort {
    public static int counterCOMPARES = 0;          //overall counters
    public static int counterSwap = 0;              //overall counters

    public static int[] readFile(String file) throws FileNotFoundException {
        File inputFile = new File(file);
        int numInt = 0;
        Scanner scan = new Scanner(inputFile);
        while (scan.hasNextInt()) {
            numInt++;
            scan.nextInt();
        }
        scan.close();

        int[] arr = new int[numInt];
        Scanner scan1 = new Scanner(inputFile);
        for (int i = 0; i < arr.length; i++) {
            arr[i] = scan1.nextInt();
        }
        scan1.close();

        return arr;
    }

    public static int[] structuringPass(int[] array) {
        int[] testArr = array;
        int counterDESCENDING = 0;      // of length 3
        //int counterSwap = 0;
        //int counterCOMPARES = 0;
        int runStatus = 0;          // 1 is ASC, 2 is DSC
        int numLength = 1;
        for(int i = 0; i < testArr.length-1; i++) {
            if(testArr[i] < testArr[i+1]) {         
                if (runStatus == 1) {
                    numLength++;
                    
                } else if(runStatus == 2) {
                    if(numLength == 3) {
                        counterDESCENDING++;
                    }
                    numLength = 1;
                    runStatus = 0;
                } else {
                    runStatus = 1;
                    numLength++;
                }
            } else {            // testArr[i] > testArr[i+1]
                if (runStatus == 1) {
                    for(int j = 0; j < numLength/2; j++) {
                        int temp = testArr[i-numLength+1];
                        testArr[i-numLength+1+j] = testArr[i-j];
                        testArr[i - j] = temp;
                    }
                    counterSwap++;
                    runStatus = 0;
                    numLength = 1;
                    
                } else if(runStatus == 2) {
                    numLength++;
                } else {
                    runStatus = 2;
                    numLength++;
                }
            }
            counterCOMPARES++;
        }
        System.out.println("We counted " + counterDESCENDING + " DEC Run of length 3");
        System.out.println("We performed " + counterSwap + " reversals of runs in ASC order");
        System.out.println("We performed " + counterCOMPARES + " compares during structuring");
        return testArr;
    }

    public static int[] SortDEC(int[] array) {
        //int counterCOMPARES = 0;
        //int counterSwap = 0;
        int[] testArr = array;
        for(int i = 0; i < testArr.length-1; i++) {
            int j = i;
            while (testArr[j+1] > testArr[j]) {
                int temp = testArr[j];
                testArr[j] = testArr[j+1];
                testArr[j+1] = temp;
                
                counterSwap++;
                if(j == 0) {
                    break;
                }
                j--;
                counterCOMPARES++;
            } 
            counterCOMPARES++;

        }
        System.out.println("We performed " + counterCOMPARES + " compares overall");
        System.out.println("We performed " + counterSwap + " swaps overall");
        return testArr;
    }
    public static void main(String[] args) throws FileNotFoundException {
        if (0 < args.length) {                                           //check for data inputs
            int[] array = readFile(args[0]);
            for(int i = 0; i < array.length; i++) {
                System.out.print(array[i] + " ");
            }
            System.out.println("");
            System.out.println("We sorted in DEC order");
            
            //System.out.println(Arrays.toString(array) + " ");
            int[] arrStructure = structuringPass(array);
            //System.out.println(Arrays.toString(arrStructure));      //Array after structuring
            
            int[] sortedArr = SortDEC(arrStructure);
            //int[] sortedArr = SortDEC(array);             //Question 2

            for(int i = 0; i < array.length; i++) {
                System.out.print(sortedArr[i] + " ");
            }
            System.out.println("");

        } else {
            System.out.println("No file found for data input");
        }

    }
  }
/*
Question 2:
It did not affect the number of swaps and comparison overall. 
The swaps and comparisons done in the structuring pass would of happened in the sorting run.
Nothing else should be affected.

Question 3:
I had to have an extra variable to count the length of the run and a IF statement to check if the 
run is of length 3.

Question 4:
You would need a Node class to start the double linked list. 
By implementing a double linked list, it is easier to compare the next element or previous element. It is also easier to add or delete nodes.
The result will not change.


*/