package foodapp.dao;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.WriteAbortedException;
import java.math.BigInteger;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

import foodapp.model.vo.Admin;
import foodapp.model.vo.Food;
import foodapp.model.vo.FoodMenu;
import foodapp.model.vo.User;

public class UserRepository {

	private final File dataFile=new File("Users.dat"); //List<User> ����� ����
 
	private List<User> users = new ArrayList<User>(); //User ����Ʈ

	private FoodMenu foodMenu; //���ð����� ���ĸ޴�

	private String phone; //�α��� ���� ����ȣ (User��ü�� 1:1����)

	private final static int SEATS = 10; //�¼� ��

	private boolean[] reservations = new boolean[SEATS]; //�¼� ��������
	
	public UserRepository() {

		this.readFromFile();
		if(users.size() < 1) {
			User admin = new Admin("admin", "1234", "admin", "0", "0", false, 
					null, null, "0", 0, false, new TreeMap<Food, Integer>());
			signUp(admin, admin.getPassword());
		}
		this.loadDefaultFoodMenu();
	}

	public void readFromFile() {
		if(dataFile.exists()==false)
			return;

		try (FileInputStream file=new FileInputStream(dataFile);
			ObjectInputStream in=new ObjectInputStream(file);){
			
			this.users = (ArrayList<User>)in.readObject();
			this.foodMenu = (FoodMenu)in.readObject();

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
	
	public boolean signUp(User user, String originalPassword) {
		if(this.phone != null) {
			System.out.println("ȸ������ �Ϸ��� ���� �α׾ƿ� ���ּ���.");
			return false;
		}
		if(user == null) {
			System.out.println("����� �����Ͱ� �����ϴ�.");
		}
		else {
			try {
				String strongPassword = this.generateStorngPasswordHash(originalPassword);
				user.setPassword(strongPassword);
			} catch(InvalidKeySpecException e) {
				e.printStackTrace();
			} catch(NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
		}

		if(isDuplicate(user)) {
			System.out.println("�̹� �����ϴ� ȸ�� �Դϴ�.");
			return false;
		}
		else {
			users.add(user);
			System.out.println("ȸ�������� ���������� ó���Ǿ����ϴ�.");

			Collections.sort(this.users, (i,j)->{
				return i.getUsername().compareTo(j.getUsername());
			});
			return true;
		}

	}
	
	public User signIn(String phoneTextField, String passwordField) {
		if(phoneTextField.equals(this.phone)) {
			System.out.println("�̹� �α��� �Ǿ� �ֽ��ϴ�.");
			return null;
		}

		User user = getUserByPhoneAndPassword(phoneTextField, passwordField);

		if(user==null) {
			System.out.println("�α��ο� ���� �Ͽ����ϴ�.");
			return null;
		}
		else {
			user.setLogged(true);
			setPhone(phoneTextField);
			System.out.println("�α��� �ƽ��ϴ�.");
			return user;
		}
	}

	public void logOff(String phoneNum) {
		if (!phoneNum.equals(this.phone)) {
			System.out.println("����ȣ�� ��ġ���� �ʽ��ϴ�.");
			System.out.println("�α׾ƿ��� �����Ͽ����ϴ�.");
			return;
		}

		User user = null;
		Iterator<User>itr = users.iterator();
		while(itr.hasNext()) {	
			user = itr.next();
			if(user.getPhone().equals(this.phone)) {
				user.setLogged(false);
				user.setOrdering(false);

				if(user.getSeatNo() >=1 && user.getSeatNo() <=reservations.length) {
					reservations[user.getSeatNo() -1] = false;
					user.setSeatNo(0);
				}
				this.phone = null;
				System.out.println("�α׾ƿ� �ƽ��ϴ�.");
				break;
			}
		}
	}

	public void showUsers() {
		System.out.println("�̸�\t��ȭ\t�̸���\t�ּ�\t�α��λ���\t�ֹ���¥\t�ֹ�����\t�ֹ�����");

		Iterator<User> itr = users.iterator();
		User user = null;
		while(itr.hasNext()) {
			user = itr.next();
			user.showUserInfo();
		}
	}

	private void loadDefaultFoodMenu() {
		if(this.foodMenu != null && foodMenu.getFoodMenuList().size() > 0)
			return;
		foodMenu = new FoodMenu();

		foodMenu.addFood(new Food("NOODLE", 1, "¥���", 5000));
		foodMenu.addFood(new Food("NOODLE", 2, "«��", 6500));
		foodMenu.addFood(new Food("NOODLE", 3, "�ᱹ��", 6000));
		foodMenu.addFood(new Food("NOODLE", 4, "����ø�", 6000));
		foodMenu.addFood(new Food("NOODLE", 5, "��Į����", 5500));

		foodMenu.addFood(new Food("SOUP", 1, "��ġ�", 5500));
		foodMenu.addFood(new Food("SOUP", 2, "���屹", 5500));
		foodMenu.addFood(new Food("SOUP", 3, "Ȳ�±�", 6500));

		foodMenu.addFood(new Food("RICE", 1, "�ܺ�����", 5000));
		foodMenu.addFood(new Food("RICE", 2, "��������", 6000));
		foodMenu.addFood(new Food("RICE", 3, "��ä��", 6500));
		foodMenu.addFood(new Food("RICE", 4, "�����", 5500));
		foodMenu.addFood(new Food("RICE", 5, "ȸ����", 8000));

		List<Food> menu = foodMenu.getFoodMenuList();
		foodMenu.setFoodMenuList(menu);
	}

	private User getUserByPhoneAndPassword(String phoneNum, String originalPassword) {
		User user = null;
		Iterator<User> itr = users.iterator();
		boolean valid= false;

		while(itr.hasNext()) {
			user = itr.next();
			if(user.getPhone().equals(phoneNum)) {
				try {
					valid = this.validatePassword(originalPassword, user.getPassword());
					
					if(valid) return user;
					else return null;

				} catch(InvalidKeySpecException e) {
					e.printStackTrace();
				} catch(NoSuchAlgorithmException e) {
					e.printStackTrace();
				}
			}
		}
		
		return null;
	}

	public User getUserByPhone(String phoneNum) {
		if (phoneNum == null) return null;

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

	/* password validation
	 * source : 
	 * https://howtodoinjava.com/security/how-to-generate-secure-password-hash-md5-sha-pbkdf2-bcrypt-examples/
	 * */
	private String generateStorngPasswordHash(String password) 
			throws NoSuchAlgorithmException, InvalidKeySpecException {
        int iterations = 500;
        char[] chars = password.toCharArray();
        byte[] salt = getSalt();
         
        PBEKeySpec spec = new PBEKeySpec(chars, salt, iterations, 64 * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] hash = skf.generateSecret(spec).getEncoded();
        return iterations + ":" + toHex(salt) + ":" + toHex(hash);
    }
     
    private byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
     
    private String toHex(byte[] array) throws NoSuchAlgorithmException {
        BigInteger bi = new BigInteger(1, array);
        String hex = bi.toString(16);
        int paddingLength = (array.length * 2) - hex.length();
        if(paddingLength > 0)
        {
            return String.format("%0"  +paddingLength + "d", 0) + hex;
        }else{
            return hex;
        }
    }

    private boolean validatePassword(String originalPassword, String storedPassword) 
    		throws NoSuchAlgorithmException, InvalidKeySpecException {
        String[] parts = storedPassword.split(":");
        int iterations = Integer.parseInt(parts[0]);
        byte[] salt = fromHex(parts[1]);
        byte[] hash = fromHex(parts[2]);
         
        PBEKeySpec spec = new PBEKeySpec(originalPassword.toCharArray(), salt, iterations, hash.length * 8);
        SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
        byte[] testHash = skf.generateSecret(spec).getEncoded();
         
        int diff = hash.length ^ testHash.length;
        for(int i = 0; i < hash.length && i < testHash.length; i++)
        {
            diff |= hash[i] ^ testHash[i];
        }
        return diff == 0;
    }

    private byte[] fromHex(String hex) throws NoSuchAlgorithmException {
        byte[] bytes = new byte[hex.length() / 2];
        for(int i = 0; i<bytes.length ;i++)
        {
            bytes[i] = (byte)Integer.parseInt(hex.substring(2 * i, 2 * i + 2), 16);
        }
        return bytes;
    }

    public User getAdmin() {
		Iterator<User> itr = users.iterator();
		User user = null;
		while(itr.hasNext()) {
			user = itr.next();
			if(user.getUsername().equals("admin") && user.getPhone().equals("admin"))
				return user;
		}
		return null;
    }

	//getter setter
	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.phone = phone; }
	public boolean[] getReservations() { return reservations; }
	public void setReservations(boolean[] reservations) { this.reservations = reservations; }
	public static int getSeats() { return SEATS; }
	public FoodMenu getFoodMenu() { return foodMenu; }
	public List<User> getUsers() { return users; }
}