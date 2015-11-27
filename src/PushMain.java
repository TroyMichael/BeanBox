import dataContainers.Image;
import filter.implementedFilters.*;
import interfaces.Writeable;
import pipes.implementedPipes.GenericPipe;

import java.io.StreamCorruptedException;

/**
 * Created by Michael on 09.11.2015.
 *
 */
public class PushMain {

    public static void main(String[] args) {

        Sink sink = new Sink();

        GenericPipe toSink = new GenericPipe();
        CountBallsFilter countBallsFilter = new CountBallsFilter((Writeable<Image>) toSink);

        GenericPipe toCountBallsFilter = new GenericPipe(countBallsFilter);
        SaveScreenshotFilter screenshotFilter = new SaveScreenshotFilter((Writeable<Image>) toCountBallsFilter);

        GenericPipe toScreenshotFilter = new GenericPipe(screenshotFilter);
        CreateBlobsFilter createBlobsFilter = new CreateBlobsFilter((Writeable<Image>) toScreenshotFilter);

        GenericPipe toCreateBlobFilter = new GenericPipe(createBlobsFilter);
        RemoveDisturbancesFilter removeDisturbancesFilter = new RemoveDisturbancesFilter((Writeable<Image>) toCreateBlobFilter);

        GenericPipe toRemoveDisturbancesFilter = new GenericPipe(removeDisturbancesFilter);
        SegmentationFilter segmentationFilter = new SegmentationFilter((Writeable<Image>) toRemoveDisturbancesFilter);

        GenericPipe toSegmentationFilter = new GenericPipe(segmentationFilter);
        DefineROIFilter defineROIFilter = new DefineROIFilter((Writeable<Image>) toSegmentationFilter);

        GenericPipe toDefineROIFilter = new GenericPipe(defineROIFilter);
        LoadImageFilter loadImageFilter = new LoadImageFilter((Writeable<Image>) toDefineROIFilter);

        GenericPipe toImageFilter = new GenericPipe(loadImageFilter);
        Source source = new Source((Writeable<String>) toImageFilter);

        //PUSH
        //run first filter write
        try {
            source.write("loetstellen.jpg");
        } catch (StreamCorruptedException e) {
            e.printStackTrace();
        }
    }
}