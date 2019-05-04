import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Student> students;
    private List<Turn> turns;
    private List<Turn> fixedTurns;

    static private Order order = null;


    public void addStudent(Student student) {
        students.add(student);
        /*开始排序*/
        order();
    }

    public List<Student> getStudents() {
        return students;
    }

    public List<Turn> getFixedTurns() {
        return fixedTurns;
    }

    private Order() {
        students = new ArrayList<>();
        turns = new ArrayList<>();
        fixedTurns = new ArrayList<>();
    }

    static public Order getInstance() {
        if (order == null) {
            synchronized (Order.class) {
                if (order == null) {
                    order = new Order();
                }
            }
        }
        return order;
    }

    /**
     * 将人员分配到各自的位置
     */
    private void initTurn(List<Student> students, List<Turn> turns) {
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
        sortStudents(students);
    }


    /**
     * 主要函数，进行排班
     */
    void order() {
        /*将人员分配到各自的位置*/
        initTurn(students, turns);
        /*将只有两个或者两个以下的班次位置上的人员进行固定(或者已经被安排两个学生的班次),并它取出*/
        pickOutShouldFixed(turns, fixedTurns);
        List<Turn> filterTurns = filterTurns(turns, 3);
        List<Student> filterStudents = extractStudents(filterTurns, true);
        for (; ; ) {
            //若是本身这个班次有一个人被固定在这个班次，就直接把另外一个人也安排上
            for (int i = 0; i < filterTurns.size(); i++) {//遍历挑选出来的都为3个人的班次
                Turn t = filterTurns.get(i);
                //判断被挑选出来的班次中是否有一个被确定人员
                // （不会出现两个被确定的人员如果有在这之前的算法会直接把该班次转移到确定班次列表）
                if (t.getFixedStudentCount() != 0) {
                    List<Student> middleStudents = searchNotFixedStudent(t);//找出2个未固定的学生
                    if (middleStudents.size() == 0) {
                        continue;
                    }
                    t.addFixedStudengt(middleStudents.get(0));//绑定空闲班次个数最小那个
                    pickOutShouldFixed(turns, fixedTurns);//更新
                    filterTurns = filterTurns(turns, 3);//重新寻找挑选出未被确定3人班次
                    filterStudents = extractStudents(filterTurns, true);//找到其中的学生
                    i--;
                }
            }
            if (turns.size() == 0) {
                break;
            }
            //这里的filterTurns里面的所有的学生若是被安排了一次，绝不可能被安排在filterTurns里面的班次中
            //因为上面的算法已经剔除在filterTurns里面而且还有被安排的一次的人
            Student student = filterStudents.get(0);//得到空闲班次最少的那个人
            for (int i = 0; i < student.getSuitableTurns().size(); i++) {
                Turn turn = student.getSuitableTurns().get(i);//遍历这个学生能够安排的班次
                for (Turn tFilter : filterTurns) {//遍历当前取出来的这几个班次
                    if (tFilter == turn) {//若这个学生能够在这个班次值班
                        turn.addFixedStudengt(student);//则将这个班次和学生绑定
                        pickOutShouldFixed(turns, fixedTurns);//刷新
                    }
                }
            }
            filterTurns = filterTurns(turns, 3);
            filterStudents = extractStudents(filterTurns, true);
        }
    }


    /**
     * 从目标的学生列表中删除某个学生（且如果列表中有多个相同学生对象，一次执行只删除一个）
     *
     * @param goalList    目标列表
     * @param goalStudent 目标学生
     */
    private void formTurnListDeleteStudent(List<Student> goalList, Student goalStudent) {
        for (int i = 0; i < goalList.size(); i++) {
            Student s = goalList.get(i);
            if (goalStudent == s) {
                goalList.remove(s);
                break;
            }
        }
    }


    /**
     * 在未安排成功的班次列表中剔除已经确定安排了两次的同学
     * 或者删除已经安排了两个人的班次的其他无关人员
     *
     * @param turns 所需要剔除对的班次列表
     */
    private void removeFixed(List<Turn> turns) {
        for (Turn t1 : turns) {
            //对已经固定了两个人的班次删除多余那些人
            if (t1.getFixedStudentCount() >= 2) {//判断当前班次是否已经已经被安排了两次
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
            } else {
                //对安排了两次的学生在其他班次进行剔除
                for (int i = 0; i < t1.getStudents().size(); i++) {//遍历当前班次的未固定的学生列表
                    Student s1 = t1.getStudents().get(i);//s1为具体未固定学生
                    if (s1.getFixedCount() >= 2) {//找出那个已经被安排了两次及以上的学生
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
    }

    /**
     * 取出和固定人数为2及以下的班次
     * 或者取出已经固定人数为2的班次
     *
     * @param turns      待取班次列表
     * @param fixedTurns 固定班次（目标班次列表）
     */
    private void pickOutShouldFixed(List<Turn> turns, List<Turn> fixedTurns) {
        //保证剔除的准确性
        /*在其他班次删除那些已经安排了两次的人*/
        removeFixed(turns);
        for (int i = 0; i < turns.size(); i++) {
            Turn turn = turns.get(i);
            if (turn.getStudentNumber() <= 2 && turn.getStudentNumber() > 0) {//如果当前班次的学生是0到两人之间，则对当前班次的人员固定
                for (Student s : turn.getStudents()) {
                    turn.addFixedStudengt(s);
                }
                fixedTurns.add(turn);//将当前班次添加到固定班次列表
                turns.remove(turn);//与此同时从未完成班次列表里删除
                pickOutShouldFixed(turns, fixedTurns);//更新turns和fixedTurns
                i = 0;
            } else if (turn.getFixedStudentCount() == 2) {//取出已经固定人数为2的班次
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
    private List<Student> searchNotFixedStudent(Turn turn) {
        List<Student> middleStudents = new ArrayList<>();
        for (Student s : turn.getStudents()) {
            boolean m = false;
            for (Student sF : turn.getFixedStudents()) {
                if (sF == s) {
                    m = true;
                }
            }
            if (!m) {
                middleStudents.add(s);
            }
        }
        sortStudents(middleStudents);
        return middleStudents;
    }


    /**
     * 学生冒泡排序，将学生按照空闲班次时间的个数来从小到大排序
     *
     * @param students 需要排序的学生列表
     */
    private void sortStudents(List<Student> students) {
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
    private void sortTurns(List<Turn> turns) {
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
    private List<Turn> filterTurns(List<Turn> turns, int students) {
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
