package hello.objectprinciple.test0502.solution;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

public class WeeklyPlan implements RecurringPlan {
    private Set<DayOfWeek> dayOfWeeks;

    public WeeklyPlan(Set<DayOfWeek> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }

    @Override
    public boolean includes(LocalDate day) {
        return dayOfWeeks.contains(day.getDayOfWeek());
    }
}