package services;

import models.Member;
import models.Coach;
import utils.FileUtils;
import java.util.List;

public class ScheduleService {
    private static final String SCHEDULE_FILE = "data/schedules.txt";

    public void createSchedule(Coach coach, Member member, String schedule) {
        String data = String.format("%s,%s,%s", 
            coach.getCoachId(),
            member.getMemberId(),
            schedule
        );
        FileUtils.writeToFile(SCHEDULE_FILE, data, true);
        member.setSchedule(schedule);
    }

    public String getSchedule(Member member) {
        List<String> schedules = FileUtils.readFromFile(SCHEDULE_FILE);
        return schedules.stream()
            .filter(line -> line.split(",")[1].equals(member.getMemberId()))
            .map(line -> line.split(",")[2])
            .findFirst()
            .orElse("No schedule found");
    }
}