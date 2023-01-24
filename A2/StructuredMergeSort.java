import java.util.Arrays;

public class StructuredMergeSort {
    public static void sort(int[] arr) {
        int length = arr.length;
        JumpList jpl = new JumpList(length);
        int[] buffArr = new int[length];                //creating buffer array
        for (int i = 0; i < length; i++) {
            buffArr[i] = arr[i];
        }
        int counter = 0;                            // counter of comparisons from structuring
        int runLength = 1;
        int state = -1;                             // 1 = ASCENDING    2 = DESCENDING  -1 = RESET
        for (int i = 0; i < length-1; i++) {
            if (arr[i] <= arr[i+1]) {
                if (state == -1) {
                    state = 1;
                    runLength++;
                }
                else if (state == 2) {
                    state = -1;
                    
                    swap(buffArr, i-runLength + 1, i);
                    int start = i - runLength+1;
                    int end = runLength;
                    ARun run1 = new ARun(i-runLength + 1, runLength);
                    jpl.enqueue(run1);
                    runLength = 1;
                } else {
                    runLength++;
                }
                counter++;
            }
            if (arr[i] > arr[i+1]) {
                if (state == -1) {
                    state = 2;
                    runLength++;
                }
                else if (state == 1) {
                    state = -1;
                    ARun run1 = new ARun(i-runLength + 1, runLength);
                    jpl.enqueue(run1);
                    runLength = 1;
                } else {
                    runLength++;
                }
                counter++;
            }
        }
        if(state == 2) {                                            // Completing the last run in the structuring
            swap(buffArr, length-runLength, length-1);
            ARun run1 = new ARun(length-runLength, runLength);
            jpl.enqueue(run1);
        } else {
            ARun run1 = new ARun(length-runLength, runLength);
            jpl.enqueue(run1);
        }

        for (int i = 0; i < length; i++) {                            //copying buffer array to arr after swaps
            arr[i] = buffArr[i];
        }

        while(!jpl.isEmpty()) {                                           // check for runs left in the jump list
            ARun run1 = jpl.dequeue();
            if(run1.getStart()+run1.getLength() == length) {
                jpl.enqueue(run1);                                        // check if its the last run from left to right
                continue;
            }   
            ARun run2 = jpl.dequeue();
            ARun run3 = new ARun(run1.getStart(), run1.getLength() + run2.getLength());   
            jpl.enqueue(run3);

            MergeSort.merge(arr, buffArr, run1, run2);                      //merge(arr, buffArr, jpl.dequeue(), jpl.dequeue());
            for (int i = 0; i < length; i++) {
                arr[i] = buffArr[i];
            }
            
        }
        System.out.println("Comparisons: " + (MergeSort.compares + counter));
    }
    
    public static void swap(int[] arr, int start, int end) {
        int temp;
        while (start < end) {
            temp = arr[start];
            arr[start] = arr[end];
            arr[end] = temp;
            start++;
            end--;
        }
    }
}
