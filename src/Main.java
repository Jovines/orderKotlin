import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class Main {
    public static void main(String[] args) {
        Main.init();
        Order.getInstance().order();


    }


    static public List<Student> init() {
        List<Student> students = Order.getInstance().getStudents();
        students.add(new Student("C")
                .add(1, 4)
                .add(3, 1)
                .add(5, 2)
        );
        students.add(new Student("V")
                .add(4, 1)
                .add(5, 3)
                .add(5, 2)
                .add(3, 1)
        );
        students.add(new Student("B")
                .add(2, 4)
                .add(2, 2)
                .add(3, 1)
                .add(4, 2)
        );
        students.add(new Student("X")
                .add(1, 3)
                .add(3, 1)
                .add(2, 2)
                .add(3, 2)
                .add(5, 2)
        );
        students.add(new Student("Z")
                .add(5, 3)
                .add(3, 4)
                .add(3, 1)
                .add(1, 4)
        );
        students.add(new Student("L")
                .add(4, 2)
                .add(1, 3)
                .add(5, 3)
                .add(5, 2)
                .add(2, 4)
        );
        students.add(new Student("K")
                .add(1, 2)
                .add(4, 1)
                .add(2, 2)
                .add(1, 3)
        );
        students.add(new Student("Q")
                .add(3, 3)
                .add(5, 2)
                .add(1, 2)
                .add(2, 4)
        );
        students.add(new Student("W")
                .add(2, 4)
                .add(4, 1)
                .add(3, 3)
                .add(2, 1)
        );
        students.add(new Student("E")
                .add(2, 2)
                .add(3, 4)
                .add(2, 4)
                .add(2, 3)
        );
        students.add(new Student("M")
                .add(1, 3)
                .add(1, 2)
                .add(2, 1)
                .add(5, 3)
        );
        students.add(new Student("R")
                .add(2, 3)
                .add(2, 4)
                .add(1, 4)
                .add(2, 1)
        );
        students.add(new Student("T")
                .add(3, 1)
                .add(3, 2)
                .add(5, 3)
        );
        students.add(new Student("Y")
                .add(1, 4)
                .add(4, 3)
                .add(3, 3)
                .add(1, 3)
        );
        students.add(new Student("U")
                .add(2, 2)
                .add(4, 3)
        );
        return students;
    }
}
