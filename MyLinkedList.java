//MyLinkedList.java
import java.io.PrintWriter;

/**
*The class MyLInkedList manages a list of nodes containing strings and provides methods for adding, traversing,
* and modifying elements of the list.
*NOTE, This list will add elements in Lexicographical Order.
*/
public class MyLinkedList 
{
	private Node head;
	/**
	*The head referrence, This is the starting point on the list
	*/

	private Node cursor;
	/**
	*Cursor provides a node reference that can be used to traverse the list.
	*/

	public MyLinkedList() //Constructs an empty linked list
	{
		head = null;
		cursor = null;
	}
	public MyLinkedList(String s) //Construcs a node using the provided string s and then sets that
	{							  // node as the head of the list.
		head = new Node (s);
		cursor = null;
	}

	public void add (String data) //Adds an element to the list, NOTE: The elements are added in Alphabetic order
	{
		//System.out.println("Adding: " + data + " to list");
		if (data == null) //Checks to see of the provided data is not null
		{
			System.out.println("Cannot add an empty element to the list.");
			System.exit(-1);
		}

		Node node = new Node (data); //Creates a new node with the provided data

		if (head == null) //If the list is empty, this new node is made head.
		{
			head = node;
		}
		else if (head.getData().compareTo(data) > 0) //If data has a value less than that on head, then it is made
		{											//the new head, and the old head is set to the next pointer of the new node
			//System.out.println("Adding: "+data+" To front of list: "+ head.getData());
			pushFront(node);
		}
		else
		{
			cursor = head; //IF the new node does not belong at the beggining, we traverse the list untill we find
			while (cursor.hasNext() && cursor.getNextData().compareTo(data) < 0) //The last node with a value less that, or the list ends
			{										//The new data, then we add the new node at that spot.
				cursor = cursor.getNextNode();//Traversing the List
			}
			insert(node, cursor); //Inserting the node
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
	*Adds a node to the beginning of the list, making it the new head.
	*/
	public void pushFront(Node node)
	{
		if (head != null)
			node.setNext(head);
		head = node;
	}

	/**
	*Prints to a PrintWriter the contents of the list. Separated by spaces.
	*@param writer A PrintWriter that will be used to print the contents of the list.
	*
	*/
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

	public String getHeadData() //Returns the string currently at the head of the list.
	{
		return head.getData();
	}
}