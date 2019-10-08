package order;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public  class Order implements Serializable {


    public static int turnCanfixed = 2;//一个班次能被最多能被安排人数
    public static int studentShouldBeFixed = 2;//学生应该被安排次数

    public static void setStudentShouldBeFixed(int studentShouldBeFixed) {
        Order.studentShouldBeFixed = studentShouldBeFixed;
    }

    public static void setTurnCanfixed(int turnCanfixed) {
        Order.turnCanfixed = turnCanfixed;
    }

    /**
     * 主要函数，进行排班
     */
    public static void order(List<Student> students,List<Turn> turns,List<Turn> fixedTurns,int turnCanfixed,int studentShouldBeFixed) {
        reset(fixedTurns,turns,students);//进行所有数据的复位
        initTurn(students, turns);/*将人员分配到各自的位置*/
        pickOutShouldFixed(turns, fixedTurns,turnCanfixed,studentShouldBeFixed);/*将只有n或者n以下的班次位置上的人员进行固定(或者已经被安排n个学生的班次),并它取出*/

        List<Turn> filterTurns = null;//挑选出n+1个人班次，也就是现在turns里面还剩余的最少人数的班次
        for (; ; ) {
            for (int j = turnCanfixed+1;j<= Turn.maxStudentCount ; j++) {
                filterTurns = filterTurns(turns, j);//重新寻找挑选出未被确定n+1人班次
                if (filterTurns.size() != 0) {
                    break;
                }
            }

            if (turns.size() == 0) {
                break;
            }

            Turn maxFixedturn = selectMaxFixedCountTurn(filterTurns);//找到固定人员最多的那个班次
            List<Student> middleStudents = searchNotFixedStudent(maxFixedturn);//找出未固定的学生
            maxFixedturn.addFixedStudengt(middleStudents.get(0));//绑定空闲班次个数最小的那个学生
            pickOutShouldFixed(turns, fixedTurns,turnCanfixed,studentShouldBeFixed);//更新

        }
    }




    /**
     * 找出班次列表中被固定的人员最多的那个班次
     *
     * @param turns 所需要寻找的班次
     * @return 被固定人员最多的那个班次
     *
     */
    private static Turn selectMaxFixedCountTurn(List<Turn> turns) {
        Turn turn = turns.get(0);
        for (Turn t : turns) {
            if (t.getFixedStudentCount() > turn.getFixedStudentCount()) {
                turn = t;
            }
        }
        return turn;
    }


    /**
     * 将人员分配到各自的位置
     */
    private static void initTurn(List<Student> students, List<Turn> turns) {
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                Turn turn = new Turn(i + 1, j + 1);
                for (Student s : students) {
                    if (s.check(i + 1, j + 1)) {
                        turn.addStudengt(s);
                    }
                }
                if (turn.getStudentNumber() > Turn.maxStudentCount) {
                    Turn.maxStudentCount = turn.getStudentNumber();
                }
                if (turn.getStudentNumber() != 0) {
                    turns.add(turn);
                }
            }
        }
        sortTurns(turns);
    }


    /**
     * 复位，将其中的数据清空
     * @param fixedTurns 需要清空的班次列表
     * @param turns 需要清空的班次列表
     * @param students 需要重置的学生列表（这里不会清空这个列表只是会重置其中的学生对象的数据）
     */
    private static void reset(List<Turn> fixedTurns, List<Turn> turns, List<Student> students) {
        for (int i = 0, j = fixedTurns.size(); i < j; i++) {
            fixedTurns.get(0).reset();
            fixedTurns.remove(0);
        }
        for (int i = 0, j = turns.size(); i < j; i++) {
            turns.get(0).reset();
            turns.remove(0);
        }
        for (Student student : students) {
            student.reset();
        }

    }


    /**
     * 从目标的学生列表中删除某个学生（且如果列表中有多个相同学生对象，一次执行只删除一个）
     *
     * @param goalList    目标列表
     * @param goalStudent 目标学生
     */
    private static void formTurnListDeleteStudent(List<Student> goalList, Student goalStudent) {
        for (int i = 0; i < goalList.size(); i++) {
            Student s = goalList.get(i);
            if (goalStudent == s) {
                goalList.remove(s);
                break;
            }
        }
    }


    /**
     * 在未安排成功的班次列表中剔除已经确定安排了学生需要被次的同学
     * 或者删除已经安排了两个人的班次的其他无关人员
     *
     * @param turns 所需要剔除对的班次列表
     */
    private static void removeFixed(List<Turn> turns, int turnCanfixed, int studentShouldBeFixed) {
        for (Turn t1 : turns) {
            //对已经固定了班次所需个人的班次删除多余那些人
            if (t1.getFixedStudentCount() >= turnCanfixed) {//判断当前班次是否已经已经被安排了所需次
                for (int i = 0; i < t1.getStudents().size(); i++) {//遍历当前班次的学生
                    Student s1 = t1.getStudents().get(i);//假如这个学生s1是张三
                    boolean n = false;
                    for (Student s2 : t1.getFixedStudents()) {//遍历当前班次所固定的学生
                        if (s1 == s2) {//张三(s1)是否是被固定在当前班次的人
                            n = true;
                        }
                    }
                    if (!n) {//如果张三（s1）不是，则从当前班次的学生中删除张三
                        formTurnListDeleteStudent(t1.getStudents(), s1);
                        i--;
                    }
                }
            }
            //对安排了（单个学生所要安排次数）的学生在其他班次进行剔除
            for (int i = 0; i < t1.getStudents().size(); i++) {//遍历当前班次的未固定的学生列表
                Student s1 = t1.getStudents().get(i);//s1为具体未固定学生
                if (s1.getFixedCount() >= studentShouldBeFixed) {//找出那个已经被安排了（单个学生所要安排次数）及以上的学生
                    boolean isExist = false;
                    for (Turn t2 : s1.getFixedTurns()) {//查看这个被安排了两次及以上的学生，是否是在当前班次
                        if (t1 == t2) {
                            isExist = true;
                        }
                    }
                    if (!isExist) {//若这个被安排了两次的学生没有在当前班次，则在当前班次删除该学生
                        t1.getStudents().remove(s1);
                        i--;
                    }
                }
            }
        }

    }

    /**
     * 取出和固定人数为班次所需人数及以下的班次
     * 或者取出已经固定人数为班次所需人数的班次
     *
     * @param turns      待取班次列表
     * @param fixedTurns 固定班次（目标班次列表）
     */
    private static void pickOutShouldFixed(List<Turn> turns, List<Turn> fixedTurns, int turnCanfixed, int studentShouldBeFixed) {
        //保证剔除的准确性
        /*在其他班次删除那些已经安排了n次的人*/
        removeFixed(turns,turnCanfixed,studentShouldBeFixed);
        for (int i = 0; i < turns.size(); i++) {//遍历整个班次列表
            Turn turn = turns.get(i);
            if (turn.getStudentNumber() <= turnCanfixed && turn.getStudentNumber() > 0) {//如果当前班次的学生是0到要求人数之间，则对当前班次的人员固定
                for (Student s : turn.getStudents()) {
                    turn.addFixedStudengt(s);
                    removeFixed(turns,turnCanfixed,studentShouldBeFixed);
                }
                fixedTurns.add(turn);//将当前班次添加到固定班次列表
                turns.remove(turn);//与此同时从未完成班次列表里删除
                pickOutShouldFixed(turns, fixedTurns,turnCanfixed,studentShouldBeFixed);//更新turns和fixedTurns
                i = 0;
            } else if (turn.getFixedStudentCount() == turnCanfixed) {//取出已经固定人数为班次最大人数的班次

                fixedTurns.add(turn);
                turns.remove(turn);//进行清除
                i--;
            } else if (turn.getStudentNumber() == 0) {//清除为空的轮次
                turns.remove(turn);//进行清除
                i--;
            }
        }
    }


    /**
     * 找到指定的班次中未被固定的学生
     *
     * @param turn 所要搜索的班次
     * @return 返回学生列表
     */
    private static List<Student> searchNotFixedStudent(Turn turn) {
        List<Student> middleStudents = new ArrayList<>();
        for (Student student : turn.getStudents()) {//遍历班次内的学生
            boolean m = false;
            for (Student sF : turn.getFixedStudents()) {//遍历班次内已经被固定的学生
                if (sF == student) {
                    m = true;
                }
            }
            if (!m) {//如果这个学生没有在班次内被确定
                middleStudents.add(student);
            }
        }
        sortStudents(middleStudents);//排序，空闲班次，从小到大
        return middleStudents;
    }


    /**
     * 学生冒泡排序，将学生按照空闲班次时间的个数来从小到大排序
     *
     * @param students 需要排序的学生列表
     */
    private static void sortStudents(List<Student> students) {
        for (int i = 0; i < students.size() - 1; i++) {
            for (int j = 0; j < students.size() - 1 - i; j++) {
                if (students.get(j).getFreeTimeCount() > students.get(j + 1).getFreeTimeCount()) {
                    Student m = students.get(j + 1);
                    students.set(j + 1, students.get(j));
                    students.set(j, m);
                }
            }
        }
    }

    /**
     * 班次冒泡排序，将班次按照里面存在学生的空闲班次时间的总个数来从小到大排序
     *
     * @param turns 需要排序的班次列表
     */
    private static void sortTurns(List<Turn> turns) {
        for (int i = 0; i < turns.size() - 1; i++) {
            for (int j = 0; j < turns.size() - 1 - i; j++) {
                if (turns.get(j).getAllStudentCanCount() > turns.get(j + 1).getAllStudentCanCount()) {
                    Turn m = turns.get(j + 1);
                    turns.set(j + 1, turns.get(j));
                    turns.set(j, m);
                }
            }
        }
    }


    /**
     * 从班次列表取出对应人数的班次
     *
     * @param students 所需要取出的班次里面存在的人数
     */
    private static List<Turn> filterTurns(List<Turn> turns, int students) {
        List<Turn> filterTurns = new ArrayList<>();
        for (Turn t : turns) {
            if (t.getStudentNumber() == students) {
                filterTurns.add(t);
            }
        }
        sortTurns(filterTurns);
        return filterTurns;
    }

    /**
     * 从班次的列表里提取出同学列表
     *
     * @param turns    所需要提取的轮次列表
     * @param isRepeat 同学列表里面的元素是否可以重复
     * @return 返回一个学生列表（并按照从学生的空闲课次的多少从高到低排列）
     */
    private List<Student> extractStudents(List<Turn> turns, boolean isRepeat) {
        List<Student> students = new ArrayList<>();
        boolean isExists = false;
        for (Turn t : turns) {
            for (Student turnStudent : t.getStudents()) {
                if (isRepeat) {
                    students.add(turnStudent);
                } else {
                    for (Student s : students) {
                        if (s == turnStudent) {
                            isExists = true;
                        }
                    }
                    if (students.size() == 0 || !isExists) {
                        students.add(turnStudent);
                    }
                    isExists = false;
                }
            }
        }
        //冒泡排序
        sortStudents(students);
        return students;
    }


    public void nowPrintTurns(List<Turn> turns) {
        for (Turn t : turns) {
            System.out.println(t.toString());
        }
        System.out.println("==================================");
    }

    public void nowPrintStudents(List<Student> students) {
        for (Student s : students) {
            System.out.print(s.getName() + " ");
        }
        System.out.println("\n==================================");
    }
}
