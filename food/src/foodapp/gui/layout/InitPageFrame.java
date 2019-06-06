package foodapp.gui.layout;

import static foodapp.dao.Constants.ADD_FOOD;
import static foodapp.dao.Constants.ADMIN_PAGE;
import static foodapp.dao.Constants.CARD;
import static foodapp.dao.Constants.CASH;
import static foodapp.dao.Constants.FOOD_MENU_PAGE;
import static foodapp.dao.Constants.INIT_PAGE;
import static foodapp.dao.Constants.NOODLE;
import static foodapp.dao.Constants.NOODLE_TABLE;
import static foodapp.dao.Constants.ORDER;
import static foodapp.dao.Constants.ORDER_VIEW_PAGE;
import static foodapp.dao.Constants.RICE;
import static foodapp.dao.Constants.RICE_TABLE;
import static foodapp.dao.Constants.SIGN_UP_PAGE;
import static foodapp.dao.Constants.SOUP;
import static foodapp.dao.Constants.SOUP_TABLE;
import static foodapp.dao.Constants.WINDOW_HEIGHT;
import static foodapp.dao.Constants.WINDOW_WIDTH;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.BorderFactory;
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
import javax.swing.table.TableModel;

import foodapp.dao.UserRepository;
import foodapp.gui.event.SignInEventHandler;
import foodapp.gui.event.SignOffEventHandler;
import foodapp.model.vo.Admin;
import foodapp.model.vo.Food;
import foodapp.model.vo.FoodMenu;
import foodapp.model.vo.User;


public class InitPageFrame extends JFrame implements MouseListener {

	private static final long serialVersionUID = 1L;

	private JSplitPane mainSplitPane1, mainSplitPane2, mainSplitPane3;
	private JSplitPane subSplitPane1, subSplitPane2, subSplitPane3;

	private JPanel cards;
	private JPanel card1, card2, card3, card4;

	private JPanel menuCards;
	private JPanel noodleCard, soupCard, riceCard;
	
	private JPanel topPanel, bottomPanel, rightPanel, centerAndRightPanel, leftPanel;
	private JPanel subPanel1, subPanel2, centerPanel;
	private JPanel p1, p2, p3;
	private JTextArea orderListTextArea;
	private JScrollPane orderListScrollPane;
	
	private JButton foodMenuBtn, adminPageBtn, orderViewBtn;
	private JButton signInBtn1, signInBtn2, signUpBtn1, signUpBtn2;
	private JButton logOffBtn1, logOffBtn2;
	private JButton orderBtn;
	
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

	/* CONSTRUCTOR */
	public InitPageFrame(UserRepository userRepo) throws Exception {
		this.userRepo = userRepo;
		initialize();
		setPopularMenuList();
	}

	private void initialize() throws Exception {
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setLocationRelativeTo(null); //center window
		getContentPane().setLayout(new BorderLayout());

		//�ý��� �޴� File-exit ����
		createTopMenuBar();

		/* ù��° JSplitPane */
		createFirstTop();
		createFirstBottom();
		createFirstSP();

		/* �ι�° JSplitPane */
		createSecondTop();
		createSecondBottom();
		createSecondSP();

		/* ����° JSplitPane */
		createThirdTop();
		createThirdBottom();
		createThirdSP();

		/* ������ JSplitPane�� border ���� */
		invokeSplitPane();

		/* CardLayout ����� */
		updateCards();

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

		setVisible(true);
		setResizable(false);
		UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private void createFirstSP() {
		mainSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);

		mainSplitPane1.setTopComponent(topPanel);
		mainSplitPane1.setBottomComponent(bottomPanel);
	}

	private void createFirstTop() {
		createFirstTopLeft();
		createFirstTopCenter();
		createFirstTopRight();

		subSplitPane2 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		subSplitPane2.setDividerLocation(.2);
		subSplitPane2.setDividerSize(1);
		centerAndRightPanel = new JPanel(new GridLayout(1,2));
		subSplitPane2.setLeftComponent(leftPanel);
		centerAndRightPanel.add(centerPanel);
		centerAndRightPanel.add(rightPanel);
		subSplitPane2.setRightComponent(centerAndRightPanel);

		topPanel = new JPanel(new BorderLayout());
		topPanel.add(subSplitPane2);
	}
	
