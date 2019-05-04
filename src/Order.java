import java.util.ArrayList;
import java.util.List;

public class Order {

    private List<Student> students;
    private List<Turn> turns;
    private List<Turn> fixedTurns;

    static private Order order = null;


    public void addStudent(Student student) {
        students.add(student);
        /*��ʼ����*/
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
     * ����Ա���䵽���Ե�λ��
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
     * ��Ҫ�����������Ű�
     */
    void order() {
        /*����Ա���䵽���Ե�λ��*/
        initTurn(students, turns);
        /*��ֻ�����������������µİ��λ���ϵ���Ա���й̶�(�����Ѿ�����������ѧ���İ��),����ȡ��*/
        pickOutShouldFixed(turns, fixedTurns);
        List<Turn> filterTurns = filterTurns(turns, 3);
        List<Student> filterStudents = extractStudents(filterTurns, true);
        for (; ; ) {
            //���Ǳ�����������һ���˱��̶��������Σ���ֱ�Ӱ�����һ����Ҳ������
            for (int i = 0; i < filterTurns.size(); i++) {//������ѡ�����Ķ�Ϊ3���˵İ��
                Turn t = filterTurns.get(i);
                //�жϱ���ѡ�����İ�����Ƿ���һ����ȷ����Ա
                // ���������������ȷ������Ա���������֮ǰ���㷨��ֱ�ӰѸð��ת�Ƶ�ȷ������б�
                if (t.getFixedStudentCount() != 0) {
                    List<Student> middleStudents = searchNotFixedStudent(t);//�ҳ�2��δ�̶���ѧ��
                    if (middleStudents.size() == 0) {
                        continue;
                    }
                    t.addFixedStudengt(middleStudents.get(0));//�󶨿��а�θ�����С�Ǹ�
                    pickOutShouldFixed(turns, fixedTurns);//����
                    filterTurns = filterTurns(turns, 3);//����Ѱ����ѡ��δ��ȷ��3�˰��
                    filterStudents = extractStudents(filterTurns, true);//�ҵ����е�ѧ��
                    i--;
                }
            }
            if (turns.size() == 0) {
                break;
            }
            //�����filterTurns��������е�ѧ�����Ǳ�������һ�Σ��������ܱ�������filterTurns����İ����
            //��Ϊ������㷨�Ѿ��޳���filterTurns������һ��б����ŵ�һ�ε���
            Student student = filterStudents.get(0);//�õ����а�����ٵ��Ǹ���
            for (int i = 0; i < student.getSuitableTurns().size(); i++) {
                Turn turn = student.getSuitableTurns().get(i);//�������ѧ���ܹ����ŵİ��
                for (Turn tFilter : filterTurns) {//������ǰȡ�������⼸�����
                    if (tFilter == turn) {//�����ѧ���ܹ���������ֵ��
                        turn.addFixedStudengt(student);//�������κ�ѧ����
                        pickOutShouldFixed(turns, fixedTurns);//ˢ��
                    }
                }
            }
            filterTurns = filterTurns(turns, 3);
            filterStudents = extractStudents(filterTurns, true);
        }
    }


    /**
     * ��Ŀ���ѧ���б���ɾ��ĳ��ѧ����������б����ж����ͬѧ������һ��ִ��ֻɾ��һ����
     *
     * @param goalList    Ŀ���б�
     * @param goalStudent Ŀ��ѧ��
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
     * ��δ���ųɹ��İ���б����޳��Ѿ�ȷ�����������ε�ͬѧ
     * ����ɾ���Ѿ������������˵İ�ε������޹���Ա
     *
     * @param turns ����Ҫ�޳��Եİ���б�
     */
    private void removeFixed(List<Turn> turns) {
        for (Turn t1 : turns) {
            //���Ѿ��̶��������˵İ��ɾ��������Щ��
            if (t1.getFixedStudentCount() >= 2) {//�жϵ�ǰ����Ƿ��Ѿ��Ѿ�������������
                for (int i = 0; i < t1.getStudents().size(); i++) {//������ǰ��ε�ѧ��
                    Student s1 = t1.getStudents().get(i);//�������ѧ��s1������
                    boolean n = false;
                    for (Student s2 : t1.getFixedStudents()) {//������ǰ������̶���ѧ��
                        if (s1 == s2) {//����(s1)�Ƿ��Ǳ��̶��ڵ�ǰ��ε���
                            n = true;
                        }
                    }
                    if (!n) {//���������s1�����ǣ���ӵ�ǰ��ε�ѧ����ɾ������
                        formTurnListDeleteStudent(t1.getStudents(), s1);
                        i--;
                    }
                }
            } else {
                //�԰��������ε�ѧ����������ν����޳�
                for (int i = 0; i < t1.getStudents().size(); i++) {//������ǰ��ε�δ�̶���ѧ���б�
                    Student s1 = t1.getStudents().get(i);//s1Ϊ����δ�̶�ѧ��
                    if (s1.getFixedCount() >= 2) {//�ҳ��Ǹ��Ѿ������������μ����ϵ�ѧ��
                        boolean isExist = false;
                        for (Turn t2 : s1.getFixedTurns()) {//�鿴��������������μ����ϵ�ѧ�����Ƿ����ڵ�ǰ���
                            if (t1 == t2) {
                                isExist = true;
                            }
                        }
                        if (!isExist) {//����������������ε�ѧ��û���ڵ�ǰ��Σ����ڵ�ǰ���ɾ����ѧ��
                            t1.getStudents().remove(s1);
                            i--;
                        }
                    }
                }
            }
        }
    }

    /**
     * ȡ���͹̶�����Ϊ2�����µİ��
     * ����ȡ���Ѿ��̶�����Ϊ2�İ��
     *
     * @param turns      ��ȡ����б�
     * @param fixedTurns �̶���Σ�Ŀ�����б�
     */
    private void pickOutShouldFixed(List<Turn> turns, List<Turn> fixedTurns) {
        //��֤�޳���׼ȷ��
        /*���������ɾ����Щ�Ѿ����������ε���*/
        removeFixed(turns);
        for (int i = 0; i < turns.size(); i++) {
            Turn turn = turns.get(i);
            if (turn.getStudentNumber() <= 2 && turn.getStudentNumber() > 0) {//�����ǰ��ε�ѧ����0������֮�䣬��Ե�ǰ��ε���Ա�̶�
                for (Student s : turn.getStudents()) {
                    turn.addFixedStudengt(s);
                }
                fixedTurns.add(turn);//����ǰ�����ӵ��̶�����б�
                turns.remove(turn);//���ͬʱ��δ��ɰ���б���ɾ��
                pickOutShouldFixed(turns, fixedTurns);//����turns��fixedTurns
                i = 0;
            } else if (turn.getFixedStudentCount() == 2) {//ȡ���Ѿ��̶�����Ϊ2�İ��
                fixedTurns.add(turn);
                turns.remove(turn);//�������
                i--;
            } else if (turn.getStudentNumber() == 0) {//���Ϊ�յ��ִ�
                turns.remove(turn);//�������
                i--;
            }
        }
    }


    /**
     * �ҵ�ָ���İ����δ���̶���ѧ��
     *
     * @param turn ��Ҫ�����İ��
     * @return ����ѧ���б�
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
     * ѧ��ð�����򣬽�ѧ�����տ��а��ʱ��ĸ�������С��������
     *
     * @param students ��Ҫ�����ѧ���б�
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
     * ���ð�����򣬽���ΰ����������ѧ���Ŀ��а��ʱ����ܸ�������С��������
     *
     * @param turns ��Ҫ����İ���б�
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
     * �Ӱ���б�ȡ����Ӧ�����İ��
     *
     * @param students ����Ҫȡ���İ��������ڵ�����
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
     * �Ӱ�ε��б�����ȡ��ͬѧ�б�
     *
     * @param turns    ����Ҫ��ȡ���ִ��б�
     * @param isRepeat ͬѧ�б������Ԫ���Ƿ�����ظ�
     * @return ����һ��ѧ���б������մ�ѧ���Ŀ��пδεĶ��ٴӸߵ������У�
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
        //ð������
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
