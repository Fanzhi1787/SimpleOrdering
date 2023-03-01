/**
 * @author 繁枝
 * @Description 玄功护体 Bug皆去！
 * @date 2022/12/20-12:39
 */
public class OrderingStart {
    public static void main(String[] args) {

        OrderingUtil ou = new OrderingUtil();
        ou.initial();
        ou.startMenu();
        Thread th = new myThread();
        th.start();
    }
}

class myThread extends Thread {
    @Override
    public void run() {
        OrderingUtil ou = new OrderingUtil();
        ou.add();
    }
}

