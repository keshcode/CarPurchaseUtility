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
	HashSet<Integer> generatePrize(ArrayList<Integer> randomId,HashMap<Integer,Customer> customerHash){
		HashSet<Integer> winnerId = new HashSet<Integer>();
		Random generator = new Random();
		Object[] values = customerHash.values().toArray();
		
		for(int i=0;i<RANDOM_GENERATED_IDS_LIMIT;i++){
			Customer randomValue = (Customer) values[generator.nextInt(values.length)];
			for(int j=0;j<RANDOM_ID_LIMIT;j++){
				if(randomValue.id == randomId.get(j)){
					winnerId.add(randomId.get(j));
					break;	
				}
			}
		}
		return winnerId;
	}
}
