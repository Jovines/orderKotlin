import order.Order;
import order.OrderScene;
import order.Student;
import order.Turn;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Main.init();
        OrderScene orderScene = new OrderScene("");
        orderScene.addStudentAll(init());
        System.out.printf("");
    }


    static public List<Student> init() {
        List<Student> students = new ArrayList<>();
        students.add(new Student("������")
                .add(3, 1)
                .add(4, 1)
                .add(3, 2)
                .add(5, 2)
                .add(4, 3)
                .add(1, 4)
        );
        students.add(new Student("������")
                .add(2, 3)
                .add(4, 3)
                .add(1, 4)
                .add(3, 4)
        );
        students.add(new Student("������")
                .add(2, 1)
                .add(4, 1)
                .add(1, 4)
        );
        students.add(new Student("����Ȼ")
                .add(2, 1)
                .add(4, 2)
                .add(1, 4)
                .add(4, 4)
        );
        students.add(new Student("���Ľ�")
                .add(1, 1)
                .add(2, 1)
                .add(4, 1)
                .add(2, 2)
                .add(1, 4)
        );
        students.add(new Student("������")
                .add(1, 1)
                .add(3, 1)
                .add(5, 1)
                .add(2, 2)
                .add(4, 3)
                .add(1, 4)
        );
        students.add(new Student("������")
                .add(1, 1)
                .add(4, 1)
                .add(2, 1)
                .add(5, 1)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("����")
                .add(1, 4)
                .add(2, 4)
                .add(4, 2)
                .add(4, 3)
                .add(4, 4)
        );
        students.add(new Student("���ĸ�")
                .add(1, 4)
                .add(3, 3)
                .add(4, 3)
        );
        students.add(new Student("����")
                .add(1, 3)
                .add(1, 4)
                .add(4, 2)
                .add(4, 4)
        );
        students.add(new Student("������")
                .add(1, 3)
                .add(1, 4)
                .add(4, 2)
                .add(4, 4)
        );
        students.add(new Student("�Ŵ�")
                .add(1, 4)
                .add(4, 1)
                .add(4, 4)
                .add(5, 3)
        );
        students.add(new Student("�����")
                .add(1, 1)
                .add(2, 1)
                .add(5, 2)
                .add(3, 3)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("�ĠD")
                .add(1, 4)
                .add(4, 3)
        );
        students.add(new Student("������")
                .add(1, 1)
                .add(3, 1)
                .add(4, 2)
                .add(3, 3)
                .add(1, 3)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("�ν���")
                .add(1, 1)
                .add(3, 1)
                .add(4, 1)
                .add(2, 2)
                .add(4, 2)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("����")
                .add(1, 1)
                .add(3, 1)
                .add(5, 1)
                .add(2, 2)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("�޿�Դ")
                .add(1, 1)
                .add(2, 1)
                .add(5, 1)
                .add(4, 2)
                .add(3, 3)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("���ҽ�")
                .add(1, 1)
                .add(2, 1)
                .add(5, 1)
                .add(4, 2)
                .add(3, 3)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("�ƽ�")
                .add(3, 1)
                .add(5, 1)
                .add(1, 3)
                .add(2, 3)
                .add(3, 3)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("л����")
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("��С�")
                .add(1, 1)
                .add(3, 1)
                .add(2, 2)
                .add(4, 2)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("Ҷ���")
                .add(1, 1)
                .add(3, 1)
                .add(5, 1)
                .add(1, 3)
                .add(2, 3)
                .add(3, 3)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );

        students.add(new Student("����չ")
                .add(1, 1)
                .add(3, 1)
                .add(5, 1)
                .add(2, 2)
                .add(3, 3)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );

        return students;
    }
}
