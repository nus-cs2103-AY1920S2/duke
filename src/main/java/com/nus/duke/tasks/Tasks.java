package com.nus.duke.tasks;

import javafx.util.Pair;
import com.nus.duke.parser.Parser;

public class Tasks {
    public enum TASK_STATUS {
        INCOMPLETE {
            public String toString() { return "\u2717"; }
        },
        COMPLETE {
            public String toString() {
                return "\u2713";
            }
        },
    }

    public enum TASK_CATEGORY {
        TODO {
            public String toString() {
                return "T";
            }
        },

        DEADLINE {
            public String toString() {
                return "D";
            }
        },

        EVENT {
            public String toString() {
                return "E";
            }
        },
    }

    private String name;
    private TASK_STATUS status;
    private TASK_CATEGORY category;

    public Tasks(String name) {
        this(name, TASK_STATUS.INCOMPLETE, TASK_CATEGORY.TODO);
    }
    public Tasks(String name, TASK_STATUS status, TASK_CATEGORY category) {
        this.name = name;
        this.status = status;
        this.category = category;
    }

    public String getName() {
        return this.name;
    }
    public TASK_STATUS getStatus() {
        return this.status;
    }
    public TASK_CATEGORY getType() {return this.category;}
    public void changeStatus(TASK_STATUS status) {
        this.status = status;
    }
    public void changeCategory(TASK_CATEGORY category) {
        this.category = category;
    }

    public boolean equals(Object obj) {
        if (obj == null) return false;
        if (obj == this) return true;
        if (!(obj instanceof Tasks)) return false;
        Tasks o = (Tasks) obj;
        return o.getName() == this.getName();
    }
}
