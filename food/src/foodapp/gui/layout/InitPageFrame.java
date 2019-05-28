package foodapp.gui.layout;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JSeparator;
import javax.swing.JSplitPane;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import org.openide.awt.DropDownButtonFactory;


public class InitPageFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private static final int WIDTH = 900;
	private static final int HEIGHT = 900;
	
	private JPanel topPanel, bottomPanel, rightPanel, leftPanel, subPanel1, subPanel2;
	
	private JButton logoBtn, menuDropDownBtn, myPageBtn, orderViewBtn;
	private JButton signInBtn1, signInBtn2, signUpBtn1, signUpBtn2;
	private JButton logOffBtn1, logOffBtn2;
	private JButton orderBtn;
	
	private JTextField phoneTextField;

	public InitPageFrame(String title) throws Exception {
		super(title);
		setSize(WIDTH, HEIGHT);
		setLocationRelativeTo(null); //center window
		setLayout(new BorderLayout());
		
		/* ��� �޴� �� */
		this.createTopMenuBar();

		/* ȭ�� split */
		splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane1.setDividerLocation(300 + splitPane1.getInsets().top);

		phoneTextField = new JTextField("", 11); //�ڵ��� 11�ڸ�
		signInBtn1 = new JButton("�α���");
		signUpBtn1 = new JButton("ȸ������");
		logOffBtn1 = new JButton("�α׾ƿ�");
		orderBtn = new JButton("�ֹ��ϱ�");

		leftPanel = new JPanel();
		rightPanel = new JPanel(new GridLayout(4,1));
		subPanel1 = new JPanel(new FlowLayout(FlowLayout.RIGHT)); 
		subPanel1.add(new JLabel("�ڵ��� ��ȣ"));
		subPanel1.add(phoneTextField);
		subPanel2 = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		subPanel2.add(signInBtn1);
		subPanel2.add(signUpBtn1);
		subPanel2.add(logOffBtn1);
		rightPanel.add(new JLabel());
		rightPanel.add(subPanel1);
		rightPanel.add(subPanel2);

		topPanel = new JPanel(new GridLayout(1,2));
		topPanel.add(leftPanel);
		topPanel.add(rightPanel);
		bottomPanel = new JPanel(new GridLayout(2,1));
		bottomPanel.add(new JPanel());
		bottomPanel.add(orderBtn);
		splitPane1.setTopComponent(topPanel);
		splitPane1.setBottomComponent(bottomPanel);
		splitPane1.setEnabled(false);

		/* �׺���̼� �޴� */
		JToolBar navBar = this.createNavBar();

		splitPane2 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane2.setDividerLocation(70 + splitPane2.getInsets().top);

		topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		topPanel.add(navBar);
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(splitPane1);
		splitPane2.setTopComponent(topPanel);
		splitPane2.setBottomComponent(bottomPanel);
		splitPane2.setEnabled(false);

		/* ���� �г� */
		JPanel topMostPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		signInBtn2 = new JButton("�α���");
		signUpBtn2 = new JButton("ȸ������");
		logOffBtn2 = new JButton("�α׾ƿ�");
		topMostPanel.add(signInBtn2);
		topMostPanel.add(signUpBtn2);
		topMostPanel.add(logOffBtn2);
		splitPane3 = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		splitPane3.setDividerLocation(30 + splitPane3.getInsets().top);

		topPanel = topMostPanel;
		bottomPanel = new JPanel(new BorderLayout());
		bottomPanel.add(splitPane2);
		splitPane3.setTopComponent(topPanel);
		splitPane3.setBottomComponent(bottomPanel);
		splitPane3.setEnabled(false);

		add(splitPane3);
		
		
		/* �� component�� �̺�Ʈ �߰� */
//		signInBtn1.addActionListener(new SignInEventHandler(this.phoneTextField));
//		signInBtn2.addActionListener(new SignInEventHandler(this.phoneTextField));

//		p1.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				String name=((JPanel)e.getSource()).getName();
//				if(Integer.parseInt(name)==1) {
//					card.next(p2.getParent());
//					flag=false;
//				}
//			}
//		});

		setVisible(true);
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
		ImageIcon icon = new ImageIcon(getClass().getResource("images/burger.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(100, 50, Image.SCALE_SMOOTH));

		logoBtn = new JButton(icon);
		logoBtn.setPreferredSize(new Dimension(100, 50));

		JPanel panel = new JPanel();
        panel.add(logoBtn); //add button to panel

        toolbar.add(panel);//add panel to toolbar
		toolbar.add(new JSeparator());

		//menu dropdown
		menuDropDownBtn = createDropDownButton();
		menuDropDownBtn.setPreferredSize(new Dimension(70, 50));
		
		panel = new JPanel();
		panel.add(menuDropDownBtn);

		toolbar.add(panel);
		toolbar.add(new JSeparator());

		//order view
		icon = new ImageIcon(getClass().getResource("images/orderView.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(65, 45, Image.SCALE_SMOOTH));
		orderViewBtn = new JButton(icon);
		orderViewBtn.setPreferredSize(new Dimension(100, 50));

		panel = new JPanel();
        panel.add(orderViewBtn); //add button to panel

		toolbar.add(panel);
		toolbar.add(new JSeparator());
		
        //mypage
		icon = new ImageIcon(getClass().getResource("images/mypage.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		myPageBtn = new JButton(icon);
		myPageBtn.setPreferredSize(new Dimension(100, 50));

		panel = new JPanel();
        panel.add(myPageBtn); //add button to panel

		toolbar.add(panel);

//		setLayout(new FlowLayout(FlowLayout.LEFT));
//		add(toolbar);

		return toolbar;
	}

	private JButton createDropDownButton() {
		JPopupMenu popupMenu = createDropDownMenu();
		
		ImageIcon icon = new ImageIcon(getClass().getResource("images/menu2.png"));
		icon = new ImageIcon(icon.getImage()
								.getScaledInstance(40, 40, Image.SCALE_SMOOTH));
		
		JButton menuDropDownBtn = DropDownButtonFactory.createDropDownButton(icon, popupMenu);
		menuDropDownBtn.addActionListener(this);
		
		return menuDropDownBtn;
	}

	private JPopupMenu createDropDownMenu() {
		JPopupMenu popupMenu = new JPopupMenu();

//		List<Food> foodMenu = userRepo.getFoodMenu();
//		Iterator<Food> itr = foodMenu.iterator();
//		Food food = null;
//		while(itr.hasNext()) {
//			food = itr.next();
//			JMenuItem menuItem = new JMenuItem(food.toString());
//			popupMenu.add(menuItem);
//			menuItem.addActionListener(this);
//		}
		
		return popupMenu;
	}

	@Override
	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
//		System.out.println(source);
		if (source instanceof JMenuItem) {
			JMenuItem clickedMenuItem = (JMenuItem) source;
			String menuText = clickedMenuItem.getText();
			System.out.println(menuText+ "�� �߰��մϴ�.");
		} else if (source instanceof JButton) {
			System.out.println("�޴��� �����մϴ�.");
		}
	}

	public JTextField getPhoneTextField() { return phoneTextField; }
	public void setPhoneTextField(JTextField phoneTextField) { this.phoneTextField = phoneTextField; }
	private JSplitPane splitPane1, splitPane2, splitPane3;
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
	
	
	public static void main(String[] args) throws Exception {
		new InitPageFrame("food");
	}
}