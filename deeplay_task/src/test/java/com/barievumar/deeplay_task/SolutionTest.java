package com.barievumar.deeplay_task;

import com.barievumar.deeplay_task.exception.WrongDataException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;


class SolutionTest {

    @BeforeEach
    void initialize_race_info(){
        Solution.initialize(new Solution().getClass().getResource("/right_initializing_info").getFile().toString());
    }
    @Test
    void test_task_condition_for_human() {
        int length = Solution.getResult("STWSWTPPTPTTPWPP","Human");
        Assertions.assertEquals(10,length);
    }

    @Test
    void test_task_condition_for_swamper() {
        int length = Solution.getResult("STWSWTPPTPTTPWPP","Swamper");
        Assertions.assertEquals(15,length);
    }

    @Test
    void test_task_condition_for_woodman() {
        int length = Solution.getResult("STWSWTPPTPTTPWPP","Woodman");
        Assertions.assertEquals(12,length);
    }

    @Test
    void test_with_another_field_description_for_human(){
        int length = Solution.getResult("SWPPTWSSTWPSWTTP","Human");
        Assertions.assertEquals(11,length);
    }

    @Test
    void test_with_another_field_description_for_swamper(){
        int length = Solution.getResult("SWPPTWSSTWPSWTTP","Swamper");
        Assertions.assertEquals(12,length);
    }

    @Test
    void test_with_another_field_description_for_woodman(){
        int length = Solution.getResult("SWPPTWSSTWPSWTTP","Woodman");
        Assertions.assertEquals(13,length);
    }

    @Test
    void test_field_with_same_cells_for_human(){
        int length = Solution.getResult("PPPPPPPPPPPPPPPP","Human");
        Assertions.assertEquals(6,length);
    }

    @Test
    void test_field_with_same_cells_for_swamper(){
        int length = Solution.getResult("PPPPPPPPPPPPPPPP","Swamper");
        Assertions.assertEquals(12,length);
    }

    @Test
    void test_field_with_same_cells_for_woodman(){
        int length = Solution.getResult("SSSSSSSSSSSSSSSS","Woodman");
        Assertions.assertEquals(18,length);
    }

    @Test
    void test_wrong_field_description_length_exception(){
        try {
            Solution.getResult("SSSSSSTTTPP","Human");
            Assertions.fail();
        }
        catch (WrongDataException e){
            if(!e.getMessage().equals("Exception: fieldDescriptions must contain 16 symbols"))Assertions.fail();
        }
    }

    @Test
    void test_no_such_race_exception(){
        try {
            Solution.getResult("SSSSSSSSSSSSSSSS","Witcher");
            Assertions.fail();
        }
        catch (WrongDataException e){
            if(!e.getMessage().equals("Exception: no such race in raceInfoMap"))Assertions.fail();
        }
    }

    @Test
    void test_wrong_cell_in_field_description_exception(){
        try {
            Solution.getResult("SSSSSSSSSSSSASSS","Human");
            Assertions.fail();
        }
        catch (WrongDataException e){
            if(!e.getMessage().equals("Exception: no such cell in raceInfoMap"))Assertions.fail();
        }
    }

    @Test
    void test_initializing_race_info_map(){
        HashMap<String, Map<String,Integer>> raceInfoMap = new HashMap<>();
        Map<String,Integer> humanMap = new HashMap<>();
        humanMap.put("S",5);
        humanMap.put("W",2);
        humanMap.put("T",3);
        humanMap.put("P",1);
        Map<String,Integer> swamperMap = new HashMap<>();
        swamperMap.put("S",2);
        swamperMap.put("W",2);
        swamperMap.put("T",5);
        swamperMap.put("P",2);
        Map<String,Integer> woodmanMap = new HashMap<>();
        woodmanMap.put("S",3);
        woodmanMap.put("W",3);
        woodmanMap.put("T",2);
        woodmanMap.put("P",2);
        raceInfoMap.put("Human",humanMap);
        raceInfoMap.put("Swamper",swamperMap);
        raceInfoMap.put("Woodman",woodmanMap);
        Assertions.assertEquals(raceInfoMap,Solution.getRaceInfoMap());
    }

    @Test
    void test_crushing_race_info_map_initializing(){
        try{
            Solution.initialize(new Solution().getClass().getResource("/wrong_initializing_info").getFile().toString());
            Assertions.fail();
        }
        catch (WrongDataException e){
            if(!e.getMessage().equals("Exception: the number of obstacles and the data in the race description must match"))
                Assertions.fail();
        }
    }

    @Test
    void test_empty_file_exception(){
        try{
            Solution.initialize(new Solution().getClass().getResource("/empty_file").getFile().toString());
            Assertions.fail();
        }
        catch (WrongDataException e){
            if(!e.getMessage().equals("Exception: file is empty"))Assertions.fail();
        }
    }
}