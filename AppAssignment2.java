import java.util.Scanner;

public class AppAssignment2 {
    private static final Scanner SCANNER = new Scanner(System.in);

    public static void main(String[] args) {
        final String CLEAR = "\033[H\033[2J";
        final String COLOR_BLUE_BOLD = "\033[34;1m";
        final String COLOR_RED_BOLD = "\033[31;1m";
        final String COLOR_GREEN_BOLD = "\033[33;1m";
        final String RESET = "\033[0m";

        final String DASHBOARD = "Welcome to Smart Banking";
        final String CREAT_NEW_ACCOUNT = "Creat New Account";
        final String DEPOSIT = "Deposit";
        final String WITHDRAWALS = "Withdrawals";
        final String TRANSFER = "Transfer";
        final String CHECK_ACCOUNT_BALANCE = "Check Account Balance";
        final String DELETE_ACCOUNT = "Delete account";
        final String EXIT = "Exit";

        final String ERROR_MSG = String.format("\t%s%s%s\n", COLOR_RED_BOLD, "%s", RESET);
        final String SUCCESS_MSG = String.format("\t%s%s%s\n", COLOR_GREEN_BOLD, "%s", RESET);

        String[][] accounts = new String[0][3];
        int accountIdCounter = 1;

        String screen = DASHBOARD;

        mainLoop:
        do {
            final String APP_TITLE = String.format("%s%s%s", COLOR_BLUE_BOLD, screen, RESET);

            System.out.println(CLEAR);
            System.out.println("\t" + APP_TITLE + "\n");

            switch (screen) {
                case DASHBOARD:
                    System.out.println("\t[1]. Create New Account");
                    System.out.println("\t[2]. Deposit");
                    System.out.println("\t[3]. Withdrawals");
                    System.out.println("\t[4]. Transfer");
                    System.out.println("\t[5]. Check Account Balance");
                    System.out.println("\t[6]. Delete Account");
                    System.out.println("\t[7]. Exit\n");
                    System.out.print("\tEnter an option to continue: ");
                    int option = SCANNER.nextInt();
                    SCANNER.nextLine();

                    switch (option) {
                        case 1:
                            screen = CREAT_NEW_ACCOUNT;
                            break;
                        case 2:
                            screen = DEPOSIT;
                            break;
                        case 3:
                            screen = WITHDRAWALS;
                            break;
                        case 4:
                            screen = TRANSFER;
                            break;
                        case 5:
                            screen = CHECK_ACCOUNT_BALANCE;
                            break;
                        case 6:
                            screen = DELETE_ACCOUNT;
                            break;
                        case 7:
                            System.out.println(CLEAR);
                            System.exit(0);
                        default:
                            continue;
                    }
                    break;

                case CREAT_NEW_ACCOUNT:
                    String newAccountId = String.format("SDB-%05d", accountIdCounter);
                    String newName;
                    boolean valid;

                    do {
                        valid = true;
                        System.out.print("\tEnter A/C Name: ");
                        newName = SCANNER.nextLine().strip();
                        if (newName.isBlank()) {
                            System.out.printf(ERROR_MSG, "A/C name can't be empty");
                            valid = false;
                            continue;
                        }
                        for (int i = 0; i < newName.length(); i++) {
                            if (!(Character.isLetter(newName.charAt(i)) || Character.isSpaceChar(newName.charAt(i)))) {
                                System.out.printf(ERROR_MSG, "Invalid A/C name");
                                valid = false;
                                break;
                            }
                        }
                    } while (!valid);

                    int initialDeposit;

                    do {
                        valid = true;
                        System.out.print("Enter your Deposited Amount Here: ");
                        initialDeposit = SCANNER.nextInt();
                        SCANNER.nextLine();

                        if (initialDeposit > 5000) {
                            System.out.println("Initial Deposit: " + initialDeposit);
                            System.out.println();
                        } else {
                            System.out.printf(ERROR_MSG, "Not Sufficient Amount In Your A/C");
                            valid = false;
                            continue;
                        }
                    } while (!valid);

                    String[] newAccount = {newAccountId, newName, String.valueOf(initialDeposit)};
                    String[][] newAccounts = new String[accounts.length + 1][3];
                    for (int i = 0; i < accounts.length; i++) {
                        newAccounts[i] = accounts[i];
                    }
                    newAccounts[newAccounts.length - 1] = newAccount;
                    accounts = newAccounts;

                    accountIdCounter++;

                    System.out.printf(SUCCESS_MSG, String.format("%s:%s has been saved successfully", newAccountId, newName));
                    System.out.print("\tDo you want to continue adding (Y/n)? ");
                    if (SCANNER.nextLine().strip().equalsIgnoreCase("Y")) {
                        continue;
                    }
                    screen = DASHBOARD;
                    break;


                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        } while (true);
    }
}
