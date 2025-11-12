import java.util.Scanner;

// Abstract Class (Abstraction)
abstract class Account {
    protected String accountHolder;
    protected double balance;

    public Account(String accountHolder, double balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    // Encapsulation: Getters
    public String getAccountHolder() { return accountHolder; }
    public double getBalance() { return balance; }

    // Abstract Methods
    abstract void deposit(double amount);
    abstract void withdraw(double amount);
}

// Inheritance: SavingsAccount extends Account
class SavingsAccount extends Account {
    private double interestRate = 0.05;

    public SavingsAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: ₹" + amount);
    }

    @Override
    void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("❌ Insufficient balance!");
        }
    }

    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
        System.out.println("Interest added: ₹" + interest);
    }
}

// Inheritance: CurrentAccount extends Account
class CurrentAccount extends Account {
    private double overdraftLimit = 1000;

    public CurrentAccount(String accountHolder, double balance) {
        super(accountHolder, balance);
    }

    @Override
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: ₹" + amount);
    }

    @Override
    void withdraw(double amount) {
        if (balance + overdraftLimit >= amount) {
            balance -= amount;
            System.out.println("Withdrawn: ₹" + amount);
        } else {
            System.out.println("❌ Overdraft limit exceeded!");
        }
    }
}

// Main Class
public class BankSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Account Holder Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Initial Balance: ₹");
        double balance = sc.nextDouble();

        System.out.println("Choose Account Type: 1. Savings  2. Current");
        int type = sc.nextInt();

        Account acc;
        if (type == 1)
            acc = new SavingsAccount(name, balance);
        else
            acc = new CurrentAccount(name, balance);

        int choice;
        do {
            System.out.println("\n--- Banking Menu ---");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter amount to deposit: ₹");
                    double dep = sc.nextDouble();
                    acc.deposit(dep);
                    break;

                case 2:
                    System.out.print("Enter amount to withdraw: ₹");
                    double wd = sc.nextDouble();
                    acc.withdraw(wd);
                    break;

                case 3:
                    System.out.println("Current Balance: ₹" + acc.getBalance());
                    break;

                case 4:
                    System.out.println("Thank you for using our banking system!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 4);

        sc.close();
    }
}
