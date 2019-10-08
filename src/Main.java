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
        System.out.printf(orderScene.getFixedTurns().toString());
        System.out.println(orderScene.getFixedTurns().size());
        System.out.println(orderScene.getStudents().size());
    }


    static public List<Student> init() {
        List<Student> students = new ArrayList<>();


        students.add(new Student("丁德桥")
                .add(1, 4)
                .add(3, 4)
                .add(4, 3)
                .add(2, 2)
        );

        students.add(new Student("苏亚州")
                .add(3, 1)
                .add(4, 1)
                .add(3, 2)
                .add(5, 2)
                .add(4, 3)
                .add(1, 4)
        );
        students.add(new Student("朱榆涛")
                .add(2, 3)
                .add(4, 3)
                .add(1, 4)
                .add(3, 4)
        );
        students.add(new Student("刘大泽")
                .add(2, 1)
                .add(4, 1)
                .add(1, 4)
        );
        students.add(new Student("王君然")
                .add(2, 1)
                .add(4, 2)
                .add(1, 4)
                .add(4, 4)
        );
        students.add(new Student("张文江")
                .add(1, 1)
                .add(2, 1)
                .add(4, 1)
                .add(2, 2)
                .add(1, 4)
        );
        students.add(new Student("李月月")
                .add(1, 1)
                .add(3, 1)
                .add(5, 1)
                .add(2, 2)
                .add(4, 3)
                .add(1, 4)
        );
        students.add(new Student("敬清清")
                .add(1, 1)
                .add(4, 1)
                .add(2, 1)
                .add(5, 1)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("李鑫")
                .add(1, 4)
                .add(2, 4)
                .add(4, 2)
                .add(4, 3)
                .add(4, 4)
        );
        students.add(new Student("王澳歌")
                .add(1, 4)
                .add(3, 3)
                .add(4, 3)
        );
        students.add(new Student("唐鑫")
                .add(1, 3)
                .add(1, 4)
                .add(4, 2)
                .add(4, 4)
        );
        students.add(new Student("周香怜")
                .add(1, 3)
                .add(1, 4)
                .add(4, 2)
                .add(4, 4)
        );
        students.add(new Student("张川")
                .add(1, 4)
                .add(4, 1)
                .add(4, 4)
                .add(5, 3)
        );
        students.add(new Student("刘浩瑜")
                .add(1, 1)
                .add(2, 1)
                .add(5, 2)
                .add(3, 3)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );
        students.add(new Student("夏燚")
                .add(1, 4)
                .add(2, 2)
                .add(4, 3)
        );
//        students.add(new Student("曾启宏")
//                .add(1, 1)
//                .add(3, 1)
//                .add(4, 2)
//                .add(3, 3)
//                .add(1, 3)
//                .add(1, 4)
//                .add(2, 4)
//                .add(4, 4)
//                .add(5, 4)
//        );
        students.add(new Student("何锦宇")
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
//        students.add(new Student("李潇")
//                .add(1, 1)
//                .add(3, 1)
//                .add(5, 1)
//                .add(2, 2)
//                .add(1, 4)
//                .add(2, 4)
//                .add(4, 4)
//                .add(5, 4)
//        );
        students.add(new Student("罗楷源")
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
//        students.add(new Student("秦岩杰")
//                .add(1, 1)
//                .add(2, 1)
//                .add(5, 1)
//                .add(4, 2)
//                .add(3, 3)
//                .add(1, 4)
//                .add(2, 4)
//                .add(4, 4)
//                .add(5, 4)
//        );
        students.add(new Student("唐近")
                .add(3, 1)
                .add(5, 1)
                .add(1, 3)
                .add(2, 3)
                .add(3, 3)
                .add(1, 4)
                .add(4, 4)
                .add(5, 4)
        );
//        students.add(new Student("谢中林")
//                .add(1, 4)
//                .add(2, 4)
//                .add(4, 4)
//                .add(5, 4)
//        );
        students.add(new Student("熊小璞")
                .add(1, 1)
                .add(3, 1)
                .add(2, 2)
                .add(4, 2)
                .add(1, 4)
                .add(2, 4)
                .add(4, 4)
                .add(5, 4)
        );

        students.add(new Student("袁钰钿")
                .add(1, 1)
                .add(1, 4)
                .add(2, 2)
                .add(2, 4)
                .add(3, 1)
                .add(4, 1)
                .add(4, 2)
                .add(4, 4)
                .add(5, 4)
        );


//        students.add(new Student("叶玉峰")
//                .add(1, 1)
//                .add(3, 1)
//                .add(5, 1)
//                .add(1, 3)
//                .add(2, 3)
//                .add(3, 3)
//                .add(1, 4)
//                .add(4, 4)
//                .add(5, 4)
//        );



//        students.add(new Student("张鹏展")
//                .add(1, 1)
//                .add(3, 1)
//                .add(5, 1)
//                .add(2, 2)
//                .add(3, 3)
//                .add(1, 4)
//                .add(2, 4)
//                .add(4, 4)
//                .add(5, 4)
//        );

        return students;
    }
}
