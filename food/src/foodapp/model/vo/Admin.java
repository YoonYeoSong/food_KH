package foodapp.model.vo;

import java.util.GregorianCalendar;
import java.util.Map;
import java.util.TreeMap;

public class Admin extends User {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
			System.out.println("\t--- ����� ---");
			for(Map.Entry<Food, Integer> entry : salesResult.entrySet())
				System.out.println("\t" + entry.getKey() + "  :  �� " + entry.getValue() + " �� �Ǹ�.");
		}
	}
	
	public Map<Food, Integer> getSalesResult() { return (TreeMap<Food, Integer>)salesResult; } 
	public void setSalesResult(Map<Food, Integer> salesResult) { this.salesResult = salesResult; }
}
