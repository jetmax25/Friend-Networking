import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


class Person{
	
	private String name;
	private ArrayList<Person> friends;
	private boolean checked = false; 
	
	public Person(String name){
		this.name = name;
		friends = new ArrayList<Person>();
	}
	
	public void addFriend(Person person)
	{
		friends.add(person);
	}
	
	public ArrayList<Person> getFriends()
	{
		checked = true;
		return this.friends;
		
	}
	
	public int count()
	{
		return friends.size();
	}
	
	public int allCount()
	{
		if(checked) return 0;
		checked = true;
		int num = 1;
		for(Person p : friends)
		{
			num += p.allCount();
		}
		
		return num;
	}
	
	public void reset()
	{
		if(!checked) return;
		
		checked = false;
		for(Person p : friends)
		{
			p.reset();
		}
	}
	public String getName(){
		return this.name;
	}
}

public class friends {

	public static void main (String[] args)
	{
		Scanner in = new Scanner(System.in);
		
		int numCases = in.nextInt();
		
		for(int i = 0; i < numCases; i++)
		{
			HashMap<String, Person> people = new HashMap<String, Person>();
			int numPeople = in.nextInt();
			String tempString = in.next();
			Person me = new Person(tempString);
			
			people.put(tempString, new Person(tempString));
			
			for(int j = 0; j < numPeople - 1; j++){
				tempString = in.next();
				
				people.put(tempString, new Person(tempString));
			}
			int connections = in.nextInt();
			
			for(int j = 0; j < connections; j++)
			{
				String friend1, friend2;
				friend1 = in.next();
				friend2 = in.next();
				
				people.get(friend1).addFriend(people.get(friend2));
				people.get(friend2).addFriend(people.get(friend1));
			}
			
			
			me = people.get("You");
			int myCount = me.count() + me.allCount() -1;
			me.reset();
			
			int enemies = in.nextInt();
			
			System.out.println("Social Network: " + (i + 1));
			
			for(int j = 0; j < enemies; j++)
			{
				String enName = in.next();
	
				if(people.containsKey(enName)){
					Person enemy = people.get(enName);
					int enCount = enemy.count() + enemy.allCount() -1;
					enemy.reset();
					System.out.println(enemy.getName() + ": Difference of " + (myCount - enCount) + " point(s)."); 
				}
				else{
					System.out.println(enName + ": Difference of " + myCount + " point(s).");
				}
				
			}
			
			

			
			
		}
	}
	
	
}

