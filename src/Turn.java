import java.util.ArrayList;
import java.util.List;

public class Turn {
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

    /**
     * 把班次和学生进行互相绑定
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

    int getDayTime() {
        return dayTime;
    }

    int getWorkDay() {
        return workDay;
    }


    int getFixedStudentCount() {
        return fixedStudents.size();
    }

    List<Student> getFixedStudents() {
        return fixedStudents;
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("星期【")
                .append(workDay)
                .append("】第【")
                .append(dayTime)
                .append("】班次有学生：");
        if (students.size() == 0) {
            stringBuilder.append("无");
        } else {
            for (Student s :
                    students) {
                stringBuilder.append(s);
            }
        }
        return stringBuilder.toString();
    }
}
