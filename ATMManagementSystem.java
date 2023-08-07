import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
class ATM {
private Map<String, Account> accounts;
public ATM() {
this.accounts = new HashMap<>();
}
public void addAccount(Account account) {
accounts.put(account.getAccountNumber(), account);
}
public void removeAccount(String accountNumber) {
accounts.remove(accountNumber);
}
public boolean validatePin(String accountNumber, String pin) {
Account account = accounts.get(accountNumber);
return account != null && account.validatePin(pin);
}
public void deposit(String accountNumber, double amount) {
Account account = accounts.get(accountNumber);
if (account != null) {
account.deposit(amount);
System.out.println("Deposit successful. New balance: " + account.getBalance());
} else {
System.out.println("Invalid account number.");
}
}
public void withdraw(String accountNumber, String pin, double amount) {
Account account = accounts.get(accountNumber);
if (account != null) {
if (account.validatePin(pin)) {
if (account.getBalance() >= amount) {
account.withdraw(amount);
System.out.println("Withdrawal successful. New balance: " + account.getBalance());
} else {
System.out.println("Insufficient balance.");
}
} else {
System.out.println("Invalid PIN.");
}
} else {
System.out.println("Invalid account number.");
}
}
}
class Account {
private String accountNumber;
private String pin;
private double balance;
public Account(String accountNumber, String pin, double balance) {
this.accountNumber = accountNumber;
this.pin = pin;
this.balance = balance;
}
public String getAccountNumber() {
return accountNumber;
}
public double getBalance() {
return balance;
}
public boolean validatePin(String pin) {
return this.pin.equals(pin);
}
public void deposit(double amount) {
balance += amount;
}
public void withdraw(double amount) {
balance -= amount;
}
}
class ATMManagementSystem {
public static void main(String[] args) {
ATM atm = new ATM();
Account account1 = new Account("1207", "1111", 5000.0);
Account account2 = new Account("1208", "1111", 10000.0);
Account account3 = new Account("1209", "1111", 5000.0);
Account account4 = new Account("1210", "1111", 10000.0);
atm.addAccount(account1);
atm.addAccount(account2);
atm.addAccount(account3);
atm.addAccount(account4);
Scanner scanner = new Scanner(System.in);
System.out.print("Enter account number: ");
String accountNumber = scanner.nextLine();
System.out.print("Enter PIN: ");
String pin = scanner.nextLine();
if (atm.validatePin(accountNumber, pin)) {
System.out.println("Authentication successful.");
while (true) {
System.out.println("\n==== ATM Menu ====");
System.out.println("1. Deposit");
System.out.println("2. Withdraw");
System.out.println("3. Exit");
System.out.print("Enter your choice: ");
int choice = scanner.nextInt();
switch (choice) {
case 1:
System.out.print("Enter amount to deposit: ");
double depositAmount = scanner.nextDouble();
atm.deposit(accountNumber, depositAmount);
break;
case 2:
System.out.print("Enter amount to withdraw: ");
double withdrawAmount = scanner.nextDouble();
atm.withdraw(accountNumber, pin, withdrawAmount);
break;
case 3:
System.out.println("Thank you for using the ATM. Goodbye!");
System.exit(0);
default:
System.out.println("Invalid choice.");
}
}
} else {
System.out.println("Invalid account number or PIN.");
}
}
}