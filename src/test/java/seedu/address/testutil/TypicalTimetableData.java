package seedu.address.testutil;

import seedu.address.timetable.TimetableData;

public class TypicalTimetableData {
    public static final int VALID_SEMESTER = 2;
    public static final String[] VALID_MODULE_CODE_ARRAY = {"EC1301", "CS2103T"};
    public static final String[] VALID_MODULE_LESSON_ARRAY = {"TUT:S28,LEC:1", "LEC:G16"};

    public static final int VALID_SEMESTER_2 = 1;
    public static final String[] VALID_MODULE_CODE_ARRAY_2 = {"CS2100", "EC1301"};
    public static final String[] VALID_MODULE_LESSON_ARRAY_2 = {"TUT:01", "TUT:S28"};
    public static final TimetableData VALID_DATA_2 = new TimetableDataBuilder()
            .withSemester(VALID_SEMESTER_2)
            .withModuleCodeArray(VALID_MODULE_CODE_ARRAY_2)
            .withModuleLessonArray(VALID_MODULE_LESSON_ARRAY_2)
            .build();


    private static final String[] typicalModuleCodeArray = {"CS2100"};
    private static final String[] typicalModuleLessonArray = {"TUT:01,LEC:1"};
    public static final TimetableData DATA = new TimetableDataBuilder()
            .withSemester(1)
            .withModuleCodeArray(typicalModuleCodeArray)
            .withModuleLessonArray(typicalModuleLessonArray)
            .build();
}
