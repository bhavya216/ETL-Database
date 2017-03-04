package etl;

public class Product {

	private int id;
	private String name;
	private int sal;
	private String add;
        private String gender;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSal() {
		return sal;
	}

	public void setSal(int sal) {
		this.sal = sal;
	}

	public String getAdd() {
		return add;
	}

	public void setAdd(String add) {
		this.add = add;
	}
        
        public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Product(int id, String name, int sal, String add, String gender) {
		super();
		this.id = id;
		this.name = name;
		this.sal = sal;
		this.add = add;
                this.gender=gender;
	}

}
