package com.kh.food.view;

import static com.kh.food.view.Constants.LOGOFF;
import static com.kh.food.view.Constants.MENU_EXIT;
import static com.kh.food.view.Constants.OFF;
import static com.kh.food.view.Constants.ORDER;
import static com.kh.food.view.Constants.SHOW_USERS;
import static com.kh.food.view.Constants.SIGNIN;
import static com.kh.food.view.Constants.SIGNUP;
import static com.kh.food.view.Constants.VIEW_ORDER;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeMap;

import com.kh.food.controller.UserController;
import com.kh.food.model.vo.Food;
import com.kh.food.model.vo.MenuChoiceException;
import com.kh.food.model.vo.User;

public class MainMenu {
	public final static Scanner CONSOLE = new Scanner(System.in);
	private UserController controller;
//	private LoginPageFrame winFrame = new LoginPageFrame("food");
	
	public void showMainMenu() {
		System.out.println("=== Ȩ������ ===");
		System.out.println("1. ȸ�� ����");
		System.out.println("2. �α���");
		System.out.println("3. �α׿���");
		System.out.println("4. �ֹ� �ϱ�");
		System.out.println("5. �ֹ� ��ȸ");
		System.out.println("6. �մ���Ȳ ��ȸ");
		System.out.println("0. ���α׷� ����.");
		System.out.print("�޴� �Է�: ");
	}

	public void initPageView() {
		
	}

	public void mainMenu(UserController controller) {
		this.controller = controller;
		int choice = -1;
		do {
			try {
				showMainMenu();
				choice = CONSOLE.nextInt(); CONSOLE.nextLine();
				if(choice <MENU_EXIT || choice > SHOW_USERS)
					throw new MenuChoiceException(choice);

				switch(choice) {
					case SIGNUP: controller.signUp(); break;
					case SIGNIN: controller.signIn(); break;
					case LOGOFF: controller.logOff(); break;
					case ORDER: controller.order(); break;
					case VIEW_ORDER: controller.viewOrder(); break;
					case SHOW_USERS: controller.showUsers(); seatView(); break;
					case MENU_EXIT:
						controller.logOff();
						controller.storeToFile();
						System.out.println("���α׷��� �����մϴ�.");
						return;
				}
			} catch(MenuChoiceException e) {
				e.showWrongChoice();
				System.out.println("�޴������� �ٽ� �մϴ�.\n");
			}
		} while(choice != 0);
	}
	
	public User signUpView() {
		System.out.println("=== ȸ�� ���� ===");
		System.out.print("ȸ�� �̸� : ");
		String username = CONSOLE.nextLine();
		System.out.print("ȸ�� ��ȭ : ");
		String phone = CONSOLE.nextLine();
		System.out.print("ȸ�� �̸��� : " );
		String email = CONSOLE.nextLine();
		System.out.print("ȸ�� �ּ� : ");
		String address = CONSOLE.nextLine();
		
		User user = new User(username, phone, email, address, OFF,
				new TreeMap<Food, Integer>(), null, -1);
		return user;
	}

	public String signInView() {
		System.out.println("=== ȸ�� �α��� ===");
		System.out.print("�ڵ�����ȣ : ");
		String phone = CONSOLE.nextLine();

		return phone;
	}

	public void showFoodMenu() {
		System.out.println("=== ���� �޴� ===");
		System.out.println("  1. �ܹ��� --- 2,000");
		System.out.println("  2. ġŲ --- 9,000");
		System.out.println("=== ���� �޴� ===");
		System.out.println("  3. �ݶ� --- 1,000");
		System.out.println("  4. ���� --- 500");
		System.out.println("  0. �ֹ� ����");
		System.out.print("�޴���ȣ �Է� : ");
	}

	public Map<Food,Integer> orderView(){
		Map<Food, Integer> orderList = new TreeMap<Food,Integer>();
		List<Food> foodMenu = controller.getFoodMenu();
		int choice = -1;
		int qty = 0;
		do {
			try {
				this.showFoodMenu();
				choice = CONSOLE.nextInt(); CONSOLE.nextLine();

				if(choice > MENU_EXIT && choice <= foodMenu.size()) {
					System.out.print("���� : ");
					qty = CONSOLE.nextInt(); CONSOLE.nextLine();
					if(qty <0)
						throw new MenuChoiceException(qty);

					orderList.put(foodMenu.get(choice-1), qty);
				}
				else if(choice == MENU_EXIT) {
					System.out.println("�ֹ��� ��Ĩ�ϴ�.");
					return orderList;
				}
				else {
					throw new MenuChoiceException(choice);
				}

//				switch(choice) {
//					case MENU_BURGER:
//						orderList.put(BURGER, qty);
//						total += PRICE_BURGER * qty;
//						break;
//					case MENU_EXIT:
//						System.out.println("�ֹ��� �Ϸ��Ͽ����ϴ�.");
//						return orderList;
//					default:
//						throw new MenuChoiceException(choice);
//				}
			} catch(MenuChoiceException e) {
				e.showWrongChoice();
				System.out.println("�޴������� �ٽ��մϴ�.\n");
			}
		}while(choice!= 0);
		
		return orderList;
	}
	
	public void seatView() {
		System.out.println("�¼� ��Ȳ(X: ���¼�)");
		boolean[] reservations = controller.getReservations();
		
		for(int i=0;i<reservations.length; i++) {
			if(i==reservations.length/2)
				System.out.println();
			System.out.print((reservations[i]==true ? 'O':'X') + " ");
		}
		System.out.println();
	}

	public int reserveSeatView() {
		seatView();
		boolean[] reservations = controller.getReservations();
		Set<Integer> seatNos = new HashSet<Integer>();
		
		for(int i=0;i<reservations.length; i++)
			if(reservations[i]==false) seatNos.add(i+1);

		char answer = '\u0000';
		int seatNo = -1;
		do {
			System.out.print("�Ļ��ϰ� ���ðڽ��ϱ�? (Y/N) : ");
			answer = Character.toUpperCase(CONSOLE.nextLine().charAt(0));
			if(answer =='Y') {
				try {
					System.out.print("�¼� ��ȣ ���� (");
					Iterator<Integer> itr = seatNos.iterator();
					while(itr.hasNext())
						System.out.print(itr.next() + " ");
					System.out.print("�� ��1) : ");
					seatNo = CONSOLE.nextInt(); CONSOLE.nextLine();
					if(!seatNos.contains(seatNo) || seatNo <0 || seatNo >= reservations.length)
						throw new MenuChoiceException(seatNo);

					controller.getReservations()[seatNo-1] = true;

				} catch(MenuChoiceException e) {
					e.showWrongChoice();
					System.out.println(seatNo + "�� �ڸ��� ������ �� �����ϴ�.");
				}
			}
			else if(answer =='N') {
				System.out.println("���� ������ �����ϼ̽��ϴ�.");
			}
			else {
				System.out.println("Y/N ���� �ٽ� �Է����ּ���.");
			}
		} while(answer!='Y' && answer!='N');

		return seatNo;
	}
	
}
