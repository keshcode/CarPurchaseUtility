import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

class Admin  implements Constants{
	Customer addNewCustomer(int id,String name,int carId,int carType,String model,int price){
		Customer obj = new Customer();
		obj.SetId(id);
		obj.SetName(name);
		obj.SetListOfPurchasedCars(addNewCarToCustomer(carType,carId,model,price));
		return obj;
	}
	Car addNewCarToCustomer(int carType,int carId,String model,int price){
		Car obj;
		switch(carType){
			case MARUTI:
				obj = new Maruti(price);
				System.out.println(obj.resaleValue);
			break;	
			
			case HYUNDAI: 
				obj = new Hyundai(price);
				System.out.println(obj.resaleValue);
			break;
			case TOYOTA:
				obj = new Toyota(price);
				System.out.println(obj.resaleValue);
			break;
			default :
				obj = new Car();
				System.out.println(obj.resaleValue);
			break;	
		}
		obj.id = carId;
		obj.model = model;
		obj.price = price;
		return obj;
	}
	
	void listAllCustomer(HashMap<Integer,Customer> customerHash ,ArrayList<Customer> sortArray){
		for(Customer s : sortArray){    
        		Customer obj=customerHash.get(s.id);  
        		System.out.println("Cutomer Details:");  
        		System.out.println(obj.id+" "+obj.name);
        		System.out.println("Cutomer's Car Details");
        		for(Car itr:obj.listOfPurchasedCars){
        			System.out.println(itr.id+" "+itr.model+" "+itr.price+" "+itr.resaleValue);
        		}   
        		System.out.println("______________________________");
    		}  
	}
	void individualCustomer(HashMap<Integer,Customer> customerHash,int customerId){
		Customer obj = customerHash.get(customerId);
		System.out.println("Cutomer Details:");  
        	System.out.println(obj.id+" "+obj.name);
        	for(Car itr:obj.listOfPurchasedCars){
        		System.out.println("Cutomer's Car Details");
        		System.out.println(itr.id+" "+itr.model+" "+itr.price+" "+itr.resaleValue);
        	}   
	}
	ArrayList<Integer> generatePrize(ArrayList<Integer> randomId,HashMap<Integer,Customer> customerHash){
		ArrayList<Integer> winnerId = new ArrayList<Integer>();
		Random generator = new Random();
		Object[] values = customerHash.values().toArray();
		
		for(int i=0;i<RANDOM_GENERATED_IDS_LIMIT;i++){
			Customer randomValue = (Customer) values[generator.nextInt(values.length)];
			System.out.println("random number generated: "+randomValue.id);
			for(int j=0;j<RANDOM_ID_LIMIT;j++){
				if(randomValue.id == randomId.get(j)){
					winnerId.add(randomId.get(j));
					break;	
				}
			}
		}
		//HashSet<String> hs = new HashSet<String>();
		//hs = addAll(winnerId);
		//winnerId.clear();
		//winnerId.addAll(hs);
		return winnerId;
	}
}

class Controler extends Admin implements Constants{
	int choice;
	HashMap<Integer,Customer> customerHash = new HashMap<Integer,Customer>();
	HashMap<Integer,Boolean> carHash = new HashMap<Integer,Boolean>();
	ArrayList<Customer> sortArray = new ArrayList<Customer>();
	
