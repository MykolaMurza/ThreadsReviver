package ua.training.threads;

import java.util.Scanner;

public class App {
    public static Strings string = new Strings();
    public static Integers integer = new Integers();
    public static Thread threadString = new Thread(string);
    public static Thread threadInteger = new Thread(integer);
    public static Scanner scan = new Scanner(System.in);
    public static String com = "";
    public static boolean isRunning = true;
    public static boolean autoRevive = false;

    public static void main(String[] args) {
        threadString.start();
        threadInteger.start();
        System.out.println("Program started...\nType /help to see all commands");

        while (isRunning) {
            scan = new Scanner(System.in);
            System.out.println("----------------------||----------------------");
            System.out.print("Enter command: ");
            com = scan.next();
            checkCommand();
            autoRevive();
        }

        string.end();
        integer.end();
        scan.close();
        System.out.println("Exit!");
    }

    private static void autoRevive() {
        if (autoRevive) {
            if (!threadString.isAlive()) {
                revive("String");
            }
            if (!threadInteger.isAlive()) {
                revive("Integer");
            }
        }
    }

    private static void checkCommand() {
        switch (com.toLowerCase()) {
            case "/exit":
                isRunning = false;
                break;
            case "/help":
                System.out.println("/help - list of all commands");
                System.out.println("/exit - exit the program");
                System.out.println("/int <Integer> - pass next int to Integers thread");
                System.out.println("/str <String> - pass next string to Strings thread");
                System.out.println("/stats - all threads 'alive' status");
                System.out.println("/revive <'Integer','String'> - restarts named Thread");
                System.out.println("/auto_revive - switches auto revive thread option");
                break;
            case "/int":
                if (!threadInteger.isAlive()) {
                    System.out.println("ERROR - Integer thread is dead!");
                    break;
                }
                String integerArg = scan.next();
                integer.setNum(integerArg);
                hold(50);
                break;
            case "/str":
                if (!threadString.isAlive()) {
                    System.out.println("ERROR - String thread is dead!");
                    break;
                }
                scan.skip(" ");
                String stringArg = scan.nextLine();
                string.setStr(stringArg);
                hold(50);
                break;
            case "/stats":
                System.out.println("Integer thread alive status: " + threadInteger.isAlive());
                System.out.println("String thread alive status: " + threadString.isAlive());
                break;
            case "/revive":
                revive(scan.next());
                break;
            case "/auto_revive":
                autoRevive = !autoRevive;
                System.out.println("Auto Revive otipon is now: " + autoRevive);
                break;
            default:
                System.out.println("ERROR - Unknown command!");
                break;
        }
    }

    private static void revive(String arg) {
        switch (arg.toLowerCase()) {
            case "integer":
                if (threadInteger.isAlive()) {
                    System.out.println("ERROR - Integer thread is still alive!");
                    break;
                }
                integer = new Integers();
                threadInteger = new Thread(integer);
                threadInteger.start();
                hold(100);
                if (threadString.isAlive()) {
                    System.out.println("Integer thread is revived!");
                }
                break;
            case "string":
                if (threadString.isAlive()) {
                    System.out.println("ERROR - String thread is still alive!");
                    break;
                }
                string = new Strings();
                threadString = new Thread(string);
                threadString.start();
                hold(100);
                if (threadString.isAlive()) {
                    System.out.println("String thread is revived!");
                }
                break;
            default:
                System.out.println("ERROR - unknown thread!");
                break;
        }
    }

    private static void hold(int delay) {
        try {
            Thread.sleep(delay);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
