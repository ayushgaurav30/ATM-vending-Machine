import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String name;
    private String code;
    private double balance;

    public User(String name, String code, double balance) {
        this.name = name;
        this.code = code;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Successfully deposited $" + amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("Successfully withdrew $" + amount);
        } else {
            System.out.println("Invalid withdrawal amount or insufficient balance.");
        }
    }
}

class ATM {
    private Map<String, User> users;

    public ATM() {
        users = new HashMap<>();
        // Predefined users
        users.put("1234", new User("Alice", "1234", 1000.0));
        users.put("5678", new User("Bob", "5678", 1500.0));
        users.put("9101", new User("Charlie", "9101", 2000.0));
    }

    public User authenticate(String code) {
        return users.get(code);
    }

    public void menu(User user) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n===== ATM Menu =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Cash");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Current Balance: $" + user.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    user.deposit(depositAmount);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    user.withdraw(withdrawAmount);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your code: ");
        String code = scanner.nextLine();

        User user = atm.authenticate(code);
        if (user != null) {
            System.out.println("Welcome, " + user.getName() + "!");
            atm.menu(user);
        } else {
            System.out.println("Invalid code. Access denied.");
        }

        scanner.close();
    }
}