	private void createFirstTopLeft() {
		leftPanel = new JPanel(new GridLayout(3,1));
		ImageIcon icon = null;
		icon = new ImageIcon(getClass().getResource("../images/noodle.jpg"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(180, 92, Image.SCALE_SMOOTH));
		noodleBtn = new JButton(icon); noodleBtn.setName(NOODLE);
//		noodleBtn.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		noodleBtn.setMargin(new Insets(0, 0, 0, 0));
		noodleBtn.setBackground(Color.LIGHT_GRAY);
		leftPanel.add(noodleBtn);

		icon = new ImageIcon(getClass().getResource("../images/soup.jpg"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(180, 92, Image.SCALE_SMOOTH));
		soupBtn = new JButton(icon); soupBtn.setName(SOUP);
		soupBtn.setMargin(new Insets(0, 0, 0, 0));
		soupBtn.setBackground(Color.LIGHT_GRAY);
		leftPanel.add(soupBtn);

		icon = new ImageIcon(getClass().getResource("../images/rice.png"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(180, 92, Image.SCALE_SMOOTH));
		riceBtn = new JButton(icon); riceBtn.setName(RICE);
		riceBtn.setMargin(new Insets(0, 0, 0, 0));
		riceBtn.setBackground(Color.LIGHT_GRAY);
		leftPanel.add(riceBtn);
	}

	private void createFirstTopCenter() {
		CardLayout layout = new CardLayout();
		menuCards = new JPanel(layout);

		noodleCard = new JPanel(new BorderLayout());
		soupCard = new JPanel(new BorderLayout());
		riceCard = new JPanel(new BorderLayout());
		
		Map<String, DefaultTableModel> tableModels = constructTableModels();
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

		menuCards.add(noodleCard, NOODLE);
		menuCards.add(soupCard,SOUP);
		menuCards.add(riceCard, RICE);
		
		
		orderSelectionPanel = new JPanel(new GridLayout(5,1));

		menuCategoryLabel = new JLabel("�޴� ī�װ�");
		menuCategoryTxt = new JTextField("", 10);
		menuCategoryTxt.setEditable(false);
		int row = 0;
		Object obj = modelN.getValueAt(row, 0);
		if(obj != null) {
			switch(obj.toString()) {
				case NOODLE: obj = "�� �޴�"; break;
				case SOUP: obj = "�� �޴�"; break;
				case RICE: obj = "�� �޴�"; break;
			}
			this.menuCategoryTxt.setText(obj.toString());
		}
		
		menuChoiceLabel = new JLabel("���� �޴�");
		subMenuTxt = new JTextField(10);
		subMenuTxt.setEditable(false);



		menuQtyLabel = new JLabel("�� ��");
		ComboBoxModel<Integer> comboBoxModel = 
				new DefaultComboBoxModel<Integer>(new Integer[] {1,2,3,4,5});
		menuQtyComboBox = new JComboBox<Integer>(comboBoxModel);
		
		addMenuLabel = new JLabel("�޴� �߰�");
		addMenuBtn = new JButton("PUSH �߰�");
		addMenuBtn.setName(ADD_FOOD);

		payCardBtn = new JToggleButton("ī�� �ֹ�");
		payCardBtn.setName(CARD);
		payCardBtn.setBackground(Color.WHITE);
		payCashBtn = new JToggleButton("���� �ֹ�");
		payCashBtn.setName(CASH);
		payCashBtn.setBackground(Color.WHITE);

		payMethodBtnGrp = new ButtonGroup();
		payMethodBtnGrp.add(payCardBtn);
		payMethodBtnGrp.add(payCashBtn);
		

		menuCategoryPanel = new JPanel(new GridLayout(1,2));
		menuChoicePanel = new JPanel(new GridLayout(1,2));
		menuQtyPanel = new JPanel(new GridLayout(1,2));
		payMethodPanel = new JPanel(new GridLayout(1,2));
		addMenuPanel = new JPanel(new GridLayout(1,2));
		
		JPanel l1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel l2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel l3 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JPanel l4 = new JPanel(new FlowLayout(FlowLayout.RIGHT));

		l1.add(menuCategoryLabel);
		l2.add(menuChoiceLabel);
		l3.add(menuQtyLabel);
		l4.add(addMenuLabel);

		menuCategoryPanel.add(l1); menuCategoryPanel.add(menuCategoryTxt);
		menuChoicePanel.add(l2); menuChoicePanel.add(subMenuTxt);
		menuQtyPanel.add(l3); menuQtyPanel.add(menuQtyComboBox);
		addMenuPanel.add(l4); addMenuPanel.add(addMenuBtn);
		payMethodPanel.add(payCardBtn); payMethodPanel.add(payCashBtn);
		
		orderSelectionPanel.add(menuCategoryPanel);
		orderSelectionPanel.add(menuChoicePanel);
		orderSelectionPanel.add(menuQtyPanel);
		orderSelectionPanel.add(addMenuPanel);
		orderSelectionPanel.add(payMethodPanel);
		
		subSplitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		subSplitPane1.setTopComponent(menuCards);
		subSplitPane1.setBottomComponent(orderSelectionPanel);

		centerPanel = new JPanel(new BorderLayout());
		centerPanel.add(subSplitPane1, BorderLayout.CENTER);


		noodleBtn.addMouseListener(this);
		soupBtn.addMouseListener(this);
		riceBtn.addMouseListener(this);
		
		addMenuBtn.addMouseListener(this);

		payCardBtn.addMouseListener(this);
		payCashBtn.addMouseListener(this);
	}

	private void createFirstTopRight() {
		//��ư, �ؽ�Ʈ �ʵ� �ʱ�ȭ
		phoneTextField = new JTextField("", 11); //�ڵ��� 11�ڸ�
		passwordField = new JPasswordField(11);
		signInBtn1 = new JButton("�α���");
		logOffBtn1 = new JButton("�α׾ƿ�");
		signUpBtn1 = new JButton("ȸ������");
		signUpBtn1.setName(SIGN_UP_PAGE);

		rightPanel = new JPanel(new GridLayout(2,1));
		subPanel1 = new JPanel(new GridLayout(2,1)); 
		p1 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		p3 = new JPanel(new GridLayout(2,1));
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
		p3.add(subPanel1);
		p3.add(subPanel2);
		orderListTextArea = new JTextArea("", 200, 200);
		orderListTextArea.setEditable(false);
        orderListTextArea.setFont(new Font("�������", Font.BOLD, 11));
        orderListTextArea.setForeground(Color.DARK_GRAY);
		orderListScrollPane = new JScrollPane(orderListTextArea, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, 
				JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		rightPanel.add(p3);
		rightPanel.add(orderListScrollPane);

		signInBtn1.addMouseListener(new SignInEventHandler(phoneTextField, passwordField, userRepo));
		logOffBtn1.addMouseListener(new SignOffEventHandler(phoneTextField, passwordField, userRepo));
		signUpBtn1.addMouseListener(this);
	}

	private void createFirstBottom() {
		popularMenuTextArea = new JTextArea(200, 300);
		popularMenuTextArea.setEditable(false);
		popularMenuPanel = new JPanel(new BorderLayout());
		popularMenuPanel.add(popularMenuTextArea);

		orderBtn = new JButton("�ֹ� �ϱ�");
		orderBtn.setName(ORDER);
		
		subSplitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		subSplitPane3.setTopComponent(popularMenuPanel);
		subSplitPane3.setBottomComponent(orderBtn);

		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(subSplitPane3);

		orderBtn.addMouseListener(this);
	}
	
	private void createSecondSP() {
		mainSplitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		mainSplitPane2.setTopComponent(topPanel);
		mainSplitPane2.setBottomComponent(bottomPanel);
	}
	
	private void createSecondTop() {
		/* �׺���̼� �޴� */
		JToolBar navBar = this.createNavBar();
		navBar.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));

		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(navBar);

		foodMenuBtn.addMouseListener(this);
		orderViewBtn.addMouseListener(this);
		adminPageBtn.addMouseListener(this);
	}

	private void createSecondBottom() {
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(mainSplitPane1);
	}
	
	private void createThirdSP() {
		mainSplitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		mainSplitPane3.setTopComponent(topPanel);
		mainSplitPane3.setBottomComponent(bottomPanel);
	}

	private void createThirdTop() {
		signInBtn2 = new JButton("�α���");
		logOffBtn2 = new JButton("�α׾ƿ�");
		signUpBtn2 = new JButton("ȸ������");
		signUpBtn2.setName(SIGN_UP_PAGE);

		topPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		topPanel.add(signInBtn2);
		topPanel.add(signUpBtn2);
		topPanel.add(logOffBtn2);

		signInBtn2.addMouseListener(new SignInEventHandler(phoneTextField, passwordField, userRepo));
		logOffBtn2.addMouseListener(new SignOffEventHandler(phoneTextField, passwordField, userRepo));
		signUpBtn2.addMouseListener(this);
	}

	private void createThirdBottom() {
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(mainSplitPane2);
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
		
		ImageIcon icon = null;

		//food menu btn
		icon = new ImageIcon(getClass().getResource("../images/menu.png"));
		icon = new ImageIcon(icon.getImage().getScaledInstance(43, 43, Image.SCALE_SMOOTH));
		foodMenuBtn = new JButton(icon);
//		foodMenuBtn.setPreferredSize(new Dimension(75, 40));
		foodMenuBtn.setName(FOOD_MENU_PAGE);
		
		JPanel panel = new JPanel();
		panel.add(foodMenuBtn);

		toolbar.add(panel);
		toolbar.add(new JSeparator());

		//order view
		icon = new ImageIcon(getClass().getResource("../images/orderView.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(43, 43, Image.SCALE_SMOOTH));
		orderViewBtn = new JButton(icon);
		orderViewBtn.setName(ORDER_VIEW_PAGE);

		panel = new JPanel();
        panel.add(orderViewBtn); //add button to panel

		toolbar.add(panel);
		toolbar.add(new JSeparator());
		
        //admin page
		icon = new ImageIcon(getClass().getResource("../images/admin.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(43, 43, Image.SCALE_SMOOTH));
		adminPageBtn = new JButton(icon);
		adminPageBtn.setName(ADMIN_PAGE);

		panel = new JPanel();
        panel.add(adminPageBtn); //add button to panel

		toolbar.add(panel);

		return toolbar;
	}
	
	private TableModel createDefaultTableModel() {
		String[] colNames = {"ī�װ�", "�޴���ȣ", "�޴��̸�", "����"};

        return new DefaultTableModel(colNames, 0) {
			private static final long serialVersionUID = 1L;
			String[] colNames = {"ī�װ�", "�޴���ȣ", "�޴��̸�", "����"};
			@Override
			public String getColumnName(int column) {
				return colNames[column];
			}
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
        };
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
				return i.compareTo(j);
			});

			itr = foodMenuList.iterator();
		}

		Food food = null;
		String[] colNames = {"ī�װ�", "�޴���ȣ", "�޴��̸�", "����"};
        modelN = (DefaultTableModel)createDefaultTableModel();
        modelS = (DefaultTableModel)createDefaultTableModel();
        modelR = (DefaultTableModel)createDefaultTableModel();

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
				case NOODLE: tempNoodleList[countN++] = temp;break;
				case SOUP: tempSoupList[countS++] = temp; break;
				case RICE: tempRiceList[countR++] = temp; break;
			}
		}
		
		String[][] noodleList = new String[countN][colNames.length];
		String[][] soupList = new String[countS][colNames.length];
		String[][] riceList = new String[countR][colNames.length];
		
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
		
		map.put(NOODLE, modelN);
		map.put(SOUP, modelS);
		map.put(RICE, modelR);

		return map;
	}

	private void invokeSplitPane() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
				mainSplitPane1.setDividerLocation(300 + mainSplitPane1.getInsets().top);
//				mainSplitPane1.setDividerLocation(mainSplitPane1.getSize().height/2);
				mainSplitPane1.setDividerSize(1);
//				mainSplitPane1.setEnabled(false);

				mainSplitPane2.setDividerLocation(53 + mainSplitPane2.getInsets().top);
//				mainSplitPane1.setDividerLocation(mainSplitPane2.getSize().height/2);
				mainSplitPane2.setDividerSize(1);
//				mainSplitPane2.setEnabled(false);

				mainSplitPane3.setDividerLocation(30 + mainSplitPane3.getInsets().top);
//				mainSplitPane1.setDividerLocation(mainSplitPane3.getSize().height/2);
				mainSplitPane3.setDividerSize(1);
//				mainSplitPane3.setEnabled(false);

				subSplitPane1.setDividerLocation(150 + subSplitPane1.getInsets().top);
//				mainSplitPane1.setDividerLocation(subSplitPane1.getSize().height/2);
				subSplitPane1.setDividerSize(1);
//				subSplitPane1.setEnabled(false);

				subSplitPane3.setDividerLocation(110+ subSplitPane3.getInsets().top);
//				mainSplitPane1.setDividerLocation(subSplitPane3.getSize().height/2);
				subSplitPane3.setDividerSize(1);
//				subSplitPane3.setEnabled(false);
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
		if (source instanceof JButton) {
			String name = ((JButton) e.getSource()).getName();
			switch(name) {
				case INIT_PAGE: createHomePage(); break;
				case FOOD_MENU_PAGE: createFoodMenuPage(); break;
				case ORDER_VIEW_PAGE: createOrderViewPage(); break;
				case ADMIN_PAGE: createAdminPage(); break;
				case SIGN_UP_PAGE: createSignUpPage(); break;
				case NOODLE: showNoodleMenu(); break;
				case SOUP: showSoupMenu(); break;
				case RICE: showRiceMenu(); break;
				case ADD_FOOD: saveOrderList(name); break;
				case ORDER: completeOrder(); break;
				default:
					break;
			}
		}
		else if(source instanceof JTable) {
			DefaultTableModel model = null;
			JTable table = (JTable)e.getSource();
			String name = table.getName();
			switch(name) {
				case NOODLE_TABLE: model = (DefaultTableModel)menuNoodleTable.getModel(); break;
				case SOUP_TABLE: model = (DefaultTableModel)menuSoupTable.getModel(); break;
				case RICE_TABLE: model = (DefaultTableModel)menuRiceTable.getModel(); break;
				default:
					break;
			}

			int row = table.getSelectedRow();

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
			JOptionPane.showMessageDialog(null, "���������� ������ �ּ���.", "���� ���� ����", JOptionPane.WARNING_MESSAGE);
			return;
		}

		int result = JOptionPane.showConfirmDialog(null, "�ֹ��� �Ϸ��Ͻðڽ��ϱ�?", "�ֹ� Ȯ��",
				JOptionPane.OK_CANCEL_OPTION);

		if(result!= JOptionPane.OK_OPTION) {
			System.out.println("�ֹ��� ����մϴ�.");

			User user = userRepo.getUserByPhone(this.phoneTextField.getText());
			user.setOrdering(false);

			return;
		}

		String payMethod = this.payCardBtn.isSelected()? CARD : CASH;

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
		orderListTextArea.setText("");
		

		JOptionPane.showMessageDialog(null, "�ֹ��� �Ϸ� �Ǿ����ϴ�.", "�ֹ����� �Ϸ� Ȯ��", JOptionPane.WARNING_MESSAGE);
	}

