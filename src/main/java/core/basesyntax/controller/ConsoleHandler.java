package core.basesyntax.controller;

import core.basesyntax.dao.BetDao;
import core.basesyntax.dao.UserDao;
import core.basesyntax.library.Inject;
import core.basesyntax.model.Bet;
import java.util.Scanner;

public class ConsoleHandler {

    @Inject
    private BetDao betDao;

    @Inject
    private UserDao userDao;

    public void handle() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("q")) {
                betDao.getAll().forEach(System.out::println);
                return;
            }
            Bet bet = null;
            try {
                String[] betData = command.split(" ");
                int value = Integer.parseInt(betData[0]);
                double risk = Double.parseDouble(betData[1]);
                if (value <= 0 || risk <= 0) {
                    System.out.println("Value and risk can not be zero or less");
                    continue;
                }
                bet = new Bet(value, risk);
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                System.out.println("Please, enter correct data");
            }
            if (bet != null) {
                betDao.add(bet);
                System.out.println(bet.toString());
            }
        }
    }

}
