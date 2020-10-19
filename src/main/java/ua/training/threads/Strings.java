package ua.training.threads;

public class Strings implements Runnable {
    private String str = "";
    private boolean isRunning = true;

    @Override
    public void run() {
        while (isRunning) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!str.contentEquals("")) {
                System.out.println("(String) - i got '" + str + "'!");
                str = "";
            }
        }

        System.out.println("String - stop!");
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public void end() {
        this.isRunning = false;
    }
}