	public void saveOrderList(String name) {
		if(this.phoneTextField.getText() == ""
				|| (this.phoneTextField.isEditable()
						&& this.passwordField.isEditable())) {
			JOptionPane.showMessageDialog(null, "�α����� �ʿ��մϴ�.", "�α��� Ȯ��", JOptionPane.WARNING_MESSAGE);
			this.orderListTextArea.setText("");
			return;
		}

		User user = userRepo.getUserByPhone(this.phoneTextField.getText());

		if (user == null) {
			JOptionPane.showMessageDialog(null, "�ڵ������� ���� ����", "�α��� Ȯ��", JOptionPane.WARNING_MESSAGE);
			this.orderListTextArea.setText("");
			return;
		}

		if(!name.equals(ADD_FOOD)) {
			JOptionPane.showMessageDialog(null, "�߸��� �޴��Դϴ�", "���α׷� ���� Ȯ��", JOptionPane.WARNING_MESSAGE);
			this.orderListTextArea.setText("");
			return;
		}


		if(!user.isOrdering()) {
			this.tempOrderList = new TreeMap<Food, Integer>();
			user.setOrdering(true);
		}

		TableModel model = null;
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
		
		displayOrderList();

		JOptionPane.showMessageDialog(null, "�ֹ��� �߰��Ǿ����ϴ�.", "�ֹ� �߰� Ȯ��", JOptionPane.WARNING_MESSAGE);
		return;
	}

