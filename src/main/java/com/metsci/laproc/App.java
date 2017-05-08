package com.metsci.laproc;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.metsci.laproc.application.Application;
import com.metsci.laproc.data.ClassifierDataSet;
import com.metsci.laproc.data.DataPoint;
import com.metsci.laproc.data.DataPointImpl;
import com.metsci.laproc.data.TagHeader;

/**
 * Example starting application for the laproc library
 */
public class App {
    private static boolean useFirstDataSet = true;

	public static void main( String[] args )
    {
        //Create an empty list of classifier data sets to populate with data sets from the example CSV file.
		List<ClassifierDataSet> dataSetList = new ArrayList<ClassifierDataSet>();

        //Create an empty list of tag headers to populate with tags from the example CSV file.
		List<TagHeader> tagHeaders = new ArrayList<TagHeader>();
        if(useFirstDataSet) {
            importData(dataSetList, tagHeaders);
        } else {
            importData2(dataSetList, tagHeaders);
        }

        //Initialize and run the application with data from the CSV file.
        Application application = new Application(dataSetList, tagHeaders);
        application.run();
    }

    /**
     * Imports example data into the application
     * @param dataSetList list of data sets to populate
     * @param tagHeaders list of tag headers to populate
     */
    private static void importData(List<ClassifierDataSet> dataSetList, List<TagHeader> tagHeaders) {
        try {
            //Create CSV reader for the example data set and read the first row.
            CSVReader reader = new CSVReader("..\\laproc\\test-data\\dataset1.csv");
            String[] line = reader.getLine();

            //The first row contains the headers for each set of tags.
            //Note: In this example, the 4th "header" is the truth value and the 5th is the classifier score.
            //      These values will be stored separately from the other tags as their values will be used in many
            //      calculations for the points.
            TagHeader tag0 = new TagHeader("classifier"); //Create a tag header to distinguish classifiers
            TagHeader tag1 = new TagHeader(line[0]);//fold
            TagHeader tag2 = new TagHeader(line[1]);//environment
            TagHeader tag3 = new TagHeader(line[2]);//exercise

            tagHeaders.add(tag0);
            tagHeaders.add(tag1);
            tagHeaders.add(tag2);
            tagHeaders.add(tag3);

            while(true) {
                line = reader.getLine();

                //The final line in the file has been reached.
                if(line == null)
                    break;

                //Each line beyond the first represents a single data point.
                DataPoint point;

                //Given that the 4th column is the truth value and the 5th column is the classifier score,
                //create a data point accordingly.
                if(line[3].equals("1"))
                    point = new DataPointImpl(true, Double.parseDouble(line[4]));
                else
                    point = new DataPointImpl(false, Double.parseDouble(line[4]));

                //A classifier data set is a set of data points that share the same tags.
                //Check if the a classifier data set with the new point's tags has already been created
                boolean found = false;
                for(ClassifierDataSet set : dataSetList){
                    List<List<String>> tags = set.getTags();
                    if(tags.get(0).contains("classifier0") &&
                            tags.get(0).contains(line[0]) &&
                            tags.get(0).contains(line[1]) &&
                            tags.get(0).contains(line[2])){
                		//If a matching classifier data set is found, add the point to the set
                	    set.add(point);
                		found = true;
                		break;
                	}
                }
                //If no matching classifier data set is found, create a new one with the point's tags
                if(!found){
                    ArrayList<List<String>> tags = new ArrayList<List<String>>();
                    ArrayList<String> tagSet = new ArrayList<String>();
                    tagSet.add("classifier0");
                    tagSet.add(line[0]);
                    tagSet.add(line[1]);
                    tagSet.add(line[2]);
                    tags.add(tagSet);
                    //Note: The name for classifier data sets will not be used until the sets are grouped together
                	ClassifierDataSet newSet = new ClassifierDataSet(tags, "meaningless");
                	newSet.add(point);
                	dataSetList.add(newSet);
                }

                //Create second classifier With random values for classifier scores in the same manner as the first
                DataPoint point1;
                if(line[3].equals("1"))
                    point1 = new DataPointImpl(true, Math.random());
                else
                    point1 = new DataPointImpl(false, Math.random());
                found = false;
                for(ClassifierDataSet set : dataSetList){
                    List<List<String>> tags = set.getTags();
                    if(tags.get(0).contains("classifier1") &&
                            tags.get(0).contains(line[0]) &&
                            tags.get(0).contains(line[1]) &&
                            tags.get(0).contains(line[2])){
                        set.add(point1);
                        found = true;
                        break;
                    }
                }
                if(!found){
                    ArrayList<List<String>> tags = new ArrayList<List<String>>();
                    ArrayList<String> tagSet = new ArrayList<String>();
                    tagSet.add("classifier1");
                    tagSet.add(line[0]);
                    tagSet.add(line[1]);
                    tagSet.add(line[2]);
                    tags.add(tagSet);
                    ClassifierDataSet newSet = new ClassifierDataSet(tags, "meaningless");
                    newSet.add(point1);
                    dataSetList.add(newSet);
                }
            }

            //Populate all the tag headers with the corresponding tags

            for(ClassifierDataSet dataSet : dataSetList){
                List<List<String>> tags = dataSet.getTags();
                List<String> tagSet = tags.get(0);
                for(int i = 0; i < tagSet.size(); i++){
                    if(!tagHeaders.get(i).getTags().contains(tagSet.get(i))){
                        tagHeaders.get(i).addTag(tagSet.get(i));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void importData2(List<ClassifierDataSet> dataSetList, List<TagHeader> tagHeaders) {
        try {
            CSVReader reader = new CSVReader("..\\laproc\\test-data\\dataset2.csv");
            String[] firstLine = reader.getLine();

            TagHeader tag0 = new TagHeader("Feature");
            TagHeader tag1 = new TagHeader("Exercise");

            tagHeaders.add(tag0);
            tagHeaders.add(tag1);

            while(true) {
                String[] line = reader.getLine();
                if (line == null)
                    break;
                for (int i = 0; i < line.length - 2; i++) {
                    DataPoint point;
                    if (line[line.length-2].equals("1"))
                        point = new DataPointImpl(true, Double.parseDouble(line[i]));
                    else
                        point = new DataPointImpl(false, Double.parseDouble(line[i]));
                    boolean found = false;
                    for (ClassifierDataSet set : dataSetList) {
                        List<List<String>> tags = set.getTags();
                        if (tags.get(0).contains(firstLine[i]) &&
                                tags.get(0).contains(line[line.length-1])) {
                            set.add(point);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        ArrayList<List<String>> tags = new ArrayList<List<String>>();
                        ArrayList<String> tagSet = new ArrayList<String>();
                        tagSet.add(firstLine[i]);
                        tagSet.add(line[line.length-1]);
                        tags.add(tagSet);
                        ClassifierDataSet newSet = new ClassifierDataSet(tags, "meaningless");
                        newSet.add(point);
                        dataSetList.add(newSet);
                    }
                }
            }
            for(ClassifierDataSet dataSet : dataSetList){
                List<List<String>> tags = dataSet.getTags();
                List<String> tagSet = tags.get(0);
                for(int i = 0; i < tagSet.size(); i++){
                    if(!tagHeaders.get(i).getTags().contains(tagSet.get(i))){
                        tagHeaders.get(i).addTag(tagSet.get(i));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
