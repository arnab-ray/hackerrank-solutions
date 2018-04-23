/**
 * Created by arnab.ray on 26/03/18.
 */

class Node {
    int val;
    Node left, right;
    boolean isErasable;

    public Node(int val) {
        this.val = val;
        this.left = this.right = null;
        this.isErasable = false;
    }

    public Node(int val, boolean isErasable) {
        this.val = val;
        this.left = this.right = null;
        this.isErasable = isErasable;
    }
}

public class Forest {

    private static void printForest(Node root) {
        if(root != null) {
            if(root.isErasable) {
                System.out.println();
                if(root.left != null) {
                    printForest(root.left);
                    System.out.println();
                }
                if(root.right != null) {
                    printForest(root.right);
                    System.out.println();
                }
            }
            else {
                if(root.left != null)
                    printForest(root.left);
                System.out.print(" " + root.val + " ");
                if(root.right != null)
                    printForest(root.right);
            }
        }
    }

    /*private Node addNode(Node root, int val, boolean isErasable) {
        if(root == null) {
            root = new Node(val, isErasable);
        }
        return root;
    }*/

    public static void main(String[] args) {
        /*Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        Node root = null;
        for(int i = 0; i < n; i++) {
            //add nodes
            int val = in.nextInt();
            String isErasable = in.next();
            root = addNode(root, val, Boolean.valueOf(isErasable));
        }
        in.close();*/
        Node root = new Node(1, true);
        root.left = new Node(2, false);
        root.right = new Node(3, false);
        root.left.left = new Node(4, false);
        root.left.right = new Node(5, true);
        root.right.left = new Node(6, false);
        root.right.right = new Node(7, false);
        root.left.right.left = new Node(8, false);

        printForest(root);
    }
}
