import java.util.LinkedList;
import java.util.List;

/**
 * Created by arnab.ray on 20/02/18.
 */
public class TestDump {

    public static class Node {
        int v;
        int w;

        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }

        public boolean equals(Object o) {
            if(o == this)
                return true;
            if(!(o instanceof Node))
                return false;
            Node n = (Node) o;
            return n.v == this.v;
        }

        public String toString() {
            return this.v + "";
        }
    }

    public static void main(String[] args) {
        //int[] a = {1,7,8,3,4,2};
        //Arrays.sort(a);
        int[] a = new int[5];
        for(int i = 0; i < a.length; i++)
            System.out.print(a[i] + " ");
        System.out.println();

        List<Node>[] adjList = new LinkedList[3];
        for(int i = 0; i < 3; i++)
            adjList[i] = new LinkedList<Node>();

        List<Node> aList = new LinkedList<Node>();
        aList.add(new Node(1,10));
        aList.add(new Node(2,11));
        aList.add(new Node(3,12));

        adjList[0].add(new Node(1,10));
        adjList[0].add(new Node(2,11));

        System.out.println(adjList[0].size());

        for(Node node : aList)
            System.out.print(node + " ");
        System.out.println();

        aList.remove(new Node(2,5));
        for(Node node : aList)
            System.out.print(node + " ");
        System.out.println((char) 97);
    }
}
