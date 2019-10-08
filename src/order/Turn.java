package order;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Turn implements Serializable {
    private int workDay;
    private int dayTime;
    private List<Student> students;
    private List<Student> fixedStudents;
    static int maxStudentCount = 0;


    Turn(int workDay, int dayTime) {
        this.workDay = workDay;
        this.dayTime = dayTime;
        students = new ArrayList<>();
        fixedStudents = new ArrayList<>();
    }


    /**
     * 获取当前班次所有学生的空闲班次的总和
     *
     * @return 学生们所剩空闲时间总和
     */
    int getAllStudentCanCount() {
        int n = 0;
        for (Student s : students) {
            n += s.getFreeTimeCount();
        }
        return n;
    }


    void addStudengt(Student student) {
        students.add(student);
        student.addSuitableTurn(this);
    }

    public void reset() {
        maxStudentCount = 0;
        for (int i = 0, j = students.size(); i < j; i++) {
            students.remove(0);
        }
        for (int i = 0, j = fixedStudents.size(); i < j; i++) {
            fixedStudents.remove(0);
        }
    }

    /**
     * 把班次和学生进行互相绑定
     *
     * @param student 需要绑定的学生
     */
    void addFixedStudengt(Student student) {

        if (!isFixedExists(student)) {
            student.fixed(this);
            fixedStudents.add(student);
        }
        if (student.getFixedCount() > 2) {
            System.out.println();
        }
    }

    private boolean isFixedExists(Student student) {
        boolean n = false;
        for (Student s : fixedStudents) {
            if (student == s) {
                n = true;
            }
        }
        return n;
    }

    int getStudentNumber() {
        return students.size();
    }

    List<Student> getStudents() {
        return students;
    }

    public int getDayTime() {
        return dayTime;
    }

    public int getWorkDay() {
        return workDay;
    }



    int getFixedStudentCount() {
        return fixedStudents.size();
    }

    public List<Student> getFixedStudents() {
        return fixedStudents;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder
                .append("第")
                .append(dayTimeChange(this))
                .append("节课：");
        return stringBuilder.toString();
    }


    private String dayTimeChange(Turn turn) {
        switch (turn.getDayTime()) {
            case 1:
                return "一二";
            case 2:
                return "三四";
            case 3:
                return "五六";
            case 4:
                return "七八";
        }
        return "发生错误";
    }
}