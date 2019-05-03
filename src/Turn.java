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
     * ��ȡ��ǰ�������ѧ���Ŀ��а�ε��ܺ�
     * @return ѧ������ʣ����ʱ���ܺ�
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
     * �Ѱ�κ�ѧ�����л����
     * @param student ��Ҫ�󶨵�ѧ��
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
        stringBuilder.append("���ڡ�")
                .append(workDay)
                .append("���ڡ�")
                .append(dayTime)
                .append("�������ѧ����");
        if (students.size() == 0) {
            stringBuilder.append("��");
        } else {
            for (Student s :
                    students) {
                stringBuilder.append(s);
            }
        }
        return stringBuilder.toString();
    }
}
