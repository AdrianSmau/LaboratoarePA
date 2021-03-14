package com.company;

import java.util.Map;

public class Solution {
    private Map<Student, School> matching;
    private final boolean success;

    public Solution(boolean success, Map<Student, School> matching) {
        this.success = true;
        this.matching = matching;
    }

    public Solution(boolean success) {
        this.success = false;
        matching = null;
    }

    public Map<Student, School> getMatching() {
        return matching;
    }

    public void setMatching(Map<Student, School> matching) {
        this.matching = matching;
    }

    public String displaySolution() {
        if (success) {
            var stringBuilder = new StringBuilder();
            for (Map.Entry<Student, School> entry : matching.entrySet()) {
                stringBuilder.append("(");
                stringBuilder.append(entry.getKey());
                stringBuilder.append(":");
                stringBuilder.append(entry.getValue());
                stringBuilder.append(")");
                stringBuilder.append("\n");
            }
            return stringBuilder.substring(0, stringBuilder.toString().length() - 1);
        } else
            return "(Matching invalid)";
    }

    @Override
    public String toString() {
        return "[" + displaySolution() + "]";
    }
}
