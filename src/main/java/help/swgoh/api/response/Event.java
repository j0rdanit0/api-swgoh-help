package help.swgoh.api.response;

public class Event
{
    public String id;
    public int priority;
    public String name;
    public String summary;
    public String desc;
    public int type;
    public int status;
    public Schedule[] schedule;

    public class Schedule
    {
        public long start;
        public long end;
        public long show;
        public long hide;
    }
}
