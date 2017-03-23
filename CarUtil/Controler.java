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
				HashSet<Integer> winnerId = generatePrize(randomId,customerHash);
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
