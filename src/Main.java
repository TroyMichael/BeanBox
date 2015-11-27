import dataContainers.Image;
import filter.implementedFilters.*;
import pipes.BufferedSyncPipe;

/**
 * Created by Michael on 09.11.2015.
 *
 */
public class Main {

    private static final int MAX_BUFFER_SIZE = 24;

    //doesnt work
    public static void main(String[] args) {

        //create all pipes
        BufferedSyncPipe sourceLoadImagePipe = new BufferedSyncPipe(MAX_BUFFER_SIZE);
        BufferedSyncPipe loadImageDefineROIPipe = new BufferedSyncPipe(MAX_BUFFER_SIZE);
        BufferedSyncPipe defineROISegmentImagePipe = new BufferedSyncPipe(MAX_BUFFER_SIZE);
        BufferedSyncPipe segmentationDisturbancesPipe = new BufferedSyncPipe(MAX_BUFFER_SIZE);
        BufferedSyncPipe removeDisturbancesCreateBlobsPipe = new BufferedSyncPipe(MAX_BUFFER_SIZE);
        BufferedSyncPipe createBlobsSaveScreenshotPipe = new BufferedSyncPipe(MAX_BUFFER_SIZE);
        BufferedSyncPipe saveScreenshotCountBallsPipe = new BufferedSyncPipe(MAX_BUFFER_SIZE);
        BufferedSyncPipe countBallsSinkPipe = new BufferedSyncPipe(MAX_BUFFER_SIZE);

        //create all filters
        Source source = new Source(sourceLoadImagePipe, "src/loetstellen.png");
        LoadImageFilter loadImageFilter = new LoadImageFilter(sourceLoadImagePipe, loadImageDefineROIPipe);
        DefineROIFilter defineROIFilter = new DefineROIFilter(loadImageDefineROIPipe, defineROISegmentImagePipe);
        SegmentationFilter segmentationFilter = new SegmentationFilter(defineROISegmentImagePipe, segmentationDisturbancesPipe);
        RemoveDisturbancesFilter removeDisturbancesFilter = new RemoveDisturbancesFilter(segmentationDisturbancesPipe, removeDisturbancesCreateBlobsPipe);
        CreateBlobsFilter createBlobsFilter = new CreateBlobsFilter(removeDisturbancesCreateBlobsPipe, createBlobsSaveScreenshotPipe);
        SaveScreenshotFilter saveScreenshotFilter = new SaveScreenshotFilter(createBlobsSaveScreenshotPipe, saveScreenshotCountBallsPipe);
        CountBallsFilter countBallsFilter = new CountBallsFilter((interfaces.Readable<Image>) saveScreenshotCountBallsPipe);
        Sink sink = new Sink(countBallsSinkPipe);


        //PUSH
        //run first pipe write

        new Thread(source).start();
        new Thread(loadImageFilter).start();
        new Thread(defineROIFilter).start();
        new Thread(segmentationFilter).start();
        new Thread(removeDisturbancesFilter).start();
        new Thread(createBlobsFilter).start();
        new Thread(saveScreenshotFilter).start();
        new Thread(countBallsFilter).start();
        new Thread(sink).start();


//        //PULL
//        //run last pipe read
//        try {
//            countBallsFilter.read();
//        } catch (StreamCorruptedException e) {
//            e.printStackTrace();
//        }
    }
}