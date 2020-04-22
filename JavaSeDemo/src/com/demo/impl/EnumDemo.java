package com.demo.impl;

import com.demo.inter.TaskExcuter;

public enum EnumDemo implements TaskExcuter {

    APPLY {
        @Override
        public String gerPath() {
            return "APPLY";
        };
    },
    SIGN {
        @Override
        public String gerPath() {
            return "SIGN";
        };
    },
    NOSIGN {
        @Override
        public String gerPath() {
            return "NOSIGN";
        };
    };

    private EnumDemo(){

    }

}