	void Operationoption(){
		System.out.println("Choose the respective option to perform task \n"
				  +"Press 1 : Add new Customer\n"
				  +"Press 2 : Add new Car to existing Customer\n"
				  +"press 3 : List of Customer\n"
				  +"Press 4 : Details of individual Customer Based On ID\n"
				  +"Press 5 : Generate prizes for Customer\n"
				  +"Press 0 : Exit\n");
		Scanner sc = new Scanner(System.in);
		choice = sc.nextInt();
	}
	void actionOnChoice(){
		switch(choice){
			case ADD_NEW_CUSTOMER :
				Scanner sc = new Scanner(System.in);
				System.out.println("Enter new Customer Id\n");
				int id = sc.nextInt();
				
				if(!customerHash.containsKey(id)){
					System.out.println("Enter new Customer Name\n");
					String name = sc.next();
					
					System.out.println("Enter new Car id\n");
					int carId = sc.nextInt();
					
					System.out.println("Press 1 :Maruti\n" + "Press 2: Hyundai\n" + "Press 3: Toyota\n");
					int carType = sc.nextInt();
					
					if(!carHash.containsKey(carId)){
						System.out.println("Enter Car model no\n");
						String model = sc.next();
						
						System.out.println("Enter Car price\n");
						int price = sc.nextInt();
						
						Integer wId = new Integer(id);
						Integer wCarId = new Integer(carId);
						Customer custObj = addNewCustomer(id,name,carId,carType,model,price);
						customerHash.put(wId,custObj);
						sortArray.add(custObj);
						carHash.put(wCarId,true);
						
					}
					else{
						System.out.println("#Car already exists#\n");
					}
				}
				else{
					System.out.println("#Customer already exists#\n");
				}
			break;
			case ADD_NEW_CAR_TO_EXISTING_CUSTOMER :
				int carType,customerId;
				Scanner sc1 = new Scanner(System.in);
				System.out.println("Enter the customer id where to insert ");
				customerId = sc1.nextInt();
				System.out.println("Press 1 :Maruti\n" + "Press 2: Hyundai\n" + "Press 3: Toyota");
				carType = sc1.nextInt();
				Customer obj = customerHash.get(customerId);
				System.out.println("Enter car ID ");
				int carId = sc1.nextInt();
				if(!carHash.containsKey(carId)){
					System.out.println("Enter car model");
					String model = sc1.next();
					System.out.println("Enter car Price");
					int price = sc1.nextInt();
					obj.SetListOfPurchasedCars(addNewCarToCustomer(carType,carId,model,price));
				}
				else{
					System.out.println("Car id already taken");
				}
			break;
			case LIST_OF_CUSTOMER :
					Collections.sort(sortArray,new Comparator<Customer>(){
						public int compare(Customer sortArray1, Customer sortArray2){
							return sortArray1.name.compareTo(sortArray2.name);
						}
					});
					listAllCustomer(customerHash,sortArray);
			break;
			case DETAILS_OF_INDIVIDUAL_CUSTOMER :
				Scanner sc2 = new Scanner(System.in);
				System.out.println("Enter Customer Id");
				customerId = sc2.nextInt();
				if(customerHash.containsKey(customerId))
					individualCustomer(customerHash,customerId);
				else
					System.out.println("customer with this id does not exists");
			break;
			case GENERATE_PRIZE :
				Scanner sc3 = new Scanner(System.in);
				System.out.println("Enter any 3 random Customer Id's");
				ArrayList<Integer> randomId = new ArrayList<Integer>();
				for(int i=0;i<RANDOM_ID_LIMIT;i++){
					int idValue = sc3.nextInt();
						Integer widValue = new Integer(idValue); 
						randomId.add(idValue);
				}				
				ArrayList<Integer> winnerId = generatePrize(randomId,customerHash);
				if(winnerId.size()==0)	
					System.out.println("NO one won");
				else{
					System.out.println("====LIST OF WINNERS ==== ");
					for(Integer ids :winnerId)
						System.out.println(ids);
				}
			break;
		}
	}
	
}

class Customer {
	int id;
	String name;
	ArrayList<Car> listOfPurchasedCars = new ArrayList<Car>();
	Customer(){
		id = 0;
		name ="";
	}
	Customer(int i , String name , Car carObj){
		this.id = id;
		this.name = name;
		listOfPurchasedCars.add(carObj);
	}
	int GetId(){
		return id;
	}
	void SetId(int id){
		this.id = id; 
	}
	String GetName(){
		return name;
	}
	void SetName(String name){
		this.name = name;
	}
	void SetListOfPurchasedCars(Car carObj){
		listOfPurchasedCars.add(carObj);
	}
	ArrayList<Car> GetListOfPurchasedCars(){
		return listOfPurchasedCars;
	}
}

class Car{
	int id;
	String model;
	int price;
	double resaleValue;
	Car(){
		id=0;
		model="";
		price=0;
	}
	Car(int id,String model,int price){
		this.id = id;
		this.model = model;
		this.price = price;
	}
	int GetId(){
		return id;	
	}
	void SetId(int id){
		this.id = id; 
	}
	String GetModel(){
		return model;
	}
	void SetModel(String model){
		this.model = model;
	}
	int GetPrice(){
		return price;
	}
	void SetPrice(int price){
		this.price = price;
	}
}

class Maruti extends Car implements Constants{
	Maruti(int price){
		this.resaleValue = MARUTI_RESALE_PERCENT*price;
	}
}

class Hyundai extends Car implements Constants{
	Hyundai(int price){
		this.resaleValue = HYUNDAI_RESALE_PERCENT*price;
	}
}

class Toyota extends Car implements Constants{
	Toyota(int price){
		this.resaleValue = TOYOTA_RESALE_PERCENT*price;
	}
}

interface Constants{
	double MARUTI_RESALE_PERCENT = (0.8);
	double HYUNDAI_RESALE_PERCENT = (0.6);
	double TOYOTA_RESALE_PERCENT = (0.4);
	int ADD_NEW_CUSTOMER = 1;
	int ADD_NEW_CAR_TO_EXISTING_CUSTOMER = 2;
	int LIST_OF_CUSTOMER =3;
	int DETAILS_OF_INDIVIDUAL_CUSTOMER = 4;
	int GENERATE_PRIZE =5;
	int MARUTI = 1;
	int HYUNDAI = 2;
	int TOYOTA = 3;
	int RANDOM_ID_LIMIT = 3;
	int RANDOM_GENERATED_IDS_LIMIT = 6;
}
public class CarPurchaseUtility{
		
	public static void main(String args[]){
		Controler cont = new Controler();
		while(true){
			cont.Operationoption();
			if(cont.choice==0)
			break;
			cont.actionOnChoice();
		}
	}
}
