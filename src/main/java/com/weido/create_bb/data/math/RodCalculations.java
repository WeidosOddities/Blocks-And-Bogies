package com.weido.create_bb.data.math;

import net.createmod.catnip.math.AngleHelper;
import net.minecraft.util.Mth;

public class RodCalculations {
    public record WalschaertsParameters(
            float eccentricRod,
            float expansionLink,
            float eccentricCrankRadius,
            float expansionLinkX,
            float expansionLinkY,
            float radiusConnect,
            float mainCrankRadius,
            float mainRodLength,
            float radiusRod,
            float combinationLever,
            float combinationLeverUpper,
            float combinationLeverLower,
            float unionLink,
            float dropLink,
            float valveY,
            float pistonDistance,
            float yOffset,
            float xOffset
    ) {}

    public static float[] calculateGear(float wheelAngle, WalschaertsParameters params) {
        //I don't understand why certain values have to be negative, but it works, and I'm not going to complain 💀
        float a_main = AngleHelper.rad(wheelAngle);
        float a_eccentric = -AngleHelper.rad(wheelAngle + 90);

        MathCalculations.Point eccentricCrank = new MathCalculations.Point(
                params.eccentricCrankRadius() * Mth.sin(a_eccentric),
                params.eccentricCrankRadius() * Mth.cos(a_eccentric)
        );

        MathCalculations.Point mainCrank = new MathCalculations.Point(
                params.mainCrankRadius() * Mth.sin(a_main),
                params.mainCrankRadius() * Mth.cos(a_main)
        );

        MathCalculations.Point mainRodEndPoint = MathCalculations.calculateXIntersection(
                mainCrank,
                params.mainRodLength(),
                0,false
        );

        MathCalculations.Point expansionLinkPivot = new MathCalculations.Point(
                params.expansionLinkX(),
                params.expansionLinkY()
        );

        MathCalculations.Point expansionLinkEndPoint = MathCalculations.calculateIntersection(
                eccentricCrank, params.eccentricRod(),
                expansionLinkPivot, params.expansionLink(),
                true
        );
        //Why does this have to be negative???? 😭
        MathCalculations.Point unionLinkStartPoint = new MathCalculations.Point(
                -mainRodEndPoint.x(),
                mainRodEndPoint.y() - params.dropLink()
        );

        MathCalculations.Point radiusRodStartPoint = MathCalculations.calculateConnectPoint(
                expansionLinkEndPoint,
                expansionLinkPivot,
                params.radiusConnect()
        );

        MathCalculations.Point valveInitialPoint = new MathCalculations.Point(
                params.expansionLinkX(),
                params.radiusConnect()
        );

        MathCalculations.Point initialValvePoint = MathCalculations.calculateXIntersection(
                valveInitialPoint,
                params.radiusRod(),
                params.valveY(), true
        );

        float leftBound = initialValvePoint.x() - params.combinationLeverUpper();
        float rightBound = initialValvePoint.x() + params.combinationLeverUpper();
        float bestX = initialValvePoint.x();
        float minDistance = Float.MAX_VALUE;

        //Binary search for the minimal position of the valve/combination lever.
        for (int i = 0; i < 10; i++) {
            float leftX = (2 * leftBound + rightBound) / 3;
            float rightX = (leftBound + 2 * rightBound) / 3;

            float leftDistance = calculateValvePosition(leftX, params.valveY(),
                    unionLinkStartPoint, params, radiusRodStartPoint);
            float rightDistance = calculateValvePosition(rightX, params.valveY(),
                    unionLinkStartPoint, params, radiusRodStartPoint);

            if (leftDistance < rightDistance) {
                rightBound = rightX;
                if (leftDistance < minDistance) {
                    minDistance = leftDistance;
                    bestX = leftX;
                }
            } else {
                leftBound = leftX;
                if (rightDistance < minDistance) {
                    minDistance = rightDistance;
                    bestX = rightX;
                }
            }
        }

        MathCalculations.Point valveCalculationPoint = new MathCalculations.Point(
                bestX, params.valveY()
        );

        MathCalculations.Point unionLinkCalculationEnd = MathCalculations.calculateIntersection(
                unionLinkStartPoint, params.unionLink(),
                valveCalculationPoint, params.combinationLeverLower(),
                true
        );

        MathCalculations.Point radiusRodCalculationEnd = MathCalculations.calculateIntersection(
                radiusRodStartPoint, params.radiusRod(),
                valveCalculationPoint, params.combinationLeverUpper(),
                false
        );

        return new float[]{
                mainRodEndPoint.x(),

                expansionLinkEndPoint.x(), expansionLinkEndPoint.y(),
                radiusRodStartPoint.x(), radiusRodStartPoint.y(),
                unionLinkStartPoint.x(),
                valveCalculationPoint.x(),

                //Adjusted to model rotation
                Mth.PI - MathCalculations.calculateAngle(mainRodEndPoint, mainCrank),
                MathCalculations.calculateAngle(expansionLinkEndPoint, eccentricCrank),
                Mth.PI/2 + MathCalculations.calculateAngle(expansionLinkEndPoint, expansionLinkPivot),
                Mth.PI + MathCalculations.calculateAngle(unionLinkStartPoint, unionLinkCalculationEnd),
                Mth.PI + MathCalculations.calculateAngle(radiusRodStartPoint, radiusRodCalculationEnd),
                -Mth.PI/2 + MathCalculations.calculateAngle(valveCalculationPoint, unionLinkCalculationEnd)
        };
    }

    private static float calculateValvePosition(float x, float y, MathCalculations.Point unionLinkStartPoint, WalschaertsParameters params, MathCalculations.Point radiusRodStartPoint) {
        MathCalculations.Point valvePos = new MathCalculations.Point(x, y);

        MathCalculations.Point unionLinkEnd = MathCalculations.calculateIntersection(
                unionLinkStartPoint, params.unionLink(),
                valvePos, params.combinationLeverLower(),
                true
        );

        MathCalculations.Point radiusRodEnd = MathCalculations.calculateIntersection(
                radiusRodStartPoint, params.radiusRod(),
                valvePos, params.combinationLeverUpper(),
                false
        );

        MathCalculations.Point combinationLeverEnd = MathCalculations.calculateConnectPoint(
                unionLinkEnd,
                valvePos,
                -params.combinationLeverUpper()
        );

        return MathCalculations.calculateDistance(radiusRodEnd, combinationLeverEnd);
    }
}
