package foodapp.gui.layout;

import static foodapp.dao.Constants.ADMIN_PAGE;
import static foodapp.dao.Constants.FOOD_MENU_PAGE;
import static foodapp.dao.Constants.INIT_PAGE;
import static foodapp.dao.Constants.ORDER_VIEW_PAGE;
import static foodapp.dao.Constants.SIGN_UP_PAGE;
import static foodapp.dao.Constants.WINDOW_HEIGHT;
import static foodapp.dao.Constants.WINDOW_WIDTH;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.table.DefaultTableModel;

import foodapp.dao.UserRepository;
import foodapp.gui.event.SignInEventHandler;
import foodapp.gui.event.SignOffEventHandler;
import foodapp.model.vo.Admin;
import foodapp.model.vo.Food;
import foodapp.model.vo.FoodMenu;
import foodapp.model.vo.User;


public class InitPageFrame extends JFrame implements MouseListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JSplitPane splitPane1, splitPane2, splitPane3;
	private JSplitPane splitMenuCenterPane;

	private JPanel cards;
	private JPanel card1, card2, card3, card4;

	private JPanel menuCards;
	private JPanel noodleCard, soupCard, riceCard;
	
	private JPanel topPanel, bottomPanel, rightPanel, leftPanel, subPanel1, subPanel2, centerPanel;
	
	private JButton logoBtn, foodMenuBtn, adminPageBtn, orderViewBtn;
	private JButton signInBtn1, signInBtn2, signUpBtn1, signUpBtn2;
	private JButton logOffBtn1, logOffBtn2;
	private JButton orderBtn;
	
	private JLabel popularMenuLabel;
	private JTextArea popularMenuTextArea;
	private JPanel popularMenuPanel;

	private JPanel orderSelectionPanel;
	
	private JLabel menuCategoryLabel, menuChoiceLabel, menuQtyLabel;
	private JTextField menuCategoryTxt;
	private JTextField subMenuTxt;
	private JToggleButton payCardBtn, payCashBtn;
	private ButtonGroup payMethodBtnGrp;
	
	private JLabel addMenuLabel;
	private JButton addMenuBtn;
	
	private JPanel menuCategoryPanel, menuChoicePanel, menuQtyPanel, payMethodPanel, addMenuPanel;
	
	private JPanel orderViewLeftPanel;
	private JSplitPane centerReceipt;
	private JPanel receiptLabelPanel;
	private JLabel receiptLabel;
	private JTextArea receiptTextArea;
	private JScrollPane scrollTextArea;

	private JSplitPane orderViewSplitPane1;
	private JSplitPane orderViewSplitPane2;
	private JPanel orderViewRightPanel;

	private JSplitPane orderViewSplitPane3;
	private JPanel backButtonPanel;
	private JButton orderViewHomeBtn;

	private JSplitPane bottomSplitPane;

	private JButton noodleBtn, soupBtn, riceBtn;
	
	private DefaultTableModel modelN, modelS, modelR;
	
	private JComboBox<Integer> menuQtyComboBox;
	private JTextField phoneTextField;
	private JPasswordField passwordField;
	
	private JTable menuNoodleTable;
	private JTable menuSoupTable;
	private JTable menuRiceTable;
	private JScrollPane scrollNoodlePane;
	private JScrollPane scrollSoupPane;
	private JScrollPane scrollRicePane;
	
	private UserRepository userRepo = null;
	
	private Map<Food, Integer> tempOrderList;

	public InitPageFrame(UserRepository userRepo) throws Exception {
		this.userRepo = userRepo;
		initialize();
		setPopularMenuList();
	}

	private void initialize() throws Exception {

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
		signUpBtn1.setName(SIGN_UP_PAGE);

		orderBtn = new JButton("�ֹ� �ϱ�");
		orderBtn.setName("ORDER");


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
		
//		FoodMenu menu = userRepo.getFoodMenu();
//		List<Food> foodMenuList = null;
//		if(menu!= null)
//			foodMenuList = menu.getFoodMenuList();
//
//		if(foodMenuList != null) {
//			Collections.sort(foodMenuList, (i,j)->{
//				return i.getMenuCategory().compareTo(j.getMenuCategory()) == 0 ? 
//							i.getMenuNo() - j.getMenuNo(): i.getMenuCategory().compareTo(j.getMenuCategory());
//			});
//		}
//
//		Iterator<Food> itr = foodMenuList.iterator();
//		Food food = null;
//
//		String[] colNames = {"ī�װ�", "�޴���ȣ", "�޴��̸�", "����"};
//        modelN = new DefaultTableModel(colNames, 0);
//        modelS = new DefaultTableModel(colNames, 0);
//        modelR = new DefaultTableModel(colNames, 0);
//
//		String[][] tempNoodleList = new String[userRepo.getFoodMenu().getFoodMenuList().size()][colNames.length];
//		String[][] tempSoupList = new String[userRepo.getFoodMenu().getFoodMenuList().size()][colNames.length];
//		String[][] tempRiceList = new String[userRepo.getFoodMenu().getFoodMenuList().size()][colNames.length];
//
//		int countN =0, countS=0, countR=0;
//
//		while(itr.hasNext()) {
//			food = itr.next();
//
//			String[] temp = new String[] { food.getMenuCategory(), 
//					String.valueOf(food.getMenuNo()), 
//					food.getMenuName(), 
//					food.toCurrency(food.getMenuPrice()) };
//			switch(food.getMenuCategory()) {
//				case "NOODLE": tempNoodleList[countN++] = temp;break;
//				case "SOUP": tempSoupList[countS++] = temp; break;
//				case "RICE": tempRiceList[countR++] = temp; break;
//			}
//		}
//		
//		String[][] noodleList = new String[countN][colNames.length];
//		String[][] soupList = new String[countS][colNames.length];
//		String[][] riceList = new String[countR][colNames.length];
//		
//		
////		for(int i =0; i<noodleList.length; i++)
////			System.arraycopy(tempNoodleList[i], 0, noodleList[i], 0, noodleList.length);
////		
////		for(int i =0; i<soupList.length; i++)
////			System.arraycopy(tempSoupList[i], 0, soupList[i], 0, soupList.length);
////		ERROR!
////		for(int i =0; i<riceList.length; i++)
////			System.arraycopy(tempRiceList[i], 0, riceList[i], 0, riceList.length);
//		
//		for(int i =0; i<noodleList.length; i++) {
//			for(int j =0; j<noodleList[i].length; j++)
//				noodleList[i][j] = tempNoodleList[i][j];
//		}
//		for(int i =0; i<soupList.length; i++) {
//			for(int j =0; j<soupList[i].length; j++)
//				soupList[i][j] = tempSoupList[i][j];
//		}
//		for(int i =0; i<riceList.length; i++) {
//			for(int j =0; j<riceList[i].length; j++)
//				riceList[i][j] = tempRiceList[i][j];
//		}
//		
//		for(int i =0;  i<noodleList.length; i++)  modelN.addRow(noodleList[i]);
//		for(int i =0;  i<soupList.length; i++)  modelS.addRow(soupList[i]);
//		for(int i =0;  i<riceList.length; i++)  modelR.addRow(riceList[i]);
		Map<String, DefaultTableModel> tableModels = (TreeMap<String, DefaultTableModel>)constructTableModels();

		modelN = tableModels.get("NOODLE");
		modelS = tableModels.get("SOUP");
		modelR = tableModels.get("RICE");
		
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
		ComboBoxModel<Integer> comboBoxModel = new DefaultComboBoxModel<Integer>(new Integer[] {1,2,3,4,5});
		menuQtyComboBox = new JComboBox<Integer>(comboBoxModel);
		
		addMenuLabel = new JLabel("�޴� �߰�");
		addMenuBtn = new JButton("PUSH �߰�");
		addMenuBtn.setName("ADD_FOOD");

		
		payCardBtn = new JToggleButton("ī�� �ֹ�");
		payCardBtn.setName("CARD");
		payCardBtn.setBackground(Color.WHITE);
		payCashBtn = new JToggleButton("���� �ֹ�");
		payCashBtn.setName("CASH");
		payCashBtn.setBackground(Color.WHITE);

		payMethodBtnGrp = new ButtonGroup();
		payMethodBtnGrp.add(payCardBtn);
		payMethodBtnGrp.add(payCashBtn);
		
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
		splitMenuCenterPane.setDividerLocation(210 + splitMenuCenterPane.getInsets().top);
		splitMenuCenterPane.setDividerSize(1);
		centerPanel = new JPanel(new BorderLayout(0, 0));
		centerPanel.add(splitMenuCenterPane, BorderLayout.CENTER);

		popularMenuLabel = new JLabel("�ǽð� �α� �޴� TOP 5");
		popularMenuTextArea = new JTextArea(200, 300);
		popularMenuTextArea.setEditable(false);
		popularMenuPanel = new JPanel(new BorderLayout());
		popularMenuPanel.add(popularMenuLabel);
		popularMenuPanel.add(popularMenuTextArea);

		
		topPanel = new JPanel(new GridLayout(1,3));
		topPanel.add(leftPanel);
		topPanel.add(centerPanel);
		topPanel.add(rightPanel);
		bottomSplitPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		bottomSplitPane.setTopComponent(popularMenuPanel);
		bottomSplitPane.setBottomComponent(orderBtn);

		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(bottomSplitPane);
		splitPane1.setTopComponent(topPanel);
		splitPane1.setBottomComponent(bottomPanel);

		/* �׺���̼� �޴� */
		JToolBar navBar = this.createNavBar();

		splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

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
	

		orderViewLeftPanel = new JPanel();
		centerReceipt = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		receiptLabelPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		receiptLabel = new JLabel("�ֹ� ����");
		receiptLabelPanel.add(receiptLabel);
		receiptTextArea = new JTextArea(400, 500);
		receiptTextArea.setEditable(false);
		Font font = new Font("�������", Font.BOLD, 13);
        receiptTextArea.setFont(font);
        receiptTextArea.setForeground(Color.BLUE);

		scrollTextArea = new JScrollPane(receiptTextArea);
		centerReceipt.setTopComponent(receiptLabelPanel);
		centerReceipt.setBottomComponent(scrollTextArea);

		orderViewSplitPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		orderViewSplitPane1.setLeftComponent(orderViewLeftPanel);
		orderViewSplitPane1.setRightComponent(centerReceipt);

		orderViewSplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		orderViewRightPanel = new JPanel();
		orderViewSplitPane2.setLeftComponent(orderViewSplitPane1);
		orderViewSplitPane2.setRightComponent(orderViewRightPanel);
		
		orderViewSplitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		backButtonPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		orderViewHomeBtn = new JButton("�ڷ� ����");
		orderViewHomeBtn.setName(INIT_PAGE);
		orderViewHomeBtn.addMouseListener(this);
		backButtonPanel.add(orderViewHomeBtn);
		orderViewSplitPane3.setTopComponent(backButtonPanel);
		orderViewSplitPane3.setBottomComponent(orderViewSplitPane2);
		invokeSplitPane();


		CardLayout cl = new CardLayout();
		cards = new JPanel(cl);

		card1 = new JPanel(new BorderLayout());
		card2 = new JPanel(new BorderLayout());
		card3 = new JPanel(new BorderLayout());
		card4 = new JPanel(new BorderLayout());

		card1.add(splitPane3);
		card2.add(new FoodMenuPageFrame(cl, cards, userRepo));
		card3.add(orderViewSplitPane3);
		card4.add(new SignUpPageFrame(cl, cards, userRepo));

		cards.add(card1, INIT_PAGE);
		cards.add(card2, FOOD_MENU_PAGE);
		cards.add(card3, ORDER_VIEW_PAGE);
		cards.add(card4, SIGN_UP_PAGE);

//		getContentPane().add(cards);
		add(cards);
		
		/* �� component�� �̺�Ʈ �߰� */
		/* Ŭ���̺�Ʈ */
//		logoBtn.addMouseListener(new HomeBtnEventHandler());

		signInBtn1.addMouseListener(new SignInEventHandler(phoneTextField, passwordField, userRepo));
		signInBtn2.addMouseListener(new SignInEventHandler(phoneTextField, passwordField, userRepo));

		logOffBtn1.addMouseListener(new SignOffEventHandler(phoneTextField, passwordField, userRepo));
		logOffBtn2.addMouseListener(new SignOffEventHandler(phoneTextField, passwordField, userRepo));
		
		signUpBtn1.addMouseListener(this);
		signUpBtn2.addMouseListener(this);
		
//		menuDropDownBtn.addActionListener(new MenuPageBtnEventHandler());
		orderBtn.addMouseListener(this);

		/* ���ο� ������ �̵� */
		logoBtn.addMouseListener(this);
		signUpBtn1.addMouseListener(this);
		signUpBtn2.addMouseListener(this);
		foodMenuBtn.addMouseListener(this);
		orderViewBtn.addMouseListener(this);
		adminPageBtn.addMouseListener(this);
		
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
					if (user == null)
						return;
					if(user.isOrdering())
						user.setOrdering(false);

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

	private Map<String, DefaultTableModel> constructTableModels() {
		Map<String, DefaultTableModel> map = new TreeMap<String, DefaultTableModel>();
		
		FoodMenu menu = userRepo.getFoodMenu();
		List<Food> foodMenuList = null;
		Iterator<Food> itr = null;
		if(menu!= null)
			foodMenuList = menu.getFoodMenuList();

		if(foodMenuList != null) {
			Collections.sort(foodMenuList, (i,j)->{
				return i.getMenuCategory().compareTo(j.getMenuCategory()) == 0 ? 
							i.getMenuNo() - j.getMenuNo(): i.getMenuCategory().compareTo(j.getMenuCategory());
			});

			itr = foodMenuList.iterator();
		}

		Food food = null;
		String[] colNames = {"ī�װ�", "�޴���ȣ", "�޴��̸�", "����"};
        modelN = new DefaultTableModel(colNames, 0);
        modelS = new DefaultTableModel(colNames, 0);
        modelR = new DefaultTableModel(colNames, 0);

		String[][] tempNoodleList = new String[foodMenuList ==null? 0: foodMenuList.size()][colNames.length];
		String[][] tempSoupList = new String[foodMenuList ==null? 0: foodMenuList.size()][colNames.length];
		String[][] tempRiceList = new String[foodMenuList ==null? 0: foodMenuList.size()][colNames.length];

		int countN =0, countS=0, countR=0;

		while(itr!= null && itr.hasNext()) {
			food = itr.next();

			String[] temp = new String[] { food.getMenuCategory(), 
					String.valueOf(food.getMenuNo()), 
					food.getMenuName(), 
					food.toCurrency(food.getMenuPrice()) };
			switch(food.getMenuCategory()) {
				case "NOODLE": tempNoodleList[countN++] = temp;break;
				case "SOUP": tempSoupList[countS++] = temp; break;
				case "RICE": tempRiceList[countR++] = temp; break;
			}
		}
		
		String[][] noodleList = new String[countN][colNames.length];
		String[][] soupList = new String[countS][colNames.length];
		String[][] riceList = new String[countR][colNames.length];
		
		
//		for(int i =0; i<noodleList.length; i++)
//			System.arraycopy(tempNoodleList[i], 0, noodleList[i], 0, noodleList.length);
//		
//		for(int i =0; i<soupList.length; i++)
//			System.arraycopy(tempSoupList[i], 0, soupList[i], 0, soupList.length);
//		ERROR!
//		for(int i =0; i<riceList.length; i++)
//			System.arraycopy(tempRiceList[i], 0, riceList[i], 0, riceList.length);
		
		for(int i =0; i<noodleList.length; i++) {
			for(int j =0; j<noodleList[i].length; j++)
				noodleList[i][j] = tempNoodleList[i][j];
		}
		for(int i =0; i<soupList.length; i++) {
			for(int j =0; j<soupList[i].length; j++)
				soupList[i][j] = tempSoupList[i][j];
		}
		for(int i =0; i<riceList.length; i++) {
			for(int j =0; j<riceList[i].length; j++)
				riceList[i][j] = tempRiceList[i][j];
		}
		
		for(int i =0;  i<noodleList.length; i++)  modelN.addRow(noodleList[i]);
		for(int i =0;  i<soupList.length; i++)  modelS.addRow(soupList[i]);
		for(int i =0;  i<riceList.length; i++)  modelR.addRow(riceList[i]);
		
		map.put("NOODLE", modelN);
		map.put("SOUP", modelS);
		map.put("RICE", modelR);

		return map;
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
//		menuDropDownBtn = createDropDownButton();
		icon = new ImageIcon(getClass().getResource("../images/menu2.png"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		foodMenuBtn = new JButton(icon);
		
		foodMenuBtn.setPreferredSize(new Dimension(70, 50));
		foodMenuBtn.setName(FOOD_MENU_PAGE);
		
		panel = new JPanel();
		panel.add(foodMenuBtn);

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
		adminPageBtn = new JButton(icon);
		adminPageBtn.setPreferredSize(new Dimension(100, 50));
		adminPageBtn.setName(ADMIN_PAGE);

		panel = new JPanel();
        panel.add(adminPageBtn); //add button to panel

		toolbar.add(panel);

		return toolbar;
	}

	private void invokeSplitPane() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
				splitPane2.setDividerLocation(53 + splitPane2.getInsets().top);
				splitPane2.setDividerSize(1);

				bottomSplitPane.setDividerLocation(93+ bottomSplitPane.getInsets().top);
				bottomSplitPane.setEnabled(false);
				bottomSplitPane.setDividerSize(1);

                orderViewSplitPane1.setDividerLocation(200 + orderViewSplitPane1.getInsets().left);
                orderViewSplitPane2.setDividerLocation(675+orderViewSplitPane2.getInsets().left);
                orderViewSplitPane3.setDividerLocation(40+orderViewSplitPane3.getInsets().top);
                centerReceipt.setDividerLocation(30+centerReceipt.getInsets().top);

                orderViewSplitPane1.setEnabled(false);
                orderViewSplitPane1.setDividerSize(1);

                orderViewSplitPane2.setEnabled(false);
                orderViewSplitPane2.setDividerSize(1);

                orderViewSplitPane3.setEnabled(false);
                orderViewSplitPane3.setDividerSize(1);
                
                centerReceipt.setEnabled(false);
                centerReceipt.setDividerSize(1);
            }
        });
    }
	@Override
	public void mouseClicked(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}

	@Override
	public void mousePressed(MouseEvent e) {
		Object source = e.getSource();
		System.out.println(source);
		if (source instanceof JButton) {
			String name = ((JButton) e.getSource()).getName();
			switch(name) {
				case INIT_PAGE: createHomePage(); break;
				case FOOD_MENU_PAGE: createMenuPage(); break;
				case ORDER_VIEW_PAGE: createOrderViewPage(); break;
				case ADMIN_PAGE: createAdminPage(); break;
				case SIGN_UP_PAGE: createSignUpPage(); break;
				case "NOODLE": showNoodleMenu(); break;
				case "SOUP": showSoupMenu(); break;
				case "RICE": showRiceMenu(); break;
				case "ADD_FOOD": saveOrderList(name); break;
				case "CARD": 
					payCardBtn.setText("ī�� �ֹ� (���õ�)");
					payCashBtn.setText("���� �ֹ�");
					break;
				case "CASH": 
					payCashBtn.setText("���� �ֹ� (���õ�)");
					payCardBtn.setText("ī�� �ֹ�");
					break;
				case "ORDER": completeOrder(); break;
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

			Object o1 = model.getValueAt(row, 1);
			Object o2 = model.getValueAt(row, 2);
			if(o1 == null || o2 == null) return;
			this.subMenuTxt.setText(o1.toString() + ". " + o2.toString());
		}
	}

	public void completeOrder() {
		if(this.phoneTextField.getText() == "") {
			JOptionPane.showMessageDialog(null, "�α����� �ʿ��մϴ�.", "�α��� Ȯ��", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if(!payCardBtn.isSelected() && !payCashBtn.isSelected()) {
			System.out.println("��������� ������ �ּ���.");
			return;
		}

		int result = JOptionPane.showConfirmDialog(null, "�ֹ��� �Ϸ��Ͻðڽ��ϱ�?", "�ֹ� Ȯ��",
				JOptionPane.OK_CANCEL_OPTION);

		if(result!= JOptionPane.OK_OPTION) {
			System.out.println("�ֹ��� ����մϴ�.");
			return;
		}

		String payMethod = this.payCardBtn.isSelected()? "CARD" : "CASH";

		User user = userRepo.getUserByPhone(this.phoneTextField.getText());
		if(user == null)
			return;
		if(!user.isOrdering())
			return;

		Map<Food, Integer> orderList = tempOrderList;

		User admin = userRepo.getAdmin();

		Map<Food, Integer> salesResult = ((Admin)admin).getSalesResult();
		if (salesResult == null)
			((Admin) admin).setSalesResult(new TreeMap<Food, Integer>());

		Food food = null;
		int qty  =0;
		for(Map.Entry<Food, Integer> entry : orderList.entrySet()) {
			food = entry.getKey();
			qty = entry.getValue();

			for(Map.Entry<Food, Integer> e: salesResult.entrySet()) {
				System.out.println(e.getKey());
				System.out.println(e.getValue());
				System.out.println(food.equals(e.getKey()));
			}
			if(salesResult.get(food) != null) {
				System.out.println(food);
				System.out.println(salesResult.get(food));
				salesResult.put(food, salesResult.get(food) + qty);
				System.out.println(salesResult.get(food));
			}
			else
				salesResult.put(food, qty);
		}
		((Admin)admin).setSalesResult(salesResult);
		user.setOrderList(tempOrderList);
		user.setRecentPayMethod(payMethod);
		user.setOrdering(false);
		user.setOrderCreated(new GregorianCalendar());
		userRepo.showUsers();
		setPopularMenuList();

		JOptionPane.showMessageDialog(null, "�ֹ��� �Ϸ� �Ǿ����ϴ�.", "�ֹ����� �Ϸ� Ȯ��", JOptionPane.WARNING_MESSAGE);
	}

	public void saveOrderList(String name) {
		if(this.phoneTextField.getText() == ""
				|| (this.phoneTextField.isEditable()
						&& this.passwordField.isEditable())) {
			JOptionPane.showMessageDialog(null, "�α����� �ʿ��մϴ�.", "�α��� Ȯ��", JOptionPane.WARNING_MESSAGE);
			return;
		}

		User user = userRepo.getUserByPhone(this.phoneTextField.getText());

		if (user == null) {
			JOptionPane.showMessageDialog(null, "�ڵ������� ���� ����", "�α��� Ȯ��", JOptionPane.WARNING_MESSAGE);
			return;
		}

		if(!name.equals("ADD_FOOD")) {
			JOptionPane.showMessageDialog(null, "�߸��� �޴��Դϴ�", "���α׷� ���� Ȯ��", JOptionPane.WARNING_MESSAGE);
			return;
		}


		if(!user.isOrdering()) {
//			user.setOrderList(new TreeMap<Food, Integer>());
			this.tempOrderList = new TreeMap<Food, Integer>();
			user.setOrdering(true);
		}

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
		
		tempOrderList.put(food, (int)this.menuQtyComboBox.getSelectedItem());


		JOptionPane.showMessageDialog(null, "�ֹ��� �߰��Ǿ����ϴ�.", "�ֹ� �߰� Ȯ��", JOptionPane.WARNING_MESSAGE);
		return;
	}
	
	private void createAdminPage() {
		if(phoneTextField.getText() == null
				|| !phoneTextField.getText().equals("admin")) {
			JOptionPane.showMessageDialog(null, "�����ڷ� ���� �α��� ���ּ���.", "������ ���� ����", JOptionPane.WARNING_MESSAGE);
			return;
		}

		JFrame frame = new AdminPageFrame(modelN, modelS, modelR, userRepo);
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

			}
		});

		frame.setSize(500, 500);
		frame.setLocation(this.getX() + 50 , this.getY() + 90);
		frame.setLocationRelativeTo(null);

		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setVisible(true); 
	}

	private void createHomePage() {
		System.out.println("Ȩ�������� �̵��մϴ�.");
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, INIT_PAGE);
	}

	private void createMenuPage() {
		CardLayout cl = (CardLayout)(cards.getLayout());
		cards = new JPanel(cl);

		card1 = new JPanel(new BorderLayout());
		card2 = new JPanel(new BorderLayout());
		card3 = new JPanel(new BorderLayout());
		card4 = new JPanel(new BorderLayout());

		card1.add(splitPane3);
		card2.add(new FoodMenuPageFrame(cl, cards, userRepo));
		card3.add(orderViewSplitPane3);
		card4.add(new SignUpPageFrame(cl, cards, userRepo));

		cards.add(card1, INIT_PAGE);
		cards.add(card2, FOOD_MENU_PAGE);
		cards.add(card3, ORDER_VIEW_PAGE);
		cards.add(card4, SIGN_UP_PAGE);
		
		add(cards);

		System.out.println("���� �޴��������� �̵��մϴ�."); 
        cl.show(cards, FOOD_MENU_PAGE);
	}

	private void createOrderViewPage() {
		System.out.println("�ֹ� ��ȸ�������� �̵��մϴ�."); 
		CardLayout cl = (CardLayout)(cards.getLayout());

		if(this.phoneTextField.getText().equals("")) {
			cl.show(cards, ORDER_VIEW_PAGE);
			receiptTextArea.setText("");
			return;
		}
		User user = userRepo.getUserByPhone(this.phoneTextField.getText());
		if(user ==null) {
			JOptionPane.showMessageDialog(null, "�α����� �ʿ��մϴ�.", "�α��� Ȯ��", JOptionPane.WARNING_MESSAGE);
			return;
		}
		Map<Food, Integer> orderList = user.getOrderList();

		if(orderList == null || orderList.size() < 1) {
			cl.show(cards, ORDER_VIEW_PAGE);
			receiptTextArea.setText("");
			return;
		}
		
		Date temp = new Date(user.getOrderCreated().getTimeInMillis());
		SimpleDateFormat sdf = new SimpleDateFormat("yy�� MM�� dd��, HH�� mm�� ss��");
		String date = sdf.format(temp);
		String msg = "\t�ֹ� ��¥:  " + date + "\n\n";
		Food food = null;
		int qty = 0;
		int sum = 0;
		for(Map.Entry<Food, Integer> entry : orderList.entrySet()) {
			food = entry.getKey();
			qty = entry.getValue();
			msg += "\t" + food + " * " + qty + " ��.\n";
			sum += food.getMenuPrice() * qty;
		}
		msg += "\n\t �� �ֹ� ��: " + NumberFormat.getCurrencyInstance(Locale.KOREA).format(sum);

		receiptTextArea.setText(msg);

        cl.show(cards, ORDER_VIEW_PAGE);
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

	private void setPopularMenuList() {
		Map<Food, Integer> salesResult = (TreeMap<Food, Integer>)((Admin)userRepo.getAdmin()).getSalesResult();				
		if (salesResult == null)
			return;

		List<Map.Entry<Integer, Food>> sortedSales = new ArrayList<Map.Entry<Integer, Food>>();

		Map.Entry<Integer, Food> newEntry = null;
		for(Map.Entry<Food, Integer> entry : salesResult.entrySet()) {
			newEntry = new AbstractMap.SimpleEntry<Integer, Food>(entry.getValue(), entry.getKey());
			sortedSales.add(newEntry);
		}
		
		Collections.sort(sortedSales, (i,j)->{
			return j.getKey() - i.getKey();
		});


		int count = 0;
		int qty = 0;
		String msg = "    ��  �α� �޴�\n";
		Food food = null;
		Iterator<Map.Entry<Integer, Food>> itr = sortedSales.iterator();
		this.popularMenuTextArea.setText("");

		while(itr.hasNext() && count++ < 5) {
			newEntry = itr.next();
			food = newEntry.getValue();
			qty = newEntry.getKey();
			msg += "    " + count+ "�� �޴�:   " + food.toString() + " - - - �� " + qty + " �� �ȷȾ��.";
			if(count <5) msg+="\n";
		}
		popularMenuTextArea.setText(msg);
		Font font = new Font("�������", Font.BOLD, 11);
        popularMenuTextArea.setFont(font);
        popularMenuTextArea.setForeground(Color.BLUE);
	}

	public JTextField getPhoneTextField() { return phoneTextField; }
	public void setPhoneTextField(JTextField phoneTextField) { this.phoneTextField = phoneTextField; }
	public JButton getLogoBtn() { return logoBtn; } 
	public void setLogoBtn(JButton logoBtn) { this.logoBtn = logoBtn; } 
	public JButton getFoodMenuBtn() { return foodMenuBtn; } 
	public void setMenuDropDownBtn(JButton foodMenuBtn) { this.foodMenuBtn = foodMenuBtn; } 
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