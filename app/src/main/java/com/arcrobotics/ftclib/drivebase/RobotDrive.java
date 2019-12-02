package com.arcrobotics.ftclib.drivebase;

public abstract class RobotDrive {

    public enum MotorType {
        kFrontLeft(0),
        kFrontRight(1),
        kBackLeft(2),
        kBackRight(3),
        kLeft(0),
        kRight(1),
        kBack(2);

        public final int value;

        MotorType(int value) {
            this.value = value;
        }
    }

}