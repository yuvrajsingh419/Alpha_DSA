import java.util.*;

public class Valid_BST {
    static class Node {
        int data;
        Node left;
        Node right;

        Node(int data) {
            this.data = data;
        }
    }

    //lec-3
    public static Node insert(Node root, int val){
        if(root == null) {
            root = new Node(val);
            return root;
        }

        if(root.data > val){
            //left subtree
            root.left = insert(root.left, val);
        } else {
            //right subtree
            root.right = insert(root.right, val);
        }
        return root;
    }

    
    public static void inorder(Node root) {
         if(root == null){
            return;
         }
         inorder(root.left);
         System.out.print(root.data+" ");
         inorder(root.right);
    }

    //lec-4
    public static boolean search(Node root, int key){ // T.C = O(H)
        if(root == null){
            return false;
        }

        if(root.data == key){
            return true;
        }

        if(root.data > key){
            return search(root.left, key);
        }

        else{
            return search(root.right, key);
        }
    }

    //lec-5
    public static Node delete(Node root, int val){
        if(root.data < val){
            root.right = delete(root.right, val);
        }  
        else if(root.data > val){
            root.left = delete(root.left, val);
        }
        else{ //voila
            //case 1 - leaf node
            if(root.left == null && root.right == null){
                return null;
            }

            //case 2 - single child
            if(root.left == null){
                return root.right;
            }
            else if(root.right == null){
                return root.left;
            }

            //case 3 - both children
            Node IS = findInorderSuccessor(root.right);
            root.data = IS.data;
            root.right = delete(root.right, IS.data);
        }

        return root;
    }

    
    public static Node findInorderSuccessor(Node root){
        while(root.left != null){
            root = root.left;
        }
        return root;
    }

    //lec-6
    public static void printInRange(Node root, int k1, int k2){
        if(root == null){
            return;
        }
        if(root.data >=k1 && root.data <=k2) {
            printInRange(root.left, k1, k2);
            System.out.print(root.data+" ");
            printInRange(root.right, k1, k2);
        }
        else if(root.data < k1) {
            printInRange(root.left, k1, k2);    
        }
        else {
            printInRange(root.right, k1, k2);
        }
    }

    //lec-7
    public static void printPath(ArrayList<Integer> path){
        for(int i=0; i<path.size(); i++){
            System.out.print(path.get(i)+"->");
        }
        System.out.println("Null");
    }


    public static void printRoot2Leaf(Node root, ArrayList<Integer> path){
        if(root == null){
            return;
        }
        path.add(root.data);
        if(root.left == null && root.right == null){
            printPath(path);
        }
        printRoot2Leaf(root.left, path);
        printRoot2Leaf(root.right, path);
        path.remove(path.size()-1);
    }

    //lec-8
    public static boolean isVAlidBST(Node root, Node min, Node max){
        if(root == null){
            return true;
        }

        if(min != null && root.data <= min.data){
            return false;
        }

        else if(max != null && root.data >= max.data){
            return false;
        }

        return isVAlidBST(root.left, min, root) && isVAlidBST(root.right, root, max);
    }

    public static void main(String[] args) {
        int values[] = {8, 5, 3, 6, 10, 11, 14};
        
       //int values[] = {1, 1, 1};  // Not Valid BST
        Node root = null;

        for(int i=0; i<values.length; i++){
            root = insert(root, values[i]);
        }

        //lec-3
        inorder(root);
        System.out.println();

        // //lec-4
        // if(search(root, 5)) {
        //     System.out.println("Found");
        // } else {
        //     System.out.println("Not Found");
        // }

    //    root = delete(root, 5);
    //    System.out.println();

    //    inorder(root);
         
    // //lec-6        
    // printInRange(root, 5, 11);

    // led-7
    // printRoot2Leaf(root, new ArrayList<>());


    //lec-8 
    if(isVAlidBST(root, null, null)){
        System.out.println("Valid BST");
    } else {
        System.out.println("Not Valid BST");
    }


    }
}
