package com.arcrobotics.ftclib.drivebase;

/**
 * Think of a vector as a ray with a starting point at the origin.
 * The point represented by (x, y) is the point that the ray's arrow points.
 * This represents the vector in 2d Cartesian space. A vector is essentially a value,
 * or magnitude, with a set direction. You can also put a vector in polar form:
 * (r, theta), where r is the magnitude and theta is the directional angle.
 */
public class Vector2d {

    public double x;
    public double y;

    /**
     * Default constructor, no params.
     */
    public Vector2d() {}

    /**
     * The constructor that sets up the x and y values for the 2d vector.
     *
     * @param x the x value of the vector
     * @param y the y value of the vector
     */
    public Vector2d(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Rotate a vector in Cartesian space.
     *
     * @param angle angle in degrees by which to rotate vector counter-clockwise.
     */
    public void rotate(double angle) {
        double cosA = Math.cos(angle * (Math.PI / 180.0));
        double sinA = Math.sin(angle * (Math.PI / 180.0));
        double[] out = new double[2];
        out[0] = x * cosA - y * sinA;
        out[1] = x * sinA + y * cosA;
        x = out[0];
        y = out[1];
    }

    public double angle() { return Math.atan2(y, x); }

    /**
     * Returns dot product of this vector with argument.
     *
     * @param vec Vector with which to perform dot product.
     */
    public double dot(Vector2d vec) {
        return x * vec.x + y * vec.y;
    }

    /**
     * Returns magnitude of vector.
     */
    public double magnitude() {
        return Math.sqrt(dot(this));
    }

    /**
     * Returns scalar projection of this vector onto argument.
     *
     * @param vec Vector onto which to project this vector.
     */
    public double scalarProject(Vector2d vec) {
        return dot(vec) / vec.magnitude();
    }

}
