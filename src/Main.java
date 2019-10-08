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
        students.add(new Student("ËÕÑÇÖİ")
                .add(3, 1)
                .add(4, 1)
                .add(3, 2)
                .add(5, 2)
                .add(4, 3)
                .add(1, 4)
        );
        students.add(new Student("ÖìÓÜÌÎ")
                .add(2, 3)
                .add(4, 3)
                .add(1, 4)
                .add(3, 4)
        );
        students.add(new Student("Áõ´óÔó")
                .add(2, 1)
                .add(4, 1)
                .add(1, 4)
        );
        students.add(new Student("Íõ¾ıÈ»")
                .add(2, 1)
                .add(4, 2)
                .add(1, 4)
                .add(4, 4)
        );
        students.add(new Student("ÕÅÎÄ½­")
                .add(1, 1)
                .add(2, 1)
                .add(4, 1)
                .add(2, 2)
                .add(1, 4)
        );
        students.add(new Student("ÀîÔÂÔÂ")
                .add(1, 1)
                .add(3, 1)
                .add(5, 1)
                .add(2, 2)
                .add(4, 3)
                .add(1, 4)
        );
        students.add(new Student("¾´ÇåÇå")
                .add(1, 1)
                .add(4, 1)
                .add(2, 1)
                .add(5, 1)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("ÀîöÎ")
                .add(1, 4)
                .add(2, 4)
                .add(4, 2)
                .add(4, 3)
                .add(4, 4)
        );
        students.add(new Student("Íõ°Ä¸è")
                .add(1, 4)
                .add(3, 3)
                .add(4, 3)
        );
        students.add(new Student("ÌÆöÎ")
                .add(1, 3)
                .add(1, 4)
                .add(4, 2)
                .add(4, 4)
        );
        students.add(new Student("ÖÜÏãÁ¯")
                .add(1, 3)
                .add(1, 4)
                .add(4, 2)
                .add(4, 4)
        );
        students.add(new Student("ÕÅ´¨")
                .add(1, 4)
                .add(4, 1)
                .add(4, 4)
                .add(5, 3)
        );
        students.add(new Student("ÁõºÆè¤")
                .add(1, 1)
                .add(2, 1)
                .add(5, 2)
                .add(3, 3)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("ÏÄ D")
                .add(1, 4)
                .add(4, 3)
        );
        students.add(new Student("ÔøÆôºê")
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
        students.add(new Student("ºÎ½õÓî")
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
        students.add(new Student("Àîäì")
                .add(1, 1)
                .add(3, 1)
                .add(5, 1)
                .add(2, 2)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("ÂŞ¿¬Ô´")
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
        students.add(new Student("ÇØÑÒ½Ü")
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
        students.add(new Student("ÌÆ½ü")
                .add(3, 1)
                .add(5, 1)
                .add(1, 3)
                .add(2, 3)
                .add(3, 3)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("Ğ»ÖĞÁÖ")
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("ĞÜĞ¡è±")
                .add(1, 1)
                .add(3, 1)
                .add(2, 2)
                .add(4, 2)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("Ò¶Óñ·å")
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

        students.add(new Student("ÕÅÅôÕ¹")
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
