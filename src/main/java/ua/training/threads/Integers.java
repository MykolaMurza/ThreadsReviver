package ua.training.threads;

public class Integers implements Runnable {
    private String num = "";
    private boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!num.contentEquals("")) {
                System.out.println("(Integer) - i got an " + Integer.parseInt(num) + "!");
                num = "";
            }
        }

        System.out.println("Integer - stop!");
    }

    public int getNum() {
        return Integer.parseInt(num);
    }

    public void setNum(String string) {
        this.num = string;
    }

    public void end() {
        this.isRunning = false;
    }
}