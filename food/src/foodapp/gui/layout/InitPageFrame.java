package foodapp.gui.layout;

import static foodapp.dao.Constants.FOOD_MENU_PAGE;
import static foodapp.dao.Constants.INIT_PAGE;
import static foodapp.dao.Constants.MY_PAGE;
import static foodapp.dao.Constants.ORDER_PAGE;
import static foodapp.dao.Constants.ORDER_VIEW_PAGE;
import static foodapp.dao.Constants.SIGN_UP_PAGE;
import static foodapp.dao.Constants.WINDOW_HEIGHT;
import static foodapp.dao.Constants.WINDOW_WIDTH;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import org.openide.awt.DropDownButtonFactory;

import foodapp.dao.UserRepository;
import foodapp.gui.event.HomeBtnEventHandler;
import foodapp.gui.event.MenuPageBtnEventHandler;
import foodapp.gui.event.OrderBtnEventHandler;
import foodapp.gui.event.SignInEventHandler;
import foodapp.gui.event.SignOffEventHandler;
import foodapp.model.vo.Admin;
import foodapp.model.vo.Food;
import foodapp.model.vo.User;


public class InitPageFrame extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;
	
	private JSplitPane splitPane1, splitPane2, splitPane3;
	private JSplitPane splitMenuCenterPane;

	private JPanel cards;
	private JPanel card1, card2, card3, card4, card5, card6;

	private JPanel menuCards;
	private JPanel noodleCard, soupCard, riceCard;
	
	private JPanel topPanel, bottomPanel, rightPanel, leftPanel, subPanel1, subPanel2, centerPanel;
	
	private JButton logoBtn, menuDropDownBtn, myPageBtn, orderViewBtn;
	private JButton signInBtn1, signInBtn2, signUpBtn1, signUpBtn2;
	private JButton logOffBtn1, logOffBtn2;
	private JButton orderBtn;

	private JPanel orderSelectionPanel;
	
	private JLabel menuCategoryLabel, menuChoiceLabel, menuQtyLabel;
	private JTextField menuCategoryTxt;
	private JTextField subMenuTxt;
	private JButton payCardBtn, payCashBtn;
	
	private JLabel addMenuLabel;
	private JButton addMenuBtn;
	
	private JPanel menuCategoryPanel, menuChoicePanel, menuQtyPanel, payMethodPanel, addMenuPanel;
	
	private JButton noodleBtn, soupBtn, riceBtn;
	
	private DefaultTableModel modelN, modelS, modelR;
	
	private JComboBox menuQtyComboBox;
	private JTextField phoneTextField;
	private JPasswordField passwordField;
	
	private JTable menuNoodleTable;
	private JTable menuSoupTable;
	private JTable menuRiceTable;
	private JScrollPane scrollNoodlePane;
	private JScrollPane scrollSoupPane;
	private JScrollPane scrollRicePane;
	
	private UserRepository userRepo = null;
	

	/**
	 * Create the application.
	 */
	public InitPageFrame(UserRepository userRepo) throws Exception {
		this.userRepo = userRepo;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() throws Exception {

		CardLayout cl = new CardLayout();
		cards = new JPanel(cl);

		card1 = new JPanel(new BorderLayout());
		card2 = new JPanel(new BorderLayout());
		card3 = new JPanel(new BorderLayout());
		card4 = new JPanel(new BorderLayout());
		card5 = new JPanel(new BorderLayout());
		card6 = new JPanel(new BorderLayout());

		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null); //center window
		getContentPane().setLayout(new BorderLayout());


		/* ��� �޴� �� */
		this.createTopMenuBar();

		/* ȭ�� split */
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane1.setDividerLocation(300 + splitPane1.getInsets().top);
		splitPane1.setDividerSize(1);

		phoneTextField = new JTextField("", 11); //�ڵ��� 11�ڸ�
		passwordField = new JPasswordField(11);
		signInBtn1 = new JButton("�α���");
		signUpBtn1 = new JButton("ȸ������");
		logOffBtn1 = new JButton("�α׾ƿ�");
		orderBtn = new JButton("�ֹ��ϱ�");
		orderBtn.setName(ORDER_PAGE);
		signUpBtn1.setName(SIGN_UP_PAGE);


		leftPanel = new JPanel(new GridLayout(3,2));
		ImageIcon icon = null;

		icon = new ImageIcon(getClass().getResource("../images/noodle_char.jpg"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
		leftPanel.add(new JLabel(icon));
		icon = new ImageIcon(getClass().getResource("../images/noodle.png"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
		noodleBtn = new JButton(icon); noodleBtn.setName("NOODLE");
		leftPanel.add(noodleBtn);


		icon = new ImageIcon(getClass().getResource("../images/soup_char.jpg"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
		leftPanel.add(new JLabel(icon));
		icon = new ImageIcon(getClass().getResource("../images/soup.png"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
		soupBtn = new JButton(icon); soupBtn.setName("SOUP");
		leftPanel.add(soupBtn);

		icon = new ImageIcon(getClass().getResource("../images/rice_char.jpg"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(130, 100, Image.SCALE_SMOOTH));
		leftPanel.add(new JLabel(icon));
		icon = new ImageIcon(getClass().getResource("../images/rice.png"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(150, 100, Image.SCALE_SMOOTH));
		riceBtn = new JButton(icon); riceBtn.setName("RICE");
		leftPanel.add(riceBtn);
		

		logoBtn = new JButton(icon);
		logoBtn.setPreferredSize(new Dimension(100, 50));
		logoBtn.setName(INIT_PAGE);
		rightPanel = new JPanel(new GridLayout(4,1));
		subPanel1 = new JPanel(new GridLayout(2,1)); 
		JPanel p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p1.add(new JLabel("�ڵ��� ��ȣ"));
		p1.add(phoneTextField);
		p2.add(new JLabel("��й�ȣ"));
		p2.add(passwordField);
		subPanel1.add(p1);
		subPanel1.add(p2);
		subPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		subPanel2.add(signInBtn1);
		subPanel2.add(signUpBtn1);
		subPanel2.add(logOffBtn1);
		rightPanel.add(new JLabel());
		rightPanel.add(subPanel1);
		rightPanel.add(subPanel2);


		/* ���ĸ޴� �г� */
		CardLayout menuCl = new CardLayout();
		menuCards = new JPanel(menuCl);


		noodleCard = new JPanel(new BorderLayout());
		soupCard = new JPanel(new BorderLayout());
		riceCard = new JPanel(new BorderLayout());
		
		List<Food> foodMenuList = userRepo.getFoodMenu().getFoodMenuList();
		Iterator<Food> itr = foodMenuList.iterator();
		Food food = null;

		String[] colNames = {"ī�װ�", "�޴���ȣ", "�޴��̸�", "����"};
        modelN = new DefaultTableModel(colNames, 0);
        modelS = new DefaultTableModel(colNames, 0);
        modelR = new DefaultTableModel(colNames, 0);

		String[][] noodleList = new String[userRepo.getFoodMenu().getFoodMenuList().size()][colNames.length];
		String[][] soupList = new String[userRepo.getFoodMenu().getFoodMenuList().size()][colNames.length];
		String[][] riceList = new String[userRepo.getFoodMenu().getFoodMenuList().size()][colNames.length];

		int countN =0, countS=0, countR=0;

		while(itr.hasNext()) {
			food = itr.next();

			String[] temp = new String[] { food.getMenuCategory(), 
					String.valueOf(food.getMenuNo()), 
					food.getMenuName(), 
					food.toCurrency(food.getMenuPrice()) };
			switch(food.getMenuCategory()) {
				case "NOODLE": noodleList[countN++] = temp;break;
				case "SOUP": soupList[countS++] = temp; break;
				case "RICE": riceList[countR++] = temp; break;
			}
		}
		
		for(int i =0;  i<noodleList.length; i++)  modelN.addRow(noodleList[i]);
		for(int i =0;  i<soupList.length; i++)  modelS.addRow(soupList[i]);
		for(int i =0;  i<riceList.length; i++)  modelR.addRow(riceList[i]);

		menuNoodleTable = new JTable(modelN);
		menuNoodleTable.setName("NOODLE_TABLE");
		menuSoupTable = new JTable(modelS);
		menuSoupTable.setName("SOUP_TABLE");
		menuRiceTable = new JTable(modelR);
		menuRiceTable.setName("RICE_TABLE");

		menuNoodleTable.setAutoCreateRowSorter(true);
		menuSoupTable.setAutoCreateRowSorter(true);
		menuRiceTable.setAutoCreateRowSorter(true);

		menuNoodleTable.addMouseListener(this);
		menuSoupTable.addMouseListener(this);
		menuRiceTable.addMouseListener(this);

		scrollNoodlePane = new JScrollPane(menuNoodleTable);
		scrollSoupPane = new JScrollPane(menuSoupTable);
		scrollRicePane = new JScrollPane(menuRiceTable);
		
		noodleCard.add(scrollNoodlePane);
		soupCard.add(scrollSoupPane);
		riceCard.add(scrollRicePane);


		menuCards.add(noodleCard, "NOODLE");
		menuCards.add(soupCard,"SOUP");
		menuCards.add(riceCard, "RICE");

		orderSelectionPanel = new JPanel(new GridLayout(5,1));

		menuCategoryLabel = new JLabel("�޴� ī�װ�");
		menuCategoryTxt = new JTextField("", 10);
		menuCategoryTxt.setEditable(false);
		
		menuChoiceLabel = new JLabel("���� �޴�");
		subMenuTxt = new JTextField(10);
		subMenuTxt.setEditable(false);

		menuQtyLabel = new JLabel("�� ��");
		menuQtyComboBox = new JComboBox(new Integer[] {1,2,3,4,5});
		
		addMenuLabel = new JLabel("�޴� �߰�");
		addMenuBtn = new JButton("PUSH �߰�");
		addMenuBtn.setName("ADD_FOOD");

		
		payCardBtn = new JButton("ī�� �ֹ�");
		payCardBtn.setName("CARD");
		payCashBtn = new JButton("���� �ֹ�");
		payCashBtn.setName("CASH");
		
		payCardBtn.addMouseListener(this);
		payCashBtn.addMouseListener(this);

		menuCategoryPanel = new JPanel(new GridLayout(1,2));
		menuChoicePanel = new JPanel(new GridLayout(1,2));
		menuQtyPanel = new JPanel(new GridLayout(1,2));
		payMethodPanel = new JPanel(new GridLayout(1,2));
		addMenuPanel = new JPanel(new GridLayout(1,2));

		menuCategoryPanel.add(menuCategoryLabel); menuCategoryPanel.add(menuCategoryTxt);
		menuChoicePanel.add(menuChoiceLabel); menuChoicePanel.add(subMenuTxt);
		menuQtyPanel.add(menuQtyLabel); menuQtyPanel.add(menuQtyComboBox);
		payMethodPanel.add(payCardBtn); payMethodPanel.add(payCashBtn);
		addMenuPanel.add(addMenuLabel); addMenuPanel.add(addMenuBtn);
		
		orderSelectionPanel.add(menuCategoryPanel);
		orderSelectionPanel.add(menuChoicePanel);
		orderSelectionPanel.add(menuQtyPanel);
		orderSelectionPanel.add(addMenuPanel);
		orderSelectionPanel.add(payMethodPanel);
		
		noodleBtn.addMouseListener(this);
		soupBtn.addMouseListener(this);
		riceBtn.addMouseListener(this);
		
		addMenuBtn.addMouseListener(this);

		splitMenuCenterPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitMenuCenterPane.setTopComponent(menuCards);
		splitMenuCenterPane.setBottomComponent(orderSelectionPanel);
		splitMenuCenterPane.setDividerLocation(170 + splitMenuCenterPane.getInsets().top);
		splitMenuCenterPane.setDividerSize(1);
		centerPanel = new JPanel(new BorderLayout(0, 0));
		centerPanel.add(splitMenuCenterPane, BorderLayout.CENTER);

		topPanel = new JPanel(new GridLayout(1,3));
		topPanel.add(leftPanel);
		topPanel.add(centerPanel);
		topPanel.add(rightPanel);
		bottomPanel = new JPanel(new GridLayout(2,1));
		bottomPanel.add(new JPanel());
		bottomPanel.add(orderBtn);
		splitPane1.setTopComponent(topPanel);
		splitPane1.setBottomComponent(bottomPanel);

		/* �׺���̼� �޴� */
		JToolBar navBar = this.createNavBar();

		splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane2.setDividerLocation(65 + splitPane2.getInsets().top);
		splitPane2.setDividerSize(1);

		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(navBar);
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(splitPane1);
		splitPane2.setTopComponent(topPanel);
		splitPane2.setBottomComponent(bottomPanel);

		/* ���� �г� */
		JPanel topMostPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		signInBtn2 = new JButton("�α���");
		signUpBtn2 = new JButton("ȸ������");
		logOffBtn2 = new JButton("�α׾ƿ�");
		signUpBtn2.setName(SIGN_UP_PAGE);
		topMostPanel.add(signInBtn2);
		topMostPanel.add(signUpBtn2);
		topMostPanel.add(logOffBtn2);
		splitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane3.setDividerLocation(30 + splitPane3.getInsets().top);
		splitPane3.setDividerSize(1);

		topPanel = topMostPanel;
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(splitPane2);
		splitPane3.setTopComponent(topPanel);
		splitPane3.setBottomComponent(bottomPanel);

		splitPane1.setEnabled(false);
		splitPane2.setEnabled(false);
		splitPane3.setEnabled(false);
		splitMenuCenterPane.setEnabled(false);

		card1.add(splitPane3);
		card2.add(new FoodMenuPageFrame(cl, cards));
		card3.add(new OrderViewPageFrame(cl, cards));
		card4.add(new MyPageFrame(cl, cards));
		card5.add(new OrderPageFrame(cl, cards));
		card6.add(new SignUpPageFrame(cl, cards, userRepo));

		cards.add(card1, INIT_PAGE);
		cards.add(card2, FOOD_MENU_PAGE);
		cards.add(card3, ORDER_VIEW_PAGE);
		cards.add(card4, MY_PAGE);
//		cards.add(card5, ORDER_PAGE);
		cards.add(card6, SIGN_UP_PAGE);

		getContentPane().add(cards);
		
		/* �� component�� �̺�Ʈ �߰� */
		/* Ŭ���̺�Ʈ */
		logoBtn.addMouseListener(new HomeBtnEventHandler());

		signInBtn1.addMouseListener(new SignInEventHandler(phoneTextField, passwordField, userRepo));
		signInBtn2.addMouseListener(new SignInEventHandler(phoneTextField, passwordField, userRepo));

		logOffBtn1.addMouseListener(new SignOffEventHandler(phoneTextField, passwordField, userRepo));
		logOffBtn2.addMouseListener(new SignOffEventHandler(phoneTextField, passwordField, userRepo));
		
		signUpBtn1.addMouseListener(this);
		signUpBtn2.addMouseListener(this);
		
		menuDropDownBtn.addActionListener(new MenuPageBtnEventHandler());
		orderBtn.addActionListener(new OrderBtnEventHandler(userRepo));

		/* ���ο� ������ �̵� */
		logoBtn.addMouseListener(this);
		signUpBtn1.addMouseListener(this);
		signUpBtn2.addMouseListener(this);
		menuDropDownBtn.addMouseListener(this);
		orderBtn.addMouseListener(this);
		orderViewBtn.addMouseListener(this);
		myPageBtn.addMouseListener(this);
		
		/* window closing */
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowOpened(WindowEvent e) {
				
			}
			@Override
			public void windowClosing(WindowEvent e) {
				User user = null;
				if (userRepo.getPhone() != null) {
					user = (User)userRepo.getUserByPhone(userRepo.getPhone());
					if(user.getOrderCreated() == null) {
						user.setOrderList(null);
						user.setOrdering(false);
					}
					user.setLogged(false);
				}
				userRepo.storeToFile();
			}
		});

//		pack();
		setVisible(true);
		setResizable(false);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void signInBtnAddMouseListener(JButton signInBtn) {
		signInBtn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String phoneNum = phoneTextField.getSelectedText().replaceAll("\\s+", "");
			}
		});
	}

	private void createTopMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu menuFile = new JMenu("File");
		JMenuItem menuItemExit = new JMenuItem("Exit");
		
		menuFile.add(menuItemExit);
		
		menuBar.add(menuFile);
	}

	
	private JToolBar createNavBar() {
		JToolBar toolbar = new JToolBar();
		toolbar.setFloatable(false);
		
		//logo�޴���ư
		ImageIcon icon = new ImageIcon(getClass().getResource("../images/burger.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(100, 50, Image.SCALE_SMOOTH));

		logoBtn = new JButton(icon);
		logoBtn.setPreferredSize(new Dimension(100, 50));
		logoBtn.setName(INIT_PAGE);

		JPanel panel = new JPanel();
        panel.add(logoBtn); //add button to panel

        toolbar.add(panel);//add panel to toolbar
		toolbar.add(new JSeparator());

		//menu dropdown
		menuDropDownBtn = createDropDownButton();
		menuDropDownBtn.setPreferredSize(new Dimension(70, 50));
		menuDropDownBtn.setName(FOOD_MENU_PAGE);
		
		panel = new JPanel();
		panel.add(menuDropDownBtn);

		toolbar.add(panel);
		toolbar.add(new JSeparator());

		//order view
		icon = new ImageIcon(getClass().getResource("../images/orderView.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(65, 45, Image.SCALE_SMOOTH));
		orderViewBtn = new JButton(icon);
		orderViewBtn.setPreferredSize(new Dimension(100, 50));
		orderViewBtn.setName(ORDER_VIEW_PAGE);

		panel = new JPanel();
        panel.add(orderViewBtn); //add button to panel

		toolbar.add(panel);
		toolbar.add(new JSeparator());
		
        //mypage
		icon = new ImageIcon(getClass().getResource("../images/mypage.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		myPageBtn = new JButton(icon);
		myPageBtn.setPreferredSize(new Dimension(100, 50));
		myPageBtn.setName(MY_PAGE);

		panel = new JPanel();
        panel.add(myPageBtn); //add button to panel

		toolbar.add(panel);

		return toolbar;
	}

	private JButton createDropDownButton() {
		JPopupMenu popupMenu = createDropDownMenu();
		
		ImageIcon icon = new ImageIcon(getClass().getResource("../images/menu2.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		JButton menuDropDownBtn = DropDownButtonFactory.createDropDownButton(icon, popupMenu);
//		menuDropDownBtn.addActionListener(this);
		
		return menuDropDownBtn;
	}

	private JPopupMenu createDropDownMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

		List<Food> foodMenuList = userRepo.getFoodMenu().getFoodMenuList();
		Iterator<Food> itr = foodMenuList.iterator();
		Food food = null;
		while(itr.hasNext()) {
			food = itr.next();
			JMenuItem menuItem = new JMenuItem(food.toString());
			popupMenu.add(menuItem);
		}
		
		return popupMenu;
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		System.out.println(source);
		if (source instanceof JButton) {
			String name = ((JButton) e.getSource()).getName();
			switch(name) {
				case INIT_PAGE: createHomePage(); break;
				case FOOD_MENU_PAGE: createMenuPage(); break;
				case ORDER_VIEW_PAGE: createOrderViewPage(); break;
				case MY_PAGE: createMyPage(); break;
				case SIGN_UP_PAGE: createSignUpPage(); break;
				case "NOODLE": showNoodleMenu(); break;
				case "SOUP": showSoupMenu(); break;
				case "RICE": showRiceMenu(); break;
				case "ADD_FOOD": saveOrderList(name); break;
				case "CARD": case "CASH": completeOrder(name);break;
				default:
					break;
			}
		}
		else if(source instanceof JTable) {
			DefaultTableModel model = null;
			JTable table = (JTable)e.getSource();
			String name = table.getName();
			switch(name) {
				case "NOODLE_TABLE": model = (DefaultTableModel)menuNoodleTable.getModel(); break;
				case "SOUP_TABLE": model = (DefaultTableModel)menuSoupTable.getModel(); break;
				case "RICE_TABLE": model = (DefaultTableModel)menuRiceTable.getModel(); break;
				default:
					break;
			}

			int row = table.getSelectedRow();
			System.out.println(model.getValueAt(row, 1));
			System.out.println((String)model.getValueAt(row, 2));
			this.subMenuTxt.setText(model.getValueAt(row, 1).toString() + ". " + (String)model.getValueAt(row, 2).toString());
		}
	}
	public void completeOrder(String name) {
		User user = userRepo.getUserByPhone(this.phoneTextField.getText());
		if(user == null) return;

		Map<Food, Integer> orderList = user.getOrderList();

		User admin = userRepo.getUserByPhone("admin");

		Map<Food, Integer> salesResult = ((Admin)admin).getSalesResult();
		for(Map.Entry<Food, Integer> entry : salesResult.entrySet()) {
			salesResult.put(entry.getKey(), entry.getValue() + orderList.get(entry.getKey()));
//			map.put(key, map.get(key) + 1);
			
		}
		user.setRecentPayMethod(name);
		user.setOrdering(false);
		user.setOrderCreated(new GregorianCalendar());
		userRepo.showUsers();

	}

	public void saveOrderList(String name) {
		if(name.equals("ADD_FOOD")) {
			DefaultTableModel model = null;
			int row = 0;
			if(this.menuCategoryTxt.getText().equals(""))
				this.menuCategoryTxt.setText("�� �޴�");
			
			switch(this.menuCategoryTxt.getText().charAt(0)) {
				case '��':
					model = (DefaultTableModel)menuNoodleTable.getModel();
					row = menuNoodleTable.getSelectedRow();
					break;
				case '��': 
					model = (DefaultTableModel)menuSoupTable.getModel();
					row = menuSoupTable.getSelectedRow();
					break;
				case '��':
					model = (DefaultTableModel)menuRiceTable.getModel();
					row = menuRiceTable.getSelectedRow();
					break;
			}
			
			String menuCategory = (String)model.getValueAt(row, 0);
			int menuNo = Integer.valueOf((String)model.getValueAt(row, 1));
			String menuName=  (String)model.getValueAt(row, 2);
			int menuPrice = 0;
			String temp = ((String)model.getValueAt(row, 3)).substring(1);
			menuPrice = Integer.valueOf(temp.replace(",", ""));

			Food food = new Food(menuCategory, menuNo, menuName, menuPrice);
			User user = userRepo.getUserByPhone(this.phoneTextField.getText());
			
			if (user == null) {
				System.out.println("�α����� �ʿ��մϴ�.");
				return;
			}
			
			Map<Food, Integer> orderList = user.getOrderList();

			
			if(user.isOrdering())
				orderList.put(food, (int)this.menuQtyComboBox.getSelectedItem());
			else
				orderList = new TreeMap<Food, Integer>();

			user.setOrderList(orderList);
			user.setOrdering(true);
		}
	}

	private void createHomePage() {
		System.out.println("Ȩ�������� �̵��մϴ�.");
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, INIT_PAGE);
	}
	private void createMenuPage() {
		System.out.println("���� �޴��������� �̵��մϴ�."); 
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, FOOD_MENU_PAGE);
	}
	private void createOrderViewPage() {
		System.out.println("�ֹ� ��ȸ�������� �̵��մϴ�."); 
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, ORDER_VIEW_PAGE);
	}
	private void createMyPage() {
		System.out.println("������������ �̵��մϴ�."); 
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, MY_PAGE);
	}
	private void createSignUpPage() {
		System.out.println("ȸ������ �������� �̵��մϴ�."); 
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, SIGN_UP_PAGE);
	}


	/* �޴� */
	private void showNoodleMenu() {
		System.out.println("��޴��� �����ݴϴ�");
		CardLayout cl = (CardLayout)(menuCards.getLayout());
        cl.show(menuCards, "NOODLE");
        this.menuCategoryTxt.setText("�� �޴�");
	}

	private void showSoupMenu() {
		System.out.println("�� �޴��� �����ݴϴ�");
		CardLayout cl = (CardLayout)(menuCards.getLayout());
        cl.show(menuCards, "SOUP");
        this.menuCategoryTxt.setText("�� �޴�");
	}

	private void showRiceMenu() {
		System.out.println("�� �޴��� �����ݴϴ�.");
		CardLayout cl = (CardLayout)(menuCards.getLayout());
        cl.show(menuCards, "RICE");
        this.menuCategoryTxt.setText("�� �޴�");
	}

	
	public JTextField getPhoneTextField() { return phoneTextField; }
	public void setPhoneTextField(JTextField phoneTextField) { this.phoneTextField = phoneTextField; }
	public JButton getLogoBtn() { return logoBtn; } 
	public void setLogoBtn(JButton logoBtn) { this.logoBtn = logoBtn; } 
	public JButton getMenuDropDownBtn() { return menuDropDownBtn; } 
	public void setMenuDropDownBtn(JButton menuDropDownBtn) { this.menuDropDownBtn = menuDropDownBtn; } 
	public JButton getOrderViewBtn() { return orderViewBtn; } 
	public void setOrderViewBtn(JButton orderViewBtn) { this.orderViewBtn = orderViewBtn; } 
	public JButton getSignInBtn1() { return signInBtn1; } 
	public void setSignInBtn1(JButton signInBtn1) { this.signInBtn1 = signInBtn1; } 
	public JButton getSignInBtn2() { return signInBtn2; } 
	public void setSignInBtn2(JButton signInBtn2) { this.signInBtn2 = signInBtn2; } 
	public JButton getSignUpBtn1() { return signUpBtn1; } 
	public void setSignUpBtn1(JButton signUpBtn1) { this.signUpBtn1 = signUpBtn1; } 
	public JButton getSignUpBtn2() { return signUpBtn2; } 
	public void setSignUpBtn2(JButton signUpBtn2) { this.signUpBtn2 = signUpBtn2; } 
	public JButton getLogOffBtn1() { return logOffBtn1; } 
	public void setLogOffBtn1(JButton logOffBtn1) { this.logOffBtn1 = logOffBtn1; } 
	public JButton getLogOffBtn2() { return logOffBtn2; } 
	public void setLogOffBtn2(JButton logOffBtn2) { this.logOffBtn2 = logOffBtn2; } 
	public JButton getOrderBtn() { return orderBtn; } 
	public void setOrderBtn(JButton orderBtn) { this.orderBtn = orderBtn; }

}