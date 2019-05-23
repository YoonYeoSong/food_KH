package com.kh.food.model.vo;

public class MenuChoiceException extends Exception {
	private int wrongChoice;

	public MenuChoiceException(int wrongChoice) {
		super("�߸��� ��ȣ �����Դϴ�.");
		this.wrongChoice = wrongChoice;
	}

	public void showWrongChoice() {
		System.out.println(wrongChoice + "�� �ش��ϴ� ������ �������� �ʽ��ϴ�.");
	}

	public int getWrongChoice() { return wrongChoice; }
	public void setWrongChoice(int wrongChoice) { this.wrongChoice = wrongChoice; }
}
