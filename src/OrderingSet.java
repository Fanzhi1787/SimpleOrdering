/**
 * @author 繁枝
 * @Description 玄功护体 Bug皆去！
 * @date 2022/12/20-12:38
 */
public class OrderingSet {
    String[] names = new String[4];     //保存订餐人名称
    String[] dishMegs = new String[4];  //保存所选信息，包括菜品名及份数
    int[] times = new int[4];           //保存送餐时间
    String[] addresses = new String[4]; //保存送餐地址
    int[] states = new int[4];          //保存订单状态，0 表示已预定，1 表示已完成
    double[] sumPrices = new double[4]; //保存订单的总金额
}
