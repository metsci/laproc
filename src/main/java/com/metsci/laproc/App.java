package com.metsci.laproc;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
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
	public static void main( String[] args )
    {
        //Create an empty list of classifier data sets to populate with data sets from the example CSV file.
		List<ClassifierDataSet> dataSetList = new ArrayList<ClassifierDataSet>();

        //Create an empty list of tag headers to populate with tags from the example CSV file.
		List<TagHeader> tagHeaders = new ArrayList<TagHeader>();
        importData(dataSetList, tagHeaders);

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
            TagHeader tag1 = new TagHeader(line[0]); //fold
            TagHeader tag2 = new TagHeader(line[1]); //environment
            TagHeader tag3 = new TagHeader(line[2]); //exercise
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

                //Given that the 4th column is the truth value and the 5th column is the classifer score,
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
                	if(tags.get(0).contains(line[0]) && tags.get(0).contains(line[1]) && tags.get(0).contains(line[2])){
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
                	tagSet.add(line[0]);
                	tagSet.add(line[1]);
                	tagSet.add(line[2]);
                    tags.add(tagSet);
                    //Note: The name for classifier data sets will not be used until the sets are grouped together
                	ClassifierDataSet newSet = new ClassifierDataSet(tags, "meaningless");
                	newSet.add(point);
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
            System.out.println(e);
        }
    }
}
