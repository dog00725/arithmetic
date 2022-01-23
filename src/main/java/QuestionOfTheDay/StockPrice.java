package QuestionOfTheDay;

import java.util.*;

/**
 * @Author: huqs
 * @Date: 2022/1/23 19:13
 */
public class StockPrice {

    /*
        使用map加treeMap
        map记录时间戳和对应价格；treeMap记录当前所有时间戳种的价格以及存在个数
     */

    Map<Integer,Integer> map;
    TreeMap<Integer,Integer> treeMap;
    int cur;

    public StockPrice() {
        map = new HashMap<>();
        treeMap = new TreeMap<>();
        cur = Integer.MIN_VALUE;
    }

    public void update(int timestamp, int price) {
        cur = Math.max(timestamp,cur);
        if (map.containsKey(timestamp)){
            int oldPrice = map.get(timestamp);
            int times = treeMap.get(oldPrice)-1;
            if (times > 0){
                treeMap.put(oldPrice,times);
            }else{
                treeMap.remove(oldPrice);
            }
        }
        map.put(timestamp,price);
        treeMap.put(price,treeMap.getOrDefault(price,0)+1);
    }

    public int current() {
        return map.get(cur);
    }

    public int maximum() {
        return treeMap.lastKey();
    }

    public int minimum() {
        return treeMap.firstKey();
    }

    public static void main(String[] args) {
        StockPrice stockPrice = new StockPrice();
        stockPrice.update(1, 10); // 时间戳为 [1] ，对应的股票价格为 [10] 。
        stockPrice.update(2, 5);  // 时间戳为 [1,2] ，对应的股票价格为 [10,5] 。
        System.out.println(stockPrice.current());
        System.out.println(stockPrice.maximum());
        stockPrice.update(1, 3);  // 之前时间戳为 1 的价格错误，价格更新为 3 。
        // 时间戳为 [1,2] ，对应股票价格为 [3,5] 。
        System.out.println(stockPrice.maximum());
        stockPrice.update(4, 2);  // 时间戳为 [1,2,4] ，对应价格为 [3,5,2] 。
        System.out.println(stockPrice.minimum()); // 返回 2 ，最低价格时间戳为 4 ，价格为 2
    }
}
