package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp

public class Drive  extends LinearOpMode {

    private DcMotor intake;
    private DcMotorEx flyWheel = null;
    private Servo Carlitos;

    public void runOpMode() {


        intake = hardwareMap.get(DcMotor.class, "intake");
        flyWheel = hardwareMap.get(DcMotorEx.class,"flyWheel");
        flyWheel.setDirection(DcMotorSimple.Direction.REVERSE);
        Carlitos = hardwareMap.get(Servo.class, "Carlitos");

        flyWheel.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            intake.setPower(-.8);
            flyWheel.setVelocity(1560);
            if (this.gamepad1.b){
                Carlitos.setPosition(.5);
            }
            if (this.gamepad1.a){
                Carlitos.setPosition(0);
            }
        }
    }

}


