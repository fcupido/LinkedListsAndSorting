import java.io.PrintWriter;
public class MyLinkedList 
{
	private Node head;
	private Node cursor;

	public MyLinkedList()
	{
		head = null;
		cursor = null;
	}
	public MyLinkedList(String s)
	{
		head = new Node (s);
		cursor = null;
	}

	public void add (String data)
	{
		//System.out.println("Adding: " + data + " to list");
		if (data == null)
		{
			System.out.println("Cannot add an empty element to the list.");
			System.exit(-1);
		}

		Node node = new Node (data);

		if (head == null)
		{
			head = node;
		}
		else if (head.getData().compareTo(data) > 0)
		{
			//System.out.println("Adding: "+data+" To front of list: "+ head.getData());
			pushFront(node);
		}
		else
		{
			cursor = head;
			while (cursor.hasNext() && cursor.getNextData().compareTo(data) < 0)
			{
				cursor = cursor.getNextNode();
			}
			insert(node, cursor);
		}
	}

	/**
	*Inserts a node after a specified node.
	*It sets the next field of the at Node as the next field of the Node node
	* and then sets the node Node as the next field of the at node.
	*@param node The Node to insert.
	*@param at The Node which node will follow. 
	*/
	public void insert (Node node, Node at)
	{
		node.setNext(at.getNextNode());
		at.setNext(node);
	}
	/**
	*Note: Do not use to add to aen empty list!!
	*
	*
	*/
	public void pushFront(Node node)
	{
		if (head != null)
			node.setNext(head);
		head = node;
	}

	@Override
	public String toString ()
	{
		String s = new String ("");
		if(head == null)
		{
			s.concat("This list is empty.");
		} 
		else 
		{
			cursor = head;
			while (cursor != null)
			{
				s.concat(cursor.getData());
				s.concat(" ");
				cursor = cursor.getNextNode();
			}
		}
		return s;
	}

	public void print (PrintWriter writer)
	{
		if(head == null)
		{
			System.out.println("This list is empty.");
		} 
		else 
		{
			cursor = head;
			while (cursor != null)
			{
				writer.print(cursor.getData() + " ");
				cursor = cursor.getNextNode();
			}
		}
		writer.println();
	}

	public String getHeadData()
	{
		return head.getData();
	}
}