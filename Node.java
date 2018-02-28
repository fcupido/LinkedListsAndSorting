//Node.java
/**
*Class node has a string and a reference to another node.
*@autor Felipe Cupido
*@since Feb 10, 2018
*/

public class Node 
{
	private String data;
	/**
	*The string held as the data of the node
	*/

	private Node next;
	/**
	*A reference to the next node in a list
	*/

	public Node () //Creates a new node with void elements
	{
		data = null;
		next = null;
	}

	public Node (String s) //Creates a new node and sets the data to the argument s
	{
		data = s;
		next = null;
	}

	public Node (String s, Node n) //Creates a new node and sets the data to s and the next node to n
	{
		data = s;
		next = n;
	}

	public boolean hasNext ()//Returns true if the 'next' field is not null
	{
		if (next != null)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	public String getNextData ()//Retruns the string held by the next node.
	{
		return next.getData();	
	}

	public Node getNextNode ()//Returns the nodes 'next' field
	{
		return next;
	}

	public String getData () // Returns the data held by the node
	{
		return data;
	}

	public void setNext (Node n) //Sets 'n' as the node's 'next' field
	{
		next = n;
	}

}