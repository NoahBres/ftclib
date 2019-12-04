# In Development: ftclib

Modeled off wpilib for FRC, the ftclib uses modularization and class dependencies
for easy robot programming. No need for multiple third-party libraries. Everything
can be found right here.

## Installation

Download the library and port to your ftc-app project.
In the build.gradle file of your teamcode, type the following:
```gradle
dependencies {
    compileOnly ':ftclib'
}
```

## Usage

For drivetrain kinematics, you can do:
```java
MecanumDrive dt = new MecanumDrive(motors);

x = gp1.joyLeft.x;
y = gp1.joyLeft.y;
turn = gp1.joyRight.x;

dt.driveRobot(x, y, turn);
```
For a simple CV pipeline using a camera server, you can do:
```java
Camera cmr = new Camera("webcam");

res = cmr.getInstance();

if (res.hasSkystone()) robot.pathTo(res.atSkystone());
```