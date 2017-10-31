import java.lang.StringBuilder;

public class LCA<Key extends Comparable<Key>, Value> {
    private Node root;
    
    private class Node {
        private Key key;           
        private Value val; 
        //replace these with a list of nodes
        private Node left;
        private Node right;
        private int N;

        public Node(Key key, Value val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }
    
    public boolean isEmpty()
    {
    	return size() == 0;	
    }

    public int size() 
    {
    	return size(root); 
    }   
    
    private int size(Node x) {
        if (x == null)
        {
        	return 0;
        }
        else
        {
        	return x.N;
       	}
    }
    
    /**
     *  Search BST for given key.
     *  What is the value associated with given key?
     *
     *  @param key the search key
     *  @return value associated with the given key if found, or null if no such key exists.
     */
    public Value get(Key key) { 
    	
    	if(key == null)
    	{
    		return null;
    	}
    	else
    	{	
    		return get(root, key);
    	}
    }

    private Value get(Node x, Key key) {
        if (x == null)
        {
        	return null;
       	}
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
        {
        	return get(x.left, key);
       	}
        else if(cmp > 0)
       	{
        	return get(x.right, key);
        }
        else
        {
        	return x.val;
        }
    }

    /**
     *  Insert key-value pair into BST.
     *  If key already exists, update with new value.
     *
     *  @param key the key to insert
     *  @param val the value associated with key
     */
    public void insert(Key key, Value val) {
		if(key == null)
		{ 
			return;
		}
        if(val == null)
        {
        	delete(key); 
        	return; 
        }
        root = insert(root, key, val);
    }

    private Node insert(Node x, Key key, Value val) {
        if(x == null)
        {
        	return new Node(key, val, 1);
       	}
        int cmp = key.compareTo(x.key);
        if(cmp < 0)
        {
        	x.left  = insert(x.left,  key, val);
        }
        else if(cmp > 0) 
        {
        	x.right = insert(x.right, key, val);
       	}
        else
        {
        	x.val   = val;
       	}
        x.N = 1 + size(x.left) + size(x.right);
        return x;
    }
    
    /**
     * Print all keys of the tree in a sequence, in-order.
     * That is, for each node, the keys in the left subtree should appear before the key in the node.
     * Also, for each node, the keys in the right subtree should appear before the key in the node.
     * For each subtree, its keys should appear within a parenthesis.
     *
     * Example 1: Empty tree -- output: "()"
     * Example 2: Tree containing only "A" -- output: "(()A())"
     * Example 3: Tree:
     *   B
     *  / \
     * A   C
     *      \
     *       D
     *
     * output: "((()A())B(()C(()D())))"
     *
     * output of example in the assignment: (((()A(()C()))E((()H(()M()))R()))S(()X()))
     *
     * @return a String with all keys in the tree, in order, parenthesised.
     */
    public String printTree() {
      if(isEmpty())
      {
    	  return "()";
      }
      
      StringBuilder output = new StringBuilder();
      output.append("(");
      printTree(root, output);
      
      String result = output.toString();
      return result;
      
    }
    
    private StringBuilder printTree(Node x, StringBuilder output){
    	if(x == null)
    	{
    		return (output.append(")"));
    	}
    	output.append("(");
    	printTree(x.left, output);
    	output.append(x.key);
    	output.append("(");
    	printTree(x.right, output);
    	output.append(")");
    	return output;
    	
    }
    
    public void delete(Key key) {
    	if(key!=null)
    	{
    		root = delete(root, key);
    	}
    }
    
    private Node delete(Node x, Key key){
    	if(x == null)
    	{
    		return null;
    	}
    	int cmp = key.compareTo(x.key);
    	if(cmp < 0)
    	{
    		x.left  = delete(x.left, key);
    	}
    	else if(cmp > 0)
    	{
    		x.right = delete(x.right, key);
    	}
    	else
    	{
    		if(x.right 	== null)
    		{
    			return x.left;				
    		}
    		if(x.left 	== null)
    		{
    			return x.right;
    		}
    		
    		Node t = x;
    		x = t.left;
    		while(x.right != null)
    		{
    			x = x.right;
    		}
    		x.left = deleteMax(t.left);
    		x.right = t.right; 		
    	}
    	x.N = size(x.left) + size(x.right) + 1;
    	return x;
    }
    
    private Node deleteMax(Node x)
    {
    	if(x.right == null)
    	{
    		return x.left;
    	}
    	x.right = deleteMax(x.right);
    	x.N = 1 + size(x.left) + size(x.right);
    	return x;
    }

    public boolean contains(Key x){
    	return(get(x)!=null);
    }
    
    
    
    public Key lowestCommonAncestor(Key node1, Key node2) {
    	if (root == null)
    	{
			return null;			
		}
    	if(contains(node1)==false || contains(node2)==false)
    	{
    		return null;
    	}
    	return lowestCommonAncestor(root, node1, node2);
    }
    
	private Key lowestCommonAncestor(Node currentNode, Key node1, Key node2)
	{
		//compares key to see if it is too far or needs to keep going
		//this can be repurposed for assignment 2
		//use cmp to decide when to head back and whether to go into each child
		int cmp1 = currentNode.key.compareTo(node1);
		int cmp2 = currentNode.key.compareTo(node2);
		
		if(cmp1 > 0 && cmp2 > 0)
		{
			return lowestCommonAncestor(currentNode.left, node1, node2);
		}
		else if(cmp1 < 0 && cmp2 < 0)
		{
			return lowestCommonAncestor(currentNode.right, node1, node2);
		}
		else{
			return currentNode.key;
		}	
	}
	//non working
	//kinda working
	/*
	public static Node lowestCommonAncestor(Node rootNode, Node node1, Node node2) 
	{
	    if(rootNode == null)
	    {
	        return null;
	    }
	 
	    if(rootNode == node1 || rootNode == node2)
	    {
	        return rootNode;
	    }
	 
	    Node left = rootNode.left;
	    Node right = rootNode.right;
	    Node leftNode = lowestCommonAncestor(left, node1, node2);
	    Node rightNode = lowestCommonAncestor(right, node1, node2);
	 
	    if(leftNode != null && rightNode != null)
	    {
	        return rootNode;
	    }
	    else if(leftNode == null && rightNode == null)
	    {
	        return null;
	    }
	    if(leftNode == null)
	    {
	        return rightNode;
	    }
	    else
	    {
	    	return leftNode;
	    }
	}
	*/

}