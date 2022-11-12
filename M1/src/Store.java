
public class Store {
	
	private String name, location;

	public Store(String name, String location) {
		this.name = name;
		this.location = location;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public String toString() {
		return name + " (" + location +")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Store) {
			Store other = (Store) obj;
			return this.name.equalsIgnoreCase(other.name) &&
					this.location.equalsIgnoreCase(other.location);
		} else {
			return false;
		}
	}

}
