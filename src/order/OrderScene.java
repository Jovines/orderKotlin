package order;


import java.util.ArrayList;
import java.util.List;

public class OrderScene {
    private String name;
    private List<Student> students;//总的学生列表，如不是删除和添加学生或者改变次序不能轻易改变
    private List<Turn> turns;//还未排好的班次列表
    private List<Turn> fixedTurns;//已经排好的班次列表

    private int turnCanfixed = 0;//一个班次能被最多能被安排人数
    private int studentShouldBeFixed = 0;//学生应该被安排次数

    public OrderScene(String name) {
        this.name = name;
        students = new ArrayList<>();
        turns = new ArrayList<>();
        fixedTurns = new ArrayList<>();
    }

    public OrderScene() {
    }

    public void addStudent(Student student) {
        if (!students.contains(student)) {
            students.add(student);
        }
        if (!student.getOrderSceneList().contains(this)) {
            student.addOrderScene(this);
        }
        order();

    }

    public void addStudentAll(List<Student> students) {
        students.addAll(students);
        for (Student s :
                students) {
            s.addOrderScene(this);
        }
        order();

    }


    public void order() {
//        if (turnCanfixed == 0 && studentShouldBeFixed == 0) {
        Order.order(students, turns, fixedTurns, Order.turnCanfixed, Order.studentShouldBeFixed);
//        }
//        Order.order(students, turns, fixedTurns, turnCanfixed, studentShouldBeFixed);
    }

    public void removeStudent(Student student) {
        students.remove(student);
        student.removeOrderScene(this);
        order();
    }

    public void setTurnCanfixed(int turnCanfixed) {
        this.turnCanfixed = turnCanfixed;
    }

    public void setStudentShouldBeFixed(int studentShouldBeFixed) {
        this.studentShouldBeFixed = studentShouldBeFixed;
    }

    public List<Turn> getFixedTurns() {
        return fixedTurns;
    }

    public int getStudentShouldBeFixed() {
        return studentShouldBeFixed;
    }

    public int getTurnCanfixed() {
        return turnCanfixed;
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Turn> getTurns() {
        return turns;
    }

    public String getName() {
        return name;
    }
}