	private void displayOrderList() {
		String msg = "";
		for(Map.Entry<Food, Integer> entry : tempOrderList.entrySet())
			msg +=  entry.getKey() + "  :  " +  entry.getValue() + "�� �ֹ�.\n";

		this.orderListTextArea.setText(msg);
	}

	private void showNoodleMenu() {
		menuNoodleTable.getSelectionModel().clearSelection();
		this.subMenuTxt.setText("");
		CardLayout cl = (CardLayout)(menuCards.getLayout());
        cl.show(menuCards, NOODLE);
        this.menuCategoryTxt.setText("�� �޴�");
	}

	private void showSoupMenu() {
		menuSoupTable.getSelectionModel().clearSelection();
		this.subMenuTxt.setText("");
		CardLayout cl = (CardLayout)(menuCards.getLayout());
        cl.show(menuCards, SOUP);
        this.menuCategoryTxt.setText("�� �޴�");
	}

	private void showRiceMenu() {
		menuRiceTable.getSelectionModel().clearSelection();
		this.subMenuTxt.setText("");
		CardLayout cl = (CardLayout)(menuCards.getLayout());
        cl.show(menuCards, RICE);
        this.menuCategoryTxt.setText("�� �޴�");
	}

	private void createAdminPage() {
		if(phoneTextField.getText() == null
				|| !phoneTextField.getText().equals("admin")) {
			JOptionPane.showMessageDialog(null, "�����ڷ� ���� �α��� ���ּ���.", "������ ���� ����", JOptionPane.WARNING_MESSAGE);
			return;
		}

		JFrame frame = new AdminPageCard(modelN, modelS, modelR, popularMenuTextArea, userRepo);
		
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
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, INIT_PAGE);
	}

	private void updateCards() {
		CardLayout cl = null;
		if(cards == null
				|| !(cards.getLayout() instanceof CardLayout)) 
			cl = new CardLayout();
		else
			cl = (CardLayout)cards.getLayout();


		cards = new JPanel(cl);

		card1 = new JPanel(new BorderLayout());
		card2 = new JPanel(new BorderLayout());
		card3 = new JPanel(new BorderLayout());
		card4 = new JPanel(new BorderLayout());

		card1.add(mainSplitPane3);
		card2.add(new FoodMenuPageCard(cl, cards, userRepo));
		card3.add(new OrderViewPageCard(cl, cards, phoneTextField, userRepo));
		card4.add(new SignUpPageCard(cl, cards, userRepo));

		cards.add(card1, INIT_PAGE);
		cards.add(card2, FOOD_MENU_PAGE);
		cards.add(card3, ORDER_VIEW_PAGE);
		cards.add(card4, SIGN_UP_PAGE);

		add(cards);

		cards.revalidate();
	}

	private void createFoodMenuPage() {
		CardLayout cl = (CardLayout)(cards.getLayout());
		updateCards();

        cl.show(cards, FOOD_MENU_PAGE);
	}

	private void createOrderViewPage() {
		CardLayout cl = (CardLayout)(cards.getLayout());
		updateCards();
        cl.show(cards, ORDER_VIEW_PAGE);
	}
	
	private void createSignUpPage() {
		CardLayout cl = (CardLayout)(cards.getLayout());
        cl.show(cards, SIGN_UP_PAGE);
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

}