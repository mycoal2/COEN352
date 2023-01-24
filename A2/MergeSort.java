import java.util.Arrays;

public class MergeSort {
    static int compares = 0;
    public static void sort(int[] arr) {
        int length = arr.length;
        int[] buffArr = new int[length];
        for (int i = 0; i < length; i++) {
            buffArr[i] = arr[i];
        }
        JumpList jpl = new JumpList(length);
        for (int i = 0; i < length; i++) {
            ARun run = new ARun(i, 1);
            jpl.enqueue(run);
        }   

        while(!jpl.isEmpty()) {
            ARun run1 = jpl.dequeue();
            if(run1.getStart()+run1.getLength() == length) {
                jpl.enqueue(run1);                                        // check if its the last
                continue;
            }   
            ARun run2 = jpl.dequeue();
            ARun run3 = new ARun(run1.getStart(), run1.getLength() + run2.getLength());   
            jpl.enqueue(run3);

            merge(arr, buffArr, run1, run2);    //merge(arr, buffArr, jpl.dequeue(), jpl.dequeue());
            for (int i = 0; i < length; i++) {
                arr[i] = buffArr[i];
            }
        }
        System.out.println("Comparisons: " + compares);
    }

    public static void merge(int[] src, int[] dst, ARun run1, ARun run2) {
        //System.out.println("b4");                                             //testing
        //System.out.println(Arrays.toString(dst));
        
        int length = run1.getLength() + run2.getLength();
        int start = run1.getStart();                    // run1 start will always be before run2 because its from left to right.
        int end = start + length;                       // last index should point to
        int index = start;                              // index for dst
        int counter1 = 0;                               // run1 counter
        int counter2 = 0;                               // run2 counter

        while(counter1 < run1.getLength() && counter2 < run2.getLength()) {
            if(src[run1.getStart() + counter1] <= src[run2.getStart() + counter2]) {
                dst[index] = src[run1.getStart()+counter1];
                compares++;
                counter1++;
                index++;
            } else {
                dst[index] = src[run2.getStart()+counter2];
                compares++;
                counter2++;
                index++;
            }
        }

        if(counter1 == run1.getLength()) {                              //copying the rest of run2 after completing run1
            while (index != end) {
                dst[index] = src[run2.getStart()+counter2];
                counter2++;
                index++;
            }
        } else {
            while (index != end) {                                      //copying the rest of run1 after completing run2
                dst[index] = src[run1.getStart()+counter1];
                counter1++;
                index++;
            }
        }
        //System.out.println("after");                                  //testing
        //System.out.println(Arrays.toString(dst));
    }
}
