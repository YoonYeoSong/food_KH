package com.kh.food.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.kh.food.model.vo.Food;
import com.kh.food.model.vo.User;
import com.kh.food.view.MainMenu;

public class UserController {
	private final File dataFile=new File("Users.dat"); //List<User> ����� ����
 
	private List<User> users = new ArrayList<User>(); //User ����Ʈ

	private List<Food> foodMenu; //���ð����� ���ĸ޴�
	
	private String phone; //�α��� ���� ����ȣ (User��ü�� 1:1����)

	private MainMenu menu = new MainMenu(); //�޴� ������ ���� ��ü

	private final static int SEATS = 10; //�¼� ��

	private boolean[] reservations = new boolean[SEATS]; //�¼� ��������

	/* ���θ޴� �����ϱ� */
	public void mainMenu() throws Exception {
		phone = null;
		this.readFromFile();
		this.loadDefaultFoodMenu();
		menu.mainMenu(this);
	}

	public void readFromFile() {
		if(dataFile.exists()==false)
			return;

		try (FileInputStream file=new FileInputStream(dataFile);
			ObjectInputStream in=new ObjectInputStream(file);){
			
			this.users = (ArrayList<User>)in.readObject();
			this.foodMenu = (ArrayList<Food>)in.readObject();
			
		} catch(WriteAbortedException e) {
			e.printStackTrace();
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}

	public void storeToFile() {

		try (FileOutputStream file=new FileOutputStream(dataFile);
			ObjectOutputStream out=new ObjectOutputStream(file);){
			
			out.writeObject(this.users);
			out.writeObject(this.foodMenu);
			
			out.close();
		} catch(Exception e) {
			e.printStackTrace();
			return;
		}
	}
	
	public void signUp() {
		if(this.phone != null)
			System.out.println("ȸ������ �Ϸ��� ���� �α׾ƿ� ���ּ���.");

		User user = menu.signUpView();

		if(isDuplicate(user)) {
			System.out.println("�̹� �����ϴ� ȸ�� �Դϴ�.");
			return;
		}
		else {
			System.out.println("ȸ�������� ���������� ó���Ǿ����ϴ�.");
			users.add(user);
		}

		Collections.sort(this.users, (i,j)->{
			return i.getUsername().compareTo(j.getUsername());
		});
	}


	public void signIn() {
		String phoneInput = menu.signInView();
		if(phoneInput.equals(this.phone) || phone != null)
			System.out.println("�̹� �α��� �Ǿ� �ֽ��ϴ�.");

		User user = getUserByPhone(phoneInput);
		if(user==null) {
			System.out.println("�α��ο� ���� �Ͽ����ϴ�.");
		}
		else {
			user.setLogged(true);
			setPhone(phoneInput);
			System.out.println("�α��� �ƽ��ϴ�.");
		}
	}
	
	public void signIn(String phoneTextField) {
		if(phoneTextField.equals(this.phone))
			System.out.println("�̹� �α��� �Ǿ� �ֽ��ϴ�.");

		User user = getUserByPhone(phoneTextField);
		if(user==null) {
			System.out.println("�α��ο� ���� �Ͽ����ϴ�.");
		}
		else {
			user.setLogged(true);
			setPhone(phoneTextField);
			System.out.println("�α��� �ƽ��ϴ�.");
		}
	}

	public void logOff() {
		User user = null;
		Iterator<User>itr = users.iterator();
		while(itr.hasNext()) {	
			user = itr.next();
			if(user.getPhone().equals(this.phone)) {
				user.setLogged(false);
				if(user.getSeatNo() >=1 && user.getSeatNo() <=reservations.length) {
					reservations[user.getSeatNo() -1] = false;
					user.setSeatNo(0);
				}
				this.phone = null;
				System.out.println("�α׾ƿ� �ƽ��ϴ�.");
			}
		}
	}

	public void order() {
		if (phone==null) {
			System.out.println("�α��� �� �̿��� �� �ֽ��ϴ�.");
			return;
		}
		
		//�ֹ�
		User user = getUserByPhone(this.phone);
		Map<Food, Integer> orderList = menu.orderView();
		user.setOrderList(orderList);

		//�¼�
		if(orderList.size() > 0) {
			this.reserveSeat();
		
			//�ֹ��Ѿ� �� �ֹ����� ���
			int total = 0;
			for(Map.Entry<Food, Integer> entry : orderList.entrySet()) {
				total += (entry.getValue() * entry.getKey().getMenuPrice());
				System.out.println("\t" + entry.getKey().getMenuName() + " ----- "
						+ entry.getKey().getMenuPrice() + " * " + entry.getValue() + "��");
			}

			System.out.println("�ֹ��Ͻ� �Ѿ��� : " + total + "�� �Դϴ�.");
		}
		else
			System.out.println("�ֹ��� ����ϼ̽��ϴ�.");
	}

	public void viewOrder() {
		if (phone==null) {
			System.out.println("�α��� �� �̿��� �� �ֽ��ϴ�.");
			return;
		}
		else {
			User user = getUserByPhone(phone);
			if(user!= null)
				user.showOrderList();
			else{
				System.out.println("�ش��ϴ� ������ �����ϴ�.");
			}
		}
	}

	public void reserveSeat() {
		int seatNo = menu.reserveSeatView();
		User user = getUserByPhone(this.phone);
		if(seatNo >=1 && seatNo <= reservations.length) {
			user.setSeatNo(seatNo);
		}
		if(user.getOrderList().size() > 0)
			user.setOrderCreated(new GregorianCalendar());
	}

	public void showUsers() {
		System.out.println("�̸�\t��ȭ\t�̸���\t�ּ�\t�α��λ���\t�ֹ���¥");

		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			itr.next().showUserInfo();
		}
	}

	private void loadDefaultFoodMenu() {
		if(this.foodMenu != null && foodMenu.size() > 0)
			return;

		foodMenu = new ArrayList<Food>();
		foodMenu.add(new Food(1, "�ܹ���", 2000));
		foodMenu.add(new Food(2, "ġŲ", 9000));
		foodMenu.add(new Food(3, "�ݶ�", 1000));
		foodMenu.add(new Food(4, "����", 500));

		Collections.sort(foodMenu, (i,j)->{
			return i.getMenuNo() - j.getMenuNo();
		});
	}

	private User getUserByPhone(String phoneNum) {
		User user = null;
		Iterator<User> itr = users.iterator();
		while(itr.hasNext()) {
			user = itr.next();
			if(user.getPhone().equals(phoneNum)) {
				return user;
			}
		}
		return null;
	}

	private boolean isDuplicate(User user) {
		Set<User> set = new HashSet<User>();
		for (User u: users) set.add(u);
		return !set.add(user);
	}

	//getter setter
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	public boolean[] getReservations() { return reservations; }
	public void setReservations(boolean[] reservations) { this.reservations = reservations; }
	public static int getSeats() { return SEATS; }
	public List<Food> getFoodMenu() { return foodMenu; }
}
