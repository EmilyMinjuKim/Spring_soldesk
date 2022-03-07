package kr.co.soft.mapperVO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVO {

	private String id;
	private String name;
	private String brand;
	private int price;
	
	public ProductVO() {
		
	}
	
	public ProductVO(String id, String name, String brand, int price) {
		this.id = id;
		this.name = name;
		this.brand = brand;
		this.price = price;
	}

	@Override
	public String toString() {
		
		return "ProductVO[id="+id+" name="+name+" brand="+brand+" price="+price+"]";
	}

}
