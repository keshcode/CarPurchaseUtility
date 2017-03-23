class Car implements Constants{
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
