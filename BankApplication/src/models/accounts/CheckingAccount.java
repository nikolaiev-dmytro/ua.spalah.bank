package models.accounts;

/**
 * Created by Man on 07.01.2017.
 */
public class CheckingAccount extends SavingAccount {
    private double overdraft;
    private double balance;
    private AccountType accountType = AccountType.CHECKING;

    public double getOverdraft() {
        return overdraft;
    }

    public CheckingAccount(double balance, double overdraft) {
        super(balance);
        setOverdraft(overdraft);
    }
    @Override
    public void setBalance(double balance){
        if (balance >= -overdraft) {
             this.balance = balance;
        } else {
            throw new IllegalArgumentException("Your credit is exceeded");
        }
    }
    public void setOverdraft(double overdraft) {
        if (overdraft > 0) {
            this.overdraft = overdraft;
        } else {
            throw new IllegalArgumentException("Overdraft can't be negative");
        }
    }

    @Override
    public String toString() {
        return "\nCheckingAccount{" +
                "balance=" + super.getBalance() +
                ", overdraft=" + overdraft +
                ", accountType=" + accountType +
                "}";
    }
}
