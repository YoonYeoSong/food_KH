package com.kh.food.gui;

import javax.swing.*;

public class InitPageFrame extends JFrame{

	JButton signUpBtn = new JButton("ȸ������");
	JButton signInBtn = new JButton("�α���");
	JButton logOffBtn = new JButton("�α׾ƿ�");
	JButton orderBtn = new JButton("�ֹ��ϱ�");
	JButton orderViewBtn = new JButton("�ֹ���ȸ");
	JButton menuBtn = new JButton("�޴�");
	JButton myPageBtn = new JButton("����������");
	JButton adminPageBtn = new JButton("������");

	JTextField phone;

	public InitPageFrame() {
		// TODO Auto-generated constructor stub
		add(signUpBtn);
		add(signInBtn);
		add(logOffBtn);
	
		
		setVisible(true);
	}
	

}
