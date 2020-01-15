package com.test.game.soduku;

/**
 * @author qingchuan.xia
 * @date 2019/10/23 16:46
 */
public class TestSudoku {

    public static void main(String[] args) {
        NodeCacheRepo nodeCacheRepo = new NodeCacheRepo();
        nodeCacheRepo.put(1,"A",1,8);
        nodeCacheRepo.put(3,"B",1,3);
        nodeCacheRepo.put(4,"B",2,6);
        nodeCacheRepo.put(2,"C",1,7);
        nodeCacheRepo.put(5,"C",2,9);
        nodeCacheRepo.put(7,"C",3,2);

        nodeCacheRepo.put(2,"D",4,5);
        nodeCacheRepo.put(6,"D",5,7);
        nodeCacheRepo.put(5,"E",5,4);
        nodeCacheRepo.put(6,"E",5,5);
        nodeCacheRepo.put(7,"E",6,7);

        nodeCacheRepo.put(4,"F",5,1);
        nodeCacheRepo.put(8,"F",6,3);
        nodeCacheRepo.put(3,"G",7,1);
        nodeCacheRepo.put(8,"G",9,6);
        nodeCacheRepo.put(9,"G",9,8);

        nodeCacheRepo.put(3,"H",7,8);
        nodeCacheRepo.put(4,"H",8,5);
        nodeCacheRepo.put(8,"H",9,1);
        nodeCacheRepo.put(2,"I",7,9);
        nodeCacheRepo.put(7,"I",9,4);

        SudokuCalculator calculator = new SudokuCalculator();
        calculator.calc(nodeCacheRepo);

    }


}
