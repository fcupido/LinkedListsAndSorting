public class Node 
{
	private String data;
	private Node next;

	public Node ()
	{
		data = null;
		next = null;
	}

	public Node (String s)
	{
		data = s;
		next = null;
	}

	public Node (String s, Node n)
	{
		data = s;
		next = n;
	}

	public boolean hasNext ()
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

	public String getNextData ()
	{
		return next.getData();	
	}

	public Node getNextNode ()
	{
		return next;
	}

	public String getData ()
	{
		return data;
	}

	public void setNext (Node n)
	{
		next = n;
	}

}