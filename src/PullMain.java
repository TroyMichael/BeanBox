import dataContainers.Image;
import filter.implementedFilters.*;
import interfaces.Readable;
import pipes.implementedPipes.GenericPipe;

import java.io.StreamCorruptedException;

/**
 * Created by Michael on 09.11.2015.
 *
 */
public class PullMain {


    public static void main(String[] args) {

        //create pipes and filters for pull
        Source source = new Source("loetstellen.jpg");
        GenericPipe sendSourcePipe = new GenericPipe(source);

        LoadImageFilter loadImageFilter = new LoadImageFilter((Readable<String>) sendSourcePipe);
        GenericPipe loadImageDefineROIPipe = new GenericPipe(loadImageFilter);

        DefineROIFilter defineROIFilter = new DefineROIFilter((Readable<Image>) loadImageDefineROIPipe);
        GenericPipe defineROISegmentImagePipe = new GenericPipe(defineROIFilter);

        SegmentationFilter segmentationFilter = new SegmentationFilter((Readable<Image>) defineROISegmentImagePipe);
        GenericPipe segmentationDisturbancesPipe = new GenericPipe(segmentationFilter);

        RemoveDisturbancesFilter removeDisturbancesFilter = new RemoveDisturbancesFilter((Readable<Image>) segmentationDisturbancesPipe);
        GenericPipe removeDisturbancesCreateBlobsPipe = new GenericPipe<>(removeDisturbancesFilter);

        CreateBlobsFilter createBlobsFilter = new CreateBlobsFilter((Readable<Image>) removeDisturbancesCreateBlobsPipe);
        GenericPipe createBlobsSaveScreenshotPipe = new GenericPipe<>(createBlobsFilter);

        SaveScreenshotFilter saveScreenshotFilter = new SaveScreenshotFilter((Readable<Image>) createBlobsSaveScreenshotPipe);
        GenericPipe saveScreenshotCountBallsPipe = new GenericPipe<>(saveScreenshotFilter);

        CountBallsFilter countBallsFilter = new CountBallsFilter((Readable<Image>) saveScreenshotCountBallsPipe);
        GenericPipe sinkCountBallsPipe = new GenericPipe(countBallsFilter);

        Sink sink = new Sink((Readable<Image>) sinkCountBallsPipe);

        //PULL
        //run last filter read
        try {
            sink.read();
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }
}