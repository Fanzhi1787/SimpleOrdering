import java.util.Scanner;

/**
 * @author 繁枝
 * @Description 玄功护体 Bug皆去！
 * @date 2022/12/20-12:39
 */
public class OrderingUtil {
    String[] dishNames = {"香菇滑鸡", "羊肉串", "水煮鱼"};  //菜单名称
    double[] prices = new double[]{66.0, 77.0, 88.0}; //菜品单价
    int[] PraiseNums = new int[3];   //点赞数

    OrderingSet oSet = new OrderingSet();

    public void initial() {
        //初始化订单信息
        oSet.names[0] = "张政";
        oSet.dishMegs[0] = "香菇滑鸡 2份";
        oSet.times[0] = 12;
        oSet.addresses[0] = "南京邮电大学锁金村校区";
        oSet.sumPrices[0] = 76.0;
        oSet.states[0] = 1;

        oSet.names[1] = "张政";
        oSet.dishMegs[1] = "羊肉串 2份";
        oSet.times[1] = 18;
        oSet.addresses[1] = "南京邮电大学锁金村校区";
        oSet.sumPrices[1] = 45.0;
        oSet.states[1] = 0;
    }

    public void startMenu() {
        OrderingUtil om = new OrderingUtil();
        Scanner input = new Scanner(System.in);
        int num = -1;        //用户输入 0 返回主菜单,否则退出系统
        boolean isExit = false;   //标志用户是否退出系统 ：true退出系统
        System.out.println("\n欢迎使用”吃货联盟点餐系统 ");
        do {
            //显示菜单
            System.out.println("* * * * * * * * * * * * * * * * * * * * *");
            System.out.println("1、我要订餐");
            System.out.println("2、查看餐袋");
            System.out.println("3、签收订单");
            System.out.println("4、删除订单");
            System.out.println("5、我要点赞");
            System.out.println("6、退出系统");
            System.out.println("* * * * * * * * * * * * * * * * * * * * *");
            System.out.print("请选择：");
            int choose = input.nextInt();  //记录用户选择的功能编号
            switch (choose) {
                case 1 -> {
                    //我要订餐
                    System.out.println("* * * * * * 我要订餐* * * * * * ");
                    add();
                }
                case 2 -> {
                    //查看餐袋
                    System.out.println("* * * * * * 查看餐袋* * * * * * ");
                    display();
                }
                case 3 -> {
                    //签收订单
                    System.out.println("* * * * * * 签收订单* * * * * * ");
                    sign();
                }
                case 4 -> {
                    //删除订单
                    System.out.println("* * * * * * 删除订单* * * * * * ");
                    delete();
                }
                case 5 -> {
                    //我要点赞
                    System.out.println("* * * * * * 我要点赞* * * * * * ");
                    praise();
                }
                case 6 -> {
                    //退出系统
                    isExit = true;
                    System.out.println("* * * * * * 退出系统* * * * * * ");
                }
                default ->
                    //退出系统
                        isExit = true;
            }
            if (!isExit) {
                System.out.println("输入'0'返回：");
                num = input.nextInt();

            } else {
                break;
            }
        } while (num == 0);
    }

    //参看餐袋
    public void display() {
        System.out.println("序号\t 订餐人\t 餐品信息\t\t 送餐时间\t 送餐地址\t\t 总金额\t 订单状态");
        for (int i = 0; i < oSet.names.length; i++) {
            if (oSet.names[i] != null) {
                String state = (oSet.states[i] == 0) ? "已预定" : "已完成";
                String date = oSet.times[i] + "点";
                String sumPrice = oSet.sumPrices[i] + "元";
                System.out.println((i + 1) + "\t" + oSet.names[i] + "\t" + oSet.dishMegs[i] + "\t" + date + "\t" +
                        oSet.addresses[i] + "\t" + sumPrice + "\t" + state);
            }
        }
    }

