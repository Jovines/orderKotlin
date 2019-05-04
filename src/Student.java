import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private boolean[][] suitableSchedule;//还剩时间表
    private List<Turn> suitableTurns;
    private boolean[][] totalSchedule;//时间表
    private int totalTimeCount = 0;//时间表上的空闲时间
    private int freeTimeCount = 0;//还剩的空闲时间
    private List<Turn> fixedTurns;
    private int fixedCount = 0;//已经被安排了多少次

    public Student(String name) {
        this();
        this.name = name;
    }

    public Student() {
        fixedTurns = new ArrayList<>();
        suitableTurns = new ArrayList<>();
        suitableSchedule = new boolean[5][4];
        totalSchedule = new boolean[5][4];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                suitableSchedule[i][j] = false;
                totalSchedule[i][j] = false;
            }
        }
    }

    void addSuitableTurn(Turn turn) {
        suitableTurns.add(turn);
    }

    public void reset() {
        freeTimeCount = totalTimeCount;
        fixedCount = 0;
        for (int i = 0, j = fixedTurns.size(); i < j; i++) {
            fixedTurns.remove(0);
        }
        for (int i = 0, j = suitableTurns.size(); i < j; i++) {
            suitableTurns.remove(0);
        }
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                suitableSchedule[i][j] = totalSchedule[i][j];
            }
        }
    }

    List<Turn> getSuitableTurns() {
        return suitableTurns;
    }

    public int getTotalTimeCount() {
        return totalTimeCount;
    }

    public boolean[][] getTotalSchedule() {
        return totalSchedule;
    }

    public void setFixedCount(int fixedCount) {
        this.fixedCount = fixedCount;
    }

    public void setFreeTimeCount(int freeTimeCount) {
        this.freeTimeCount = freeTimeCount;
    }

    void fixed(Turn turn) {
        suitableSchedule[turn.getWorkDay() - 1][turn.getDayTime() - 1] = false;
        fixedTurns.add(turn);
        suitableTurns.remove(turn);
        fixedCount++;
        freeTimeCount--;
    }


    public boolean[][] getSuitableSchedule() {
        return suitableSchedule;
    }

    int getFixedCount() {
        return fixedCount;
    }

    public Student add(int workDay, int dayTime) {
        suitableSchedule[workDay - 1][dayTime - 1] = true;
        totalSchedule[workDay - 1][dayTime - 1] = true;
        totalTimeCount++;
        freeTimeCount ++;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void remove(int workDay, int dayTime) {
        suitableSchedule[workDay - 1][dayTime - 1] = false;
        totalSchedule[workDay - 1][dayTime - 1] = false;
        totalTimeCount--;
        freeTimeCount--;
    }


    boolean check(int workDay, int dayTime) {
        return suitableSchedule[--workDay][--dayTime];
    }

    List<Turn> getFixedTurns() {
        return fixedTurns;
    }

    public int getFreeTimeCount() {
        return freeTimeCount;
    }

    public String getName() {
        return name;
    }


    @Override
    public String toString() {
        return name + "【" + freeTimeCount + '-' + fixedCount + "】";
    }
}
