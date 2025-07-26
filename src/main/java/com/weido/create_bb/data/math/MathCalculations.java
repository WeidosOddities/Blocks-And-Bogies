package com.weido.create_bb.data.math;

import net.minecraft.util.Mth;

public class MathCalculations {
    public record Point(float x, float y) {}

    public static Point calculateIntersection(Point point1, float radius1, Point point2, float radius2, boolean upperIntersection) {
        float distance = calculateDistance(point2, point1);
        float distanceSquared = Mth.square(distance);

        float r1Squared = Mth.square(radius1);
        float r2Squared = Mth.square(radius2);

        float x_offset = (distanceSquared - r2Squared + r1Squared) / (2 * distance);
        float y_offset = (upperIntersection ? -1 : 1) * (1 / (2 * distance)) *
                Mth.sqrt(4 * distanceSquared * r1Squared -
                Mth.square(distanceSquared - r2Squared + r1Squared));

        float angle = calculateAngle(point2, point1);

        float sinAngle = Mth.sin(angle);
        float cosAngle = Mth.cos(angle);

        float x = cosAngle * x_offset - sinAngle * y_offset + point1.x;
        float y = sinAngle * x_offset + cosAngle * y_offset + point1.y;

        return new Point(x, y);
    }
    public static Point calculateXIntersection(Point point, float radius, float targetY, boolean upperIntersection) {
        float dy = point.y - targetY;

        float x_offset = Mth.sqrt(Mth.square(radius) - Mth.square(dy));

        return new Point(
                point.x + (upperIntersection ? x_offset : -x_offset), targetY
        );
    }
    public static float calculateAngle(Point point2, Point point1) {
        float dx = point2.x - point1.x;
        float dy = point2.y - point1.y;

        return (float) Mth.atan2(dy, dx);
    }
    public static Point calculateConnectPoint(Point point2, Point point1, float connectionPoint) {
        float ratio = connectionPoint / calculateDistance(point2, point1);

        return new Point(
                point1.x + (ratio * (point2.x - point1.x)),
                point1.y + (ratio * (point2.y - point1.y))
        );
    }

    public static float calculateDistance(Point point2, Point point1) {
        float dx = point2.x - point1.x;
        float dy = point2.y - point1.y;
        return Mth.sqrt(Mth.square(dx) + Mth.square(dy));
    }
}