    //我要订餐
    public synchronized boolean add() {
        String tName = Thread.currentThread().getName();
        System.out.println("线程" + tName + "订餐：");
        boolean isAdd = false;  //记录是否可以订餐
        Scanner input = new Scanner(System.in);
        for (int j = 0; j < oSet.names.length; j++) {
            if (oSet.names[j] == null) {
                isAdd = true;
                System.out.println("请输入订餐姓名");
                String name = input.next();    //姓名
                //显示供先择的菜品信息
                System.out.println("序号" + "\t" + "菜名" + "\t" + "单价" + "" + "\t" + "点赞数");
                for (int i = 0; i < dishNames.length; i++) {
                    String price = prices[i] + "元";
                    String priaiseNum = (PraiseNums[i]) > 0 ? PraiseNums[i] + "赞" : "0";
                    System.out.println((i + 1) + "\t" + dishNames[i] + "\t" + price + "\t" + priaiseNum);
                }
                System.out.println("请输入您要点的菜品编号:");
                int chooseDish = input.nextInt();
                System.out.println("请选择您需要的份数");
                int number = input.nextInt();
                String dishMeg = dishNames[chooseDish - 1] + " " + number + "份"; //商品信息+数量
                double sumPrice = prices[chooseDish - 1] * number;           //餐品总金额
                double deliCharge = (sumPrice >= 50) ? 0 : 5;                  //送餐金额

                System.out.println("请输入配送时间（送餐时间是1点至24点");
                int time = input.nextInt();                             //配送时间
                while (time < 1 || time > 24) {
                    System.out.println("你输入的有误，请输入1-24之间的整数");
                    time = input.nextInt();
                }
                System.out.println("请输入送餐地址");
                String address = input.next();                          //配送地址

                System.out.println("订餐成功");
                System.out.println("您订的是：" + dishMeg);
                System.out.println("送餐时间为" + time + "点");
                System.out.println("餐费" + sumPrice + "元,送餐费为" + deliCharge + "元，总计：" + (sumPrice + deliCharge) + "元");

                oSet.names[j] = name;
                oSet.dishMegs[j] = dishMeg;
                oSet.times[j] = time;
                oSet.addresses[j] = address;
                oSet.sumPrices[j] = sumPrice + deliCharge;
                break;
            }
        }
        if (!isAdd) {
            System.out.println("对不起，当前餐袋已满！");
            return false;
        } else {
            return true;
        }
    }

    //签收订单
    public void sign() {
        Scanner input = new Scanner(System.in);
        boolean isSignFind = false;                 //找到要签收的订单
        display();
        System.out.println("请选择要签收的订单");

        int signorderId = input.nextInt();
        for (int i = 0; i < oSet.names.length; i++) {
            if (oSet.names[i] != null && oSet.states[i] == 0 && signorderId == i + 1) {
                oSet.states[i] = 1;
                System.out.println("订单签收成功");
                isSignFind = true;       //标记已找到此订单

            } else if (oSet.names[i] != null && oSet.states[i] == 1 && signorderId == i + 1) {
                System.out.println("您选择的订单已签收，不能再次签收哦");
                isSignFind = true;       //标记已找到此订单
            }

        }
        //未找到的订单序号：不可签收
        if (!isSignFind) {
            System.out.println("您选择的订单不存在");
        }
    }

    //删除订单
    public void delete() {

        boolean isDelete = false;//标记是否找到要删除订单
        display();
        System.out.println("请输入要删除的订单序号：");

        int delId = new Scanner(System.in).nextInt();
        for (int i = 0; i < oSet.names.length; i++) {
            if (oSet.names[i] != null && oSet.states[i] == 1 && delId == i + 1) {
                isDelete = true;
                for (int j = delId - 1; j < oSet.names.length - 1; j++) {
                    oSet.names[j] = oSet.names[j + 1];                      //姓名
                    oSet.dishMegs[j] = oSet.dishMegs[j + 1];                //餐品信息
                    oSet.times[j] = oSet.times[j + 1];                      //送餐时间
                    oSet.addresses[j] = oSet.addresses[j + 1];              //收货地址
                    oSet.states[j] = oSet.states[j + 1];                    //点赞数
                    oSet.sumPrices[j] = oSet.sumPrices[j + 1];              //总价

                }
                int endIndex = oSet.names.length - 1;
                oSet.dishMegs[endIndex] = null;
                oSet.times[endIndex] = 0;
                oSet.addresses[endIndex] = null;
                oSet.sumPrices[endIndex] = 0;
                oSet.states[endIndex] = 0;

                System.out.println("删除订单成功！");
                break;

            } else if (oSet.names[i] != null && oSet.states[i] == 0 && delId == i + 1) {
                System.out.println("您选择的订单未签收，不能删除");
                isDelete = true;
                break;
            }
        }
        if (!isDelete) {
            System.out.println("您要删除的订单不存在！");
        }
    }

    //我要点赞
    public void praise() {

        System.out.println("序号" + "\t" + "菜名" + "\t" + "单价");
        for (int i = 0; i < dishNames.length; i++) {
            String price = prices[i] + "元";
            String prianiseNum = (PraiseNums[i]) > 0 ? PraiseNums[i] + "赞" : " ";
            System.out.println((i + 1) + "\t" + dishNames[i] + "\t" + price + "\t" + prianiseNum);
        }
        System.out.println("请选择您要点赞的菜品序号：");

        int priaiseNum = new Scanner(System.in).nextInt();
        PraiseNums[priaiseNum - 1]++;  //点赞数加1
        System.out.println("点赞成功");
    }
}


