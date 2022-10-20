package org.firstinspires.ftc.teamcode.mina.camera;

import org.firstinspires.ftc.teamcode.mina.RWConfig;
import org.firstinspires.ftc.teamcode.mina.RWRobot;
import org.firstinspires.ftc.teamcode.mina.events.StartEvent;
import org.firstinspires.ftc.teamcode.mina.utils.Telemetrie;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvPipeline;

public class RWOpenCV {

    public enum RWPipeType{
        NULL,
        CON,
        TAG;
    }

    public static OpenCvCamera camera;

    public static RWPipeType pipeType = RWPipeType.NULL;

    private static ConDetectorPipeline conDetectorPipeline;
    private static AprilTagDetectorPipeline aprilTagDetectorPipeline;

    public static void init(){
        if(RWRobot.startType == StartEvent.StartType.CONTROL && !RWConfig.OPENCV_IN_CONTROL)
            return;
        pipeType = RWPipeType.NULL;

        if(RWConfig.DEBUG) {
            int cameraMonitorViewId = RWRobot.opMode.hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", RWRobot.opMode.hardwareMap.appContext.getPackageName());
            camera = OpenCvCameraFactory.getInstance().createWebcam(RWConfig.webcamName, cameraMonitorViewId);
        }else {
            camera = OpenCvCameraFactory.getInstance().createWebcam(RWConfig.webcamName);
        }

        camera.setPipeline(new ConDetectorPipeline());

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(800, 448);
            }
            @Override
            public void onError(int errorCode)
            {
                Telemetrie.addTel(Telemetrie.TelType.ERR, "Camera OPENCV", "Nu s-a putut deschide");
            }
        });
    }

    public static void update(){
        if(RWRobot.startType == StartEvent.StartType.CONTROL && !RWConfig.OPENCV_IN_CONTROL)
            return;
        if(pipeType == RWPipeType.CON){

        }else if(pipeType == RWPipeType.TAG){

        }
    }

}
