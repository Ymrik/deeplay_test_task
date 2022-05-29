package com.barievumar.deeplay_task;

public class Test {
    public static void main(String[] args) {
        Solution.initialize(new Test().getClass().getResource("/right_initializing_info").getFile().toString());
        System.out.println(Solution.getResult("STWSWTPPTPTTPWPP","Human"));
    }
}
