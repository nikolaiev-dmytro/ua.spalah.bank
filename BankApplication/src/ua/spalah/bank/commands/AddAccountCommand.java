package ua.spalah.bank.commands;

import ua.spalah.bank.models.accounts.Account;
import ua.spalah.bank.models.accounts.CheckingAccount;
import ua.spalah.bank.models.accounts.SavingAccount;
import ua.spalah.bank.services.ClientService;

import java.util.Scanner;

/**
 * Created by Man on 13.01.2017.
 */
public class AddAccountCommand implements Command {
    private final ClientService clientService;

    public AddAccountCommand(ClientService clientService) {
        this.clientService = clientService;
    }

    @Override
    public void execute() {
        Scanner in = new Scanner(System.in);
        Account account=null;
        System.out.println("Enter the type of account:\n1.Saving account\n2.Checking account");
        int i = in.nextInt();
        switch (i) {
            case 1: {
                System.out.println("Enter the start balance for your account");
                double balance = in.nextDouble();
                account = new SavingAccount(balance);
                clientService.addAccount(BankCommander.currentClient, account);
                selectThisAccountActive(in, account);
                break;
            }
            case 2: {
                System.out.println("Enter the start balance for your account");
                double balance = in.nextDouble();
                System.out.println("Enter the overdraft for your account");
                double overdraft = in.nextDouble();
                account = new CheckingAccount(balance, overdraft);
                clientService.addAccount(BankCommander.currentClient, account);
                selectThisAccountActive(in, account);
                break;
            }
            default: {
                System.out.println("Incorrect input");
                break;
            }
        }
    }

    private static void selectThisAccountActive(Scanner in, Account account) {
        if (BankCommander.currentClient.getAccounts().size() == 1) {
            BankCommander.currentClient.setActiveAccount(account);
        } else {
            System.out.println("Do you want to make this account active?\n1. Yes\n2.No");
            int answer = in.nextInt();
            switch (answer) {
                case 1: {
                    BankCommander.currentClient.setActiveAccount(account);
                    break;
                }
                default: {
                    if (answer != 2) {
                        System.out.println("Incorrect input");
                    }
                    break;
                }
            }
        }
    }
    @Override
    public String getCommandInfo() {
        return "Add account for current client";
    }

    @Override
    public boolean currentClientIsNeeded() {
        return true;
    }


}
