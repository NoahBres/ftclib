package com.arcrobotics.ftclib.drivebase;

import com.arcrobotics.ftclib.hardware.Motor;

/**
 * #TODO: get DcMotor from FTC tree, or let user interface with the lib
 * This is a classfile representing the kinematics of a mecanum drivetrain
 * and controls their speed. The drive methods {@link #driveRobotCentric(double, double, double)}
 * and {@link #driveFieldCentric(double, double, double, double)} are meant to be put inside
 * of a loop. You can call them in {@code void loop()} in an OpMode and within
 * a {@code while (!isStopRequested() && opModeIsActive())} loop in the
 * {@code runOpMode()} method in LinearOpMode.
 *
 * For the derivation of mecanum kinematics, please watch this video:
 * https://www.youtube.com/watch?v=8rhAkjViHEQ.
 */
public class MecanumDrive extends RobotDrive {

    Motor[] motors;

    /**
     * Sets up the constructor for the mecanum drive.
     *
     * @param myMotors The array of motors in order of:
     *                 frontLeft, frontRight, backLeft, backRight.
     *                 Do not input in any other order.
     */
    public MecanumDrive(Motor[] myMotors) {
        motors = myMotors;
    }

    /**
     * Sets the range of the input, see RobotDrive for more info.
     *
     * @param min The minimum value of the range.
     * @param max The maximum value of the range.
     */
    public void setRange(double min, double max) {
        super.setRange(min, max);
    }

    /**
     * Sets the max speed of the drivebase, see RobotDrive for more info.
     *
     * @param value The maximum output speed.
     */
    public void setMaxSpeed(double value) {
        super.setMaxSpeed(value);
    }

    /**
     * Stop the motors.
     */
    @Override
    public void stopMotor() {
        motors[MotorType.kFrontLeft.value].stopMotor();
        motors[MotorType.kFrontRight.value].stopMotor();
        motors[MotorType.kBackLeft.value].stopMotor();
        motors[MotorType.kBackRight.value].stopMotor();
    }

    /**
     * Drives the robot from the perspective of the robot itself rather than that
     * of the driver.
     *
     * @param xSpeed
     * @param ySpeed
     * @param turnSpeed
     */
    public void driveRobotCentric(double xSpeed, double ySpeed, double turnSpeed) {
        driveFieldCentric(xSpeed, ySpeed, turnSpeed, 0.0);
    }

    public void driveFieldCentric(double xSpeed, double ySpeed,
                                  double turnSpeed, double gyroAngle) {
        xSpeed = clipRange(xSpeed);
        ySpeed = clipRange(ySpeed);

        Vector2d input = new Vector2d(xSpeed, ySpeed);
        input.rotate(-gyroAngle);

        double theta = Math.atan2(ySpeed, xSpeed);

        double[] wheelSpeeds = new double[4];
        wheelSpeeds[MotorType.kFrontLeft.value] =
                input.magnitude() * Math.sin(theta + Math.PI / 4) + turnSpeed;
        wheelSpeeds[MotorType.kFrontRight.value] =
                input.magnitude() * Math.sin(theta - Math.PI / 4) - turnSpeed;
        wheelSpeeds[MotorType.kBackLeft.value] =
                input.magnitude() * Math.sin(theta - Math.PI / 4) + turnSpeed;
        wheelSpeeds[MotorType.kBackRight.value] =
                input.magnitude() * Math.sin(theta + Math.PI / 4) - turnSpeed;

        normalize(wheelSpeeds);

        motors[MotorType.kFrontLeft.value].set(wheelSpeeds[MotorType.kFrontLeft.value] * maxOutput);
        motors[MotorType.kFrontRight.value].set(wheelSpeeds[MotorType.kFrontRight.value] * -maxOutput);
        motors[MotorType.kBackLeft.value].set(wheelSpeeds[MotorType.kBackLeft.value] * maxOutput);
        motors[MotorType.kBackRight.value].set(wheelSpeeds[MotorType.kBackRight.value] * -maxOutput);
    }

}
