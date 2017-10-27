public class LCA {
	
	Node root;
	
	class Node {

	    String key;
		Node left;
		Node right;
		
		Node(String key) 
		{
			this.key = key;
		}
	}

	
	//take in keys as a list of values in order
	//going root,left child, right child, left child of left child, 
	//right child of left child, left child of right child, etc.
	public static void createTree(String [] listOfKeys)
	{
		int i = 0;
		Node newNode = null;
		Node tempNode = null;
		while(i <= listOfKeys.length)
		{
			
			if(listOfKeys[i] != null)
			{
				newNode.key = listOfKeys[i];
				tempNode = newNode;
				if(tempNode.left == null){
					newNode = tempNode.left;
				}
				else
				{
					newNode = tempNode.right;
				}
			}
			else
			{
				newNode = null;
			}
			i++;
		}
	}
	
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

}