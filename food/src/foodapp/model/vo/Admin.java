package foodapp.model.vo;

import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

public class Admin extends User{
	//�޴�, �Ǹŷ�
	private Map<Food, Integer> salesResult;

	public Admin(String username, String password, String phone, String email, String address, boolean logged,
			Map<Food, Integer> orderList, GregorianCalendar orderCreated, String recentPayMethod, int seatNo,
			boolean ordering, Map<Food, Integer> salesResult) {
		super(username, password, phone, email, address, logged, orderList, orderCreated, recentPayMethod, seatNo,
				ordering);

		this.salesResult = salesResult;
	}
	
	@Override
	public void showUserInfo() {
		super.showUserInfo();
		if(salesResult.size() >0) {
			System.out.println("--- ����� ---");
			for(Map.Entry<Food, Integer> entry : salesResult.entrySet())
				System.out.println(entry.getKey() + "   :  �� " + entry.getValue() + " �� �Ǹ�.");
		}
	}

	public Map<Food, Integer> getSalesResult() { return salesResult; } 
	public void setSalesResult(Map<Food, Integer> salesResult) { this.salesResult = salesResult; }
}
