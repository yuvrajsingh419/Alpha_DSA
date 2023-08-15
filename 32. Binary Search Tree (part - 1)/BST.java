public class BST{
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

    public static void main(String[] args) {
        int values[] = {5, 1, 3, 4, 2, 7};
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

       root = delete(root, 1);
       System.out.println();

       inorder(root);
         
    }
}