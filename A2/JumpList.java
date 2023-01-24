public class JumpList {
    private ARun[] runs;
    private int head;
    private int tail;

    public JumpList(int num) {
        runs = new ARun[num];
        head = 0;
        tail = 0;
    }

    public boolean isEmpty() {
        //System.out.println("check empty");
        if(head == runs.length-1) {
            if(runs[0] == null) {
                return true;
            } else {
                return false;
            }
        }

        if(runs[head+1] == null) {                      //Only 1 run left in the array
            return true;
        }
        else {
            return false;
        }
    }
    
    public void enqueue(ARun run) {
        runs[tail] = run;
        if(tail == runs.length - 1) {        // check if end of circular queue has been reached
            tail = 0; 
        } else {
            tail++;
        }
    }

    public ARun dequeue() {
        ARun temp = runs[head];
        runs[head] = null;
        if(head == runs.length -1) {        // check if end of circular queue has been reached
            head = 0;
        } else {
            head++;
        }
        return temp;
    }

    public int getHead() {
        return head;
    }
    public int getTail() {
        return tail;
    }
}
