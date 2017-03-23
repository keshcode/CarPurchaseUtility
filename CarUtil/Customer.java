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
