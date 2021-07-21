package com.stream;



import lombok.AllArgsConstructor;
import lombok.Data;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class FindAbsence {
        /*自己的方法：
        List<String> l = studentList.filter(list.getlist() -> allMatch(for each )).collect(Collectors.toList());
        print l
        */
    @Data
    @AllArgsConstructor
    class ExamStudentScore{
        private String studentName;
        private Integer scoreValue;
        private String subject;
    }
    Map<String, List<ExamStudentScore>> studentMap;


    @Before
    public void init(){

        studentMap = new HashMap<>();
        List<ExamStudentScore> zsScoreList = new ArrayList<>();
        zsScoreList.add(
                new ExamStudentScore(
                        "张三",
                        30,
                        "Chinese"
                )
        );
        zsScoreList.add(
                new ExamStudentScore(
                        "张三",
                        40,
                        "ENGLISH"));
        zsScoreList.add(
                new ExamStudentScore(
                        "张三",
                        50,
                        "MATHS"));
        studentMap.put("张三", zsScoreList);

        List<ExamStudentScore> lsScoreList = new ArrayList<>();
        lsScoreList.add(
                new ExamStudentScore(
                        "李四",
                        80,
                        "CHINESE"));
        lsScoreList.add(
                new ExamStudentScore(
                        "李四",
                        null,
                        "ENGLISH"));
        lsScoreList.add(
                new ExamStudentScore(
                        "李四",
                        100,
                        "MATHS"));
        studentMap.put("李四", lsScoreList);

        List<ExamStudentScore> wwScoreList = new ArrayList<>();
        wwScoreList.add(
                new ExamStudentScore(
                        "王五",
                        null,
                        "CHINESE"));
        wwScoreList.add(
                new ExamStudentScore(
                        "王五",
                        null,
                        "ENGLISH"));
        wwScoreList.add(
                new ExamStudentScore(
                        "王五",
                        70,
                        "MATHS"));
        studentMap.put("王五", wwScoreList);
    }
    @Test
    public void findAbsence(){
        studentMap.forEach((studentName, scoreList) -> {
            boolean isAbsence = scoreList.stream().anyMatch(value -> {
                return value.getScoreValue() == null;
            });
            if (isAbsence){
                System.out.println("此学生"+studentName + "有缺考情况");
            }
            });
    }

}

