import java.util.ArrayList;
import java.util.List;

public class Student {

    private String name;
    private boolean[][] suitableSchedule;//时间表
    private List<Turn> suitableTurns;
    private int freeTimeCount = 0;//时间表上的空闲时间
    private List<Turn> fixedTurns;
    private int fixedCount = 0;//已经被安排了多少次

    Student(String name) {
        this.name = name;
        fixedTurns = new ArrayList<>();
        suitableTurns = new ArrayList<>();
        suitableSchedule = new boolean[5][4];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                suitableSchedule[i][j] = false;
            }
        }
    }

    void addSuitableTurn(Turn turn) {
        suitableTurns.add(turn);
    }

    List<Turn> getSuitableTurns() {
        return suitableTurns;
    }

    void fixed(Turn turn) {
        suitableSchedule[turn.getWorkDay() - 1][turn.getDayTime() - 1] = false;
        fixedTurns.add(turn);
        suitableTurns.remove(turn);
        fixedCount++;
        freeTimeCount--;
    }



    int getFixedCount() {
        return fixedCount;
    }

    Student add(int workDay, int dayTime) {
        suitableSchedule[workDay - 1][dayTime - 1] = true;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (suitableSchedule[i][j]) {
                    freeTimeCount++;
                }
            }
        }
        return this;
    }

    public void remove(int workDay, int dayTime) {
        suitableSchedule[workDay - 1][dayTime - 1] = false;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 4; j++) {
                if (suitableSchedule[i][j]) {
                    freeTimeCount++;
                }
            }
        }
    }

    boolean check(int workDay, int dayTime) {
        return suitableSchedule[--workDay][--dayTime];
    }

    List<Turn> getFixedTurns() {
        return fixedTurns;
    }

    int getFreeTimeCount() {
        return freeTimeCount;
    }

    String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name + "【" + freeTimeCount + '-' + fixedCount + "】";
    }
}
