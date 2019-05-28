package foodapp.dao;

import java.util.Scanner;

public final class Constants {
	private Constants() {
		//restrict initialization
	}

	/* �ܼ� �Է� */
	public static final Scanner		CONSOLE		= new Scanner(System.in);


	/* Ȩ������ �޴���ȣ */
	public static final int			SIGNUP				= 1;
	public static final int			SIGNIN				= 2;
	public static final int			LOGOFF				= 3;
	public static final int			ORDER				= 4;
	public static final int			VIEW_ORDER			= 5;
	public static final int			SHOW_USERS			= 6;
	
	public static final int			MENU_EXIT			= 0;
	

	public static final int 		WINDOW_WIDTH 		= 900;
	public static final int 		WINDOW_HEIGHT 		= 900;

	public static final String 		INIT_PAGE 			= "Card - Home Page";
	public static final String 		FOOD_MENU_PAGE 		= "Card - Food Menu";
	public static final String 		ORDER_PAGE 			= "Card - Order";
	public static final String 		ORDER_VIEW_PAGE 	= "Card - View Order";
	public static final String 		SIGN_UP_PAGE 		= "Card - Sign Up";
	public static final String 		MY_PAGE 			= "Card - My Page";

	/* ���� �޴� ���� */
	//���� �޴���ȣ
//	public static final int			MENU_BURGER			= 1;
//	public static final int			MENU_CHICKEN		= 2;
//	public static final int			MENU_COKE			= 3;
//	public static final int			MENU_MILK			= 4;
//	//���� �̸�
//	public static final String		BURGER				= "�ܹ���";
//	public static final String		CHICKEN				= "ġŲ";
//	public static final String		COKE				= "�ݶ�";
//	public static final String		MILK				= "����";
//	//���� ����
//	public static final int			PRICE_BURGER		= 2000;
//	public static final int			PRICE_CHICKEN		= 9000;
//	public static final int			PRICE_COKE			= 1000;
//	public static final int			PRICE_MILK			= 500;


	/* User �α��� ���� */
	public static final boolean		OFF					= false;
	public static final boolean		ON					= true;
}
