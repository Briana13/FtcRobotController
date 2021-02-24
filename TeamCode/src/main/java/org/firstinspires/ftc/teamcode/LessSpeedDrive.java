package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class LessSpeedDrive  extends LinearOpMode {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;

    private DcMotor intake;
    private DcMotorEx flyWheel = null;
    private Servo Carlitos;

    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftRear = hardwareMap.get(DcMotor.class, "leftRear");
        rightRear = hardwareMap.get(DcMotor.class, "rightRear");
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        intake = hardwareMap.get(DcMotor.class, "intake");
        flyWheel = hardwareMap.get(DcMotorEx.class,"flyWheel");
        flyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        Carlitos = hardwareMap.get(Servo.class, "Carlitos");

        flyWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            intake.setPower(-.8);
            flyWheel.setVelocity(1600);
            if (this.gamepad1.b){
                Carlitos.setPosition(.5);
            }
            if (this.gamepad1.a){
                Carlitos.setPosition(0);
            }

            double r = Math.hypot(-gamepad1.left_stick_x, gamepad1.right_stick_x);
            double robotAngle = Math.atan2(gamepad1.right_stick_x, -gamepad1.left_stick_x) - Math.PI / 4;
            double leftY = -gamepad1.left_stick_y;


            final double v1 = r * Math.cos(robotAngle) + leftY;
            final double v2 = r * Math.sin(robotAngle) - leftY;
            final double v3 = r * Math.sin(robotAngle) + leftY;
            final double v4 = r * Math.cos(robotAngle) - leftY;

            leftFront.setPower(v1);
            rightFront.setPower(v2);
            leftRear.setPower(v3);
            rightRear.setPower(v4);

        }
    }

}

